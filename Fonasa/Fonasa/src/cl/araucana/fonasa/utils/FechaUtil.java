package cl.araucana.fonasa.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaUtil {
	public static String formatearFecha(String formato, Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		return sdf.format(date);
	}
	
	public static Date parsearFecha(String formato, String date){
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formato);
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getFechaHoy(String formato) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formato);
			return sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
