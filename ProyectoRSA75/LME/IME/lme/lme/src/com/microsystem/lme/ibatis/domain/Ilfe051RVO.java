/**
 * 
 */
package com.microsystem.lme.ibatis.domain;

/**
 * @author Daniel
 *
 */
public class Ilfe051RVO extends IlfeBaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6133307193528393969L;
	protected Integer codMot; //Codigo Motivo
	protected String gloMot;
	protected String fechaEstado; //FECHAEST
	protected String horaEstado; //HORAEST

	public String getFechaEstado() {
		return fechaEstado;
	}

	public String getHoraEstado() {
		return horaEstado;
	}

	public void setFechaEstado(String fechaEstado) {
		this.fechaEstado = fechaEstado;
	}

	public void setHoraEstado(String horaEstado) {
		this.horaEstado = horaEstado;
	}

	public Integer getCodMot() {
		return this.codMot;
	}

	public void setCodMot(Integer codMod) {
		this.codMot = codMod;
	}

	public String getGloMot() {
		return this.gloMot;
	}

	public void setGloMot(String gloMot) {
		this.gloMot = gloMot;
	}

}
