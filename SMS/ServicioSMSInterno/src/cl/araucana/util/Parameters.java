package cl.araucana.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Parameters {
	private static final String BUNDLE_NAME = "cl.araucana.util.ws";
	private static ResourceBundle RESOURCE_BUNDLE;

	private Parameters() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			System.out.println("key not found: " + key);
			e.printStackTrace();
			return "";
		}
	}

	static {
		try {
			RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
