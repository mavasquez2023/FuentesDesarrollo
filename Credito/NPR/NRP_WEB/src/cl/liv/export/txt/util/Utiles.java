package cl.liv.export.txt.util;

import java.math.BigDecimal;
import java.util.HashMap;

import cl.lib.export.txt.impl.GenerarTXT;
import cl.liv.export.comun.util.Funciones;

public class Utiles {

	public static String obtenerDataProcesada(HashMap<String, String> configuracion,HashMap<String, Object> registro, String valor){
		
		int largo = 0;
		
		try{
			largo = Integer.parseInt(configuracion.get("largo").toString());
		}
		catch(Exception e){
			largo = valor.length();
		}
		
		
		if(configuracion.get("ejecutar_metodo").equalsIgnoreCase("true")){
			valor = Funciones.ejecutarMetodo(configuracion.get("clase"), configuracion.get("metodo"), registro);
		}
		
		if(largo < valor.length()){
			valor = valor.substring(0, largo);
		}
		
		
		String salida = rellenarTexto(valor,largo , configuracion.get("relleno").toString(), configuracion.get("alineamiento").toString());
		return salida;
	} 
	
	public static String rellenarTexto(String texto,int largo, String relleno, String alineamientoTexto){
		for(int i=texto.length(); i<largo;i++){
			if(alineamientoTexto.equalsIgnoreCase("derecha")){
				texto = relleno + texto;
			}
			else if(alineamientoTexto.equalsIgnoreCase("izquierda")){
				texto =  texto + relleno;
			}
		}
		
		return texto;
	} 
	

	public static void ejecutarMetodo(GenerarTXT instancia, HashMap<String, String> columna, HashMap<String, Object> valores) {
		String key =  columna.get("data");
		if(valores.get("RUTBEN")!= null && valores.get("RUTBEN").toString().equals("7910467")) {
		}
		if(columna.get("metodo") != null && columna.get("metodo").toString().equalsIgnoreCase("_SUM_")) {
			
			Integer valor = (Integer)  instancia.dataTMP.get(columna.get("nombre"));
			if(valor == null) {
				valor = 0;
			}
			Integer valorASumar = 0;
			if(valores.get(key) instanceof Integer) {
				valorASumar = (Integer)valores.get(key);
			}
			else if(valores.get(key) instanceof BigDecimal) {
				valorASumar = ((BigDecimal)valores.get(key)).intValue();
			}
			if(valorASumar == null) {
				valorASumar = 0;
			}
			
			instancia.dataTMP.put(columna.get("nombre"), ( valorASumar + valor)  );
		}
		else if(columna.get("metodo") != null && columna.get("metodo").toString().equalsIgnoreCase("_COUNT_")) {
			Integer valor = (Integer)  instancia.dataTMP.get(columna.get("nombre"));
			if(valor == null) {
				valor = 0;
			}
			valor++;
			
			instancia.dataTMP.put(columna.get("nombre"), (valor)  );
		}
		else if(columna.get("metodo") != null && columna.get("metodo").toString().equalsIgnoreCase("_FIRST_")) {
			if(instancia.dataTMP.get(columna.get("nombre")) == null) {

				instancia.dataTMP.put(columna.get("nombre"), (valores.get(key))  );
			}
			
		}
	}
}
