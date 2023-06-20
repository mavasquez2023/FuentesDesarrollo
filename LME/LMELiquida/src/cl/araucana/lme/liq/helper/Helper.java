package cl.araucana.lme.liq.helper;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*Implementacion de la clase Helper. 
 * Contiene implementaciones de funciones utiles para ser usadas en todo el sistema.*/
public class Helper {
	
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat shf = new SimpleDateFormat("HHmmss");
	private static SimpleDateFormat sdm = new SimpleDateFormat("yyyyMM");
	
	
	final static BigInteger ZERO = new BigInteger("0");

	public static void main(String[] args) {
		System.out.println(getFechaContinua("20161116"));
	}
	
	/*Funcion que obtiene el dia siguiente a la fecha hasta, fecha= AAAAMMDD.*/
	public static String getFechaContinua(String fecha) {

		String fechaRes = "";
		Calendar cal = Calendar.getInstance();

		try {
			Date date = sdf.parse(fecha);
			cal.setTime(date);
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-1);
			date = cal.getTime();
			fechaRes = sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return fechaRes;
	}
	
	/*Funcion que obtiene la fecha de vigencia.*/
	public static String obtenerFechaVigencia(String fecha) {

		String fechaRes = "";
		Date date = new Date();
		Calendar cal = Calendar.getInstance();

		String DATE_FORMAT = "dd/MM/yyyy"; // Formato en el que se debe recibir la cadena
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

		try {
			date = sdf.parse(fecha);
			cal.setTime(date);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 2);
			date = cal.getTime();
			fechaRes = sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return fechaRes;
	}

	public static String dv(int num) {
		int M = 0, S = 1, T = num;
		for (; T != 0; T /= 10)
			S = (S + T % 10 * (9 - M++ % 6)) % 11;
		char r = (char) (S != 0 ? S + 47 : 75);
		return String.valueOf(r);
	}

	public static String dv(String numLicen) {
		BigInteger num = new BigInteger(numLicen);
		return dv(num);
	}

	public static String dv(BigInteger num) {
		long M = 0, S = 1, T = num.longValue();
		for (; T != 0; T /= 10)
			S = (S + T % 10 * (9 - M++ % 6)) % 11;
		char r = (char) (S != 0 ? S + 47 : 75);
		return String.valueOf(r);
	}

	public static String digitoVerificadorRut(String strRut) {
		int rut = 0;
		int s = 0;
		String l_dv = "";

		rut = Integer.parseInt(strRut);

		if (strRut.trim() == "0") {
			return "0";
		}

		for (int i = 2; i < 8; i++) {
			s = s + (rut % 10) * i;
			rut = (rut - (rut % 10)) / 10;
		}

		s = s + (rut % 10) * 2;
		rut = (rut - (rut % 10)) / 10;
		s = s + (rut % 10) * 3;
		rut = (rut - (rut % 10)) / 10;
		s = 11 - (s % 11);

		if (s == 10)
			l_dv = "K";
		else if (s == 11)
			l_dv = "0";
		else
			l_dv = s + "";

		return (l_dv);
	}

	public static String separadorDeMiles(String cadena) {
		String resultado = "";
		int count = 0;

		for (int i = cadena.length(); i >= 1; i--) {
			count++;

			if (count == 3) {
				resultado = "." + cadena.charAt(i - 1) + resultado;
				count = 0;
			} else {
				resultado = cadena.charAt(i - 1) + resultado;
			}

		}

		if (resultado.charAt(0) == '.' && resultado.length() > 0) {
			resultado = resultado.substring(1, resultado.length());
		}

		return resultado;
	}

	/*Metodo para validar correos electrónicos.*/
	public static boolean isEmail(String correo) {
		Pattern pat = null;
		Matcher mat = null;
		pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
		mat = pat.matcher(correo);
		if (mat.find()) {
			return true;
		} else {
			return false;
		}
	}

	public static String paddingString(String s, int n, char c, boolean paddingLeft) {

		if (s == null) {
			char[] ch2 = new char[n];
			Arrays.fill(ch2, c);
			String resp = new String(ch2);
			return resp;
		}

		int add = n - s.length();
		if (add <= 0) {
			return s;
		}

		StringBuffer str = new StringBuffer(s);
		char[] ch = new char[add];
		Arrays.fill(ch, c);
		if (paddingLeft) {
			str.insert(0, ch);
		} else {
			str.append(ch);
		}

		return str.toString();
	}

	/*Función que elimina un archivo dada la ruta especifica.*/
	public static int deleteFile(String rutaArchivo) {

		//String ruta = rutaArchivo;
		File file = new File(rutaArchivo);

		if (file.delete()) {

			return 1;

		} else {

			return 0;
		}
	}

	/**
	  * Encode a String using Base64 using the default platform encoding
	  **/
	public final static String encodeBase64(String s) {
		return encodeBase64(s.getBytes());
	}

