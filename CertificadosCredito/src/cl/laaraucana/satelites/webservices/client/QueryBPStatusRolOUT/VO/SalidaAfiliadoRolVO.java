package cl.laaraucana.satelites.webservices.client.QueryBPStatusRolOUT.VO;

import java.util.Date;


public class SalidaAfiliadoRolVO {
	private String rutBP;
	private Date fechaInicioRol;
	private Date fechaTerminoRol;
	private String rol;
	
	public SalidaAfiliadoRolVO (){}
	


	public SalidaAfiliadoRolVO(String rutBP, Date fechaInicioRol, Date fechaTerminoRol, String rol) {
		super();
		this.rutBP = rutBP;
		this.fechaInicioRol = fechaInicioRol;
		this.fechaTerminoRol = fechaTerminoRol;
		this.rol = rol;
	}



	/**
	 * @return the rutBP
	 */
	public String getRutBP() {
		return rutBP;
	}



	/**
	 * @param rutBP the rutBP to set
	 */
	public void setRutBP(String rutBP) {
		this.rutBP = rutBP;
	}



	/**
	 * @return the fechaInicioRol
	 */
	public Date getFechaInicioRol() {
		return fechaInicioRol;
	}



	/**
	 * @param fechaInicioRol the fechaInicioRol to set
	 */
	public void setFechaInicioRol(Date fechaInicioRol) {
		this.fechaInicioRol = fechaInicioRol;
	}



	/**
	 * @return the fechaTerminoRol
	 */
	public Date getFechaTerminoRol() {
		return fechaTerminoRol;
	}



	/**
	 * @param fechaTerminoRol the fechaTerminoRol to set
	 */
	public void setFechaTerminoRol(Date fechaTerminoRol) {
		this.fechaTerminoRol = fechaTerminoRol;
	}



	/**
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}



	/**
	 * @param rol the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
}
