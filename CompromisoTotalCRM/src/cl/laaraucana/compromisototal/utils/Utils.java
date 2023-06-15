package cl.laaraucana.compromisototal.utils;

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

public class Utils {

	private static DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(new Locale("es", "CL"));
	private static DecimalFormat format = new DecimalFormat("###,###,###.##", simbolos);
	private static DecimalFormat formateador = new DecimalFormat("###,###");
	private static DecimalFormat formateador2 = new DecimalFormat("###,###.#####");
	private static DateFormat formatoSAP = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat formatoAS400 = new SimpleDateFormat("yyyyMMdd");
	private static DateFormat formatoAsicom = new SimpleDateFormat("yyyyMMdd");
	private static DateFormat formatoWeb = new SimpleDateFormat("dd-MM-yyyy");
	// private static DateFormat formatoSAPFull = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static DateFormat formatoWebFull = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	// private static DateFormat horas = new SimpleDateFormat("HH:mm:ss");

	public static ActionForward returnErrorForward(ActionMapping mapping, Exception e) {
		return new ActionForward(mapping.findForward("error").getPath() + "?errorMsg=" + e.getMessage(), false);
	}

	public static ActionForward returnErrorTablaForward(ActionMapping mapping, String mensaje, String colspan) {
		return new ActionForward(mapping.findForward("errorTabla").getPath() + "?errorMsg=" + mensaje + "&colspan=" + colspan, false);
	}

