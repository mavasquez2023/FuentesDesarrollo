package cl.araucana.spl.util;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import cl.araucana.spl.base.Constants;

public class Renderer {
	private static final Logger logger = Logger.getLogger(Renderer.class);
	
	public static final java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(Constants.DEFAULT_DATE_PATTERN);
	public static final SimpleDateFormat dateFormatForDb = new SimpleDateFormat(Constants.DB_DATE_PATTERN);
	public static final java.text.SimpleDateFormat hourFormat = new java.text.SimpleDateFormat(Constants.DEFAULT_HOUR_PATTERN);
	
	public static final java.text.SimpleDateFormat dateMsgFormat = new java.text.SimpleDateFormat("yyyyMMdd");
	public static final java.text.SimpleDateFormat datetimeMsgFormat = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
	public static final java.text.SimpleDateFormat datetimeFormat = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
	public static final java.text.SimpleDateFormat hourMsgFormat = new java.text.SimpleDateFormat("HHmmss");
	
	public static final java.text.DecimalFormat intFormat = new java.text.DecimalFormat("0");
	public static final java.text.DecimalFormat decFormat = new java.text.DecimalFormat("0.00");
	public static final java.text.DecimalFormat moneyFormat = new java.text.DecimalFormat("##,###,###,###,###,###.##");
	public static final java.text.DecimalFormat thousandsFormat = new java.text.DecimalFormat("#,###");
	public static final DecimalFormat intFormater4 = new DecimalFormat("0000");	
	public static final DecimalFormat intFormater5 = new DecimalFormat("00000");	
	public static final DecimalFormat intFormater6 = new DecimalFormat("000000");	
	
	
	public String formatRut(int numero, int digitoVerificador) {
		String rutConComas = thousandsFormat.format((long)numero);
		return  rutConComas.replace(',','.') + "-" + digitoVerificador;
	}
	public String formatRut(int numero, String digitoVerificador) {
		String rutConComas = thousandsFormat.format((long)numero);
		return  rutConComas.replace(',','.') + "-" + digitoVerificador;
	}

	public String formatDec(double d) {
		return decFormat.format(d);
	}

	public String formatInt(long l) {
		return intFormat.format(l);
	}
	public String formatMoney(double d) {
	    NumberFormat nf = NumberFormat.getCurrencyInstance();
	    logger.debug("formateando d -> " + nf.format(d));
		
		return "" + d;
	}
	public String formatDec(Number n) {
		if (n == null)
			return "";
		return decFormat.format(n.doubleValue());
	}
	public String formatInt(Number n) {
		if (n == null)
			return "";
		return intFormat.format(n.longValue());
	}
	public String formatMoney(Number n) {
		if (n == null)
			return "";
		String valor = moneyFormat.format(n.longValue());
		/*valor = valor.replace('.','j');
		valor = valor.replace(',','.');
		valor = valor.replace('j',',');*/
		return valor;
	}

	public String formatMoney(Float n) {
		if (n == null)
			return "";
		String valor = moneyFormat.format(n.longValue());
		valor = valor.replace('.','j');
		valor = valor.replace(',','.');
		valor = valor.replace('j',',');
		return valor;
	}
	
	public String formatDatetime(java.util.Date d) {		
		if (d == null)	return "";
		return datetimeFormat.format(d);
	}
	
	public String formatDatetimeMsg(java.util.Date d) {		
		if (d == null)	return "";
		return datetimeMsgFormat.format(d);
	}

	/**
	 * Da formato a a una fecha segun formato en Constants.DEFAULT_DATE_FORMAT
	 * @param d La fecha a formatear.
	 * @return Si d es null, un string vacio. Si no, la fecha segun el formato.
	 * 
	 */
	public String formatDate(java.util.Date d) {
		if (d == null)	return "";
		return dateFormat.format(d);
	}

	public String formatDateMsg(java.util.Date d) {
		if (d == null) return "";
		return dateMsgFormat.format(d);
	}
	
