package cl.laaraucana.surakm.dao;

import java.io.Serializable;
import java.util.List;


public interface GenericDao<T extends Serializable> {

	public T findById(Class<T> clazz, long id);

	public void save(T Entity);
	
	public List<T> findByRut(Class<T> class1, int rut);
	
}
