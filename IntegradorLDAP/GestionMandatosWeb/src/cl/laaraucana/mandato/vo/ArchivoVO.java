/**
 * 
 */
package cl.laaraucana.mandato.vo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author J-Factory
 *
 */
public class ArchivoVO {
	private CommonsMultipartFile rechazo;
	private String nombre;
	private String tipo;

	/**
	 * @return the rechazo
	 */
	public CommonsMultipartFile getRechazo() {
		return rechazo;
	}

	/**
	 * @param rechazo the rechazo to set
	 */
	public void setRechazo(CommonsMultipartFile rechazo) {
		this.rechazo = rechazo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
}
