/**
 * 
 */
package cl.laaraucana.reportesil.dao.vo;

import java.io.Serializable;

/**
 * @author IBM Software Factory
 *
 */
public class PeriodosRentaVO implements Serializable{
	private String periodo;
	private int remuneracionImponible;
	private int descuentosPrevisionales;
	private int impuesto;
	private int remuneracionNeta;
	private int subsidio;
	private int total;
	private int entidad;
	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}
	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	/**
	 * @return the remuneracionImponible
	 */
	public int getRemuneracionImponible() {
		return remuneracionImponible;
	}
	/**
	 * @param remuneracionImponible the remuneracionImponible to set
	 */
	public void setRemuneracionImponible(int remuneracionImponible) {
		this.remuneracionImponible = remuneracionImponible;
	}
	/**
	 * @return the descuentosPrevisionales
	 */
	public int getDescuentosPrevisionales() {
		return descuentosPrevisionales;
	}
	/**
	 * @param descuentosPrevisionales the descuentosPrevisionales to set
	 */
	public void setDescuentosPrevisionales(int descuentosPrevisionales) {
		this.descuentosPrevisionales = descuentosPrevisionales;
	}
	/**
	 * @return the impuesto
	 */
	public int getImpuesto() {
		return impuesto;
	}
	/**
	 * @param impuesto the impuesto to set
	 */
	public void setImpuesto(int impuesto) {
		this.impuesto = impuesto;
	}
	/**
	 * @return the remuneracionNeta
	 */
	public int getRemuneracionNeta() {
		return remuneracionNeta;
	}
	/**
	 * @param remuneracionNeta the remuneracionNeta to set
	 */
	public void setRemuneracionNeta(int remuneracionNeta) {
		this.remuneracionNeta = remuneracionNeta;
	}
	/**
	 * @return the subsidio
	 */
	public int getSubsidio() {
		return subsidio;
	}
	/**
	 * @param subsidio the subsidio to set
	 */
	public void setSubsidio(int subsidio) {
		this.subsidio = subsidio;
	}
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @return the entidad
	 */
	public int getEntidad() {
		return entidad;
	}
	/**
	 * @param entidad the entidad to set
	 */
	public void setEntidad(int entidad) {
		this.entidad = entidad;
	}
	
	
}
