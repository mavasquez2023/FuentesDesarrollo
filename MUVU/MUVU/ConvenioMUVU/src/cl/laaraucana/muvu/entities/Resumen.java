package cl.laaraucana.muvu.entities;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Date;

@Entity
@Table(name = "ResumenMUVU")
public class Resumen implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "RUT_AFILIADO")
	private long rutAfiliado;
	@Column(name = "DV_AFILIADO")
	private String dvAfiliado;
	@Column(name = "CORREO_ELECTRONICO")
	private String email;
	@Column(name = "FECHA_NACIMIENTO")
	private Date fechaNacimiento;
	@Column(name = "FECHA_INCORPORACION")
	private Date fechaInscripcion;
	@Column(name = "FECHA_ENROLAMIENTO")
	private Date fechaEnrolamiento;
	@Column(name = "FECHA_ACT_FISICA")
	private Date fechaActFisica;
	@Column(name = "FECHA_ALTA")
	private Date fechaAlta;
	@Column(name = "FECHA_BAJA")
	private Date fechaBaja;
	@Column(name = "ESTADO_FINAL")
	private String estado;
	@Column(name = "MOTIVO_DE_BAJA")
	private String motivo;
	@Transient
	private String nombreAfiliado;
	
	/**
	 * @return the rutAfiliado
	 */
	public long getRutAfiliado() {
		return rutAfiliado;
	}
	/**
	 * @param rutAfiliado the rutAfiliado to set
	 */
	public void setRutAfiliado(long rutAfiliado) {
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
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	/**
	 * @return the fechaInscripcion
	 */
	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}
	/**
	 * @param region the fechaInscripcion to set
	 */
	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	/**
	 * @return the fechaEnrolamiento
	 */
	public Date getFechaEnrolamiento() {
		return fechaEnrolamiento;
	}
	/**
	 * @param fechaEnrolamiento the fechaEnrolamiento to set
	 */
	public void setFechaEnrolamiento(Date fechaEnrolamiento) {
		this.fechaEnrolamiento = fechaEnrolamiento;
	}
	/**
	 * @return the fechaActFisica
	 */
	public Date getFechaActFisica() {
		return fechaActFisica;
	}
	/**
	 * @param fechaActFisica the fechaActFisica to set
	 */
	public void setFechaActFisica(Date fechaActFisica) {
		this.fechaActFisica = fechaActFisica;
	}
	/**
	 * @return the fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}
	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	/**
	 * @return the fechaBaja
	 */
	public Date getFechaBaja() {
		return fechaBaja;
	}
	/**
	 * @param fechaBaja the fechaBaja to set
	 */
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}
	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
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
	
	
}
