// @annotations-disabled tagSet="websphere" tagSet="ejb"
package cl.araucana.cp.distribuidor.business.ejbs;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.ejb.SessionContext;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cache.EhCache;
import org.hibernate.impl.SessionFactoryImpl;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.hibernate.stat.Statistics;

import cl.araucana.cp.distribuidor.base.Constants;
import cl.araucana.cp.distribuidor.business.beans.Estadistica;
import cl.araucana.cp.distribuidor.business.mgr.AsignadorMgr;
import cl.araucana.cp.distribuidor.business.mgr.CargaConfigMgr;
import cl.araucana.cp.distribuidor.business.mgr.ProcesadorMgr;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NodoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.distribuidor.hibernate.utils.HibernateUtil;
import cl.araucana.cp.distribuidor.logger.InitAuditLogger;
import cl.araucana.cp.mail.ReportaError;
import cl.araucana.cp.mail.data.Mail;

/**
 * Bean implementation class for Session Bean: DistribuidorSession
 *
 * @ejb.bean name="DistribuidorSession" type="Stateless" jndi-name="ejb/cl/araucana/distribuidor/business/ejbs/DistribuidorSessionHome" view-type="remote" transaction-type="Bean"
 *
 * @ejb.home remote-class="cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionHome"
 *
 * @ejb.interface remote-class="cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSession"
 *
 */
public class DistribuidorSessionBean implements javax.ejb.SessionBean 
{
	private static final long serialVersionUID = -1938982973051296343L;
	private static Logger logger = null;
	private static String logjFile = "log4jPropertiesFile.properties";
	private SessionContext mySessionCtx;
	private Session session = null;
	private HashMap parametros = new HashMap();
	
	static
	{
		try
		{
			Properties prop = new Properties();
			prop.load(DistribuidorSessionBean.class.getResourceAsStream(logjFile)); 
			PropertyConfigurator.configure(prop);
			logger = Logger.getLogger(DistribuidorSessionBean.class);
			InitAuditLogger.process();HashMap tmp = new HashMap();
			tmp.put("1", "" + 10);
			tmp.put("2", "" + 1);
			tmp.put("3", "" + 9);
			tmp.put("4", "" + 2);
			tmp.put("5", "" + 7);
			tmp.put("6", "" + 2);
			tmp.put("7", "" + 2);
			tmp.put("8", "" + 0);
			tmp.put("20", "" + 5);
			tmp.put("21", "" + 1);
			tmp.put("22", "" + 3);
			tmp.put("23", "" + 1);
			tmp.put("24", "" + 0);
			tmp.put("25", "" + 0);
			tmp.put("40", "" + 5);
			tmp.put("41", "" + 1);
			tmp.put("42", "" + 3);
			tmp.put("43", "" + 1);
			tmp.put("44", "" + 0);
			tmp.put("60", "" + 0 + "#" + 1);
			Constants.TOTAL_MX_SECCION = tmp;
		} catch (IOException e)
		{
			logger.error("Problemas configurando log4j", e);
		}
		try
		{
			HibernateUtil.getSessionFactory();
		} catch (Exception e)
		{
			logger.error("problemas con la inicializacion", e);
		}
	}

	private Session getSession() throws DaoException 
	{
		try
		{
			if (this.session == null)
				this.session = HibernateUtil.getSession();
			if (this.session.isConnected())
				return this.session;
			this.session = HibernateUtil.getSession();
			return this.session;
		} catch (Exception e)
		{
			logger.error("problemas obtencion session hibernate EJB", e);
			throw new DaoException("ERROR al tratar de obtener session hibernate en EJB:" + e); 
		} finally
		{
			logger.info("Conexion conectada?" + this.session.isConnected());
		}
	}

	public SessionContext getSessionContext() 
	{
		return this.mySessionCtx;
	}

	public void setSessionContext(SessionContext ctx) 
	{
		this.mySessionCtx = ctx;
	}

	public void ejbCreate() throws CreateException, IOException, Exception 
	{
    	logger.debug("DistribuidorSessionBean:ejbCreate2");
    	if (!cargaConfiguracion())
    		throw new Exception("configuracion parametros/conceptos EJB invalida para procesamiento");
	}

