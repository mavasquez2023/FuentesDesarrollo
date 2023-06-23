package cl.araucana.spl.util;

import java.util.ResourceBundle;

public class ResourceHelper {
	private static ResourceHelper rh = null;
	static ResourceBundle resources=ResourceBundle.getBundle("cl.araucana.spl.resources.ApplicationResources");
	
	private ResourceHelper() {
		super();
	}
    
	static public ResourceHelper getInstance() {
		if (rh == null) {
			rh = new ResourceHelper();
		}
		return rh;
	}
	
	public String getProperty(String key) {
		return resources.getString(key);
	}	
	
}
