/*
 * Creado el 17-07-2006
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.recursos;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class Rut {
	public static char calculaDV(int rut) {
	    int suma = 0;
	    int digito = 0;
	    int j = 1;
	    char resultado;
	    
	    for (int i = String.valueOf(rut).length(); i >= 0; --i) {
	        ++j;
	        if (j > 7)
	            j = 2;
	        suma += (rut % 10) * j;
	        rut = rut / 10;
	    }
	    // Calculo del valor del digito
	    digito = 11 - suma % 11;
	    if (digito == 10)
	        resultado = 'K';
	    else {
	        if (digito == 11)
	            resultado = '0';
	        else {
	           resultado = String.valueOf(digito).charAt(0);
	        }
	    }
	    return resultado;
	}

	public static boolean validaRut(String rutdv){
		rutdv = rutdv.toUpperCase();
		int punto= rutdv.indexOf(".");
		while (punto >= 0) {
			rutdv = rutdv.substring(0, punto) + rutdv.substring(punto + 1);
			punto= rutdv.indexOf(".");
		}
		int posg = rutdv.indexOf("-");
		if (posg == -1) {
		    posg = rutdv.length() - 1;
		}
		String rut = rutdv.substring(0, posg);
		char dv = rutdv.substring(rutdv.length() - 1).charAt(0);
		char dvok= calculaDV(Integer.parseInt(rut));
		if (dvok!=dv){
			return false;
		}
		return true;
	}
	}


