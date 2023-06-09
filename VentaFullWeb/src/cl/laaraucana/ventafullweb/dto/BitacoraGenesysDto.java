package cl.laaraucana.ventafullweb.dto;

import java.io.Serializable;
import java.util.Date;

public class BitacoraGenesysDto implements Serializable {
	
	
	private int rutAfiliado;
	private String dvAfiliado;
	private String celular;
	private Date fechaAgenda;
	private String idConversation;
	private int estado;
	private Date fechaCreacion;
	private String idEncode;
	private String autorizar;
	private String aceptar;
	
	/**
	 * @return the rutAfiliado
	 */
	public int getRutAfiliado() {
		return rutAfiliado;
	}
	/**
	 * @param rutAfiliado the rutAfiliado to set
	 */
	public void setRutAfiliado(int rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}
	/**
	 * @return the dvAfiliado
	 */
	public String getDvAfiliado() {
		return dvAfiliado;
	}
	/**
	 * @param dvAfiliado the dvAfiliado to set
	 */
	public void setDvAfiliado(String dvAfiliado) {
		this.dvAfiliado = dvAfiliado;
	}
	/**
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}
	/**
	 * @param celular the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}
	/**
	 * @return the fechaAgenda
	 */
	public Date getFechaAgenda() {
		return fechaAgenda;
	}
	/**
	 * @param fechaAgenda the fechaAgenda to set
	 */
	public void setFechaAgenda(Date fechaAgenda) {
		this.fechaAgenda = fechaAgenda;
	}
	/**
	 * @return the idConversation
	 */
	public String getIdConversation() {
		return idConversation;
	}
	/**
	 * @param idConversation the idConversation to set
	 */
	public void setIdConversation(String idConversation) {
		this.idConversation = idConversation;
	}
	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @return the idEncode
	 */
	public String getIdEncode() {
		return idEncode;
	}
	/**
	 * @param idEncode the idEncode to set
	 */
	public void setIdEncode(String idEncode) {
		this.idEncode = idEncode;
	}
	/**
	 * @return the autorizar
	 */
	public String getAutorizar() {
		return autorizar;
	}
	/**
	 * @param autorizar the autorizar to set
	 */
	public void setAutorizar(String autorizar) {
		this.autorizar = autorizar;
	}
	/**
	 * @return the aceptar
	 */
	public String getAceptar() {
		return aceptar;
	}
	/**
	 * @param aceptar the aceptar to set
	 */
	public void setAceptar(String aceptar) {
		this.aceptar = aceptar;
	}
	
	
		
}
