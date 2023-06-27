package cl.araucana.cp.distribuidor.presentation.beans;

public class DTOcertificado {
	private String entidad = null;
	private String nombre = null;
	private String rutEntidad = null;
	private String dvEntidad = null;
	private Object total = null;
	
	
	
	public DTOcertificado() {
		super();
	}


	public DTOcertificado(String entidad, String nombre, String rut, String dv, Object total) {
		super();
		this.entidad = entidad;
		this.nombre = nombre;
		this.rutEntidad = rut;
		this.dvEntidad = dv;
		this.total = total;
	}
	
	
	public String getEntidad() {
		return entidad;
	}


	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}



	public String getDvEntidad() {
		return dvEntidad;
	}


	public void setDvEntidad(String dvEntidad) {
		this.dvEntidad = dvEntidad;
	}


	public String getRutEntidad() {
		return rutEntidad;
	}


	public void setRutEntidad(String rutEntidad) {
		this.rutEntidad = rutEntidad;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Object getTotal() {
		return total;
	}


	public void setTotal(Object total) {
		this.total = total;
	}

	
	
}
