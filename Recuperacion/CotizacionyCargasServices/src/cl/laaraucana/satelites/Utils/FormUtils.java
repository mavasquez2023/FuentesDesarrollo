package cl.laaraucana.satelites.Utils;

public class FormUtils {
	
	public static boolean isLong(String numero) {
		boolean out = false;
		try {
			Long.parseLong(numero);
			out = true;
		} catch (Exception e) {
			out = false;
		}
		return out;
	}
	
	public static boolean isInt(String numero) {
		boolean out = false;
		try {
			Integer.parseInt(numero);
			out = true;
		} catch (Exception e) {
			out = false;
		}
		return out;
	}

}
