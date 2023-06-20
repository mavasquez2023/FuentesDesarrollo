package cl.laaraucana.dsiniestro.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Sucursales")
public class Sucursal implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "codigo")
	private String codigoSucursal;
	@Column(name = "Descripcion")
	private String descripcion;
	@Column(name = "email")
	private String emailEjecutivo;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the codigoSucursal
	 */
	public String getCodigoSucursal() {
		return codigoSucursal;
	}
	/**
	 * @param codigoSucursal the codigoSucursal to set
	 */
	public void setCodigoSucursal(String codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the emailEjecutivo
	 */
	public String getEmailEjecutivo() {
		return emailEjecutivo;
	}
	/**
	 * @param emailEjecutivo the emailEjecutivo to set
	 */
	public void setEmailEjecutivo(String emailEjecutivo) {
		this.emailEjecutivo = emailEjecutivo;
	}
	
	
}
