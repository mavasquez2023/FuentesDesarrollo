/**
 * 
 */
package cl.araucana.ldap.ibatis.vo;

/**
 * @author IBM Software Factory
 *
 */
public class SucursalesVO {
	private int oficina;
	private String nombreOficina;
	private int sucursal;
	private String nombreSucursal;
	private int estado;

	/**
	 * @return the oficina
	 */
	public int getOficina() {
		return oficina;
	}
	/**
	 * @param oficina the oficina to set
	 */
	public void setOficina(int oficina) {
		this.oficina = oficina;
	}
	/**
	 * @return the sucursal
	 */
	public int getSucursal() {
		return sucursal;
	}
	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}
	/**
	 * @return the nombreSucursal
	 */
	public String getNombreSucursal() {
		return nombreSucursal;
	}
	/**
	 * @param nombreSucursal the nombreSucursal to set
	 */
	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}
	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	/**
	 * @return the nombreOficina
	 */
	public String getNombreOficina() {
		return nombreOficina;
	}
	/**
	 * @param nombreOficina the nombreOficina to set
	 */
	public void setNombreOficina(String nombreOficina) {
		this.nombreOficina = nombreOficina;
	}
	
	
}
