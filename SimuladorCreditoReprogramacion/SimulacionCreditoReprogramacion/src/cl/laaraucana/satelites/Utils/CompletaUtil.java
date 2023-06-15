package cl.laaraucana.satelites.Utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CompletaUtil {
	
	public CompletaUtil() {
		super();
	}

	public static boolean isEmpty(String dato) {

		if (dato != null && dato.toString().trim().length() > 0) {
			return false;
		} else {
			return true;
		}
	}

	public static double evalNum(String dato) {

		try {
			if (dato != null && dato.length() > 0) {
				dato = dato.replaceAll(",", ".");
				return new Double(dato).doubleValue();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public static String quitaCerosIzqEntero(String dato) {

		if (!isEmpty(dato)) {

			try {

				BigInteger bi = new BigInteger(dato);
				return String.valueOf(bi.intValue());
			} catch (Exception e) {
				// ignore
			}

		}

		return null;
	}


	public static String quitaCerosIzqString(String dato) {
		for (int i=0;i<dato.length();i++) {
			if (!dato.substring(i,i+1).equals("0")) {
				return dato.substring(i);
			}
		}
		return "0";
	}
	
	public static String quitaCerosIzqDecimal(String dato) {

		if (!isEmpty(dato)) {

			try {

				BigDecimal bd = new BigDecimal(dato);
				return String.valueOf(bd.doubleValue());
			} catch (Exception e) {
				// ignore
			}

		}

		return null;
	}

	public static String llenaConCeros(String dato, int largoFinal,
			boolean izquierda) {
		if(dato == null){
			dato = "";
		}
		String salida = dato;
		//if (!isEmpty(dato)) {
			for (int i = dato.length(); i < largoFinal; i++) {
				if (izquierda) {
					salida = "0" + salida;
				} else {
					salida = salida + "0";
				}
			}
		//}
		return salida;
	}

	public static String llenaConEspacios(String dato, int largoFinal,
			boolean izquierda) {
		String salida = dato;
		//if (!isEmpty(dato)) {
			for (int i = dato.length(); i < largoFinal; i++) {
				if (izquierda) {
					salida = " " + salida;
				} else {
					salida = salida + " ";
				}
			}
		//}
		return salida;
	}
	
	public static String llenaVacioConEspacios(int largoFinal) {
		String salida = "";
		
			for (int i = 0; i < largoFinal; i++) {
					salida += " ";
			}
		return salida;
	}

	public static String formateadorDuroParaNumeros(String valor, int cantDecimales) {
		String ret = "";
		String ent = valor.trim();
		String dec = "0";
		if (ent.contains(".")) {
			dec = ent.substring(ent.indexOf(".")+1);
			ent = ent.substring(0,ent.indexOf("."));
		}
		dec += "000000000000000000000";
		int putPoint = 1;
		for (int i=ent.length()-1;i>=0;i--) {
			ret = ent.substring(i,i+1)+ret;
			if (putPoint++ == 3 && i>0) {
				putPoint = 1;
				ret = "."+ret;
			}
		}
		if (cantDecimales > 0) {
			ret += "," + dec.substring(0,cantDecimales);
		}
//		System.out.println("val:"+valor+";cntDec:"+cantDecimales+";ent:" + ent+";dec:" + dec+";RET:"+ret);
		return "=\""+ret+"\"";
	}
	
	public static String procesaRut(String rut) {
		String salida = null;
		if (rut != null) {
			int i = rut.indexOf("-");
			if (i > 0) {
				salida = rut.substring(0, i);
			} else {
				salida = rut.trim();
			}
		}
		return salida;
	}

	/**
	 * Metodo que recibe un rut de tipo string de largo 10 con -, y que devuelve
	 * un rut formateado con puntos y digito verificador -
	 */
	public static String rutFormateado(String rut) {
		String rutFormateado = "";
		String verificador = "";
		String rutSinVerific = "";
		String rutSinFormato = "";
		int rut1 = Integer.parseInt(procesaRut(rut));
		rut = Integer.toString(rut1);

		if ((rut.length() == 10) || (rut.length() == 9) || (rut.length() == 8)
				|| (rut.length() == 7)) {

			if (rut.indexOf("-") > 0) {
				rutSinFormato = rut;
				rutSinVerific = rut.substring(0, rut.length() - 2);
				verificador = rut.substring(rut.length(), rut.length());
			} else {
				rutSinFormato = rut;
				verificador = generaVerificador(rut);
			}

			if ((rut.length() == 10) && rutSinFormato != "") {
				rutFormateado = rutSinFormato.substring(0, 2) + "."
						+ rutSinFormato.substring(2, 5) + "."
						+ rutSinFormato.substring(5, 8) + "-" + verificador;

			} else if ((rut.length() == 9) && rutSinFormato != "") {
				rutFormateado = rutSinFormato.substring(0, 2) + "."
						+ rutSinFormato.substring(2, 5) + "."
						+ rutSinFormato.substring(5, 8) + "-" + verificador;

			} else if ((rut.length() == 8) && rutSinFormato != "") {
				rutFormateado = rutSinFormato.substring(0, 2) + "."
						+ rutSinFormato.substring(2, 5) + "."
						+ rutSinFormato.substring(5, 8) + "-" + verificador;

			} else if ((rut.length() == 7) && rutSinFormato != "") {
				verificador = generaVerificador("0" + rut);
				rutFormateado = rutSinFormato.substring(0, 1) + "."
						+ rutSinFormato.substring(1, 4) + "."
						+ rutSinFormato.substring(4, 7) + "-" + verificador;

			}

			return rutFormateado;
		} else {
			rutFormateado = rut;
			return rutFormateado;
		}

	}

	// Genera Digito verificador a partir de String rut
	private static String generaVerificador(String rut) {
		int verific = 0;
		String verificador = "";

		if ((rut.length() >= 8)) {
			verific = (Integer.parseInt(rut.substring(0, 1)) * 8
					+ Integer.parseInt(rut.substring(1, 2)) * 9
					+ Integer.parseInt(rut.substring(2, 3)) * 4
					+ Integer.parseInt(rut.substring(3, 4)) * 5
					+ Integer.parseInt(rut.substring(4, 5)) * 6
					+ Integer.parseInt(rut.substring(5, 6)) * 7
					+ Integer.parseInt(rut.substring(6, 7)) * 8 + Integer
					.parseInt(rut.substring(7, 8)) * 9) % 11;
		} else if ((rut.length() == 7)) {
			verific = (Integer.parseInt(rut.substring(0, 1)) * 2
					+ Integer.parseInt(rut.substring(1, 2)) * 7
					+ Integer.parseInt(rut.substring(2, 3)) * 6
					+ Integer.parseInt(rut.substring(3, 4)) * 5
					+ Integer.parseInt(rut.substring(4, 5)) * 4
					+ Integer.parseInt(rut.substring(5, 6)) * 3 + Integer
					.parseInt(rut.substring(6, 7)) * 2) % 11;
		}
		verificador = Integer.toString(verific);
		if (verific == 10) {
			verificador = "K";
		}
		return verificador;
	}

	public static java.util.Date fechaDiasAtras(int dias) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -dias);
		return cal.getTime();
	}

	public static String formatDate(String date, String fmtComoViene,
			String fmtComoSale) {
		SimpleDateFormat patron = new SimpleDateFormat(fmtComoSale);
		SimpleDateFormat patron2 = new SimpleDateFormat(fmtComoViene);
		CharSequence fechaConCeros = "0000";
		String fechaConFormato = "";
		if (!date.contains(fechaConCeros) && date != null && !date.equals("")) {
			try {

				fechaConFormato = patron.format(new Date(patron2.parse(
						date).getTime()));
			} catch (ParseException e) {
				// e.printStackTrace();
			}
		}
		return fechaConFormato;
//		return date;
	}

	public static Timestamp strToTimeStap(String date, String fmtComoViene) {

		SimpleDateFormat patron = new SimpleDateFormat(fmtComoViene);

		Timestamp timestamp;
		try {
			timestamp = new Timestamp(patron.parse(date).getTime());
			return timestamp;
		} catch (ParseException e) {
			// ignore
			// e.printStackTrace();
		}
		return null;
	}

//	public static void main(String[] args) {
//
//		System.out.println(Utils.formatDate("103237", "hhmmss", "hh:mm"));
//
//		System.out.println(Utils.quitaCerosIzqEntero("00000001"));
//		System.out.println(Utils.quitaCerosIzqDecimal("0.0000000E+00"));
//		System.out.println(Utils.quitaCerosIzqDecimal("0.0030000E+00"));
//		System.out.println(Utils.quitaCerosIzqDecimal("00000.0030000E+03"));
//		System.out.println(Utils.quitaCerosIzqDecimal("0001.0000000E+03"));
//		
//		System.out.println("Largo resultado: "+Utils.llenaConEspacios("", 13, true).length());
//
//	}

	public static String fechaMenosDias(Date fecha, int dias) {

		SimpleDateFormat patron = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(fecha.getTime());
		cal.add(Calendar.DATE, -dias);

		String fechaConFormato = patron.format(cal.getTime());

		return fechaConFormato;
	}

	public static String fechaMasDias(Date fecha, int dias) {

		SimpleDateFormat patron = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(fecha.getTime());
		cal.add(Calendar.DATE, dias);

		String fechaConFormato = patron.format(cal.getTime());

		return fechaConFormato;
	}

}
