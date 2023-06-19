package cl.laaraucana.benef.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "DATOS_BENEFICIO",
		propOrder = {"codigoBeneficio", "rutBeneficiario", "dvBeneficiario", "nombre", "apellidoPaterno", "apellidoMaterno", "email", "celular", "sucursalBeneficio", "fechaGeneracionBeneficio", "fechaEnvioComprobante", "estadoBeneficio", "fechaConfirmacionBeneficio"})
public class BeneficioVO implements Serializable{
	@XmlElement(name="CODIGO_BENEFICIO", required=true)
	private String codigoBeneficio;
	@XmlElement(name="RUT", required=true)
	private int rutBeneficiario;
	@XmlElement(name="DV", required=true)
	private String dvBeneficiario;
	@XmlElement(name="NOMBRE", required=true)
	private String nombre;
	@XmlElement(name="AP_PATERNO", required=true)
	private String apellidoPaterno;
	@XmlElement(name="AP_MATERNO", required=true)
	private String apellidoMaterno;
	@XmlElement(name="E_MAIL", required=true)
	private String email;
	@XmlElement(name="CELULAR", required=true)
	private String celular;
	@XmlElement(name="SUCURSAL", required=true)
	private String sucursalBeneficio;
	@XmlElement(name="FECHA_GENERACION_BENEFICIO", required=true)
	private String fechaGeneracionBeneficio;
	@XmlElement(name="FECHA_ENVIO_COMPROBANTE", required=true)
	private String fechaEnvioComprobante;
	@XmlElement(name="ESTADO_CODIGO", required=true)
	private String estadoBeneficio;
	@XmlElement(name="FECHA_CONFIRMACION_CODIGO", required=true)
	private String fechaConfirmacionBeneficio;
	
	
	/**
	 * @return the codigoBeneficio
	 */
	public String getCodigoBeneficio() {
		return codigoBeneficio;
	}
	/**
	 * @param codigoBeneficio the codigoBeneficio to set
	 */
	public void setCodigoBeneficio(String codigoBeneficio) {
		this.codigoBeneficio = codigoBeneficio;
	}
	/**
	 * @return the rutBeneficiario
	 */
	public int getRutBeneficiario() {
		return rutBeneficiario;
	}
	/**
	 * @param rutBeneficiario the rutBeneficiario to set
	 */
	public void setRutBeneficiario(int rutBeneficiario) {
		this.rutBeneficiario = rutBeneficiario;
	}
	/**
	 * @return the dvBeneficiario
	 */
	public String getDvBeneficiario() {
		return dvBeneficiario;
	}
	/**
	 * @param dvBeneficiario the dvBeneficiario to set
	 */
	public void setDvBeneficiario(String dvBeneficiario) {
		this.dvBeneficiario = dvBeneficiario;
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
	 * @return the apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	/**
	 * @param apellidoPaterno the apellidoPaterno to set
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	/**
	 * @return the apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	/**
	 * @param apellidoMaterno the apellidoMaterno to set
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
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
	 * @return the sucursalBeneficio
	 */
	public String getSucursalBeneficio() {
		return sucursalBeneficio;
	}
	/**
	 * @param sucursalBeneficio the sucursalBeneficio to set
	 */
	public void setSucursalBeneficio(String sucursalBeneficio) {
		this.sucursalBeneficio = sucursalBeneficio;
	}
	/**
	 * @return the fechaGeneracionBeneficio
	 */
	public String getFechaGeneracionBeneficio() {
		return fechaGeneracionBeneficio;
	}
	/**
	 * @param fechaGeneracionBeneficio the fechaGeneracionBeneficio to set
	 */
	public void setFechaGeneracionBeneficio(String fechaGeneracionBeneficio) {
		this.fechaGeneracionBeneficio = fechaGeneracionBeneficio;
	}
	/**
	 * @return the fechaEnvioComprobante
	 */
	public String getFechaEnvioComprobante() {
		return fechaEnvioComprobante;
	}
	/**
	 * @param fechaEnvioComprobante the fechaEnvioComprobante to set
	 */
	public void setFechaEnvioComprobante(String fechaEnvioComprobante) {
		this.fechaEnvioComprobante = fechaEnvioComprobante;
	}
	/**
	 * @return the estadoBeneficio
	 */
	public String getEstadoBeneficio() {
		return estadoBeneficio;
	}
	/**
	 * @param estadoBeneficio the estadoBeneficio to set
	 */
	public void setEstadoBeneficio(String estadoBeneficio) {
		this.estadoBeneficio = estadoBeneficio;
	}
	/**
	 * @return the fechaConfirmacionBeneficio
	 */
	public String getFechaConfirmacionBeneficio() {
		return fechaConfirmacionBeneficio;
	}
	/**
	 * @param fechaConfirmacionBeneficio the fechaConfirmacionBeneficio to set
	 */
	public void setFechaConfirmacionBeneficio(String fechaConfirmacionBeneficio) {
		this.fechaConfirmacionBeneficio = fechaConfirmacionBeneficio;
	}
	
	
	
}
