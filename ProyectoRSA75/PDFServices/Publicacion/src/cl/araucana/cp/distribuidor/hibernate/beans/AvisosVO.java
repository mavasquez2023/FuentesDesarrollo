package cl.araucana.cp.distribuidor.hibernate.beans;

import cl.araucana.cp.distribuidor.hibernate.beans.AuditableVO;

public class AvisosVO extends AuditableVO
{
	private static final long serialVersionUID = 1L;
	
	private int idAvisos;
	private String titulo;
	private int estado;
	private String contenido;
	private String link;
	private int ancho;
	private int alto;
	
	public AvisosVO(){}
	
	public String getContenido() {
		return this.contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public int getEstado() {
		return this.estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getLink() {
		return this.link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * @return this.el alto
	 */
	public int getAlto() {
		return this.alto;
	}

	/**
	 * @param alto el alto a establecer
	 */
	public void setAlto(int alto) {
		this.alto = alto;
	}

	/**
	 * @return this.el ancho
	 */
	public int getAncho() {
		return this.ancho;
	}

	/**
	 * @param ancho el ancho a establecer
	 */
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	public String getTitulo() {
		return this.titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * @return this.el idAvisos
	 */
	public int getIdAvisos() {
		return this.idAvisos;
	}
	/**
	 * @param idAvisos el idAvisos a establecer
	 */
	public void setIdAvisos(int idAvisos) {
		this.idAvisos = idAvisos;
	}
	

}
