/**
 * 
 */
package cl.araucana.tgr.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

/**
 * @author usist199
 *
 */
public class Log4jInit extends HttpServlet {

	 public
	  void init() {
		 System.out.println("Inicializando Log4j para WSTGR");
	    String prefix =  getServletContext().getRealPath("/");
	    String file = getInitParameter("log4j-init-file");
	    // if the log4j-init-file is not set, then no point in trying
	    if(file != null) {
	      PropertyConfigurator.configure(prefix+"/"+file);
	    }
	  }

	  public
	  void doGet(HttpServletRequest req, HttpServletResponse res) {
	  }

}
