package cl.laaraucana.botonpago.web.webservices.clientes.querybpstatus.vo;

import java.util.ArrayList;

import cl.laaraucana.botonpago.web.webservices.model.AbstractSalidaVO;

public class SalidaListaQueryBPVO extends AbstractSalidaVO {

	ArrayList<SalidaQueryBPVO> lista = new ArrayList<SalidaQueryBPVO>();

	public ArrayList<SalidaQueryBPVO> getLista() {
		return lista;
	}

	public void setLista(ArrayList<SalidaQueryBPVO> lista) {
		this.lista = lista;
	}

}
