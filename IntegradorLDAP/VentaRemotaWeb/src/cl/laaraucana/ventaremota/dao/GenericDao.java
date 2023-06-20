package cl.laaraucana.ventaremota.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends Serializable> {

	public T findById(Class<T> clazz, long id);
	
	public T findById(Class<T> clazz, String id);
	
	public List<T> findCCRbyRut(Class<T> clazz, long rut);

	public void save(T Entity);
	
	public void update(T Entity);
	
	public List<T> findAllRechazadoByRut(Class<T> clazz, long rut, long numoferta);
	
	public List<T> findAllbyRut(Class<T> clazz, long rut);
	
	public void caducar(Class<T> clazz);
	
	public void updateById(Class<T> clazz, long id, String estado);
	
	public void deleteByOferta(Class<T> clazz, long numoferta);
	
	public List<T> findAllbyEstado(Class<T> clazz, String estado);

}
