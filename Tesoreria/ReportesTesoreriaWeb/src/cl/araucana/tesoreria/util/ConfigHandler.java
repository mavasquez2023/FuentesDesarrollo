package cl.araucana.tesoreria.util;

import java.util.ResourceBundle;

public class ConfigHandler {

	private static final ResourceBundle main = ResourceBundle.getBundle("reportes_tesoreria");

	public static String getConfig(String key) {
		return main.getString(key);
	}
}