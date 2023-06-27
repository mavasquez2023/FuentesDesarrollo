package com.microsystem.lme.helper;

import java.io.File;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*Implementacion de la clase Helper. 
 * Contiene implementaciones de funciones utiles para ser usadas en todo el sistema.*/
public class Helper {

	final static BigInteger ZERO = new BigInteger("0");

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
}
