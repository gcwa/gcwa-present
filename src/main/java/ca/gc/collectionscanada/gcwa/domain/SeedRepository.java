package ca.gc.collectionscanada.gcwa.domain;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface SeedRepository extends CrudRepository<Seed, Long> {

    List<Seed> findAll();

    List<Seed> findByCollectionAndAccess(Collection collection, Boolean access, Sort sort);
    
    Category findOneById(long id);
}
