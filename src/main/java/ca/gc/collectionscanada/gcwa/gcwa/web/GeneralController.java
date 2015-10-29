package ca.gc.collectionscanada.gcwa.gcwa.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneralController {
	@Autowired
	private MessageSource message;

	private final Logger log = LoggerFactory.getLogger(GeneralController.class);

	@RequestMapping("/")
	public String home(Model model, Locale locale) {
		log.info("Homepage requested");
		model.addAttribute("sectionTitle", message.getMessage("intro1.title", null, locale));
		return "home";
	}

	@RequestMapping("/help")
	public String helpView(Model model, Locale locale) {
		model.addAttribute("sectionTitle", message.getMessage("help.title", null, locale));
		return "help";
	}

	@RequestMapping("/comments")
	public String commentView(Model model, Locale locale) {
		model.addAttribute("sectionTitle", message.getMessage("comments.title", null, locale));
		return "comments";
	}
}