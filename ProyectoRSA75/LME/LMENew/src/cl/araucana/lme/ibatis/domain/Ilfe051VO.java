/*
 * Created on 14-10-2011
 *
 */
package cl.araucana.lme.ibatis.domain;

import java.io.Serializable;

/**
 * @author j-factory
 *
 */
public class Ilfe051VO extends IlfeBaseVO implements Serializable{

	/**
	 * 
	 */
	protected static final long serialVersionUID = 7505841470983391968L;
	protected Integer codMot; //CODMOT
	protected String gloMot;
	protected String fechaEstado; //FECHAEST
	protected String horaEstado; //HORAEST
	protected String afiNom;
	private String user="LME";
	private String horaEnvio;
	
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

	/**
	 * @return el gloMot
	 */
	public String getGloMot() {
		return gloMot;
	}

	/**
	 * @param gloMot el gloMot a establecer
	 */
	public void setGloMot(String gloMot) {
		this.gloMot = gloMot;
	}

	/**
	 * @return el afiNom
	 */
	public String getAfiNom() {
		return afiNom;
	}

	/**
	 * @param afiNom el afiNom a establecer
	 */
	public void setAfiNom(String afiNom) {
		this.afiNom = afiNom;
	}

	/**
	 * @return el horaEnvio
	 */
	public String getHoraEnvio() {
		return horaEnvio;
	}

	/**
	 * @param horaEnvio el horaEnvio a establecer
	 */
	public void setHoraEnvio(String horaEnvio) {
		this.horaEnvio = horaEnvio;
	}

	/**
	 * @return el user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user el user a establecer
	 */
	public void setUser(String user) {
		this.user = user;
	}
	
}
