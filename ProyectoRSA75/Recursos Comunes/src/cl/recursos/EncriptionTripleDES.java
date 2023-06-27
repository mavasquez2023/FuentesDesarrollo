/**
 * 
 */
package cl.recursos;

/**
 * @author Usist24
 *
 */
import org.bouncycastle.crypto.*;
import org.bouncycastle.crypto.paddings.*;
import org.bouncycastle.crypto.engines.*;
import org.bouncycastle.crypto.modes.*;
import org.bouncycastle.crypto.params.*;
//Encriptar / Desencriptar con Java en TripleDes
public class EncriptionTripleDES {

    BlockCipher engine = new DESedeEngine();

    public byte[] Encrypt(byte[] key, String plainText) {
        byte[] ptBytes = plainText.getBytes();
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(engine));
        cipher.init(true, new KeyParameter(key));
        byte[] rv = new byte[cipher.getOutputSize(ptBytes.length)];
        int tam = cipher.processBytes(ptBytes, 0, ptBytes.length, rv, 0);
        try {
            cipher.doFinal(rv, tam);
        } catch (Exception ce) {
            ce.printStackTrace();
        }
        return rv;
    }

    public String Decrypt(byte[] key, byte[] cipherText) {
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(engine));
        cipher.init(false, new KeyParameter(key));
        byte[] rv = new byte[cipher.getOutputSize(cipherText.length)];
        int tam = cipher.processBytes(cipherText, 0, cipherText.length, rv, 0);
        try {
            cipher.doFinal(rv, tam);
        } catch (Exception ce) {
            ce.printStackTrace();
        }
        return new String(rv).trim();
    }
    
    public static void main(String[] args) throws Exception {
    	EncriptionTripleDES esource = new EncriptionTripleDES();
        String key = "DC101AB52CF894CEE52F61731643B94F";
        String cad = "http://nachxs.wordpress.com";
        byte[] keyb = FunctionByte.hex2byte(key);
        System.out.println("Cadena Ingreso: " + cad);
        String enc = FunctionByte.toHEX1(esource.Encrypt(keyb, cad));
        System.out.println("Encripta: " + enc);
        String des = esource.Decrypt(keyb, FunctionByte.hex2byte(enc));
        System.out.println("Desencripta: " + des);
    }

}
