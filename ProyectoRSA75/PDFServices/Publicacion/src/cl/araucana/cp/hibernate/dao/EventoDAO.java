package cl.araucana.cp.hibernate.dao;

import java.sql.Date;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.hibernate.beans.EventoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) EventoDao.java 1.5 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/
/**
 * @author jsalazar
 * @author cchamblas
 * 
 * @version 1.5
 */
public class EventoDAO 
{
	private static Logger log = Logger.getLogger(EventoDAO.class);
	/**
	 * registra evento
	 * @param idAgente
	 * @param idTipoEvento
	 * @param parametros
	 * @param session
	 * @throws DaoException
	 */
	public static void registraEvento(String idAgente, int idTipoEvento, String parametros, Session session)throws DaoException
	{
		try
		{
			log.debug("EventoDAO:registraEvento");
			EventoVO evento = new EventoVO();
			evento.setIdTipoEvento(idTipoEvento);
			evento.setIdAgente(new Integer(idAgente.trim()).intValue());
			evento.setParametros(parametros);
			evento.setCuando(new Date((new java.util.Date()).getTime()));
			session.save(evento);
			session.flush();
		} catch (Exception ex)
		{
			log.error("ERROR EventoDAO:registraEvento:" + ex);
			throw new DaoException("Problemas registraEvento", ex);
		}
	}
}
