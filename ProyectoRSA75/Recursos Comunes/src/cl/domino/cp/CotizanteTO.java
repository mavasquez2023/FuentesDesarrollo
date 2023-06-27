/**
 * 
 */
package cl.domino.cp;

/**
 * @author usist24
 *
 */
public class CotizanteTO {
	private int periodo; 
	private int rutCotizante;
	private char dvRutCotizante;
	private String codigoAFP_CP;
	private String codigoAFP_Previred=null;
	private boolean activo;
	/**
	 * @return el activo
	 */
	public boolean isActivo() {
		return activo;
	}
	/**
	 * @param activo el activo a establecer
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	/**
	 * @return el codigoAFP_CP
	 */
	public String getCodigoAFP_CP() {
		return codigoAFP_CP;
	}
	/**
	 * @param codigoAFP_CP el codigoAFP_CP a establecer
	 */
	public void setCodigoAFP_CP(String codigoAFP_CP) {
		this.codigoAFP_CP = codigoAFP_CP;
	}
	/**
	 * @return el codigoAFP_Previred
	 */
	public String getCodigoAFP_Previred() {
		return codigoAFP_Previred;
	}
	/**
	 * @param codigoAFP_Previred el codigoAFP_Previred a establecer
	 */
	public void setCodigoAFP_Previred(String codigoAFP_Previred) {
		this.codigoAFP_Previred = codigoAFP_Previred;
	}
	/**
	 * @return el dvRutCotizante
	 */
	public char getDvRutCotizante() {
		return dvRutCotizante;
	}
	/**
	 * @param dvRutCotizante el dvRutCotizante a establecer
	 */
	public void setDvRutCotizante(char dvRutCotizante) {
		this.dvRutCotizante = dvRutCotizante;
	}
	/**
	 * @return el periodo
	 */
	public int getPeriodo() {
		return periodo;
	}
	/**
	 * @param periodo el periodo a establecer
	 */
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	/**
	 * @return el rutCotizante
	 */
	public int getRutCotizante() {
		return rutCotizante;
	}
	/**
	 * @param rutCotizante el rutCotizante a establecer
	 */
	public void setRutCotizante(int rutCotizante) {
		this.rutCotizante = rutCotizante;
	}
		
}