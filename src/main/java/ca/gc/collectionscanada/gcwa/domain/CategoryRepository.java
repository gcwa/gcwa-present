package ca.gc.collectionscanada.gcwa.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	List<Category> findAll();
	List<Category> findByEnabled(Boolean enabled);
	Category findOneById(long id);
}
