package cl.araucana.spl.action.roles;



import java.io.Serializable;

public class InfoUsuarioDVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1178692245825813839L;

	/** usuario**/
	private String usuario;
	/** Rut **/
	private String rut;
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	}
