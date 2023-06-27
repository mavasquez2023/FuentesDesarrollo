package cl.araucana.cp.presentation.mgr;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.cache.EhCache;
import org.hibernate.impl.SessionFactoryImpl;

import cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSession;
import cl.araucana.cp.distribuidor.business.ejbs.DistribuidorSessionHome;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NodoVO;
import cl.araucana.cp.distribuidor.hibernate.exceptions.DaoException;
import cl.araucana.cp.hibernate.beans.DistribuidorVO;
import cl.araucana.cp.hibernate.dao.DistribuidorDAO;
import cl.araucana.cp.hibernate.utils.HibernateUtil;

/*
 * @(#) DistribuidorMgr.java 1.19 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
/**
 * @author cchamblas
 * @author ccostagliola
 * 
 * @version 1.19
 */
public class DistribuidorMgr
{
	protected DistribuidorDAO distribuidorDao;
	private final String JNDIName = "ejb/session/Distribuidor";
	private final String JNDIName2 = "ejb/session/Distribuidor";
	private String idPersona;
	private DistribuidorSession nodoEjb = null;
	private Logger logger = Logger.getLogger(DistribuidorMgr.class);
	private DistribucionThread dt;

	public DistribuidorMgr(Session session, String idPersona)
	{
		this.idPersona = idPersona;
		this.distribuidorDao = new DistribuidorDAO(session);
	}

	/**
	 * carga nodo
	 * 
	 * @param idEnvio
	 * @param idPersona
	 * @return
	 */
	public HashMap asignaNodos(int idEnvio, String idPers)
	{
		try
		{
			this.dt = new DistribucionThread(idEnvio, idPers);
			return this.dt.getNodoTH();
		} catch (Exception ex)
		{
			return null;
		}
	}

	/**
	 * valida
	 * 
	 * @throws RemoteException
	 */
	public void valida(HashMap asignacionNodos)
	{
		try
		{
			HashMap hashNodo = new HashMap();
			HashMap hashAsig = new HashMap();
			for (Iterator it = asignacionNodos.keySet().iterator(); it.hasNext();)
			{
				String id = (String) it.next();
				NodoVO nodo = (NodoVO) asignacionNodos.get(id);
				if (hashAsig.containsKey("" + nodo.getIdNodo()))
				{
					ArrayList idsOld = (ArrayList)hashAsig.get("" + nodo.getIdNodo());
					idsOld.add(id);
					hashAsig.put("" + nodo.getIdNodo(), idsOld);
				} else
				{
					ArrayList ids = new ArrayList();
					ids.add(id);
					hashAsig.put("" + nodo.getIdNodo(), ids);
					hashNodo.put("" + nodo.getIdNodo(), nodo);
				}
			}
			for (Iterator it = hashAsig.keySet().iterator(); it.hasNext();)
			{
				String idNodo = (String) it.next();
				NodoVO nodo = (NodoVO) hashNodo.get(idNodo);
				ArrayList ids = (ArrayList)hashAsig.get(idNodo);
				DistribucionThread procesadorThread = new DistribucionThread(this.idPersona, ids, nodo);
				procesadorThread.start();
			}
		} catch (Exception e)
		{
			this.logger.error("DistribuidorMgr:valida:", e);
		}
	}

