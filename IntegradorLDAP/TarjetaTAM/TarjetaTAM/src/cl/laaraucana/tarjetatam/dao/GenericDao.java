package cl.laaraucana.tarjetatam.dao;

import java.io.Serializable;
import java.util.List;


public interface GenericDao<T extends Serializable> {

	public T findById(Class<T> clazz, long id);

	public void save(T Entity);
	
	public T findByOficina(Class<T> clazz, String idsucursal);
	
	public List<T> findAll(Class<T> clazz);
	
	public boolean existRut(Class<T> clazz, String rut);
	
	public List<T> findByRut(Class<T> class1, String rut);
	
	public List<T> findAllByRut(Class<T> clazz, long rut);
	
	public List<T> findAllByAutorizedCredito(Class<T> clazz, long fol, long cuota);
	
	public T findByComunaSuc(Class<T> clazz, String idComuna);
	
	public List<T> findByComunaReg(Class<T> clazz, int idRegion);

}
