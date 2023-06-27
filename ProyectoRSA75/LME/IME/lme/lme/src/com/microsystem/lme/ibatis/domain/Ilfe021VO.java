/*
 * Created on 14-10-2011
 *
 */
package com.microsystem.lme.ibatis.domain;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @author microsystem
 *
 */
public class Ilfe021VO implements Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = -7540559349755969966L;
	private Integer ideOpe; //IDEOPE
	private String urlOpe; //URLOPE
	private String codOpe; //CODOPE
	private String codCcaf; //CODCCAF
	private String pwdCcaf; //PWDCCAF

	private Integer numImpre; //NUMIMPRE 
	private String estado; //ESTADO
	private String fechaProceso; //FECPROC
	private String enviada; //ENVIADA
	private String respWs; //RESPWS

	private String glosaEstado; //GLORESP
	private String fechaRespuesta; //FECRESP
	private String horaRespuesta; //HORRESP

	/**
	 * @return Returns the codCcaf.
	 */
	public String getCodCcaf() {
		return codCcaf;
	}

	/**
	 * @param codCcaf The codCcaf to set.
	 */
	public void setCodCcaf(String codCcaf) {
		this.codCcaf = codCcaf;
	}

	/**
	 * @return Returns the codOpe.
	 */
	public String getCodOpe() {
		return codOpe;
	}

	/**
	 * @param codOpe The codOpe to set.
	 */
	public void setCodOpe(String codOpe) {
		this.codOpe = codOpe;
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
	 * @return Returns the pwdCcaf.
	 */
	public String getPwdCcaf() {
		return pwdCcaf;
	}

	/**
	 * @param pwdCcaf The pwdCcaf to set.
	 */
	public void setPwdCcaf(String pwdCcaf) {
		this.pwdCcaf = pwdCcaf;
	}

	/**
	 * @return Returns the urlOpe.
	 */
	public String getUrlOpe() {
		return urlOpe;
	}

	/**
	 * @param urlOpe The urlOpe to set.
	 */
	public void setUrlOpe(String urlOpe) {
		this.urlOpe = urlOpe;
	}

	/**
	 * @return Returns the enviada.
	 */
	public String getEnviada() {
		return enviada;
	}

	/**
	 * @param enviada The enviada to set.
	 */
	public void setEnviada(String enviada) {
		this.enviada = enviada;
	}

	/**
	 * @return Returns the estado.
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado The estado to set.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return Returns the fechaProceso.
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}

	/**
	 * @param fechaProceso The fechaProceso to set.
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
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

	/**
	 * @return Returns the respWs.
	 */
	public String getRespWs() {
		return respWs;
	}

	/**
	 * @param respWs The respWs to set.
	 */
	public void setRespWs(String respWs) {
		this.respWs = respWs;
	}

	/**
	 * @return Returns the fechaRespuesta.
	 */
	public String getFechaRespuesta() {
		return fechaRespuesta;
	}

	/**
	 * @param fechaRespuesta The fechaRespuesta to set.
	 */
	public void setFechaRespuesta(String fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}

	/**
	 * @return Returns the gloResp.
	 */
	public String getGlosaEstado() {
		return glosaEstado;
	}

	/**
	 * @param gloResp The gloResp to set.
	 */
	public void setGlosaEstado(String glosaEstado) {
		this.glosaEstado = glosaEstado;
	}

	/**
	 * @return Returns the horaRespuesta.
	 */
	public String getHoraRespuesta() {
		return horaRespuesta;
	}

	/**
	 * @param horaRespuesta The horaRespuesta to set.
	 */
	public void setHoraRespuesta(String horaRespuesta) {
		this.horaRespuesta = horaRespuesta;
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
