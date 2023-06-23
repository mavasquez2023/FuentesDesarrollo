package cl.araucana.spl.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @author rgili
 *
 */
public class HttpRequestor {
	private static final Logger log = Logger.getLogger(HttpRequestor.class);
	private URL url = null;
	
	public HttpRequestor(String urlStr) throws MalformedURLException {
		url = new URL(urlStr);
	}
	public String doRequest(String content) throws IOException {
		return doRequest(content, false);
	}
	
	public String doRequestAsForm(Map params) throws IOException {
		String content = encodeParams(params);
		return doRequest(content, true);
	}

	private String doRequest(String content, boolean sendAsForm) throws IOException {
		HttpURLConnection conn = null;
		String mensaje = content;
		
		try {
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
		
			if (sendAsForm) {
				conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			}
			conn.connect();
		
			// enviar el mensaje
			PrintWriter os = new PrintWriter(conn.getOutputStream());
			os.print(mensaje);
			os.flush();
			os.close();
		
			// recibir la respuesta
			StringBuffer sb = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String linea = null;
			while ((linea = reader.readLine()) != null) {
				sb.append(linea);
			}
			reader.close();
		
			return sb.toString();
		} finally {
			conn.disconnect();
		}
	}
	
	private String encodeParams(Map params) throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer();
		for (Iterator i = params.keySet().iterator(); i.hasNext(); ) {
			String key = (String)i.next();
			String value = (String)params.get(key);
			sb.append(key);
			sb.append("=");
			sb.append(encodeURL(value));
			if (i.hasNext())
				sb.append("&");
		}
		return sb.toString();
	}
	
	private String encodeURL(String value) throws UnsupportedEncodingException {
		log.info("Estoy en encodeURL");
		Renderer render = new Renderer();
		return render.encodeURL(value);
	}
	
}
