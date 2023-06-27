package cl.araucana.adminCpe.presentation.mgr;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.CalendarioDAO;
import cl.araucana.cp.distribuidor.hibernate.beans.CalendarioVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) CalendarioMgr.java 1.2 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author vagurto
 * 
 * @version 1.2
 */
public class CalendarioMgr
{
	private CalendarioDAO calendarioDAO;
	
	static Logger log = Logger.getLogger(CalendarioMgr.class);

	/**
	 * Calendario
	 * @param session
	 */
	public CalendarioMgr(Session session)
	{
		this.calendarioDAO = new CalendarioDAO(session);
	}
	
	/**
	 * lista calendario
	 * @return
	 * @throws DaoException
	 */
	public List getCalendario() throws DaoException
	{
		return this.calendarioDAO.getCalendario();
	}

	/**
	 * guarda calendario
	 * @param calendarioVO
	 * @throws DaoException
	 */
	public void guardarCalendario(CalendarioVO calendarioVO) throws DaoException
	{
		this.calendarioDAO.guardarCalendario(calendarioVO);
	}
}
