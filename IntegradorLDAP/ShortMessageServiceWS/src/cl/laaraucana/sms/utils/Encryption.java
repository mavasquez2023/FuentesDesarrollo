package cl.laaraucana.sms.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

public class Encryption {

	private static final Logger logger = Logger.getLogger(Encryption.class);
	private static final String privateKey = Configuration.getProperty("crypto.key");
	private static SecretKeySpec secretKey;

	public void setKey(String myKey) {
		MessageDigest sha;
		try {
			byte[] key = myKey.getBytes();
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public String AESEncrypt(String strToEncrypt) {
		return AESEncrypt(strToEncrypt, privateKey);
	}

	public String AESDecrypt(String strToDecrypt) {
		return AESDecrypt(strToDecrypt, privateKey);
	}

	public String AESEncrypt(String strToEncrypt, String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();
			return new String(base64.encode(cipher.doFinal(strToEncrypt.getBytes())));
		} catch (Exception e) {
			logger.info("Encryption.AESEncrypt, error while encrypting" + e.toString());
		}
		return null;
	}

	public String AESDecrypt(String strToDecrypt, String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();
			return new String(cipher.doFinal((byte[]) base64.decode(strToDecrypt)));
		} catch (Exception e) {
			logger.info("Encryption.AESDecrypt error while decrypting" + e.toString());
		}
		return null;
	}

	public String base64Encode(String input) {
		byte[] encodedAsBytes = input.getBytes();
		org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();
		return new String(base64.encode(encodedAsBytes));
	}

	public String base64Decode(String secret) {
		try {
			org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();
			return new String((byte[]) base64.decode(secret));
		} catch (Exception e) {
			logger.info("Encryption.base64Decode error while decoding" + e.toString());
		}
		return null;
	}
}