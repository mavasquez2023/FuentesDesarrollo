/**
 * 
 */
package cl.laaraucana.rendicionpagonomina.ibatis.vo;

import java.util.List;

/**
 * @author IBM Software Factory
 *
 */
public class ResumenCargaPagoBenefVo {
	private String convenio;
	private String producto;
	private int cantidadRegistros;
	private long montoNomina;
	private int sinmandato;
	private long montoPendiente;
	private List<DetalleCargaPagoBenefVo> listaNomina;
	private List<String> listaErrores;
	private String nombreArchivo;
	
	/**
	 * @return the convenio
	 */
	public String getConvenio() {
		return convenio;
	}
	/**
	 * @param convenio the convenio to set
	 */
	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}
	/**
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}
	/**
	 * @param producto the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}
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
	 * @return the montoNomina
	 */
	public long getMontoNomina() {
		return montoNomina;
	}
	/**
	 * @param montoNomina the montoNomina to set
	 */
	public void setMontoNomina(long montoNomina) {
		this.montoNomina = montoNomina;
	}
	/**
	 * @return the sinmandato
	 */
	public int getSinmandato() {
		return sinmandato;
	}
	/**
	 * @param sinmandato the sinmandato to set
	 */
	public void setSinmandato(int sinmandato) {
		this.sinmandato = sinmandato;
	}
	/**
	 * @return the listaNomina
	 */
	public List<DetalleCargaPagoBenefVo> getListaNomina() {
		return listaNomina;
	}
	/**
	 * @param listaNomina the listaNomina to set
	 */
	public void setListaNomina(List<DetalleCargaPagoBenefVo> listaNomina) {
		this.listaNomina = listaNomina;
	}
	/**
	 * @return the montoPendiente
	 */
	public long getMontoPendiente() {
		return montoPendiente;
	}
	/**
	 * @param montoPendiente the montoPendiente to set
	 */
	public void setMontoPendiente(long montoPendiente) {
		this.montoPendiente = montoPendiente;
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
	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	/**
	 * @param nombreArchivo the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	
	
}
