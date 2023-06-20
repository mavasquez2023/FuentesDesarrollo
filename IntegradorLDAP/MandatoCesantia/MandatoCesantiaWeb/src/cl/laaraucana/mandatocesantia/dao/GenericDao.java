package cl.laaraucana.mandatocesantia.dao;

import java.io.Serializable;
import java.util.List;


public interface GenericDao<T extends Serializable> {

	public T findById(Class<T> clazz, long id);

	public void save(T Entity);
	
	public List<T> findAll(Class<T> clazz);
	
	public void update(T Entity);
	
	public List<T> findAllByRutBita(Class<T> clazz, long rut);
	
	
 

}
