package cl.araucana.adminCpe.hibernate.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.hibernate.beans.CalendarioVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) CalendarioDao.java 1.4 10/06/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/

/**
 * @author jdelgado
 * @author aacu�a
 * 
 * @version 1.4
 */
public class CalendarioDAO
{
	private static Logger log = Logger.getLogger(CalendarioDAO.class);
	private Session session;
	
	public CalendarioDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List getCalendario() throws DaoException
	{
		try
		{
			log.info("CalendarioDAO:getCalendario:");
			return this.session.createCriteria(CalendarioVO.class).list();
		} catch (Exception ex)
		{
			log.error("Error en CalendarioDAO.getCalendario");
			throw new DaoException("CalendarioDAO:getCalendario:", ex);
		}
	}
	/**
	 * guarda cambios calendario
	 * @param calendarioVO
	 * @throws DaoException
	 */
	public void guardarCalendario(CalendarioVO calendarioVO) throws DaoException
	{
		try 
		{
			Object e = this.session.get(CalendarioVO.class, new Integer(calendarioVO.getIdCal()));
			if (e == null)
				this.session.save(calendarioVO);
			else
				this.session.merge(calendarioVO);
		} catch (Exception ex) 
		{
			log.error("Ha ocurrido la siguiente excepcion en CalendarioDAO.guardaCalendario: " + ex);
			throw new DaoException("Error al guardar el calendario: ", ex);
		}
	}
}
