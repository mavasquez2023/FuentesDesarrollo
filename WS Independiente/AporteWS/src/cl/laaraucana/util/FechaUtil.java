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
	/**
	 * Retorna fecha en formato AS400 'yyyyMMdd'
	 * @return
	 */
	public static String getFechaHoyAs400() {
		return formatoAs400.format(new Date());
	}

	/**
	 * Retorna hora en formato AS400 'hhmmss'
	 * @return
	 */
	public static String getHoraAs400() {
		return formatoHoraAs400.format(new Date());
	}

	public static String parseFechaAs400MonthYear(String periodo) throws ParseException{
		return formatoMonthYearWEB.format(formatoMonthYearAs400.parse(periodo));
	}
	
	/**
	 * Convierte fecha en formato AS400 a SAP
	 * @param fecha
	 * @return
	 * @throws ParseException
	 */
	public static String pasaFechaAsASap(String fecha) throws ParseException{
		  Date parsedUtilDate;
		  parsedUtilDate = formatoAs400.parse(fecha);
		  String formatStr = formatoSAP.format(parsedUtilDate);
		  return formatStr;
	}
	
	public static Date formatMonthYearWEB(String periodo) throws ParseException{
		return formatoMonthYearWEB.parse(periodo);
	}
	
	/**
	 * @param periodo Con formato yyyyMM (obligatorio)
	 * @return
	 * @throws ParseException 
	 */
	public static String sumarUnMes(String periodo) throws ParseException{
		Calendar calendario = Calendar.getInstance();
		if(periodo!=null && periodo.length()>0){
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
	public static boolean compararFechaAs400(String fechaUno, String fechaDos) throws ParseException{
		
		if(fechaDos!=null && fechaDos.length()==0){
			return true;//Primera ejecucion, evita comprobar fechas en null o de largo 0
		}
		
		if(formatoMonthYearAs400.parse(fechaUno).after(formatoMonthYearAs400.parse(fechaDos))){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Retorna fecha actual en formato yyyy'-'MM'-'dd
	 * @return
	 */
	public static String getFechaSAP() {
		return fechaSAP.format(new Date());
	}
}
