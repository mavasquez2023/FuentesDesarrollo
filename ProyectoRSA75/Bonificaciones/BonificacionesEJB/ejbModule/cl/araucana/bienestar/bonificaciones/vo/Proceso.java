package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class Proceso implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	public static final String DSC_REEMBOLSO_SOCIOS="REEMBOLSO SOCIOS";
	
	private long codigo=0;
	private String descripcion=null;
	private Date fecha=null;



	/**
	 * @return
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @return
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param l
	 */
	public void setCodigo(long l) {
		codigo = l;
	}

	/**
	 * @param string
	 */
	public void setDescripcion(String string) {
		descripcion = string;
	}

	/**
	 * @param date
	 */
	public void setFecha(Date date) {
		fecha = date;
	}

}
