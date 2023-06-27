package cl.araucana.cp.presentation.mgr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import cl.araucana.cp.distribuidor.hibernate.beans.CiudadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.ComunaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadCCAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadMutualVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EntidadVO;
import cl.araucana.cp.distribuidor.hibernate.beans.RegionVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.dao.EntidadesDAO;
/*
* @(#) EntidadesMgr.java 1.16 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.16
 */
public class EntidadesMgr
{
	private EntidadesDAO entidadesDAO;

	public EntidadesMgr(Session session)
	{
		this.entidadesDAO = new EntidadesDAO(session);
	}
	/**
	 * generos
	 * @return
	 * @throws DaoException
	 */
	public List getGeneros() throws DaoException
	{
		return this.entidadesDAO.getGeneros();
	}
	/**
	 * entidad salud
	 * @param flag
	 * @return
	 * @throws DaoException
	 */
	public List getEntsSalud() throws DaoException
	{
		return this.entidadesDAO.getEntsSalud();
	}
	/**
	 * entidad pension
	 * @param flag
	 * @param sacarNinguna
	 * @return
	 * @throws DaoException
	 */
	public List getEntsPension(boolean flag, boolean sacarNinguna) throws DaoException
	{
		return this.entidadesDAO.getEntsPension(flag, sacarNinguna);
	}
	/**
	 * entidad afc
	 * @return
	 * @throws DaoException
	 */
	public List getEntsAFC() throws DaoException
	{
		return this.entidadesDAO.getEntsAFC();
	}
	/**
	 * entidad ex caja
	 * @return
	 * @throws DaoException
	 */
	public List getEntsExCaja() throws DaoException
	{
		return this.entidadesDAO.getEntsExCaja();
	}
	/**
	 * entidad apvs
	 * @return
	 * @throws DaoException
	 */
	public List getEntsApvs() throws DaoException
	{
		return this.entidadesDAO.getEntsApvs();
	}
	/**
	 * entidad sil
	 * @return
	 * @throws DaoException
	 */
	public List getEntsSIL() throws DaoException
	{
		return this.entidadesDAO.getEntsSIL();
	}
	/**
	 * tramos asignacion familiar
	 * @return
	 * @throws DaoException
	 */
	public List getTramosAsigFam() throws DaoException
	{
		return this.entidadesDAO.getTramosAsigFam();
	}
	/**
	 * entidad mutual
	 * @return
	 * @throws DaoException
	 */
	public List getEntsMutual() throws DaoException
	{
		return this.entidadesDAO.getEntsMutual();
	}
	/**
	 * entidad ccaf
	 * @return
	 * @throws DaoException
	 */
	public List getEntsCCAF() throws DaoException
	{
		return this.entidadesDAO.getEntsCCAF();
	}
	/**
	 * codigo regimen impositivo
	 * @param idExCaja
	 * @return
	 * @throws DaoException
	 */
	public List getCodRegImp(int idExCaja) throws DaoException
	{
		return this.entidadesDAO.getCodRegImp(idExCaja);
	}
	/**
	 * regimen impositivo
	 * @return
	 * @throws DaoException
	 */
	public List getRegImp() throws DaoException
	{
		return this.entidadesDAO.getRegImp();
	}
	/**
	 * tipos movimiento personal
	 * @return
	 * @throws DaoException
	 */
	public List getTiposMovPersonal() throws DaoException
	{
		return this.entidadesDAO.getTiposMovPersonal();
	}
	/**
	 * tipos movimiento personal af
	 * @return
	 * @throws DaoException
	 */
	public List getTiposMovPersonalAF() throws DaoException
	{
		return this.entidadesDAO.getTiposMovPersonalAF();
	}
	/**
	 * regiones
	 * @return
	 * @throws DaoException
	 */
	public List getRegiones() throws DaoException
	{
		return this.entidadesDAO.getRegiones();
	}
	/**
	 * ciudades
	 * @param idRegion
	 * @return
	 * @throws DaoException
	 */
	public List getCiudades(int idRegion) throws DaoException
	{
		return this.entidadesDAO.getCiudades(idRegion);
	}
	/**
	 * comunas
	 * @param idCiudad
	 * @return
	 * @throws DaoException
	 */
	public List getComunas(int idCiudad) throws DaoException
	{
		return this.entidadesDAO.getComunas(idCiudad);
	}
	/**
	 * mutual
	 * @param idMutual
	 * @return
	 * @throws DaoException
	 */
	public EntidadMutualVO getMutual(int idMutual) throws DaoException
	{
		return this.entidadesDAO.getMutual(idMutual);
	}
	/**
	 * comuna
	 * @param idComuna
	 * @return
	 * @throws DaoException
	 */
	public ComunaVO getComuna(int idComuna) throws DaoException
	{
		return this.entidadesDAO.getComuna(idComuna);
	}
	/**
	 * ciudad
	 * @param idCiudad
	 * @return
	 * @throws DaoException
	 */
	public CiudadVO getCiudad(int idCiudad) throws DaoException
	{
		return this.entidadesDAO.getCiudad(idCiudad);
	}
	/**
	 * region
	 * @param idRegion
	 * @return
	 * @throws DaoException
	 */
	public RegionVO getRegion(int idRegion) throws DaoException
	{
		return this.entidadesDAO.getRegion(idRegion);
	}
	/**
	 * caja
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public EntidadCCAFVO getCaja(int id) throws DaoException
	{
		return this.entidadesDAO.getCaja(id);
	}

	/**
	 * retorna el porcentaje a pago para FONASA
	 * @return
	 */
	public float getPorcentajeFONASA() throws DaoException
	{
		return this.entidadesDAO.getPorcentajeFONASA();
	}

	/**
	 * lista entidad salud
	 * 
	 * @param flag
	 * @return
	 * @throws DaoException
	 */
	public HashMap getEntidadesHash(boolean flag, int valor, Class tipo) throws DaoException
	{
		List lista  = this.entidadesDAO.getEntidades(flag, valor, tipo);
		HashMap result = new HashMap();
		for (Iterator it = lista.iterator(); it.hasNext();)
		{
			EntidadVO ent = (EntidadVO)it.next();
			result.put("" + ent.getId(), ent.getNombre().trim());
		}
		return result;
	}
}
