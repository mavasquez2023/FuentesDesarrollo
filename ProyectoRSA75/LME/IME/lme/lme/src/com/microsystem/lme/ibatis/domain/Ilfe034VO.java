/*
 * Created on 13-10-2011
 *
 */
package com.microsystem.lme.ibatis.domain;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @author microsystem
 *
 */
public class Ilfe034VO implements Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = -2240630575643848248L;
	//AFIRUT,EMPRUT,NUMIMPRE,NDIAS,FECDESDE,FECHASTA,FECPROCE
	protected Integer afiRut;//AFIRUT
	protected Integer empRut;//EMPRUT
	protected String lmaFechaDesde;//FECDESDE
	protected String lmaFechaHasta;//FECHASTA
	protected String lmaFechaproceso;//FECPROCE
	protected Integer lmaNdias;//NDIAS

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
	 * @return Returns the lmaFechaDesde.
	 */
	public String getLmaFechaDesde() {
		return lmaFechaDesde;
	}

	/**
	 * @param lmaFechaDesde The lmaFechaDesde to set.
	 */
	public void setLmaFechaDesde(String lmaFechaDesde) {
		this.lmaFechaDesde = lmaFechaDesde;
	}

	/**
	 * @return Returns the lmaFechaHasta.
	 */
	public String getLmaFechaHasta() {
		return lmaFechaHasta;
	}

	/**
	 * @param lmaFechaHasta The lmaFechaHasta to set.
	 */
	public void setLmaFechaHasta(String lmaFechaHasta) {
		this.lmaFechaHasta = lmaFechaHasta;
	}

	/**
	 * @return Returns the lmaFechaproceso.
	 */
	public String getLmaFechaproceso() {
		return lmaFechaproceso;
	}

	/**
	 * @param lmaFechaproceso The lmaFechaproceso to set.
	 */
	public void setLmaFechaproceso(String lmaFechaproceso) {
		this.lmaFechaproceso = lmaFechaproceso;
	}

	/**
	 * @return Returns the lmaNdias.
	 */
	public Integer getLmaNdias() {
		return lmaNdias;
	}

	/**
	 * @param lmaNdias The lmaNdias to set.
	 */
	public void setLmaNdias(Integer lmaNdias) {
		this.lmaNdias = lmaNdias;
	}

	/**
	 * 
	 */
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
