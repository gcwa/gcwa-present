package ca.gc.collectionscanada.gcwa.domain;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface SeedRepository extends CrudRepository<Seed, Long> {

    List<Seed> findAll();
    List<Seed> findAllByCollection_Id(long id);
    List<Seed> findByCollection(Collection collection, Sort sort);
    Category findOneById(long id);
}
