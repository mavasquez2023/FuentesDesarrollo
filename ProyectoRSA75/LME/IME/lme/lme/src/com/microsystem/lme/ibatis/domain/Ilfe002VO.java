/*
 * Created on 24-10-2011
 *
 */
package com.microsystem.lme.ibatis.domain;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @author microsystem
 *
 */
public class Ilfe002VO implements Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 555107968267306436L;
	protected Integer ideOpe; //IDEOPE
	protected Integer numImpre; //NUMIMPRE
	protected String dvImpre; //DVIMPRE
	protected Integer estadoLicencia; //ESTLICEN
	protected Integer codError; //CODERR
	protected long afiRut;
	protected Integer fechaOpr;
	protected String msgErr;
	protected Integer numimprela;
	protected String fechaEstado;
	protected String horaEstado;

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

	public Integer getNumimprela() {
		return numimprela;
	}

	public void setNumimprela(Integer numimprela) {
		this.numimprela = numimprela;
	}

	public Integer getFechaOpr() {
		return fechaOpr;
	}

	public void setFechaOpr(Integer fechaOpr) {
		this.fechaOpr = fechaOpr;
	}

	public String getMsgErr() {
		return msgErr;
	}

	public void setMsgErr(String msgErr) {
		this.msgErr = msgErr;
	}

	public long getAfiRut() {
		return afiRut;
	}

	public void setAfiRut(long afiRut) {
		this.afiRut = afiRut;
	}

	/**
	 * @return Returns the codError.
	 */
	public Integer getCodError() {
		return codError;
	}

	/**
	 * @param codError The codError to set.
	 */
	public void setCodError(Integer codError) {
		this.codError = codError;
	}

	/**
	 * @return Returns the estadoLicencia.
	 */
	public Integer getEstadoLicencia() {
		return estadoLicencia;
	}

	/**
	 * @param estadoLicencia The estadoLicencia to set.
	 */
	public void setEstadoLicencia(Integer estadoLicencia) {
		this.estadoLicencia = estadoLicencia;
	}

	/**
	 * @return Returns the ideOpe.
	 */
	public Integer getIdeOpe() {
		return ideOpe;
	}

	/**
	 * @param ideOpe The ideOpe to set.
	 */
	public void setIdeOpe(Integer ideOpe) {
		this.ideOpe = ideOpe;
	}

	/**
	 * @return Returns the numImpre.
	 */
	public Integer getNumImpre() {
		return numImpre;
	}

	/**
	 * @param numImpre The numImpre to set.
	 */
	public void setNumImpre(Integer numImpre) {
		this.numImpre = numImpre;
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

	public String getDvImpre() {
		return dvImpre;
	}

	public void setDvImpre(String dvImpre) {
		this.dvImpre = dvImpre;
	}
}
