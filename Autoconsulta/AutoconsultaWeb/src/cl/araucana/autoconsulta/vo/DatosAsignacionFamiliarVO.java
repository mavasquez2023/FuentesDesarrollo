package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class DatosAsignacionFamiliarVO implements Serializable {
	
	private String fechaAfiliacion=null;
	private String nombreEmpresa=null;
	private String codigoEstadoEmpresa=null;

	/**
	 * @return
	 */
	public String getCodigoEstadoEmpresa() {
		return codigoEstadoEmpresa;
	}


	/**
	 * @return
	 */
	public String getFechaAfiliacion() {
		return fechaAfiliacion;
	}

	/**
	 * @return
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * @param string
	 */
	public void setCodigoEstadoEmpresa(String string) {
		codigoEstadoEmpresa = string;
	}


	/**
	 * @param string
	 */
	public void setFechaAfiliacion(String string) {
		fechaAfiliacion = string;
	}

	/**
	 * @param string
	 */
	public void setNombreEmpresa(String string) {
		nombreEmpresa = string;
	}

}
