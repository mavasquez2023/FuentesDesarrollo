/**
 * 
 */
package cl.recursos;

/**
 * @author Usist24
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.NetworkInterface;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Enumeration;
 
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
 
/**
 * @author Vócali Sistemas Inteligentes
 */
public class AESCrypt {
	private static final String JCE_EXCEPTION_MESSAGE = "Por favor asegurate de tener instalado el "
		+ "\"Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files\" "
		+ "(http://java.sun.com/javase/downloads/index.jsp) de aqui puedes descargarlo.";
	private static final String RANDOM_ALG = "SHA1PRNG";
	private static final String DIGEST_ALG = "SHA-256";
	private static final String HMAC_ALG = "HmacSHA256";
	private static final String CRYPT_ALG = "AES";
	private static final String CRYPT_TRANS = "AES/CBC/NoPadding";
	private static final byte[] DEFAULT_MAC =
		{0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef};
	private static final int KEY_SIZE = 32;
	private static final int BLOCK_SIZE = 16;
	private static final int SHA_SIZE = 32;
 
	private final boolean DEBUG;
	private byte[] password;
	private Cipher cipher;
	private Mac hmac;
	private SecureRandom random;
	private MessageDigest digest;
	private IvParameterSpec ivSpec1;
	private SecretKeySpec aesKey1;
	private IvParameterSpec ivSpec2;
	private SecretKeySpec aesKey2;
 
	/*******************
	 * PRIVATE METHODS *
	 *******************/
 
	/**
	 * Prints a debug message on standard output if DEBUG mode is turned on.
	 */
	protected void debug(String message) {
		if (DEBUG) {
			System.out.println("[DEBUG] " + message);
		}
	}
 
	/**
	 * Prints a debug message on standard output if DEBUG mode is turned on.
	 */
	protected void debug(String message, byte[] bytes) {
		if (DEBUG) {
			StringBuilder buffer = new StringBuilder("[DEBUG] ");
			buffer.append(message);
			buffer.append("[");
			for (int i = 0; i < bytes.length; i++) {
				buffer.append(bytes[i]);
				buffer.append(i < bytes.length - 1 ? ", " : "]");
			}
			System.out.println(buffer.toString());
		}
	}
 
	/**
	 * Generates a pseudo-random byte array.
	 * @return pseudo-random byte array of <tt>len</tt> bytes.
	 */
	protected byte[] generateRandomBytes(int len) {
		byte[] bytes = new byte[len];
		random.nextBytes(bytes);
		return bytes;
	}
 
	/**
	 * SHA256 digest over given byte array and random bytes.
 
	 * <tt>bytes.length</tt> * <tt>num</tt> random bytes are added to the digest.
	 *
 
	 * The generated hash is saved back to the original byte array.
 
	 * Maximum array size is {@link #SHA_SIZE} bytes.
	 */
	protected void digestRandomBytes(byte[] bytes, int num) {
		assert bytes.length <= SHA_SIZE;
 
		digest.reset();
		digest.update(bytes);
		for (int i = 0; i < num; i++) {
			random.nextBytes(bytes);
			digest.update(bytes);
		}
		System.arraycopy(digest.digest(), 0, bytes, 0, bytes.length);
	}
 
	/**
	 * Generates a pseudo-random IV based on time and this computer's MAC.
	 *
 
	 * This IV is used to crypt IV 2 and AES key 2 in the file.
	 * @return IV.
	 */
	protected byte[] generateIv1() {
		byte[] iv = new byte[BLOCK_SIZE];
		long time = System.currentTimeMillis();
		byte[] mac = null;
		try {
			Enumeration ifaces = NetworkInterface.getNetworkInterfaces();
			while (mac == null && ifaces.hasMoreElements()) {
				mac = ifaces.nextElement().getHardwareAddress();
			}
		} catch (Exception e) {
			// Ignore.
		}
		if (mac == null) {
			mac = DEFAULT_MAC;
		}
 
		for (int i = 0; i < 8; i++) { 			iv[i] = (byte) (time >> (i * 8));
		}
		System.arraycopy(mac, 0, iv, 8, mac.length);
		digestRandomBytes(iv, 256);
		return iv;
	}
 
