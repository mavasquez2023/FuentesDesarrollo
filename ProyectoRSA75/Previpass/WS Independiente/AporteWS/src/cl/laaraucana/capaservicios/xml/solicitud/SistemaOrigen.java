package cl.laaraucana.capaservicios.xml.solicitud;

public class SistemaOrigen {
	private String id;
	private String nombre;
	private String username;
	private String password;
	private String urlRespuesta;
	private String flgEnviaMensajes;
	private String glosaCartolaCliente;

	public SistemaOrigen (){}

	public SistemaOrigen(String id, String nombre, String username, String password, String urlRespuesta, String flgEnviaMensajes, String glosaCartolaCliente) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.username = username;
		this.password = password;
		this.urlRespuesta = urlRespuesta;
		this.flgEnviaMensajes = flgEnviaMensajes;
		this.glosaCartolaCliente = glosaCartolaCliente;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUrlRespuesta() {
		return urlRespuesta;
	}


	public void setUrlRespuesta(String urlRespuesta) {
		this.urlRespuesta = urlRespuesta;
	}


	public String getFlgEnviaMensajes() {
		return flgEnviaMensajes;
	}


	public void setFlgEnviaMensajes(String flgEnviaMensajes) {
		this.flgEnviaMensajes = flgEnviaMensajes;
	}


	public String getGlosaCartolaCliente() {
		return glosaCartolaCliente;
	}


	public void setGlosaCartolaCliente(String glosaCartolaCliente) {
		this.glosaCartolaCliente = glosaCartolaCliente;
	}
	
	
}
