package ca.gc.collectionscanada.gcwa.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SubcategoryRepository extends CrudRepository<Subcategory, Long> {
	
    List<Subcategory> findAll();
	List<Subcategory> findAllByCategory_Id(long id);
	List<Subcategory> findAllByCategory(Category category);
	List<Subcategory> findAllByCategoryAndEnabled(Category category, Boolean enabled);
}