	/**
	 *  Encode some data and return a String.
	 */
	public final static String encodeBase64(byte[] d) {
		if (d == null)
			return null;
		byte data[] = new byte[d.length + 2];
		System.arraycopy(d, 0, data, 0, d.length);
		byte dest[] = new byte[(data.length / 3) * 4];

		// 3-byte to 4-byte conversion
		for (int sidx = 0, didx = 0; sidx < d.length; sidx += 3, didx += 4) {
			dest[didx] = (byte) ((data[sidx] >>> 2) & 077);
			dest[didx + 1] = (byte) ((data[sidx + 1] >>> 4) & 017 | (data[sidx] << 4) & 077);
			dest[didx + 2] = (byte) ((data[sidx + 2] >>> 6) & 003 | (data[sidx + 1] << 2) & 077);
			dest[didx + 3] = (byte) (data[sidx + 2] & 077);
		}

		// 0-63 to ascii printable conversion
		for (int idx = 0; idx < dest.length; idx++) {
			if (dest[idx] < 26)
				dest[idx] = (byte) (dest[idx] + 'A');
			else if (dest[idx] < 52)
				dest[idx] = (byte) (dest[idx] + 'a' - 26);
			else if (dest[idx] < 62)
				dest[idx] = (byte) (dest[idx] + '0' - 52);
			else if (dest[idx] < 63)
				dest[idx] = (byte) '+';
			else
				dest[idx] = (byte) '/';
		}

		// add padding
		for (int idx = dest.length - 1; idx > (d.length * 4) / 3; idx--) {
			dest[idx] = (byte) '=';
		}
		return new String(dest);
	}

	public static BigInteger toBigInteger(String value) {
		return null == value || "".equals(value.trim()) ? ZERO : new BigInteger(value);
	}

	public static String dataTruncation(String str, int i) {
		return str.length() > i ? str.substring(0, i) : str;
	}

	public static BigInteger toBigInteger(Integer value) {
		return null == value ? ZERO : new BigInteger(value.toString());
	}

	public static BigInteger toBigInteger(Short value) {
		return null == value ? ZERO : new BigInteger(value.toString());
	}

	public static BigInteger toBigInteger(BigDecimal value) {
		return null == value ? ZERO : new BigInteger(value.toString());
	}

	public static String toString(String value) {
		return null == value ? "" : value.trim();
	}

	public Calendar assingCalendar(String value) {
		return null;
	}

	public static Date dateCal() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		cal.setTime(date);
		return date;
	}
	
	public static String reformateaGlosaEstado(String glosaEstado) {
		if (null != glosaEstado && !glosaEstado.equals("")) {
			glosaEstado = glosaEstado.replace('\'', ' ');
			if (glosaEstado.length() > 60)
				glosaEstado = glosaEstado.substring(0, 60);
		} else {
			glosaEstado = "Operador no informa la glosa";
		}

		return glosaEstado;
	}
	
	/**
	 * @param rut
	 * @param string
	 * @return
	 */
	public static String separaNumDigRut(String rut, String tipo) {
		String auxRut = "0";
		if (rut != "0") {
			int pos = rut.indexOf("-");
			if (pos > 0) {
				String[] arRut = rut.split("-");
				if (tipo.equals("N"))
					auxRut = arRut[0];
				else
					auxRut = arRut[1];
			}
		}
		return auxRut;
	}
	
	public static String set(String s) {
		return null == s ? "" : s.trim();
	}
	public static String set(BigInteger i) {
		return null == i ? "0" : i.toString();
	}
	public static String set(BigDecimal d) {
		return null == d ? "0" : d.toString();
	}
	
	/**
	 * @param l
	 * @return
	 */
	public static boolean empty(List l) {
		return null == l || l.size() == 0;
	}
	
	/**
	 * @param entidadFechaDesde
	 * @return
	 */
	public static String set(Calendar c) {
		String d = "19000101";
		if (null != c) {
			d = sdf.format(c.getTime());
		}
		return d;
	}
	
	public static String setPeriodo(Calendar c) {
		String d = "1900-01";
		if (null != c) {
			d = sdf2.format(c.getTime());
		}
		return d;
	}
	
	private Calendar cal2(String date) throws ParseException {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		try {

			calendar.setTime(sdm.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// System.out.println("Calendar2 = " + calendar);
		return calendar;
	}
	
	protected static Date date(String formated, String format) throws ParseException {
		if (null == formated || "0".equals(formated.trim()))
			formated = "19000101";

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.parse(formated);
	}

	public static String getSdf() {
		return sdf.format(new Date());
	}

	public static void setSdf(SimpleDateFormat sdf) {
		Helper.sdf = sdf;
	}

	public static String getShf() {
		return shf.format(new Date());
	}

	public static void setShf(SimpleDateFormat shf) {
		Helper.shf = shf;
	}
//	trunca un string <text> hasta <largo>
	public static String truncateText(String text, int length){

		if(text != null && text.length() > length){
			text = text.substring(0,length);
		}
		return text;

	}
}
