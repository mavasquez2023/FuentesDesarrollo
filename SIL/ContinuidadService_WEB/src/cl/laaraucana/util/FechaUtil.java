package cl.laaraucana.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Operaciones sobre fechas y su formato
 * @author IBM Software Factory
 *
 */
public class FechaUtil {
	private static DateFormat formatoAs400 = new SimpleDateFormat("yyyyMMdd");
	private static DateFormat formatoHoraAs400 = new SimpleDateFormat("HHmmss");
	private static DateFormat formatoMonthYearAs400 = new SimpleDateFormat("yyyyMM");
	private static DateFormat formatoMonthYearWEB = new SimpleDateFormat("yyyy/MM");
	private static SimpleDateFormat fechaSAP = new SimpleDateFormat("yyyy'-'MM'-'dd", new Locale("ES"));
	private static DateFormat formatoSAP = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat formatoWebFull = new SimpleDateFormat("dd-MM-yyyy HH:mm");

	/**
	 * Retorna fecha en formato AS400 'yyyyMMdd'
	 * @return
	 */
	public static String getFechaHoyAs400() {
		return formatoAs400.format(new Date());
	}

	public static String getFechaHoySap() {
		return formatoSAP.format(new Date());
	}

	/**
	 * Retorna hora en formato AS400 'hhmmss'
	 * @return
	 */
	public static String getHoraAs400() {
		return formatoHoraAs400.format(new Date());
	}

	public static String parseFechaAs400MonthYear(String periodo) throws ParseException {
		return formatoMonthYearWEB.format(formatoMonthYearAs400.parse(periodo));
	}

	public static Date pasaFechaWebToDate(String fecha) throws ParseException {
		Date parsedUtilDate;
		parsedUtilDate = formatoWebFull.parse(fecha);
		return parsedUtilDate;
	}

	/**
	 * Convierte fecha en formato AS400 a SAP
	 * @param fecha
	 * @return
	 * @throws ParseException
	 */
	public static String pasaFechaAsASap(String fecha) throws ParseException {
		Date parsedUtilDate;
		parsedUtilDate = formatoAs400.parse(fecha);
		String formatStr = formatoSAP.format(parsedUtilDate);
		return formatStr;
	}

	public static Date formatMonthYearWEB(String periodo) throws ParseException {
		return formatoMonthYearWEB.parse(periodo);
	}

	/**
	 * @param periodo Con formato yyyyMM (obligatorio)
	 * @return
	 * @throws ParseException 
	 */
	public static String sumarUnMes(String periodo) throws ParseException {
		Calendar calendario = Calendar.getInstance();
		if (periodo != null && periodo.length() > 0) {
			Date fecha = formatoMonthYearAs400.parse(periodo);
			calendario.setTime(fecha);
			calendario.add(Calendar.MONTH, 1);
		}
		return formatoMonthYearAs400.format(calendario.getTime());
	}

	/**
	 * Compara que la fechaUno sea superior a la FechaDos
	 * @param fechaUno
	 * @param fechaDos
	 * @return
	 * @throws ParseException 
	 */
	public static boolean compararFechaAs400(String fechaUno, String fechaDos) throws ParseException {

		if (fechaDos != null && fechaDos.length() == 0) {
			return true;//Primera ejecucion, evita comprobar fechas en null o de largo 0
		}

		if (formatoMonthYearAs400.parse(fechaUno).after(formatoMonthYearAs400.parse(fechaDos))) {
			return true;
		} else {
			return false;
		}
	}

	public static long calcularDifHorasFechas(Date fecha1, Date fecha2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(fecha1);
		cal2.setTime(fecha2);

		long milis1 = cal1.getTimeInMillis();
		long milis2 = cal2.getTimeInMillis();
		long diffHours = (milis1 - milis2) / (60 * 60 * 1000);
		return diffHours;
	}

	/**
	 * Retorna fecha actual en formato yyyy'-'MM'-'dd
	 * @return
	 */
	public static String getFechaSAP() {
		return fechaSAP.format(new Date());
	}

	public static String restaDiasFechaActual(int dias) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, dias * -1);
		return formatoSAP.format(c.getTime());

	}
}