	/**
	 * lista valida
	 * 
	 * @param idCotizPend
	 * @param oldRut
	 * @param cotizante
	 * @return
	 * @throws RemoteException
	 */
	public List valida(int idCotizPend, String oldRut, CotizanteVO cotizante)
	{
		try
		{
			DistribuidorVO distribuidor = this.distribuidorDao.getNodoDistribuidor();
			InitialContext initContext;
			DistribuidorSessionHome home;
			DistribuidorSession distribuidorEjb = null;

			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY, distribuidor.getContextFactory().trim());
			props.put(Context.PROVIDER_URL, distribuidor.getUrl().trim());
			props.put(Context.SECURITY_AUTHENTICATION, "simple");
			props.put(Context.SECURITY_PRINCIPAL, "admin");
			props.put(Context.SECURITY_CREDENTIALS, "admin");

			this.logger.debug("distribuidor:" + distribuidor.getUrl().trim() + ":");

			initContext = new InitialContext(props);

			Object obj = initContext.lookup(this.JNDIName);
			home = (DistribuidorSessionHome) PortableRemoteObject.narrow(obj, DistribuidorSessionHome.class);

			distribuidorEjb = home.create();
			this.logger.debug("distribuidor:" + distribuidorEjb.getEnv().toString());
			NodoVO nodo = distribuidorEjb.asigna(this.idPersona);
			// nodo = new NodoVO(1, 1, 1, 1, 1, "iiop://192.168.100.78:2809", "d", "iiop://192.168.100.78:2809", "com.ibm.websphere.naming.WsnInitialContextFactory");
			Properties props2 = new Properties();
			props2.put(Context.INITIAL_CONTEXT_FACTORY, nodo.getContextFactory().trim());
			props2.put(Context.PROVIDER_URL, nodo.getUrl().trim());
			props2.put(Context.SECURITY_AUTHENTICATION, "simple");
			props2.put(Context.SECURITY_PRINCIPAL, "admin");
			props2.put(Context.SECURITY_CREDENTIALS, "admin");
			this.logger.debug("nodo:" + nodo.getUrl().trim() + ":");

			initContext = new InitialContext(props2);

			Object obj2 = initContext.lookup(this.JNDIName2);
			home = (DistribuidorSessionHome) PortableRemoteObject.narrow(obj2, DistribuidorSessionHome.class);

			this.nodoEjb = home.create();
			this.logger.debug("validacion WEB:rut trabajador:" + cotizante.getIdCotizante() + ":");

			return this.nodoEjb.valida(idCotizPend, this.idPersona, oldRut, cotizante);
		} catch (RemoteException e)
		{
			this.logger.error("ERROR RemoteException en distribuidorMgr:valida:", e);
			return null;
		} catch (Exception e)
		{
			this.logger.error("ERROR en distribuidorMgr:valida:", e);
			return null;
		} finally
		{
		}
	}

	/**
	 * lista valida
	 * 
	 * @param idCotizPend
	 * @param oldRut
	 * @param cotizante
	 * @return
	 * @throws RemoteException
	 */
	public HashMap validaCarga(char tipoProceso, HashMap nominas)
	{
		try
		{
			DistribuidorVO distribuidor = this.distribuidorDao.getNodoDistribuidor();
			InitialContext initContext;
			DistribuidorSessionHome home;
			DistribuidorSession distribuidorEjb = null;

			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY, distribuidor.getContextFactory().trim());
			props.put(Context.PROVIDER_URL, distribuidor.getUrl().trim());
			props.put(Context.SECURITY_AUTHENTICATION, "simple");
			props.put(Context.SECURITY_PRINCIPAL, "admin");
			props.put(Context.SECURITY_CREDENTIALS, "admin");

			this.logger.debug("distribuidor:" + distribuidor.getUrl().trim() + ":");

			initContext = new InitialContext(props);

			Object obj = initContext.lookup(this.JNDIName);
			home = (DistribuidorSessionHome) PortableRemoteObject.narrow(obj, DistribuidorSessionHome.class);

			distribuidorEjb = home.create();
			this.logger.debug("distribuidor:" + distribuidorEjb.getEnv().toString());
			NodoVO nodo = distribuidorEjb.asigna(this.idPersona);
			// nodo = new NodoVO(1, 1, 1, 1, 1, "iiop://192.168.100.78:2809", "d", "iiop://192.168.100.78:2809", "com.ibm.websphere.naming.WsnInitialContextFactory");
			Properties props2 = new Properties();
			props2.put(Context.INITIAL_CONTEXT_FACTORY, nodo.getContextFactory().trim());
			props2.put(Context.PROVIDER_URL, nodo.getUrl().trim());
			props2.put(Context.SECURITY_AUTHENTICATION, "simple");
			props2.put(Context.SECURITY_PRINCIPAL, "admin");
			props2.put(Context.SECURITY_CREDENTIALS, "admin");
			this.logger.debug("nodo:" + nodo.getUrl().trim() + ":");

			initContext = new InitialContext(props2);

			Object obj2 = initContext.lookup(this.JNDIName2);
			home = (DistribuidorSessionHome) PortableRemoteObject.narrow(obj2, DistribuidorSessionHome.class);

			this.nodoEjb = home.create();
			//this.logger.debug("validacion WEB:rut trabajador:" + cotizante.getIdCotizante() + ":");

			//return this.nodoEjb.valida(idCotizPend, this.idPersona, oldRut, cotizante);
			return this.nodoEjb.validaCarga(this.idPersona, tipoProceso, nominas);
		} catch (RemoteException e)
		{
			this.logger.error("ERROR RemoteException en distribuidorMgr:valida:", e);
			return null;
		} catch (Exception e)
		{
			this.logger.error("ERROR en distribuidorMgr:valida:", e);
			return null;
		} finally
		{
		}
	}
	/**
	 * elimina trabajador
	 * 
	 * @param pendiente
	 * @param rutEmpresa
	 * @param idConvenio
	 * @param tipoProceso
	 * @param idTrabajador
	 * @return
	 * @throws RemoteException
	 */
	public int eliminaTrabajador(boolean pendiente, int rutEmpresa, int idConvenio, char tipoProceso, int idTrabajador)
	{
		try
		{
			DistribuidorVO distribuidor = this.distribuidorDao.getNodoDistribuidor();
			InitialContext initContext;
			DistribuidorSessionHome home;
			DistribuidorSession distribuidorEjb = null;

			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY, distribuidor.getContextFactory().trim());
			props.put(Context.PROVIDER_URL, distribuidor.getUrl().trim());
			props.put(Context.SECURITY_AUTHENTICATION, "simple");
			props.put(Context.SECURITY_PRINCIPAL, "admin");
			props.put(Context.SECURITY_CREDENTIALS, "admin");

			this.logger.debug("distribuidor:" + distribuidor.getUrl().trim() + ":");

			initContext = new InitialContext(props);

			Object obj = initContext.lookup(this.JNDIName);
			home = (DistribuidorSessionHome) PortableRemoteObject.narrow(obj, DistribuidorSessionHome.class);

			distribuidorEjb = home.create();
			this.logger.debug("distribuidor:" + distribuidorEjb.getEnv().toString());
			NodoVO nodo = distribuidorEjb.asigna(this.idPersona);
			// nodo = new NodoVO(1, 1, 1, 1, 1, "iiop://192.168.100.78:2809", "d", "iiop://192.168.100.78:2809", "com.ibm.websphere.naming.WsnInitialContextFactory");
			Properties props2 = new Properties();
			props2.put(Context.INITIAL_CONTEXT_FACTORY, nodo.getContextFactory().trim());
			props2.put(Context.PROVIDER_URL, nodo.getUrl().trim());
			props2.put(Context.SECURITY_AUTHENTICATION, "simple");
			props2.put(Context.SECURITY_PRINCIPAL, "admin");
			props2.put(Context.SECURITY_CREDENTIALS, "admin");
			this.logger.debug("nodo:" + nodo.getUrl().trim() + ":");

			initContext = new InitialContext(props2);

			Object obj2 = initContext.lookup(this.JNDIName2);
			home = (DistribuidorSessionHome) PortableRemoteObject.narrow(obj2, DistribuidorSessionHome.class);

			this.nodoEjb = home.create();
			return this.nodoEjb.eliminaTrabajador(pendiente, rutEmpresa, idConvenio, tipoProceso, idTrabajador, this.idPersona, 0);
		} catch (RemoteException e)
		{
			this.logger.error("ERROR RemoteException en distribuidorMgr:elimina:", e);
			return -1;
		} catch (Exception e)
		{
			this.logger.error("ERROR en distribuidorMgr:elimina:", e);
			return -1;
		} finally
		{
		}
	}

	/**
	 * cache
	 * 
	 */
	public void cache()
	{
		try
		{
			this.logger.info("CACHE WEB MGR:");
			SessionFactoryImpl sfi = (SessionFactoryImpl) HibernateUtil.getSession().getSessionFactory();
			String[] lista = sfi.getStatistics().getSecondLevelCacheRegionNames();
			for (int i = 0; i < lista.length; i++)
			{
				this.logger.debug("region:" + lista[i] + "::");
				EhCache cache = (EhCache) sfi.getSecondLevelCacheRegion(lista[i]);
				if (cache != null)
				{
					this.logger.debug(lista[i] + " != null");
					this.logger.debug("" + cache.getElementCountInMemory());
					this.logger.debug("" + cache.getSizeInMemory());
					Map m = cache.toMap();
					if (m != null)
					{
						this.logger.debug("map size:" + m.size() + "::");
						for (Iterator it = m.keySet().iterator(); it.hasNext();)
						{
							Object o = it.next();
							this.logger.debug("BB:" + o + ":" + m.get(o) + ":" + o.getClass().getName() + ":" + (m.get(o)).getClass().getName() + "::");
						}
					} else
						this.logger.error("\n\nmapa == null?!?!?\n\n");
				} else
					this.logger.debug(lista[i] + " == null");
			}
		} catch (Exception e)
		{
			this.logger.error("ERROR en cache WEB:", e);
		}
	}
	/**
	 * Distribucion thread
	 * 
	 * @author ccostagliola
	 * 
	 */
	class DistribucionThread extends Thread
	{
		private DistribuidorSession nodoEjb2 = null;
		private String idPersonaTH;
		private ArrayList idsDescriptor;
		private int idEnvio;
		private NodoVO nodo;
		private Properties mapConceptos = new Properties();

		private Logger logTH = Logger.getLogger(DistribucionThread.class);

		DistribucionThread(String idPersona, ArrayList idsDesc, NodoVO n)
		{
			this.idPersonaTH = idPersona;
			this.idsDescriptor = idsDesc;
			this.nodo = n;
			try
			{
				this.mapConceptos.load(getClass().getResourceAsStream("/files/mapeoConceptos.properties"));
			} catch (IOException e)
			{
				this.logTH.error("ERROR en distribuidorMgr: cargando properties mapeoConcepto:", e);
			}
		}

		DistribucionThread(int id, String idPersona)
		{
			this.idPersonaTH = idPersona;
			this.idEnvio = id;
		}

		/**
		 * run
		 */
		public void run()
		{
			try
			{
				this.logTH.info("carga properties:idPersona:" + this.idPersonaTH + ":descriptor:" + this.idsDescriptor.size() + ":");

				Properties props2 = new Properties();			
				props2.put(Context.INITIAL_CONTEXT_FACTORY, this.nodo.getContextFactory().trim());
//				//TODO cambio url nodo "iiop://localhost:2809"	
				props2.put(Context.PROVIDER_URL, this.nodo.getUrl().trim());
				props2.put(Context.SECURITY_AUTHENTICATION, "simple");
				props2.put(Context.SECURITY_PRINCIPAL, "admin");
				props2.put(Context.SECURITY_CREDENTIALS, "admin");

				InitialContext initContext = new InitialContext(props2);

				Object obj2 = initContext.lookup(DistribuidorMgr.this.JNDIName);
				DistribuidorSessionHome home = (DistribuidorSessionHome) PortableRemoteObject.narrow(obj2, DistribuidorSessionHome.class);

				this.nodoEjb2 = home.create();

				this.logTH.debug("nodo:" + this.nodoEjb2.getEnv().toString());
				String id = null;
				for (Iterator it = this.idsDescriptor.iterator(); it.hasNext();)
				{
					try
					{
						id = (String)it.next();
						this.logTH.info("iniciando para procesar:" + id + ":nodo:" + this.nodo.getIdNodo() + "[" + this.nodo.getUrl().trim() + "]:");
						this.nodoEjb2.valida(id, this.idPersonaTH, this.mapConceptos);
					} catch (Throwable e)
					{
						this.logTH.error("Problemas procesando la nomina " + id, e);
					}
					this.logTH.info("Liberando nodo " + this.nodo.getIdNodo());
					try
					{
						HibernateUtil.getSession().beginTransaction();
						NodoVO nodoActualizado = (NodoVO)HibernateUtil.getSession().get(NodoVO.class, new Integer(this.nodo.getIdNodo()));
						nodoActualizado.libera();
						HibernateUtil.getSession().update(nodoActualizado);
						HibernateUtil.getSession().getTransaction().commit();
						this.logTH.info("Nodo " + nodoActualizado.getIdNodo() + " ahora tiene " + nodoActualizado.getNumConnDisponibles() + " conexiones");
					} catch (Throwable e)
					{
						this.logTH.error("Problemas al liberar el nodo " + this.nodo.getIdNodo(), e);
						try
						{
							HibernateUtil.getSession().getTransaction().rollback();
						} catch (Throwable ex)
						{
							this.logTH.error("Problemas tratando de deshacer la transaccion", ex);
						}
					} finally
					{
						try
						{
							HibernateUtil.closeSession();
						} catch (Throwable e)
						{
							this.logTH.error("Problemas liberando la sesion de hibernate", e);
						}
					}
				}
				this.nodoEjb2.remove();
			} catch (RemoteException e)
			{
				this.logTH.error("ERROR RemoteException en distribuidorMgr.valida:", e);
			} catch (Exception e)
			{
				this.logTH.error("ERROR en distribuidorMgr.valida:", e);
			}
		}

		/**
		 * nodo th
		 * 
		 * @return
		 * @throws RemoteException
		 * @throws DaoException
		 */
		public HashMap getNodoTH()
		{
			this.logTH.info("getNodoTH::");
			InitialContext initContext;
			DistribuidorSessionHome home;
			DistribuidorSession distribuidorEjb = null;

			HashMap result = null;
			try
			{
				DistribuidorVO distribuidor = getNodoDistribuidorTH();
				Properties props = new Properties();
				props.put(Context.INITIAL_CONTEXT_FACTORY, distribuidor.getContextFactory().trim());
//				//TODO cambio url nodo "iiop://localhost:2809"
				props.put(Context.PROVIDER_URL, distribuidor.getUrl().trim());
				props.put(Context.SECURITY_AUTHENTICATION, "simple");
				props.put(Context.SECURITY_PRINCIPAL, "admin");
				props.put(Context.SECURITY_CREDENTIALS, "admin");

				this.logTH.debug("distribuidor:" + distribuidor.getUrl().trim() + ":");

				initContext = new InitialContext(props);

				Object obj = initContext.lookup(DistribuidorMgr.this.JNDIName);
				home = (DistribuidorSessionHome) PortableRemoteObject.narrow(obj, DistribuidorSessionHome.class);

				distribuidorEjb = home.create();
				this.logTH.debug("distribuidor:" + distribuidorEjb.getEnv().toString());

				// nodo = distribuidorEjb.getNodo(1);
				result = distribuidorEjb.asigna(this.idEnvio, this.idPersonaTH);
				// nodo = new NodoVO(1, 1, 1, 1, 1, "iiop://192.168.100.78:2809", "d", "iiop://192.168.100.78:2809", "com.ibm.websphere.naming.WsnInitialContextFactory");
			} catch (Exception e)
			{
				this.logTH.error("ERROR:DistribuidorMgr:getNodo:", e);
			}
			return result;
		}

		/**
		 * nodo distribuidor th
		 * 
		 * @return
		 * @throws RemoteException
		 * @throws DaoException
		 */
		public DistribuidorVO getNodoDistribuidorTH() throws DaoException
		{
			return DistribuidorMgr.this.distribuidorDao.getNodoDistribuidor();
		}

		/**
		 * chache
		 * 
		 */
		public void cache()
		{
			try
			{
				this.logTH.info("CACHE WEB THREAD:");
				SessionFactoryImpl sfi = (SessionFactoryImpl) HibernateUtil.getSession().getSessionFactory();
				String[] lista = sfi.getStatistics().getSecondLevelCacheRegionNames();
				for (int i = 0; i < lista.length; i++)
				{
					this.logTH.debug("region:" + lista[i] + "::");
					EhCache cache = (EhCache) sfi.getSecondLevelCacheRegion(lista[i]);
					if (cache != null)
					{
						this.logTH.debug(lista[i] + " != null");
						this.logTH.debug("" + cache.getElementCountInMemory());
						this.logTH.debug("" + cache.getSizeInMemory());
						Map m = cache.toMap();
						if (m != null)
						{
							this.logTH.debug("map size:" + m.size() + "::");
							for (Iterator it = m.keySet().iterator(); it.hasNext();)
							{
								Object o = it.next();
								this.logTH.debug("BB:" + o + ":" + m.get(o) + ":" + o.getClass().getName() + ":" + (m.get(o)).getClass().getName() + "::");
							}
						} else
							this.logTH.error("\n\nmapa == null?!?!?\n\n");
					} else
						this.logTH.debug(lista[i] + " == null");
				}
			} catch (Exception e)
			{
				this.logTH.error("ERROR en cache WEB:", e);
			}
		}
		

	}
}
