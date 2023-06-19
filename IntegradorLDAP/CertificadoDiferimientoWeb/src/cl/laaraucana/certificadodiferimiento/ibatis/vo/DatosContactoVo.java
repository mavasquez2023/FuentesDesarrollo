/**
 * 
 */
package cl.laaraucana.certificadodiferimiento.ibatis.vo;

import java.util.Date;

/**
 * @author IBM Software Factory
 *
 */
public class DatosContactoVo {
	
	private String accion;
	private long rut;
	private String dv;
	private String tipoBP;
	private String tipoDato;
	private String dato;
	private String estado;
	private Date fechaCreacion;
	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}
	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}
	/**
	 * @return the rut
	 */
	public long getRut() {
		return rut;
	}
	/**
	 * @param rut the rut to set
	 */
	public void setRut(long rut) {
		this.rut = rut;
	}
	
	/**
	 * @return the dv
	 */
	public String getDv() {
		return dv;
	}
	/**
	 * @param dv the dv to set
	 */
	public void setDv(String dv) {
		this.dv = dv;
	}
	/**
	 * @return the tipoBP
	 */
	public String getTipoBP() {
		return tipoBP;
	}
	/**
	 * @param tipoBP the tipoBP to set
	 */
	public void setTipoBP(String tipoBP) {
		this.tipoBP = tipoBP;
	}
	/**
	 * @return the tipoDato
	 */
	public String getTipoDato() {
		return tipoDato;
	}
	/**
	 * @param tipoDato the tipoDato to set
	 */
	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}
	/**
	 * @return the dato
	 */
	public String getDato() {
		return dato;
	}
	/**
	 * @param dato the dato to set
	 */
	public void setDato(String dato) {
		this.dato = dato;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
}
