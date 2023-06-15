package cl.liv.export.comun.util;

import cl.liv.comun.utiles.PropertiesUtil;

public class PropertiesComunUtil {

	
	public static String getProperty(String key){
		return PropertiesUtil.propertiesComun.getString(key);
	} 
	
}
