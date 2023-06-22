package cl.laaraucana.satelites.Utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

@SuppressWarnings("unused")
public class Utils {
	private static DateFormat formatoSAP = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat formatoWeb = new SimpleDateFormat("dd-MM-yyyy");
	private static DateFormat formatoWeb2 = new SimpleDateFormat("dd/MM/yyyy");
	private static DateFormat horas = new SimpleDateFormat("HH:mm:ss");
	private static DateFormat formatoSAPFull = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static DateFormat formatoWebFull = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private static SimpleDateFormat fechaCompleta = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
	private static SimpleDateFormat fechaSAP = new SimpleDateFormat("yyyy'-'MM'-'dd", new Locale("ES"));
	private static SimpleDateFormat fechaPrepago1 = new SimpleDateFormat("MMMM' del 'yyyy", new Locale("ES"));
	private static DateFormat formatoAs400 = new SimpleDateFormat("yyyyMMdd");
	private static DateFormat formatoAs400Periodo = new SimpleDateFormat("yyyyMM");
	private static DateFormat formatoPeriodoPrepago = new SimpleDateFormat("MM/yyyy");
	private static DecimalFormat formateador = new DecimalFormat("###,###");
	private static DecimalFormat formateador2 = new DecimalFormat("###,###.#####");

	@SuppressWarnings("rawtypes")
	public static ActionForward returnErrorForward(ActionMapping mapping, Exception e, Class clase) {
		Logger.getLogger("").error("Error en " + clase.getName() + ":" + e.getMessage(), e);
		return new ActionForward(mapping.findForward("error").getPath() + "?errorMsg=" + e.getMessage() + " - " + clase, false);
	}
	
	
	public static String fechaWeb(){
		return formatoWeb.format(new Date());
	}

	public static String pasaFechaSAPaWEB(String date) throws ParseException {
		java.util.Date parsedUtilDate;
		String formatStr = "";
		parsedUtilDate = formatoSAP.parse(date);
		formatStr = formatoWeb.format(parsedUtilDate);
		return formatStr;
	}

	/**
	 * Formato fecha as400 "20090612" a formato Web "12-06-2009"
	 * */
	public static String pasaFechaASaWEB(String date) throws ParseException {
		java.util.Date parsedUtilDate;
		String formatStr = "";
		if (date != null && date.trim().length() == 8) {
			parsedUtilDate = formatoAs400.parse(date.trim());
			formatStr = formatoWeb.format(parsedUtilDate);
		}
		return formatStr;
	}

	public static String pasaFechaWEBaSAP(String date) throws ParseException {
		java.util.Date parsedUtilDate;
		String formatStr = "";
		parsedUtilDate = formatoWeb.parse(date);
		formatStr = formatoSAP.format(parsedUtilDate);
		return formatStr;
	}

	public static String dateToString(Date fecha) {
		return formatoWeb.format(fecha);
	}
	public static String dateToString2(Date fecha) {
		return formatoWeb2.format(fecha);
	}

	/*
	 * public static String dateToString2(Date fecha){ return
	 * formatoWeb2.format(fecha); }
	 */
	public static Date stringToDate(String fecha) throws ParseException {
		return formatoWeb.parse(fecha);
	}
	
	public static Date stringToDateAs400(String periodo) throws ParseException {
		return formatoAs400Periodo.parse(periodo);
	}
	
	public static String dateToStringFull(Date fecha) {
		return formatoWebFull.format(fecha);
	}

	public static Date stringToDateFull(String fecha) throws ParseException {
		return formatoWebFull.parse(fecha);
	}

