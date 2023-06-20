package cl.laaraucana.dsiniestro.vo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FicherosVo {

	private CommonsMultipartFile cedula;
	private CommonsMultipartFile contrato;
	private CommonsMultipartFile certafp;
	private CommonsMultipartFile liqafc;
	private CommonsMultipartFile certemp;
	/**
	 * @return the cedula
	 */
	public CommonsMultipartFile getCedula() {
		return cedula;
	}
	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(CommonsMultipartFile cedula) {
		this.cedula = cedula;
	}
	
	
	/**
	 * @return the contrato
	 */
	public CommonsMultipartFile getContrato() {
		return contrato;
	}
	/**
	 * @param contrato the contrato to set
	 */
	public void setContrato(CommonsMultipartFile contrato) {
		this.contrato = contrato;
	}
	/**
	 * @return the certafp
	 */
	public CommonsMultipartFile getCertafp() {
		return certafp;
	}
	/**
	 * @param certafp the certafp to set
	 */
	public void setCertafp(CommonsMultipartFile certafp) {
		this.certafp = certafp;
	}
	/**
	 * @return the liqafc
	 */
	public CommonsMultipartFile getLiqafc() {
		return liqafc;
	}
	/**
	 * @param liqafc the liqafc to set
	 */
	public void setLiqafc(CommonsMultipartFile liqafc) {
		this.liqafc = liqafc;
	}
	/**
	 * @return the certemp
	 */
	public CommonsMultipartFile getCertemp() {
		return certemp;
	}
	/**
	 * @param certemp the certemp to set
	 */
	public void setCertemp(CommonsMultipartFile certemp) {
		this.certemp = certemp;
	}
	
	
}
