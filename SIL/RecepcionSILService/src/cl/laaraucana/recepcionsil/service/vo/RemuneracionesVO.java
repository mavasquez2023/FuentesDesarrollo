/**
 * 
 */
package cl.laaraucana.recepcionsil.service.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author J-Factory Solutions
 *
 */
public class RemuneracionesVO implements Serializable{
	//C3 Informe Remuneraciones
	private String remuneracionImponibleAFC90;
	private String remuneracionImponibleAFP60;
	private List<RemuneracionVO> informeRemuneraciones;

	/**
	 * @return the remuneracionImponibleAFC90
	 */
	public String getRemuneracionImponibleAFC90() {
		return remuneracionImponibleAFC90;
	}
	/**
	 * @param remuneracionImponibleAFC90 the remuneracionImponibleAFC90 to set
	 */
	public void setRemuneracionImponibleAFC90(String remuneracionImponibleAFC90) {
		this.remuneracionImponibleAFC90 = remuneracionImponibleAFC90;
	}
	/**
	 * @return the remuneracionImponibleAFP60
	 */
	public String getRemuneracionImponibleAFP60() {
		return remuneracionImponibleAFP60;
	}
	/**
	 * @param remuneracionImponibleAFP60 the remuneracionImponibleAFP60 to set
	 */
	public void setRemuneracionImponibleAFP60(String remuneracionImponibleAFP60) {
		this.remuneracionImponibleAFP60 = remuneracionImponibleAFP60;
	}
	/**
	 * @return the informeRemuneraciones
	 */
	public List<RemuneracionVO> getInformeRemuneraciones() {
		return informeRemuneraciones;
	}
	/**
	 * @param informeRemuneraciones the informeRemuneraciones to set
	 */
	public void setInformeRemuneraciones(List<RemuneracionVO> informeRemuneraciones) {
		this.informeRemuneraciones = informeRemuneraciones;
	}

	
	
	
}
