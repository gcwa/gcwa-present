package ca.gc.collectionscanada.gcwa.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/collection")
public class CollectionController {
/*
	@Autowired
	private MessageSource message;

	@Autowired
	private SeedRepository seedRepository;

	@Autowired
	private CollectionRepository collectionRepository;

	@Value("${gcwa.wayback.url.en}")
	private String waybackUrlEn;
	
	@Value("${gcwa.wayback.url.fr}")
	private String waybackUrlFr;
	
	private final Logger log = LoggerFactory.getLogger(GeneralController.class);

	@RequestMapping(value = "/{id:\\d+}")
	public String collection(@PathVariable("id") long id, Model model, Locale locale) {
		log.info("/collection/" + String.valueOf(id) + "  requested");

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
		    String url = seed.getHumanReadableUrl().replaceFirst("www\\d?.", "");
		    if (url.equalsIgnoreCase(previousUrl)) {
		        continue;
		    }
			String firstLetter = url.toUpperCase().substring(0, 1);
			if (StringUtils.isAlpha(firstLetter) == false) {
				firstLetter = "[0-9]";
			}
			if (alphabetizedSeeds.containsKey(firstLetter) == false) {
				alphabetizedSeeds.put(firstLetter, new ArrayList<Seed>());
			}
			alphabetizedSeeds.get(firstLetter).add(seed);
			previousUrl = url;
		}
		Map<String, List<Seed>> alphabetizedSeedsSorted = new TreeMap<String, List<Seed>>(alphabetizedSeeds);
		
        // Breadcrumbs parts
		Subcategory subcategory = collection.getSubcategory();
		Category category = subcategory.getCategory();
        Map<String, String> breadcrumbs = new LinkedHashMap<String, String>();
        //breadcrumbs.put("/category/" + category.getId(), category.getTitle());
        //breadcrumbs.put("/subcategory/" + subcategory.getId(), subcategory.getTitle());
        breadcrumbs.put("/collection/" + collection.getId(), collection.getTitle());

		model.addAttribute("sectionTitle", collection.getTitle());
		model.addAttribute("alphabetizedSeeds", alphabetizedSeedsSorted);
		if (locale.getISO3Language().equals("fra")) {
		    model.addAttribute("waybackUrl", waybackUrlFr);
		} else {
		    model.addAttribute("waybackUrl", waybackUrlEn);
		}
		
		model.addAttribute("breadcrumbs", breadcrumbs);
		model.addAttribute("navSection", "category");
		return "collection/collection";
	}
*/
}
