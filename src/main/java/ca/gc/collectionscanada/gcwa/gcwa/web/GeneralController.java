package ca.gc.collectionscanada.gcwa.gcwa.web;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.gc.collectionscanada.gcwa.domain.Category;
import ca.gc.collectionscanada.gcwa.domain.CategoryRepository;

@Controller
public class GeneralController {
	@Autowired
	private MessageSource message;

	@Autowired
	private CategoryRepository categoryRepository;
	
	private final Logger log = LoggerFactory.getLogger(GeneralController.class);

	@RequestMapping("/")
	public String home(Model model, Locale locale) {
		List<Category> categories = categoryRepository.findAll();
		
		log.info("Homepage requested");
		model.addAttribute("sectionTitle", message.getMessage("intro1.title", null, locale));
		model.addAttribute("categories", categories);
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