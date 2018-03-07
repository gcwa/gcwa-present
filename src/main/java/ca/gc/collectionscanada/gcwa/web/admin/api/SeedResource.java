package ca.gc.collectionscanada.gcwa.web.admin.api;

import ca.gc.collectionscanada.gcwa.domain.Seed;
import ca.gc.collectionscanada.gcwa.domain.SeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/api")
public class SeedResource {

    @Autowired
    private SeedRepository seedRepository;

    @RequestMapping(value = "/seeds", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Seed> getAllSeeds() {
        return seedRepository.findAll();
    }

    @RequestMapping(value = "/seeds/collection/{id:\\d+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Seed> getAllSeedsForCollection(@PathVariable("id") long id) {
        return seedRepository.findAllByCollection_Id(id);
    }
}
