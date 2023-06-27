package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class UsuarioVO implements Serializable {

	/** Serial */
	private static final long serialVersionUID = 1L;

	private String usuario = null;
	private String nombre = null;
	private String apellidoPaterno = null;
	private String apellidoMaterno = null;
	private String codigoOficina = null;
	private String oficina = null;
	
	/**
	 * Retorna el nombre completo del Usuario
	 * @return String con el nombre
	 */
	public String getFullNombre() {
		return nombre+" "+apellidoPaterno+" "+apellidoMaterno;
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
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return
	 */
	public String getOficina() {
		return oficina;
	}

	/**
	 * @return
	 */
	public String getUsuario() {
		return usuario;
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
	public void setNombre(String string) {
		nombre = string;
	}

	/**
	 * @param string
	 */
	public void setOficina(String string) {
		oficina = string;
	}

	/**
	 * @param string
	 */
	public void setUsuario(String string) {
		usuario = string;
	}

	/**
	 * @return
	 */
	public String getCodigoOficina() {
		return codigoOficina;
	}

	/**
	 * @param string
	 */
	public void setCodigoOficina(String string) {
		codigoOficina = string;
	}

}
