package cl.laaraucana.apofam.entities;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "AporteFamiliar_Bitacora")
public class Bitacora implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "RutAfiliado")
	private int rutAfiliado;
	@Column(name = "DvAfiliado")
	private String dvAfiliado;
	@Column(name = "NombreAfiliado")
	private String nombreAfiliado;
	@Column(name = "NumCargas")
	private int numeroCargas;
	@Column(name = "FechaDescarga")
	private Date fechaDescarga;
	@Column(name = "UsuarioDescarga")
	private String usuarioDescarga;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
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
	 * @return the nombreAfiliado
	 */
	public String getNombreAfiliado() {
		return nombreAfiliado;
	}
	/**
	 * @param nombreAfiliado the nombreAfiliado to set
	 */
	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}
	/**
	 * @return the numeroCargas
	 */
	public int getNumeroCargas() {
		return numeroCargas;
	}
	/**
	 * @param numeroCargas the numeroCargas to set
	 */
	public void setNumeroCargas(int numeroCargas) {
		this.numeroCargas = numeroCargas;
	}
	/**
	 * @return the fechaDescarga
	 */
	public Date getFechaDescarga() {
		return fechaDescarga;
	}
	/**
	 * @param fechaDescarga the fechaDescarga to set
	 */
	public void setFechaDescarga(Date fechaDescarga) {
		this.fechaDescarga = fechaDescarga;
	}
	/**
	 * @return the usuarioDescarga
	 */
	public String getUsuarioDescarga() {
		return usuarioDescarga;
	}
	/**
	 * @param usuarioDescarga the usuarioDescarga to set
	 */
	public void setUsuarioDescarga(String usuarioDescarga) {
		this.usuarioDescarga = usuarioDescarga;
	}
	
	
}
