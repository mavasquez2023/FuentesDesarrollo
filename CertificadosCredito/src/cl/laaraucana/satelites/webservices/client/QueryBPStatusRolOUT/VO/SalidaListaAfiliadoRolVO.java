package cl.laaraucana.satelites.webservices.client.QueryBPStatusRolOUT.VO;

import java.util.ArrayList;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaListaAfiliadoRolVO extends AbstractSalidaVO {

	private ArrayList<SalidaAfiliadoRolVO> listaAfiliado = new ArrayList<SalidaAfiliadoRolVO>();

	public ArrayList<SalidaAfiliadoRolVO> getListaAfiliado() {
		return listaAfiliado;
	}

	public void setListaAfiliado(ArrayList<SalidaAfiliadoRolVO> listaAfiliado) {
		this.listaAfiliado = listaAfiliado;
	}

}
