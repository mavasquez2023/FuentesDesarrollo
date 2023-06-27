package cl.araucana.adminCpe.presentation.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import cl.araucana.adminCpe.hibernate.utils.HibernateUtil;
import cl.araucana.adminCpe.logger.InitAuditLogger;
import cl.araucana.adminCpe.presentation.mgr.ParametroMgr;
import cl.araucana.cp.distribuidor.base.Constants;
/*
* @(#) ServletConfiguration.java 1.5 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author ccostagliola
 * @author cchamblas
 * 
 * @version 1.5
 */
public class ServletConfiguration extends HttpServlet 
{
	static final long serialVersionUID = 5985030137399567905L;
	private static final Logger logger = Logger.getLogger(ServletConfiguration.class);
	
	/**
	 * 
	 */
	public void init() throws ServletException
	{
		super.init();

		try
		{
			logger.debug("Cargar textos botones");
			cargaTextoBotones();
			logger.debug("textos botones cargados");
		} catch (IOException ioe)
		{
			logger.error("error cargando properties botones:", ioe);
			throw new ServletException("Problemas cargando textos botones! no se puede levantar la aplicacion, revisar configuracion: /files/textoBotones.properties");
		}

		ParametroMgr parametroMgr = new ParametroMgr(HibernateUtil.getSession());
		parametroMgr.cargaConstantes();
		InitAuditLogger.process();
	}

	/**
	 * carga texto botones
	 * @throws IOException
	 */
	private void cargaTextoBotones() throws IOException
	{
		Constants.TXT_BTNS.load(getClass().getResourceAsStream("/files/textoBotones.properties"));
	}
}