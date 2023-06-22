package cl.laaraucana.giftcardsencosud.main;

import java.util.Map;
import java.util.HashMap;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import cl.laaraucana.giftcardsencosud.vo.ProductVo;
import sun.misc.BASE64Encoder;

public class App {

	public static void main(String[] args) {

		Map<String, Object> datos = new HashMap<String, Object>();

	    ProductVo product = new ProductVo();
	    
	    product.setUser_transaction_code("1234321");
	    product.setProduct_code("A682452");
	    product.setProduct_description("Gift Card $10.000");
	    product.setReceptor_name("Claudio Lillo");
	    product.setReceptor_rut("116488345");
	    product.setReceptor_email("clillo007@gmail.com");
	    //product.setStock("23");
	    //product.setCreated_at("2017-05-18 01:21:58");
	    
		
	    datos.put("user_transaction_code", product.getUser_transaction_code());
	    datos.put("product_code", product.getProduct_code());
	    datos.put("product_description", product.getProduct_description());
	    datos.put("receptor_rut", product.getReceptor_rut());
	    datos.put("receptor_name", product.getReceptor_name());
	    datos.put("receptor_email", product.getReceptor_email());
	    //datos.put("created_at", product.getCreated_at());
	    //datos.put("stock", product.getStock());
	    
	    
	    
		Gson gson = new Gson(); 
		String json = gson.toJson(datos); 

		System.out.println(json);
		
		
		String url = "http://apimailtest.giftcard.cl/v1/api/coupon";
		String name = "c2c";
		String password = "password";
		String authString = name + ":" + password;
		String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
		System.out.println("Base64 encoded auth string: " + authStringEnc);
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/json").header("Authorization", "Basic " + authStringEnc)
				.post(ClientResponse.class, json);
		if (resp.getStatus() != 200) {

			System.out.println(resp.getStatus());
		}
		String output = resp.getEntity(String.class);
		System.out.println("response: " + output);

	}

}
