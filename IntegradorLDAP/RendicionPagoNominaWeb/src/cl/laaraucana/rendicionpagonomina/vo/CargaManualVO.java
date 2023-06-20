package cl.laaraucana.rendicionpagonomina.vo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class CargaManualVO {

	private CommonsMultipartFile manual;
	private String convenio;
	private String producto;

	/**
	 * @return the manual
	 */
	public CommonsMultipartFile getManual() {
		return manual;
	}

	/**
	 * @param manual the manual to set
	 */
	public void setManual(CommonsMultipartFile manual) {
		this.manual = manual;
	}

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
	
	
}
