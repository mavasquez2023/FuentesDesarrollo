/**
 * 
 */
package cl.laaraucana.mandato.ibatis.vo;

import java.util.List;

/**
 * @author IBM Software Factory
 *
 */
public class ResumenCargaRechazoVo {

	private int cantidadRegistros;
	private List<RechazoVo> listaRechazo;
	private List<String> listaErrores;
	/**
	 * @return the cantidadRegistros
	 */
	public int getCantidadRegistros() {
		return cantidadRegistros;
	}
	/**
	 * @param cantidadRegistros the cantidadRegistros to set
	 */
	public void setCantidadRegistros(int cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}
	
	/**
	 * @return the listaRechazo
	 */
	public List<RechazoVo> getListaRechazo() {
		return listaRechazo;
	}
	/**
	 * @param listaRechazo the listaRechazo to set
	 */
	public void setListaRechazo(List<RechazoVo> listaRechazo) {
		this.listaRechazo = listaRechazo;
	}
	/**
	 * @return the listaErrores
	 */
	public List<String> getListaErrores() {
		return listaErrores;
	}
	/**
	 * @param listaErrores the listaErrores to set
	 */
	public void setListaErrores(List<String> listaErrores) {
		this.listaErrores = listaErrores;
	}
		
}
