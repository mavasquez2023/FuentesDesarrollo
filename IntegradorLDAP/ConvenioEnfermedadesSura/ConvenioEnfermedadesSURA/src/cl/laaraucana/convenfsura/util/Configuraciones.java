package cl.laaraucana.convenfsura.util;

import java.util.ResourceBundle;
import org.apache.log4j.Logger;
/*import cl.araucana.common.env.AppConfig;
import com.schema.util.FileSettings;*/

public class Configuraciones {
	protected Logger logger = Logger.getLogger(this.getClass());

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