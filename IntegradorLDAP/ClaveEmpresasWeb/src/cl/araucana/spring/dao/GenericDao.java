package cl.araucana.spring.dao;

import java.io.Serializable;

public interface GenericDao<T extends Serializable> {

	public T findById(Class<T> clazz, long id);

	public void save(T Entity);

}
