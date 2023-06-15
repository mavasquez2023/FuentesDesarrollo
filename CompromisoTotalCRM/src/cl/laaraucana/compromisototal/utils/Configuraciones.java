package cl.laaraucana.compromisototal.utils;

import java.util.ResourceBundle;

public class Configuraciones {
	
	public static String HOST_SAP = ""; 
	public static String INTERNALORGANIZATION_SAP = ""; 
	
	public static String getConfig(String key) {
		ResourceBundle mainCfg = ResourceBundle.getBundle("mainConfig");
		String ambiente = mainCfg.getString("ambiente");
		ResourceBundle cfg = ResourceBundle.getBundle(ambiente);
		return cfg.getString(key);
	}

	public static String getMainConfig(String key) {
		ResourceBundle mainCfg = ResourceBundle.getBundle("mainConfig");
		return mainCfg.getString(key);
	}
}