package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.Stack;
;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class InputUpLoadFileVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private Stack lines=null;
	private String user=null;
	private String filename=null;
	private long codigoConvenio=0;
	private long codigoProducto=0;
	private String nombreConvenio=null;
	private String nombreProducto=null;

	/**
	 * @return
	 */
	public long getCodigoConvenio() {
		return codigoConvenio;
	}

	/**
	 * @return
	 */
	public long getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @return
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @return
	 */
	public Stack getLines() {
		return lines;
	}

	/**
	 * @return
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param l
	 */
	public void setCodigoConvenio(long l) {
		codigoConvenio = l;
	}

	/**
	 * @param l
	 */
	public void setCodigoProducto(long l) {
		codigoProducto = l;
	}

	/**
	 * @param string
	 */
	public void setFilename(String string) {
		filename = string;
	}

	/**
	 * @param stack
	 */
	public void setLines(Stack stack) {
		lines = stack;
	}

	/**
	 * @param string
	 */
	public void setUser(String string) {
		user = string;
	}

	/**
	 * @return
	 */
	public String getNombreConvenio() {
		return nombreConvenio;
	}

	/**
	 * @return
	 */
	public String getNombreProducto() {
		return nombreProducto;
	}

	/**
	 * @param string
	 */
	public void setNombreConvenio(String string) {
		nombreConvenio = string;
	}

	/**
	 * @param string
	 */
	public void setNombreProducto(String string) {
		nombreProducto = string;
	}

}
