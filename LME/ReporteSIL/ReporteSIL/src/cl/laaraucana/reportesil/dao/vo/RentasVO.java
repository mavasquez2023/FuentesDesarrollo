/**
 * 
 */
package cl.laaraucana.reportesil.dao.vo;

import java.io.Serializable;

/**
 * @author IBM Software Factory
 *
 */
public class RentasVO implements Serializable{
	private int rutAfiliado;
	private int numinterno;
	private int fechaHasta;
	private String periodo=""; 
	private String motivo="";
	private String monto="";
	private int maternal;
	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}
	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}
	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	/**
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}
	/**
	 * @return the maternal
	 */
	public int getMaternal() {
		return maternal;
	}
	/**
	 * @param maternal the maternal to set
	 */
	public void setMaternal(int maternal) {
		this.maternal = maternal;
	}
	/**
	 * @return the rutAfiliado
	 */
	public int getRutAfiliado() {
		return rutAfiliado;
	}
	/**
	 * @param rutAfiliado the rutAfiliado to set
	 */
	public void setRutAfiliado(int rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}
	/**
	 * @return the numinterno
	 */
	public int getNuminterno() {
		return numinterno;
	}
	/**
	 * @param numinterno the numinterno to set
	 */
	public void setNuminterno(int numinterno) {
		this.numinterno = numinterno;
	}
	/**
	 * @return the fechaHasta
	 */
	public int getFechaHasta() {
		return fechaHasta;
	}
	/**
	 * @param fechaHasta the fechaHasta to set
	 */
	public void setFechaHasta(int fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
}
