package cl.liv.export.comun.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class SessionUtil {
	public static HashMap<String, Properties> datasources = new HashMap<String, Properties>();
	public static HashMap<String, HashMap<String, Object>> reportes = new HashMap<String, HashMap<String, Object>>();
	public static HashMap<String, HashMap<String, Object>> csvs = new HashMap<String, HashMap<String, Object>>();
	public static HashMap<String, HashMap<String, Object>> xmls = new HashMap<String, HashMap<String, Object>>();
	public static HashMap<String, HashMap<String, Object>> txts = new HashMap<String, HashMap<String, Object>>();
	public static HashMap<String, HashMap<String, Object>> xlss = new HashMap<String, HashMap<String, Object>>();
	public static HashMap<String, HashMap<String, Object>> schedules = new HashMap<String, HashMap<String, Object>>();
	
	public static HashMap<String,Properties> properties = new HashMap<String, Properties>();
	
	public static void limpiarDatosSession(){
		Mensajes.info("LIMPIANDO DATOS SESSION : Datasources, Reportes...");
		datasources = new HashMap<String, Properties>();
		reportes = new HashMap<String, HashMap<String, Object>>();
		csvs = new HashMap<String, HashMap<String, Object>>();
		txts = new HashMap<String, HashMap<String, Object>>();
		xlss = new HashMap<String, HashMap<String, Object>>();
		schedules = new HashMap<String, HashMap<String,Object>>();
		properties = new HashMap<String, Properties>();
		Mensajes.info("DATOS DE SESSION LIMPIADOS : Datasources, Reportes...");
		 
	}

	public static String getProperty(String file, String key){
		if(properties.get(file) == null){
			Properties prop = new Properties();
			InputStream in;
			try {
				in = new FileInputStream(PropertiesComunUtil.getProperty("export.path.resources")+"comun/properties/"+file+".properties");
				prop.load(in);
				in.close();
				properties.put(file, prop);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		if(properties.get(file) != null)
			return properties.get(file).getProperty(key);
		
		return null;
	}
	
	
}
