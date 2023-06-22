package cl.laaraucana.util.objectvalidate;

import java.util.Arrays;
import java.util.Calendar;

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
	
	public static boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
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
		string = (string == null) ? "" : string;

		if (string.length() > Integer.valueOf(max)) {
			return false;
		} else {
			return true;
		}

	}

	public static boolean validarRut(String srut) {
		if (srut == null || srut.isEmpty()) {
			return false;
		}
		srut = srut.replace("-", "").toUpperCase();

		int rut = Integer.valueOf(srut.substring(0, srut.length() - 1));
		char dv = srut.substring(srut.length() - 1).charAt(0);

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

	public boolean validaOperador(String entrada) {
		if (entrada.equals("0") || entrada.equals("2") || entrada.equals("4")) {
			return true;
		}
		return false;
	}

	public static boolean isFechaValida(String date) {
		//formato de entrada AAAAMMDD
		if (date == null || !date.matches("\\d{4}\\d{2}\\d{2}"))
			return false;
		int mes = Integer.parseInt(date.substring(4, 6));
		int anho = Integer.parseInt(date.substring(0, 4));

		if (mes > 12 || anho > 9999)
			return false;

		int dia = Integer.parseInt(date.substring(6, 8));

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, anho);
		calendar.set(Calendar.MONTH, mes - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		if (dia > calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
			return false;
		return true;
	}

	public static boolean isHoraValida(String hora) {
		//formato de entrada AAAAMMDD
		if (hora == null || !hora.matches("\\d{2}\\d{2}\\d{2}"))
			return false;
		int h = Integer.parseInt(hora.substring(0, 2));
		int m = Integer.parseInt(hora.substring(2, 4));
		int s = Integer.parseInt(hora.substring(4, 6));

		if (h > 23 || m > 59 || s > 59)
			return false;
		return true;
	}

	public static boolean valoresPermitidos(String valor, String permitidos) {
		String[] permitidosArray = permitidos.split(";");
		if (valor == null) {
			valor = "";
		}

		if (Arrays.asList(permitidosArray).contains(valor.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean valoresNoPermitidos(String valor, String permitidos) {
		String[] nopermitidosArray = permitidos.split(";");
		if (valor == null) {
			valor = "";
		}

		if (Arrays.asList(nopermitidosArray).contains(valor.trim())) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean mayorACero(String valor) {
		if (Integer.valueOf(valor) > 0) {
			return true;
		}
		return false;
	}

	public static boolean distintoDeCero(String valor) {
		
		return !valor.equals("0");
			
		/*if (Integer.valueOf(valor) != 0) {
			return true;
		}*/
		
		/*String valor2;
		try {
			valor2 = String.valueOf(Integer.valueOf(valor));
		} catch (Exception e) {
			valor2 = valor;
		}
		if("0".equals(valor2)){
			return true;
		}
		
		return false;*/
	}
	
	public static boolean isMesValido(String valor) {
		try {
			if (Integer.valueOf(valor) >= 1 && Integer.valueOf(valor)<=12) {
				return true;
			}
		} catch (NumberFormatException e) {
		}
		return false;
	}
	
	public static boolean isAnioValido(String valor) {
		try {
			Calendar calendar = Calendar.getInstance();
			if (Integer.parseInt(valor)<=calendar.get(Calendar.YEAR)) {
				return true;
			}
		} catch (NumberFormatException e) {
		}
		return false;
	}
	
	public static boolean isPeriodoValido(String valor) {
		try {
			if(valor.length()==6){
				String anio= valor.substring(0, 4);
				String mes= valor.substring(4, 6);
				Calendar calendar = Calendar.getInstance();
				if (Integer.parseInt(anio)<=calendar.get(Calendar.YEAR)) {
					if (Integer.valueOf(mes) >= 1 && Integer.valueOf(mes)<=12) {
						return true;
					}
				}
			}
		} catch (NumberFormatException e) {
		}
		return false;
	}
}
