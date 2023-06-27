package cl.araucana.cp.jcrontab;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletConfig;

import org.apache.log4j.Logger;
import org.jcrontab.Crontab;
import org.jcrontab.web.loadCrontabServlet;
/*
* @(#) Initjcrontab.java 1.4 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cllanos
 * @author cchamblas
 * 
 * @version 1.4
 */
public class InitJcrontab extends loadCrontabServlet 
{
	private static final long serialVersionUID = 2005542442272647444L;
	private static Logger log = Logger.getLogger(InitJcrontab.class);
	private Crontab crontab = null;

	/** 
	 * This method  starts the Crontab and lets the system
	 * Continue without wasting more resources.
	 * This method can receive the config File as a variable in web.xml
	 */
	public void process() 
	{
		String propz = "jcrontab.properties";

		String props = getServletConfig().getInitParameter("props");
		String path = getServletConfig().getServletContext().getRealPath(props);

		if (props == null) 
			props = propz;
		// Load the servlet config parameters and override the properties
		Properties propObj = new Properties();
		try 
		{
			InputStream input = createPropertiesStream(path);
			propObj.load(input);
		} catch (IOException ioe) 
		{
			log.error("problemas en proceso contrab:", ioe);
		}
		ServletConfig c = getServletConfig();
		Enumeration keys = c.getInitParameterNames();
		while (keys.hasMoreElements()) 
		{
			String key = (String) keys.nextElement();
			propObj.setProperty(key, c.getInitParameter(key));
		}

		props = getServletConfig().getInitParameter("log4j");
		path = getServletConfig().getServletContext().getRealPath(props);
		propObj.setProperty("org.jcrontab.log.log4J.Properties", path);

		props = getServletConfig().getInitParameter("crontab");
		path = getServletConfig().getServletContext().getRealPath(props);
		propObj.setProperty("org.jcrontab.data.file", path);

		this.crontab = Crontab.getInstance();

		try 
		{
			ShutdownHook();
			this.crontab.init(propObj);
		} catch (Exception e) 
		{
			log.error("problemas en proceso contrab:", e);
		}
	}

	public void doStop() 
	{
		log.info("Shutting down...");
		this.crontab.uninit(1);
		log.info("Stopped");
	}
}