	public boolean cargaConfiguracion() throws DaoException
	{
		try
		{
			if (this.session != null)
			{
				this.session.disconnect();
				this.session.close();
			}
		} catch (Exception e1)
		{
			logger.error("problemas al reiniciar conn con DB2para carga de configuracion", e1);
		}
    	CargaConfigMgr cargaConfig = new CargaConfigMgr(getSession());
    	int result = cargaConfig.valida();
		this.parametros = cargaConfig.getParametros();

    	if (result > 0) //se encontraron problemas, => se reportan, si se puede
    	{
			if (this.parametros.containsKey("" + Constants.PARAM_MAIL_TO) && 
					this.parametros.containsKey("" + Constants.PARAM_MAIL_HOST_LOCAL) && 
					this.parametros.containsKey("" + Constants.PARAM_MAIL_FROM) && 
					this.parametros.containsKey("" + Constants.PARAM_MAIL_USER) && 
					this.parametros.containsKey("" + Constants.PARAM_MAIL_PASS) && 
					this.parametros.containsKey("" + Constants.PARAM_MAIL_HOST_TO) &&
					this.parametros.containsKey("" + Constants.PARAM_MAIL_PORT))
    		{
    			try
    			{
	    			Mail mail = getMail();
	    			mail.setContenido(cargaConfig.getMsg());
	
	    	        InetAddress addr = InetAddress.getLocalHost();
	    			mail.setDescCorta("Ha ocurrido un error al iniciar EJB (" + addr.getHostAddress() + ", " + addr.getCanonicalHostName() + "), no se encontro la configuracion necesaria:");
	    			ReportaError.enviar(mail);
				} catch (Exception e)
				{
    			}
			}
    		return false;
    	}
    	return true;
	}

	private Mail getMail()
	{
		Mail mail = new Mail(Mail.ERROR, (String)this.parametros.get("" + Constants.PARAM_MAIL_TO), 
				(String)this.parametros.get("" + Constants.PARAM_MAIL_HOST_TO), 
				Integer.parseInt((String)this.parametros.get("" + Constants.PARAM_MAIL_PORT)),
				(String)this.parametros.get("" + Constants.PARAM_MAIL_FROM), (String)this.parametros.get("" + Constants.PARAM_MAIL_USER), 
				(String)this.parametros.get("" + Constants.PARAM_MAIL_PASS));
		mail.setLocalHost((String)this.parametros.get("" + Constants.PARAM_MAIL_HOST_LOCAL));
		return mail;
	}

	public void ejbActivate() 
	{
		logger.info("DistribuidorSessionBean:ejbActivate");
	}

	public void ejbPassivate() 
	{
	}

	public void ejbRemove() 
	{
	}

