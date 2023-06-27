package cl.araucana.adminCpe.presentation.mgr;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import cl.araucana.adminCpe.hibernate.dao.TesoreriaDAO;
import cl.araucana.cp.distribuidor.hibernate.beans.ConceptoTesoreriaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTesoreriaVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) TesoreriaMgr.java 1.4 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * @author cchamblas
 * 
 * @version 1.4
 */
public class TesoreriaMgr 
{	
	private TesoreriaDAO tesoreriaDao;

	public TesoreriaMgr(Session session) 
	{		
		this.tesoreriaDao = new TesoreriaDAO(session); 
	}
	/**
	 * elimina tesoreria
	 * @param conceptoTesoreriaVO
	 * @throws DaoException
	 */
	public void delete(ConceptoTesoreriaVO conceptoTesoreriaVO) throws DaoException
	{
		this.tesoreriaDao.delete(conceptoTesoreriaVO);
	}
	/**
	 * elimina mapeo tesoreria
	 * @param mapeoTesoreriaVO
	 * @throws DaoException
	 */
	public void delete(MapeoTesoreriaVO mapeoTesoreriaVO) throws DaoException
	{
		this.tesoreriaDao.delete(mapeoTesoreriaVO);
	}
	/**
	 * existe concepto tesoreria
	 * @param conceptoTesoreriaVO
	 * @return
	 * @throws DaoException
	 */
	public boolean existeConceptoTesoreria(int conceptoTesoreriaVO) throws DaoException
	{
		return this.tesoreriaDao.existeConceptoTesoreria(conceptoTesoreriaVO);
	}
	/**
	 * existe mapeo tesoreria
	 * @param mapeoTesoreriaVO
	 * @return
	 * @throws DaoException
	 */
	public boolean existeMapeoTesoreria(MapeoTesoreriaVO mapeoTesoreriaVO) throws DaoException
	{
		return this.tesoreriaDao.existeMapeoTesoreria(mapeoTesoreriaVO);
	}
	/**
	 * existe asociacion concepto tesoreria
	 * @param conceptoTesoreriaVO
	 * @return
	 * @throws DaoException
	 */
	public boolean existeAsociacionConceptoTesoreria(int conceptoTesoreria) throws DaoException
	{
		return this.tesoreriaDao.existeAsociacionConceptoTesoreria(conceptoTesoreria);
	}
	/**
	 * lista concepto tesoreria
	 * @return
	 * @throws DaoException
	 */
	public List getConceptoTesoreria() throws DaoException {
		List lista = this.tesoreriaDao.getConceptoTesoreria();
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			ConceptoTesoreriaVO concepto = (ConceptoTesoreriaVO) iter.next();
			concepto.setDescripcion(concepto.getDescripcion().trim());
		}
		return lista;
	}
	/**
	 * lista detalle tesoreria
	 * @return
	 * @param idTipoNomina, idTipoSeccion
	 * @throws DaoException
	 */
	public List getDetalleTesoreria(long idTipoSeccion, String idTipoNomina) throws DaoException
	{
		return this.tesoreriaDao.getDetalleTesoreria(idTipoSeccion, idTipoNomina);
	}
	/**
	 * lista montos
	 * @return
	 * @throws DaoException
	 */
	public List getListaMontos() throws DaoException
	{
		return this.tesoreriaDao.getListaMontos();
	}
	/**
	 * lista tipo detalle
	 * @param idNomina
	 * @param idSeccion
	 * @return
	 * @throws DaoException
	 */
	public List getTipoDetalle(char idNomina, int idSeccion) throws DaoException
	{
		return this.tesoreriaDao.getTipoDetalle(idNomina, idSeccion);
	}
	/**
	 * lista tipo nomina
	 * @return
	 * @throws DaoException
	 */
	public List getTipoNomina() throws DaoException
	{
		return this.tesoreriaDao.getTipoNomina();
	}
	/**
	 * lista tipo seccion
	 * @return
	 * @throws DaoException
	 */
	public List getTipoSeccion() throws DaoException
	{
		return this.tesoreriaDao.getTipoSeccion();
	}
	/**
	 * guarda concepto tesoreria
	 * @param conceptoTesoreriaVO
	 * @throws DaoException
	 */
	public void save(ConceptoTesoreriaVO conceptoTesoreriaVO) throws DaoException
	{
		this.tesoreriaDao.save(conceptoTesoreriaVO);
	}
	/**
	 * guarda mapeo tesoreria
	 * @param mapeoTesoreriaVO
	 * @throws DaoException
	 */
	public void save(MapeoTesoreriaVO mapeoTesoreriaVO) throws DaoException
	{
		this.tesoreriaDao.save(mapeoTesoreriaVO);
	}
	/**
	 * actualiza concepto tesoreria
	 * @param conceptoTesoreriaVO
	 * @param _conceptoTesoreriaVO
	 * @throws DaoException
	 */
	public void update(int idNuevo,int idAntiguo) throws DaoException
	{
		this.tesoreriaDao.update(idNuevo,idAntiguo);
	}
	/**
	 * actualiza concepto tesoreria
	 * @param conceptoTesoreriaVO
	 * @throws DaoException
	 */
	public void update(ConceptoTesoreriaVO conceptoTesoreriaVO) throws DaoException
	{
		this.tesoreriaDao.update(conceptoTesoreriaVO);
	}
	/**
	 * actualiza mapeo tesoreria
	 * @param mapeoTesoreriaVO
	 * @throws DaoException
	 */
	public void update(MapeoTesoreriaVO mapeoTesoreriaVO) throws DaoException
	{
		this.tesoreriaDao.update(mapeoTesoreriaVO);
	}
	/**
	 * cantidad registros
	 * 
	 * @param idTipoNomina, idTipoSeccion
	 * @return
	 * @throws DaoException
	 */
	public int getCantidadRegistros(long idTipoSeccion, String idTipoNomina)throws DaoException {
		return this.tesoreriaDao.getCantidadRegistros(idTipoSeccion, idTipoNomina);
		 
	}
}
