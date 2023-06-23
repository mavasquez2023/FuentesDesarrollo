package cl.laaraucana.capaservicios.util;

import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public class Configuraciones {
	protected Logger logger = Logger.getLogger(this.getClass());
	private static ResourceBundle mainCfg = ResourceBundle.getBundle("mainConfig");
	private static ResourceBundle cfg = ResourceBundle.getBundle(mainCfg.getString("ambiente"));

	public static String getConfig(String key) {
		return cfg.getString(key);
	}

	public static String getMainConfig(String key) {
		return mainCfg.getString(key);
	}
}