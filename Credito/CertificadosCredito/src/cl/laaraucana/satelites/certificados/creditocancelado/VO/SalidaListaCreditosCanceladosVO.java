package cl.laaraucana.satelites.certificados.creditocancelado.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaListaCreditosCanceladosVO extends AbstractSalidaVO{

	private List<SalidaCreditoCanceladoVO> listaCreditos = new ArrayList<SalidaCreditoCanceladoVO>();

	public List<SalidaCreditoCanceladoVO> getListaCreditos() {
		return listaCreditos;
	}

	public void setListaCreditos(List<SalidaCreditoCanceladoVO> listaCreditos) {
		this.listaCreditos = listaCreditos;
	}
	
	
	
}
