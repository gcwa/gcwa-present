package ca.gc.collectionscanada.repository;

import java.util.List;

public interface AbstractDAO<T> {
	public T findById(int id);

	public List<T> retrieveList();
	
	public T [] retrieveArray();

	public void update(T entity);

	public void saveOrUpdate(T entity);

	public void delete(T entity);
	
}
