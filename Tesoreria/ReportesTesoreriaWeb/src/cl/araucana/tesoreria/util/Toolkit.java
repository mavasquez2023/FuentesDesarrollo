package cl.araucana.tesoreria.util;

public class Toolkit {
	public static boolean validarRUT(String rut) {
		if (!rut.equals("") && rut.length() >= 3) {
			String rutNumero = rut.substring(0, rut.length() - 2);
			String rutDV = rut.substring(rut.length() - 1, rut.length());
			return validarRUT(Integer.parseInt(rutNumero), rutDV.toUpperCase().charAt(0));
		} else
			return false;
	}

	public static boolean validarRUT(int rut, char dv) {
		int m = 0, s = 1;
		for (; rut != 0; rut /= 10) {
			s = (s + rut % 10 * (9 - m++ % 6)) % 11;
		}
		return dv == (char) (s != 0 ? s + 47 : 75);
	}

	public static String dvRUT(String rut) {
		return String.valueOf(dvRUT(Integer.valueOf(rut)));
	}

	public static char dvRUT(int rut) {
		int m = 0, s = 1;
		for (; rut != 0; rut /= 10) {
			s = (s + rut % 10 * (9 - m++ % 6)) % 11;
		}
		return (char) (s != 0 ? s + 47 : 75);
	}
}
