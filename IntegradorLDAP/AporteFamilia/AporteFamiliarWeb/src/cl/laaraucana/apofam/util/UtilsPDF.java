package cl.laaraucana.apofam.util;

import java.util.HashMap;
import java.util.Map;


public class UtilsPDF {
public static Map<String, Object> hoja1(String rut, String nombre, int cargas, String fechaDescarga){
		
		Map<String, Object> param_map = new HashMap<String, Object>();
		
		param_map.put("rut", rut);
		param_map.put("nombre", nombre);
		param_map.put("numcargas", "0" + cargas + " ");
		param_map.put("fechaDescarga", UtilsFecha.getFechaCompleta());
		param_map.put("year", UtilsFecha.getFechaPeriodo().substring(0, 4) + " ");
		param_map.put("tope", Configuraciones.getConfig("aporte.tope.mensual"));
		
		return param_map;
	}
	
	
	
}
