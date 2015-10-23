package ca.gc.collectionscanada.common.dao;

import java.util.List;

import javax.sql.DataSource;

public class AbstractDAOImpl<T> implements AbstractDAO<T> {
	
	public DataSource dataSource;
	
	public AbstractDAOImpl() {
		
	}
	
    public AbstractDAOImpl(DataSource dataSource) {
    	this.dataSource = dataSource;
    }

	@Override
	public T findById(int id) {
		return null;
	}

	@Override
	public List<T> retrieveList() {
		return null;
	}

	@Override
	public void update(T entity) {
		
	}

	@Override
	public void saveOrUpdate(T entity) {
		
	}

	@Override
	public void delete(T entity) {
		
	}

	@Override
	public T[] retrieveArray() {
		// TODO Auto-generated method stub
		return null;
	}

/*	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}	*/
}
