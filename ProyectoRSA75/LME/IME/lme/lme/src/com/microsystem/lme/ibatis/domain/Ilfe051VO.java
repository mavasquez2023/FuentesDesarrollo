/*
 * Created on 14-10-2011
 *
 */
package com.microsystem.lme.ibatis.domain;

/**
 * @author microsystem
 *
 */
public class Ilfe051VO extends IlfeBaseVO {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 7505841470983391968L;
	protected Integer codMot; //CODMOT
	protected String fechaEstado; //FECHAEST
	protected String horaEstado; //HORAEST

	/**
	 * @return the fechaEstado
	 */
	public String getFechaEstado() {
		return fechaEstado;
	}

	/**
	 * @return the horaEstado
	 */
	public String getHoraEstado() {
		return horaEstado;
	}

	/**
	 * @param fechaEstado the fechaEstado to set
	 */
	public void setFechaEstado(String fechaEstado) {
		this.fechaEstado = fechaEstado;
	}

	/**
	 * @param horaEstado the horaEstado to set
	 */
	public void setHoraEstado(String horaEstado) {
		this.horaEstado = horaEstado;
	}

	/**
	 * @return Returns the codMot.
	 */
	public Integer getCodMot() {
		return codMot;
	}

	/**
	 * @param codMot The codMot to set.
	 */
	public void setCodMot(Integer codMot) {
		this.codMot = codMot;
	}
}
