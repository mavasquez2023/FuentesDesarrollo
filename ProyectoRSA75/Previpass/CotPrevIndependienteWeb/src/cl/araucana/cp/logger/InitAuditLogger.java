package cl.araucana.cp.logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import cl.araucana.cp.distribuidor.hibernate.beans.ParametroAuditoriaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoEventoVO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;
import cl.araucana.cp.logger.beans.Parametro;
import cl.araucana.cp.logger.beans.TipoEvento;
/*
* @(#) InitAuditLogger.java 1.3 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cllanos
 * @author cchamblas
 * 
 * @version 1.3
 */
public class InitAuditLogger 
{
	private static final Logger logger = Logger.getLogger(InitAuditLogger.class);
	/**
	 * process
	 * @param session
	 */
	public static void process(Session session) 
	{
		logger.info("Iniciando parametros de LoggerCP");
		Map tiposEvento = new HashMap();

		try
		{
			logger.debug("-> obtener lista de tipos de evento");
			List pTiposEvento = session.createCriteria(TipoEventoVO.class).addOrder(Order.asc("idTipoEvento")).list();
			logger.debug("-> recorrer lista y por cada objeto copiarlo a TipoEvento especial para log");
			for (Iterator iterator = pTiposEvento.iterator(); iterator.hasNext();) 
			{
				TipoEventoVO pTipoEvento = (TipoEventoVO) iterator.next();
				logger.debug("  -> procesar tipo de evento: "+pTipoEvento.getIdTipoEvento());
				
				logger.debug("  -> crear y problar el objeto tipoEvento");
				TipoEvento tipoEvento =  new TipoEvento();
				tipoEvento.setParametros(new ArrayList());
				tipoEvento.setId(pTipoEvento.getIdTipoEvento());
				tipoEvento.setNombre(pTipoEvento.getNombre());

				logger.debug("  -> agregarlo al mapa con el nombre del tipo de evento como clave -> "+tipoEvento.getNombre());
				tiposEvento.put(tipoEvento.getNombre().trim(), tipoEvento);
			}

			
			logger.debug("  -> obtener parametros del tipo de evento");
			List pParametros = session.createCriteria(ParametroAuditoriaVO.class).addOrder(Order.asc("idTipoEvento")).list();
			Iterator iterator = pTiposEvento.iterator();
			TipoEventoVO tipoEvento = null;
			for (Iterator iterator2 = pParametros.iterator(); iterator2.hasNext();)
			{
				ParametroAuditoriaVO pParametro = (ParametroAuditoriaVO) iterator2.next();
				if (tipoEvento==null || tipoEvento.getIdTipoEvento() < pParametro.getIdTipoEvento())
					while (iterator.hasNext() && (tipoEvento = (TipoEventoVO) iterator.next()).getIdTipoEvento() < pParametro.getIdTipoEvento())
						;

				if (tipoEvento == null || tipoEvento.getIdTipoEvento() != pParametro.getIdTipoEvento())
				{
					logger.warn("   -> tipo de evento no existe! id:" + pParametro.getIdTipoEvento());
					continue;
				}

				logger.debug("     -> procesar parametro: " + pParametro.getIdParametro());
				Parametro parametro = new Parametro();
				parametro.setId(String.valueOf(pParametro.getIdParametro()));
				parametro.setLargo(pParametro.getLargo());
				parametro.setNombre(pParametro.getNombre());
				parametro.setTipo(pParametro.getIdTipoParametro());
				((TipoEvento)tiposEvento.get(tipoEvento.getNombre().trim())).getParametros().add(parametro);
			} 

			logger.debug("-> copiar a LoggerCP: "+tiposEvento);
			AuditLogger.tiposEvento = tiposEvento;
		} catch (Exception e)
		{
			logger.error("problemas en proceso contrab:", e);
		} finally
		{
			HibernateUtil.closeSession();
		}
	}
}
