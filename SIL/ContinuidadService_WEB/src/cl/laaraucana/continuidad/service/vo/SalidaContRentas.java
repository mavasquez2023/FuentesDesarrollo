package cl.laaraucana.continuidad.service.vo;

import java.util.List;


public class SalidaContRentas {
	private RespuestaVO respuesta;
	private List<Licencia> licencias;
	private List<Renta> rentas;
	
	public RespuestaVO getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(RespuestaVO respuesta) {
		this.respuesta = respuesta;
	}
	public List<Licencia> getLicencias() {
		return licencias;
	}
	public List<Renta> getRentas() {
		return rentas;
	}
	public void setLicencias(List<Licencia> licencias) {
		this.licencias = licencias;
	}
	public void setRentas(List<Renta> rentas) {
		this.rentas = rentas;
	}

}
