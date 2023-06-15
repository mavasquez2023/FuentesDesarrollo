package cl.laaraucana.compromisototal.webservice.client.asicom.VO;

import java.util.ArrayList;

import cl.laaraucana.compromisototal.webservice.models.AbstractSalidaVO;

public class SalidaListaAsicomVO extends AbstractSalidaVO {

	private String codigoError;

	private ArrayList<SalidaAsicomVO> listaCreditos = new ArrayList<SalidaAsicomVO>();

	public ArrayList<SalidaAsicomVO> getListaCreditos() {
		return listaCreditos;
	}

	public void setListaCreditos(ArrayList<SalidaAsicomVO> arrayList) {
		this.listaCreditos = arrayList;
	}

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

}
