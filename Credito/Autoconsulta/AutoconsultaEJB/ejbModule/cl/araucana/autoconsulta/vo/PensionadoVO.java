package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class PensionadoVO implements Serializable {
	
	private long rut=0;
	private String dv=null;
	private String nombre=null;
	private String apellidos=null;
	private String fechaNacimiento=null;
	private String estado=null;

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
		return nombre+" " +apellidos;
	}

	/**
	 * @return
	 */
	public String getApellidos() {
		return apellidos;
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
	public String getEstado() {
		return estado;
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
	public void setApellidos(String string) {
		apellidos = string;
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
	public void setEstado(String string) {
		estado = string;
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
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param string
	 */
	public void setFechaNacimiento(String string) {
		fechaNacimiento = string;
	}

}
