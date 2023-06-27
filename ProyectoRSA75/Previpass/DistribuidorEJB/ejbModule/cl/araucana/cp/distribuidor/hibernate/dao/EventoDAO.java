package cl.araucana.cp.distribuidor.hibernate.dao;

import java.sql.Date;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.cp.distribuidor.hibernate.beans.EventoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;

public class EventoDAO 
{
	private static Logger log = Logger.getLogger(EventoDAO.class);

	public static void registraEvento(String idAgente, int idTipoEvento, String parametros, Session session)throws DaoException
	{
		try
		{
			log.debug("EventoDAO:registraEvento");
			EventoVO evento = new EventoVO();
			evento.setIdTipoEvento(idTipoEvento);
			if(idAgente==null){
				idAgente="0";
			}
			evento.setIdAgente(new Integer(idAgente.trim()).intValue());
			evento.setParametros(parametros);
			evento.setCuando(new Date((new java.util.Date()).getTime()));
			session.save(evento);
			session.flush();
		} catch (Exception ex)
		{	
			log.info("Evento, idTipoEvento: " + idTipoEvento);
			log.info("Evento, idAgente: " + idAgente);
			log.info("Evento, parametros: " + parametros);
			log.info("Evento, session isConnected: " + session.isConnected());
			log.error("ERROR EventoDAO:registraEvento:" + ex);
			throw new DaoException("Problemas registraEvento", ex);
		}
	}
}
