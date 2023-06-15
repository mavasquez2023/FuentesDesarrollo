package cl.laaraucana.compromisototal.webservice.client.asicom.VO;

import java.util.ArrayList;

import cl.laaraucana.compromisototal.webservice.models.AbstractSalidaVO;

public class DetalleSalidaListaAsicomVO extends AbstractSalidaVO {

	private ArrayList<DetalleSalidaAsicomVO> listaDetalleCreditos = new ArrayList<DetalleSalidaAsicomVO>();

	public ArrayList<DetalleSalidaAsicomVO> getListaDetalleCreditos() {
		return listaDetalleCreditos;
	}

	public void setListaDetalleCreditos(ArrayList<DetalleSalidaAsicomVO> listaDetalleCreditos) {
		this.listaDetalleCreditos = listaDetalleCreditos;
	}

}
