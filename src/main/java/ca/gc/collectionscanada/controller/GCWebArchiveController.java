package ca.gc.collectionscanada.controller;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.HtmlUtils;

import ca.gc.collectionscanada.common.config.GCWebArchiveConfig;
import ca.gc.collectionscanada.common.context.GCWebArchiveContext;
import ca.gc.collectionscanada.common.dao.DepartmentDAO;
import ca.gc.collectionscanada.common.dao.DepartmentWiseURLDAO;
import ca.gc.collectionscanada.common.util.PageUtilities;
import ca.gc.collectionscanada.common.util.SortUtility;
import ca.gc.collectionscanada.web.model.Department;
import ca.gc.collectionscanada.web.model.DepartmentWiseURLs;
import ca.gc.collectionscanada.web.model.FooterProperties;
import ca.gc.collectionscanada.web.model.HeaderProperties;
import ca.gc.collectionscanada.web.model.OpenSearchParser;
import ca.gc.collectionscanada.web.model.SearchItem;
import ca.gc.collectionscanada.web.model.SearchParameters;

/**
 * This is the main common controller class which will be called everytime during navigation
 * of GC Web Archive portal. This class provides all mappings required for this web application
 * 
 * @author khatrz
 *
 */
@Controller
public class GCWebArchiveController
{
	/**
    @Autowired
	private GCWebArchiveConfig config;
    @Autowired
    private DepartmentWiseURLDAO deptWiseDAO;
    @Autowired
    private DepartmentDAO deptDAO;
    */
	
	private Map<String, String> errorMessages = new HashMap<String, String>();    
    protected final Log logger = LogFactory.getLog(GCWebArchiveController.class);

	@RequestMapping("/")
	public String welcomeOne(HttpServletRequest request, Model model) {
		Locale locale = RequestContextUtils.getLocale(request);
		ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
		model = setHeaderAndFooterProperties(model, bundle);

		model.addAttribute("sectiontitle", bundle.getString("intro1.title"));
		model.addAttribute("locale", locale.toString());
		model.addAttribute("language", locale.getLanguage());

		return "intro1";
	}

