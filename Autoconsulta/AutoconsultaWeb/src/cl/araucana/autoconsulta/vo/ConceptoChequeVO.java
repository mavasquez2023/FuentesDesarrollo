package cl.araucana.autoconsulta.vo;

import java.io.Serializable;

/**
 * @author asepulveda
 *
 */
public class ConceptoChequeVO implements Serializable {

	private long folio=0;
	private String observacionDetalle=null;
	private String itemGasto=null;
	private int item=0;
	private int codigoConcepto=0;


	/**
	 * @return
	 */
	public int getCodigoConcepto() {
		return codigoConcepto;
	}

	/**
	 * @return
	 */
	public long getFolio() {
		return folio;
	}

	/**
	 * @return
	 */
	public int getItem() {
		return item;
	}

	/**
	 * @return
	 */
	public String getItemGasto() {
		return itemGasto;
	}

	/**
	 * @return
	 */
	public String getObservacionDetalle() {
		return observacionDetalle;
	}

	/**
	 * @param i
	 */
	public void setCodigoConcepto(int i) {
		codigoConcepto = i;
	}

	/**
	 * @param l
	 */
	public void setFolio(long l) {
		folio = l;
	}

	/**
	 * @param i
	 */
	public void setItem(int i) {
		item = i;
	}

	/**
	 * @param string
	 */
	public void setItemGasto(String string) {
		itemGasto = string;
	}

	/**
	 * @param string
	 */
	public void setObservacionDetalle(String string) {
		observacionDetalle = string;
	}

}
