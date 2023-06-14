package cl.laaraucana.boletaelectronica.dao;

import java.io.Serializable;
import java.util.List;

import cl.laaraucana.boletaelectronica.entities.BoletaBase;
import cl.laaraucana.boletaelectronica.entities.OrigenBoleta;

public interface GenericDao<T extends Serializable> {

	public T findById(Class<T> clazz, long id);

	public void save(T Entity);

	public void update(T Entity);

	public void deleteById(Class<T> clazz, long id);

	public List<T> findByName(Class<T> clazz, String name);

	public List<T> findAll(Class<T> clazz);
	
	public List<T> findAllNoEmitidas(Class<T> clazz);
	
	public List<T> findAllEmitidas(Class<T> clazz);

	public List<T> findAllByEstate(Class<T> clazz, int estado);

	public List<T> findByNumber(Class<T> clazz, long folio, int estado);
	
	public List<T> findByNumBolAndTipoDoc(Class<T> clazz, long numboleta, int tipodoc);
	
	public List<T> findByNumBolAndTipoDocOrigen(Class<T> clazz, int folio, int tipodoc);
	
	public List<T> findByNumber(Class<T> clazz, int folio);

	public boolean existNumberAndFol(Class<T> clazz, long folio);

	public List<T> findByFolioBoleta(Class<T> clazz, long folio);

	public T getAll(Class<T> clazz);

	public T getByCode(Class<T> clazz, int code);

	public T getLastCode(Class<T> clazz);
	
	public void updateOrigen(Class<T> clazz, long numero, int folio);
	
	public List<T> findByNumber(Class<T> clazz, long folio);
	
	public List<T> findByFecha(Class<T> clazz, long fechaInicio, long fechaFin, long folio);
	
	 

}
