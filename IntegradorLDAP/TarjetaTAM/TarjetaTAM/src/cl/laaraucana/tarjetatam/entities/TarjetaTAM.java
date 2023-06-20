package cl.laaraucana.tarjetatam.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAM_AFILIADOS")
public class TarjetaTAM implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "RutAfiliado")
	private int rut;
	@Column(name = "dvAfiliado")
	private String dv;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "fechaNacimiento")
	private int fechaNacimiento;
	@Column(name = "telefono")
	private String telefono;
	@Column(name = "email")
	private String email;
	@Column(name = "idComuna")
	private int idComuna;
	@Column(name = "idRegion")
	private int idRegion;
	@Column(name = "direccion")
	private String direccion;
	@Column(name = "numero")
	private String numero;
	@Column(name = "depto")
	private String depto;
	@Column(name = "referencia")
	private String referencia;
	@Column(name = "estadoTarjeta")
	private int estadoTarjeta;
	@Column(name = "fechaSolicitud")
	private Date fechaSolicitud;
	@Column(name = "usuarioCreacion")
	private String usuarioCreacion;
	@Column(name = "idComunaDespacho")
	private int idComunaDespacho;
	@Column(name = "idRegionDespacho")
	private int idRegionDespacho;
	@Column(name = "direccionDespacho")
	private String direccionDespacho;
	@Column(name = "numeroDespacho")
	private String numeroDespacho;
	@Column(name = "deptoDespacho")
	private String deptoDespacho;
	@Column(name = "referenciaDespacho")
	private String referenciaDespacho;
	@Column(name = "idOficinaDespacho")
	private int idOficinaDespacho;
	@Column(name = "fechaDespacho")
	private Date fechaDespacho;
	@Column(name = "fechaEnvioCorreos")
	private Date fechaEnvioCorreos;
	@Column(name = "numeroTarjeta")
	private long numeroTarjeta;
	@Column(name = "idOficinaEntrega")
	private int idOficinaEntrega;
	@Column(name = "fechaEntrega")
	private Date fechaEntrega;
	@Column(name = "usuarioEntrega")
	private String usuarioEntrega;
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
	public int getRut() {
		return rut;
	}
	/**
	 * @param rut the rut to set
	 */
	public void setRut(int rut) {
		this.rut = rut;
	}
	/**
	 * @return the dv
	 */
	public String getDv() {
		return dv;
	}
	/**
	 * @param dv the dv to set
	 */
	public void setDv(String dv) {
		this.dv = dv;
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
	 * @return the fechaNacimiento
	 */
	public int getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(int fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
	 * @return the idComuna
	 */
	public int getIdComuna() {
		return idComuna;
	}
	/**
	 * @param idComuna the idComuna to set
	 */
	public void setIdComuna(int idComuna) {
		this.idComuna = idComuna;
	}
	/**
	 * @return the idRegion
	 */
	public int getIdRegion() {
		return idRegion;
	}
	/**
	 * @param idRegion the idRegion to set
	 */
	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	/**
	 * @return the depto
	 */
	public String getDepto() {
		return depto;
	}
	/**
	 * @param depto the depto to set
	 */
	public void setDepto(String depto) {
		this.depto = depto;
	}
	/**
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}
	/**
	 * @param referencia the referencia to set
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	/**
	 * @return the estadoTarjeta
	 */
	public int getEstadoTarjeta() {
		return estadoTarjeta;
	}
	/**
	 * @param estadoTarjeta the estadoTarjeta to set
	 */
	public void setEstadoTarjeta(int estadoTarjeta) {
		this.estadoTarjeta = estadoTarjeta;
	}
	/**
	 * @return the fechaSolicitud
	 */
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}
	/**
	 * @param fechaSolicitud the fechaSolicitud to set
	 */
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	/**
	 * @return the usuarioCreacion
	 */
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	/**
	 * @param usuarioCreacion the usuarioCreacion to set
	 */
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	/**
	 * @return the idComunaDespacho
	 */
	public int getIdComunaDespacho() {
		return idComunaDespacho;
	}
	/**
	 * @param idComunaDespacho the idComunaDespacho to set
	 */
	public void setIdComunaDespacho(int idComunaDespacho) {
		this.idComunaDespacho = idComunaDespacho;
	}
	/**
	 * @return the idRegionDespacho
	 */
	public int getIdRegionDespacho() {
		return idRegionDespacho;
	}
	/**
	 * @param idRegionDespacho the idRegionDespacho to set
	 */
	public void setIdRegionDespacho(int idRegionDespacho) {
		this.idRegionDespacho = idRegionDespacho;
	}
	/**
	 * @return the direccionDespacho
	 */
	public String getDireccionDespacho() {
		return direccionDespacho;
	}
	/**
	 * @param direccionDespacho the direccionDespacho to set
	 */
	public void setDireccionDespacho(String direccionDespacho) {
		this.direccionDespacho = direccionDespacho;
	}
	/**
	 * @return the numeroDespacho
	 */
	public String getNumeroDespacho() {
		return numeroDespacho;
	}
	/**
	 * @param numeroDespacho the numeroDespacho to set
	 */
	public void setNumeroDespacho(String numeroDespacho) {
		this.numeroDespacho = numeroDespacho;
	}
	/**
	 * @return the deptoDespacho
	 */
	public String getDeptoDespacho() {
		return deptoDespacho;
	}
	/**
	 * @param deptoDespacho the deptoDespacho to set
	 */
	public void setDeptoDespacho(String deptoDespacho) {
		this.deptoDespacho = deptoDespacho;
	}
	/**
	 * @return the referenciaDespacho
	 */
	public String getReferenciaDespacho() {
		return referenciaDespacho;
	}
	/**
	 * @param referenciaDespacho the referenciaDespacho to set
	 */
	public void setReferenciaDespacho(String referenciaDespacho) {
		this.referenciaDespacho = referenciaDespacho;
	}
	/**
	 * @return the idOficinaDespacho
	 */
	public int getIdOficinaDespacho() {
		return idOficinaDespacho;
	}
	/**
	 * @param idOficinaDespacho the idOficinaDespacho to set
	 */
	public void setIdOficinaDespacho(int idOficinaDespacho) {
		this.idOficinaDespacho = idOficinaDespacho;
	}
	/**
	 * @return the fechaDespacho
	 */
	public Date getFechaDespacho() {
		return fechaDespacho;
	}
	/**
	 * @param fechaDespacho the fechaDespacho to set
	 */
	public void setFechaDespacho(Date fechaDespacho) {
		this.fechaDespacho = fechaDespacho;
	}
	/**
	 * @return the fechaEnvioCorreos
	 */
	public Date getFechaEnvioCorreos() {
		return fechaEnvioCorreos;
	}
	/**
	 * @param fechaEnvioCorreos the fechaEnvioCorreos to set
	 */
	public void setFechaEnvioCorreos(Date fechaEnvioCorreos) {
		this.fechaEnvioCorreos = fechaEnvioCorreos;
	}
	/**
	 * @return the numeroTarjeta
	 */
	public long getNumeroTarjeta() {
		return numeroTarjeta;
	}
	/**
	 * @param numeroTarjeta the numeroTarjeta to set
	 */
	public void setNumeroTarjeta(long numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	/**
	 * @return the idOficinaEntrega
	 */
	public int getIdOficinaEntrega() {
		return idOficinaEntrega;
	}
	/**
	 * @param idOficinaEntrega the idOficinaEntrega to set
	 */
	public void setIdOficinaEntrega(int idOficinaEntrega) {
		this.idOficinaEntrega = idOficinaEntrega;
	}
	/**
	 * @return the fechaEntrega
	 */
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	/**
	 * @param fechaEntrega the fechaEntrega to set
	 */
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	/**
	 * @return the usuarioEntrega
	 */
	public String getUsuarioEntrega() {
		return usuarioEntrega;
	}
	/**
	 * @param usuarioEntrega the usuarioEntrega to set
	 */
	public void setUsuarioEntrega(String usuarioEntrega) {
		this.usuarioEntrega = usuarioEntrega;
	}
	
	
}
