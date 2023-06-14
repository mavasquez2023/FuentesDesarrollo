package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class EmpresaVO implements Serializable {
	
	private long rut=0;
	private String dv=null;
	private String nombre=null;
	private String estado=null;
	private String tipo=null;

	
	/**
	 * Retorna el nombre y el rut con digito verificador incluido
	 * @return String
	 */
	public String getNombreRut() {
		return nombre + " " + getFullRut();
	}
	
	/**
	 * Retorna el rut con digito verificador incluido
	 * @return String
	 */
	public String getFullRut() {
		return rut+"-"+dv;
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
	 * @return
	 */
	public String getTipo() {
		return tipo;
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
	 * @param string
	 */
	public void setTipo(String string) {
		tipo = string;
	}

}
