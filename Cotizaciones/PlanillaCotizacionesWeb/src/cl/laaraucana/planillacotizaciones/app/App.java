package cl.laaraucana.planillacotizaciones.app;



public class App {

	public static void main(String[] args) {
		
		String ceros = "000000000";

		String ret = ceros + 32;

		System.out.println(ret.substring(ret.length() - 3, ret.length()));

	}

}
