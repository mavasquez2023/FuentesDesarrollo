package cl.araucana.adminCpe.hibernate.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.AsigFamVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoAsFamVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MapeoTipoMovtoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.MvtoPersoAFVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMovimientoPersonalVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoMvtoPersoAFVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) EstructuraDao.java 1.8 10/06/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/

/**
 * @author jdelgado
 * @author malvarez
 *
 * @version 1.8
 */
public class EstructuraDAO
{
	private static Logger log = Logger.getLogger(EstructuraDAO.class);
	private Session session;
	private boolean loggear = true;

	public EstructuraDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * elimina tipo asignacion familiar
	 * @param id
	 * @param tipo
	 * @throws Exception
	 */
	public void delTipoAsigFamiliar(int id, Class tipo) throws Exception {
		try{
			log.info("EstructuraDAO:delAsigFamiliar");
			List lista = this.session.createCriteria(tipo)
				.add(Restrictions.eq("id", new Integer(id)))
				.list();
			AsigFamVO asigFamVO;
			for (Iterator it = lista.iterator(); it.hasNext();) {
				asigFamVO = (AsigFamVO) it.next();
				this.session.delete(asigFamVO);
			}
		} catch(Exception ex) {
			log.error("EstructuraDAO:delAsigFamiliar error: " + ex.getCause().toString());
			throw new DaoException("Problemas en EstructuraDAO:delAsigFamiliar", ex);
		}
	}
	/**
	 * elimina tipo movimiento personal
	 * @param id
	 * @param tipo
	 * @throws Exception
	 */
	public void delTipoMovPersonal(int id, Class tipo) throws Exception {
		try{
			log.info("EstructuraDAO:delTipoMovPersonal");
			List lista = this.session.createCriteria(tipo)
				.add(Restrictions.eq("id", new Integer(id)))
				.list();
			TipoMovimientoPersonalVO tipoMovimientoPersonalVO;
			for (Iterator it = lista.iterator(); it.hasNext();) {
				tipoMovimientoPersonalVO = (TipoMovimientoPersonalVO) it.next();
				this.session.delete(tipoMovimientoPersonalVO);
			}
		} catch(Exception ex) {
			log.error("EstructuraDAO:delTipoMovPersonal error: " + ex.getCause().toString());
			throw new DaoException("Problemas en EstructuraDAO:delTipoMovPersonal", ex);
		}
	}
	/**
	 * elimina tipo movimiento personal af
	 * @param id
	 * @param tipo
	 * @throws Exception
	 */
	public void delTipoMovPersonalAf(int id, Class tipo) throws Exception {
		try{
			log.info("EstructuraDAO:delTipoMovPersonalAf");
			List lista = this.session.createCriteria(tipo)
				.add(Restrictions.eq("id", new Integer(id)))
				.list();
			TipoMvtoPersoAFVO tipoMvtoPersoAFVO;
			for (Iterator it = lista.iterator(); it.hasNext();) {
				tipoMvtoPersoAFVO = (TipoMvtoPersoAFVO) it.next();
				this.session.delete(tipoMvtoPersoAFVO);
			}
		} catch(Exception ex) {
			log.error("EstructuraDAO:delTipoMovPersonalAf error: " + ex.getCause().toString());
			throw new DaoException("Problemas en EstructuraDAO:delTipoMovPersonalAf", ex);
		}
	}
	/**
	 * existe asignacion familiar
	 * @param col
	 * @param dato
	 * @return
	 */
	public boolean existeAsigFamiliar(String col, int dato){
		try{
			List lista = this.session.createCriteria(AsigFamVO.class).add(Restrictions.eq(col, new Integer(dato))).list();
			if(lista.size()>0)
				return true;
			return false;
		} catch (Exception ex)
		{
			log.error("Error en EstructuraDAO:existe: " + ex);
		}
		return false;
	}
	/**
	 * existe asignacion familiar
	 * @param col
	 * @param dato
	 * @return
	 */
	public boolean existeAsigFamiliar(String col, String dato)
	{
		try
		{
			List lista=this.session.createCriteria(AsigFamVO.class).add(Restrictions.eq(col, new String(dato))).list();
			if(lista.size()>0)
				return true;
			return false;
		} catch (Exception ex)
		{
			log.error("Error en EstructuraDAO:existeAsigFamiliar: " + ex);
		}
		return false;
	}
	/**
	 * existe movimiento personal
	 * @param col
	 * @param dato
	 * @return
	 */
	public boolean existeMovPersonal(String col, int dato)
	{
		try{
			List lista=this.session.createCriteria(TipoMovimientoPersonalVO.class).add(Restrictions.eq(col, new Integer(dato))).list();
			if(lista.size()>0)
				return true;
			return false;
		} catch (Exception ex)
		{
			log.error("Error en EstructuraDAO:existeMovPersonal: " + ex);
		}
		return false;
	}
	/**
	 * existe movimiento personal
	 * @param col
	 * @param dato
	 * @return
	 */
	public boolean existeMovPersonal(String col, String dato)
	{
		try{
			List lista=this.session.createCriteria(TipoMovimientoPersonalVO.class).add(Restrictions.eq(col, new String(dato))).list();
			if(lista.size()>0)
				return true;
			return false;
		} catch (Exception ex)
		{
			log.error("Error en EstructuraDAO:existeMovPersonal: " + ex);
		}
		return false;
	}
	/**
	 * existe movimiento personal af
	 * @param col
	 * @param dato
	 * @return
	 */
	public boolean existeMovPersonalAf(String col, int dato)
	{
		try{
			List lista=this.session.createCriteria(TipoMvtoPersoAFVO.class).add(Restrictions.eq(col, new Integer(dato))).list();
			if(lista.size()>0) {
				return true;
			}
			return false;

		} catch (Exception ex) {
			log.error("Error en EstructuraDAO.existe: " + ex);
		}
		return false;
	}
	/**
	 * existe moviniento personal af
	 * @param col
	 * @param dato
	 * @return
	 */
	public boolean existeMovPersonalAf(String col, String dato)
	{
		try
		{
			List lista = this.session.createCriteria(TipoMvtoPersoAFVO.class).add(Restrictions.eq(col, new String(dato))).list();
			if(lista.size() > 0)
				return true;
			return false;

		} catch (Exception ex)
		{
			log.error("Error en EstructuraDAO.existe: " + ex);
		}
		return false;
	}
	/**
	 * lista estructura tipo asignacion familiar
	 * @return
	 * @throws DaoException
	 */
	public List getEstructuraTipoAsigFamiliar() throws DaoException
	{
		try
		{
			if (this.loggear) {
				log.info("EstructuraDAO:getEstructuraTipoMovPersonalAf:");
			}
			return this.session.createCriteria(AsigFamVO.class).addOrder(Order.asc("id")).list();
		} catch (Exception ex)
		{
			log.error("Error en EstructuraDAO:getEstructuraTipoMovPersonalAf:");
			throw new DaoException("EstructuraDAO:getEstructuraTipoMovPersonalAf:", ex);
		}
	}
	/**
	 *
	 * @param id
	 * @return lista estructura tipo asignacion familiar
	 * @throws DaoException
	 */
	public List getEstructuraTipoAsigFamiliar(int id) throws DaoException
	{
		try
		{
			if (this.loggear) {
				log.info("EstructuraDAO:getEstructuraTipoMovPersonalAf:");
			}
			return this.session.createCriteria(AsigFamVO.class).add(Restrictions.eq("id", new Integer(id))).addOrder(Order.asc("id")).list();
		} catch (Exception ex)
		{
			log.error("Error en EstructuraDAO:getEstructuraTipoMovPersonalAf:");
			throw new DaoException("EstructuraDAO:getEstructuraTipoMovPersonalAf:", ex);
		}
	}
	/**
	 *
	 * @return lista estructura tipo movimiento personal
	 * @throws DaoException
	 */
	public List getEstructuraTipoMovPersonal() throws DaoException
	{
		try
		{
			if (this.loggear) {
				log.info("EstructuraDAO:getEstructuraTipoMovPersonal:");
			}
			return this.session.createCriteria(TipoMovimientoPersonalVO.class).addOrder(Order.asc("id")).list();
		} catch (Exception ex)
		{
			log.error("Error en EstructuraDAO:getEstructuraTipoMovPersonal:");
			throw new DaoException("EstructuraDAO:getEstructuraTipoMovPersonal:", ex);
		}
	}
	/**
	 *
	 * @param id
	 * @return lista estructura movimiento personal
	 * @throws DaoException
	 */
	public List getEstructuraTipoMovPersonal(int id) throws DaoException
	{
		try
		{
			if (this.loggear) {
				log.info("EstructuraDAO:getEstructuraTipoMovPersonal:");
			}
			return this.session.createCriteria(TipoMovimientoPersonalVO.class).add(Restrictions.eq("id", new Integer(id))).addOrder(Order.asc("id")).list();
		} catch (Exception ex)
		{
			log.error("Error en EstructuraDAO:getEstructuraTipoMovPersonal:");
			throw new DaoException("EstructuraDAO:getEstructuraTipoMovPersonal:", ex);
		}
	}
	/**
	 *
	 * @return lista estructura tipo movimiento personal af
	 * @throws DaoException
	 */
	public List getEstructuraTipoMovPersonalAf() throws DaoException
	{
		try
		{
			if (this.loggear) {
				log.info("EstructuraDAO:getEstructuraTipoMovPersonalAf:");
			}
			return this.session.createCriteria(TipoMvtoPersoAFVO.class).addOrder(Order.asc("id")).list();
		} catch (Exception ex)
		{
			log.error("Error en EstructuraDAO:getEstructuraTipoMovPersonalAf:");
			throw new DaoException("EstructuraDAO:getEstructuraTipoMovPersonalAf:", ex);
		}
	}
	/**
	 *
	 * @param id
	 * @return lista estructura movimiento personal af
	 * @throws DaoException
	 */
	public List getEstructuraTipoMovPersonalAf(int id) throws DaoException
	{
		try
		{
			if (this.loggear)
				log.info("EstructuraDAO:getEstructuraTipoMovPersonalAf:");
			return this.session.createCriteria(TipoMvtoPersoAFVO.class).add(Restrictions.eq("id", new Integer(id))).addOrder(Order.asc("id")).list();
		} catch (Exception ex)
		{
			log.error("Error en EstructuraDAO:getEstructuraTipoMovPersonalAf:");
			throw new DaoException("EstructuraDAO:getEstructuraTipoMovPersonalAf:", ex);
		}
	}
	/**
	 *
	 * @param id
	 * @return eliminar tramo asignacion familiar
	 * @throws DaoException
	 */
	public String isDeleteTramoAsigFam(int id) throws DaoException {
		String retorno = "";
		try {
			List lista = this.session.createCriteria(CotizanteVO.class)
				.add(Restrictions.eq("idTramoReal", new Integer(id)))
				.list();
			if (lista.size()>0) {
				retorno = "CO";

			} else {
				lista = this.session.createCriteria(MapeoAsFamVO.class)
				.add(Restrictions.eq("id", new Integer(id)))
				.list();

				if(lista.size()>0)
					retorno = "MTAF";
			}
			return retorno;

		} catch (Exception ex) {
			log.error("Error en EstructuraDAO:isDelTipoAsig:");
			throw new DaoException("EstructuraDAO:isDelTipoAsig:", ex);
		}
	}
	/**
	 *
	 * @param id
	 * @return elimina tipo movimiento personal
	 * @throws DaoException
	 */
	public boolean isDelTipoMovPersonal(int id) throws DaoException
	{
		boolean retorno = true;
		try
		{
			List lista = this.session.createCriteria(MvtoPersoAFVO.class)
				.add(Restrictions.eq("idTipoMvto", new Integer(id)))
				.list();
			if(lista.size()>0) retorno=false;
			lista = this.session.createCriteria(MapeoTipoMovtoAFVO.class)
				.add(Restrictions.eq("id", new Integer(id)))
				.list();
			if(lista.size()>0) retorno=false;
			return retorno;
		} catch (Exception ex)
		{
			log.error("Error en EstructuraDAO:isDelTipoAsig:");
			throw new DaoException("EstructuraDAO:isDelTipoAsig:", ex);
		}
	}
	/**
	 * guarda tipo asignacion familiar
	 * @param asigFamVO
	 * @param tipo
	 * @throws DaoException
	 */
	public void saveTipoAsigFamiliar(AsigFamVO asigFamVO) throws DaoException
	{
		try
		{
			log.info("EstructuraDAO:saveTipoMovPersonalAf");
			this.session.save(asigFamVO);
		} catch(Exception ex)
		{
			log.error("EstructuraDAO:saveTipoMovPersonalAf error: " + ex.getCause().toString());
			throw new DaoException("Problemas en EstructuraDAO:saveTipoMovPersonalAf", ex);
		}
	}
	/**
	 * guarda tipo movimiento personal
	 * @param tipoMovimientoPersonalVO
	 * @param tipo
	 * @throws DaoException
	 */
	public void saveTipoMovPersonal(TipoMovimientoPersonalVO tipoMovimientoPersonalVO) throws DaoException
	{
		try
		{
			log.info("EstructuraDAO:saveTipoMovPersonal");
			this.session.save(tipoMovimientoPersonalVO);
		} catch(Exception ex) {
			log.error("EstructuraDAO:saveTipoMovPersonal error: " + ex.getCause().toString());
			throw new DaoException("Problemas en EstructuraDAO:saveTipoMovPersonal", ex);
		}
	}
	/**
	 * guarda tipo movimiento personal af
	 * @param tipoMvtoPersoAFVO
	 * @param tipo
	 * @throws DaoException
	 */
	public void saveTipoMovPersonalAf(TipoMvtoPersoAFVO tipoMvtoPersoAFVO) throws DaoException
	{
		try
		{
			log.info("EstructuraDAO:saveTipoMovPersonalAf");
			this.session.save(tipoMvtoPersoAFVO);
		} catch(Exception ex)
		{
			log.error("EstructuraDAO:saveTipoMovPersonalAf error: " + ex.getCause().toString());
			throw new DaoException("Problemas en EstructuraDAO:saveTipoMovPersonalAf", ex);
		}
	}
	/**
	 * actualiza tipo asignacion familiar
	 * @param asigFamVO
	 * @param tipo
	 * @throws DaoException
	 */
	public void updateTipoAsigFamiliar(AsigFamVO asigFamVO) throws DaoException
	{
		try
		{
			log.info("EstructuraDAO:saveTipoMovPersonalAf");

			this.session.update(asigFamVO);
		} catch(Exception ex)
		{
			log.error("EstructuraDAO:saveTipoMovPersonalAf error: " + ex.getCause().toString());
			throw new DaoException("Problemas en EstructuraDAO:saveTipoMovPersonalAf", ex);
		}
	}
	/**
	 * actualiza tipo movimiento personal
	 * @param tipoMovimientoPersonalVO
	 * @param tipo
	 * @throws DaoException
	 */
	public void updateTipoMovPersonal(TipoMovimientoPersonalVO tipoMovimientoPersonalVO) throws DaoException
	{
		try
		{
			log.info("EstructuraDAO:saveTipoMovPersonal");

			this.session.merge(tipoMovimientoPersonalVO);
		} catch(Exception ex)
		{
			log.error("EstructuraDAO:saveTipoMovPersonal error: " + ex.getCause().toString());
			throw new DaoException("Problemas en EstructuraDAO:saveTipoMovPersonal", ex);
		}
	}
	/**
	 * actualiza tipo movimiento de personal af
	 * @param tipoMvtoPersoAFVO
	 * @param tipo
	 * @throws DaoException
	 */
	public void updateTipoMovPersonalAf(TipoMvtoPersoAFVO tipoMvtoPersoAFVO) throws DaoException
	{
		try
		{
			log.info("EstructuraDAO:saveTipoMovPersonalAf");
			this.session.update(tipoMvtoPersoAFVO);
		} catch(Exception ex) {
			log.error("EstructuraDAO:saveTipoMovPersonalAf error: " + ex.getCause().toString());
			throw new DaoException("Problemas en EstructuraDAO:saveTipoMovPersonalAf", ex);
		}
	}

	/**
	 *
	 * @return contidad movimiento personal
	 * @throws DaoException
	 */
	public int cantidadMovPersonal() throws DaoException {
		 int lista=0;
		try
		{
			lista = this.session.createCriteria(TipoMovimientoPersonalVO.class).list().size();
			return lista;
		} catch (Exception ex)
		{
			log.error("Error en EstructuraDAO:isDelTipoAsig:");
			throw new DaoException("EstructuraDAO:isDelTipoAsig:", ex);
		}

	}
}
