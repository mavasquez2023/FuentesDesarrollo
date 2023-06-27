package cl.araucana.adminCpe.hibernate.beans;

import cl.araucana.cp.distribuidor.hibernate.beans.AuditableVO;

public class SPLMedioPagoVO extends AuditableVO {

	private static final long serialVersionUID = -7990300661637409289L;

	private int id;
	private int codBanco;
	private String codigo;
	private String descripcion;
	private int activo;
	private String imagen;
	private String urlInicioPago;
	public String getImagen() {
		return this.imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getUrlInicioPago() {
		return this.urlInicioPago;
	}
	public void setUrlInicioPago(String urlInicioPago) {
		this.urlInicioPago = urlInicioPago;
	}
	/**
	 * @return this.the activo
	 */
	public int getActivo() {
		return this.activo;
	}
	/**
	 * @param activo the activo to set
	 */
	public void setActivo(int activo) {
		this.activo = activo;
	}
	/**
	 * @return this.the codBanco
	 */
	public int getCodBanco() {
		return this.codBanco;
	}
	/**
	 * @param codBanco the codBanco to set
	 */
	public void setCodBanco(int codBanco) {
		this.codBanco = codBanco;
	}
	/**
	 * @return this.the descripcion
	 */
	public String getDescripcion() {
		return this.descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return this.the id
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return this.codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}
