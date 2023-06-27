package cl.araucana.wsatento.business.to;

import java.util.List;


public class UsuarioTO {
	private Integer uId;
	private String nombre;
	private String usuario;
	private String clave;
	private String estado;
	
	private EntidadTO entidad;
	private List listWebServices; 
	
	public UsuarioTO(){}
	
	public UsuarioTO(String usuario, String clave){
		this.usuario = usuario;
		this.clave = clave;
	}
	
	public UsuarioTO(Integer uId, String nombre, String usuario, String clave, String estado) {
		this.uId = uId;
		this.nombre = nombre;
		this.usuario = usuario;
		this.clave = clave;
		this.estado = estado;
	}
	
	public UsuarioTO(Integer uId, String nombre, String usuario, String clave, String estado, EntidadTO entidad, List listWebServices) {
		this.uId = uId;
		this.nombre = nombre;
		this.usuario = usuario;
		this.clave = clave;
		this.estado = estado;
		
		this.entidad = entidad;
		this.listWebServices = listWebServices;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getUId() {
		return uId;
	}

	public void setUId(Integer id) {
		uId = id;
	}

	public EntidadTO getEntidad() {
		return entidad;
	}

	public void setEntidad(EntidadTO entidad) {
		this.entidad = entidad;
	}

	public List getListWebServices() {
		return listWebServices;
	}

	public void setListWebServices(List listWebServices) {
		this.listWebServices = listWebServices;
	}
	
}