	public static String pasaFechaSAPaWEB(String date) throws ParseException {
		java.util.Date parsedUtilDate;
		String formatStr = "";
		parsedUtilDate = formatoSAP.parse(date);
		formatStr = formatoWeb.format(parsedUtilDate);
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

	public static String dateToStringSAP(Date fecha) {
		return formatoSAP.format(fecha);
	}

	public static Date stringToDate(String fecha) throws ParseException {
		return formatoWeb.parse(fecha);
	}

	public static Date stringToDateSAP(String fecha) throws ParseException {

		if (fecha == null || fecha.isEmpty()) {
			fecha = "0001-01-01";
		}
		Date date = formatoSAP.parse(fecha);

		return date;
	}

	public static Date stringToDateAS400(String fecha) throws ParseException {
		return formatoAS400.parse(fecha);
	}

	public static Date stringToDateAsicom(String fecha) throws ParseException {
		return formatoAsicom.parse(fecha);
	}

	public static String dateToStringFull(Date fecha) {
		return formatoWebFull.format(fecha);
	}

	public static Date stringToDateFull(String fecha) throws ParseException {
		return formatoSAP.parse(fecha);
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

	public static Date sumSeconds(Date fecha, int sum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.SECOND, sum);
		return cal.getTime();
	}

	public static Double stringToDouble(String str) {
		String parametro = str;
		Double numero = 0.0;
		if (parametro != null && !parametro.isEmpty()) {
			parametro = parametro.trim();
			parametro = parametro.replace(".", "");
			parametro = parametro.replace(",", ".");
			numero = new Double(parametro);
		}

		return numero;
	}

	public static Double parseTasaInteresAs400(String str) {
		String parametro = str;
		StringBuffer st = new StringBuffer(parametro);

		parametro = st.insert(2, ".").toString();

		if (parametro == null || parametro.isEmpty() || parametro.equals("")) {
			parametro = "0.0";
		}
		Double numero = new Double(parametro);

		return numero;
	}

	public static String formateaNum(long num) {
		String numRet = "";
		numRet = format.format(num); // / esto muestra $23.32
		return numRet;
	}

	public static long parseaNumero(String numStr) throws ParseException {
		long num = 0;
		Number numTemp = format.parse(numStr);
		num = numTemp.longValue();
		return num;
	}

	public static String formateaFolio(String folio) {
		// inserta - en el tercer espacio de un folio 000000000000 -->
		// 000-000000000 donde los tres primeros caracteres corresponden a
		// oficina
		StringBuffer st = new StringBuffer(folio);
		folio = st.insert(3, "-").toString();

		return folio;
	}

	public static String eliminaCeros(String cadena) {
		String cadenaResultadoString = cadena.replaceFirst("^ *", "");
		cadenaResultadoString = cadenaResultadoString.replaceFirst("^0*", "");
		return cadenaResultadoString;
	}

	public static String stringToNumeroSTR(String str) {
		String parametro = str;
		if (parametro == null || parametro.isEmpty() || parametro.equals("")) {
			parametro = "0.0";
		}
		Double numero = new Double(parametro);
		Integer i = numero.intValue();

		return i.toString();
	}

	public static Double sumaParaMontoDCTO(String fecha, Double montoInteres, Double montoCapitalAmortizado, Double montoSeguros, Double gravamenes) {
		Double resultado = null;
		int fechaOto =  Integer.parseInt(fecha.trim());;
		try {
			if (fechaOto <= 20110930) {
				resultado = (montoInteres + montoCapitalAmortizado + gravamenes);
			} else {
				resultado = (montoInteres + montoCapitalAmortizado + montoSeguros  + gravamenes);
			}
		} catch (Exception e) {
			resultado = null;
		}
		return resultado;
	}
	
	public static String formateaRolPagadorSAP(String rol) {
		String rolPagador = rol;
		if (rolPagador != null && rolPagador.trim().toUpperCase().equals("X")) {
			rolPagador = "Si";
		} else {
			rolPagador = "NO";
		}
		return rolPagador;
	}
	public static String replaceRolPagadorAs400(String rol) {
		String rolpagador = rol.trim();
		if (rolpagador.toUpperCase().trim().equals("N")) {
			rolpagador = "No";
		} else if (rolpagador.toUpperCase().trim().equals("S")) {
			rolpagador = "Si";
		}
		return rolpagador;
	}
	
	public static String formateaRepactaSAP(String rep) {
		String repacta = rep;
		if (repacta != null && repacta.trim().toUpperCase().equals("X")) {
			repacta = "Si";
		} else {
			repacta = "NO";
		}
		return repacta;
	}

	public static String formateaReprogramacSAP(String reprogra) {
		String reprogramac = reprogra;
		if (reprogramac != null && reprogramac.trim().toUpperCase().equals("X")) {
			reprogramac = "Si";
		} else {
			reprogramac = "NO";
		}
		return reprogramac;
	}
	
	public static String calculaDV(String vrut) {
		String rut = vrut.trim();
		int cantidad = rut.length();
		int factor = 2;
		int suma = 0;
		String verificador = "";

		for (int i = cantidad; i > 0; i--) {
			if (factor > 7) {
				factor = 2;
			}
			suma += (Integer.parseInt(rut.substring((i - 1), i))) * factor;
			factor++;
		}

		verificador = String.valueOf(11 - suma % 11);

		if (verificador.equals("10")) {
			verificador = "k";
		}
		if (verificador.equals("11")) {
			verificador = "0";
		}

		return vrut + "-" + verificador;
	}
	
//	public static String formatEstadoCuotaDETAS400(String estado) {
//		String estCuota = estado.trim();
//		if (estCuota.toUpperCase().trim().equals("N")) {
//			estCuota = "vigente";
//		} else if (estCuota.toUpperCase().trim().equals("S")) {
//			estCuota = "morosa";
//		}
//
//		return estCuota;
//	}
	public static String logicaMontoAbonoSAP(String estado, String totalCuota, String montoAbono) {
		String est = estado;
		if (est.trim().equals("CANCELADA")) {
			return totalCuota;
		} else {
			return montoAbono;
		}
	}
	public static String replaceEstadoCuota(String key) {
		ResourceBundle mainCfg = ResourceBundle.getBundle("cl.laaraucana.compromisototal.resource.convertAs400.estadoCuota");
		String campo;
		if (key !=null && mainCfg.containsKey(key)) {
			campo = mainCfg.getString(key);
		} else {
			campo = key;
		}
		return campo;
	}
	public static String replaceIdentificacionCaja(String key) {
		ResourceBundle mainCfg = ResourceBundle.getBundle("cl.laaraucana.compromisototal.resource.convertIntercaja.identificacionCaja");
		String campo ;
		if (key !=null && mainCfg.containsKey(key)) {
			campo = mainCfg.getString(key);
		} else  {
			campo = key;
//			campo = mainCfg.getString("default");
		}
		return campo;
	}
	public static String replaceSujetoDcto(String key) {
		ResourceBundle mainCfg = ResourceBundle.getBundle("cl.laaraucana.compromisototal.resource.convertIntercaja.sujetoDescuento");
		String campo ;
		if (key !=null && mainCfg.containsKey(key)) {
			campo = mainCfg.getString(key);
		} else  {
			campo = key;
//			campo = mainCfg.getString("default");
		}
		return campo;
	}
	
	public static String formateaDobleConDecimal(double doble) {
		String total;
		total = formateador2.format(doble);
		return total;
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
	
	
}
