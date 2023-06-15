package cl.laaraucana.compromisototal.dao.intercaja.domain;

import java.util.ArrayList;

public class SalidaListaIntercajaVO {

	private ArrayList<Sinaf20h> listaIntercaja;
	private String codError;

	public String getCodError() {
		return codError;
	}

	public void setCodError(String codError) {
		this.codError = codError;
	}

	public ArrayList<Sinaf20h> getListaIntercaja() {
		return listaIntercaja;
	}

	public void setListaIntercaja(ArrayList<Sinaf20h> listaIntercaja) {
		this.listaIntercaja = listaIntercaja;
	}

	

}
