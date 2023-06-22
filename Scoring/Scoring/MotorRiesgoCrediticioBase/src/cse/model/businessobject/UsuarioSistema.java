package cse.model.businessobject;

/**
 * UsuarioSistema business object. 
 * 
 */
public class UsuarioSistema {
	private String username;
	private String password;
	
	/**
	 * Default constructor.
	 */
	public UsuarioSistema() {
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String newUsername) {
		this.username = newUsername;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}
}
