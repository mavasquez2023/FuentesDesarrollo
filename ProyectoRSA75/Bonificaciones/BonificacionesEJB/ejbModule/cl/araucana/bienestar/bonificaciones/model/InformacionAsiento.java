package cl.araucana.bienestar.bonificaciones.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class InformacionAsiento implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	public static final String TIPO_DESCUENTO="D";
	public static final String TIPO_REEMBOLSO="R";
	
	private String tipo=null;
	private long codigo=0;
	private Date fecha=null;
	private long secuencia=0;
	private String usuario = null;

	/**
	 * @return
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * @return
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @return
	 */
	public long getSecuencia() {
		return secuencia;
	}

	/**
	 * @return
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param l
	 */
	public void setCodigo(long l) {
		codigo = l;
	}

	/**
	 * @param date
	 */
	public void setFecha(Date date) {
		fecha = date;
	}

	/**
	 * @param l
	 */
	public void setSecuencia(long l) {
		secuencia = l;
	}

	/**
	 * @param string
	 */
	public void setTipo(String string) {
		tipo = string;
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
	public void setUsuario(String string) {
		usuario = string;
	}

	public String toString(){
		return " tipo: "+tipo + " codigo: "+codigo+" fecha: "+fecha + " secuencia: "+secuencia+" usuario: "+usuario;

	}
}
