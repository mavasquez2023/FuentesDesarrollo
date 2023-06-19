/**
 * 
 */
package cl.laaraucana.contratocr.util;

import java.util.HashMap;
import java.util.Map;

import cl.laaraucana.contratocr.services.CodigosSinacofiService;
import cl.laaraucana.contratocr.services.CodigosSinacofiServiceImpl;

/**
 * @author IBM Software Factory
 *
 */
public class CodigosSingleton {
	private static CodigosSingleton instance = new CodigosSingleton();
	
	Map<String, String> codigosSinacofi= new HashMap<String, String>();
	
	public static CodigosSingleton getInstance(){
		return instance;
	}
	
	public CodigosSingleton(){
		try {
			CodigosSinacofiService listaCodigos= new CodigosSinacofiServiceImpl();
			codigosSinacofi= listaCodigos.getCodigosSinacofi();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the codigosSinacofi
	 */
	public String getDescripcion(String codigoRetorno) {
		return codigosSinacofi.get(codigoRetorno);
	}
	
	/**
	 * @return the codigosSinacofi
	 */
	public Map<String, String> getCodigosSinacofi() {
		return codigosSinacofi;
	}

	/**
	 * @param codigosSinacofi the codigosSinacofi to set
	 */
	public void setCodigosSinacofi(Map<String, String> codigosSinacofi) {
		this.codigosSinacofi = codigosSinacofi;
	}
	
	
}
