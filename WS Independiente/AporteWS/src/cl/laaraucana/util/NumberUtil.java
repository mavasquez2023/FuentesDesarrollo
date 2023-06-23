package cl.laaraucana.util;

public class NumberUtil {
	
	/**
	 * Quita ceros a la izquierda, no verifica que sea número
	 * @param nro
	 * @return
	 */
	public static String quitaCerozIzq(String nro){
		if (!nro.isEmpty()) {
			return nro.replaceFirst("0*", "");
		}
		return null;
	}
}