	public HashMap asigna(int idEnvio, String idPersona) throws DaoException 
	{
    	logger.info("DistribuidorSessionBean:asigna");

    	AsignadorMgr asignadorMgr = new AsignadorMgr(getSession());
    	try
    	{
	    	HibernateUtil.setIdUser(idPersona);
    		return asignadorMgr.asigna(idEnvio);
    	} catch (Exception e)
		{
			logger.error("ERROR en DistribuidorSessionBean:asigna:", e);
			try
			{
    			Mail mail = getMail();

				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw));
    			mail.setContenido(new StringBuffer(sw.toString()));

    	        InetAddress addr = InetAddress.getLocalHost();
				mail.setDescCorta("Ha ocurrido un error al asignar nodo en distribuidor EJB(" + addr.getHostAddress() + ", " + addr.getCanonicalHostName()
						+ "), no se encontro la configuracion necesaria:");
    			ReportaError.enviar(mail);
			} catch (Exception ex)
			{
			}
		} finally
		{
			try
			{
				HibernateUtil.closeSession();
			} catch (Exception e)
			{
			}
		}
		return null;
	}

	//asignacion solo para validacion 1 trabajador (desde WEB)
	public NodoVO asigna(String idPersona) throws DaoException 
	{
    	logger.info("DistribuidorSessionBean:asigna");

   	 	Transaction tx = null;
		tx = getSession().beginTransaction();

    	AsignadorMgr asignadorMgr = new AsignadorMgr(getSession());
    	try
    	{
	    	HibernateUtil.setIdUser(idPersona);
    		NodoVO nodo = asignadorMgr.asigna();
			tx.commit();
			logger.info("asignando nodo:" + nodo.getIdNodo() + "::");
			return nodo;
    	} catch (Exception e)
		{
			tx.rollback();
			logger.error("ERROR en DistribuidorSessionBean:asigna:", e);
			try
			{
    			Mail mail = getMail();

				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw));
    			mail.setContenido(new StringBuffer(sw.toString()));

    	        InetAddress addr = InetAddress.getLocalHost();
				mail.setDescCorta("Ha ocurrido un error al asignar nodo en distribuidor EJB(" + addr.getHostAddress() + ", " + addr.getCanonicalHostName()
						+ "), no se encontro la configuracion necesaria:");
    			ReportaError.enviar(mail);
			} catch (Exception ex)
			{
			}
		} finally
		{
			try
			{
				HibernateUtil.closeSession();
			} catch (Exception e)
			{
			}
		}
		return null;
	}

	public boolean actualizaBalanceo(String idPersona) throws DaoException
	{
    	logger.info("DistribuidorSessionBean:actualizaBalanceo");

   	 	Transaction tx = null;
		tx = getSession().beginTransaction();

    	AsignadorMgr asignadorMgr = new AsignadorMgr(getSession());
    	try
    	{
	    	HibernateUtil.setIdUser(idPersona);
    		asignadorMgr.actualizaBalanceo();
			tx.commit();
	   	 	logger.info("actualizaBalanceo finalizado::");
			return true;
    	} catch (Exception e)
		{
			tx.rollback();
			logger.error("ERROR en DistribuidorSessionBean:actualizaBalanceo:", e);
			try
			{
    			Mail mail = getMail();

				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw));
    			mail.setContenido(new StringBuffer(sw.toString()));

    	        InetAddress addr = InetAddress.getLocalHost();
				mail.setDescCorta("Ha ocurrido un error al actualizar balanceo (cierre periodo) en distribuidor EJB(" + addr.getHostAddress() + ", " + addr.getCanonicalHostName()
						+ "), no se encontro la configuracion necesaria:");
    			ReportaError.enviar(mail);
			} catch (Exception ex)
			{
			}
		} finally
		{
			try
			{
				HibernateUtil.closeSession();
			} catch (Exception e)
			{
			}
		}
		//NodoVO nodo = new NodoVO(1, 2809, 3, 3, 4, "host", "descripcion", "iiop://192.168.100.78:2809", "com.ibm.websphere.naming.WsnInitialContextFactory");
		logger.info("DistribuidorSessionBean:actualizaBalanceo: problemas durante la ejecucion");
		return false;
	}

	public StringBuffer getEnv()
	{
    	logger.info("DistribuidorSessionBean:getEnv");
		StringBuffer sb = new StringBuffer();
		sb.append("\n\n\nen EJBBBBBBBBBB!!\n\n\n");
		try 
		{
	   	 	//cache();
	        InetAddress addr = InetAddress.getLocalHost();
	    
	        sb.append("\n\ndata:host:" + addr.getHostAddress() + ":name:" + addr.getHostName() + ":Canonical:" + addr.getCanonicalHostName() + ":");

	    } catch (UnknownHostException ex)
	    {
			logger.error("ERROR en getEnv EJB:", ex);
	    }
		return sb;
	}

	public void valida(String idDescriptor, String idPersona, Properties mapConceptos)
	{
    	logger.info("DistribuidorSessionBean:valida");
    	try
    	{
	    	HibernateUtil.setIdUser(idPersona);
	    	ProcesadorMgr procesadorMgr = new ProcesadorMgr(getSession());
	    	this.parametros.put("user", idPersona);
    		procesadorMgr.valida(idDescriptor, mapConceptos, this.parametros);
    	//	cache();		
    	} catch (Exception e)
		{
			logger.error("ERROR en valida EJB:", e);
		} finally
		{
			try
			{
				HibernateUtil.closeSession();
			} catch (Exception e)
			{
			}
		}
	}
	
