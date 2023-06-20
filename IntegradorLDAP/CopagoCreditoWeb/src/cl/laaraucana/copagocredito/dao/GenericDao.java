package cl.laaraucana.copagocredito.dao;

import java.io.Serializable;
import java.util.List;


public interface GenericDao<T extends Serializable> {

	public T findById(Class<T> clazz, long id);

	public void save(T Entity);
	
	public List<T> findAll(Class<T> clazz);
	
	public List<T> findAllByRut(Class<T> clazz, long rut);
	
	public List<T> findAllByNotAutorized(Class<T> clazz, long fol, long cuota);
	
	public List<T> findAllByAutorized(Class<T> clazz, long fol, long cuota);
	
	public List<T> findAllByAutorizedCredito(Class<T> clazz, long fol, long cuota);
	
	public void update(T Entity);
	
	public List<T> findAllByRutBita(Class<T> clazz, long rut);
	
	public List<T> findMandatoByRut(Class<T> clazz, long rut);
	
	 
	
 

}
