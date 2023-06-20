/**
 * 
 */
package cl.laaraucana.pagonomsil.cripto;

import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * This class defines methods for encrypting and decrypting using the Triple DES
 * algorithm and for generating, reading and writing Triple DES keys. It also
 * defines a main() method that allows these methods to be used from the command
 * line.
 */
public class TripleDES {
	private static final String encryptionKey           = "L1Ar15c1n1CC1FEN2020ESTA"; //24 bytes
	//private static final String ivKey           		= "Ar15c1n1"; //8 bytes
    private static final String characterEncoding       = "UTF-8";
    private static final String cipherTransformation    = "DESede/CBC/NoPadding"; //DESede/CBC/PKCS5Padding
    private static final String CRYPT_ALGORITHM  		= "DESede";
    
    
    /**
     * Method for Encrypt Plain String Data
     * @param plainText
     * @return encryptedText
     */
    public static String encrypt(String plainText) {
        String encryptedText = "";
        try {
            Cipher cipher   = Cipher.getInstance(cipherTransformation);
            byte[] key      = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, CRYPT_ALGORITHM);
            IvParameterSpec ivparameterspec = new IvParameterSpec(new byte[8]);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
            byte[] cipherText = cipher.doFinal(plainText.getBytes(characterEncoding));
            encryptedText=  DatatypeConverter.printBase64Binary(cipherText);
            //Base64.Encoder encoder = Base64.getEncoder();
            //encryptedText = encoder.encodeToString(cipherText);

        } catch (Exception E) {
             System.err.println("Encrypt Exception : "+E.getMessage());
        }
        return encryptedText;
    }

    /**
     * Method For Get encryptedText and Decrypted provided String
     * @param encryptedText
     * @return decryptedText
     */
    public static String decrypt(String encryptedText) {
        String decryptedText = "";
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] key = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, CRYPT_ALGORITHM);
            IvParameterSpec ivparameterspec = new IvParameterSpec(new byte[8]);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
            //Base64.Decoder decoder = Base64.getDecoder();
           // byte[] cipherText = decoder.decode(encryptedText.getBytes("UTF8"));
            byte[] cipherText = DatatypeConverter.parseBase64Binary(encryptedText);
            decryptedText = new String(cipher.doFinal(cipherText), characterEncoding);

        } catch (Exception E) {
            System.err.println("decrypt Exception : "+E.getMessage());
        }
        return decryptedText;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter String : ");
        String plainString = sc.nextLine();
      
        String encyptStr   = encrypt(plainString);
        String decryptStr  = decrypt(encyptStr);
        
        System.out.println("Plain   String  : "+plainString);
        System.out.println("Encrypt String  : "+encyptStr);
        System.out.println("Decrypt String  : "+decryptStr);
        
    }   
}

