/**
 * 
 */
package cl.recursos;

/**
 * @author Usist24
 *
 */
/**
 *   collection of utility routines for converting and displaying binary
 *   saved in byte/short/int arrays, and loaded/displayed using hex.
 *
 *   @author Lawrie Brown, Oct 2001
 */
public class FunctionByte {

    //......................................................................
    // utility conversions between byte, short and int arrays

    /**
     *
     * @param sa
     * @return
     */
    public static byte[] short2byte(short[] sa) {
        int length = sa.length;
        byte[] ba = new byte[length * 2];
        for (int i = 0, j = 0, k; i < length; ) {
            k = sa[i++];
            ba[j++] = (byte)((k >>> 8) & 0xFF);
            ba[j++] = (byte)( k        & 0xFF);
        }
        return (ba);
    }

    //......................................................................
    /**
     * 
     * @param ba
     * @return
     */
    public static short[] byte2short(byte[] ba) {
        int length = ba.length;
        short[] sa = new short[length / 2];
        for (int i = 0, j = 0; j < length / 2; ) {
            sa[j++] = (short)(((ba[i++] & 0xFF) <<  8) |
	               ((ba[i++] & 0xFF)      ));
        }
        return (sa);
    }

    //......................................................................
    /**
     *
     * @param ia
     * @return
     */
    public static byte[] int2byte(int[] ia) {
        int length = ia.length;
        byte[] ba = new byte[length * 4];
        for (int i = 0, j = 0, k; i < length; ) {
            k = ia[i++];
            ba[j++] = (byte)((k >>>24) & 0xFF);
            ba[j++] = (byte)((k >>>16) & 0xFF);
            ba[j++] = (byte)((k >>> 8) & 0xFF);
            ba[j++] = (byte)( k        & 0xFF);
        }
        return (ba);
    }

    //......................................................................
    /**
     *
     * @param ba
     * @return
     */
    public static int[] byte2int(byte[] ba) {
        int length = ba.length;
        int[] ia = new int[length / 4];
        for (int i = 0, j = 0; j < length / 4; ) {
            ia[j++] = (((ba[i++] & 0xFF) << 24) |
	               ((ba[i++] & 0xFF) << 16) |
	               ((ba[i++] & 0xFF) <<  8) |
	               ((ba[i++] & 0xFF)      ));
        }
        return (ia);
    }

    //......................................................................
    // utility methods (adapted from cryptix.util.core.Hex class)

    /** array mapping hex value (0-15) to corresponding hex digit (0-9a-f). */
    public static final char[] HEX_DIGITS = {
        '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'
    };

    /**
     * utility method to convert a byte array to a hexadecimal string.
     * <p>
     * Each byte of the input array is converted to 2 hex symbols,
     * using the HEX_DIGITS array for the mapping, with spaces after each pair.
     * @param ba array of bytes to be converted into hex
     * @return hex representation of byte array     
     */
    public static String toHEX (byte[] ba) {
        int length = ba.length;
	if (length == 0)	return "";
        char[] buf = new char[length * 3];
        for (int i = 0, j = 0, k; i < length; ) {
            k = ba[i++];
            buf[j++] = HEX_DIGITS[(k >>> 4) & 0x0F];
            buf[j++] = HEX_DIGITS[ k        & 0x0F];
	    buf[j++] = ' ';
        }
        return new String(buf);
    }

