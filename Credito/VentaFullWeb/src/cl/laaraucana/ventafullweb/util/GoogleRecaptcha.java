package cl.laaraucana.ventafullweb.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class GoogleRecaptcha {
	private static final String RECAPTCHA_SERVICE_URL = "https://www.google.com/recaptcha/api/siteverify";
	private static final String SECRET_KEY = Configuraciones.getConfig("servicios.recaptcha.secretkey");
	
	/**
	 * checks if a user is valid
	 * @param clientRecaptchaResponse
	 * @return true if human, false if bot
	 * @throws IOException
	 * @throws ParseException
	 * @throws JSONException 
	 */
	public static boolean isValid(String clientRecaptchaResponse) throws IOException, ParseException, JSONException {
		if (clientRecaptchaResponse == null || "".equals(clientRecaptchaResponse)) {
			return false;
		}

		URL obj = new URL(RECAPTCHA_SERVICE_URL);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		//add client result as post parameter
		String postParams =
				"secret=" + SECRET_KEY +
				"&response=" + clientRecaptchaResponse;

		// send post request to google recaptcha server
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(postParams);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close(); 

		String respuestaRecaptcha = response.toString();
		
		int indexResponseSuccess = respuestaRecaptcha.indexOf("success");
		int indexResponseScore   = respuestaRecaptcha.indexOf("score");
		
		String responseSuccess  = respuestaRecaptcha.substring((indexResponseSuccess + 10), (indexResponseSuccess + 14));
		String responseScore    = respuestaRecaptcha.substring((indexResponseScore + 8), (indexResponseScore + 11));
		
		if(responseSuccess.equals("true")) {
			Boolean success = Boolean.parseBoolean(responseSuccess);
			Double score = Double.parseDouble(responseScore);

			//result should be sucessfull and spam score above 0.5
			return (success && score >= 0.6);
		} else {
			return false;
		}
		
	}

}
