
package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;

/**
 * @author asepulveda
 */
public class DetalleMovimientoPreCasoVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private long casoId=0;
	private long folioTesoreria=0;
	private long coberturaCodigo=0;
	private double monto=0;
	private String descripcion;
	private int idDetalle = 0;

	/**
	 * @return
	 */
	public long getCasoId() {
		return casoId;
	}

	/**
	 * @return
	 */
	public long getCoberturaCodigo() {
		return coberturaCodigo;
	}

	/**
	 * @return
	 */
	public long getFolioTesoreria() {
		return folioTesoreria;
	}

	/**
	 * @return
	 */
	public double getMonto() {
		return monto;
	}

	/**
	 * @param l
	 */
	public void setCasoId(long l) {
		casoId = l;
	}

	/**
	 * @param l
	 */
	public void setCoberturaCodigo(long l) {
		coberturaCodigo = l;
	}

	/**
	 * @param l
	 */
	public void setFolioTesoreria(long l) {
		folioTesoreria = l;
	}

	/**
	 * @param d
	 */
	public void setMonto(double d) {
		monto = d;
	}

	/**
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param string
	 */
	public void setDescripcion(String string) {
		descripcion = string;
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
	public void setIdDetalle(int i) {
		idDetalle = i;
	}

}
