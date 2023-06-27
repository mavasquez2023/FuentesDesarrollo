package cl.araucana.cp.utils;


import java.io.Serializable;

/**
 * Encapsula datos de conexión a sistemas AS400.
 *
 * @author Alejandro Sepúlveda (asepulveda@schema.cl)
 * @version 1.0 
 */
public class ConexionAS400VO implements Serializable{

	
	private static final long serialVersionUID = 603984869949804230L;
	
	/** Tipo de Sistema */
	private String sistema;
	
	/** Usuario del Sistema */
	private String user;
	
	/** Password del Sistema */
	private String psw;
	
	
	public String getPsw() {
		return psw;
	}
	
	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getSistema() {
		return sistema;
	}
	
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
}
