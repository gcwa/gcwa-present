package ca.gc.collectionscanada.gcwa.web.admin;

import ca.gc.collectionscanada.gcwa.domain.*;
import ca.gc.collectionscanada.gcwa.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller("adminSubcategoryController")
@RequestMapping("/admin/subcategory")
public class SubcategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    private final Logger log = LoggerFactory.getLogger(SubcategoryController.class);

    @RequestMapping(value = "/list")
    public String list(Model model, Locale locale) {
        log.info("/admin/subcategory/list requested");

        List<Subcategory> subcategories = subcategoryRepository.findAll();

        model.addAttribute("subcategories", subcategories);
        return "admin/subcategory/list";
    }

    @RequestMapping(value = "/list/category/{id:\\d+}")
    public String listForSubcategory(@PathVariable("id") long id, Model model, Locale locale) {
        log.info("/admin/subcategory/list/category/" + id + " requested");

        List<Subcategory> subcategories = subcategoryRepository.findAllByCategory_Id(id);

        model.addAttribute("subcategories", subcategories);
        return "admin/subcategory/list";
    }

    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public String subcategory(@PathVariable("id") long id, Model model, Locale locale) {
        log.info("/admin/subcategory/" + String.valueOf(id) + "  requested");

        Subcategory subcategory = subcategoryRepository.findOneById(id);
        if (subcategory == null) {
            throw new ResourceNotFoundException();
        }
        subcategory.getCategory().getId();
        List<Category> allCategories = categoryRepository.findAll();

        model.addAttribute("allCategories", allCategories);
        model.addAttribute("subcategory", subcategory);
        return "admin/subcategory/subcategory";
    }

    /**
     * @TODO add validation ASAP
     */
    @RequestMapping(method = RequestMethod.POST)
    public String subcategorySubmit(Subcategory subcategory, Model model, Locale locale,
            RedirectAttributes redirectAttributes) {
        subcategoryRepository.save(subcategory);
        redirectAttributes.addFlashAttribute("flashSuccessMsg", "Subcategory Updated");
        return "redirect:/admin/subcategory/" + subcategory.getId();
    }
}
