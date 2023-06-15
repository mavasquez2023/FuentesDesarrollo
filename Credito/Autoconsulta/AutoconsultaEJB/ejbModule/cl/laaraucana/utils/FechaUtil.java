package cl.laaraucana.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaUtil {
	private static DateFormat formatoDet = new SimpleDateFormat("yyyyMMdd");
	private static DateFormat formatoCab = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Formato yyyyMMdd a java.util.Date
	 * @param fecha
	 * @return
	 * @throws ParseException
	 */
	public static Date parseFechaAs400(String fecha) throws ParseException{
		return formatoDet.parse(fecha);
	}
	
	/**
	 * Formato dd/MM/yyyy a java.util.Date
	 * @param fecha
	 * @return
	 * @throws ParseException
	 */
	public static Date parseFechaWeb(String fecha) throws ParseException{
		return formatoCab.parse(fecha);
	}
	
	public static String fechaAs400ToWeb(String fecha){
		String salida = null;
		try {
			Date aux = formatoDet.parse(fecha);
			salida = formatoCab.format(aux);
		} catch (Exception e) {
			salida = fecha.substring(6, 8) + "/" + fecha.substring(4, 6) + "/"+ fecha.substring(0, 4);
		}
		return salida;
	}
}
