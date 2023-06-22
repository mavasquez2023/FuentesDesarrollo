package cl.laaraucana.giftcardsencosud.main;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import sun.misc.BASE64Encoder;

public class App2 {

	public static void main(String[] args) {

		String url = "http://apimailtest.giftcard.cl/v1/api/coupon/5012342";
		String name = "c2c";
		String password = "password";
		String authString = name + ":" + password;
		String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
		System.out.println("Base64 encoded auth string: " + authStringEnc);
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/json").header("Authorization", "Basic " + authStringEnc)
				.get(ClientResponse.class);
		if (resp.getStatus() != 200) {

			System.out.println(resp.getStatus());
		}
		String output = resp.getEntity(String.class);
		System.out.println("response: " + output);

	}

}
