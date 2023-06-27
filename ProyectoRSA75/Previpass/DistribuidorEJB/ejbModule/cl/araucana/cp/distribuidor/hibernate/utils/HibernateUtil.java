package cl.araucana.cp.distribuidor.hibernate.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import cl.araucana.cp.distribuidor.hibernate.exceptions.InfrastructureException;

/**
 * Basic Hibernate helper class for Hibernate configuration and startup.
 * <p>
 * Uses a static initializer to read startup options and initialize <tt>Configuration</tt> and <tt>SessionFactory</tt>.
 * <p>
 * This class also tries to figure out if JNDI binding of the <tt>SessionFactory</tt> is used, otherwise it falls back to a global static variable (Singleton). If you use this helper class to obtain
 * a <tt>SessionFactory</tt> in your code, you are shielded from these deployment differences.
 * <p>
 * Another advantage of this class is access to the <tt>Configuration</tt> object that was used to build the current <tt>SessionFactory</tt>. You can access mapping metadata programmatically with
 * this API, and even change it and rebuild the <tt>SessionFactory</tt>.
 * <p>
 * If you want to assign a global interceptor, set its fully qualified class name with the system (or hibernate.properties/hibernate.cfg.xml) property <tt>hibernate.util.interceptor_class</tt>. It
 * will be loaded and instantiated on static initialization of HibernateUtil; it has to have a no-argument constructor. You can call <tt>HibernateUtil.getInterceptor()</tt> if you need to provide
 * settings before using the interceptor.
 * <p>
 * Note: This class supports annotations by default, hence needs JDK 5.0 and the Hibernate Annotations library on the classpath. Change the single commented line in the source to make it compile and
 * run on older JDKs with XML mapping files only.
 * <p>
 * Note: This class supports only one data store. Support for several <tt>SessionFactory</tt> instances can be easily added (through a static <tt>Map</tt>, for example). You could then lookup a
 * <tt>SessionFactory</tt> by its name.
 * 
 * @author christian@hibernate.org
 */
public class HibernateUtil
{
	private static Log log = LogFactory.getLog(HibernateUtil.class);

	private static Configuration configuration;
	private static SessionFactory sessionFactory;
	private static final ThreadLocal threadSession = new ThreadLocal();
	private static final ThreadLocal thUser = new ThreadLocal();
	private static final ThreadLocal threadInterceptor = new ThreadLocal();

	// Create the initial SessionFactory from the default configuration files
	static
	{
		try
		{
			configuration = new Configuration();
			sessionFactory = configuration.configure("cl/araucana/cp/distribuidor/hibernate/beans/hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex)
		{
			log.error("Building SessionFactory failed.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Returns the SessionFactory used for this static class.
	 * 
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	/**
	 * Returns the original Hibernate configuration.
	 * 
	 * @return Configuration
	 */
	public static Configuration getConfiguration()
	{
		return configuration;
	}

	/**
	 * Retrieves the current Session local to the thread. <p/> If no Session is open, opens a new Session for the running thread.
	 * 
	 * @return Session
	 */
	public static Session getSession() throws InfrastructureException
	{
		Session s = (Session) threadSession.get();
		try
		{
			if (s == null || !s.isConnected())
			{
				log.debug("Opening new Session for this thread.");
				Interceptor interceptor = getInterceptor();
				if (interceptor == null) 
				{
					log.debug("Creating new interceptor for this thread");
					interceptor = new MiscInterceptor();
					registerInterceptor(interceptor);
				}
				s = getSessionFactory().openSession(interceptor);
				threadSession.set(s);
			}
		} catch (HibernateException ex)
		{
			throw new InfrastructureException(ex);
		}
		return s;
	}

	public static void setIdUser(String idUser)
	{
		thUser.set(idUser);
	}

	public static String getIdUser()
	{
		String user = (String) thUser.get();
		return user;
	}

	/**
	 * Closes the Session local to the thread.
	 */
	public static void closeSession() throws InfrastructureException
	{
		try
		{
			Session s = (Session) threadSession.get();
			threadSession.set(null);
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

	/**
	 * Reconnects a Hibernate Session to the current Thread.
	 * 
	 * @param session
	 *            The Hibernate Session to be reconnected.
	 */
	/*
	 * public static void reconnect(Session session) throws InfrastructureException { try { session.reconnect(); threadSession.set(session); } catch (HibernateException ex) { throw new
	 * InfrastructureException(ex); } }
	 */

	/**
	 * Disconnect and return Session from current Thread.
	 * 
	 * @return Session the disconnected Session
	 */
	public static Session disconnectSession() throws InfrastructureException
	{

		Session session = getSession();
		try
		{
			threadSession.set(null);
			if (session.isConnected() && session.isOpen())
				session.disconnect();
		} catch (HibernateException ex)
		{
			throw new InfrastructureException(ex);
		}
		return session;
	}

	/**
	 * Register a Hibernate interceptor with the current thread.
	 * <p>
	 * Every Session opened is opened with this interceptor after registration. Has no effect if the current Session of the thread is already open, effective on next close()/getSession().
	 */
	public static void registerInterceptor(Interceptor interceptor)
	{
		threadInterceptor.set(interceptor);
	}

	private static Interceptor getInterceptor()
	{
		Interceptor interceptor = (Interceptor) threadInterceptor.get();
		return interceptor;
	}

	public static void disableInterceptor()
	{
		Interceptor inter = getInterceptor();
		if (inter != null && inter instanceof MiscInterceptor)
			((MiscInterceptor) inter).disable();
	}

	public static void enableInterceptor()
	{
		Interceptor inter = getInterceptor();
		if (inter != null && inter instanceof MiscInterceptor)
			((MiscInterceptor) inter).disable();
	}
}
