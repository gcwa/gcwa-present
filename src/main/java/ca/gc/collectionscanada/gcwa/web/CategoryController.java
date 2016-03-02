package ca.gc.collectionscanada.gcwa.web;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.gc.collectionscanada.gcwa.domain.Category;
import ca.gc.collectionscanada.gcwa.domain.CategoryRepository;
import ca.gc.collectionscanada.gcwa.domain.Subcategory;
import ca.gc.collectionscanada.gcwa.domain.SubcategoryRepository;
import ca.gc.collectionscanada.gcwa.exceptions.ResourceNotFoundException;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private MessageSource message;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SubcategoryRepository subcategoryRepository;
	
	private final Logger log = LoggerFactory.getLogger(GeneralController.class);

	@RequestMapping(value="/list")
	public String list(Model model, Locale locale) {
		List<Category> categories = categoryRepository.findByEnabled(true);
		log.info("/category/list requested");

		model.addAttribute("sectionTitle", message.getMessage("intro1.title", null, locale));
		model.addAttribute("categories", categories);
		model.addAttribute("navSection", "category");
		return "category/list";
	}

	
	@RequestMapping(value="/{id:\\d+}")
	public String category(@PathVariable("id") long id, Model model, Locale locale) {
		log.info("/category/" + String.valueOf(id) + "  requested");
		
		Category category = categoryRepository.findOne(id);
		if (category == null) { 
			throw new ResourceNotFoundException(); 
		}
		List<Subcategory> subcategories = subcategoryRepository.findAllByCategoryAndEnabled(category, true);
		
		model.addAttribute("sectionTitle", category.getTitle());
		model.addAttribute("category", category);
		model.addAttribute("subcategories", subcategories);
		model.addAttribute("navSection", "category");
		return "category/category";
	}

}