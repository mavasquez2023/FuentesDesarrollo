package cl.laaraucana.rendicionpagonomina.services;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import cl.laaraucana.rendicionpagonomina.utils.Configuraciones;


@Service
public class EncryptServiceImpl implements EncryptService {

	private static String encryptionKey;
	private static String characterEncoding;
	private static String cipherTransformation;
	private static String CRYPT_ALGORITHM;

	/**
	 * Method for Encrypt Plain String Data
	 * 
	 * @param plainText
	 * @return encryptedText
	 */

	private static void init() {

		encryptionKey = Configuraciones.getConfig("encryptionKey");
		characterEncoding = Configuraciones.getConfig("characterEncoding");
		cipherTransformation = Configuraciones.getConfig("cipherTransformation");
		CRYPT_ALGORITHM = Configuraciones.getConfig("CRYPT_ALGORITHM");
	}

	public String encrypt(String plainText) {
		String encryptedText = "";

		init();

		try {
			Cipher cipher = Cipher.getInstance(cipherTransformation);
			byte[] key = encryptionKey.getBytes(characterEncoding);
			SecretKeySpec secretKey = new SecretKeySpec(key, CRYPT_ALGORITHM);
			IvParameterSpec ivparameterspec = new IvParameterSpec(key);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
			byte[] cipherText = cipher.doFinal(plainText.getBytes(characterEncoding));
			encryptedText = DatatypeConverter.printBase64Binary(cipherText);
			// Base64.Encoder encoder = Base64.getEncoder();
			// encryptedText = encoder.encodeToString(cipherText);

		} catch (Exception E) {
			System.err.println("Encrypt Exception : " + E.getMessage());
		}
		return encryptedText;
	}

	/**
	 * Method For Get encryptedText and Decrypted provided String
	 * 
	 * @param encryptedText
	 * @return decryptedText
	 */
	public String decrypt(String encryptedText) {
		String decryptedText = "";

		init();

		try {
			Cipher cipher = Cipher.getInstance(cipherTransformation);
			byte[] key = encryptionKey.getBytes(characterEncoding);
			SecretKeySpec secretKey = new SecretKeySpec(key, CRYPT_ALGORITHM);
			IvParameterSpec ivparameterspec = new IvParameterSpec(key);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
			// Base64.Decoder decoder = Base64.getDecoder();
			// byte[] cipherText = decoder.decode(encryptedText.getBytes("UTF8"));
			byte[] cipherText = DatatypeConverter.parseBase64Binary(encryptedText);
			decryptedText = new String(cipher.doFinal(cipherText), characterEncoding);

		} catch (Exception E) {
			System.err.println("decrypt Exception : " + E.getMessage());
		}
		return decryptedText;
	}

}
