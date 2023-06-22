/**
 * 
 */
package cl.laaraucana.recepcionsil.service.vo;

import java.io.Serializable;

/**
 * @author J-Factory Solutions
 *
 */
public class EntidadPrevisionalVO implements Serializable{
	//C2 Identificación Regimen Previsional
	private String letraCajaPrevisional;
	private String fechaPrimeraAfiliacion;
	private String fechaContratoTrabajo;
	private String regimenPrevisional;
	
	/**
	 * @return the letraCajaPrevisional
	 */
	public String getLetraCajaPrevisional() {
		return letraCajaPrevisional;
	}
	/**
	 * @param letraCajaPrevisional the letraCajaPrevisional to set
	 */
	public void setLetraCajaPrevisional(String letraCajaPrevisional) {
		this.letraCajaPrevisional = letraCajaPrevisional;
	}
	/**
	 * @return the fechaPrimeraAfiliacion
	 */
	public String getFechaPrimeraAfiliacion() {
		return fechaPrimeraAfiliacion;
	}
	/**
	 * @param fechaPrimeraAfiliacion the fechaPrimeraAfiliacion to set
	 */
	public void setFechaPrimeraAfiliacion(String fechaPrimeraAfiliacion) {
		this.fechaPrimeraAfiliacion = fechaPrimeraAfiliacion;
	}
	/**
	 * @return the fechaContratoTrabajo
	 */
	public String getFechaContratoTrabajo() {
		return fechaContratoTrabajo;
	}
	/**
	 * @param fechaContratoTrabajo the fechaContratoTrabajo to set
	 */
	public void setFechaContratoTrabajo(String fechaContratoTrabajo) {
		this.fechaContratoTrabajo = fechaContratoTrabajo;
	}
	
	/**
	 * @return the regimenPrevisional
	 */
	public String getRegimenPrevisional() {
		return regimenPrevisional;
	}
	/**
	 * @param regimenPrevisional the regimenPrevisional to set
	 */
	public void setRegimenPrevisional(String regimenPrevisional) {
		this.regimenPrevisional = regimenPrevisional;
	}
	
	
}
