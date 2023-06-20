package cl.laaraucana.dsiniestro.dao;

import java.io.Serializable;
import java.util.List;


public interface GenericDao<T extends Serializable> {

	public T findById(Class<T> clazz, long id);

	public void save(T Entity);
	
	public T findBySucursal(Class<T> clazz, String idsucursal);
	
	public List<T> findAll(Class<T> clazz);
	
	public boolean existRut(Class<T> clazz, String rut);
	
	public List<T> findByRut(Class<T> class1, String rut);

}
