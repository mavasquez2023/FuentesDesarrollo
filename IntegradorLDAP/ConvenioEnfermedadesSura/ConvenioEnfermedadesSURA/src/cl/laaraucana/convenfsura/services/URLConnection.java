/**
 * 
 */
package cl.laaraucana.convenfsura.services;

import java.util.Map;

/**
 * @author IBM Software Factory
 *
 */
public interface URLConnection {
	public String post(String parametros, String uri) throws Exception;
	
	/*		public String doGetRequest(String uri) throws Exception;

	public String doPostRequest(String url, String json) throws Exception;*/
	
	public String setParams(Map<String , String> params) throws Exception;
}
