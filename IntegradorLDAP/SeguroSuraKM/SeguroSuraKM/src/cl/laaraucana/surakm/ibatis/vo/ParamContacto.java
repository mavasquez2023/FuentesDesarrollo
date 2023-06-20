/**
 * 
 */
package cl.laaraucana.surakm.ibatis.vo;

import java.util.Date;

/**
 * @author IBM Software Factory
 *
 */
public class ParamContacto {
	private int rut;
	private Date fechaCorte;
	/**
	 * @return the rut
	 */
	public int getRut() {
		return rut;
	}
	/**
	 * @param rut the rut to set
	 */
	public void setRut(int rut) {
		this.rut = rut;
	}
	/**
	 * @return the fechaCorte
	 */
	public Date getFechaCorte() {
		return fechaCorte;
	}
	/**
	 * @param fechaCorte the fechaCorte to set
	 */
	public void setFechaCorte(Date fechaCorte) {
		this.fechaCorte = fechaCorte;
	}
	
}
