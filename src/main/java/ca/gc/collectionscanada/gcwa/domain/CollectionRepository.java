package ca.gc.collectionscanada.gcwa.domain;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CollectionRepository extends PagingAndSortingRepository<Collection, Long>{
    
    List<Collection> findAll();
    List<Collection> findAll(Sort sort);
    List<Collection> findAllBySubcategory_Id(long id);
	List<Collection> findAllBySubcategory(Subcategory subcategory, Sort sort);
	List<Collection> findAllBySubcategoryAndEnabled(Subcategory subcategory, Boolean enabled, Sort sort);
    Collection findOneById(long id);
}
