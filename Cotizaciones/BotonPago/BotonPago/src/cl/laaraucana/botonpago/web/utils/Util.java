package cl.laaraucana.botonpago.web.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author usist54
 * 
 */
public class Util {
	private static SimpleDateFormat fechaCompleta = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
	private static SimpleDateFormat fechaSAP = new SimpleDateFormat("yyyy'-'MM'-'dd", new Locale("ES"));
	private static DecimalFormat formateador = new DecimalFormat("###,###");
	private static DecimalFormat formateador2 = new DecimalFormat("######");
	private static DateFormat formatoAs400 = new SimpleDateFormat("yyyyMMdd");
	private static DateFormat formatoAs400Periodo = new SimpleDateFormat("yyyyMM");
	private static DateFormat formatoSAP = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat formatoSPL = new SimpleDateFormat("yyyyMMddHHmmss");
	private static DateFormat formatoWeb = new SimpleDateFormat("dd-MM-yyyy");
	private static DateFormat formatoWebFull = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private static DateFormat formatoPeriodo = new SimpleDateFormat("yyyyMM");
	
	private static DecimalFormat montoFormato = new DecimalFormat("#,###.##");

	public static String dateToStringFull(Date fecha) {
		return formatoWebFull.format(fecha);
	}

	public static String dateToStringWeb(Date fecha) {
		return formatoWeb.format(fecha);
	}
	public static String dateToStringSAP(Date fecha) {
		return formatoSAP.format(fecha);
	}
	
	public static String formatFecha(String fecha) throws ParseException {
		Date date = formatoSAP.parse(fecha);
		return formatoAs400.format(date);
	}

	/**
	 * Pasa un tipo doble a String, con divisor de "." para milies y sin
	 * decimales. Ej.: doble: "9903836.0" a String: "9.903.836"
	 */
	public static String formatDobleSinDecimal(double doble) {
		String total;
		total = formateador.format(doble);
		return total;
	}

	/**
	 * @param entrada
	 *            000 - 000000000
	 * @return 000000000000
	 */
	public static String formatearFolio(String entrada) {

		if (entrada.length() > 3) {
			StringBuffer folio = new StringBuffer(entrada);
			folio.insert(3, "-");
			entrada = folio.toString();
		}

		return entrada;
	}

	/**
	 * elimina ceros a la izquierda y deja en formato int ejm: 00003212512 =>
	 * 3212512
	 */
	public static String formatNumeroStringAS400(String numero) {
		int formateado = Integer.parseInt(numero.trim());
		return String.valueOf(formateado);
	}

	/**
	 * deja en formato int ejm: 32.125,12 => 32125.12
	 */
	public static String formatNumeroStringSap(String numero) {
		Double formateado = Double.parseDouble(numero.trim().replace(".", "").replace(",", "."));
		return formateador2.format(formateado);
	}

	public static String getCreFol(String folio, String origen) {
		String folioex = folio;
		String crefol = "";
		if (origen.trim().equals("S")) {
			StringBuffer foliobf = new StringBuffer(folioex);
			foliobf.insert(3, "-");
			folioex = foliobf.toString();
		}
		crefol = folioex.split("-")[1];

		return crefol;
	}

	/**
	 * Devuelve fecha actual en formatoAs400 "yyyyMMdd"
	 * */
	public static String getFechaAs400() {
		return formatoAs400.format(new Date());
	}

	public static String getFechaCompleta() {
		return fechaCompleta.format(new Date());
	}

	public static String getFechayHora() {
		return formatoWebFull.format(new Date());
	}

	public static String getFecha() {
		return formatoWeb.format(new Date());
	}

	/**
	 * cambia a formato Año Mes de para As400 "yyyyMM"
	 * */
	public static String getFechaPeriodo() {
		return formatoAs400Periodo.format(new Date());
	}

	public static String getFechaSAP() {
		return fechaSAP.format(new Date());
	}

	/**
	 * @param date
	 * @return retorna el perido yyyyMM desde una fecha pasada en formato yyyy
	 *         -MM -dd
	 * @throws ParseException
	 */
	public static String getFechaSAPaPeriodo(String date) throws ParseException {
		java.util.Date parsedUtilDate;
		String formatStr = "";
		parsedUtilDate = fechaSAP.parse(date);
		formatStr = formatoAs400Periodo.format(parsedUtilDate);
		return formatStr;
	}

	public static String getFechaSPL() {
		return formatoSPL.format(new Date());
	}

	/**
	 * @param date
	 * @return retorna fecha en formato yyyyMM enviandole el formato dd -MM-
	 *         yyyy
	 * @throws ParseException
	 */
	public static String getFechaWEBaPeriodo(String date) throws ParseException {
		java.util.Date parsedUtilDate;
		String formatStr = "";
		parsedUtilDate = formatoWeb.parse(date);
		formatStr = formatoAs400Periodo.format(parsedUtilDate);
		return formatStr;
	}

	public static String getOfiPro(String folio, String origen) {
		String folioex = folio;
		String ofiPro = "";
		if (origen.trim().equals("S")) {
			StringBuffer foliobf = new StringBuffer(folioex);
			foliobf.insert(3, "-");
			folioex = foliobf.toString();
		}
		ofiPro = folioex.split("-")[0];

		return ofiPro;
	}

