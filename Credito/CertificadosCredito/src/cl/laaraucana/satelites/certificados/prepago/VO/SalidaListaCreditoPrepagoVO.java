package cl.laaraucana.satelites.certificados.prepago.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaListaCreditoPrepagoVO extends AbstractSalidaVO {
	
	private List<SalidaCreditoPrepagoVO> listaCreditos = new ArrayList<SalidaCreditoPrepagoVO>();

	public List<SalidaCreditoPrepagoVO> getListaCreditos() {
		return listaCreditos;
	}

	public void setListaCreditos(
			List<SalidaCreditoPrepagoVO> listaCreditos) {
		this.listaCreditos = listaCreditos;
	}
}