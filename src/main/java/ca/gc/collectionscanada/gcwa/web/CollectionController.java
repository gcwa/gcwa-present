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
import ca.gc.collectionscanada.gcwa.domain.Collection;
import ca.gc.collectionscanada.gcwa.domain.CollectionRepository;
import ca.gc.collectionscanada.gcwa.domain.Seed;
import ca.gc.collectionscanada.gcwa.domain.SeedRepository;
import ca.gc.collectionscanada.gcwa.domain.Subcategory;
import ca.gc.collectionscanada.gcwa.exceptions.ResourceNotFoundException;

@Controller
@RequestMapping("/collection")
public class CollectionController {
	@Autowired
	private MessageSource message;

	@Autowired
	private SeedRepository seedRepository;

	@Autowired
	private CollectionRepository collectionRepository;

	private final Logger log = LoggerFactory.getLogger(GeneralController.class);

	@RequestMapping(value = "/{id:\\d+}")
	public String seed(@PathVariable("id") long id, Model model, Locale locale) {
		log.info("/seed/" + String.valueOf(id) + "  requested");

		Collection collection = collectionRepository.findOne(id);
		if (collection == null) {
			throw new ResourceNotFoundException();
		}

		Sort sort = new Sort("url"); 
		List<Seed> seeds = seedRepository.findByCollection(collection, sort);

		// Group seeds by first letter (for alpha paginator)
		String previousUrl = "";
		Map<String, List<Seed>> alphabetizedSeeds = new HashMap<String, List<Seed>>();
		for (Seed seed : seeds) {
		    String url = seed.getHumanReadableUrl().replaceFirst("www.", "");
		    if (url.equalsIgnoreCase(previousUrl)) {
		        continue;
		    }
			String firstLetter = url.toLowerCase().substring(0, 1);
			if (StringUtils.isAlpha(firstLetter) == false) {
				firstLetter = "[0-9]";
			}
			if (alphabetizedSeeds.containsKey(firstLetter) == false) {
				alphabetizedSeeds.put(firstLetter, new ArrayList<Seed>());
			}
			alphabetizedSeeds.get(firstLetter).add(seed);
			previousUrl = url;
		}
		
        // Breadcrumbs parts
		Subcategory subcategory = collection.getSubcategory();
		Category category = subcategory.getCategory();
        Map<String, String> breadcrumbs = new LinkedHashMap<String, String>();
        breadcrumbs.put("/category/" + category.getId(), category.getTitle());
        breadcrumbs.put("/subcategory/" + subcategory.getId(), subcategory.getTitle());
        breadcrumbs.put("/collection/" + collection.getId(), collection.getTitle());

		model.addAttribute("sectionTitle", collection.getTitle());
		model.addAttribute("alphabetizedSeeds", alphabetizedSeeds);
		model.addAttribute("breadcrumbs", breadcrumbs);
		model.addAttribute("navSection", "category");
		return "collection/collection";
	}
}
