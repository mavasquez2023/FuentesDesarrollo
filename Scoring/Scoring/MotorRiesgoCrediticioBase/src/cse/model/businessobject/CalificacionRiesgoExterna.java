package cse.model.businessobject;

public class CalificacionRiesgoExterna {
	
	private String nombre;
	private String descripcion;
	private Integer valor;
	private String fecNac;
	private String genero;
	private String estCivil;
	
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setFecNac(String fecNac) {
		this.fecNac = fecNac;
	}
	public String getFecNac() {
		return fecNac;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getGenero() {
		return genero;
	}
	public void setEstCivil(String estCivil) {
		this.estCivil = estCivil;
	}
	public String getEstCivil() {
		return estCivil;
	}
	
	
}
