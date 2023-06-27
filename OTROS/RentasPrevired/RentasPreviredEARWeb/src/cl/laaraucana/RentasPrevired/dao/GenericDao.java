package cl.laaraucana.RentasPrevired.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends Serializable> {

	public T findById(Class<T> clazz, long id);

	public void save(T Entity);
	
	public List<T> findByName(Class<T> clazz, String name, String campo);
	
	public List<T> findByError(Class<T> clazz);
	
	public List<T> findAll(Class<T> clazz);

}
