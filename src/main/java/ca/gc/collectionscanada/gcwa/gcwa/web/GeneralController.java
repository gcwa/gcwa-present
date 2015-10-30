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
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView index() {
		log.info("Homepage requested");
		return new ModelAndView("redirect:/category/list");
	}

}