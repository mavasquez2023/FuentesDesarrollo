package cl.liv.export.xls.util;

import liv.comun.utiles.PropertiesUtil;

public class PropertiesXLSUtil {

	
	public static String getProperty(String key){
		return PropertiesUtil.propertiesXLS.getString(key);
	} 
	
}
