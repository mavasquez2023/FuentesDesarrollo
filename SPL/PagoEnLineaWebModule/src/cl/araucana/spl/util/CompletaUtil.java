package cl.araucana.spl.util;

public class CompletaUtil {
	
	/**
	 * Completa con espacios a la derecha o con 0 a la izquierda
	 * @param entrada
	 * @param largo
	 * @param caracter
	 * @return
	 * @throws Exception
	 */
	public static String rellenarCampos(String entrada, int largo, String caracter) throws Exception {
		String espacios = "";
		String entrada2 = entrada.trim();
		if(entrada == null){
			entrada2  = "";
		}
		if (entrada2.length() < largo) {
			for (int i = 0; i < (largo - entrada2.length()); i++) {
				espacios += caracter;
			}
			if (caracter.equals("0"))
				return espacios + entrada2;
			else
				return entrada2 + espacios;
		}else if(entrada2.length() > largo){
			throw new Exception("El largo del campo supera el máximo permitido");
		}
		return entrada2;
	}
	
	/**
	 * Completa con 0 a la izquierda para variables de entrada tipo entero
	 * @param entrada
	 * @param largo
	 * @return
	 * @throws Exception
	 */
	public static String rellenarCampos(int entrada, int largo) throws Exception {
		String espacios = "";
		String caracter = "0";
		String strEntrada = String.valueOf(entrada);
		if (strEntrada.length() < largo) {
			for (int i = 0; i < (largo - strEntrada.length()); i++) {
				espacios += caracter;
			}
			return espacios + entrada;
		}else if(strEntrada.length() > largo){
			throw new Exception("El largo del campo supera el máximo permitido");
		}
		return strEntrada;
	}
}
