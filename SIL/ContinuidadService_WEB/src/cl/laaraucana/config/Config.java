package cl.laaraucana.config;

import java.util.ResourceBundle;

public class Config {

	private static final ResourceBundle mainCfg = ResourceBundle.getBundle("mainConfig");
	private static final String ambiente = mainCfg.getString("ambiente");
	private static final ResourceBundle config = ResourceBundle.getBundle(ambiente);

	public static String getConfig(String key) {
		return config.getString(key);
	}

	public static String getMainConfig(String key) {
		return mainCfg.getString(key);
	}
}