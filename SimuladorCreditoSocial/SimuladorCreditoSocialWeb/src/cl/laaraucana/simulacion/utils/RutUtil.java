package cl.laaraucana.simulacion.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RutUtil {
	
	public static boolean IsRutValido(String rutEntrada) {

		rutEntrada = rutEntrada.replace(".", "").toUpperCase();

		if (PatronRut(rutEntrada) && rutEntrada.length() <= 10) {
			String[] soloRut = rutEntrada.split("-");
			int rut = Integer.parseInt(soloRut[0]);
			char dv = soloRut[1].charAt(0);
			return ValidarRut(rut, dv);
		}
		return false;
	}

	private static boolean ValidarRut(int rut, char dv) {
		int m = 0, s = 1;
		for (; rut != 0; rut /= 10) {
			s = (s + rut % 10 * (9 - m++ % 6)) % 11;
		}
		return dv == (char) (s != 0 ? s + 47 : 75);
	}

	private static boolean PatronRut(String rutEntrada) {

		Pattern patron = Pattern.compile("\\b\\d{1,8}\\-[K|k|0-9]");
		Matcher encaja = patron.matcher(rutEntrada);
		if (encaja.find())
			return true;
		else
			return false;
	}
	 
	public static char obtenerDigitoVerificador(int rut) {
		int m = 0, s = 1;
		for (; rut != 0; rut /= 10) {
			s = (s + rut % 10 * (9 - m++ % 6)) % 11;
		}
		char dv = (char) (s != 0 ? s + 47 : 75);
		return dv;
	}

	public static String formatearRut(String rut) {
		rut = rut.replace("-", "").replace(".", "");
		String dv = rut.substring(rut.length() - 1, rut.length());
		rut = Utils.formateaDobleSinDecimal(Double.parseDouble(rut.substring(0,
				rut.length() - 1)));
		rut = rut + "-" + dv;

		return rut;
	}
	
	public static Long getRutLong(String rut) {
		rut = rut.replace(".", "");
		Long rutL =  Long.parseLong(rut.substring(0, (rut.length()-2)));
		return rutL;
	}
}