	public static boolean isFechaMenorActualAs400(String fecha) throws ParseException {

		/**
		 * Obtenemos la fecha enviada en el formato yyyyMMdd
		 */

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date fechaActual = cal.getTime();

		Date fechaDate = formatoAs400.parse(fecha);

		if (fechaDate.before(fechaActual)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Comparamos las Fechas
	 * 
	 * @param fecha
	 * @return
	 * @throws ParseException
	 */
	public static boolean isFechaMenorA(String fechaCuota, String fechaFutura) throws ParseException {

		/**
		 * Obtenemos las fechas en el formato yyyy -MM-dd
		 */
		fechaCuota= fechaCuota.replaceAll("-", "");
		int periodoCuota= Integer.parseInt(fechaCuota.substring(0, 6));
		int periodoFuturo= Integer.parseInt(fechaFutura.substring(0, 6));
		int periodoActual= Integer.parseInt(Util.getFechaPeriodo());
		
		if(periodoFuturo< periodoActual){
			periodoFuturo= periodoActual;
		}
		
		if(periodoCuota<= periodoFuturo){
			return true;
		}else{
			return false;
		}
		
		/*Date fechaActual = cal.getTime();

		Date fechaDate = formatoSAP.parse(fecha);

		if (fechaDate.before(fechaActual)) {
			return true;
		} else {
			return false;
		}*/
	}

	/**
	 * Formato fecha as400 "20090612" a formato Web "12-06-2009"
	 * */
	public static String pasaFechaASaWEB(String date) throws ParseException {
		java.util.Date parsedUtilDate;
		String formatStr = "";
		parsedUtilDate = formatoAs400.parse(date);
		formatStr = formatoWeb.format(parsedUtilDate);
		return formatStr;
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

	public static String pasaFechaSAPaWEB(String date) throws ParseException {
		java.util.Date parsedUtilDate;
		String formatStr = "";
		parsedUtilDate = formatoSAP.parse(date);
		formatStr = formatoWeb.format(parsedUtilDate);
		return formatStr;
	}

	/**
	 * "12-06-2009" a "20090612"
	 * */
	public static String pasaFechaWEBaAs400(String date) throws ParseException {
		java.util.Date parsedUtilDate;
		String formatStr = "";
		parsedUtilDate = formatoWeb.parse(date);
		formatStr = formatoAs400.format(parsedUtilDate);
		return formatStr;
	}

	public static String pasaFechaWEBaSAP(String date) throws ParseException {
		java.util.Date parsedUtilDate;
		String formatStr = "";
		parsedUtilDate = formatoWeb.parse(date);
		formatStr = formatoSAP.format(parsedUtilDate);
		return formatStr;
	}

	public static String rellenarCampos(String entrada, int largo, String caracter) throws Exception {
		String espacios = "";
		if (entrada.length() <= largo) {
			for (int i = 0; i < (largo - entrada.length()); i++) {
				espacios += caracter;
			}
			if (caracter.equals("0"))
				return espacios + entrada;
			else
				return entrada + espacios;
		} else {
			throw new Exception("Error al rellenar campo. se excede el largo maximo");
		}
	}

	public static String rellenarCampos(String entrada, String nombre, int largo, String caracter) throws RuntimeException {
		String espacios = "";

		if (entrada == null) {
			throw new RuntimeException("Campo '" + nombre + "' no inicializado");
		} else if (entrada.length() > largo) {
			throw new RuntimeException("Campo " + nombre + " es mayor de " + largo + " caracteres. (" + entrada.length() + ")");
		} else {

			if (entrada.length() < largo) {
				for (int i = 0; i < (largo - entrada.length()); i++) {
					espacios += caracter;
				}
				if (caracter.equals("0"))
					return espacios + entrada;
				else
					return entrada + espacios;
			}
		}
		return entrada;
	}

	public static String replace(String key, String bundle) {
		ResourceBundle mainCfg = ResourceBundle.getBundle(bundle);
		String campo;
		if (key != null && mainCfg.containsKey(key)) {
			campo = mainCfg.getString(key);
		} else {
			campo = key;
			// campo
			// =
			// mainCfg.getString("default");
		}
		return campo;
	}

	public static ActionForward returnErrorForward(ActionMapping mapping, Exception e) {
		return new ActionForward(mapping.findForward("error").getPath() + "?errorMsg=" + e.getMessage() + " - ", false);
	}

	public static Date stringToDateFull(String fecha) throws ParseException {
		return formatoWebFull.parse(fecha);
	}

	public static Date stringToDateWeb(String fecha) throws ParseException {
		return formatoWeb.parse(fecha);
	}

	public static Date sumDays(Date fecha, int sum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.DATE, sum);
		return cal.getTime();
	}

	public static Date sumHours(Date fecha, int sum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.HOUR, sum);
		return cal.getTime();
	}

	public static Date sumMonths(Date fecha, int sum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.MONTH, sum);
		return cal.getTime();
	}

	public static Date sumSeconds(Date fecha, int sum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.SECOND, sum);
		return cal.getTime();
	}

	/**
	 * metodo que recibe un Strig con formato DecimalFormat("#,###.##") 
	 * y elimina las comas y decimales entregando como respuesta un valor String
	 * completado conceros a la izquierda segun el "largo" recibido como parametro.
	 * **/
	public static String getFormatoMontoEntero(String in, int largo) throws Exception {
		montoFormato.setDecimalFormatSymbols(new DecimalFormatSymbols(new Locale("es")));
		String[] aux;
		String number = "";
		aux = in.replace(",", "").split("\\.");
		number = Util.rellenarCampos(aux[0], largo, "0");
		return number;
	}

	public static String isAcuerdo(String producto) {
		String c = "0";
		if (producto.trim().equalsIgnoreCase("ACUERDO")) {
			c = "A";
		}
		return c;

	}

}
