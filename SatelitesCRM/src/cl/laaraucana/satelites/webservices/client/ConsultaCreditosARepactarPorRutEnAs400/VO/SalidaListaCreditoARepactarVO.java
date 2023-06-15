package cl.laaraucana.satelites.webservices.client.ConsultaCreditosARepactarPorRutEnAs400.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaListaCreditoARepactarVO extends AbstractSalidaVO {
	
	private List<SalidaCreditoARepactarVO> listaCreditos = new ArrayList<SalidaCreditoARepactarVO>();

	public List<SalidaCreditoARepactarVO> getListaCreditos() {
		return listaCreditos;
	}

	public void setListaCreditos(
			List<SalidaCreditoARepactarVO> listaCreditos) {
		this.listaCreditos = listaCreditos;
	}
}
