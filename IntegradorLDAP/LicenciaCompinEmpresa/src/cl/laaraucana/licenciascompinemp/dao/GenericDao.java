package cl.laaraucana.licenciascompinemp.dao;

import java.io.Serializable;
import java.util.List;


public interface GenericDao<T extends Serializable> {

	public T findById(Class<T> clazz, long id);

	public void save(T Entity);
	
	public void update(T Entity);
	
	public List<T> findByComunaSuc(Class<T> clazz, int idcomuna);
	
	public List<T> findAll(Class<T> clazz);
	
	public List<T> findByComunaReg(Class<T> clazz, int idRegion);
	
	public boolean existfolio(Class<T> clazz, String folio);
	
	public List<T> findByFolio(Class<T> clazz, String folio, String rut);
	
	public List<T> findByRut(Class<T> clazz, String rut);
	
	public  List<T> getCorreoEjecutivo(Class<T> clazz);
	
	public List<T> getAllSucursales(Class<T> clazz);
	
	public List<T> getSucursalByCodigo(Class<T> clazz, String codSucursal);

}
