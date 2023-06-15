package cl.laaraucana.simat.utiles;

import java.text.DecimalFormat;

public class ManejoEspacios {

	private static DecimalFormat montoFormato = new DecimalFormat("#,###.##");

	/**
	 * metodo que recibe un Strig con formato DecimalFormat("#,###.##") 
	 * y elimina las comas y decimales entregando como respuesta un valor String
	 * completado conceros a la izquierda segun el "largo" recibido como parametro.
	 * **/
	public static String getCompletaCeros(String in, int largo) throws Exception {
		//montoFormato.setDecimalFormatSymbols(new DecimalFormatSymbols(new Locale("es")));
		String[] aux;
		String number = "";
		aux = in.replaceAll(",", "").split("\\.");
		number = ManejoEspacios.rellenarCampos(aux[0], largo, "0");
		return number;
	}

	public static String getCompletaBlancos(String in, int largo) throws Exception {
		return ManejoEspacios.rellenarCampos(in, largo, " ");
	}

	public static String getTrim(String in, int largo) throws Exception {
		return in.trim();
	}

	private static String rellenarCampos(String entrada, String nombre, int largo, String caracter) throws RuntimeException {
		String espacios = "";

		if (entrada == null) {
			throw new RuntimeException("Campo '" + nombre + "' no inicializado");
		} else if (entrada.length() > largo) {
			throw new RuntimeException("Campo " + nombre + " es mayor de " + largo + " caracteres. (" + entrada.length() + ")");
		} else {

			if (entrada.length() < largo) {
				for (int i = 0; i < (largo - entrada.length()); i++) {
					espacios += caracter;
				}
				if (caracter.equals("0"))
					return espacios + entrada;
				else
					return entrada + espacios;
			}
		}
		return entrada;
	}

	private static String rellenarCampos(String entrada, int largo, String caracter) throws Exception {
		String espacios = "";
		if (entrada == null) {
			entrada = "";
		} else if (entrada.length() < largo) {
			for (int i = 0; i < (largo - entrada.length()); i++) {
				espacios += caracter;
			}
			if (caracter.equals("0"))
				return espacios + entrada;
			else
				return entrada + espacios;
		} else if (entrada.length() > largo) {
			throw new Exception("El largo del campo supera el máximo permitido");
		}
		return entrada;
	}

	private static String quitarTildes(String entrada) {
		String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
		String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
		String output = entrada;

		for (int i = 0; i < original.length(); i++) {
			output = output.replace(original.charAt(i), ascii.charAt(i));
		}
		return output;
	}
}
