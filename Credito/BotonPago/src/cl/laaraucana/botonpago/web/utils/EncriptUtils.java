package cl.laaraucana.botonpago.web.utils;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author usist54 clase utilizada para encriptar y desencriptar en triple
 *         DES/CBC/padingPKCS5
 */
public class EncriptUtils {

	private static String TRIPLE_DES_TRANSFORMATION = "DESede/CBC/PKCS5Padding";
	private static String ALGORITHM = "DESede";

	private static byte[] generarVector() {
		byte[] vector = new byte[8];
		Random r = new Random();
		r.nextBytes(vector);
		return vector;
	}

	public static HashMap<String, byte[]> encode(byte[] input, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException {
		HashMap<String, byte[]> map = new HashMap<String, byte[]>();
		byte[] vector = generarVector();
		final IvParameterSpec iv = new IvParameterSpec(vector);
		SecretKey keySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
		Cipher encrypter = Cipher.getInstance(TRIPLE_DES_TRANSFORMATION);
		encrypter.init(Cipher.ENCRYPT_MODE, keySpec, iv);

		map.put("vector", vector);
		map.put("encriptado", encrypter.doFinal(input));

		return map;
	}

	public static byte[] decode(byte[] input, String key, byte[] vector) throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchProviderException,
			NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
		final IvParameterSpec iv = new IvParameterSpec(vector);
		SecretKey keySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
		Cipher decrypter = Cipher.getInstance(TRIPLE_DES_TRANSFORMATION);
		decrypter.init(Cipher.DECRYPT_MODE, keySpec, iv);
		return decrypter.doFinal(input);
	}
}
