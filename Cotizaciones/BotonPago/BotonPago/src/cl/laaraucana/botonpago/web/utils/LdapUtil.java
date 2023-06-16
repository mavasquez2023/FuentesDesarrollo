package cl.laaraucana.botonpago.web.utils;

import java.util.Hashtable;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;

import org.apache.log4j.Logger;

import cl.araucana.core.util.UserPrincipal;

public class LdapUtil {
	Logger logger = Logger.getLogger(this.getClass());

	private String ldapURL;
	private String userAdmin;
	private String passAdmin;

	/*	public boolean autenticarUsuario(String usuario, String password) {
			Hashtable auth = new Hashtable(11);
			String base = "ou=webusers,o=araucana,c=cl";
			String dn = "cn=" + usuario + "," + base;

			auth.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			auth.put(Context.PROVIDER_URL, ldapURL);
			auth.put(Context.SECURITY_AUTHENTICATION, "simple");
			auth.put(Context.SECURITY_PRINCIPAL, dn);
			auth.put(Context.SECURITY_CREDENTIALS, password);

			try {
				DirContext authContext = new InitialDirContext(auth);
				return true;
			} catch (AuthenticationException authEx) {
				return false;

			} catch (NamingException namEx) {
				namEx.printStackTrace();
				return false;
			}
	    }*/

	private void initParams() {
		ResourceBundle config = ResourceBundle.getBundle("etc.userRegistry.userRegistry");
		UserPrincipal admin = UserPrincipal.decodeUserCredentials(config.getString("ldap.specialAccess.credentials"));
		this.userAdmin = admin.getName();
		this.passAdmin = admin.getPassword();
		this.ldapURL = config.getString("ldap.ProviderURL");
	}

	/**
	 * Modifica la contraseña de usuario dentro del grupo 'ou=webusers' de Ldap
	 * @param usuario
	 * @param newPassword
	 * @return true si se cambió exitosamente la password
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean changePassword(String usuario, String newPassword) {
		initParams();
		Hashtable auth = new Hashtable(11);
		String dn = "cn=" + this.userAdmin;

		auth.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		auth.put(Context.PROVIDER_URL, this.ldapURL);
		auth.put(Context.SECURITY_AUTHENTICATION, "simple");
		auth.put(Context.SECURITY_PRINCIPAL, dn);
		auth.put(Context.SECURITY_CREDENTIALS, this.passAdmin);

		try {
			// Create the initial directory context
			InitialDirContext initialContext = new InitialDirContext(auth);
			DirContext ctx = (DirContext) initialContext;
			ModificationItem[] mods = new ModificationItem[1];
			Attribute mod0 = new BasicAttribute("userPassword", newPassword);
			mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, mod0);
			String base = "ou=webusers,o=araucana,c=cl";
			ctx.modifyAttributes("cn=" + usuario + "," + base, mods);
			logger.debug("Contraseña de usuario: " + usuario + " cambiada correctamente");
			return true;
		} catch (Exception e) {
			logger.debug("Error al cambiar la contraseña de usuario: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		LdapUtil ldap = new LdapUtil();
		ldap.changePassword("17504669-0", "0987");
	}

}
