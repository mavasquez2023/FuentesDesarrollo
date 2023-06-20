/*
 * Created on 14-10-2011
 *
 */
package cl.araucana.lme.ibatis.domain;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @author j-factory
 *
 */
public class IlfeBaseVO implements Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 4135076503483940650L;
	

	protected Integer ideOpe; //IDEOPE
	protected String urlOpe; //URLOPE
	protected String codOpe; //CODOPE
	protected String codCcaf; //CODCCAF
	protected String pwdCcaf; //PWDCCAF

	protected String numImpre; //NUMIMPRE 
	protected String estado; //ESTADO
	protected String fechaProceso; //FECPROC
	protected String enviada; //ENVIADA
	protected String respWs; //RESPWS
	protected String glosaEstado;//GLORESP
	protected String fechaRespuesta; //FECRESP
	protected String horaRespuesta; //HORRESP

	protected Integer afiRut; //AFIRUT
	protected Integer empRut; //EMPRUT
	protected String afiRutDV;
	protected String empRutDV;
	
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
	public String getNumImpre() {
		return numImpre;
	}

	/**
	 * @param numImpre The numImpre to set.
	 */
	public void setNumImpre(String numImpre) {
		this.numImpre = numImpre;
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
	 * @return Returns the glosaEstado.
	 */
	public String getGlosaEstado() {
		return glosaEstado;
	}

	/**
	 * @param glosaEstado The glosaEstado to set.
	 */
	public void setGlosaEstado(String glosaEstado) {
		this.glosaEstado = glosaEstado;
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
	 * @return el afiRutDv
	 */
	public String getAfiRutDV() {
		return afiRutDV;
	}

	/**
	 * @param afiRutDv el afiRutDv a establecer
	 */
	public void setAfiRutDV(String afiRutDV) {
		this.afiRutDV = afiRutDV;
	}

	/**
	 * @return el empRutDV
	 */
	public String getEmpRutDV() {
		return empRutDV;
	}

	/**
	 * @param empRutDV el empRutDV a establecer
	 */
	public void setEmpRutDV(String empRutDV) {
		this.empRutDV = empRutDV;
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
