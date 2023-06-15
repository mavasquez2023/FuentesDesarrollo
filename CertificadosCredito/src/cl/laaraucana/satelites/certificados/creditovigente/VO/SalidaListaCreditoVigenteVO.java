package cl.laaraucana.satelites.certificados.creditovigente.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaListaCreditoVigenteVO extends AbstractSalidaVO {
	
	private List<SalidaCreditoVigenteVO> listaCreditos = new ArrayList<SalidaCreditoVigenteVO>();

	public List<SalidaCreditoVigenteVO> getListaCreditos() {
		return listaCreditos;
	}

	public void setListaCreditos(List<SalidaCreditoVigenteVO> listaCreditos) {
		this.listaCreditos = listaCreditos;
	}
}
