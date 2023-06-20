/**
 * 
 */
package cl.araucana.ldap.to;

import java.util.List;

import cl.araucana.ldap.ibatis.vo.SucursalesVO;

/**
 * @author IBM Software Factory
 *
 */
public class EncargadoAnexoTO {
	private String nombreEncargado;
	private String rutEncargado;
	private String rutEmpresa;
	private String allOffice;
	private List<SucursalesVO> sucursales;
	/**
	 * @return the nombreEncargado
	 */
	public String getNombreEncargado() {
		return nombreEncargado;
	}
	/**
	 * @param nombreEncargado the nombreEncargado to set
	 */
	public void setNombreEncargado(String nombreEncargado) {
		this.nombreEncargado = nombreEncargado;
	}
	/**
	 * @return the rutEncargado
	 */
	public String getRutEncargado() {
		return rutEncargado;
	}
	/**
	 * @param rutEncargado the rutEncargado to set
	 */
	public void setRutEncargado(String rutEncargado) {
		this.rutEncargado = rutEncargado;
	}
	/**
	 * @return the rutEmpresa
	 */
	public String getRutEmpresa() {
		return rutEmpresa;
	}
	/**
	 * @param rutEmpresa the rutEmpresa to set
	 */
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	/**
	 * @return the allOffice
	 */
	public String getAllOffice() {
		return allOffice;
	}
	/**
	 * @param allOffice the allOffice to set
	 */
	public void setAllOffice(String allOffice) {
		this.allOffice = allOffice;
	}
	/**
	 * @return the sucursales
	 */
	public List<SucursalesVO> getSucursales() {
		return sucursales;
	}
	/**
	 * @param sucursales the sucursales to set
	 */
	public void setSucursales(List<SucursalesVO> sucursales) {
		this.sucursales = sucursales;
	}
	
	
	
}
