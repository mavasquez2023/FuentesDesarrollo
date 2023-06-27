package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class ReembolsoVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;
	
	private long casoId=0;
	private String casos=null;
	private String estado=null;
	private Date fechaEstado=null;
	private Date fechaOcurrencia=null;
	private String indicadorReembolso=null;
	private String rut=null;
	private String dv=null;
	private String nombre=null;
	private String apellidoPaterno=null;
	private String apellidoMaterno=null;
	private String oficina=null;
	private double montoReembolso=0;
	private long codigoReembolso=0;
	private long folioTesoreriaBienestar=0;
	private long folioTesoreriaAraucana=0;
	private String banco;
	private String descripcionBanco;
	private String cuenta;
	private String tipoCuenta;
	private String correo;
	
	/**
	 * Retorna el rut compuesto del Socio
	 * @return String con el rut
	 */
	public String getFullRut() {
		return rut + "-" + dv;
	}

	/**
	 * Retorna el nombre completo del Socio
	 * @return String con el nombre completo
	 */
	public String getFullNombre() {
		return 	nombre + " " + apellidoPaterno + " " + apellidoMaterno;
	}

	/**
	 * @return
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * @return
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * @return
	 */
	public String getDv() {
		return dv;
	}

	/**
	 * @return
	 */
	public double getMontoReembolso() {
		return montoReembolso;
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return
	 */
	public String getOficina() {
		return oficina;
	}

	/**
	 * @return
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * @param string
	 */
	public void setApellidoMaterno(String string) {
		apellidoMaterno = string;
	}

	/**
	 * @param string
	 */
	public void setApellidoPaterno(String string) {
		apellidoPaterno = string;
	}

	/**
	 * @param string
	 */
	public void setDv(String string) {
		dv = string;
	}

	/**
	 * @param d
	 */
	public void setMontoReembolso(double d) {
		montoReembolso = d;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

	/**
	 * @param string
	 */
	public void setOficina(String string) {
		oficina = string;
	}

	/**
	 * @param string
	 */
	public void setRut(String string) {
		rut = string;
	}

	/**
	 * @return
	 */
	public long getCasoId() {
		return casoId;
	}

	/**
	 * @param l
	 */
	public void setCasoId(long l) {
		casoId = l;
	}

	/**
	 * @return
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @return
	 */
	public String getIndicadorReembolso() {
		return indicadorReembolso;
	}

	/**
	 * @param string
	 */
	public void setEstado(String string) {
		estado = string;
	}

	/**
	 * @param string
	 */
	public void setIndicadorReembolso(String string) {
		indicadorReembolso = string;
	}

	/**
	 * @return
	 */
	public Date getFechaEstado() {
		return fechaEstado;
	}

	/**
	 * @param date
	 */
	public void setFechaEstado(Date date) {
		fechaEstado = date;
	}

	/**
	 * @return
	 */
	public long getCodigoReembolso() {
		return codigoReembolso;
	}

	/**
	 * @param l
	 */
	public void setCodigoReembolso(long l) {
		codigoReembolso = l;
	}

	/**
	 * @return
	 */
	public long getFolioTesoreriaAraucana() {
		return folioTesoreriaAraucana;
	}

	/**
	 * @return
	 */
	public long getFolioTesoreriaBienestar() {
		return folioTesoreriaBienestar;
	}

	/**
	 * @param l
	 */
	public void setFolioTesoreriaAraucana(long l) {
		folioTesoreriaAraucana = l;
	}

	/**
	 * @param l
	 */
	public void setFolioTesoreriaBienestar(long l) {
		folioTesoreriaBienestar = l;
	}

	/**
	 * @return
	 */
	public String getCasos() {
		return casos;
	}

	/**
	 * @param string
	 */
	public void setCasos(String string) {
		casos = string;
	}

	/**
	 * @return
	 */
	public Date getFechaOcurrencia() {
		return fechaOcurrencia;
	}

	/**
	 * @param date
	 */
	public void setFechaOcurrencia(Date date) {
		fechaOcurrencia = date;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getDescripcionBanco() {
		return descripcionBanco;
	}

	public void setDescripcionBanco(String descripcionBanco) {
		this.descripcionBanco = descripcionBanco;
	}

}
