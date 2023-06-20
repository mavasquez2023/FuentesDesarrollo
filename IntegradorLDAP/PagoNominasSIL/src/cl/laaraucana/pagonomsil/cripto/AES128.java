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
 * Program to Encrypt/Decrypt String Using AES 128 bit Encryption Algorithm
 */
public class AES128 {
    
    private static final String encryptionKey           = "L1Ar15c1n1CC1FEN";
    private static final String characterEncoding       = "UTF-8";
    private static final String cipherTransformation    = "AES/CBC/PKCS5PADDING";
    private static final String CRYPT_ALGORITHM 		= "AES";
    
    
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
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
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
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
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
