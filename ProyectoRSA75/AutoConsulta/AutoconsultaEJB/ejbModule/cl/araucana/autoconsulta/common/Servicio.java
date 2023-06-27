/*
 * Reune la informaciòn de un servicio de Autoconsulta
 */
package cl.araucana.autoconsulta.common;

import java.io.Serializable;

/**
 * @author aituarte
 *
 */
public class Servicio implements Serializable {

	private String clave;
	private String nombre;
	private boolean siempreActivo=false;
	private boolean forEmpresa=false;
	private boolean forAfiliadoActivo=false;
	private boolean forAfiliadoPasivo=false;
	private boolean forPensionado=false;
	private boolean forAhorrante=false;	
	private boolean valid = false;
	private boolean forEmpleadoPublico = false;
	private boolean forEmpresaPublica = false;
	private String action;
	private int tipo=0;
	
	/**
	 * @return
	 */
	public boolean isForAfiliadoActivo() {
		return forAfiliadoActivo;
	}

	/**
	 * @return
	 */
	public boolean isForAfiliadoPasivo() {
		return forAfiliadoPasivo;
	}

	/**
	 * @return
	 */
	public boolean isForAhorrante() {
		return forAhorrante;
	}

	/**
	 * @return
	 */
	public boolean isForEmpresa() {
		return forEmpresa;
	}

	/**
	 * @return
	 */
	public boolean isForPensionado() {
		return forPensionado;
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
	public String getClave() {
		return clave;
	}
	/**
	 * @return
	 */
	public boolean isSiempreActivo() {
		return siempreActivo;
	}

	/**
	 * @return
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * @param b
	 */
	public void setForAfiliadoActivo(boolean b) {
		forAfiliadoActivo = b;
	}

	/**
	 * @param b
	 */
	public void setForAfiliadoPasivo(boolean b) {
		forAfiliadoPasivo = b;
	}

	/**
	 * @param b
	 */
	public void setForAhorrante(boolean b) {
		forAhorrante = b;
	}

	/**
	 * @param b
	 */
	public void setForEmpresa(boolean b) {
		forEmpresa = b;
	}

	/**
	 * @param b
	 */
	public void setForPensionado(boolean b) {
		forPensionado = b;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}


	/**
	 * @param string
	 */
	public void setClave(String string) {
		clave = string;
	}
	/**
	 * @param b
	 */
	public void setSiempreActivo(boolean b) {
		siempreActivo = b;
	}

	/**
	 * @param b
	 */
	public void setValid(boolean b) {
		valid = b;
	}

	/**
	 * @return
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param string
	 */
	public void setAction(String string) {
		action = string;
	}

	/**
	 * @return
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param i
	 */
	public void setTipo(int i) {
		tipo = i;
	}

	/**
	 * @return
	 */
	public boolean isForEmpleadoPublico() {
		return forEmpleadoPublico;
	}

	/**
	 * @param b
	 */
	public void setForEmpleadoPublico(boolean b) {
		forEmpleadoPublico = b;
	}

	/**
	 * @return
	 */
	public boolean isForEmpresaPublica() {
		return forEmpresaPublica;
	}

	/**
	 * @param b
	 */
	public void setForEmpresaPublica(boolean b) {
		forEmpresaPublica = b;
	}

	}
