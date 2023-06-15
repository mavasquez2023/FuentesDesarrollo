package cl.liv.export.comun.util.file;

import java.io.IOException;
import java.util.Properties;

import cl.liv.export.comun.util.Mensajes;
import cl.liv.export.comun.util.SessionUtil;

public class PropertiesFile {

	public static void loadPropeties(String datasource, String filePath){
		Properties props = new Properties();
		try {
			String texto = ManejoArchivos.getFileAsString(filePath);
			Mensajes.info("Texto: "+ texto.split("\n").length);
			
			String[] lineas = texto.split("\n");
			for(int i=0; i< lineas.length;i++){
				Mensajes.info("linea ["+i+"]: "+ lineas[i]);
				String key = lineas[i].split("=")[0].trim();
				String value = "";
				if(lineas[i].split("=").length > 1 ){
					value = lineas[i].split("=")[1].trim();
				}
				props.put(key, value);
			}
			Mensajes.info("props: "+ props);
			Mensajes.info("Agregando properties a session: "+ datasource);
			SessionUtil.datasources.put(datasource, props);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensajes.info("ERROR AL OBENER PROPERTIES DEL ARCHIVO."+ filePath);
		}
		
	}
}
