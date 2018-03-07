package ca.gc.collectionscanada.gcwa.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {
/*
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
*/
}
