package ca.gc.collectionscanada.web;

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
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.HtmlUtils;

import ca.gc.collectionscanada.common.context.GCWebArchiveContext;
import ca.gc.collectionscanada.common.util.PageUtilities;
import ca.gc.collectionscanada.common.util.SortUtility;
import ca.gc.collectionscanada.config.GCWebArchiveConfig;
import ca.gc.collectionscanada.model.Department;
import ca.gc.collectionscanada.model.DepartmentWiseURLs;
import ca.gc.collectionscanada.model.FooterProperties;
import ca.gc.collectionscanada.model.HeaderProperties;
import ca.gc.collectionscanada.model.OpenSearchParser;
import ca.gc.collectionscanada.model.SearchItem;
import ca.gc.collectionscanada.model.SearchParameters;
import ca.gc.collectionscanada.repository.DepartmentRepository;
import ca.gc.collectionscanada.repository.DepartmentWiseURLDAO;

/**
 *
 */
@Controller
@RequestMapping("/search")
public class SearchController
{
	@Autowired
    private MessageSource message;
	
    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping
    public String searchView(HttpServletRequest request, Model model, Locale locale)
    {
        logger.info((new StringBuilder()).append("SERVLET PATH=").append(request.getServletPath()).toString());
        
        model.addAttribute("sectiontitle", message.getMessage("search.basicsearch.title", null, locale));
        model.addAttribute("language", locale.getLanguage());
        model.addAttribute("newSearch", new SearchParameters());
        return "search";
    }
    
/*
    @RequestMapping(value = "/result")
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
*/
}