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
 * This is the main common controller class which will be called everytime during navigation
 * of GC Web Archive portal. This class provides all mappings required for this web application
 * 
 * @author khatrz
 *
 */
@Controller
public class GeneralController
{
	@Autowired
    private MessageSource message;
	
	private Map<String, String> errorMessages = new HashMap<String, String>();    
    protected final Log logger = LogFactory.getLog(getClass());

    
	@RequestMapping("/")
	public String welcomeOne(HttpServletRequest request, Model model, Locale locale) {
		model.addAttribute("sectiontitle", message.getMessage("intro1.title", null, locale));
		model.addAttribute("locale", locale.toString());
		model.addAttribute("language", locale.getLanguage());
		return "intro";
	}

    @RequestMapping("/intro")
    public String introViewOne(HttpServletRequest request, Model model, Locale locale)
    {
        logger.info((new StringBuilder()).append("SERVLET PATH=").append(request.getServletPath()).toString());
        
        model.addAttribute("sectiontitle", message.getMessage("intro1.title", null, locale));
        model.addAttribute("locale", locale.toString());
        model.addAttribute("language", locale.getLanguage());
        return "intro";
    }
    
    @RequestMapping(value="/help")
    public String helpView(HttpServletRequest request, Model model, Locale locale)
    {
        logger.info("SERVLET PATH="+request.getServletPath());
        model.addAttribute("sectiontitle", message.getMessage("help.title", null, locale));
        model.addAttribute("language", locale.getLanguage());
        return "help";
    }

    @RequestMapping(value="/faq")
    public String faqView(HttpServletRequest request, Model model, Locale locale)
    {
        logger.info((new StringBuilder()).append("SERVLET PATH=").append(request.getServletPath()).toString());
        model.addAttribute("language", locale.getLanguage());
        model.addAttribute("sectiontitle", message.getMessage("faq.title", null, locale));
        return "faq";
    }

    @RequestMapping(value="/techdetails")
    public String techDetailsView(HttpServletRequest request, Model model, Locale locale)
    {
        logger.info((new StringBuilder()).append("SERVLET PATH=").append(request.getServletPath()).toString());
        model.addAttribute("language", locale.getLanguage());
        model.addAttribute("sectiontitle", message.getMessage("tech.detail.title", null, locale));
        return "techdetails";
    }

    @RequestMapping(value="/comments")
    public String commentView(HttpServletRequest request, Model model, Locale locale)
    {
        logger.info((new StringBuilder()).append("SERVLET PATH=").append(request.getServletPath()).toString());
        model.addAttribute("language", locale.getLanguage());
        model.addAttribute("sectiontitle", message.getMessage("comments.title", null, locale));
        return "comments";
    }
}