package ca.gc.collectionscanada.gcwa.gcwa.web;

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

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private MessageSource message;

	@Autowired
	private CategoryRepository CategoryRepository;
	
	@Autowired
	private SubcategoryRepository subcategoryRepository;
	
	private final Logger log = LoggerFactory.getLogger(GeneralController.class);

	@RequestMapping(value="/{id}")
	public String category(@PathVariable("id") long id, Model model, Locale locale) {
		log.info("/collection/" + String.valueOf(id) + "  requested");

		//TODO 404 when 0 category
		Category category = CategoryRepository.findOne(id);
		List<Subcategory> subcategories = subcategoryRepository.findAllByCategory(category);
		
		model.addAttribute("sectionTitle", category.getTitle());
		model.addAttribute("category", category);
		model.addAttribute("subcategories", subcategories);
		return "category/category";
	}

}