package cl.laaraucana.rendicionpagonomina.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import cl.laaraucana.rendicionpagonomina.vo.ILF4500AVo;
import cl.laaraucana.rendicionpagonomina.vo.NominaManualVo;
import cl.laaraucana.rendicionpagonomina.vo.NominaVo;

public interface GenericDao<T extends Serializable> {

	public List<T> findAll(Class<T> clazz);
	
	public List<T> findByParams(Class<T> clazz, NominaManualVo params) throws Exception ;
	
	public List<T> findByIdCabecera(Class<T> clazz, long id);
	
	public List<T> findPendientesByIdCabecera(Class<T> clazz, long id);
	
	public List<T> findByIdDetalle(Class<T> clazz, long id);

	public T findById(Class<T> clazz, int id);

	public void deleteById(Class<T> clazz, int id);

	public void save(T Entity);

	public T saveOrupdate(T Entity);

	public void remove(T Entity);
	
	public List<T> finByNumNonmina(Class<T> clazz, long numnomina);
	
	public List<T> finByFecha(Class<T> clazz, Date fecha);

	public void deleteByRut(Class<T> clazz, long rut);
	
	public void deleteBycodNom(Class<T> clazz, String codNom);
	
	public boolean existRut(Class<T> clazz, long rut);
	
	public List<T> getILF4500A(Class<T> clazz, String rut);
	
	public void updateILF4500A(Class<T> clazz, ILF4500AVo vo);
	
	public List<T> getNominasDetalle(Class<T> clazz, NominaVo nomina);
	
	public List<T> getNominasCabecera(Class<T> clazz, NominaVo nomina);
	
	public List<T> getConvenioById(Class<T> clazz, int id);
	
	public List<T> findConvenioAndNomina(Class<T> clazz, long convenio, long nomina);
	
	public List<T> findConvenioAndProducto(Class<T> clazz, String convenio, String producto);
}
 
