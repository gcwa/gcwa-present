package ca.gc.collectionscanada.gcwa.web.admin.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ca.gc.collectionscanada.gcwa.domain.Collection;
import ca.gc.collectionscanada.gcwa.domain.CollectionRepository;

@RestController
@RequestMapping("/admin/api")
public class CollectionResource {

    @Autowired
    private CollectionRepository collectionRepository;

    @RequestMapping(value = "/collections", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Collection> getAllCollections() {
        return collectionRepository.findAll();
    }

    @RequestMapping(value = "/collections/subcategory/{id:\\d+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Collection> getAllCollectionsForSubcategory(@PathVariable("id") long id) {
        return collectionRepository.findAllBySubcategory_Id(id);
    }
}
