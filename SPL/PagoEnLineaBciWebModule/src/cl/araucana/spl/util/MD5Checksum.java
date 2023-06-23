package cl.araucana.spl.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Checksum {

	public static byte[] createChecksum(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest complete = MessageDigest.getInstance("MD5");
		complete.update(data, 0, data.length);
		return complete.digest();
	}

	/**
	 * Obtiene el checksum de un archivo.
	 * 
	 * @param pathArchivo,
	 *            ruta y nombre del archivo
	 * @return String md5
	 * @throws Exception
	 * @author malvarez
	 * @since 1.0 / 28-03-2008
	 */
	public static String getMD5Checksum(byte[] data) throws NoSuchAlgorithmException {
		byte[] b = createChecksum(data);
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}
}