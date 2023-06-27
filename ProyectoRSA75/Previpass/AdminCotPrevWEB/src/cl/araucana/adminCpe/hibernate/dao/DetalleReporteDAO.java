package cl.araucana.adminCpe.hibernate.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cl.araucana.cp.distribuidor.hibernate.beans.PropertiesMapeoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.util.vo.DetalleReporteVO;

/*
 * @(#) detalleReporteDao.java 1.1 10/06/2009
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */

/**
 * @author aacu�a
 * 
 * @version 1.1
 */
public class DetalleReporteDAO
{
	private static Logger log = Logger.getLogger(DetalleReporteDAO.class);
	Session session;

	public DetalleReporteDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * 
	 * @param tipoReporte
	 * @return
	 * @throws DaoException
	 */
	public List getLista(int tipoReporte) throws DaoException
	{
		try
		{
			List result = this.session.createCriteria(DetalleReporteVO.class).add(Restrictions.eq("idReporte", new Integer(tipoReporte))).addOrder(Order.asc("idReporte")).list();
			if (result != null && result.size() > 0)
				return result;
			throw new DaoException("ERROR DetalleReporteDAO:getLista: no se encontraron valores para el reporte:" + tipoReporte + "::");
		} catch (Exception e)
		{
			log.error("DetalleReporteDAO:getLista error:", e);
			throw new DaoException("ERROR DetalleReporteDAO:getLista error:" + e.getMessage());
		}
	}

	/**
	 * 
	 * @param tipo
	 * @return
	 * @throws DaoException
	 */
	public HashMap getPropertiesMapeo(int tipo) throws DaoException
	{
		try
		{
			log.info("DetalleReporteDAO:getPropertiesMapeo");
			List result = this.session.createCriteria(PropertiesMapeoVO.class).add(Restrictions.eq("tipo", new Integer(tipo))).list();
			if (result != null && result.size() > 0)
			{
				HashMap resultHM = new HashMap();
				for (Iterator it = result.iterator(); it.hasNext();)
				{
					PropertiesMapeoVO pm = (PropertiesMapeoVO) it.next();
					resultHM.put(pm.getClave().trim(), pm.getValor().trim());
				}
				return resultHM;
			}
			throw new DaoException("ERROR DetalleReporteDAO:getPropertiesMapeo: no se encontraron parametros para tipo:" + tipo + "::");
		} catch (HibernateException ex)
		{
			throw new DaoException("ERROR DetalleReporteDAO:getPropertiesMapeo: para tipo:" + tipo + "::", ex);
		}
	}
}
