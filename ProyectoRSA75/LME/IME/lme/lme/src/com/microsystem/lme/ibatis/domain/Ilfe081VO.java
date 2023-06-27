/*
 * Created on 14-10-2011
 *
 */
package com.microsystem.lme.ibatis.domain;

/**
 * @author jcea
 * 
 */
public class Ilfe081VO {

	private String fecha; // fecha
	private String estadoImed; // estado Imed
	private String estadoMediPass; // estado MediPass
	private String fechaConsulta; // FECCON
	private String horaConsulta; // HORCON
	private String estado; // MARCA
	private String fechaHasta; // fecha

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(String fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	public String getHoraConsulta() {
		return horaConsulta;
	}

	public void setHoraConsulta(String horaConsulta) {
		this.horaConsulta = horaConsulta;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstadoImed() {
		return estadoImed;
	}

	public void setEstadoImed(String estadoImed) {
		this.estadoImed = estadoImed;
	}

	public String getEstadoMediPass() {
		return estadoMediPass;
	}

	public void setEstadoMediPass(String estadoMediPass) {
		this.estadoMediPass = estadoMediPass;
	}

	/**
	 * @return the fechaHasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @param fechaHasta the fechaHasta to set
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}



}
