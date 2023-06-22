/**
 * 
 */
package cl.laaraucana.cuotasdup.dao.VO;

import java.util.List;

/**
 * @author IBM Software Factory
 *
 */
public class ResultadoCuotasVO {
	private List<CuotaVO> listaCuotas;
	private int lineaError;
	private String columnaError;
	private int codigoResultado;
	/**
	 * @return the listaCuotas
	 */
	public List<CuotaVO> getListaCuotas() {
		return listaCuotas;
	}
	/**
	 * @param listaCuotas the listaCuotas to set
	 */
	public void setListaCuotas(List<CuotaVO> listaCuotas) {
		this.listaCuotas = listaCuotas;
	}
	/**
	 * @return the lineaError
	 */
	public int getLineaError() {
		return lineaError;
	}
	/**
	 * @param lineaError the lineaError to set
	 */
	public void setLineaError(int lineaError) {
		this.lineaError = lineaError;
	}
	/**
	 * @return the columnaError
	 */
	public String getColumnaError() {
		return columnaError;
	}
	/**
	 * @param columnaError the columnaError to set
	 */
	public void setColumnaError(String columnaError) {
		this.columnaError = columnaError;
	}
	/**
	 * @return the codigoResultado
	 */
	public int getCodigoResultado() {
		return codigoResultado;
	}
	/**
	 * @param codigoResultado the codigoResultado to set
	 */
	public void setCodigoResultado(int codigoResultado) {
		this.codigoResultado = codigoResultado;
	}
	
	
}
