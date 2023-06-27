
package cl.araucana.bienestar.bonificaciones.common;

/**
 * @author Alejandro Sepúlveda
 */
public class Util {
	
	public static boolean validateRut(String rut, String dv) {

		char dvr = '0';
		int suma = 0;
		int mul  = 2;
		for (int i=rut.length()-1; i >= 0; i--) {
			suma = suma + Character.digit(rut.charAt(i),10) * mul;
			if (mul == 7) mul = 2;
			else mul++;
		}
		int res = suma % 11;
		if (res==1) dvr = 'k';
		else if (res==0) dvr = '0';
		else {
			  int dvi = 11-res;
			  dvr = Character.forDigit(dvi,10);
		}

		if (dvr!=Character.toLowerCase(dv.charAt(0))){
			  return false;
		}
		 return true;		// rut is valid
	}

}
