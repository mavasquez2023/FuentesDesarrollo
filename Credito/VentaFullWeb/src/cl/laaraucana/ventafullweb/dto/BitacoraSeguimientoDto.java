package cl.laaraucana.ventafullweb.dto;

import java.io.Serializable;
import java.util.Date;

public class BitacoraSeguimientoDto implements Serializable {	
	
	private String rutAfiliado;
	private String dVRutAfiliado;
	private String rutEmpresa;
	private String dVRutEmpresa;
	private String fechaSeguimiento;
	private String accion;
	private String servicio;
	private String resultado;
	
	/**
	 * @return the rutAfiliado
	 */
	public String getRutAfiliado() {
		return rutAfiliado;
	}
	/**
	 * @param rutAfiliado the rutAfiliado to set
	 */
	public void setRutAfiliado(String rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}
	/**
	 * @return the dVRutAfiliado
	 */
	public String getdVRutAfiliado() {
		return dVRutAfiliado;
	}
	/**
	 * @param dVRutAfiliado the dVRutAfiliado to set
	 */
	public void setdVRutAfiliado(String dVRutAfiliado) {
		this.dVRutAfiliado = dVRutAfiliado;
	}
	/**
	 * @return the rutEmpresa
	 */
	public String getRutEmpresa() {
		return rutEmpresa;
	}
	/**
	 * @param rutEmpresa the rutEmpresa to set
	 */
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	/**
	 * @return the dVRutEmpresa
	 */
	public String getdVRutEmpresa() {
		return dVRutEmpresa;
	}
	/**
	 * @param dVRutEmpresa the dVRutEmpresa to set
	 */
	public void setdVRutEmpresa(String dVRutEmpresa) {
		this.dVRutEmpresa = dVRutEmpresa;
	}
	/**
	 * @return the fechaSeguimiento
	 */
	public String getFechaSeguimiento() {
		return fechaSeguimiento;
	}
	/**
	 * @param fechaSeguimiento the fechaSeguimiento to set
	 */
	public void setFechaSeguimiento(String fechaSeguimiento) {
		this.fechaSeguimiento = fechaSeguimiento;
	}
	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}
	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}
	/**
	 * @return the servicio
	 */
	public String getServicio() {
		return servicio;
	}
	/**
	 * @param servicio the servicio to set
	 */
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	/**
	 * @return the resultado
	 */
	public String getResultado() {
		return resultado;
	}
	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
}
