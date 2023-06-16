package cl.araucana.ctasfam.resources.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ApplicationPropertiesSingleton {

	private static final Log log = LogFactory
			.getLog(ApplicationPropertiesSingleton.class);

	private Properties prop = null;

	private String pathname;

	// instancia para controlar singleton
	private static ApplicationPropertiesSingleton instance = null;

	public static ApplicationPropertiesSingleton getInstance(String pathname) {
		if (instance == null) {
			instance = new ApplicationPropertiesSingleton(pathname);
		} else {
			if (hasChangedPath(pathname)) {
				instance = null;
				instance = new ApplicationPropertiesSingleton(pathname);
			}
		}
		return instance;
	}

	public void setPathname(String pathname) {
		this.pathname = pathname;
	}

	public String getProperty(String propertykey) {
		return prop.getProperty(propertykey);
	}

	public Properties getProperties() {
		return prop;
	}

	/*------- CONSTRUCTORES Y METODOS PROTEGIDOS, PRIVADOS -------*/
	protected ApplicationPropertiesSingleton(String pathname) {
		if (StringUtils.isEmpty(pathname))
			throw new IllegalArgumentException(
					"Parametro nombre path archivo no puede ser nulo o vacio");
		this.pathname = pathname;
		this.prop = new Properties();
		readProperties();
	}

	private ApplicationPropertiesSingleton() {
	}

	private void readProperties() {
		File file = new File(pathname);
		try {
			prop.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			log.error("Archivo " + file.getAbsolutePath()
					+ " no encontrado, motivo: " + e.getLocalizedMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			log.error("Error leer archivo " + file.getAbsolutePath()
					+ ", motivo: " + e.getLocalizedMessage(), e);
		}
	}

	private static boolean hasChangedPath(String pathname) {
		if (StringUtils.isNotEmpty(pathname)) {
			int eval = pathname.compareToIgnoreCase(instance.pathname);
			if (eval != 0) {
				return true;
			}
		}
		return false;
	}
}