    /**
     * utility method to convert a short array to a hexadecimal string.
     * <p>
     * Each word of the input array is converted to 4 hex symbols,
     * using the HEX_DIGITS array for the mapping, with spaces after every 4.
     * @param ia array of shorts to be converted into hex
     * @return hex representation of short array     
     */
    public static String toHEX (short[] ia) {
        int length = ia.length;
	if (length == 0)	return "";
        char[] buf = new char[length * 5];
        for (int i = 0, j = 0, k; i < length; ) {
            k = ia[i++];
            buf[j++] = HEX_DIGITS[(k >>>12) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>> 8) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>> 4) & 0x0F];
            buf[j++] = HEX_DIGITS[ k        & 0x0F];
	    buf[j++] = ' ';
        }
        return new String(buf);
    }

    /**
     * utility method to convert an int array to a hexadecimal string.
     * <p>
     * Each word of the input array is converted to 8 hex symbols,
     * using the HEX_DIGITS array for the mapping, with spaces after every 4.
     * @param ia array of ints to be converted into hex
     * @return hex representation of int array     
     */
    public static String toHEX (int[] ia) {
        int length = ia.length;
	if (length == 0)	return "";
        char[] buf = new char[length * 10];
        for (int i = 0, j = 0, k; i < length; ) {
            k = ia[i++];
            buf[j++] = HEX_DIGITS[(k >>>28) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>>24) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>>20) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>>16) & 0x0F];
	    buf[j++] = ' ';
            buf[j++] = HEX_DIGITS[(k >>>12) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>> 8) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>> 4) & 0x0F];
            buf[j++] = HEX_DIGITS[ k        & 0x0F];
	    buf[j++] = ' ';
        }
        return new String(buf);
    }

    /**
     * utility method to convert a byte to a hexadecimal string.
     * <p>
     * the byte is converted to 2 hex symbols,
     * using the HEX_DIGITS array for the mapping.
     * @param b byte to be converted into hex
     * @return hex representation of byte
     */
    public static String toHEX1 (byte b) {
        char[] buf = new char[2];
	int j = 0;
        buf[j++] = HEX_DIGITS[(b >>> 4) & 0x0F];
        buf[j++] = HEX_DIGITS[ b        & 0x0F];
        return new String(buf);
    }

    /**
     * utility method to convert a byte array to a hexadecimal string.
     * <p>
     * Each byte of the input array is converted to 2 hex symbols,
     * using the HEX_DIGITS array for the mapping.
     * @param ba array of bytes to be converted into hex
     * @return hex representation of byte array     
     */
    public static String toHEX1 (byte[] ba) {
        int length = ba.length;
        char[] buf = new char[length * 2];
        for (int i = 0, j = 0, k; i < length; ) {
            k = ba[i++];
            buf[j++] = HEX_DIGITS[(k >>> 4) & 0x0F];
            buf[j++] = HEX_DIGITS[ k        & 0x0F];
        }
        return new String(buf);
    }

    /**
     * utility method to convert a short array to a hexadecimal string.
     * <p>
     * Each word of the input array is converted to 4 hex symbols,
     * using the HEX_DIGITS array for the mapping.
     * @param ia array of shorts to be converted into hex
     * @return hex representation of short array     
     */
    public static String toHEX1 (short[] ia) {
        int length = ia.length;
        char[] buf = new char[length * 4];
        for (int i = 0, j = 0, k; i < length; ) {
            k = ia[i++];
            buf[j++] = HEX_DIGITS[(k >>>12) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>> 8) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>> 4) & 0x0F];
            buf[j++] = HEX_DIGITS[ k        & 0x0F];
        }
        return new String(buf);
    }

    /**
     * utility method to convert an int to a hexadecimal string.
     * <p>
     * the int is converted to 8 hex symbols,
     * using the HEX_DIGITS array for the mapping.
     * @param i int to be converted into hex
     * @return hex representation of int
     */
    public static String toHEX1 (int i) {
        char[] buf = new char[8];
	int j = 0;
        buf[j++] = HEX_DIGITS[(i >>>28) & 0x0F];
        buf[j++] = HEX_DIGITS[(i >>>24) & 0x0F];
        buf[j++] = HEX_DIGITS[(i >>>20) & 0x0F];
        buf[j++] = HEX_DIGITS[(i >>>16) & 0x0F];
        buf[j++] = HEX_DIGITS[(i >>>12) & 0x0F];
        buf[j++] = HEX_DIGITS[(i >>> 8) & 0x0F];
        buf[j++] = HEX_DIGITS[(i >>> 4) & 0x0F];
        buf[j++] = HEX_DIGITS[ i        & 0x0F];
        return new String(buf);
    }

    /**
     * utility method to convert an int array to a hexadecimal string.
     * <p>
     * Each word of the input array is converted to 8 hex symbols,
     * using the HEX_DIGITS array for the mapping.
     * @param ia array of ints to be converted into hex
     * @return hex representation of int array     
     */
    public static String toHEX1 (int[] ia) {
        int length = ia.length;
        char[] buf = new char[length * 8];
        for (int i = 0, j = 0, k; i < length; ) {
            k = ia[i++];
            buf[j++] = HEX_DIGITS[(k >>>28) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>>24) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>>20) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>>16) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>>12) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>> 8) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>> 4) & 0x0F];
            buf[j++] = HEX_DIGITS[ k        & 0x0F];
        }
        return new String(buf);
    }


    //......................................................................
    /**
     * Returns a byte array from a string of hexadecimal digits.
     *
     * @param hex string of hex characters
     * @return byte array of binary data corresponding to hex string input
     */
    public static byte[] hex2byte(String hex) {
        int len = hex.length();
	if (len==0)	return null;
        byte[] buf = new byte[((len + 1) / 2)];

        int i = 0, j = 0;
        if ((len % 2) == 1)
            buf[j++] = (byte) hexDigit(hex.charAt(i++));

        while (i < len) {
            buf[j++] = (byte) ((hexDigit(hex.charAt(i++)) << 4) |
                                hexDigit(hex.charAt(i++)));
        }
        return buf;
    }

    //......................................................................
    /**
     * Returns true if the string consists ONLY of valid hex characters
     *
     * @param hex string of hex characters
     * @return true if a valid hex string
     */
    public static boolean isHex(String hex) {
        int len = hex.length();
	if (len==0)	return false;		// nb zero length is not hex
	int i = 0;
	char ch;

	while (i < len) {
	     ch = hex.charAt(i++);
	     if (! ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'F') ||
	           (ch >= 'a' && ch <= 'f'))) return false;
	}
	return true;
    }

    //......................................................................
    /**
     * Returns the number from 0 to 15 corresponding to the hex digit <i>ch</i>.
     *
     * @param ch hex digit character (must be 0-9A-Fa-f)
     * @return   numeric equivalent of hex digit (0-15)
     */
    public static int hexDigit(char ch) {
        if (ch >= '0' && ch <= '9')
            return ch - '0';
        if (ch >= 'A' && ch <= 'F')
            return ch - 'A' + 10;
        if (ch >= 'a' && ch <= 'f')
            return ch - 'a' + 10;

        return(0);	// any other char is treated as 0
    }


    //......................................................................
    /**
     * XOR two byte arrays and return a byte array result.
     * Since arrays are in big-endian order, must right align indices to XOR.
     *
     * @param ba1 byte array of binary data for data1
     * @param ba2 byte array of binary data for data2
     * @return byte array of binary data being the XOR result
     */
    public static byte[] xor_bytes(byte[] ba1, byte[] ba2) {
	// check params and handle errors
	if ((ba1 == null) && (ba2 == null))	return null;
        int len1 = ba1.length;
        int len2 = ba2.length;
	int i;
        byte[] res;

	if (len1 > len2) {	// have ba1 larger
	    int len = len1;
            res = new byte[len];
	    int dif = len1 - len2;
            for (i=0; i<dif; i++)	// loop over bytes in ba1 only
	        res[i] = ba1[i];
            for (i=dif; i<len; i++)	// loop over common bytes
	        res[i] = (byte)(ba1[i] ^ ba2[i-dif]); // xor ba1 & ba2 bytes
	} else {
	    int len = len2;
            res = new byte[len];
	    int dif = len2 - len1;
            for (i=0; i<dif; i++)	// loop over bytes in ba2 only
	        res[i] = ba2[i];
            for (i=dif; i<len; i++)	// loop over common bytes
	        res[i] = (byte)(ba1[i-dif] ^ ba2[i]); // xor ba1 & ba2 bytes
	}
        return res;
    }

    /**
     * Analyse byte array and return its hamming weight (no 1's)
     *
     * @param ba byte array of binary data
     * @return hamming weight of data array
     */
    public static int hamming(byte[] ba) {
	// handle errors in ba param
	if (ba == null)	return 0;
        int len = ba.length;
	if (len == 0)	return 0;
	int ham = 0;

        for (int i=0; i<len; i++) {	// loop over all bytes in array
            int b = (ba[i] & 0xff);	// for this byte value only
	    while (b != 0) {		// while its not 0
	        if ((b & 0x1) == 1)	// check LSB
		    ham++;		// if LSB a 1 incr hamming weight
		b >>>= 1;		// and shift bits to right
	    }
	}
        return ham;
    }

}

