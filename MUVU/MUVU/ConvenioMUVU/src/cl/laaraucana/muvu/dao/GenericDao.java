package cl.laaraucana.muvu.dao;

import java.io.Serializable;
import java.util.List;


public interface GenericDao<T extends Serializable> {

	public T findById(Class<T> clazz, long id);

	public void save(T Entity);
	
	public void update(T Entity);
	
	public List<T> findByRut(Class<T> class1, long rut);
	
	public List<T> findByEmail(Class<T> class1, String email);
	
}
