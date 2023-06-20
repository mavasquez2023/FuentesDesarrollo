/**
 * 
 */
package cl.laaraucana.reportesil.dao.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author IBM Software Factory
 *
 */
public class RemuneracionesBCSIL implements Serializable{

	private List<PeriodosRentaVO> periodos;
	private List<PeriodosRentaVO> periodosMaternales;

	/**
	 * @return the periodos
	 */
	public List<PeriodosRentaVO> getPeriodos() {
		return periodos;
	}
	/**
	 * @param periodos the periodos to set
	 */
	public void setPeriodos(List<PeriodosRentaVO> periodos) {
		this.periodos = periodos;
	}
	/**
	 * @return the periodosMaternales
	 */
	public List<PeriodosRentaVO> getPeriodosMaternales() {
		return periodosMaternales;
	}
	/**
	 * @param periodosMaternales the periodosMaternales to set
	 */
	public void setPeriodosMaternales(List<PeriodosRentaVO> periodosMaternales) {
		this.periodosMaternales = periodosMaternales;
	}
	
}
