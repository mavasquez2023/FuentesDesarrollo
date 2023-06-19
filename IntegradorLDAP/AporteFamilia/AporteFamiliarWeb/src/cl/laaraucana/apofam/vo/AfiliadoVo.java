package cl.laaraucana.apofam.vo;

import java.util.Date;

public class AfiliadoVo {

	private int rut;
	private String dv;
	private String nombre;
	private int cargas;
	private Date fechaDescarga;
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
	 * @return the cargas
	 */
	public int getCargas() {
		return cargas;
	}
	/**
	 * @param cargas the cargas to set
	 */
	public void setCargas(int cargas) {
		this.cargas = cargas;
	}
	/**
	 * @return the fechaDescarga
	 */
	public Date getFechaDescarga() {
		return fechaDescarga;
	}
	/**
	 * @param fechaDescarga the fechaDescarga to set
	 */
	public void setFechaDescarga(Date fechaDescarga) {
		this.fechaDescarga = fechaDescarga;
	}
	
	
	
}
