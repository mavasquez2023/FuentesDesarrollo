package cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaListaConsultaCreditosPorRutEnAs400VO extends AbstractSalidaVO {
	
	private List<SalidaConsultaCreditosPorRutEnAs400VO> listaCreditos = new ArrayList<SalidaConsultaCreditosPorRutEnAs400VO>();

	public List<SalidaConsultaCreditosPorRutEnAs400VO> getListaCreditos() {
		return listaCreditos;
	}

	public void setListaCreditos(
			List<SalidaConsultaCreditosPorRutEnAs400VO> listaCreditos) {
		this.listaCreditos = listaCreditos;
	}
	
	

}