//	clillo 3-12-14 por Reliquidación, para manetner independietes
	public List valida(int idCotizPend, String idPersona, String oldRut, CotizanteVO cotizante){
		return valida(idCotizPend, idPersona, oldRut, cotizante, 0);
	}
	
//	clillo 3-12-14 por Reliquidación
	//public List valida(int idCotizPend, String idPersona, String oldRut, CotizanteVO cotizante)
	public List valida(int idCotizPend, String idPersona, String oldRut, CotizanteVO cotizante, int periodo_old)
	{
    	logger.info("DistribuidorSessionBean:validaCotizante");
		//Transaction tx = null;
    	try
    	{
	    	HibernateUtil.setIdUser(idPersona);
    		this.session = getSession();
    		//tx = this.session.beginTransaction();
	    	ProcesadorMgr procesadorMgr = new ProcesadorMgr(this.session);
//	    	clillo 3-12-14 por Reliquidación
    		//List result = procesadorMgr.valida(idCotizPend, oldRut, cotizante, this.parametros);
	    	List result = procesadorMgr.valida(idCotizPend, oldRut, cotizante, this.parametros, periodo_old);
    		//tx.commit();
    		return result;
    	} catch (Exception e)
		{
    		//if (tx != null)
    		//	tx.commit();
			logger.error("ERROR en validaCotizante EJB:", e);
			return null;
		} finally
		{
			/*try
			{
				HibernateUtil.closeSession();
			} catch (Exception e)
			{
			}*/
		}
	}

	public HashMap validaCarga(String idPersona, char tipoProceso, HashMap nominas)
	{
    	logger.info("DistribuidorSessionBean:validaCarga");
		Transaction tx = null;
    	try
    	{
	    	HibernateUtil.setIdUser(idPersona);
    		this.session = getSession();
    		tx = this.session.beginTransaction();
	    	ProcesadorMgr procesadorMgr = new ProcesadorMgr(this.session);
	    	HashMap result = procesadorMgr.validaCargas(tipoProceso, nominas, this.parametros, idPersona);
    		//tx.commit();
    		return result;
    	} catch (Exception e)
		{
    		if (tx != null)
    			//tx.commit();
			logger.error("ERROR en validaCotizante EJB:", e);
			return null;
		} finally
		{
			try
			{
				//HibernateUtil.closeSession();
			} catch (Exception e)
			{
			}
		}
	}
	
