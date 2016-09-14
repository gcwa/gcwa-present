package ca.gc.collectionscanada.gcwa.web.admin.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.gc.collectionscanada.gcwa.domain.Category;
import ca.gc.collectionscanada.gcwa.domain.CategoryRepository;

@RequestMapping("/admin/api")
public class CategoryResource {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Category> getAllCategorys() {
        return categoryRepository.findAll();
    }
}
