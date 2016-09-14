package ca.gc.collectionscanada.gcwa.web.admin;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.gc.collectionscanada.gcwa.domain.Category;
import ca.gc.collectionscanada.gcwa.domain.CategoryRepository;
import ca.gc.collectionscanada.gcwa.domain.SubcategoryRepository;
import ca.gc.collectionscanada.gcwa.exceptions.ResourceNotFoundException;

@Controller("adminCategoryController")
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private MessageSource message;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    private final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @RequestMapping(value = "/list")
    public String list(Model model, Locale locale) {
        log.info("/admin/category/ requested");

        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("categories", categories);
        return "admin/category/list";
    }

    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    public String category(@PathVariable("id") long id, Model model, Locale locale) {
        log.info("/admin/category/" + String.valueOf(id) + "  requested");

        Category category = categoryRepository.findOne(id);
        if (category == null) {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("category", category);
        return "admin/category/category";
    }

    /**
     * Update a category
     * 
     * @return
     */
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.POST)
    public String submit(@PathVariable("id") long id, @ModelAttribute Category category, Model model) {
        log.info("POST /admin/category/:id requested");

        System.out.println(category);
        model.addAttribute("category", category);
        return "admin/category/category";
    }
}