//	clillo 3-12-14 por Reliquidación
	//public int eliminaTrabajador(boolean pendiente, int rutEmpresa, int idConvenio, char tipoProceso, int idTrabajador, String idPersona)
	public int eliminaTrabajador(boolean pendiente, int rutEmpresa, int idConvenio, char tipoProceso, int idTrabajador, String idPersona, int periodo)
	{
    	logger.info("DistribuidorSessionBean:eliminaTrabajador");	
		Transaction tx = null;
    	try
    	{
	    	HibernateUtil.setIdUser(idPersona);
    		this.session = getSession();
			tx = this.session.beginTransaction();
	    	ProcesadorMgr procesadorMgr = new ProcesadorMgr(this.session);
    		//int result = procesadorMgr.eliminar(pendiente, rutEmpresa, idConvenio, tipoProceso, idTrabajador, this.parametros);
	    	int result = procesadorMgr.eliminar(pendiente, rutEmpresa, idConvenio, tipoProceso, idTrabajador, this.parametros, periodo);
			tx.commit();
    		return result;
    	} catch (Exception e)
		{
			logger.error("ERROR en eliminaTrabajador EJB:", e);
			if (tx != null)
				tx.rollback();
			return -1;
		} finally
		{
			try
			{
				HibernateUtil.closeSession();
			} catch (Exception e)
			{
			}
		}
	}
	
	public List getEstadisticas(String idPersona)
	{
		try
		{
	    	HibernateUtil.setIdUser(idPersona);
			CacheManager.create();
			CacheManager cm = CacheManager.getInstance();
			String[] nombres = cm.getCacheNames();
			List lista = new ArrayList();
			
			if (nombres != null)
			{
				for (int i = 0; i < nombres.length; i++)
				{
					Estadistica e = new Estadistica(nombres[i]);
					Ehcache ehCache = cm.getEhcache(nombres[i]);
					logger.info("agregando estadisticas region nombre:" + nombres[i] + ":" + (new Date()).getTime() + "::");
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
			logger.error("ERROR en getEstadisticas EJB:" + e);
			return null;
		}
	}
	
	public void limpiaCache(String idPersona)
	{
		try
		{
	    	HibernateUtil.setIdUser(idPersona);
			logger.info("limpiaCache EJB::");
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
			logger.error("ERROR en limpiaCache EJB:" + e);
		}		
	}
	
	public void limpiaCache(String idPersona, String region)
	{
		try
		{
	    	HibernateUtil.setIdUser(idPersona);
			logger.info("limpiaCache EJB region:" + region + "::");
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
			logger.error("ERROR en limpiaCache EJB:", e);
		}
		try
		{
			cargaConfiguracion();
		} catch (Exception e)
		{
			logger.error("ERROR en recarga de parametros EJB:", e);
		}
	}

	public void cache()
	{
		try
		{
			logger.debug("CACHE EJB:");
			SessionFactoryImpl sfi = (SessionFactoryImpl)getSession().getSessionFactory();

			Statistics stats = sfi.getStatistics();
			if (stats != null)
			{
				logger.debug("QueryCacheHitCount:" + stats.getQueryCacheHitCount() + "::");
				logger.debug("QueryCacheMissCount:" + stats.getQueryCacheMissCount() + "::");
				logger.debug("QueryCachePutCount:" + stats.getQueryCachePutCount() + "::");
				logger.debug("SecondLevelCachePutCount:" + stats.getSecondLevelCachePutCount() + "::");
				logger.debug("SecondLevelCacheMissCount:" + stats.getSecondLevelCacheMissCount() + "::");
				logger.debug("SecondLevelCacheHitCount:" + stats.getSecondLevelCacheHitCount() + "::");
				String regiones[] = stats.getSecondLevelCacheRegionNames();
				if (regiones != null)
				{
					for (int i = 0; i < regiones.length; i++)
					{
						SecondLevelCacheStatistics slcs = stats.getSecondLevelCacheStatistics(regiones[i]);
						logger.debug("Second CategoryName:" + slcs.getCategoryName() + "::");
						logger.debug("Second ElementCountInMemory:" + slcs.getElementCountInMemory() + "::");
						logger.debug("Second ElementCountOnDisk:" + slcs.getElementCountOnDisk() + "::");
						logger.debug("Second HitCount:" + slcs.getHitCount() + "::");
						logger.debug("Second MissCount:" + slcs.getMissCount() + "::");
						logger.debug("Second PutCount:" + slcs.getPutCount() + "::");
						logger.debug("Second SizeInMemory:" + slcs.getSizeInMemory() + "::");
						logger.debug("Second Class:" + slcs.getClass().getName() + "::");
					}
				} else
					logger.debug("getSecondLevelCacheRegionNames == null!!");
				logger.debug("fin stats!!");
			} else
				logger.debug("getStatistics == null!!");

			String[] lista = sfi.getStatistics().getSecondLevelCacheRegionNames();
			for (int i = 0; i < lista.length; i++)
			{
				logger.debug("region:" + lista[i] + ":" + (new Date()).getTime() + "::");
				EhCache cache = (EhCache)sfi.getSecondLevelCacheRegion(lista[i]); 
				if (cache != null)
				{
					logger.debug(lista[i] + " != null");
					logger.debug(""+cache.getElementCountInMemory());
					logger.debug(""+cache.getSizeInMemory());
					Map m = cache.toMap();
					if (m != null)
					{
						logger.debug("map size:" + m.size() + "::");
    					for (Iterator it = m.keySet().iterator(); it.hasNext();)
    					{
    						Object o = it.next();
    						logger.debug("BB:" + o + ":" + m.get(o) + ":" + o.getClass().getName() + ":" + (m.get(o)).getClass().getName() + "::");					
   					}
					} else
						logger.error("\n\nmapa == null?!?!?\n\n");
				} else
					logger.debug(lista[i] + " == null");
			}

			//CacheManager cm = CacheManager.getInstance();
			//String nombres[] = cm.getCacheNames();
			
			CacheManager.create();
			CacheManager cm = CacheManager.getInstance();
			String[] nombres = cm.getCacheNames();
			
			if (nombres != null)
			{
				for (int i = 0; i < nombres.length; i++)
				{
					Ehcache ehCache = cm.getEhcache(nombres[i]);
					logger.debug("nombre:" + nombres[i] + ":" + (new Date()).getTime() + "::");
					logger.debug("Size:" + ehCache.getSize() + "::");
					logger.debug("StatisticsAccuracy:" + ehCache.getStatisticsAccuracy() + "::");
					logger.debug("isDisabled:" + ehCache.isDisabled()  + "::");
					logger.debug("toString:" + ehCache.toString() + "::");
					net.sf.ehcache.Statistics st = ehCache.getStatistics();

					logger.debug("AssociatedCacheName:" + st.getAssociatedCacheName() + "::");
					logger.debug("CacheHits:" + st.getCacheHits() + "::");
					logger.debug("CacheMisses:" + st.getCacheMisses() + "::");
					logger.debug("EvictionCount:" + st.getEvictionCount() + "::");
					logger.debug("InMemoryHits:" + st.getInMemoryHits() + "::");
					logger.debug("ObjectCount:" + st.getObjectCount() + "::");
					logger.debug("OnDiskHits:" + st.getOnDiskHits() + "::");
					logger.debug("StatisticsAccuracy:" + st.getStatisticsAccuracy() + "::");
					logger.debug("StatisticsAccuracyDescription:" + st.getStatisticsAccuracyDescription() + "::");
					logger.debug("toString:" + st.toString() + "::");
					List l = ehCache.getKeys();
					if (l != null)
					{
						logger.debug("\tlista size:" + l.size() + "::");
    					for (Iterator it = l.iterator(); it.hasNext();)
    					{
    						Object o = it.next();
    						logger.debug("\t:" + o + ":" + o.getClass().getName() + ":" + (new Date()).getTime() + "::");
    						Element e = ehCache.getQuiet(o);
    						logger.debug("\t\tCreationTime:" + e.getCreationTime() + "::");
    						logger.debug("\t\tExpirationTime:" + e.getExpirationTime() + "::");
    						logger.debug("\t\tHitCount:" + e.getHitCount() + "::");
    						logger.debug("\t\tLastAccessTime:" + e.getLastAccessTime() + "::");
    						logger.debug("\t\tLastUpdateTime:" + e.getLastUpdateTime() + "::");
    						logger.debug("\t\tTimeToIdle:" + e.getTimeToIdle() + "::");
    						logger.debug("\t\tTimeToLive:" + e.getTimeToLive() + "::");
    						logger.debug("\t\tVersion:" + e.getVersion() + "::");
    						logger.debug("\t\tKey:" + e.getKey() + "::");
    						logger.debug("\t\tValue:" + e.getValue() + "::");

    						Element e2 = ehCache.getQuiet(o);
    						logger.debug("\t\tCreationTime2:" + e2.getCreationTime() + "::");
    						logger.debug("\t\tExpirationTime2:" + e2.getExpirationTime() + "::");
    						logger.debug("\t\tHitCount2:" + e2.getHitCount() + "::");
    						logger.debug("\t\tLastAccessTime2:" + e2.getLastAccessTime() + "::");
    						logger.debug("\t\tLastUpdateTime2:" + e2.getLastUpdateTime() + "::");
    					}
					} else
						logger.error("\n\nlista == null?!?!?\n\n");
					logger.debug("fin EH CACHE::\n\n");
				}
				logger.debug("fin CACHE::");
			} else
				logger.debug("nombres == null!!");
		} catch (Exception e)
		{
			logger.error("ERROR en cache EJB:", e);
		}
	}
}
