package ca.gc.collectionscanada.gcwa.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;


@Controller
public class PageController {

	private final Logger log = LoggerFactory.getLogger(GeneralController.class);
	
	@RequestMapping("/")
    public String homeView(Model model, Locale locale) {
        model.addAttribute("navSection", "home");

        return "page/home_" + locale.getLanguage();
    }
}
