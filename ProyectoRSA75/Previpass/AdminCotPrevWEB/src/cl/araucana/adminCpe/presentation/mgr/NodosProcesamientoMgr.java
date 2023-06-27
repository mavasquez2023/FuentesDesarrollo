package cl.araucana.adminCpe.presentation.mgr;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import cl.araucana.adminCpe.hibernate.dao.NodoDAO;
import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.business.beans.Estadistica;
import cl.araucana.cp.distribuidor.business.beans.ReportStats;
import cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSession;
import cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionHome;
import cl.araucana.cp.distribuidor.hibernate.beans.NodoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
/*
* @(#) NodosProcesamientoMgr.java 1.3 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.3
 */
public class NodosProcesamientoMgr
{
	private String idPersona;
	private NodoDAO nodoDao;
	final String JNDIName = "ejb/session/Distribuidor";
	private static Logger log = Logger.getLogger(NodosProcesamientoMgr.class);

	public NodosProcesamientoMgr(Session session, String idPersona)
	{
		this.nodoDao = new NodoDAO(session);
		this.idPersona = idPersona;
	}

	/**
	 * lista stats nodo
	 * @return
	 * @throws DaoException
	 */
	public List getStatsXNodo() throws DaoException
	{
		try
		{
			List result = new ArrayList();
			List listaNodos = this.nodoDao.getListaNodosActivos();
			for (Iterator it = listaNodos.iterator(); it.hasNext();)
			{
				NodoVO nodo = (NodoVO)it.next();
				DistribuidorSession nodoEjb = null;
				List lista = null;
				try
				{
					nodoEjb = conecta(nodo);
					lista = nodoEjb.getEstadisticas(this.idPersona);
					if (lista != null)
						result.add(creaReportStats(Constants.MSG_STATS_OK, nodo, lista));
					else
						result.add(creaReportStats(Constants.MSG_STATS_ERROR, nodo, new ArrayList()));
				} catch (RemoteException e)
				{
					result.add(creaReportStats(Constants.MSG_STATS_ERROR_CONN, nodo, new ArrayList()));
				}
			}
			result.add(creaReportStatsWeb());
			return result;
		} catch (DaoException e)
		{
			log.error("error obteniendo estadisticas: ", e);
			throw new DaoException("Problemas obteniendo estadisticas", e);
		}
	}
	/**
	 * distribuidor session
	 * @param nodo
	 * @return
	 * @throws RemoteException
	 */
	private DistribuidorSession conecta(NodoVO nodo) throws RemoteException
	{
		try
		{
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY, nodo.getContextFactory().trim());
			props.put(Context.PROVIDER_URL, nodo.getUrl().trim());
			props.put(Context.SECURITY_AUTHENTICATION, "simple");
			props.put(Context.SECURITY_PRINCIPAL, "admin");
			props.put(Context.SECURITY_CREDENTIALS, "admin");
			log.info("conectando a nodo:" + nodo.getUrl().trim() + ":");
	
			InitialContext initContext = new InitialContext(props);
	        
	 		Object obj2 = initContext.lookup(this.JNDIName);
	 		DistribuidorSessionHome home = (DistribuidorSessionHome) PortableRemoteObject.narrow(obj2, DistribuidorSessionHome.class);
		
			return home.create();
		} catch (Exception e)
		{
			log.error("error conectandose a nodo: ", e);
			throw new RemoteException("Problemas conectandose a nodo", e);
		}
	}
	
	private ReportStats creaReportStats(String msg, NodoVO nodo, List lista)
	{
		Estadistica totales = new Estadistica();
		for (Iterator it = lista.iterator(); it.hasNext();)
			totales.add((Estadistica)it.next());
		return new ReportStats(nodo.getNumConnDisponibles(), nodo.getIdNodo(), nodo.getDescripcion(), msg, lista, totales);
	}
	/**
	 * reporte crea stats web
	 * @return
	 */
	private ReportStats creaReportStatsWeb()
	{
		List lista = getEstadisticas();
		String msg = Constants.MSG_STATS_OK;
		Estadistica totales = new Estadistica();
		if (lista == null)
		{
			lista = new ArrayList();
			msg = Constants.MSG_STATS_ERROR;
		} else
			for (Iterator it = lista.iterator(); it.hasNext();)
				totales.add((Estadistica)it.next());

		return new ReportStats(0, -1, "Servidor Web", msg, lista, totales);
	}

	/**
	 * lista estadisticas
	 * @return
	 */
	public List getEstadisticas()
	{
		try
		{
			CacheManager.create();
			CacheManager cm = CacheManager.getInstance();
			String[] nombres = cm.getCacheNames();
			List lista = new ArrayList();
			
			if (nombres != null)
			{
				for (int i = 0; i < nombres.length; i++)
				{
					String nombre = nombres[i];
					Estadistica e = new Estadistica(nombre.substring(nombre.lastIndexOf('.') + 1));
					Ehcache ehCache = cm.getEhcache(nombre);
					log.info("agregando estadisticas region nombre (WEB):" + nombres[i] + ":" + (new Date()).getTime() + "::");
					net.sf.ehcache.Statistics st = ehCache.getStatistics();

					e.setCacheHits(st.getCacheHits());
					e.setCacheMisses(st.getCacheMisses());
					e.setInMemoryHits(st.getInMemoryHits());
					e.setOnDiskHits(st.getOnDiskHits());
					e.setObjectCount(st.getObjectCount());
					lista.add(e);
				}
			}
			return lista;
		} catch (Exception e)
		{
			log.error("ERROR en getEstadisticas WEB:" + e);
			return null;
		}
	}
	/**
	 * limpia cache
	 * @param lista
	 */
	public void limpiaCache(String[] lista)
	{
		for (int i = 0; i < lista.length; i++)
		{
			if (lista[i].equals("todo"))
			{
				try
				{
					limpiaCacheWeb();
					List listaNodos = this.nodoDao.getListaNodosActivos();
					for (Iterator it = listaNodos.iterator(); it.hasNext();)
					{
						NodoVO nodo = (NodoVO)it.next();
						DistribuidorSession nodoEjb = conecta(nodo);
						nodoEjb.limpiaCache(this.idPersona);
					}
					break;
				} catch (DaoException e)
				{
					log.error("error limpiaCache:", e);
				} catch (RemoteException e)
				{
					log.error("error limpiaCache:" , e);
				}				
			} else
			{
				if (lista[i].startsWith("check"))
				{
					int idNodo = new Integer(lista[i].substring(5)).intValue();
					if (idNodo == -1) //WEB
						limpiaCacheWeb();
					else if (idNodo > 0) //solo los seleccionados
					{
						try
						{
							NodoVO nodo = this.nodoDao.getNodo(idNodo);
							DistribuidorSession nodoEjb = conecta(nodo);
							nodoEjb.limpiaCache(this.idPersona);
						} catch (DaoException e)
						{
							log.error("error limpiaCache:", e);
						} catch (RemoteException e)
						{
							log.error("error limpiaCache:", e);
						}
					} 
				} else //por region
				{
					int idNodo = new Integer(lista[i].substring(0, lista[i].indexOf("_"))).intValue();
					if (idNodo > 0)
					{
						try
						{
							NodoVO nodo = this.nodoDao.getNodo(idNodo);
							DistribuidorSession nodoEjb = conecta(nodo);
							nodoEjb.limpiaCache(this.idPersona, lista[i]/*.substring(lista[i].indexOf("_") + 1)*/);
						} catch (DaoException e)
						{
							log.error("error limpiaCache:", e);
						} catch (RemoteException e)
						{
							log.error("error limpiaCache:", e);
						}
					} else if (idNodo == -1) //WEB
					{
						limpiaCacheWeb(lista[i].substring(lista[i].indexOf("_") + 1));
					}
				}
			}
		}
	}

	/**
	 * limpia cache web
	 *
	 */
	public void limpiaCacheWeb()
	{
		try
		{
			log.info("limpiaCacheWeb");
			CacheManager.create();
			CacheManager cm = CacheManager.getInstance();
			String[] nombres = cm.getCacheNames();
			
			if (nombres != null)
			{
				for (int i = 0; i < nombres.length; i++)
				{
					Ehcache ehCache = cm.getEhcache(nombres[i]);
					ehCache.removeAll();
					ehCache.clearStatistics();
				}
			}
		} catch (Exception e)
		{
			log.error("ERROR en limpiaCache WEB:" + e);
		}		
	}

	/**
	 * limpia cache web
	 * @param region
	 */
	public void limpiaCacheWeb(String region)
	{
		try
		{
			log.info("limpiaCacheWeb region:" + region + "::");
			CacheManager.create();
			CacheManager cm = CacheManager.getInstance();
			
			Ehcache ehCache = cm.getEhcache(region);
			if (ehCache != null)
			{
				ehCache.removeAll();
				ehCache.clearStatistics();
			}
		} catch (Exception e)
		{
			log.error("ERROR en limpiaCache WEB:" + e);
		}		
	}

	/**
	 * lista actualiza nodos
	 * @return
	 * @throws Exception
	 */
	public List actualizaNodos() throws Exception
	{
		try
		{
			List result = new ArrayList();
			List listaNodos = this.nodoDao.getListaNodosActivos();
			for (Iterator it = listaNodos.iterator(); it.hasNext();)
			{
				NodoVO nodo = (NodoVO)it.next();
				DistribuidorSession nodoEjb = null;
				try
				{
					nodoEjb = conecta(nodo);
					log.info("actualizando parametros nodo:" + nodo.getDescripcion() + "::");
					boolean resultado = nodoEjb.cargaConfiguracion();
					if (!resultado)
						result.add(nodo.getDescripcion() + ": " + Constants.MSG_REFRESH_PARAM_ERROR);
				} catch (RemoteException e)
				{
					result.add(nodo.getDescripcion() + ": " + Constants.MSG_STATS_ERROR_CONN);
				}
			}
			return result;
		} catch (DaoException e)
		{
			log.error("error actualizando parametros nodos (habilitados):", e);
			throw new DaoException("Problemas actualizando parametros nodos", e);
		}
	}

	/**
	 * actualiza nodo
	 * @param nodo
	 * @return
	 * @throws Exception
	 */
	public boolean actualizaNodo(NodoVO nodo) throws Exception
	{
		try
		{
			DistribuidorSession nodoEjb = null;
			try
			{
				nodoEjb = conecta(nodo);
				log.info("actualizando parametros nodo:" + nodo.getDescripcion() + "::");
				return nodoEjb.cargaConfiguracion();
			} catch (RemoteException e)
			{
				log.error("error actualizando parametros nodo:", e);
				return false;
			}
		} catch (DaoException e)
		{
			log.error("error actualizando parametros nodo:", e);
			throw new DaoException("Problemas actualizando parametros nodo", e);
		}
	}
}
