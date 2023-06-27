package cl.araucana.adminCpe.hibernate.utils;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;

import cl.araucana.adminCpe.hibernate.exceptions.InfrastructureException;



/**
  * Basic Hibernate helper class for Hibernate configuration and startup.
  * <p>
  * Uses a static initializer to read startup options and initialize
  * <tt>Configuration</tt> and <tt>SessionFactory</tt>.
  * <p>
  * This class also tries to figure out if JNDI binding of the <tt>SessionFactory</tt>
  * is used, otherwise it falls back to a global static variable (Singleton). If
  * you use this helper class to obtain a <tt>SessionFactory</tt> in your code,
  * you are shielded from these deployment differences.
  * <p>
  * Another advantage of this class is access to the <tt>Configuration</tt> object
  * that was used to build the current <tt>SessionFactory</tt>. You can access
  * mapping metadata programmatically with this API, and even change it and rebuild
  * the <tt>SessionFactory</tt>.
  * <p>
  * If you want to assign a global interceptor, set its fully qualified
  * class name with the system (or hibernate.properties/hibernate.cfg.xml) property
  * <tt>hibernate.util.interceptor_class</tt>. It will be loaded and instantiated
  * on static initialization of HibernateUtil; it has to have a
  * no-argument constructor. You can call <tt>HibernateUtil.getInterceptor()</tt> if
  * you need to provide settings before using the interceptor.
  * <p>
  * Note: This class supports annotations by default, hence needs JDK 5.0
  * and the Hibernate Annotations library on the classpath. Change the single
  * commented line in the source to make it compile and run on older JDKs with
  * XML mapping files only.
  * <p>
  * Note: This class supports only one data store. Support for several
  * <tt>SessionFactory</tt> instances can be easily added (through a static <tt>Map</tt>,
  * for example). You could then lookup a <tt>SessionFactory</tt> by its name.
  *
  * @author christian@hibernate.org
  */
  	public class HibernateUtil 
  	{
  		private static Logger log = Logger.getLogger(HibernateUtil.class);

  		private static String archivoConfig = "cl/araucana/adminCpe/hibernate/beans/hibernate.cfg.xml";

  		//reemplazar:
  			//configuration por ((Configuration)configMap.get(key))
  			//sessionFactory por ((SessionFactory)sessFactMap.get(key))
  			//threadSession por ((ThreadLocal)thSessionMap.get(key))
  			//threadTransaction por ((ThreadLocal)thTxMap.get(key))
  			//threadInterceptor por ((ThreadLocal)thIntercep.get(key))
  		private static HashMap sessFactMap = new HashMap();
  		private static HashMap configMap = new HashMap();
  		private static HashMap thSessionMap = new HashMap();
  		private static HashMap thIntercep = new HashMap();
  		private static HashMap thUser = new HashMap();
  		private static HashMap configsMaps = new HashMap();

  		static 
  		{
  			configsMaps.put("SPL", "cl/araucana/adminCpe/hibernate/beans/SPLhibernate.cfg.xml");
  		}

  		public static void configura(String key)
  		{
			log.info("\n\nconfigurando:" + key + "::");
  			try
  			{
  				if (key != null && !key.equals(""))
  					archivoConfig = (String)configsMaps.get(key);
  				Configuration configuration = new Configuration();
  				if (key != null && key.equals(""))
  					configuration.setInterceptor(new MiscInterceptor());
  				SessionFactory sessionFactory = configuration.configure(archivoConfig).buildSessionFactory();

  				configMap.put(key, configuration);
  				sessFactMap.put(key, sessionFactory);
  				thSessionMap.put(key, new ThreadLocal());
  				thIntercep.put(key, new ThreadLocal());
  				thUser.put(key, new ThreadLocal());
  			} catch (Throwable ex)
  			{
  				log.error("Building SessionFactory failed.", ex);
  				throw new ExceptionInInitializerError(ex);
  			}
  		}

  		public static SessionFactory getSessionFactory()
  		{
  			return getSessionFactory("");
  		}

  		public static SessionFactory getSessionFactory(String key)
  		{
  			return (SessionFactory)sessFactMap.get(key);
  		}

  		public static Configuration getConfiguration()
  		{
  			return (Configuration)configMap.get("");
  		}

  		public static Configuration getConfiguration(String key)
  		{
  			return (Configuration)configMap.get(key);
  		}

  		public static Session getSession() throws InfrastructureException
  		{
  			return getSession("");
  		}

  		public static Session getSession(String key) throws InfrastructureException
  		{
  			if (sessFactMap.get(key) == null)
  				configura(key);
  			ThreadLocal th = ((ThreadLocal)thSessionMap.get(key));
  			Session s = (Session) th.get();
  			try
  			{
  				if (s == null)
  				{
  					log.debug("Opening new Session for this thread.");
  					if (getInterceptor(key) != null)
  					{
  						log.debug("Using interceptor: " + getInterceptor(key).getClass());
  						s = getSessionFactory(key).openSession(getInterceptor(key));
  					} else
  						s = getSessionFactory(key).openSession();
  					th.set(s);
  				}

  			} catch (HibernateException ex)
  			{
  				throw new InfrastructureException(ex);
  			}
  			return s;
  		}

  		public static Session getSession(String key, String idUser) throws InfrastructureException
  		{
  			if (sessFactMap.get(key) == null)
  				configura(key);
  			ThreadLocal th = ((ThreadLocal)thSessionMap.get(key));
  			Session s = (Session) th.get();
  			try
  			{
  				if (s == null)
  				{
  					log.debug("Opening new Session for this thread.");
  					if (getInterceptor(key) != null)
  					{
  						log.debug("Using interceptor: " + getInterceptor(key).getClass());
  						s = getSessionFactory(key).openSession(getInterceptor(key));
  					} else
  						s = getSessionFactory(key).openSession();
  					th.set(s);
  				}
  			} catch (HibernateException ex)
  			{
  				throw new InfrastructureException(ex);
  			}

  			ThreadLocal tUser = ((ThreadLocal)thUser.get(key));
  			String user = (String) tUser.get();
  			if (user == null)
  				tUser.set(idUser);
  			return s;
  		}

  		public static StatelessSession getStatelessSession() throws InfrastructureException
  		{
  			try
  			{
  				Configuration configuration = new Configuration();
  				SessionFactory sessionFactory = configuration.configure(archivoConfig).buildSessionFactory();
  				return sessionFactory.openStatelessSession();
  			} catch (HibernateException ex)
  			{
  				throw new InfrastructureException(ex);
  			}
  		}

  		public static void setIdUser(String idUser) throws InfrastructureException
  		{
  			setIdUser("", idUser);
  		}

  		public static void setIdUser(String key, String idUser) throws InfrastructureException
  		{
  			ThreadLocal tUser = ((ThreadLocal)thUser.get(key));
  			tUser.set(idUser);
  		}

  		public static String getIdUser()
  		{
  			return getIdUser("");
  		}

  		public static String getIdUser(String key)
  		{
  			ThreadLocal tUser = ((ThreadLocal)thUser.get(key));
  			String user = (String) tUser.get();
  			return user;
  		}

  		public static void closeSession() throws InfrastructureException
  		{
  			closeSession("");
  		}

  		public static void closeSession(String key) throws InfrastructureException
  		{
  			try
  			{
  				ThreadLocal th = ((ThreadLocal)thSessionMap.get(key));
  				Session s = (Session) th.get();
  				th.set(null);
  				if (s != null && s.isOpen())
  				{
  					log.debug("Closing Session of this thread.");
  					s.close();
  				}
  			} catch (HibernateException ex)
  			{
  				throw new InfrastructureException(ex);
  			}
  		}

  		private static Interceptor getInterceptor(String key)
  		{
  			Interceptor interceptor = (Interceptor) ((ThreadLocal)thIntercep.get(key)).get();
  			return interceptor;
  		}

  }
