package cl.araucana.ctasfam.batch.common.util;


import java.util.Properties;

public class ConfiguracionUtil {
	private static Properties configuracionInterna = null;
	private static Properties configuracionBaseDatos = null;
	
	public static final String FILE_INTERNAL_APP_CONFIG = "InternalAppConfig.properties";
	
	public static void setConfiguracionInterna(Properties config){
		configuracionInterna = config;
	}
	
	public static void setConfiguracionBaseDatos(Properties config){
		configuracionBaseDatos = config;
	}

	public static String getValor(String llave){
		if(configuracionBaseDatos != null){
			String valor = configuracionBaseDatos.getProperty(llave);
			if(valor!=null && !valor.equals(""))
				return valor;
		}
		
		if(configuracionInterna != null){
			String valor = configuracionInterna.getProperty(llave);
			if(valor!=null && !valor.equals(""))
				return valor;
		}

		return null;
	}
}

