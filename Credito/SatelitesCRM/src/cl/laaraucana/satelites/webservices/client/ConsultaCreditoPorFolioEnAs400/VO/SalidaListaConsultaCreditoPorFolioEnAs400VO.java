package cl.laaraucana.satelites.webservices.client.ConsultaCreditoPorFolioEnAs400.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaListaConsultaCreditoPorFolioEnAs400VO extends AbstractSalidaVO {
	private List<SalidaConsultaCreditoPorFolioEnAs400VO> listaCuotas = new ArrayList<SalidaConsultaCreditoPorFolioEnAs400VO>();

	public List<SalidaConsultaCreditoPorFolioEnAs400VO> getListaCuotas() {
		return listaCuotas;
	}

	public void setListaCuotas(
			List<SalidaConsultaCreditoPorFolioEnAs400VO> listaCuotas) {
		this.listaCuotas = listaCuotas;
	}
	
	


}