	/**
	 * Generates an AES key starting with an IV and applying the supplied user password.
	 *
 
	 * This AES key is used to crypt IV 2 and AES key 2.
	 * @return AES key of {@link #KEY_SIZE} bytes.
	 */
	protected byte[] generateAESKey1(byte[] iv, byte[] password) {
		byte[] aesKey = new byte[KEY_SIZE];
		System.arraycopy(iv, 0, aesKey, 0, iv.length);
		for (int i = 0; i < 8192; i++) {
			digest.reset();
			digest.update(aesKey);
			digest.update(password);
			aesKey = digest.digest();
		}
		return aesKey;
	}
 
	/**
	 * Generates the random IV used to crypt file contents.
	 * @return IV 2.
	 */
	protected byte[] generateIV2() {
		byte[] iv = generateRandomBytes(BLOCK_SIZE);
		digestRandomBytes(iv, 256);
		return iv;
	}
 
	/**
	 * Generates the random AES key used to crypt file contents.
	 * @return AES key of {@link #KEY_SIZE} bytes.
	 */
	protected byte[] generateAESKey2() {
		byte[] aesKey = generateRandomBytes(KEY_SIZE);
		digestRandomBytes(aesKey, 32);
		return aesKey;
	}
 
	/**
	 * Utility method to read bytes from a stream until the given array is fully filled.
	 * @throws IOException if the array can't be filled.
	 */
	protected void readBytes(InputStream in, byte[] bytes) throws IOException {
		if (in.read(bytes) != bytes.length) {
			throw new IOException("Fin de archivo inesperado");
		}
	}
 
	/**************
	 * PUBLIC API *
	 **************/
 
	/**
	 * Builds an object to encrypt or decrypt files with the given password.
	 * @throws GeneralSecurityException if the platform does not support the required cryptographic methods.
	 * @throws UnsupportedEncodingException if UTF-16 encoding is not supported.
	 */
	public AESCrypt(String password) throws GeneralSecurityException, UnsupportedEncodingException {
		this(false, password);
	}
 
	/**
	 * Builds an object to encrypt or decrypt files with the given password.
	 * @throws GeneralSecurityException if the platform does not support the required cryptographic methods.
	 * @throws UnsupportedEncodingException if UTF-16 encoding is not supported.
	 */
	public AESCrypt(boolean debug, String password) throws GeneralSecurityException, UnsupportedEncodingException {
		try {
			DEBUG = debug;
			setPassword(password);
			random = SecureRandom.getInstance(RANDOM_ALG);
			digest = MessageDigest.getInstance(DIGEST_ALG);
			cipher = Cipher.getInstance(CRYPT_TRANS);
			hmac = Mac.getInstance(HMAC_ALG);
		} catch (GeneralSecurityException e) {
			throw new GeneralSecurityException(JCE_EXCEPTION_MESSAGE, e);
		}
	}
 
	/**
	 * Changes the password this object uses to encrypt and decrypt.
	 * @throws UnsupportedEncodingException if UTF-16 encoding is not supported.
	 */
	public void setPassword(String password) throws UnsupportedEncodingException {
		this.password = password.getBytes("UTF-16LE");
		debug("Password usado: ", this.password);
	}
 
