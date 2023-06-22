package cl.laaraucana.mediopagosilws.utils;

public class Funciones {
	
	//validaciones return boolean parameter String
	
	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static boolean maxLength(String string, int max) {
		if (string.length() > max) {
			return false;
		}
		return true;
	}

	public static boolean validarRut(int rut, char dv) {
		int m = 0, s = 1;
		for (; rut != 0; rut /= 10) {
			s = (s + rut % 10 * (9 - m++ % 6)) % 11;
		}
		return dv == (char) (s != 0 ? s + 47 : 75);
	}
	
	public static String rellenar(String entrada, int largo, String caracter) {
		String espacios = "";
		if (entrada.length() > largo) {
			entrada = entrada.substring(0, largo);
			System.out.println("ERROR");
		}
		if (entrada.length() < largo) {
			for (int i = 0; i < (largo - entrada.length()); i++) {
				espacios += caracter;
			}
			if (caracter.equals("0")) {
				return espacios + entrada;
			} else {
				return entrada + espacios;
			}
		}
		return entrada;
	}


}
