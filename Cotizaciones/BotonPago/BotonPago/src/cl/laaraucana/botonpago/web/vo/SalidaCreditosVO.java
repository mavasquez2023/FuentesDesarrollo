package cl.laaraucana.botonpago.web.vo;

import java.util.ArrayList;

public class SalidaCreditosVO {

	private ArrayList<CreditoVO> listaCreditos = new ArrayList<CreditoVO>();
	private String codError;
	private String mensaje;

	public ArrayList<CreditoVO> getListaCreditos() {
		return listaCreditos;
	}

	public void setListaCreditos(ArrayList<CreditoVO> listaCreditos) {
		this.listaCreditos = listaCreditos;
	}

	public String getCodError() {
		return codError;
	}

	public void setCodError(String codError) {
		this.codError = codError;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
