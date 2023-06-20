package cl.laaraucana.silmsil.util;

import java.util.ResourceBundle;

public class Configuraciones {

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
	
	public String getAtributoFormulario_LM(String key){
		ResourceBundle mainCfg = ResourceBundle.getBundle("formulario_LM");
		return mainCfg.getString(key);		
	}

	public String getAtributoFormulario_SIL(String key){
		ResourceBundle mainCfg = ResourceBundle.getBundle("formulario_SIL");
		return mainCfg.getString(key);		
	}
	
	public static String getAtributoPaginacion(String key){
		ResourceBundle mainCfg = ResourceBundle.getBundle("paginacion");
		return mainCfg.getString(key);		
	}
}