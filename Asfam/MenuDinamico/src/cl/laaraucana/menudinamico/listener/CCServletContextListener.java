package cl.laaraucana.menudinamico.listener;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import cl.araucana.core.registry.UserRegistryImpl;

public class CCServletContextListener implements ServletContextListener 
{
	
	public void contextInitialized(ServletContextEvent event) {
		InitialContext ctx = null;
		DataSource dataSource = null;
		
		try {
			ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/userRegistry");
					
			UserRegistryImpl.initDataSource(dataSource);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
	}
}