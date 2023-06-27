

package cl.araucana.ea.servlet.event.listeners;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.xml.sax.helpers.DefaultHandler;

import cl.araucana.common.*;
import cl.araucana.core.util.ApplicationBean;


public final class ContextListener implements ServletContextListener {

	private String name;
	
	/*
	 * 		Bind application configuration info to the initial servletContext.
	 * 
	 * 		Search order for "EA.ini"
	 * 		1. declaraed as system properties with -D option. e.g -DEAConfigFile=/tmp/EA.ini
	 * 		2. web application servletContext parameter
	 * 		3. under classpath directory
	 * 		4. under the /WEB-INF directory. must be exist.
	 * 
	 * 		Read the definitions of conceptos (conceptos.xml) & servicios (servicios.xml)
	 * 		1. use file locations if they are defined in EA.ini
	 * 		2. by default, use files located in /WEB-INF
	 */
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		
		String organizationName =
				servletContext.getInitParameter("organizationName");
				
		String name = servletContext.getInitParameter("name");
		String title = servletContext.getInitParameter("title");
		String version = servletContext.getInitParameter("version");
		String releaseDate = servletContext.getInitParameter("releaseDate");
		String copyright = servletContext.getInitParameter("copyright");

		this.name = name;
				
		ApplicationBean applicationBean = new ApplicationBean();
		
		applicationBean.setOrganizationName(organizationName);
		applicationBean.setName(name);
		applicationBean.setTitle(title);
		applicationBean.setVersion(version);
		applicationBean.setReleaseDate(releaseDate);
		applicationBean.setCopyright(copyright);
		
		servletContext.setAttribute("software", applicationBean);
		
		// some examples.
		// InputStream is = this.getClass().getResourceAsStream("/config/resource/service_def.xml");
		// InputStream is = t.getClass().getClassLoader().getResourceAsStream("config/resource/service_def.xml");
		// InputStream is = servletContext.getResourceAsStream("/META-INF/service_def.xml");
		// InputStream isC = servletContext.getResourceAsStream("/WEB-INF/concepto.xml");

		log("Iniciando aplicación ...");

		log("Application:");
		log("    organization name = " + organizationName);
		log("    title = " + title);
		log("    version = " + version);
		log("    release date = " + releaseDate);
		log("    copyright = " + copyright);
		log("");
		
		int status = 0;
		Properties properties = new Properties();
		InitialContext initCtx = null;

		try {
			initCtx = new InitialContext();
		} catch (NamingException e) {
			status = -1;
			
			log("Error crítico. No se pudo obtener contexto inicial de JNDI.");			
			e.printStackTrace(System.err);
		}

		InputStream is = null;
		
		log("Cargando configuración ...");
					
		try {
			log("Buscando la variable EA_INI en properties del sistema ...");
			
			is = new FileInputStream(System.getProperty("EA_INI"));
			status = 1;			
		} catch (Exception e) {
			log("No se encuentra en properties del sistema.");
		}

		if(status == 0) {				
			try {
				log("Buscando la variable EA_INI en contexto de aplicación ...");
				
				is = new FileInputStream(
						servletContext.getInitParameter("EA_INI"));
						
				status = 2;			
			} catch (Exception e) {
				log("No se encuentra en contexto de aplicación.");
			}
		}

		if(status == 0) {				
			try {
				log("Buscando el archivo \"ea.ini\" en el classpath ...");
				
				is = new FileInputStream("ea.ini");
				status = 3;			
			} catch (Exception e) {
				log("No se encuentra en el classpath.");
			}
		}

		if((status != -1) && (status == 0)) {
			try {
				log("Buscando el archivo \"ea.ini\" en /WEB-INF ...");
				
				is = servletContext.getResourceAsStream("/WEB-INF/ea.ini");	
				status = 4;
				
				log("\"ea.ini\" encontrado en (" + status + ") intento.");
						
				properties.load(is);			
				properties.list(System.out);
				is.close();
				initCtx.rebind("INIT_CONFIG", properties);
				
				Properties p = (Properties) initCtx.lookup("INIT_CONFIG");
				
				log("INIT_CONFIG = " +  p);
			} catch (Exception e) {
				status = -1;
				e.printStackTrace(System.err);
			}
		}

		if(status == -1) {
			log("Error crítico. No se pudo cargar archivo \"ea.ini\"");				
		} else {
			log("Información catalogada en initial servletContext (INIT_CONFIG).");
		}
		
		log("Cargando \"conceptos.xml\" ...");			
		log("Catalogando conceptos en initial servletContext (CONCEPTOS) ...");
		
		try {
			if(properties.containsKey("CONCEPTOS.XML")) {
				log("Buscando la variable \"CONCEPTOS.XML\" en contexto de aplicación ...");
					
				is = servletContext.getResourceAsStream(properties.getProperty("CONCEPTOS.XML"));	
			} else {
				log("No se encuentra \"CONCEPTOS.XML\" en contexto de aplicación.");	
				log("Cargando \"conceptos.xml\" desde /WEB-INF ...");
					
				is = servletContext.getResourceAsStream("/WEB-INF/conceptos.xml");	
			}
			
			DefaultHandler handler = new ConceptoFileHandler();
			XMLConfigFileReader reader = new XMLConfigFileReader(null, handler);
			
			reader.read(is);
			
			Map conceptos = reader.getConfigInfo();
			
			initCtx.rebind("CONCEPTOS", conceptos);
			log(conceptos.size() + " conceptos son cargados.");			
		} catch (Exception e) {
			status = -1;
			
			log("Ha fallado la carga de conceptos.");	
			e.printStackTrace(System.err);			
		}
		
		log("Cargando \"servicios.xml\" ...");			
		log("Catalogando servicios a initial servletContext (SERVICIOS) ...");
		
		try {
			log("Cargando archivo \"SERVICIOS.XML\" en contexto de aplicación.");	
		
			if(properties.containsKey("SERVICIOS.XML")) {	
				is = servletContext.getResourceAsStream(properties.getProperty("SERVICIOS.XML"));	
			} else {
				log("No se encuentra \"SERVICIOS.XML\" en contexto de aplicación.");	
				log("Buscando \"servicios.xml\" desde /WEB-INF ...");
					
				is = servletContext.getResourceAsStream("/WEB-INF/servicios.xml");	
			}
			
			DefaultHandler handler = new ServiceFileHandler();
			XMLConfigFileReader reader = new XMLConfigFileReader(null, handler);
			reader.read(is);
			Map servicios = reader.getConfigInfo();
			
			initCtx.rebind("SERVICIOS", servicios);
			log(servicios.size() + " servicios son cargados.");			
		} catch (Exception e) {
			status = -1;
			
			log("Ha fallado la carga de servicios.");	
			e.printStackTrace(System.err);			
		}

		servletContext.setAttribute("INIT_CONFIG_STATUS", new Integer(1));
		
		log("Aplicación iniciada.");
	}

	public void contextDestroyed(ServletContextEvent event) {
	}
	
	private void log(String message) {
		System.out.println(name + ": " + message);
	}
}
