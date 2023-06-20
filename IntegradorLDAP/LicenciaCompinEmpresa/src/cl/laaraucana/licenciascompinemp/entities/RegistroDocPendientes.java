package cl.laaraucana.licenciascompinemp.entities;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
//@Table(name = "RegistroLicencias")
@Table(name = "RegistroDocumentosPendientesLM")
public class RegistroDocPendientes implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "RUTAFI")
	private String rut;
	@Column(name = "NOMAFI")
	private String nombre;
	@Column(name = "TELEFONO")
	private String telefono;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "FOLIOLICENCIA")
	private String folioLicencia;
	@Column(name = "FOLIOINTERNO")
	private String FolioInterno;
	@Column(name = "NOMFILE")
	private String nombreArchivoEnviado;
	@Column(name = "VIAINGRESO")
	private String tipoAfiliado;
	@Column(name = "ESMATERNAL")
	private String esMaternal;
	@Column(name = "RUTENCARGADOEMP")
	private String rutEncargadoEmpresa;
	@Column(name = "RUTEMP")
	private String rutEmpresaSeleccionada;
	@Column(name = "EMAILEJECUTIVO")
	private String emailEjecutivo;
	@Column(name = "FECHA")
	private Date fechacre;
	@Column(name = "estado")
	private String estado;
	@Column(name = "tipoLicencia")
	private String tipoLicencia;
	
	@Transient
	private String opcion;
	@Transient
	private String serie;
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
	 * @return the rut
	 */
	public String getRut() {
		return rut;
	}
	/**
	 * @param rut the rut to set
	 */
	public void setRut(String rut) {
		this.rut = rut;
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
	 * @return the folioLicencia
	 */
	public String getFolioLicencia() {
		return folioLicencia;
	}
	/**
	 * @param folioLicencia the folioLicencia to set
	 */
	public void setFolioLicencia(String folioLicencia) {
		this.folioLicencia = folioLicencia;
	}
	/**
	 * @return the folioInterno
	 */
	public String getFolioInterno() {
		return FolioInterno;
	}
	/**
	 * @param folioInterno the folioInterno to set
	 */
	public void setFolioInterno(String folioInterno) {
		FolioInterno = folioInterno;
	}
	/**
	 * @return the nombreArchivoEnviado
	 */
	public String getNombreArchivoEnviado() {
		return nombreArchivoEnviado;
	}
	/**
	 * @param nombreArchivoEnviado the nombreArchivoEnviado to set
	 */
	public void setNombreArchivoEnviado(String nombreArchivoEnviado) {
		this.nombreArchivoEnviado = nombreArchivoEnviado;
	}
	/**
	 * @return the tipoAfiliado
	 */
	public String getTipoAfiliado() {
		return tipoAfiliado;
	}
	/**
	 * @param tipoAfiliado the tipoAfiliado to set
	 */
	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}
	/**
	 * @return the esMaternal
	 */
	public String getEsMaternal() {
		return esMaternal;
	}
	/**
	 * @param esMaternal the esMaternal to set
	 */
	public void setEsMaternal(String esMaternal) {
		this.esMaternal = esMaternal;
	}
	/**
	 * @return the rutEncargadoEmpresa
	 */
	public String getRutEncargadoEmpresa() {
		return rutEncargadoEmpresa;
	}
	/**
	 * @param rutEncargadoEmpresa the rutEncargadoEmpresa to set
	 */
	public void setRutEncargadoEmpresa(String rutEncargadoEmpresa) {
		this.rutEncargadoEmpresa = rutEncargadoEmpresa;
	}
	/**
	 * @return the rutEmpresaSeleccionada
	 */
	public String getRutEmpresaSeleccionada() {
		return rutEmpresaSeleccionada;
	}
	/**
	 * @param rutEmpresaSeleccionada the rutEmpresaSeleccionada to set
	 */
	public void setRutEmpresaSeleccionada(String rutEmpresaSeleccionada) {
		this.rutEmpresaSeleccionada = rutEmpresaSeleccionada;
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
	/**
	 * @return the fechacre
	 */
	public Date getFechacre() {
		return fechacre;
	}
	/**
	 * @param fechacre the fechacre to set
	 */
	public void setFechacre(Date fechacre) {
		this.fechacre = fechacre;
	}
	/**
	 * @return the opcion
	 */
	public String getOpcion() {
		return opcion;
	}
	/**
	 * @param opcion the opcion to set
	 */
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
	/**
	 * @return the serie
	 */
	public String getSerie() {
		return serie;
	}
	/**
	 * @param serie the serie to set
	 */
	public void setSerie(String serie) {
		this.serie = serie;
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
	 * @return the tipoLicencia
	 */
	public String getTipoLicencia() {
		return tipoLicencia;
	}
	/**
	 * @param tipoLicencia the tipoLicencia to set
	 */
	public void setTipoLicencia(String tipoLicencia) {
		this.tipoLicencia = tipoLicencia;
	}
		
	
	}
