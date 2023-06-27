/**
 * 
 */
package cl.recursos;

/**
 * @author Usist24
 *
 */
import java.security.InvalidKeyException;


public class TripleDES {
    DES tdes = new DES();
    private static final int BLOCK_128 = 16;
    private static final int BLOCK_192 = 24;

    public String encrypt3DES(String str, String key) throws Exception {
        String enc = null;
        try {
            if (key.length() == 32) {
                byte[] keys = new byte[BLOCK_128];
                keys = FunctionByte.hex2byte(key);
                byte[] k1 = new byte[8];
                byte[] k2 = new byte[8];
                System.arraycopy(keys, 0, k1, 0, 8);
                tdes.setKey(k1);
                byte[] encode_1 = tdes.encrypt(FunctionByte.hex2byte(str));
                System.arraycopy(keys, 8, k2, 0, 8);
                tdes.setKey(k2);
                byte[] encode_2 = tdes.decrypt(encode_1);
                tdes.setKey(k1);
                byte[] encode_3 = tdes.encrypt(encode_2);
                enc = FunctionByte.toHEX1(encode_3);
                System.out.println("Encripta TipleDES: " + str + " --> " + enc);
            } else if (key.length() == 48) {
                byte[] keys = new byte[BLOCK_192];
                keys = FunctionByte.hex2byte(key);
                byte[] k1 = new byte[8];
                byte[] k2 = new byte[8];
                byte[] k3 = new byte[8];
                System.arraycopy(keys, 0, k1, 0, 8);
                tdes.setKey(k1);
                byte[] encode_1 = tdes.encrypt(FunctionByte.hex2byte(str));
                System.arraycopy(keys, 8, k2, 0, 8);
                tdes.setKey(k2);
                byte[] encode_2 = tdes.decrypt(encode_1);
                System.arraycopy(keys, 16, k3, 0, 8);
                tdes.setKey(k3);
                byte[] encode_3 = tdes.encrypt(encode_2);
                enc = FunctionByte.toHEX1(encode_3);
                System.out.println("Encripta TipleDES: " + str + " --> " + enc);
            } else {
                throw new InvalidKeyException("ERROR: Longitud de la llave incorrecta");
            }
        } catch (Exception ex) {
            throw ex;
        }
        return enc;
    }

    public String dencrypt3DES(String str, String key) throws Exception {
        String denc = "";
        try {
            if (key.length() == 32) {
                byte[] keys = new byte[BLOCK_128];
                keys = FunctionByte.hex2byte(key);
                byte[] k1 = new byte[8];
                byte[] k2 = new byte[8];
                System.arraycopy(keys, 0, k1, 0, 8);
                tdes.setKey(k1);
                byte[] decode_3 = tdes.decrypt(FunctionByte.hex2byte(str));
                System.arraycopy(keys, 8, k2, 0, 8);
                tdes.setKey(k2);
                byte[] decode_2 = tdes.encrypt(decode_3);
                tdes.setKey(k1);
                byte[] decode_1 = tdes.decrypt(decode_2);
                denc = FunctionByte.toHEX1(decode_1);
                System.out.println("Desencripta TipleDES: " + str + " --> " + denc);
            } else if (key.length() == 48) {
                byte[] keys = new byte[BLOCK_192];
                keys = FunctionByte.hex2byte(key);
                byte[] k1 = new byte[8];
                byte[] k2 = new byte[8];
                byte[] k3 = new byte[8];
                System.arraycopy(keys, 16, k3, 0, 8);
                tdes.setKey(k3);
                byte[] decode_3 = tdes.decrypt(FunctionByte.hex2byte(str));
                System.arraycopy(keys, 8, k2, 0, 8);
                tdes.setKey(k2);
                byte[] decode_2 = tdes.encrypt(decode_3);
                System.arraycopy(keys, 0, k1, 0, 8);
                tdes.setKey(k1);
                byte[] decode_1 = tdes.decrypt(decode_2);
                denc = FunctionByte.toHEX1(decode_1);
                System.out.println("Desencripta TipleDES: " + str + " --> " + denc);
            } else {
                throw new InvalidKeyException("ERROR: Longitud de la llave incorrecta");
            }
        } catch (Exception ex) {
            throw ex;
        }
        return denc;
    }
    public static void main(String[] args) throws Exception {
    	System.out.println("In...");
    	TripleDES tdes = new TripleDES();
        String key = "DC101AB52CF894CEE52F61731643B94F";
        String cad = "http://www.laaraucana.cl";
        System.out.println("Cadena Ingreso: " + cad);
        String enc = tdes.encrypt3DES(cad, key);
        System.out.println("Encriptado: " + enc);
        String des = tdes.dencrypt3DES(enc, key);
        System.out.println("Desencripta: " + des);
    }
}

