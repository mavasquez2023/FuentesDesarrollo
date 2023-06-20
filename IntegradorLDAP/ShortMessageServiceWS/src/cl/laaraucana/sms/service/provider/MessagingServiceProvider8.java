package cl.laaraucana.sms.service.provider;

import org.apache.log4j.Logger;

import cl.laaraucana.sms.utils.Networking;
import cl.laaraucana.sms.utils.Configuration;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MessagingServiceProvider8 {

	private static final Logger logger = Logger.getLogger(MessagingServiceProvider.class);
	private static final Networking networkTools = new Networking();

	public String sendMessage(String requestBody, String authorizationKey, String endpoint) {
		String result = "";
		try {
			OkHttpClient client;
			if (networkTools.isProxyEnabled()) {
				client = new OkHttpClient().newBuilder().proxy(networkTools.getProxy()).build();
			} else {
				client = new OkHttpClient().newBuilder().build();
			}
			okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, requestBody);
			Request request = new Request.Builder().url(endpoint).method("POST", body)
					.addHeader("Content-Type", "application/json")
					.addHeader("Authorization", "Basic " + authorizationKey)
					.addHeader("Api-key", Configuration.apiKey)
					.addHeader("Accept", "application/json")
					.build();
			Response response = client.newCall(request).execute();
			result = response.body().string();
		} catch (Exception e) {
			logger.error(String.format("Error sending message using endpoint %s %s", endpoint, e));
		}
		return result;
	}
}