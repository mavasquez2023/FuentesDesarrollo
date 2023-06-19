package cl.laaraucana.contratocr.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends Serializable> {

	public T findById(Class<T> clazz, long id);

	public void save(T Entity);
	
	public void update(T Entity);
	
	public List<T> findbyRut(Class<T> clazz, long rut);
	
	public void updateByRut(Class<T> clazz, long id, String codigoBarra);

}
