package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class AhorranteVO implements Serializable {
	
	private long rut=0;
	private String dv=null;
	private String nombre=null;
	private String apellidoMaterno=null;
	private String apellidoPaterno=null;
	private String tipo=null;

	/**
	 * Devuelve el rut completo
	 * @return
	 */
	public String getFullRut() {
		return rut+" - " +dv;
	}
	
	/**
	 * Devuelve el npmbre completo
	 * @return
	 */
	public String getFullNombre() {
		return nombre+" " +apellidoPaterno+" "+apellidoMaterno;
	}

	/**
	 * @return
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * @return
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * @return
	 */
	public String getDv() {
		return dv;
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return
	 */
	public long getRut() {
		return rut;
	}

	/**
	 * @param string
	 */
	public void setApellidoMaterno(String string) {
		apellidoMaterno = string;
	}

	/**
	 * @param string
	 */
	public void setApellidoPaterno(String string) {
		apellidoPaterno = string;
	}

	/**
	 * @param string
	 */
	public void setDv(String string) {
		dv = string;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

	/**
	 * @param l
	 */
	public void setRut(long l) {
		rut = l;
	}

	/**
	 * @return
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param string
	 */
	public void setTipo(String string) {
		tipo = string;
	}

}
