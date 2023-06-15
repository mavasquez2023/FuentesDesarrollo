/**
 * 
 */
package cl.laaraucana.resultadonrp.dao.vo;

import java.text.DecimalFormat;

/**
 * @author IBM Software Factory
 *
 */
public class ResumenDisponibilizacionVO {
	private int periodo;
	private String concepto;
	private String ruta;
	private int cantidadArchivos;
	DecimalFormat formateador = new DecimalFormat("###,###.##");
	/**
	 * @return the periodo
	 */
	public int getPeriodo() {
		return periodo;
	}
	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	
	/**
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}
	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	/**
	 * @return the ruta
	 */
	public String getRuta() {
		return ruta;
	}
	/**
	 * @param ruta the ruta to set
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	/**
	 * @return the cantidadArchivos
	 */
	public String getCantidadArchivos() {
		return formateador.format(cantidadArchivos);
	}
	
	public int getNumTXT() {
		return cantidadArchivos;
	}
	/**
	 * @param cantidadArchivos the cantidadArchivos to set
	 */
	public void setCantidadArchivos(int cantidadArchivos) {
		this.cantidadArchivos = cantidadArchivos;
	}
	

}
