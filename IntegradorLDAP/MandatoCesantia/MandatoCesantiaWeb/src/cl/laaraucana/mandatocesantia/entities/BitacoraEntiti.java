package cl.laaraucana.mandatocesantia.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BitacoraMandatoCesantia")
public class BitacoraEntiti implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idBita")
	private long idBita;
	@Column(name = "RutCliente")
	private long rutCliente;
	@Column(name = "DvCliente")
	private String dvCliente;
	@Column(name = "Nombre")
	private String nombre;
	@Column(name = "Celular")
	private String celular;
	@Column(name = "Telefono")
	private String telefono;
	@Column(name = "Email")
	private String email;
	@Column(name = "Autorizado")
	private String autorizado;
	@Column(name = "Fecha")
	private Date fecha;
	/**
	 * @return the idBita
	 */
	public long getIdBita() {
		return idBita;
	}
	/**
	 * @param idBita the idBita to set
	 */
	public void setIdBita(long idBita) {
		this.idBita = idBita;
	}
	/**
	 * @return the rutCliente
	 */
	public long getRutCliente() {
		return rutCliente;
	}
	/**
	 * @param rutCliente the rutCliente to set
	 */
	public void setRutCliente(long rutCliente) {
		this.rutCliente = rutCliente;
	}
	/**
	 * @return the dvCliente
	 */
	public String getDvCliente() {
		return dvCliente;
	}
	/**
	 * @param dvCliente the dvCliente to set
	 */
	public void setDvCliente(String dvCliente) {
		this.dvCliente = dvCliente;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the autorizado
	 */
	public String getAutorizado() {
		return autorizado;
	}
	/**
	 * @param autorizado the autorizado to set
	 */
	public void setAutorizado(String autorizado) {
		this.autorizado = autorizado;
	}
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
}
