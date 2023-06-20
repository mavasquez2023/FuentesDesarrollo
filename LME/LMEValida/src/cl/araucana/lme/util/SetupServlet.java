/*
 * Created on 20-10-2011
 *
 */
package cl.araucana.lme.util;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

/**
 * @author j-factory
 *
 */
public class SetupServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -114342606166116084L;

	/**
	* Metodo que inicializa Servlet
	* @param config Configurador del Servlet
	* @throws ServletException
	* @since 1.0
	*/
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// Lee el directorio donde va a ser colocado el archivo de logs
		String directory = getInitParameter("log-directory");

		// Adiciona el parametro del directorio como un Property del sistema
		// para que pueda ser utilizado dentro del archivo de configuraci�n del Log4J
		System.setProperty("log.directory", directory);

		// Extrae el path donde se encuentra el contexto
		// Asume que el archivo de configuraci�n se encuentra en este directorio
		String prefix = getServletContext().getRealPath("/");//+"\\";

		// Lee el nombre del archivo de configuraci�n de Log4J
		String file = getInitParameter("log4j-init-file");

		if (file == null || file.length() == 0 || !(new File(prefix + file)).isFile()) {
			System.err.println("ERROR: No puede leer el archivo de configuracion. " + prefix + file);
			throw new ServletException();
		}

		// Revisa otra par�metro de configuraci�n que le indica
		// si debe revisar el archivo de log por cambios.
		String watch = config.getInitParameter("watch");

		// Extrae el par�metro que le indica cada que tiempo debe revisar el archivo de configuraci�n
		String timeWatch = config.getInitParameter("time-watch");

		// Revisa como debe realizar la configuraci�n de Log4J y llama al m�todo adecuado
		if (watch != null && watch.equalsIgnoreCase("true")) {
			if (timeWatch != null) {
				PropertyConfigurator.configureAndWatch(prefix + file, Long.parseLong(timeWatch));
			} else {
				PropertyConfigurator.configureAndWatch(prefix + file);
			}
		} else {
			PropertyConfigurator.configure(prefix + file);
		}

	}

	/**
	* Metodo que destruye Servlet.
	* @since 1.0
	*/
	public void destroy() {
		super.destroy();
	}
}
