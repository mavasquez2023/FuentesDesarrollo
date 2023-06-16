package cl.ccaf.previpass.util;

public class Utiles {

	public static int generarClave(String rutEmpresa,String email){
		double clave =  Math.random()*10000;
		return (int) clave;
	}

}
