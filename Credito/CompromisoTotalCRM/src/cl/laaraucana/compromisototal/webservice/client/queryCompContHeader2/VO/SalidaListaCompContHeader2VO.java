package cl.laaraucana.compromisototal.webservice.client.queryCompContHeader2.VO;

import java.util.ArrayList;

import cl.laaraucana.compromisototal.webservice.models.AbstractSalidaVO;

public class SalidaListaCompContHeader2VO extends AbstractSalidaVO {

	private ArrayList<SalidaCompContHeader2VO> detalle;

	public ArrayList<SalidaCompContHeader2VO> getDetalle() {
		return detalle;
	}

	public void setDetalle(ArrayList<SalidaCompContHeader2VO> detalle) {
		this.detalle = detalle;
	}

}
