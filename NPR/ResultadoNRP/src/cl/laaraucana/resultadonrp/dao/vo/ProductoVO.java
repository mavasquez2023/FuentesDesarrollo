/**
 * 
 */
package cl.laaraucana.resultadonrp.dao.vo;

/**
 * @author IBM Software Factory
 *
 */
public class ProductoVO {
	private int periodo;
	private String origen;
	private String tipo_afiliado;
	private String producto;
	private int registros;
	private long monto;
	
	
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
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}
	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	/**
	 * @return the tipo_afiliado
	 */
	public String getTipo_afiliado() {
		return tipo_afiliado;
	}
	/**
	 * @param tipo_afiliado the tipo_afiliado to set
	 */
	public void setTipo_afiliado(String tipo_afiliado) {
		this.tipo_afiliado = tipo_afiliado;
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
	 * @return the registros
	 */
	public int getRegistros() {
		return registros;
	}
	/**
	 * @param registros the registros to set
	 */
	public void setRegistros(int registros) {
		this.registros = registros;
	}
	/**
	 * @return the monto
	 */
	public long getMonto() {
		return monto;
	}
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(long monto) {
		this.monto = monto;
	}
	
}
