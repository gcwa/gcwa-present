package ca.gc.collectionscanada.gcwa.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
public class GeneralController {

	private final Logger log = LoggerFactory.getLogger(GeneralController.class);

	@RequestMapping("/eng")
	public String indexEng(Model model, Locale locale) {
        return "redirect:/?lang=en";
	}

	@RequestMapping("/fra")
    public String indexFra(Model model, Locale locale) {
        return "redirect:/?lang=fr";
    }
}
