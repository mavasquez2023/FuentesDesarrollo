package cl.laaraucana.satelites.webservices.client.ConsultaDatosPagosCreditosPorFolioAs400.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaListaConsultaDatosPagosCreditosPorFolioVO extends AbstractSalidaVO{

    protected List<SalidaConsultaDatosPagosCreditosPorFolioVO> listaCuotas = new ArrayList<SalidaConsultaDatosPagosCreditosPorFolioVO>();
    protected String nTotalCuotas;
    
	public List<SalidaConsultaDatosPagosCreditosPorFolioVO> getListaCuotas() {
		return listaCuotas;
	}

	public void setListaCuotas(List<SalidaConsultaDatosPagosCreditosPorFolioVO> listaCuotas) {
		this.listaCuotas = listaCuotas;
	}

	public String getnTotalCuotas() {
		return nTotalCuotas;
	}

	public void setnTotalCuotas(String nTotalCuotas) {
		this.nTotalCuotas = nTotalCuotas;
	}

}
