/*
 * Created on 24-10-2011
 *
 */
package cl.araucana.lme.ibatis.domain;

import java.io.Serializable;

/**
 * @author j-factory
 *
 */
public class Ilfe004VO implements Serializable {

	/**
	 * 
	 */
	protected Integer ideOpe; //IDEOPE
	protected Integer numImpre; //NUMIMPRE
	protected Integer empRut;
	protected String empRutDv;
	protected Integer calidadTrabajador;
	protected Integer contratoDuracionIndef;
	/**
	 * @return el calidadTrabajador
	 */
	public Integer getCalidadTrabajador() {
		return calidadTrabajador;
	}
	/**
	 * @param calidadTrabajador el calidadTrabajador a establecer
	 */
	public void setCalidadTrabajador(Integer calidadTrabajador) {
		this.calidadTrabajador = calidadTrabajador;
	}
	/**
	 * @return el contratoDuracionIndef
	 */
	public Integer getContratoDuracionIndef() {
		return contratoDuracionIndef;
	}
	/**
	 * @param contratoDuracionIndef el contratoDuracionIndef a establecer
	 */
	public void setContratoDuracionIndef(Integer contratoDuracionIndef) {
		this.contratoDuracionIndef = contratoDuracionIndef;
	}
	/**
	 * @return el empRut
	 */
	public Integer getEmpRut() {
		return empRut;
	}
	/**
	 * @param empRut el empRut a establecer
	 */
	public void setEmpRut(Integer empRut) {
		this.empRut = empRut;
	}
	/**
	 * @return el empRutDv
	 */
	public String getEmpRutDv() {
		return empRutDv;
	}
	/**
	 * @param empRutDv el empRutDv a establecer
	 */
	public void setEmpRutDv(String empRutDv) {
		this.empRutDv = empRutDv;
	}
	/**
	 * @return el ideOpe
	 */
	public Integer getIdeOpe() {
		return ideOpe;
	}
	/**
	 * @param ideOpe el ideOpe a establecer
	 */
	public void setIdeOpe(Integer ideOpe) {
		this.ideOpe = ideOpe;
	}
	/**
	 * @return el numImpre
	 */
	public Integer getNumImpre() {
		return numImpre;
	}
	/**
	 * @param numImpre el numImpre a establecer
	 */
	public void setNumImpre(Integer numImpre) {
		this.numImpre = numImpre;
	}
	
	
	}
