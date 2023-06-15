package cl.araucana.ctasfam.listener;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class WebSessionListener implements HttpSessionListener  {
	//private final int MAX_USER=4;
	Logger log = Logger.getLogger(this.getClass());
	
	public void sessionCreated(HttpSessionEvent arg0) {
		ServletContext contexto = arg0.getSession().getServletContext();
		log.info("WebSessionListener:Session creada");
		synchronized (contexto) {
			Integer usuarioConectados = (Integer) contexto.getAttribute("usuariosConectados");
			
			Properties Config = new Properties();
			try{
				Config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("configuracion.properties"));
			}catch(Exception e){}
			
			if (usuarioConectados == null) {
				usuarioConectados = new Integer(0);
			}
			
			usuarioConectados = new Integer(usuarioConectados.intValue()+1);

			contexto.setAttribute("usuariosConectados", usuarioConectados);
			log.info("WebSessionListener:usuarios:"+usuarioConectados);
		
		
			if(usuarioConectados.intValue()>(Integer.valueOf(Config.getProperty("SESIONES")).intValue())){
				//arg0.getSession().invalidate();
				return;
			}
		}
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		log.info("WebSessionListener:Session destruida");
		ServletContext contexto = arg0.getSession().getServletContext();
		synchronized (contexto) {
			Integer usuarioConectados = (Integer) contexto
					.getAttribute("usuariosConectados");
			if (usuarioConectados == null) {
				usuarioConectados = new Integer(0);
			}
			usuarioConectados = new Integer(Math.max(0, usuarioConectados.intValue()-1));
			contexto.setAttribute("usuariosConectados", usuarioConectados);
			log.info("WebSessionListener:usuarios:"+usuarioConectados);
		}
	}
}
