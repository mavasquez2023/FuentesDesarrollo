package cl.laaraucana.mandato.vo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class CargaRechazoVO {

	private CommonsMultipartFile rechazo;
	
	/**
	 * @return the rechazo
	 */
	public CommonsMultipartFile getRechazo() {
		return rechazo;
	}
	/**
	 * @param rechazo the rechazo to set
	 */
	public void setRechazo(CommonsMultipartFile rechazo) {
		this.rechazo = rechazo;
	}
}
