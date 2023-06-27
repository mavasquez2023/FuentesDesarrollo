package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class ReembolsoSocioVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;
	
	private String rut=null;
	private String dv=null;
	private String nombre=null;
	private String apellidoPaterno=null;
	private String apellidoMaterno=null;
	private double montoReembolso=0;
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

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
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

	public String getDv() {
		return dv;
	}

	public void setDv(String dv) {
		this.dv = dv;
	}

	public double getMontoReembolso() {
		return montoReembolso;
	}

	public void setMontoReembolso(double montoReembolso) {
		this.montoReembolso = montoReembolso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

}
