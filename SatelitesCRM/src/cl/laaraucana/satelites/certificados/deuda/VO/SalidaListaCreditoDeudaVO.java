package cl.laaraucana.satelites.certificados.deuda.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaListaCreditoDeudaVO extends AbstractSalidaVO {
	
	private List<SalidaCreditoDeudaVO> listaCreditos = new ArrayList<SalidaCreditoDeudaVO>();

	public List<SalidaCreditoDeudaVO> getListaCreditos() {
		return listaCreditos;
	}

	public void setListaCreditos(
			List<SalidaCreditoDeudaVO> listaCreditos) {
		this.listaCreditos = listaCreditos;
	}
}