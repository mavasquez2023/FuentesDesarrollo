package cl.laaraucana.envioFormularioASFAM.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends Serializable> {

	public T findById(Class<T> clazz, long id);

	public void save(T Entity);

	public List<T> findAll(Class<T> clazz);
	
	public T findBySucursal(Class<T> clazz, String idSucursal);
	
	public List<T> findAnalistasBySucursal(Class<T> clazz, String idSucursal);

	public List<T> findByRut(Class<T> clazz, long rut);

	public void update(T Entity);

 

	 

}
