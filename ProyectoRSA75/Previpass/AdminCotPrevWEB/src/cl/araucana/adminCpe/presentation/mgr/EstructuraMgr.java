package cl.araucana.adminCpe.presentation.mgr;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.EstructuraDAO;
import cl.araucana.cp.distribuidor.hibernate.beans.AsigFamVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMvtoPersoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMovimientoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) EstructuraMgr.java 1.6 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * @author malvarez
 * 
 * @version 1.6
 */
public class EstructuraMgr
{
	static Logger log = Logger.getLogger(EstructuraMgr.class);
	private EstructuraDAO estructuraDAO;

	public EstructuraMgr(Session session)
	{
		this.estructuraDAO = new EstructuraDAO(session);
	}

	/**
	 * elimina tipo asignacion familiar 
	 * @param id
	 * @param tipo
	 * @throws Exception
	 */
	public void delTipoAsigFamiliar(int id, Class tipo) throws Exception 
	{
		this.estructuraDAO.delTipoAsigFamiliar(id, tipo);
	}
	/**
	 * elimina tipo movimiento peersonal
	 * @param id
	 * @param tipo
	 * @throws Exception
	 */
	public void delTipoMovPersonal(int id, Class tipo) throws Exception 
	{
		this.estructuraDAO.delTipoMovPersonal(id, tipo);
	}
	/**
	 * elimina tipo movimiento personal af
	 * @param id
	 * @param tipo
	 * @throws Exception
	 */
	public void delTipoMovPersonalAf(int id, Class tipo) throws Exception 
	{
		this.estructuraDAO.delTipoMovPersonalAf(id, tipo);
	}
	/**
	 * existe asignacion familar
	 * @param col
	 * @param nombre
	 * @return
	 */
	public boolean existeAsigFamiliar(String col,int nombre){
		return this.estructuraDAO.existeAsigFamiliar(col, nombre);
	}
	/**
	 * existe asignacion familiar
	 * @param col
	 * @param nombre
	 * @return
	 */
	public boolean existeAsigFamiliar(String col,String nombre){
		return this.estructuraDAO.existeAsigFamiliar(col, nombre);
	}
	/**
	 * existe movimiento personal
	 * @param col
	 * @param nombre
	 * @return
	 */
	public boolean existeMovPersonal(String col,int nombre){
		return this.estructuraDAO.existeMovPersonal(col, nombre);
	}
	/**
	 * existe movimiento personal
	 * @param col
	 * @param nombre
	 * @return
	 */
	public boolean existeMovPersonal(String col,String nombre){
		return this.estructuraDAO.existeMovPersonal(col, nombre);
	}
	/**
	 * existe movimiento personal af
	 * @param col
	 * @param nombre
	 * @return
	 */
	public boolean existeMovPersonalAf(String col,int nombre){
		return this.estructuraDAO.existeMovPersonalAf(col, nombre);
	}
	/**
	 * existe movimiento personal af
	 * @param col
	 * @param nombre
	 * @return
	 */
	public boolean existeMovPersonalAf(String col,String nombre){
		return this.estructuraDAO.existeMovPersonalAf(col, nombre);
	}
	/**
	 * lista estructura tipo asignacion familar
	 * @return
	 * @throws DaoException
	 */
	public List getEstructuraTipoAsigFamiliar() throws DaoException
	{
		return this.estructuraDAO.getEstructuraTipoAsigFamiliar();
	}
	/**
	 * lista estructura tipo asignacion familar
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public List getEstructuraTipoAsigFamiliar(int id) throws DaoException
	{
		return this.estructuraDAO.getEstructuraTipoAsigFamiliar(id);
	}
	/**
	 * lista estructura tipo mov personal
	 * @return
	 * @throws DaoException
	 */
	public List getEstructuraTipoMovPersonal() throws DaoException
	{
		return this.estructuraDAO.getEstructuraTipoMovPersonal();
	}
	/**
	 * lista estructura tipo movimiento personal
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public List getEstructuraTipoMovPersonal(int id) throws DaoException
	{
		return this.estructuraDAO.getEstructuraTipoMovPersonal(id);
	}
	/**
	 * lista estructura tipo movimiento personal af
	 * @return
	 * @throws DaoException
	 */
	public List getEstructuraTipoMovPersonalAf() throws DaoException
	{
		return this.estructuraDAO.getEstructuraTipoMovPersonalAf();
	}
	/**
	 * lista estructura tipo movimiento personal af
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public List getEstructuraTipoMovPersonalAf(int id) throws DaoException
	{
		return this.estructuraDAO.getEstructuraTipoMovPersonalAf(id);
	}
	/**
	 * eliminar tramo asignacion familiar
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public String isDeleteTramoAsigFam(int id) throws DaoException 
	{
		return this.estructuraDAO.isDeleteTramoAsigFam(id);
	}
	/**
	 * eliminar tipo movimiento personal
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public boolean isDelTipoMovPersonal(int id) throws DaoException 
	{
		return this.estructuraDAO.isDelTipoMovPersonal(id);
	}
	/**
	 * guarda tipo asignacion familiar
	 * @param tipAsigFamiliarVO
	 * @param tipo
	 * @throws DaoException
	 */
	public void saveTipoAsigFamiliar(AsigFamVO tipAsigFamiliarVO) throws DaoException 
	{
		this.estructuraDAO.saveTipoAsigFamiliar(tipAsigFamiliarVO);
	}
	/**
	 * guarda tipo movimiento personal
	 * @param tipoMovimientoPersonalVO
	 * @param tipo
	 * @throws DaoException
	 */
	public void saveTipoMovPersonal(TipoMovimientoPersonalVO tipoMovimientoPersonalVO) throws DaoException 
	{
		this.estructuraDAO.saveTipoMovPersonal(tipoMovimientoPersonalVO);
	}
	/**
	 * guarda tipo movimiento personal af
	 * @param tipoMvtoPersoAFVO
	 * @param tipo
	 * @throws DaoException
	 */
	public void saveTipoMovPersonalAf(TipoMvtoPersoAFVO tipoMvtoPersoAFVO) throws DaoException 
	{
		this.estructuraDAO.saveTipoMovPersonalAf(tipoMvtoPersoAFVO);
	}
	/**
	 * actulaiza tipo asignacion familar
	 * @param tipAsigFamiliarVO
	 * @param tipo
	 * @throws DaoException
	 */
	public void updateTipoAsigFamiliar(AsigFamVO tipAsigFamiliarVO) throws DaoException 
	{
		this.estructuraDAO.updateTipoAsigFamiliar(tipAsigFamiliarVO);
	}
	/**
	 * actualiza tipo movimiento personal
	 * @param tipoMovimientoPersonalVO
	 * @param tipo
	 * @throws DaoException
	 */
	public void updateTipoMovPersonal(TipoMovimientoPersonalVO tipoMovimientoPersonalVO) throws DaoException 
	{
		this.estructuraDAO.updateTipoMovPersonal(tipoMovimientoPersonalVO);
	}
	/**
	 * actualiza tipo movimiento personal af
	 * @param tipoMvtoPersoAFVO
	 * @param tipo
	 * @throws DaoException
	 */
	public void updateTipoMovPersonalAf(TipoMvtoPersoAFVO tipoMvtoPersoAFVO) throws DaoException 
	{
		this.estructuraDAO.updateTipoMovPersonalAf(tipoMvtoPersoAFVO);
	}
	/**
	 * cantidad movimiento personal
	 * @return
	 * @throws DaoException
	 */
	public int cantidadMovPersonal()throws DaoException 
	{
		return this.estructuraDAO.cantidadMovPersonal();
	}
}
