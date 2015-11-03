package ca.gc.collectionscanada.gcwa.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.gc.collectionscanada.gcwa.domain.Category;
import ca.gc.collectionscanada.gcwa.domain.CategoryRepository;
import ca.gc.collectionscanada.gcwa.domain.Collection;
import ca.gc.collectionscanada.gcwa.domain.CollectionRepository;
import ca.gc.collectionscanada.gcwa.domain.Subcategory;
import ca.gc.collectionscanada.gcwa.domain.SubcategoryRepository;
import ca.gc.collectionscanada.gcwa.exceptions.ResourceNotFoundException;

@Controller
@RequestMapping("/subcategory")
public class SubcategoryController {
	@Autowired
	private MessageSource message;

	@Autowired
	private SubcategoryRepository subcategoryRepository;

	@Autowired
	private CollectionRepository collectionRepository;

	private final Logger log = LoggerFactory.getLogger(GeneralController.class);

	@RequestMapping(value = "/{id:\\d+}")
	public String subcategory(@PathVariable("id") long id, Model model, Locale locale) {
		log.info("/subcategory/" + String.valueOf(id) + "  requested");

		Subcategory subcategory = subcategoryRepository.findOne(id);
		if (subcategory == null) {
			throw new ResourceNotFoundException();
		}

		Sort sort = new Sort("title"); 
		List<Collection> collections = collectionRepository.findAllBySubcategory(subcategory, sort);

		// Group collections by first letter (for alpha paginator)
		Map<String, List<Collection>> alphabetizedCollections = new HashMap<String, List<Collection>>();
		for (Collection collection : collections) {
			String firstLetter = collection.getTitle().substring(0, 1).toUpperCase();
			if (StringUtils.isAlpha(firstLetter) == false) {
				firstLetter = "[0-9]";
			}
			if (alphabetizedCollections.containsKey(firstLetter) == false) {
				alphabetizedCollections.put(firstLetter, new ArrayList<Collection>());
			}
			alphabetizedCollections.get(firstLetter).add(collection);
		}
		
		Category category = subcategory.getCategory();

		// Breadcrumbs parts
		Map<String, String> breadcrumbs = new LinkedHashMap<String, String>();
		breadcrumbs.put("/category/" + category.getId(), category.getTitle());

		model.addAttribute("sectionTitle", subcategory.getTitle());
		model.addAttribute("subcategory", subcategory);
		model.addAttribute("collections", collections);
		model.addAttribute("alphabetizedCollections", alphabetizedCollections);
		model.addAttribute("breadcrumbs", breadcrumbs);
		model.addAttribute("navSection", "category");
		return "subcategory/subcategory";
	}
}
