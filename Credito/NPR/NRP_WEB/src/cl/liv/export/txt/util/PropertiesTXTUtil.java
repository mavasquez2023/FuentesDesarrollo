package cl.liv.export.txt.util;

import cl.liv.comun.utiles.PropertiesUtil;

public class PropertiesTXTUtil {

	
	public static String getProperty(String key){
		return PropertiesUtil.propertiesTXT.getString(key);
	} 
	
}
