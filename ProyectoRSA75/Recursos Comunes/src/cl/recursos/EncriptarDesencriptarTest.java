package cl.recursos;

import cl.araucana.core.util.UserPrincipal;




public class EncriptarDesencriptarTest {
	public static void main(String[] args) {
		//Encriptar
		UserPrincipal usuario = new UserPrincipal("USRWASSI","USR60SISTE");
		String credEnc = usuario.encode();
		System.out.println(credEnc);
		
		//Desencriptar
		UserPrincipal admin2 = UserPrincipal.decodeUserCredentials(credEnc);
		
		System.out.println("Usuario: " + admin2.getName());
		System.out.println("Password: " + admin2.getPassword());
	}
}
