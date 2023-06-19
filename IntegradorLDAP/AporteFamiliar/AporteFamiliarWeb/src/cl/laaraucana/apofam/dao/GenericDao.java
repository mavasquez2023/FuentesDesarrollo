package cl.laaraucana.apofam.dao;

import java.io.Serializable;
import java.util.List;


public interface GenericDao<T extends Serializable> {


	public void save(T Entity);
	
	public void update(T Entity);
	
	public List<T> findByRut(Class<T> class1, int rut);
	
	
}
