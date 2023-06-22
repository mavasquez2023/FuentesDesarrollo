package cl.laaraucana.util.objectvalidate;

public class Funciones {

	public static String rellenar(String entrada, String slargo, String caracter, String truncate) throws Exception {
		if (entrada == null) {
			entrada = caracter;
		}
		String espacios = "";
		Integer largo = Integer.valueOf(slargo);
		if (entrada.length() > largo) {
			if (truncate != null && truncate.equals("false")) {
				throw new Exception("ERROR " + entrada + " LARGO EXCEDE EL MAXIMO");
			}
			entrada = entrada.trim().substring(0, largo);
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

	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	public static boolean isNumeric(String string)
	{
	    if (string == null)
	    {
	        //throw new NullPointerException("El string no puede ser nulo");
	    	return false;
	    }
	    final int len = string.length();
	    if (len == 0)
	    {
	        return false;
	    }
	    for (int i = 0; i < len; ++i)
	    {
	        if (!Character.isDigit(string.charAt(i)))
	        {
	            return false;
	        }
	    }
	    return true;
	}

	public static boolean notNullEmpty(String str) {
		if (str != null && !str.trim().isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean maxLength(String string, String max) {
		if (string.length() > Integer.valueOf(max)) {
			return false;
		} else {
			return true;
		}

	}

	public static boolean validarRut(int rut, char dv) {
		int m = 0, s = 1;
		for (; rut != 0; rut /= 10) {
			s = (s + rut % 10 * (9 - m++ % 6)) % 11;
		}
		if (dv == (char) (s != 0 ? s + 47 : 75)) {
			return true;
		}
		return false;
	}

	//	public static String truncate(String str, String i) {
	//		return str.length() > Integer.valueOf(i) ? str.substring(0, Integer.valueOf(i)) : str;
	//	}

	public static String replace(String valor, String oldChar, String newChar) {
		if (valor != null) {
			return valor.replace(oldChar, newChar);
		}
		return valor;
	}

	public String changeCase(String str, String to) {
		if (str != null && to.equals("toUpperCase")) {
			return str.toUpperCase();
		} else if (str != null && to.equals("toLowerCase")) {
			return str.toLowerCase();
		}
		return str;
	}

}
