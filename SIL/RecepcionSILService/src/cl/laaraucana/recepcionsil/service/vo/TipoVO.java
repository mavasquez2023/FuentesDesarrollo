/**
 * 
 */
package cl.laaraucana.recepcionsil.service.vo;

import java.io.Serializable;

/**
 * @author IBM Software Factory
 *
 */
public class TipoVO implements Serializable{
	//A3 Tipo Licencia
	private String recuperabilidad;
	private String inicioTramiteInvalidez;
	private String fechaConcepcion;
	
	/**
	 * @return the recuperabilidad
	 */
	public String getRecuperabilidad() {
		return recuperabilidad;
	}
	/**
	 * @param recuperabilidad the recuperabilidad to set
	 */
	public void setRecuperabilidad(String recuperabilidad) {
		this.recuperabilidad = recuperabilidad;
	}
	/**
	 * @return the inicioTramiteInvalidez
	 */
	public String getInicioTramiteInvalidez() {
		return inicioTramiteInvalidez;
	}
	/**
	 * @param inicioTramiteInvalidez the inicioTramiteInvalidez to set
	 */
	public void setInicioTramiteInvalidez(String inicioTramiteInvalidez) {
		this.inicioTramiteInvalidez = inicioTramiteInvalidez;
	}
	/**
	 * @return the fechaConcepcion
	 */
	public String getFechaConcepcion() {
		return fechaConcepcion;
	}
	/**
	 * @param fechaConcepcion the fechaConcepcion to set
	 */
	public void setFechaConcepcion(String fechaConcepcion) {
		this.fechaConcepcion = fechaConcepcion;
	}
	
	
}
