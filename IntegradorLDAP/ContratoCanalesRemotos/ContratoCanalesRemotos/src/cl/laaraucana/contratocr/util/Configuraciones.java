package cl.laaraucana.contratocr.util;

import java.util.ResourceBundle;
import org.apache.log4j.Logger;
/*import cl.araucana.common.env.AppConfig;
import com.schema.util.FileSettings;*/

public class Configuraciones {
	protected Logger logger = Logger.getLogger(this.getClass());
	public static String DISPONIBLE= "01";
	public static String APROBADO= "02";
	public static String RECHAZADO= "03";
	public static String CADUCADO= "04";
	public static String MAX_INTENTOS= "05";

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