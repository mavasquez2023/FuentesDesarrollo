package cl.laaraucana.envioFormularioASFAM.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FicherosVo {

	private CommonsMultipartFile solicitud;
	private CommonsMultipartFile certificado;

	public CommonsMultipartFile getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(CommonsMultipartFile solicitud) {
		this.solicitud = solicitud;
	}

	public CommonsMultipartFile getCertificado() {
		return certificado;
	}

	public void setCertificado(CommonsMultipartFile certificado) {
		this.certificado = certificado;
	}

}