	public static Date sumDays(Date fecha, int sum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.DATE, sum);
		return cal.getTime();
	}

	public static Date sumMonths(Date fecha, int sum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.MONTH, sum);
		return cal.getTime();
	}

	public static Date sumHours(Date fecha, int sum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.HOUR, sum);
		return cal.getTime();
	}

	public static Date sumSeconds(Date fecha, int sum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.SECOND, sum);
		return cal.getTime();
	}

	public static String getFechaCompleta() {
		return fechaCompleta.format(new Date());
	}

	public static String fechaSAP() {
		return fechaSAP.format(new Date());
	}

	public static String getFechaPrepago1() {
		return fechaPrepago1.format(new Date());
	}

	/*
	 * public static String parseFechaAs400(String fecha){ //formato fecha as400
	 * 20090612 try { Date date = formatoAs400.parse(fecha); return
	 * formatoWeb2.format(date); } catch (ParseException e) { return fecha;
	 * //fecha tiene otro formato, retornar la original } }
	 */

	/**
	 * Utilizado para Certificado de Prepago y Finiquito. Formato fecha Web
	 * "12-06-2009" a formato as400 "20090612"
	 * */
	public static String pasaFechaWEBaAs400(String date) throws ParseException {
		java.util.Date parsedUtilDate;
		String formatStr = "";
		parsedUtilDate = formatoWeb.parse(date);
		formatStr = formatoAs400.format(parsedUtilDate);
		return formatStr;
	}

	/**
	 * Utilizado para Certificado de Prepago y Finiquito. Toma la fecha actual y
	 * cambia a formato Año Mes de para As400 "yyyyMM"
	 * */
	public static String getFechaPeriodo() {
		return formatoAs400Periodo.format(new Date());
	}

	/**
	 * 
	 * @param date
	 * @return retorna fecha en formato yyyyMM enviandole el formato dd-MM-yyyy
	 * @throws ParseException
	 */
	public static String getFechaWEBaPeriodo(String date) throws ParseException {
		java.util.Date parsedUtilDate;
		String formatStr = "";
		parsedUtilDate = formatoWeb.parse(date);
		formatStr = formatoAs400Periodo.format(parsedUtilDate);
		return formatStr;
	}

	/**
	 * 
	 * @param date
	 * @return retorna el perido yyyyMM desde una fecha pasada en formato
	 *         yyyy-MM-dd
	 * @throws ParseException
	 */

	public static String getFechaSapaPeriodo(String date) throws ParseException {
		java.util.Date parsedUtilDate;
		String formatStr = "";
		parsedUtilDate = fechaSAP.parse(date);
		formatStr = formatoAs400Periodo.format(parsedUtilDate);
		return formatStr;
	}

	public static Double stringToDouble(String str) {
		String parametro = str;
		Double numero = 0.0;

		if (parametro != null && parametro.length()>0) {
			parametro = parametro.trim();
			parametro = parametro.replace(".", "").replace(",", ".");
			numero = new Double(parametro);
		}

		return numero;
	}

	/**
	 * Devuelve fecha actual en formatoAs400 "yyyyMMdd"
	 * */
	public static String getFechaHoyAs400() {
		return formatoAs400.format(new Date());
	}

	/**
	 * Toma una fecha en formato SAP "yyyy-MM-dd" y la devuelve en formatoAs400
	 * "yyyyMMdd"
	 * */
	public static String pasaFechaSAPaAs400(String date) throws ParseException {
		java.util.Date parsedUtilDate;
		String formatStr = "";
		parsedUtilDate = formatoSAP.parse(date);
		formatStr = formatoAs400.format(parsedUtilDate);
		return formatStr;
	}

	public static String formatearFolio(String entrada) {

		if (entrada.length() > 3) {
			StringBuffer folio = new StringBuffer(entrada);
			folio.insert(3, "-");
			entrada = folio.toString();
		}

		return entrada;
	}

	/**
	 * Obtiene el valor anterior de algun String cortado por algun parametro
	 * enviado
	 * */
	public static String obtenerValorAnteriorA(String entrada, String corte) {
		String salida = entrada;
		if (entrada.contains(corte)) {
			String[] array = entrada.split(corte);
			if (salida.length() >= 1) {
				salida = array[0];
			}
		}
		return salida;
	}

	public static String obtenerValorSiguienteA(String entrada, String corte) {
		String salida = entrada;
		if (entrada.contains(corte)) {
			String[] array = entrada.split(corte);
			if (salida.length() >= 2) {
				salida = array[1];
			}
		}
		return salida;
	}

	/**
	 * Pasa un tipo doble a String, con divisor de "." para milies y sin
	 * decimales. Ej.: doble: "9903836.0" a String: "9.903.836"
	 */
	public static String formateaDobleSinDecimal(double doble) {
		String total;
		total = formateador.format(doble);
		return total;
	}

	/**
	 * Pasa un tipo doble a String, con divisor de "." para milies y sin
	 * decimales. Ej.: doble: "9903836.56" a String: "9.903.836,56"
	 */
	public static String formateaDobleConDecimal(double doble) {
		String total;
		total = formateador2.format(doble);
		return total;
	}
	
	/**
	 * 
	 * @param montoSap
	 * @return 
	 */
	public static String formateaMontoSAP(String montoSap){
		return montoSap.replace(".", "").replace(",", ".");
	}
	
	public static Double formateaMontoSAPDouble(String montoSap){
		return Double.valueOf(montoSap.replace(".", "").replace(",", "."));
	}
	
	/**
	 * 
	 * @param mesesRestarSumar
	 * @return
	 * @throws Exception 
	 */
	public static String obtenerPeriodoCualquiera(String periodo, int mesesRestarSumar) throws Exception {
		Calendar calendar = Calendar.getInstance();
		if(periodo==null || periodo.equals("") ){
			calendar.setTime(new Date()); 
		}else{
			calendar.setTime(stringToDateAs400(periodo));
		}
		calendar.add(Calendar.MONTH, mesesRestarSumar); 
		return formatoAs400Periodo.format(calendar.getTime());
	}
	
	public static int obtenerDiaActual() {
		
		return Integer.parseInt(getFechaHoyAs400().substring(6));
	}
	
}
