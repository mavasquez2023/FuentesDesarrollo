/**
 * 
 */
package cl.araucana.tgr.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author IBM Software Factory
 *
 */
public class TokenFactory {
	private static TokenFactory instance = new TokenFactory();
	private Map<String, String> tokens= new HashMap<String, String>();

	public static TokenFactory getInstance(){
			return instance;
	}

	public TokenFactory(){
	}

	/**
	 * @return the tokens
	 */
	public Map<String, String> getTokens() {
		return tokens;
	}

	/**
	 * @param tokens the tokens to set
	 */
	public void setTokens(Map<String, String> tokens) {
		this.tokens = tokens;
	}
	
	public void addToken(String ip, String fecha) {
		this.tokens.put(ip, fecha);
	}
	
	public void delToken(String ip) {
		this.tokens.remove(ip);
	}
	
	public String getToken(String ip) {
		String fecha= this.tokens.get(ip);
		return fecha;
	}
	
}
