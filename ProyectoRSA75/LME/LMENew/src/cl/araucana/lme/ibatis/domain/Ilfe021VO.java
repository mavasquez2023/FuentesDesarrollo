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
public class Ilfe021VO extends IlfeBaseVO implements Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = -7540559349755969966L;
	private String user;
	private String afiNom;
	private String horaEnvio;
	private String numImpDV;
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
	/**
	 * @return el numImpDV
	 */
	public String getNumImpDV() {
		return numImpDV;
	}
	/**
	 * @param numImpDV el numImpDV a establecer
	 */
	public void setNumImpDV(String numImpDV) {
		this.numImpDV = numImpDV;
	}
	
	

	}
