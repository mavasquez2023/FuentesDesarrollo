package cl.araucana.adminCpe.hibernate.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.EnvioVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

/*
 * @(#) EnvioDao.java 1.6 10/06/2009
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */

/**
 * @author creyes
 * @author cchamblas
 * 
 * @version 1.6
 */
public class EnvioDAO
{
	private static Logger log = Logger.getLogger(EnvioDAO.class);
	Session session;

	public EnvioDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * 
	 * @param idNodo
	 * @return
	 * @throws DaoException
	 */
	public int getNumEnviosPorNodo(int idNodo) throws DaoException
	{
		try
		{
			List result = this.session.createCriteria(EnvioVO.class).add(Restrictions.eq("idNodo", new Integer(idNodo))).list();
			return result.size();
		} catch (Exception e)
		{
			log.error("\n\nEnvioDAO:getNumEnviosPorNodo error:", e);
			throw new DaoException("Error en EnvioDAO:getNumEnviosPorNodo", e);
		}
	}

	/**
	 * 
	 * @param id
	 * @return envio
	 * @throws DaoException
	 */
	public EnvioVO getEnvio(int id) throws DaoException
	{
		try
		{
			Criteria crit = this.session.createCriteria(EnvioVO.class);
			return (EnvioVO) crit.add(Restrictions.eq("id", new Integer(id))).addOrder(Order.desc("id")).uniqueResult();
		} catch (Exception e)
		{
			log.error("\n\nEnvioDAO:getEnvio error:", e);
			throw new DaoException("Error en EnvioDAO:getEnvio", e);
		}
	}
}
