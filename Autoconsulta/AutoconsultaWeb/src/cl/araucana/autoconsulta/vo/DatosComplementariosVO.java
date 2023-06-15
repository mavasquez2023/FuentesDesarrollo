/*
 * Creado el 07-05-2007
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package cl.araucana.autoconsulta.vo;

import java.io.Serializable;

/**
 * @author USIST28
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class DatosComplementariosVO  implements Serializable{
	String email;
	long telefono;
	
	public DatosComplementariosVO() {
		telefono = 0;
		email = "";
	}

	public DatosComplementariosVO(long t,String e) {
		telefono = t;
		email = e;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return
	 */
	public long getTelefono() {
		return telefono;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string) {
		email = string;
	}

	/**
	 * @param l
	 */
	public void setTelefono(long l) {
		telefono = l;
	}

}
