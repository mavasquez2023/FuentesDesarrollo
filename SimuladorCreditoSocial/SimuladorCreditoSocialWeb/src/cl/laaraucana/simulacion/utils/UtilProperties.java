package cl.laaraucana.simulacion.utils;

import java.util.ResourceBundle;

public class UtilProperties {
	
	private static ResourceBundle resource = ResourceBundle.getBundle("simulacionWeb");
	
	public static String getDescripcionTipoAfiliado(String codigo){
		return resource.getString(codigo);
	}
	
	public static String getValorProperties(String codigo){
		return resource.getString(codigo);
	}

}
