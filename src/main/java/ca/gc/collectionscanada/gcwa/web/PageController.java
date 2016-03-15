package ca.gc.collectionscanada.gcwa.web;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {
	@Autowired
	private MessageSource message;

	private final Logger log = LoggerFactory.getLogger(GeneralController.class);
/*
	@RequestMapping("/help")
	public String helpView(Model model, Locale locale) {
        model.addAttribute("sectionTitle", message.getMessage("help.title", null, locale));
		model.addAttribute("navSection", "help");
		return "page/help";
	}

	@RequestMapping("/about-us")
	public String aboutUsView(Model model, Locale locale) {
        model.addAttribute("sectionTitle", message.getMessage("aboutus.title", null, locale));
		model.addAttribute("navSection", "about-us");
		return "page/about-us";
	}
	
    @RequestMapping("/copyright-disclaimer")
    public String copyrightDisclaimerView(Model model, Locale locale) {
        model.addAttribute("sectionTitle", message.getMessage("copyright.disclaimer.title", null, locale));
        model.addAttribute("navSection", "copyright-disclaimer");
        return "page/copyright-disclaimer";
    }
*/
}