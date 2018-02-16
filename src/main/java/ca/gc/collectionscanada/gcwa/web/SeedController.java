package ca.gc.collectionscanada.gcwa.web;

import ca.gc.collectionscanada.gcwa.domain.Collection;
import ca.gc.collectionscanada.gcwa.domain.*;
import ca.gc.collectionscanada.gcwa.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/seed")
public class SeedController {

	@Autowired
	private SeedRepository seedRepository;

	@Autowired
	private SubcategoryRepository subcategoryRepository;

	@Value("${gcwa.wayback.url.en}")
	private String waybackUrlEn;
	
	@Value("${gcwa.wayback.url.fr}")
	private String waybackUrlFr;
	
	private final Logger log = LoggerFactory.getLogger(GeneralController.class);

	@RequestMapping(value = "/subcategory/{id:\\d+}")
	public String subcategory(@PathVariable("id") long id, Model model, Locale locale) {
		log.info("/seed/subcategory/" + String.valueOf(id) + "  requested");
        // Only Federal Government Web for now
        if (id != 2) {
            throw new ResourceNotFoundException();
        }

		
		Subcategory subcategory = subcategoryRepository.findOne(id);
		if (subcategory == null) {
			throw new ResourceNotFoundException();
		}

		List<Seed> seeds = seedRepository.findBySubcategoryId(subcategory.getId());

		// Hack, last last minute change, only for this page
		if (id == 2) {
		    subcategory.setTitleEn("Federal Government URLs");
		    subcategory.setTitleFr("Adresses URL du Gouvernement Fédéral");
		}
		
		// Group seeds by collection name
		Map<String, List<Seed>> groupedSeeds = new HashMap<String, List<Seed>>();
		for (Seed seed : seeds) {
			Collection collection = seed.getCollection();
			if (groupedSeeds.containsKey(collection.getTitle()) == false) {
			    groupedSeeds.put(collection.getTitle(), new ArrayList<Seed>());
			}
		    if (groupedSeeds.get(collection.getTitle()).contains(seed) == false) {
		        groupedSeeds.get(collection.getTitle()).add(seed);
		    }
		}
		Map<String, List<Seed>> groupedSeedsSorted = new TreeMap<String, List<Seed>>(groupedSeeds);
		
        // Breadcrumbs parts
		//Subcategory subcategory = collection.getSubcategory();
		//Category category = subcategory.getCategory();
        Map<String, String> breadcrumbs = new LinkedHashMap<String, String>();
        //breadcrumbs.put("/category/" + category.getId(), category.getTitle());
        //breadcrumbs.put("/subcategory/" + subcategory.getId(), subcategory.getTitle());
        //breadcrumbs.put("/collection/" + collection.getId(), collection.getTitle());

		model.addAttribute("sectionTitle", subcategory.getTitle());
		model.addAttribute("groupedSeeds", groupedSeedsSorted);
		if (locale.getISO3Language().equals("fra")) {
		    model.addAttribute("waybackUrl", waybackUrlFr);
		} else {
		    model.addAttribute("waybackUrl", waybackUrlEn);
		}
		
		model.addAttribute("breadcrumbs", breadcrumbs);
		model.addAttribute("navSection", "category");
		return "seed/subcategory";
	}
}
