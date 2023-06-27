package cl.araucana.wslme.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigUtil {
	public static Properties recursosDeAplicacion = null;
	public static Properties configuracionesDeNegocio = null;

	
	
	public static String PATH_RECURSOS_DE_APLICACION = "RecursosDeAplicacion.properties";
	public static String PATH_CONFIGURACIONES_DE_NEGOCIO = "c:\\wslme\\ConfiguracionesDeNegocio.properties";
	public static String PATH_OPERADORES_REGISTRADOS = "c:\\wslme\\UsuariosRegistrados.properties";
	
	private static Logger log = Logger.getLogger(ConfigUtil.class);
	
	public static String getValorRecursosDeAplicacion(String llave){
		log.debug("Obteniendo el valor de la llave [" + llave + "] del archivo de propiedades interno [" + PATH_RECURSOS_DE_APLICACION + "]");
		if(recursosDeAplicacion == null){
			recursosDeAplicacion = new Properties();
    		try {
    			recursosDeAplicacion.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PATH_RECURSOS_DE_APLICACION));
			} catch (Exception e) {
				log.error("Codigo 0011: No se puede cargar el archivo de propiedades interno [" + PATH_RECURSOS_DE_APLICACION + "]");
				e.printStackTrace();
			}
		}
		
		return getValor(recursosDeAplicacion, llave);
	}
	
	public static String getValorConfiguracionesDeNegocio(String llave){
		log.debug("Obteniendo el valor de la llave [" + llave + "] del archivo de propiedades interno [" + PATH_CONFIGURACIONES_DE_NEGOCIO + "]");
		if(configuracionesDeNegocio == null){
			configuracionesDeNegocio = new Properties();
    		try {
    			configuracionesDeNegocio.load(new FileInputStream(new File(PATH_CONFIGURACIONES_DE_NEGOCIO)));
			} catch (Exception e) {
				log.error("Codigo 0012: No se puede cargar el archivo de propiedades externo [" + PATH_CONFIGURACIONES_DE_NEGOCIO + "]");
				e.printStackTrace();
			}
		}
		
		return getValor(configuracionesDeNegocio, llave);
	}
	
	public static String getValor(Properties fileProperties, String llave){
		if(fileProperties != null){
			String valor = fileProperties.getProperty(llave);
			return (valor != null && valor.equals(""))? null: valor;
		}
		return null;
	}
}
