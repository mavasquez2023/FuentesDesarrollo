package com.microsystem.lme.util;

import java.util.ResourceBundle;

public class Configuraciones {
	private static ResourceBundle mainCfg = ResourceBundle.getBundle("mainConfig");
	private static ResourceBundle config = ResourceBundle.getBundle(mainCfg.getString("ambiente"));
	
	public static String getConfig(String key) {
		/*ResourceBundle mainCfg = ResourceBundle.getBundle("mainConfig");
		String ambiente = mainCfg.getString("ambiente");
		ResourceBundle cfg = ResourceBundle.getBundle(ambiente);
		return cfg.getString(key);*/
		if(config == null){
			mainCfg = ResourceBundle.getBundle("mainConfig");
			config = ResourceBundle.getBundle(mainCfg.getString("ambiente"));
		}
		return config.getString(key);
	}

	public static String getMainConfig(String key) {
		/*ResourceBundle mainCfg = ResourceBundle.getBundle("mainConfig");
		return mainCfg.getString(key);*/
		if(mainCfg == null){
			mainCfg = ResourceBundle.getBundle("mainConfig");
		}
		return mainCfg.getString(key);
	}
}