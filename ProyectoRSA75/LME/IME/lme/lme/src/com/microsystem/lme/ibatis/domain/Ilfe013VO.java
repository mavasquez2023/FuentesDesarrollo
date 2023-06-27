/*
 * Created on 14-10-2011
 *
 */
package com.microsystem.lme.ibatis.domain;

/**
 * @author jcea
 *
 */
public class Ilfe013VO extends IlfeBaseVO {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1574164867842765100L;
	protected Integer codigoPrevisionRemAnt; //INSPREVER
	protected Integer ndiasRemAnt; //DIASSIL
	protected Integer montoImponibleRemAnt; //RENTA
	protected Integer montoTotalRemAnt; //TOTMONRA
	protected Integer montoIncapacidadRemAnt; //SUBSIDIO
	protected Integer ndiasIncapacidadRemAnt; //NDIASINCA
	protected Integer periodo; //PERIODO

	/**
	 * 
	 */
	public Ilfe013VO() {
		super();
	}

	/**
	 * @return Returns the codigoPrevisionRemAnt.
	 */
	public Integer getCodigoPrevisionRemAnt() {
		return codigoPrevisionRemAnt;
	}

	/**
	 * @param codigoPrevisionRemAnt The codigoPrevisionRemAnt to set.
	 */
	public void setCodigoPrevisionRemAnt(Integer codigoPrevisionRemAnt) {
		this.codigoPrevisionRemAnt = codigoPrevisionRemAnt;
	}

	/**
	 * @return Returns the montoImponibleRemAnt.
	 */
	public Integer getMontoImponibleRemAnt() {
		return montoImponibleRemAnt;
	}

	/**
	 * @param montoImponibleRemAnt The montoImponibleRemAnt to set.
	 */
	public void setMontoImponibleRemAnt(Integer montoImponibleRemAnt) {
		this.montoImponibleRemAnt = montoImponibleRemAnt;
	}

	/**
	 * @return Returns the montoIncapacidadRemAnt.
	 */
	public Integer getMontoIncapacidadRemAnt() {
		return montoIncapacidadRemAnt;
	}

	/**
	 * @param montoIncapacidadRemAnt The montoIncapacidadRemAnt to set.
	 */
	public void setMontoIncapacidadRemAnt(Integer montoIncapacidadRemAnt) {
		this.montoIncapacidadRemAnt = montoIncapacidadRemAnt;
	}

	/**
	 * @return Returns the montoTotalRemAnt.
	 */
	public Integer getMontoTotalRemAnt() {
		return montoTotalRemAnt;
	}

	/**
	 * @param montoTotalRemAnt The montoTotalRemAnt to set.
	 */
	public void setMontoTotalRemAnt(Integer montoTotalRemAnt) {
		this.montoTotalRemAnt = montoTotalRemAnt;
	}

	/**
	 * @return Returns the ndiasIncapacidadRemAnt.
	 */
	public Integer getNdiasIncapacidadRemAnt() {
		return ndiasIncapacidadRemAnt;
	}

	/**
	 * @param ndiasIncapacidadRemAnt The ndiasIncapacidadRemAnt to set.
	 */
	public void setNdiasIncapacidadRemAnt(Integer ndiasIncapacidadRemAnt) {
		this.ndiasIncapacidadRemAnt = ndiasIncapacidadRemAnt;
	}

	/**
	 * @return Returns the ndiasRemAnt.
	 */
	public Integer getNdiasRemAnt() {
		return ndiasRemAnt;
	}

	/**
	 * @param ndiasRemAnt The ndiasRemAnt to set.
	 */
	public void setNdiasRemAnt(Integer ndiasRemAnt) {
		this.ndiasRemAnt = ndiasRemAnt;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

}
