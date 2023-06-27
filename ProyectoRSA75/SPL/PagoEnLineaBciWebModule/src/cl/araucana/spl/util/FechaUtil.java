package cl.araucana.spl.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Operaciones sobre fechas y su formato
 * @author IBM Software Factory
 *
 */
public class FechaUtil {
	private static DateFormat formatoAs400 = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat fechaSAP = new SimpleDateFormat("yyyy'-'MM'-'dd", new Locale("ES"));
	/**
	 * Retorna fecha en formato AS400 'yyyyMMdd'
	 * @return
	 */
	public static String getFechaHoyAs400() {
		return formatoAs400.format(new Date());
	}

	/**
	 * Retorna fecha actual en formato yyyy'-'MM'-'dd
	 * @return
	 */
	public static String getFechaSAP() {
		return fechaSAP.format(new Date());
	}
}
