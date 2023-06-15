package cl.araucana.core.util;

import java.util.StringTokenizer;
import com.Ostermiller.util.Base64;

public class UserPrincipal {

	//private static final int MAX_ENCODED_UC_LENGTH = 32;
	//private static final String PADDER = "3xF8#!+-&$0Y";
	
	private String name;
	private String password;
	
	public UserPrincipal(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String toString() {
		return "name = [" + name + "] password = [" + password + "]";
	}
	
	public String encode() {
		String userCredentials = getName()+":"+getPassword();
		try {
			//Encripta credenciales y las concatena con el vector inicial generado
			String[] encodedUserCredentials = EncriptUtils.encode(userCredentials);
			return cl.araucana.core.util.Hex.encode((encodedUserCredentials[0]+":"+encodedUserCredentials[1]));
		} catch (Exception e) {
			System.out.println("Error al encriptar credenciales");
			return null;
		}
	}
	
	public static UserPrincipal  decodeUserCredentials_old(
			String encodedUserCredentials) {
				
		encodedUserCredentials = Hex.decode(encodedUserCredentials);
		
		String decodedUserCredentials = Base64.decode(encodedUserCredentials);
		StringTokenizer st = new StringTokenizer(decodedUserCredentials, ":");
			
		if (st.countTokens() == 2 || st.countTokens() == 3) {
			String userName = st.nextToken();
			String password = st.nextToken();
		
			return new UserPrincipal (userName, password);
		}
		
		return null;
	}
	
	public static UserPrincipal decodeUserCredentials(String encodedUserCredentials) {
		String vectorIni, xmlEncriptado; 		
		try {
			encodedUserCredentials = cl.araucana.core.util.Hex.decode(encodedUserCredentials);
			String[] credenciales = encodedUserCredentials.split(":");
			xmlEncriptado = credenciales[0];
			vectorIni = credenciales[1];
			String decodedUserCredentials = EncriptUtils.desEncripta(xmlEncriptado, vectorIni);
			String[] userPass = decodedUserCredentials.split(":");
			return new UserPrincipal(userPass[0] , userPass[1]);
		} catch (Exception e) {
			return null;
		}
	}
	
/*	public static void main(String[] args) {
		UserPrincipal userPrincipal;
		
		if (args.length == 3 && args[0].equals("***")) {
			userPrincipal = new UserPrincipal(args[1], args[2]);
			
			System.out.println(userPrincipal.encode());
		} else if (args.length == 2 && args[0].equals("***")) {
			userPrincipal = decodeUserCredentials(args[1]);
			
			System.out.println(userPrincipal);
		}
		
		System.exit(0);
	}
	public static void main(String[] args){
		UserPrincipal user = new UserPrincipal("70016160-9","4232");
		String encodedUserCredentials="";
		for(int i =0; i<1000;i++){
			encodedUserCredentials = user.encode();
			System.out.println(i);
			UserPrincipal user2 = UserPrincipal.decodeUserCredentials(encodedUserCredentials);
			System.out.println("Usuario: "+ user2.getName());
			System.out.println("Password: " + user2.getPassword());
		}
		
		System.out.println("Credenciales encriptadas: " + encodedUserCredentials);
		//UserPrincipal user2 = UserPrincipal.decodeUserCredentials(encodedUserCredentials);
		
		
		
	}*/
}
