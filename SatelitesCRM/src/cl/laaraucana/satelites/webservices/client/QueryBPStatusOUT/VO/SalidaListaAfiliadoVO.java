package cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.VO;

import java.util.ArrayList;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaListaAfiliadoVO extends AbstractSalidaVO {

	private ArrayList<SalidaAfiliadoVO> listaAfiliado = new ArrayList<SalidaAfiliadoVO>();

	public ArrayList<SalidaAfiliadoVO> getListaAfiliado() {
		return listaAfiliado;
	}

	public void setListaAfiliado(ArrayList<SalidaAfiliadoVO> listaAfiliado) {
		this.listaAfiliado = listaAfiliado;
	}

}
