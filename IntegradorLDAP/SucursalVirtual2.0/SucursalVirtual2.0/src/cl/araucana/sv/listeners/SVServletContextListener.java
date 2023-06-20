package cl.araucana.sv.listeners;

import cl.araucana.core.registry.UserRegistryImpl;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;






public class SVServletContextListener
  implements ServletContextListener
{
  public void contextInitialized(ServletContextEvent event) {
    InitialContext ctx = null;
    DataSource dataSource = null;
    
    try {
      ctx = new InitialContext();
      
      dataSource = 
        (DataSource)ctx.lookup("java:comp/env/jdbc/userRegistry");
      
      UserRegistryImpl.initDataSource(dataSource);
      
      log("Using 'jdbc/userRegistry' data source.");
    } catch (NamingException e) {
      log("'jdbc/userRegistry' data source not registered in JNDI.");
      log("Internal user registry data source will be used.");
    } 
  }

  
  public void contextDestroyed(ServletContextEvent event) {}
  
  private void log(String message) {
    System.out.println("SV: " + message);
  }
}