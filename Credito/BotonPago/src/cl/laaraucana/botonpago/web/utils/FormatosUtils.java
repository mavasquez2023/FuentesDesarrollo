package cl.laaraucana.botonpago.web.utils;

public class FormatosUtils {

	public static String formatoSalida(String cadena) {
		String salida = "";
		if(null ==  cadena) {
			return null;
		}
		if(cadena.isEmpty()) {
			return null;
		}
	    char caracter;
	    boolean bandera = true;
	    for (int i = 0; i < cadena.length(); i++) {
	        caracter = (char) cadena.charAt(i);
	        if (caracter == ' ') {
	        	salida+=(caracter);
	            bandera = true;
	        } else {
	            if (bandera == true) {
	                if (Character.isLowerCase(caracter)) {
	                    caracter = (char) (caracter - 'a' + 'A');
	                    salida+=(caracter);
	                } else {
	                	salida+=(caracter);
	                }
	                bandera = false;
	            } else {
	                if (Character.isUpperCase(caracter)) {
	                    caracter = (char) (caracter + 'a' - 'A');
	                    salida+=(caracter);
	                } else {
	                	salida+=(caracter);
	                }
	            }
	        }
	    }
		return salida;
	}
	
	public static String formatoFechaHora(String fechaHora) {
		String fecha = fechaHora.split(" ")[0];
		String hora = fechaHora.split(" ")[1];
		return formatoFechaHora(fecha, hora);
	}
	
	public static String formatoFechaHora(String fecha, String hora) {
		return formatoFecha(fecha) + " " + formatoHora(hora);
	}

	private static String formatoFecha(String fecha) {
		fecha = fecha.replaceAll("-", "/");
		String dia = fecha.split("/")[0];
		String mes = fecha.split("/")[1];
		String agno = fecha.split("/")[2];
		if(agno.length()<4) {
			int anio = Integer.parseInt(agno);
			if (anio < 40) {
				agno = "20" + agno;
			} else {
				agno = "19" + agno;
			}
		}
		return dia + "/" + mes + "/" + agno;
	}
	
	private static String formatoHora (String hora) {
		if (hora.length()>5) {
		 return hora.substring(0, 5);
		}else {
			return hora;
		}
	}
	
}
