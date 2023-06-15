package cl.laaraucana.simat.LDAP;

import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;

/*
 * clase que permite solicitar autenticacion en servidor LDAP
 * */

public class ValidadorLDAP {

	public boolean autenticacionLDAP(String user, String pass) throws Exception {
		boolean key = false;

		try {
			User usuario = null;
			System.out.println("instanciando coneccion");
			UserRegistryConnection conn = new UserRegistryConnection(user, pass);
			System.out.println("Obteniendo usuario");
			usuario = conn.getUser(user);
			usuario.getFullName();
			System.out.println("* * * * * LOGIN LDAP SIMAT: user= [" + usuario + "] * * * * * ");
			key = true;
		} catch (Exception e) {
			key = false;
		}

		return key;

	}

}
