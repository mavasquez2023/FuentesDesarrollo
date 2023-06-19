/**
 * 
 */
package cl.laaraucana.convenfsura.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

//import com.squareup.okhttp.MediaType;
//import com.squareup.okhttp.OkHttpClient;
//import com.squareup.okhttp.Request;
//import com.squareup.okhttp.RequestBody;
//import com.squareup.okhttp.Response;


/**
 * @author IBM Software Factory
 *
 */
@Service
public class URLConnectionImpl implements URLConnection {
//	public static final MediaType JSON
//   = MediaType.parse("application/json; charset=utf-8");
	private static final Logger logger = Logger.getLogger(URLConnectionImpl.class);
	/* (non-Javadoc)
	 * @see cl.laaraucana.conveniosura.services.URLConnection#post(java.lang.String, java.lang.String)
	 */
	@Override
	public String post(String parametros, String uri) throws Exception {
		boolean resultado = false;
		URL url = new URL(uri);
		logger.info("Conectando url: " + uri);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod("POST");
		//connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("charset", "utf-8");
		connection.setRequestProperty("Content-Length", "" + Integer.toString(parametros.getBytes().length));
		connection.setUseCaches(false);
		logger.debug("Escribiendo parametros en outputstream");
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(parametros);
		wr.flush();
		String line;
		String codigo;
		String descripcion;
		String data="";
		JSONParser parser = new JSONParser();
		logger.debug("Leyendo salida");
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		JSONObject jsonObject = null;
		if ((line = reader.readLine()) != null) {
			Object obj = parser.parse(line);
			jsonObject = (JSONObject) obj;
			codigo = jsonObject.get("returnCode").toString();
			descripcion = jsonObject.get("returnMsg").toString();
			data= jsonObject.get("data").toString();
			resultado = (codigo.equals("200") && descripcion.equals("OK"));
			//System.out.println(line);
			logger.debug("Codigo respuesta:" + codigo + ", Descripción:" + descripcion);
			logger.debug("exito: " + resultado);
		}
		logger.debug("Cerrando conexión");
		wr.close();
		connection.disconnect();
		return data;
	}
	@Override
	public String setParams(Map<String, String> params) throws Exception {
		JSONObject jsonObject = new JSONObject(params);
		String salida=jsonObject.toJSONString();
		/*String salida="";
		int i=1;
		for (Iterator iterator = params.keySet().iterator(); iterator.hasNext();) {
			String codigo = (String) iterator.next();
			if(i==1){
				salida+= "?";
			}else{
				salida+= "&";
			}
			salida+= codigo + "=" + params.get(codigo);
			i++;
		}*/
		logger.info("params :" + salida);
		return salida;
	}
/*	@Override
	public String doPostRequest(String url, String json) throws Exception {
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder()
		.url(url)
		.post(body)
		.build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}
	
	@Override
	public String doGetRequest(String url) throws Exception {
		OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
            .url(url)
            .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
      }
*/
}
