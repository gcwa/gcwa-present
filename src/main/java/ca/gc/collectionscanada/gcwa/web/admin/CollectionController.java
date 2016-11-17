package ca.gc.collectionscanada.gcwa.web.admin;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.gc.collectionscanada.gcwa.domain.Collection;
import ca.gc.collectionscanada.gcwa.domain.CollectionRepository;
import ca.gc.collectionscanada.gcwa.domain.Seed;
import ca.gc.collectionscanada.gcwa.domain.SeedRepository;
import ca.gc.collectionscanada.gcwa.domain.Subcategory;
import ca.gc.collectionscanada.gcwa.domain.SubcategoryRepository;
import ca.gc.collectionscanada.gcwa.exceptions.ResourceNotFoundException;

@Controller("adminCollectionController")
@RequestMapping("/admin/collection")
public class CollectionController {
    @Autowired
    private MessageSource message;

    @Autowired
    private SeedRepository seedRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Value("${gcwa.wayback.url.en}")
    private String waybackUrlEn;

    @Value("${gcwa.wayback.url.fr}")
    private String waybackUrlFr;

    private final Logger log = LoggerFactory.getLogger(CollectionController.class);

    @RequestMapping(value = "/list")
    public String list(Model model, Locale locale) {
        log.info("/admin/collection/list requested");

        List<Collection> collections = collectionRepository.findAll();

        model.addAttribute("collections", collections);
        model.addAttribute("subcategoryId", null);
        return "admin/collection/list";
    }

    @RequestMapping(value = "/list/subcategory/{id:\\d+}")
    public String listForSubcategory(@PathVariable("id") long id, Model model, Locale locale) {
        log.info("/admin/collection/list requested");

        Subcategory subcategory = subcategoryRepository.findOne(id);
        List<Collection> collections = collectionRepository.findAllBySubcategory_Id(id);

        model.addAttribute("collections", collections);
        model.addAttribute("subcategory", subcategory);
        model.addAttribute("subcategoryId", id);
        return "admin/collection/list";
    }

    @RequestMapping(value = "/{id:\\d+}")
    public String seed(@PathVariable("id") long id, Model model, Locale locale) {
        log.info("/admin/seed/" + String.valueOf(id) + "  requested");

        Collection collection = collectionRepository.findOne(id);
        if (collection == null) {
            throw new ResourceNotFoundException();
        }
        long seedCount = seedRepository.countByCollection_Id(id);
        List<Subcategory> allSubcategories = subcategoryRepository.findAll();

        model.addAttribute("collection", collection);
        model.addAttribute("seedCount", seedCount);
        model.addAttribute("allSubcategories", allSubcategories);
        return "admin/collection/collection";
    }

    /**
     * Empty form to create new Collection entity for subcategory ID
     */
    @RequestMapping(value = "/new/{id:\\d+}")
    public String collectionNew(@PathVariable("id") long id, Model model, Locale locale) {
        List<Subcategory> allSubcategories = subcategoryRepository.findAll();
        Subcategory subcategory = subcategoryRepository.findOne(id);
        Collection collection = new Collection();
        collection.setSubcategory(subcategory);
        collection.setEnabled(true);

        model.addAttribute("collection", collection);
        model.addAttribute("allSubcategories", allSubcategories);
        return "admin/collection/collection";
    }

    /**
     * Empty form to create new Collection entity
     */
    @RequestMapping(value = "/new")
    public String collectionNew(Model model, Locale locale) {
        List<Subcategory> allSubcategories = subcategoryRepository.findAll();

        model.addAttribute("collection", new Collection());
        model.addAttribute("allSubcategories", allSubcategories);
        return "admin/collection/collection";
    }

    /**
     * @TODO add validation ASAP
     */
    @RequestMapping(method = RequestMethod.POST)
    public String collectionSubmit(Collection collection, Model model, Locale locale,
            RedirectAttributes redirectAttributes) {
        collectionRepository.save(collection);
        redirectAttributes.addFlashAttribute("flashSuccessMsg", "Subcategory Updated");
        return "redirect:/admin/collection/" + collection.getId();
    }
    
    /**
     * Delete a collection
     */
    @RequestMapping(value = "/delete/{id:\\d+}")
    public String delete(@PathVariable("id") long id, Model model, Locale locale,
            RedirectAttributes redirectAttributes) {

        Collection collection = collectionRepository.findOne(id);
        long seedCount = seedRepository.countByCollection_Id(id);
        
        if (seedCount == 0) {
            long subcategoryId = collection.getSubcategory().getId();
            collectionRepository.delete(collection);
            redirectAttributes.addFlashAttribute("flashSuccessMsg", "Collection Deleted");
            return "redirect:/admin/subcategory/" + subcategoryId;
        } else {
            redirectAttributes.addFlashAttribute("flashErrorMsg", "Collection not deleted, still have " + seedCount + " assigned to it");
            return "redirect:/admin/collection/" + id;
        }        
    }
}
