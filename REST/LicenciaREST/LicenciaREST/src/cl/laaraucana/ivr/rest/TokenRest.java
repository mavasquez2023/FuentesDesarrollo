/**
 * 
 */
package cl.laaraucana.ivr.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ow2.util.base64.Base64;

import cl.laaraucana.ivr.vo.Autorizacion;
import cl.laaraucana.ivr.vo.Token;


@Path("/token")
public class TokenRest {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public Token getToken(Autorizacion credenciales) {
		String usuario= credenciales.getUsuario();
		String password=credenciales.getPassword();
		Token salida = new Token();
		try {
			/*
			String jsonmanula="{\"usuario\":\"Claudio\", \"password\":\"1234\"}";
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject)parser.parse(jsonmanula);
			usuario = (String)jsonObject.get("usuario");
			password = (String)jsonObject.get("password");
			*/
			
			String credenciales2=usuario+":"+password;
			char[] encoded_token= Base64.encode(credenciales2.getBytes());
			salida.setToken(String.valueOf(encoded_token));
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return salida;
    }
}
