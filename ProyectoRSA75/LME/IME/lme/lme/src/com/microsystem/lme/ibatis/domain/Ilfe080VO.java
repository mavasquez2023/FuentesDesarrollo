/*
 * Created on 14-10-2011
 *
 */
package com.microsystem.lme.ibatis.domain;

/**
 * @author jcea
 * 
 */
public class Ilfe080VO {

	/**
	 * 
	 */
	private String idOperador; // IDOPE
	private String numLicencia; // NUMLIC
	private String digLicencia; // DIGLIC
	private String fechaConsulta; // FECCON
	private String horaConsulta; // HORCON
	private String estado; // ESTADO

	public String getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(String idOperador) {
		this.idOperador = idOperador;
	}

	public String getNumLicencia() {
		return numLicencia;
	}

	public void setNumLicencia(String numLicencia) {
		this.numLicencia = numLicencia;
	}

	public String getDigLicencia() {
		return digLicencia;
	}

	public void setDigLicencia(String digLicencia) {
		this.digLicencia = digLicencia;
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

}
