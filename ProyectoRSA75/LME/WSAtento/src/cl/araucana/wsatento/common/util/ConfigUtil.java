package cl.araucana.wsatento.common.util;

import java.util.Properties;

public class ConfigUtil {
	public static Properties applicationResources = null;
	private static final String FILE_NAME = "ApplicationResources.properties";
	
	private static void loadProperties(){
		if(applicationResources == null){
    		applicationResources = new Properties();
    		try {
				applicationResources.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_NAME));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getValor(String llave){
		loadProperties();
		if(applicationResources != null){
			String valor = applicationResources.getProperty(llave);
			return (valor != null && valor.equals(""))? null: valor;
		}
		return null;
	}
	
}
