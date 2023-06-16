package cl.laaraucana.botonpago.web.vo;

public class Rol {
	private String rol;
	private String label;

	public Rol(String rol, String label) {
		super();
		this.rol = rol;
		this.label = label;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
