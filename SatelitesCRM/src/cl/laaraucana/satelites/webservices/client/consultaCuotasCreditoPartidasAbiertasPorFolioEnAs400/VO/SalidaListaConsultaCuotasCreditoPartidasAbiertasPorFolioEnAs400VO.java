package cl.laaraucana.satelites.webservices.client.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaListaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO extends AbstractSalidaVO {
	private List<SalidaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO> listaCuotas = new ArrayList<SalidaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO>();

	public List<SalidaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO> getListaCuotas() {
		return listaCuotas;
	}

	public void setListaCuotas(
			List<SalidaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO> listaCuotas) {
		this.listaCuotas = listaCuotas;
	}
	
	


}
