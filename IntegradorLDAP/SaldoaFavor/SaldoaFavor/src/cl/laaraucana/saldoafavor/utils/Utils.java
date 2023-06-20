/**
 * 
 */
package cl.laaraucana.saldoafavor.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author IBM Software Factory
 *
 */
public class Utils {
	public static String getFecha() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date = sdf.format(new Date());
		return date;
	}
	
	public static String getHora() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String date = sdf.format(new Date());
		return date;
	}
	
	public static Date getFechaDate() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse(sdf.format(new Date()));
		return date;
	}
	
	public static Date getHoraDate() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date date = sdf.parse(sdf.format(new Date()));
		return date;
	}
}
