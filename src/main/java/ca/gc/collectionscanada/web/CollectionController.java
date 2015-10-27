package ca.gc.collectionscanada.web;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.gc.collectionscanada.common.util.SortUtility;
import ca.gc.collectionscanada.model.Department;
import ca.gc.collectionscanada.model.DepartmentWiseURLs;
import ca.gc.collectionscanada.repository.DepartmentRepository;
import ca.gc.collectionscanada.repository.DepartmentWiseURLDAO;

/**
 *
 */
@Controller
@RequestMapping("/collection")
public class CollectionController
{
	/**
    @Autowired
	private GCWebArchiveConfig config;
	*/
	
    @Autowired
    private DepartmentWiseURLDAO deptWiseDAO;

    @Autowired
    private DepartmentRepository deptDAO;

	@Autowired
    private MessageSource message;
	
    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value="/gcwa")
    public String gcwaView(HttpServletRequest request, Model model, Locale locale)
    {
        logger.info("SERVLET PATH="+request.getServletPath());
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
        
        model.addAttribute("sectiontitle", message.getMessage("dept.list.title", null, locale));
        model.addAttribute("language", locale.getLanguage());
        model.addAttribute("letters", letters);
        model.addAttribute("fromAtoZ", fromAtoZ);
        return "deptList";
    }

    
    @RequestMapping(value="deptWiseURL")
    public String deptWiseListView(HttpServletRequest request, Model model, Locale locale)
    {
        logger.info("SERVLET PATH="+request.getServletPath());
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
                DepartmentWiseURLs array[] = SortUtility.sortDeptAlphabeticallyEng(masterArray, letters[i]);
                departmentWiseURLs.add(array);
            }

        } else
        {
            for(int i = 0; i < letters.length; i++)
            {
                DepartmentWiseURLs array[] = SortUtility.sortDeptAlphabeticallyFr(masterArray, letters[i]);
                departmentWiseURLs.add(array);
            }

        }
        departmentWiseURLs.toArray(fromAtoZ);

        model.addAttribute("sectiontitle", message.getMessage("url.list.title", null, locale));
        model.addAttribute("locale", locale);
        model.addAttribute("language", locale.getLanguage());
        model.addAttribute("letters", letters);
        model.addAttribute("fromAtoZ", fromAtoZ);
        return "deptWiseURL";
    }

    
    /*
    
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
}