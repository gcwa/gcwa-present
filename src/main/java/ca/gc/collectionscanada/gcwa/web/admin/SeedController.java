package ca.gc.collectionscanada.gcwa.web.admin;

import ca.gc.collectionscanada.gcwa.domain.Collection;
import ca.gc.collectionscanada.gcwa.domain.CollectionRepository;
import ca.gc.collectionscanada.gcwa.domain.Seed;
import ca.gc.collectionscanada.gcwa.domain.SeedRepository;
import ca.gc.collectionscanada.gcwa.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller("adminSeedController")
@RequestMapping("/admin/seed")
public class SeedController {
    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private SeedRepository seedRepository;

    private final Logger log = LoggerFactory.getLogger(SeedController.class);

    @RequestMapping(value = "/list")
    public String list(Model model, Locale locale) {
        log.info("/admin/seed/list requested");

        // this one will load via ajax

        model.addAttribute("collectionId", null);
        return "admin/seed/list";
    }

    @RequestMapping(value = "/list/collection/{id:\\d+}")
    public String listForCollection(@PathVariable("id") long id, Model model, Locale locale) {
        log.info("/admin/seed/list/collection/" + id + " requested");

        // this one will load via ajax

        Collection collection = collectionRepository.findOneById(id);
        model.addAttribute("collectionId", id);
        model.addAttribute("collection", collection);
        return "admin/seed/list";
    }

    @RequestMapping(value = "/{id:\\d+}")
    public String seed(@PathVariable("id") long id, Model model, Locale locale) {
        log.info("/admin/seed/" + String.valueOf(id) + "  requested");

        Seed seed = seedRepository.findOneById(id);
        if (seed == null) {
            throw new ResourceNotFoundException();
        }
        Sort sort = new Sort("titleEn");
        List<Collection> allCollections = collectionRepository.findAll(sort);

        model.addAttribute("seed", seed);
        model.addAttribute("allCollections", allCollections);
        return "admin/seed/seed";
    }

    @RequestMapping(value = "/new/{id:\\d+}")
    public String seedCreate(@PathVariable("id") long id, Model model, Locale locale) {
        log.info("/admin/seed/new requested");

        Seed seed = new Seed();
        Collection collection = collectionRepository.findOneById(id);
        seed.setCollection(collection);
        Sort sort = new Sort("titleEn");
        List<Collection> allCollections = collectionRepository.findAll(sort);

        model.addAttribute("seed", seed);
        model.addAttribute("allCollections", allCollections);
        return "admin/seed/seed";
    }

    /**
     * @TODO add validation ASAP
     */
    @RequestMapping(method = RequestMethod.POST)
    public String seedSubmit(Seed seed, Model model, Locale locale, RedirectAttributes redirectAttributes) {
        seedRepository.save(seed);
        redirectAttributes.addFlashAttribute("flashSuccessMsg", "Seed Updated");
        return "redirect:/admin/seed/list/collection/" + seed.getCollection().getId();
    }

    /**
     * Delete a seed
     */
    @RequestMapping(value = "/delete/{id:\\d+}")
    public String delete(@PathVariable("id") long id, Model model, Locale locale,
            RedirectAttributes redirectAttributes) {

        Seed seed = seedRepository.findOneById(id);
        long collectionId = seed.getCollection().getId();
        seedRepository.delete(seed);
        redirectAttributes.addFlashAttribute("flashSuccessMsg", "Seed Deleted");
        return "redirect:/admin/seed/list/collection/" + collectionId;
    }
}
