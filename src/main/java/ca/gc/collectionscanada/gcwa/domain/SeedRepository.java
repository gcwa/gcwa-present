package ca.gc.collectionscanada.gcwa.domain;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SeedRepository extends CrudRepository<Seed, Long> {

    List<Seed> findAll();
    List<Seed> findAllByCollection_Id(long id);
    List<Seed> findByCollection(Collection collection, Sort sort);
    Seed findOneById(long id);
    long countByCollection_Id(long id);

    /* @TODO order by url, then subcat */
    @Query("SELECT s FROM Seed s JOIN FETCH s.collection c JOIN FETCH c.subcategory sc WHERE sc.id = ?1 ORDER BY s.url")
    List<Seed> findBySubcategoryId(long id);
}
