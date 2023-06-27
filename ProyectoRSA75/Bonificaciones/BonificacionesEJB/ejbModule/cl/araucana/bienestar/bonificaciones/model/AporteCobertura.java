package cl.araucana.bienestar.bonificaciones.model;

import java.io.Serializable;
import java.util.Date;


/**
 * @author Alejandro Sepúlveda
 *
 */
public class AporteCobertura implements Serializable{
	
	/** Serial */
	private static final long serialVersionUID = 1L;


	private long codigoCobertura=0;
	private int aporteBienestar=0;
	private long casoID=0;
	private int idDetalle = 0;
	private String usuario;
	private Date fecha;
	

	/**
	 * @return
	 */
	public int getAporteBienestar() {
		return aporteBienestar;
	}

	/**
	 * @return
	 */
	public long getCasoID() {
		return casoID;
	}

	/**
	 * @return
	 */
	public long getCodigoCobertura() {
		return codigoCobertura;
	}

	/**
	 * @return
	 */
	public int getIdDetalle() {
		return idDetalle;
	}

	/**
	 * @param i
	 */
	public void setAporteBienestar(int i) {
		aporteBienestar = i;
	}

	/**
	 * @param l
	 */
	public void setCasoID(long l) {
		casoID = l;
	}

	/**
	 * @param l
	 */
	public void setCodigoCobertura(long l) {
		codigoCobertura = l;
	}

	/**
	 * @param i
	 */
	public void setIdDetalle(int i) {
		idDetalle = i;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
