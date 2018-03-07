package ca.gc.collectionscanada.gcwa.web.admin.api;

import ca.gc.collectionscanada.gcwa.domain.Subcategory;
import ca.gc.collectionscanada.gcwa.domain.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/api")
public class SubcategoryResource {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @RequestMapping(value = "/subcategories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Subcategory> getAllSubcategorys() {
        return subcategoryRepository.findAll();
    }

    @RequestMapping(value = "/subcategories/category/{id:\\d+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Subcategory> getAllSubcategorysForCollection(@PathVariable("id") long id) {
        return subcategoryRepository.findAllByCategory_Id(id);
    }
}
