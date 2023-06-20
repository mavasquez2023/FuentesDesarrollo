package cl.laaraucana.muvu.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class UtilsPDF {
public static Map<String, Object> hoja1(String rut, String nombre, String fechaInicio, String fechaTermino){
		
		Map<String, Object> param_map = new HashMap<String, Object>();
		
		param_map.put("rut_afiliado", rut);
		param_map.put("nombre_afiliado", nombre);
		param_map.put("fecha_ini", fechaInicio);
		param_map.put("fecha_fin", fechaTermino);
		
		return param_map;
	}
	
	public static Map<String, Object> hoja2(String email){
		
		Map<String, Object> param_map = new HashMap<String, Object>();
		
		param_map.put("email", email);
		
		return param_map;
	}
	
	
}
