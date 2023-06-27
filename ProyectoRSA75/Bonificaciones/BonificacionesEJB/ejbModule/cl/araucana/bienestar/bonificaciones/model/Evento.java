package cl.araucana.bienestar.bonificaciones.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class Evento implements Serializable{
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	public static final String TIPO_MANUAL="MANUAL";
	public static final String TIPO_AUTOMATICO="AUTOMATICO";
	
	private String comentario = null;
	private Date fechaIngreso = null;
	private String tipo = TIPO_MANUAL;
	private String usuario = null;
	

	/**
	 * @return
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * @return
	 */
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @return
	 */
	public String getTipo() {
		return tipo;
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
	public void setComentario(String string) {
		comentario = string;
	}

	/**
	 * @param date
	 */
	public void setFechaIngreso(Date date) {
		fechaIngreso = date;
	}

	/**
	 * @param string
	 */
	public void setTipo(String string) {
		tipo = string;
	}

	/**
	 * @param string
	 */
	public void setUsuario(String string) {
		usuario = string;
	}

}
