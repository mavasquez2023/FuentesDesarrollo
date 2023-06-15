package cl.laaraucana.satelites.certificados.finiquito.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaListaFiniquitoVO extends AbstractSalidaVO {
	
	private List<SalidaFiniquitoVO> listaCreditos = new ArrayList<SalidaFiniquitoVO>();

	public List<SalidaFiniquitoVO> getListaCreditos() {
		return listaCreditos;
	}

	public void setListaCreditos(
			List<SalidaFiniquitoVO> listaCreditos) {
		this.listaCreditos = listaCreditos;
	}
}