	/**
	 * The file at <tt>fromPath</tt> is encrypted and saved at <tt>toPath</tt> location.
	 *
 
	 * <tt>version</tt> can be either 1 or 2.
	 * @throws IOException when there are I/O errors.
	 * @throws GeneralSecurityException if the platform does not support the required cryptographic methods.
	 */
	public void encrypt(int version, String fromPath, String toPath)
	throws IOException, GeneralSecurityException {
		InputStream in = null;
		OutputStream out = null;
		byte[] text = null;
		try {
			ivSpec1 = new IvParameterSpec(generateIv1());
			aesKey1 = new SecretKeySpec(generateAESKey1(ivSpec1.getIV(), password), CRYPT_ALG);
			ivSpec2 = new IvParameterSpec(generateIV2());
			aesKey2 = new SecretKeySpec(generateAESKey2(), CRYPT_ALG);
			debug("IV1: ", ivSpec1.getIV());
			debug("AES1: ", aesKey1.getEncoded());
			debug("IV2: ", ivSpec2.getIV());
			debug("AES2: ", aesKey2.getEncoded());
 
			in = new FileInputStream(fromPath);
			debug("Abierto para la lectura: " + fromPath);
			out = new FileOutputStream(toPath);
			debug("Abierto para la escritura: " + toPath);
 
			out.write("AES".getBytes("UTF-8"));	// Heading.
			out.write(version);	// Version.
			out.write(0);	// Reserved.
			if (version == 2) {	// No extensions.
				out.write(0);
				out.write(0);
			}
			out.write(ivSpec1.getIV());	// Initialization Vector.
 
			text = new byte[BLOCK_SIZE + KEY_SIZE];
			cipher.init(Cipher.ENCRYPT_MODE, aesKey1, ivSpec1);
			cipher.update(ivSpec2.getIV(), 0, BLOCK_SIZE, text);
			cipher.doFinal(aesKey2.getEncoded(), 0, KEY_SIZE, text, BLOCK_SIZE);
			out.write(text);	// Crypted IV and key.
			debug("IV2 + AES2 ciphertext: ", text);
 
			hmac.init(new SecretKeySpec(aesKey1.getEncoded(), HMAC_ALG));
			text = hmac.doFinal(text);
			out.write(text);	// HMAC from previous cyphertext.
			debug("HMAC1: ", text);
 
			cipher.init(Cipher.ENCRYPT_MODE, aesKey2, ivSpec2);
			hmac.init(new SecretKeySpec(aesKey2.getEncoded(), HMAC_ALG));
			text = new byte[BLOCK_SIZE];
			int len, last = 0;
			while ((len = in.read(text)) > 0) {
				cipher.update(text, 0, BLOCK_SIZE, text);
				hmac.update(text);
				out.write(text);	// Crypted file data block.
				last = len;
			}
			last &= 0x0f;
			out.write(last);	// Last block size mod 16.
			debug("�ltimo bloque de tama�o mod 16: " + last);
 
			text = hmac.doFinal();
			out.write(text);	// HMAC from previous cyphertext.
			debug("HMAC2: ", text);
		} catch (InvalidKeyException e) {
			throw new GeneralSecurityException(JCE_EXCEPTION_MESSAGE, e);
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}
 
	/**
	 * The file at <tt>fromPath</tt> is decrypted and saved at <tt>toPath</tt> location.
	 *
 
	 * Source file can be encrypted using version 1 or 2 of aescrypt.
	 * @throws IOException when there are I/O errors.
	 * @throws GeneralSecurityException if the platform does not support the required cryptographic methods.
	 */
	public void decrypt(String fromPath, String toPath)
	throws IOException, GeneralSecurityException {
		InputStream in = null;
		OutputStream out = null;
		byte[] text = null, backup = null;
		long total = 3 + 1 + 1 + BLOCK_SIZE + BLOCK_SIZE + KEY_SIZE + SHA_SIZE + 1 + SHA_SIZE;
		int version;
		try {
			in = new FileInputStream(fromPath);
			debug("Opened for reading: " + fromPath);
			out = new FileOutputStream(toPath);
			debug("Opened for writing: " + toPath);
 
			text = new byte[3];
			readBytes(in, text);	// Heading.
			if (!new String(text, "UTF-8").equals("AES")) {
				throw new IOException("Encabezado de archivo no v�lido");
			}
 
			version = in.read();	// Version.
			if (version < 1 || version > 2) {
				throw new IOException("El n�mero de versi�n no compatible: " + version);
			}
			debug("Version: " + version);
 
			in.read();	// Reserved.
 
			if (version == 2) {	// Extensions.
				text = new byte[2];
				int len;
				do {
					readBytes(in, text);
					len = ((0xff & (int) text[0]) << 8) | (0xff & (int) text[1]);
					if (in.skip(len) != len) {
						throw new IOException("Fin inesperado de la extensi�n");
					} 					
					total += 2 + len;
					debug("Skipped extension sized: " + len);
				} while (len != 0);
			}
			text = new byte[BLOCK_SIZE];
			readBytes(in, text);
			// Initialization Vector. 			
			ivSpec1 = new IvParameterSpec(text);
			aesKey1 = new SecretKeySpec(generateAESKey1(ivSpec1.getIV(), password), CRYPT_ALG);
			debug("IV1: ", ivSpec1.getIV()); 			debug("AES1: ", aesKey1.getEncoded());
			cipher.init(Cipher.DECRYPT_MODE, aesKey1, ivSpec1);
			backup = new byte[BLOCK_SIZE + KEY_SIZE];
			readBytes(in, backup);
			// IV and key to decrypt file contents.
			debug("IV2 + AES2 ciphertext: ", backup);
			text = cipher.doFinal(backup);
			ivSpec2 = new IvParameterSpec(text, 0, BLOCK_SIZE);
			aesKey2 = new SecretKeySpec(text, BLOCK_SIZE, KEY_SIZE, CRYPT_ALG);
			debug("IV2: ", ivSpec2.getIV());
			debug("AES2: ", aesKey2.getEncoded());
			hmac.init(new SecretKeySpec(aesKey1.getEncoded(), HMAC_ALG));
			backup = hmac.doFinal(backup);
			text = new byte[SHA_SIZE];
			readBytes(in, text);
			// HMAC and authenticity test.
			if (!Arrays.equals(backup, text)) {
				throw new IOException("El mensaje ha sido alterado o contrase�a incorrecta");
			} 			
			debug("HMAC1: ", text);
			total = new File(fromPath).length() - total;
			// Payload size. 			
			if (total % BLOCK_SIZE != 0) { 				
				throw new IOException("Archivo de entrada est� da�ado");
			} 			
			if (total == 0) {
				// Hack: empty files won't enter block-processing for-loop below.
				in.read();
				// Skip last block size mod 16.
			} 			
			debug("Payload size: " + total);
			cipher.init(Cipher.DECRYPT_MODE, aesKey2, ivSpec2);
			hmac.init(new SecretKeySpec(aesKey2.getEncoded(), HMAC_ALG));
			backup = new byte[BLOCK_SIZE];
			text = new byte[BLOCK_SIZE];
			for (int block = (int) (total / BLOCK_SIZE); block > 0; block--) {
				int len = BLOCK_SIZE;
				if (in.read(backup, 0, len) != len) {	// Cyphertext block.
					throw new IOException("Fin inesperado de contenido del archivo");
				}
				cipher.update(backup, 0, len, text);
				hmac.update(backup, 0, len);
				if (block == 1) {
					int last = in.read();	// Last block size mod 16.
					debug("Last block size mod 16: " + last);
					len = (last > 0 ? last : BLOCK_SIZE);
				}
				out.write(text, 0, len);

			}
			out.write(cipher.doFinal());

			backup = hmac.doFinal();
			text = new byte[SHA_SIZE];
			readBytes(in, text);	// HMAC and authenticity test.
			if (!Arrays.equals(backup, text)) {
				throw new IOException("El mensaje ha sido alterado o contrase�a incorrecta");
			}
			debug("HMAC2: ", text);
		} catch (InvalidKeyException e) {
			throw new GeneralSecurityException(JCE_EXCEPTION_MESSAGE, e);
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}
 
	public static void main(String[] args) {
		try {
			if (args.length < 4) {
				System.out.println("AESCrypt e|d password fromPath toPath");
				return;
			}
			AESCrypt aes = new AESCrypt(true, args[1]);
			switch (args[0].charAt(0)) {
			case 'e':
				aes.encrypt(2, args[2], args[3]);
				break;
			case 'd':
				aes.decrypt(args[2], args[3]);
				break;
			default:
				System.out.println("Operaci�n no v�lida: debe ser (e)ncrypt o (d)ecrypt.");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
