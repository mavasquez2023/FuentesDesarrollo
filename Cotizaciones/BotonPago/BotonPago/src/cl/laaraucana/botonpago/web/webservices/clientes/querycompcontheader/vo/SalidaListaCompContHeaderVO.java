package cl.laaraucana.botonpago.web.webservices.clientes.querycompcontheader.vo;

import java.util.ArrayList;

import cl.laaraucana.botonpago.web.webservices.model.AbstractSalidaVO;

public class SalidaListaCompContHeaderVO extends AbstractSalidaVO {

	private ArrayList<SalidaCompContHeaderVO> listaCreditos = new ArrayList<SalidaCompContHeaderVO>();

	public ArrayList<SalidaCompContHeaderVO> getListaCreditos() {
		return listaCreditos;
	}

	public void setListaCreditos(ArrayList<SalidaCompContHeaderVO> listaCreditos) {
		this.listaCreditos = listaCreditos;
	}

}
