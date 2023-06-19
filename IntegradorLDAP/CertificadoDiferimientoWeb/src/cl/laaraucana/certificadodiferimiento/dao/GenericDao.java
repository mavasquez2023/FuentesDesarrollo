package cl.laaraucana.certificadodiferimiento.dao;

import java.io.Serializable;
import java.util.List;


public interface GenericDao<T extends Serializable> {

	public T findById(Class<T> clazz, long id);

	public void save(T Entity);
	
	public List<T> findAll(Class<T> clazz);
	
	public List<T> findAllByRut(Class<T> clazz, long rut);
	
	public List<T> findAllByNotAutorized(Class<T> clazz, String fol, int cuota);
	
	public List<T> findAllByAutorized(Class<T> clazz, String fol, int cuota);
	
	public List<T> findAllByAutorizedCredito(Class<T> clazz, String fol, int cuota);
	
	public void update(T Entity);
	
	public List<T> findAllByRutBita(Class<T> clazz, long rut);
	
	public List<T> prefijoTelefono(Class<T> clazz, int tipo); 

}
