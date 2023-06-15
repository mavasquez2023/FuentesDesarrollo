package cl.laaraucana.compromisototal.webservice.client.queryCompContHeader.VO;

import java.util.ArrayList;

import cl.laaraucana.compromisototal.webservice.models.AbstractSalidaVO;

public class SalidaListaCompContHeaderVO extends AbstractSalidaVO {

	private ArrayList<SalidaCompContHeaderVO> detalle;

	public ArrayList<SalidaCompContHeaderVO> getDetalle() {
		return detalle;
	}

	public void setDetalle(ArrayList<SalidaCompContHeaderVO> detalle) {
		this.detalle = detalle;
	}

}
