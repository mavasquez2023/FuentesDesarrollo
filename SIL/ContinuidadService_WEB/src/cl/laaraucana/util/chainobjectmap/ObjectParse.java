package cl.laaraucana.util.chainobjectmap;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ObjectParse {
	
	//Constantes para conversión de fechas
	public static final String F_FECHA_AS400 ="ddMMyyyy"; 
	public static final String F_FECHA_SAP ="dd-MM-yyyy"; 
	public static final String F_PERIODO ="yyyyMM"; 
	
	public static int stringToInt(String entrada){
		return Integer.parseInt(entrada);
	}
	
	public static float stringToFloat(String entrada){
		return Float.parseFloat(entrada);
	}
	
	/**
	 * Convierte fecha en String a Date, se debe especificar en que formato está
	 * @param entrada
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String entrada, String format) throws ParseException{
		Date fecha = null;
		DateFormat formatoFecha = new SimpleDateFormat(format);
		
		fecha = formatoFecha.parse(entrada);
		
		return fecha;
	}
}
