/*
 * Created on 10-12-2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package cl.araucana.cierrecpe.business;

import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.*;

import cl.araucana.cierrecpe.dao.CPDAO;
import cl.araucana.cierrecpe.dao.ParametrosCPDAO;
import cl.araucana.cierrecpe.business.Parametros;
import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.araucana.core.util.logging.LogManager;

/**
 * @author Usist24
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MyServletContextListener implements ServletContextListener {
	//variable para solicitar instancia de singleton Parametros
	private Parametros param;
	private CPDAO cpDAO=null;
	private ParametrosCPTO paramTO=null;
	private static Logger logger = LogManager.getLogger();
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc= event.getServletContext();
		try {
			cpDAO= new CPDAO();
			ParametrosCPDAO paramDAO= new ParametrosCPDAO(cpDAO.getConnection());
			paramTO= (ParametrosCPTO) paramDAO.select(null);
			logger.config("Param periodo:" + paramTO.getPeriodoSistema());
			logger.config("Param fecha cierre:" + paramTO.getFechaCierre());
		} catch (SQLException e) {
			logger.severe("SQLException, mensaje:" + e.getMessage());
			e.printStackTrace();
		}
		finally{
			if (cpDAO!= null){
				cpDAO.closeConnectionDAO();
			}
			//Se carga los parámetros
	    	param= Parametros.getInstance();
	    	param.setParam(paramTO);
	    	//sc.setAttribute("parametros" , param.getParam());
		}
		
    	
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub

	}

}
