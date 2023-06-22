package cse.model.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Logger;

public class UID {

	private static Logger m_log = Logger.getAnonymousLogger();
	private static char separator;
	private static StringBuffer IPAddressSegment;
	private static SecureRandom prng;

	static {
		try {
			// Get IPAddress Segment
			IPAddressSegment = new StringBuffer();
			InetAddress addr = InetAddress.getLocalHost();
			StringBuffer strTemp = new StringBuffer();
			byte[] ipaddr = addr.getAddress();
			for (int i = 0; i < ipaddr.length; i++) {
				Byte b = new Byte(ipaddr[i]);

				strTemp = new StringBuffer(Integer.toHexString(b.intValue() & 0x000000ff));
				while (strTemp.length() < 2) {
					strTemp.insert(0, '0');
				}
				IPAddressSegment.append(strTemp);
			}

			if (separator != '\u0000') {
				IPAddressSegment.append(separator);
			}

			//Get Random Segment Algoritm
			prng = SecureRandom.getInstance("SHA1PRNG");

		} catch (UnknownHostException ex) {
			m_log.severe("Unknown Host Exception Caught: " + ex.getMessage());
		} catch (NoSuchAlgorithmException nsae) {
			m_log.severe("No Such Algorithm Exception Caught: " + nsae.getMessage());
		}
	}

	public static final String getUID() {
		StringBuffer strRetVal = new StringBuffer(IPAddressSegment.toString());
		StringBuffer strTemp = new StringBuffer();

		//Get CurrentTimeMillis() segment
		strTemp = new StringBuffer(Long.toHexString(System.currentTimeMillis()));
		while (strTemp.length() < 12) {
			strTemp.insert(0, '0');
		}
		strRetVal.append(strTemp);
		if (separator != '\u0000') {
			IPAddressSegment.append(separator);
		}

		// Get Random Segment
		strTemp = new StringBuffer(Integer.toHexString(prng.nextInt()));
		while (strTemp.length() < 8) {
			strTemp.insert(0, '0');
		}

		strRetVal.append(strTemp.substring(4));
		if (separator != '\u0000') {
			IPAddressSegment.append(separator);
		}

		//Get IdentityHash() segment
		strTemp = new StringBuffer(Long.toHexString(System.identityHashCode((Object) new UID())));
		while (strTemp.length() < 8) {
			strTemp.insert(0, '0');
		}
		strRetVal.append(strTemp);

		return strRetVal.toString().toUpperCase();
	}

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10; i++) {
			long lngStart = System.currentTimeMillis();
			m_log.info(UID.getUID());
			m_log.info("Elapsed time: " + (System.currentTimeMillis() - lngStart));
		}
	}
}