	public String formatHourMsg(java.util.Date d) {
		if (d == null) return "";
		return hourMsgFormat.format(d);
	}	
	
	public String formatDateForDb(Date d) {
		if (d == null) return null;
		return dateFormatForDb.format(d);
	}

	public String formatTime(java.util.Date d) {
		if (d == null)
			return "";
		return d.toString();
	}
	
	/**
	 * Entrega un java.math.BigDecimal
	 * @param s Un string con numeros
	 */
	public java.math.BigDecimal parseBigDecimal(String s) {
		BigDecimal b = null;
		try {
			b = new BigDecimal(s);
		} catch (NumberFormatException nfe) {
			logger.info("Problemas parseando supuesto numero: " + s + ": " + nfe);
		}
		return b;
	}
	
	/**
	 * Trata de formatear el objeto entregado usando String.valueOf.
	 * 
	 * @param o El objeto por convertir
	 * @return Si el objeto es null, retorna un string vacio (""). Si no retorna el valor de String.valueOf(objeto).
	 * 
	 */
	public String formatSimple(Object o) {
		String s = "";
		if (o != null) {
			s = String.valueOf(o);
		}
		return s;
	}

	public String formatSimple(BigDecimal o) {
		if (o != null && o instanceof BigDecimal && Nulls.isNotNull((BigDecimal) o)) {
			return String.valueOf(o);
		}
		return "";
	}
	public String formatSimple(Date o) {
		if (o != null && o instanceof Date && Nulls.isNotNull((Date) o)) {
			return formatDate((Date) o);
		}
		return "";
	}
	
	/**
	 * Parse de una fecha que se asume en formato correspondiente al patron Constants.DEFAULT_DATE_PATTERN
	 * 
	 * @param fecha La fecha a crear
	 * @return El objeto tipo Date correspondiente a la fecha. Si la fecha es vacio o null, retorna null.
	 * 
	 * @throws ParseException Si el valor entregado no se puede parsear con el formato mencionado.
	 */
	public Date parseDate(String fecha) throws ParseException {
		Date d = null;
		if (fecha != null && fecha.trim().length() > 0) {
			d = dateFormat.parse(fecha);
		}
		return d;
	}

	public String formatInt4(Number n) {
		if (n==null) return "";
		return intFormater4.format(n.longValue());
	}
	
	public String formatInt5(Number n) {
		if (n==null) return "";
		return intFormater5.format(n.longValue());
	}
	public String formatInt6(Number n) {
		if (n==null) return "";
		return intFormater6.format(n.longValue());
	}
	
	public String formatIdTransaccion(String idContrato, BigDecimal correlativo) {
		int LARGO_PARTE_ID_CONTRATO = 5;
		String parteContrato = idContrato.substring(0, LARGO_PARTE_ID_CONTRATO);
		String parteCorrelativo = String.valueOf(correlativo);
		char[] idArray = {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'};
		for (int i = 0; i < parteContrato.length(); i++)
			idArray[i] = parteContrato.charAt(i);
		for (int i = parteCorrelativo.length()-1; i >= 0; i--)
			idArray[(idArray.length-(parteCorrelativo.length()-i))] = parteCorrelativo.charAt(i);
		return String.valueOf(idArray);
	}
	
	public String decodeURL(String str) throws UnsupportedEncodingException {
		if (str == null)
			return null;
		return URLDecoder.decode(str, Constants.CHARSET);	
	}

	public String encodeURL(String str) throws UnsupportedEncodingException {
		if (str == null)
			return null;
		return URLEncoder.encode(str, Constants.CHARSET);	
	}
	
	public Date parseMsgDate(String s) throws java.text.ParseException {
		Date d = null;
		if (s == null || s.trim().equals("")) 
			return null;
		else 
			d = dateMsgFormat.parse(s);
		return d;
	}
	
	public Date parseMsgDatetime(String s) throws java.text.ParseException {
		Date d = null;
		if (s == null || s.trim().equals(""))
			return null;
		else 
			d = datetimeMsgFormat.parse(s);
		return d;
	}

}
