/*
 * Created on 13-10-2011
 *
 */
package com.microsystem.lme.ibatis.domain;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * @author microsystem
 *
 */
public class Ilfe033VO implements Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = -6385132093903657526L;
	protected Integer afiRut;
	protected Integer empRut;
	protected String anoMesRemAnt;//PERIODO
	protected Integer codigoPrevisionRemAnt;//INSPREPER
	protected BigDecimal montoImponibleRemAnt;//MTOIMPOANT
	protected BigDecimal montoIncapacidadRemAnt;//MTOINCAANT
	protected BigDecimal montoTotalRemAnt;//MTOTOTANT
	protected Integer ndiasIncapacidadRemAnt;//DIASINCANT
	protected Integer ndiasRemAnt;//DIASREMANT

	/**
	 * @return Returns the afiRut.
	 */
	public Integer getAfiRut() {
		return afiRut;
	}

	/**
	 * @param afiRut The afiRut to set.
	 */
	public void setAfiRut(Integer afiRut) {
		this.afiRut = afiRut;
	}

	/**
	 * @return Returns the empRut.
	 */
	public Integer getEmpRut() {
		return empRut;
	}

	/**
	 * @param empRut The empRut to set.
	 */
	public void setEmpRut(Integer empRut) {
		this.empRut = empRut;
	}

	/**
	 * @return Returns the anoMesRemAnt.
	 */
	public String getAnoMesRemAnt() {
		return anoMesRemAnt;
	}

	/**
	 * @param anoMesRemAnt The anoMesRemAnt to set.
	 */
	public void setAnoMesRemAnt(String anoMesRemAnt) {
		this.anoMesRemAnt = anoMesRemAnt;
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
	public BigDecimal getMontoImponibleRemAnt() {
		return montoImponibleRemAnt;
	}

	/**
	 * @param montoImponibleRemAnt The montoImponibleRemAnt to set.
	 */
	public void setMontoImponibleRemAnt(BigDecimal montoImponibleRemAnt) {
		this.montoImponibleRemAnt = montoImponibleRemAnt;
	}

	/**
	 * @return Returns the montoIncapacidadRemAnt.
	 */
	public BigDecimal getMontoIncapacidadRemAnt() {
		return montoIncapacidadRemAnt;
	}

	/**
	 * @param montoIncapacidadRemAnt The montoIncapacidadRemAnt to set.
	 */
	public void setMontoIncapacidadRemAnt(BigDecimal montoIncapacidadRemAnt) {
		this.montoIncapacidadRemAnt = montoIncapacidadRemAnt;
	}

	/**
	 * @return Returns the montoTotalRemAnt.
	 */
	public BigDecimal getMontoTotalRemAnt() {
		return montoTotalRemAnt;
	}

	/**
	 * @param montoTotalRemAnt The montoTotalRemAnt to set.
	 */
	public void setMontoTotalRemAnt(BigDecimal montoTotalRemAnt) {
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

	public String toString() {
		StringBuffer sb = new StringBuffer();
		try {
			Class c = Class.forName(this.getClass().getName());
			Field[] f = c.getDeclaredFields();
			for (int i = 0; i < f.length; i++)
				sb.append(f[i].getName() + "=").append(f[i].get(this)).append("\n");

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