    /*
    @RequestMapping("intro1")
    public String introViewOne(HttpServletRequest request, ModelMap model)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        logger.info((new StringBuilder()).append("SERVLET PATH=").append(request.getServletPath()).toString());
        
        model = setHeaderAndFooterProperties(model, bundle);

        model.addAttribute("sectiontitle", bundle.getString("intro1.title"));
        model.addAttribute("locale", locale.toString());
        model.addAttribute("language", locale.getLanguage());
        return "intro1";
    }
    
    @RequestMapping("intro2")
    public String introView(HttpServletRequest request, ModelMap model)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        logger.info((new StringBuilder()).append("SERVLET PATH=").append(request.getServletPath()).toString());
        
        model = setHeaderAndFooterProperties(model, bundle);

        model.addAttribute("sectiontitle", bundle.getString("intro2.title"));
        model.addAttribute("locale", locale.toString());
        model.addAttribute("language", locale.getLanguage());
        return "intro2";
    }

    @RequestMapping("search")
    public String searchView(HttpServletRequest request, ModelMap model)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        logger.info((new StringBuilder()).append("SERVLET PATH=").append(request.getServletPath()).toString());
        
        model = setHeaderAndFooterProperties(model, bundle);

        model.addAttribute("sectiontitle", bundle.getString("search.basicsearch.title"));
        model.addAttribute("language", locale.getLanguage());
        model.addAttribute("newSearch", new SearchParameters());
        return "search";
    }

    @RequestMapping("searchResult")
    public String searchResultView(SearchParameters searchParam, HttpServletRequest request, HttpServletResponse response, ModelMap myModel)
        throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        GCWebArchiveContext ctx = GCWebArchiveContext.getInstance();
        Locale locale = RequestContextUtils.getLocale(request);
        String query = null;
        String start = null;
        if(searchParam.getContainWord() != null)
        {
            SearchParameters searchParams = new SearchParameters();
            searchParams.setContainWord(searchParam.getContainWord());
            ctx.setSearchParams(searchParams);
            ctx.setLanguage(locale.getLanguage());
            ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
            ctx.setSectionTitle(bundle.getString("search.basicsearch.results.title"));
            myModel.addAttribute("language", locale.getLanguage());
            myModel.addAttribute("sectiontitle", bundle.getString("search.basicsearch.results.title"));
            myModel.addAttribute("searchParam", searchParam);
            query = searchParam.getContainWord().trim();
        } else
        {
            ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
            query = ctx.getSearchParams().getContainWord();
            start = request.getParameter("s");
            if(start == null)
                start = ctx.getStartIndex();
            myModel.addAttribute("language", locale.getLanguage());
            myModel.addAttribute("sectiontitle", bundle.getString("search.basicsearch.results.title"));
            myModel.addAttribute("searchParam", ctx.getSearchParams());
        }
        String originalQuery = query;
        String showAllParam = request.getParameter("sa");
        String searchBoxValue = "";
        int hitsPerDup = 1;
        boolean showAll = false;
        boolean morePages = false;
        
        if(showAllParam != null && showAllParam.equals("t")) {
            showAll = true;            
        }
        
        if (query != null) {
            query = query.trim();
            Pattern exactUrlMatch = Pattern
                    .compile("^exacturl:(https?://)?([^/]+)$");
            Matcher matcher = exactUrlMatch.matcher(query);
            if (matcher != null && matcher.matches()) {
                logger.info("Invalid exact URL Query: " + matcher.group(2));
                logger.info("Adding slash to query");
                if (matcher.group(1) != null && matcher.group(1).length() > 0)
                    query += "/";
            }
            query = new String(query.getBytes("ISO-8859-1"), "UTF-8");
            logger.info("Query is: " + query);
            searchBoxValue = PageUtilities.text2html(query, false);

            if (query.indexOf("site:") != -1) {
                logger.info("Query is a site query.");
                showAll = true;
            }

            query = URLEncoder.encode(query, config.getProperties()
                    .getProperty("text.defaultEncoding"));
            logger.info("Query: " + query);
        }

        if (start == null)
            start = "0";

        try {
            String viewerHost = "http://" + request.getServerName() + ":" + request.getServerPort();

            String openSearchURI = (String) config.getProperties().getProperty(
                    "opensearch.host");
            String parserClassName = (String) config.getProperties()
                    .getProperty("opensearch.parserclass");
            String resultsPerPage = (String) config.getProperties()
                    .getProperty("opensearch.resultsPerPage");

            OpenSearchParser parser = (OpenSearchParser) Class.forName(
                    parserClassName).newInstance();

            if (query != null) {
                // append exclusions from exclusions file to end of query
                String newQuery = query;
                String activeQueryFilter = config.getProperties().getProperty("exclusions.activateQueryFilter");
                if (activeQueryFilter != null && activeQueryFilter.trim().equals("true"))
                    newQuery = PageUtilities.appendExclusionsToQuery(query, config);
                // exclude images from query if no specific type specified
                if (originalQuery.indexOf("type:") == -1) {
                    if (Boolean.parseBoolean(config.getProperties()
                            .getProperty("basicsearch.onlyReturnHtml"))) {
                        if (query.trim().length() > 0)
                            newQuery += "+type:html";
                    }
                }
                String queryUrl = openSearchURI + "?query=" + newQuery
                        + "&start=" + start + "&hitsPerPage=" + resultsPerPage
                        + "&hitsPerDup=" + hitsPerDup;

                if (showAll)
                    queryUrl += "&dedupField=exacturl";
                logger.info("Query URL: " + queryUrl);
                parser.parseXmlFile(queryUrl);
            }

            List<SearchItem> items = parser.getItems();

            for (SearchItem item : items) {
                int titleWrapSize = Integer.parseInt((config.getProperties()
                        .getProperty("text.titleWrapSize")));
                int urlWrapSize = Integer.parseInt((config.getProperties()
                        .getProperty("text.urlWrapSize")));
                item.setWrappedLink(PageUtilities.wrapText(item.getLink(),
                        urlWrapSize));
                item.setTitle(PageUtilities.wrapText(item.getTitle(),
                        titleWrapSize));
                String description = item.getDescription();
                description = PageUtilities.escapeDescription(description);
                item.setDescription(description);
            }

            int totalResults = ((Integer) parser.getSearchStats().get(
                    "totalResults")).intValue();

            Map<String, Object> navLinks = new HashMap<String, Object>();

            if (totalResults > 0) {
                int startIndex = ((Integer) parser.getSearchStats().get(
                        "startIndex")).intValue();
                navLinks.put("previousLink", PageUtilities.buildPreviousLink(request
                        .getRequestURI(), query, startIndex, totalResults,
                        Integer.parseInt(resultsPerPage), showAll));
                navLinks.put("nextLink", PageUtilities.buildNextLink(request.getRequestURI(),
                        query, startIndex, totalResults, Integer
                                .parseInt(resultsPerPage), showAll));
            }

            logger.debug("Number of search items returned: " + items.size());

            myModel = setHeaderAndFooterProperties(myModel, ResourceBundle.getBundle("uiproperties", locale));
            myModel.put("viewerHost", viewerHost);
            myModel.put("searchItems", items);
            myModel.put("searchStats", parser.getSearchStats());
            myModel.put("navLinks", navLinks);
            myModel.put("showAll", showAll);
            myModel.put("searchBoxValue", searchBoxValue);
            myModel.put("searchQuery", HtmlUtils.htmlEscape(query));
            myModel.put("searchPageURL", HtmlUtils.htmlEscape(request
                    .getRequestURI()));
            myModel.put("locale", locale);


            if (!showAll)
                myModel.put("morePages", parser.hasMorePages());
            else {
                if (Integer.parseInt(start) + Integer.parseInt(resultsPerPage) < totalResults)
                    morePages = true;
                myModel.put("morePages", morePages);
            }
        } catch (ConnectException e) {
            String errorPage = request.getContextPath() + "/connecterror.jsp";
            logger
                    .info("Caught connection exception, redirecting to error page: "
                            + errorPage);
            e.printStackTrace();
            response.sendRedirect(errorPage);
        }
        
        
        ctx.setStartIndex(start);
        
        
        logger.info((new StringBuilder()).append("SERVLET PATH=").append(request.getServletPath()).toString());
        return "searchResult";
    }

    @RequestMapping(value="advancesearch")
    public String advanceSearchView(HttpServletRequest request, ModelMap model)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        logger.info((new StringBuilder()).append("SERVLET PATH=").append(request.getServletPath()).toString());
        model = setHeaderAndFooterProperties(model, bundle);
        model.addAttribute("sectiontitle", bundle.getString("search.advancesearch.title"));
        model.addAttribute("language", locale.getLanguage());
        model.addAttribute("newSearch", new SearchParameters());
        return "advancesearch";
    }

    @RequestMapping(value="advanceSearchResult")
    public String advanceSearchResults(SearchParameters searchParam, HttpServletRequest request, HttpServletResponse response, ModelMap myModel)
        throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException
    {
        String start = null;
        String uiKeywords = "";
        String uiNotKeywords = "";
        String query = "";
        String dateParam = "";
        boolean showAll = false;
        String datePattern = "^\\d{4}-\\d{2}-\\d{2}$";
        GCWebArchiveContext ctx = GCWebArchiveContext.getInstance();
        Locale locale = RequestContextUtils.getLocale(request);
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        String keywords = "";
        String notKeywords = "";
        String startDate = "";
        String endDate = "";
        String type = "";
        String url = "";
        if(searchParam.getContainWord() != null)
        {
            keywords = searchParam.getContainWord().trim();
            notKeywords = searchParam.getNotContainWord().trim();
            startDate = searchParam.getStartDate();
            endDate = searchParam.getEndDate();
            type = searchParam.getType();
            url = searchParam.getFromWebsite();
            SearchParameters searchParams = new SearchParameters();
            searchParams.setContainWord(searchParam.getContainWord());
            ctx.setSearchParams(searchParams);
            ctx.setLanguage(locale.getLanguage());
            ctx.setSectionTitle(ResourceBundle.getBundle("uiproperties", locale).getString("search.basicsearch.results.title"));
            myModel.addAttribute("language", locale.getLanguage());
            myModel.addAttribute("sectiontitle", bundle.getString("search.basicsearch.results.title"));
            myModel.addAttribute("searchParam", searchParam);
        } else
        {
            keywords = ctx.getSearchParams().getContainWord() == null || ctx.getSearchParams().getContainWord().equals("") ? "" : ctx.getSearchParams().getContainWord();
            notKeywords = ctx.getSearchParams().getNotContainWord() == null || ctx.getSearchParams().getNotContainWord().equals("") ? "" : ctx.getSearchParams().getNotContainWord();
            startDate = ctx.getSearchParams().getStartDate() == null || ctx.getSearchParams().getStartDate().equals("") ? "" : ctx.getSearchParams().getStartDate();
            endDate = ctx.getSearchParams().getEndDate() == null || ctx.getSearchParams().getEndDate().equals("") ? "" : ctx.getSearchParams().getEndDate();
            type = ctx.getSearchParams().getType() == null || ctx.getSearchParams().getType().equals("") ? "" : ctx.getSearchParams().getType();
            url = ctx.getSearchParams().getFromWebsite() == null || ctx.getSearchParams().getFromWebsite().equals("") ? "" : ctx.getSearchParams().getFromWebsite();
            start = request.getParameter("s");
            if(start == null)
                start = ctx.getStartIndex();
            myModel.addAttribute("language", locale.getLanguage());
            myModel.addAttribute("sectiontitle", ResourceBundle.getBundle("uiproperties", locale).getString("search.basicsearch.results.title"));
            myModel.addAttribute("searchParam", ctx.getSearchParams());
        }
        List<String> searchQueryParams = new ArrayList<String>();
        List<String> errors = new ArrayList<String>();
        
        if(keywords != null && !keywords.equals(""))
        {
            uiKeywords = new String(keywords.getBytes("ISO-8859-1"), "UTF-8");
            searchQueryParams.add(uiKeywords);
        }
        if(notKeywords != null && !notKeywords.equals(""))
        {
            uiNotKeywords = new String(notKeywords.getBytes("ISO-8859-1"), "UTF-8");
            String notKeywordsArray[] = notKeywords.split("\\s+");
            String arr$[] = notKeywordsArray;
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                String notKeyword = arr$[i$];
                searchQueryParams.add("-"+ notKeyword);
            }
        }
        
        myModel.put("notKeywords", uiNotKeywords);
        logger.debug("Keyword is: " + keywords);
        if(type != null && !type.equals(""))
            searchQueryParams.add("type:" + type);
        if(url != null && url.length() > 0)
        {
            String trimmedUrl = PageUtilities.trimStartOfUrl(url);
            searchQueryParams.add("url:" + trimmedUrl);
            showAll = true;
            myModel.put("url", HtmlUtils.htmlEscape(url));
        }
        if(startDate != null && startDate.trim().length() > 0)
        {
            dateParam = "date:";
            logger.info("Start date is not null...");
            if(endDate == null || endDate.trim().length() == 0)
                errors.add(errorMessages.get("endDateRequired"));
            if(!Pattern.matches(datePattern, startDate))
            {
                errors.add(errorMessages.get("invalidStartDate"));
                myModel.put("startDate", startDate);
            } else
            {
                startDate = startDate.replace("-", "");
                dateParam += startDate + "000000";
            }
        }
        if(endDate != null && endDate.trim().length() > 0)
        {
            logger.info("End date is not null...");
            if(startDate == null || startDate.trim().length() == 0)
                errors.add(errorMessages.get("startDateRequired"));
            if(!Pattern.matches(datePattern, endDate))
            {
                errors.add(errorMessages.get("invalidEndDate"));
                myModel.put("endDate", endDate);
            } else
            {
                endDate = endDate.replace("-", "");
                dateParam += "-" + endDate + "000000";
            }
            searchQueryParams.add(dateParam);
        }
        if(errors.size() <= 0)
        {
            logger.info("Errors size is 0");
            int count = 1;
            for (String param : searchQueryParams) {
                query += param;

                if (count < searchQueryParams.size())
                    query += " ";

                count++;
            }

            if(showAll){
            	query = query + "&sa=t";
            }
                
        } else
        {
            myModel.put("errors", errors);
        }
        String originalQuery = query;
        String showAllParam = request.getParameter("sa");
        String searchBoxValue = "";
        int hitsPerDup = 1;
        boolean morePages = false;
        if(showAllParam != null && showAllParam.equals("t"))
            showAll = true;
        
        if(query != null)
        {
            query = query.trim();
            Pattern exactUrlMatch = Pattern.compile("^exacturl:(https?://)?([^/]+)$");
            Matcher matcher = exactUrlMatch.matcher(query);
            if(matcher != null && matcher.matches())
            {
                logger.info((new StringBuilder()).append("Invalid exact URL Query: ").append(matcher.group(2)).toString());
                logger.info("Adding slash to query");
                if(matcher.group(1) != null && matcher.group(1).length() > 0)
                    query = (new StringBuilder()).append(query).append("/").toString();
            }
            query = new String(query.getBytes("ISO-8859-1"), "UTF-8");
            logger.info((new StringBuilder()).append("Query is: ").append(query).toString());
            searchBoxValue = PageUtilities.text2html(query, false);
            if(query.indexOf("site:") != -1)
            {
                logger.info("Query is a site query.");
                showAll = true;
            }
            query = URLEncoder.encode(query, config.getProperties().getProperty("text.defaultEncoding"));
            logger.info((new StringBuilder()).append("Query: ").append(query).toString());
        }
        if(start == null)
            start = "0";
        try
        {
        	String viewerHost = "http://" + request.getServerName() + ":" + request.getServerPort();
            String openSearchURI = config.getProperties().getProperty("opensearch.host");
            String parserClassName = config.getProperties().getProperty("opensearch.parserclass");
            String resultsPerPage = config.getProperties().getProperty("opensearch.resultsPerPage");
            OpenSearchParser parser = (OpenSearchParser)Class.forName(parserClassName).newInstance();
            if(query != null)
            {
                String newQuery = query;
                String activeQueryFilter = config.getProperties().getProperty("exclusions.activateQueryFilter");
                if(activeQueryFilter != null && activeQueryFilter.trim().equals("true"))
                    newQuery = PageUtilities.appendExclusionsToQuery(query, config);
                if(originalQuery.indexOf("type:") == -1 && Boolean.parseBoolean(config.getProperties().getProperty("basicsearch.onlyReturnHtml")) && query.trim().length() > 0)
                    newQuery = (new StringBuilder()).append(newQuery).append("+type:html").toString();
                String queryUrl = (new StringBuilder()).append(openSearchURI).append("?query=").append(newQuery).append("&start=").append(start).append("&hitsPerPage=").append(resultsPerPage).append("&hitsPerDup=").append(hitsPerDup).toString();
                if(showAll)
                    queryUrl = (new StringBuilder()).append(queryUrl).append("&dedupField=exacturl").toString();
                logger.info((new StringBuilder()).append("Query URL: ").append(queryUrl).toString());
                parser.parseXmlFile(queryUrl);
            }
            List<SearchItem> items = parser.getItems();

            for (SearchItem item : items) {
                int titleWrapSize = Integer.parseInt((config.getProperties()
                        .getProperty("text.titleWrapSize")));
                int urlWrapSize = Integer.parseInt((config.getProperties()
                        .getProperty("text.urlWrapSize")));
                item.setWrappedLink(PageUtilities.wrapText(item.getLink(),
                        urlWrapSize));
                item.setTitle(PageUtilities.wrapText(item.getTitle(),
                        titleWrapSize));
                String description = item.getDescription();
                description = PageUtilities.escapeDescription(description);
                item.setDescription(description);
            }

            int totalResults = ((Integer)parser.getSearchStats().get("totalResults")).intValue();
            Map<String, Object> navLinks = new HashMap<String, Object>();
            
            if(totalResults > 0)
            {
                int startIndex = ((Integer)parser.getSearchStats().get("startIndex")).intValue();
                navLinks.put("previousLink", PageUtilities.buildPreviousLink(request.getRequestURI(), query, startIndex, totalResults, Integer.parseInt(resultsPerPage), showAll));
                navLinks.put("nextLink", PageUtilities.buildNextLink(request.getRequestURI(), query, startIndex, totalResults, Integer.parseInt(resultsPerPage), showAll));
            }
            logger.debug("Number of search items returned: " + items.size());
            myModel.put("viewerHost", viewerHost);
            myModel.put("searchItems", items);
            myModel.put("searchStats", parser.getSearchStats());
            myModel.put("navLinks", navLinks);
            myModel.put("showAll", Boolean.valueOf(showAll));
            myModel.put("searchBoxValue", searchBoxValue);
            myModel.put("searchQuery", HtmlUtils.htmlEscape(query));
            myModel.put("searchPageURL", HtmlUtils.htmlEscape(request.getRequestURI()));
            if(!showAll)
            {
                myModel.put("morePages", Boolean.valueOf(parser.hasMorePages()));
            } else
            {
                if(Integer.parseInt(start) + Integer.parseInt(resultsPerPage) < totalResults)
                    morePages = true;
                myModel.put("morePages", Boolean.valueOf(morePages));
            }
        }
        catch(ConnectException e)
        {
            String errorPage = (new StringBuilder()).append(request.getContextPath()).append("/connecterror.jsp").toString();
            logger.info((new StringBuilder()).append("Caught connection exception, redirecting to error page: ").append(errorPage).toString());
            e.printStackTrace();
            response.sendRedirect(errorPage);
        }
        
        ctx.setStartIndex(start);
        logger.info("SERVLET PATH=" + request.getServletPath());
        return "searchResult";
    }
    
    @RequestMapping(value="deptWiseURL")
    public String deptWiseListView(HttpServletRequest request, ModelMap model)
    {
        logger.info("SERVLET PATH="+request.getServletPath());
        Locale locale = RequestContextUtils.getLocale(request);
        DepartmentWiseURLs masterArray[] = deptWiseDAO.retrieveArray();
        String letters[] = {
            "[0-9]", "A", "B", "C", "D", "E", "F", "G", "H", "I", 
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", 
            "T", "U", "V", "W", "X", "Y", "Z"
        };
        DepartmentWiseURLs fromAtoZ[][] = new DepartmentWiseURLs[letters.length][];
        ArrayList <DepartmentWiseURLs []>departmentWiseURLs = new ArrayList<DepartmentWiseURLs []>(letters.length);
        if(locale.getLanguage().equalsIgnoreCase("en"))
        {
            for(int i = 0; i < letters.length; i++)
            {
                ca.gc.collectionscanada.web.model.DepartmentWiseURLs array[] = SortUtility.sortDeptAlphabeticallyEng(masterArray, letters[i]);
                departmentWiseURLs.add(array);
            }

        } else
        {
            for(int i = 0; i < letters.length; i++)
            {
                ca.gc.collectionscanada.web.model.DepartmentWiseURLs array[] = SortUtility.sortDeptAlphabeticallyFr(masterArray, letters[i]);
                departmentWiseURLs.add(array);
            }

        }
        departmentWiseURLs.toArray(fromAtoZ);
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        model = setHeaderAndFooterProperties(model, bundle);

        model.addAttribute("sectiontitle", bundle.getString("url.list.title"));
        model.addAttribute("locale", locale);
        model.addAttribute("language", locale.getLanguage());
        model.addAttribute("letters", letters);
        model.addAttribute("fromAtoZ", fromAtoZ);
        return "deptWiseURL";
    }

    @RequestMapping(value="deptList")
    public String deptListView(HttpServletRequest request, ModelMap model)
    {
        logger.info("SERVLET PATH="+request.getServletPath());
        Locale locale = RequestContextUtils.getLocale(request);
        Department masterArray[] = deptDAO.retrieveArray();
        String letters[] = {
            "[0-9]", "A", "B", "C", "D", "E", "F", "G", "H", "I", 
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", 
            "T", "U", "V", "W", "X", "Y", "Z"
        };
        Department fromAtoZ[][] = new Department[letters.length][];
        ArrayList <Department []> alphabetDepts = new ArrayList<Department []>(letters.length);
        if(locale.getLanguage().equalsIgnoreCase("en"))
        {
            for(int i = 0; i < letters.length; i++)
            {
                Department array[] = SortUtility.sortDeptAlphabetsEng(masterArray, letters[i]);
                alphabetDepts.add(array);
            }

        } else
        {
            for(int i = 0; i < letters.length; i++)
            {
                Department array[] = SortUtility.sortDeptAlphabetsFr(masterArray, letters[i]);
                alphabetDepts.add(array);
            }

        }
        alphabetDepts.toArray(fromAtoZ);
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        
        model = setHeaderAndFooterProperties(model, bundle);

        model.addAttribute("sectiontitle", bundle.getString("dept.list.title"));
        model.addAttribute("language", locale.getLanguage());
        model.addAttribute("letters", letters);
        model.addAttribute("fromAtoZ", fromAtoZ);
        return "deptList";
    }

    @RequestMapping(value="help")
    public String helpView(HttpServletRequest request, ModelMap model)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        logger.info("SERVLET PATH="+request.getServletPath());
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        model = setHeaderAndFooterProperties(model, bundle);
        model.addAttribute("sectiontitle", bundle.getString("help.title"));
        model.addAttribute("language", locale.getLanguage());
        return "help";
    }

    @RequestMapping(value="faq")
    public String faqView(HttpServletRequest request, ModelMap model)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        logger.info((new StringBuilder()).append("SERVLET PATH=").append(request.getServletPath()).toString());
        model.addAttribute("language", locale.getLanguage());
        model.addAttribute("sectiontitle", bundle.getString("faq.title"));
        return "faq";
    }

    @RequestMapping(value="techdetails")
    public String techDetailsView(HttpServletRequest request, ModelMap model)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        logger.info((new StringBuilder()).append("SERVLET PATH=").append(request.getServletPath()).toString());
        model.addAttribute("language", locale.getLanguage());
        model.addAttribute("sectiontitle", bundle.getString("tech.detail.title"));
        return "techdetails";
    }

    @RequestMapping(value="comments")
    public String commentView(HttpServletRequest request, ModelMap model)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        logger.info((new StringBuilder()).append("SERVLET PATH=").append(request.getServletPath()).toString());
        model = setHeaderAndFooterProperties(model, bundle);
        model.addAttribute("language", locale.getLanguage());
        model.addAttribute("sectiontitle", bundle.getString("comments.title"));
        return "comments";
    }
    
    @RequestMapping(value="elections")
    public String electionsView(HttpServletRequest request, ModelMap model)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        logger.info("SERVLET PATH="+request.getServletPath());
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        model = setHeaderAndFooterProperties(model, bundle);
        model.addAttribute("sectiontitle", bundle.getString("elections.title"));
        model.addAttribute("language", locale.getLanguage());
        return "elections";
    }
    
    @RequestMapping(value="elect2006")
    public String elections2006View(HttpServletRequest request, ModelMap model)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        logger.info("SERVLET PATH="+request.getServletPath());
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        model = setHeaderAndFooterProperties(model, bundle);
        model.addAttribute("sectiontitle", bundle.getString("elections.title.2006"));
        model.addAttribute("language", locale.getLanguage());
        return "elect2006";
    }
    
    @RequestMapping(value="elect2008")
    public String elections2008View(HttpServletRequest request, ModelMap model)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        logger.info("SERVLET PATH="+request.getServletPath());
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        model = setHeaderAndFooterProperties(model, bundle);
        model.addAttribute("sectiontitle", bundle.getString("elections.title.2008"));
        model.addAttribute("language", locale.getLanguage());
        return "elect2008";
    }
    
    @RequestMapping(value="elect2011")
    public String elections2011View(HttpServletRequest request, ModelMap model)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        logger.info("SERVLET PATH="+request.getServletPath());
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        model = setHeaderAndFooterProperties(model, bundle);
        model.addAttribute("sectiontitle", bundle.getString("elections.title.2011"));
        model.addAttribute("language", locale.getLanguage());
        return "elect2011";
    }
    
    @RequestMapping(value="raildisaster")
    public String raiDisasterView(HttpServletRequest request, ModelMap model)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        logger.info("SERVLET PATH="+request.getServletPath());
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        model = setHeaderAndFooterProperties(model, bundle);
        model.addAttribute("sectiontitle", bundle.getString("section.rail.disaster.title"));
        model.addAttribute("language", locale.getLanguage());
        return "raildisaster";
    }
    @RequestMapping(value="olympics")
    public String olympicsView(HttpServletRequest request, ModelMap model)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        logger.info("SERVLET PATH="+request.getServletPath());
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        model = setHeaderAndFooterProperties(model, bundle);
        model.addAttribute("sectiontitle", bundle.getString("section.olympics.games.title"));
        model.addAttribute("language", locale.getLanguage());
        return "olympics";
    }
    @RequestMapping(value="olymp2006")
    public String olymp2006View(HttpServletRequest request, ModelMap model)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        logger.info("SERVLET PATH="+request.getServletPath());
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        model = setHeaderAndFooterProperties(model, bundle);
        model.addAttribute("sectiontitle", bundle.getString("olympics.title.2006"));
        model.addAttribute("language", locale.getLanguage());
        return "olymp2006";
    }
    @RequestMapping(value="olymp2008")
    public String olymp2008View(HttpServletRequest request, ModelMap model)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        logger.info("SERVLET PATH="+request.getServletPath());
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        model = setHeaderAndFooterProperties(model, bundle);
        model.addAttribute("sectiontitle", bundle.getString("olympics.title.2008"));
        model.addAttribute("language", locale.getLanguage());
        return "olymp2008";
    }
    @RequestMapping(value="olymp2010")
    public String olymp2010View(HttpServletRequest request, ModelMap model)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        logger.info("SERVLET PATH="+request.getServletPath());
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        model = setHeaderAndFooterProperties(model, bundle);
        model.addAttribute("sectiontitle", bundle.getString("olympics.title.2010"));
        model.addAttribute("language", locale.getLanguage());
        return "olymp2010";
    }
    @RequestMapping(value="olymp2012")
    public String olymp2012View(HttpServletRequest request, ModelMap model)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        logger.info("SERVLET PATH="+request.getServletPath());
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        model = setHeaderAndFooterProperties(model, bundle);
        model.addAttribute("sectiontitle", bundle.getString("olympics.title.2012"));
        model.addAttribute("language", locale.getLanguage());
        return "olymp2012";
    }
    @RequestMapping(value="olymp2014")
    public String olymp2014View(HttpServletRequest request, ModelMap model)
    {
        Locale locale = RequestContextUtils.getLocale(request);
        logger.info("SERVLET PATH="+request.getServletPath());
        ResourceBundle bundle = ResourceBundle.getBundle("uiproperties", locale);
        model = setHeaderAndFooterProperties(model, bundle);
        model.addAttribute("sectiontitle", bundle.getString("olympics.title.2014"));
        model.addAttribute("language", locale.getLanguage());
        return "olymp2014";
    }
    */
	public Model setHeaderAndFooterProperties(Model model, ResourceBundle bundle) {
		HeaderProperties headerProp = new HeaderProperties();
		FooterProperties footerProp = new FooterProperties();

		// Following is for header properties
		headerProp.setAltTitle(bundle.getString("header.govt.of.canada"));
		headerProp.setImageURL(bundle.getString("header.image.url"));
		headerProp.setCanadaURL(bundle.getString("header.canada.ca.link"));
		headerProp.setServiceURL(bundle.getString("header.services.link"));
		headerProp.setDept(bundle.getString("header.dept"));
		headerProp.setHeaderDeptURL(bundle.getString("header.dept.link"));
		headerProp.setSearch(bundle.getString("header.search"));

		// Following is for footer properties

		footerProp.setTermsCond(bundle.getString("footer.terms.and.condition"));
		footerProp.setTermsCondLink(bundle.getString("footer.terms.and.condition.link"));
		footerProp.setTransparency(bundle.getString("footer.transparency"));
		footerProp.setTranspLink(bundle.getString("footer.transparency.link"));
		footerProp.setAboutUsTitle(bundle.getString("footer.about.us"));
		footerProp.setAboutUsLink(bundle.getString("footer.about.us.link"));
		footerProp.setOurMandate(bundle.getString("footer.mandate"));
		footerProp.setOurMandLink(bundle.getString("footer.mandate.link"));
		footerProp.setLibAndArch(bundle.getString("footer.librarian.and.archivist"));

		footerProp.setLibAndArchLink(bundle.getString("footer.librarian.and.archivist.link"));
		footerProp.setServAndProg(bundle.getString("footer.service.and.program"));
		footerProp.setServAndProgLink(bundle.getString("footer.service.and.program.link"));
		footerProp.setLacEvents(bundle.getString("footer.lac.events"));
		footerProp.setLacEventsLink(bundle.getString("footer.lac.events.link"));

		footerProp.setNewsTitle(bundle.getString("footer.news.title"));
		footerProp.setNewsTitleLink(bundle.getString("footer.news.title.link"));
		footerProp.setNewsRelease(bundle.getString("footer.news.release"));
		footerProp.setNewsReleaseLink(bundle.getString("footer.news.release.link"));
		footerProp.setSpeeches(bundle.getString("footer.speeches"));
		footerProp.setSpeechesLink(bundle.getString("footer.speeches.link"));
		footerProp.setPhotoGallery(bundle.getString("footer.photo.gallery"));
		footerProp.setPhotoGalLink(bundle.getString("footer.photo.gallery.link"));
		footerProp.setVideos(bundle.getString("footer.videos"));
		footerProp.setVideosLink(bundle.getString("footer.videos.link"));
		footerProp.setPodCasts(bundle.getString("footer.podcasts"));
		footerProp.setPodCastsLink(bundle.getString("footer.podcasts.link"));

		footerProp.setContactUsTitle(bundle.getString("footer.contactus.title"));
		footerProp.setContactUsLink(bundle.getString("footer.contactus.title.link"));
		footerProp.setAddress(bundle.getString("footer.address"));
		footerProp.setAddressLink(bundle.getString("footer.address.link"));
		footerProp.setTelephoneNums(bundle.getString("footer.telephone.numbers"));
		footerProp.setTelephoneLink(bundle.getString("footer.telephone.numbers.link"));
		footerProp.setEmails(bundle.getString("footer.emails"));
		footerProp.setEmailLink(bundle.getString("footer.emails.link"));
		footerProp.setFindAnEmployee(bundle.getString("footer.find.employee"));
		footerProp.setFindEmployeeLink(bundle.getString("footer.find.employee.link"));

		footerProp.setStayConnectedTitle(bundle.getString("footer.stay.connected.title"));
		footerProp.setStayConnectedLink(bundle.getString("footer.stay.connected.title.link"));
		footerProp.setRssFeed(bundle.getString("footer.rss.feed"));
		footerProp.setRssFeedLink(bundle.getString("footer.rss.feed.link"));

		footerProp.setHealthTitle(bundle.getString("footer.health.title"));
		footerProp.setHealthTitleLink(bundle.getString("footer.health.title.link"));
		footerProp.setTravelTitle(bundle.getString("footer.travel.title"));
		footerProp.setTravelLink(bundle.getString("footer.travel.title.link"));
		footerProp.setServiceCanadaTitle(bundle.getString("footer.service.canada.title"));
		footerProp.setServCandaLink(bundle.getString("footer.service.canada.title.link"));
		footerProp.setJobsTitle(bundle.getString("footer.jobs.title"));
		footerProp.setJobsLink(bundle.getString("footer.jobs.title.link"));
		footerProp.setEconomyTitle(bundle.getString("footer.economy.title"));
		footerProp.setEconomyLink(bundle.getString("footer.economy.title.link"));
		footerProp.setDateModified(bundle.getString("date.modified"));

		model.addAttribute("headerProp", headerProp);
		model.addAttribute("footerProp", footerProp);

		return model;
	}

}