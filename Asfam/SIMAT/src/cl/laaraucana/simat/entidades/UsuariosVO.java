package cl.laaraucana.simat.entidades;

public class UsuariosVO {
	private int id;
	private String nombre_usuario;
	private String acceso;
	private String ultima_coneccion;

	public UsuariosVO() {
	}

	//Timestamp
	public UsuariosVO(int id, String nombre_usuario, String acceso, String ultima_coneccion) {
		super();
		this.id = id;
		this.nombre_usuario = nombre_usuario;
		this.acceso = acceso;
		this.ultima_coneccion = ultima_coneccion;
	}

	public String getAcceso() {
		return acceso;
	}

	public String getId() {
		return String.valueOf(id);
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public String getUltima_coneccion() {
		return String.valueOf(ultima_coneccion);
	}

	public void setAcceso(String acceso) {
		this.acceso = acceso;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public void setUltima_coneccion(String ultima_coneccion) {

		this.ultima_coneccion = ultima_coneccion;

	}

}
