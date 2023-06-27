package cl.araucana.adminCpe.logger;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.EventoDAO;
import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.logger.beans.Parametro;
import cl.araucana.adminCpe.logger.beans.TipoEvento;
import cl.araucana.cp.distribuidor.base.Utils;
import cl.araucana.cp.distribuidor.business.beans.ObjectToLog;

public class AuditLogger 
{
	private static final Logger logger = Logger.getLogger(AuditLogger.class);

	protected static Map tiposEvento;
	private static ResourceBundle equivalencias = ResourceBundle.getBundle("cl.araucana.adminCpe.logger.equivalencias");

	/**
	 * 
	 * @param accion
	 * @param objectToLog
	 * @param session
	 */
	public static void registrarEvento(String accion, ObjectToLog objectToLog, Session session)
	{
		try 
		{
			if (true)
				return;
			//logger.setLevel(Level.DEBUG);
			logger.debug("-> obtener equivalencia del VO a Tipo de Evento");
			String idUser = HibernateUtil.getIdUser();
			String nombreClase = objectToLog.getClassName();
			logger.debug("\n\nAuditLogger:nombreClase:" + nombreClase + ":idUser:" + idUser + "::");
			Map parametrosEntrada = objectToLog.getParametros();
			StringBuffer nombreTipoEvento = new StringBuffer(equivalencias.getString(nombreClase));
			if (nombreTipoEvento.length()==0) throw new AuditLoggerException("No se encuentra la equivalencia de: " + nombreClase);

			logger.debug("-> concatenar accion (" + accion + ") a tipo de evento " + nombreTipoEvento + "::");
			nombreTipoEvento.append("_");
			nombreTipoEvento.append(accion);

			logger.debug("-> obtener tipo de evento y sus parametros:" + nombreTipoEvento + "::");
			TipoEvento tipoEvento = (TipoEvento) AuditLogger.tiposEvento.get(nombreTipoEvento.toString());
			if (tipoEvento == null) throw new AuditLoggerException("No se encuentra el tipo de evento: " + nombreTipoEvento);
			List parametros = tipoEvento.getParametros();
			
			logger.debug("-> ");
			logger.debug("-> concatenar los parametros");
			StringBuffer cadenaParametros = new StringBuffer();
			for (Iterator iterator = parametros.iterator(); iterator.hasNext();) 
			{
				Parametro parametro = (Parametro) iterator.next();
				String valorParametro = (String)parametrosEntrada.get(parametro.getId());
				logger.debug("  -> concatenar: " + valorParametro + ": (corresponde a parametro: " + parametro.getId()+")");
				cadenaParametros.append(Utils.rellena(parametro.getTipo() == 6, parametro.getLargo(), " ", valorParametro));
			}

			logger.debug("-> Crear EventoDAO y persistr el evento");
			EventoDAO.registraEvento(idUser, tipoEvento.getId(), cadenaParametros.toString(), session);
		} catch (Exception e) 
		{
			logger.error("No se pudo registrar log", e);
		}
	}
}
