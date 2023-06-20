/**
 * 
 */
package cl.laaraucana.pagodirecto.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author IBM Software Factory
 *
 */
@SuppressWarnings("serial")
public class InicioServlet extends HttpServlet{
	private static ResourceBundle configProperties = ResourceBundle.getBundle("etc/config");
	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			try {
				String url= configProperties.getString("app.envio.url");
				
				String queryString= "?" + request.getQueryString();
				System.out.println("Invocadno url de pago:" + url + queryString);
				
				URL obj = new URL(url + queryString);
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				
				// optional default is GET
				con.setRequestMethod("GET");

				//add request header
				con.setRequestProperty("cache-control", "no-cache");
				con.setRequestProperty("user-agent", "Chrome/92.0.4515.107");
				//con.setRequestProperty("X-API-KEY", "myApiKey");
				//con.setRequestProperty("X-API-EMAIL", "myEmail@mail.com");

				int responseCode = con.getResponseCode();
				System.out.println("Sending 'GET' request to URL : " + url);
				System.out.println("Response Code : " + responseCode);

				BufferedReader in = new BufferedReader(
				           new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer responsebuffer = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
				    responsebuffer.append(inputLine);
				}
				in.close();

				//print result
				System.out.println(responsebuffer.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				doGet(request, response);
			}
}
