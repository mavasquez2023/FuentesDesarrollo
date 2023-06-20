package cl.laaraucana.licenciascompin.entities;

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
@Table(name = "RegistroLicencias")
public class RegistroLicencias implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "rutAfi")
	private String rut;
	@Column(name = "nomafi")
	private String nombre;
	@Transient
	private String serie;
	@Column(name = "telefono")
	private String telefono;
	@Column(name = "email")
	private String email;
	@Column(name = "IdComuna")
	private long comuna;
	@Column(name = "idRegion")
	private long region;
	@Column(name = "folioLicencia")
	private String folioLicencia;
	@Column(name = "fecha")
	private Date fechacre;
	@Column(name = "viaIngreso")
	private String tipoAfiliado;
	@Column(name = "esMaternal")
	private String esMaternal;
	@Transient
	private String opcion;
	@Column(name = "sucursal")
	private String sucursal;
	@Column(name = "tipoLicencia")
	private String tipoLicencia;
	@Column(name = "estado")
	private String estado;
	@Transient
	private String rutCliente;
	@Transient
	private String notas;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getComuna() {
		return comuna;
	}

	public void setComuna(long comuna) {
		this.comuna = comuna;
	}

	public long getRegion() {
		return region;
	}

	public void setRegion(long region) {
		this.region = region;
	}

	public String getFolioLicencia() {
		return folioLicencia;
	}

	public void setFolioLicencia(String folioLicencia) {
		this.folioLicencia = folioLicencia;
	}

	public Date getFechacre() {
		return fechacre;
	}

	public void setFechacre(Date fechacre) {
		this.fechacre = fechacre;
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
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
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
	 * @return the rutCliente
	 */
	public String getRutCliente() {
		return rutCliente;
	}

	/**
	 * @param rutCliente the rutCliente to set
	 */
	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}

	/**
	 * @return the notas
	 */
	public String getNotas() {
		return notas;
	}

	/**
	 * @param notas the notas to set
	 */
	public void setNotas(String notas) {
		this.notas = notas;
	}
	
	
	
}
