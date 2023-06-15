package cl.laaraucana.botonpago.web.vo;

import java.util.ArrayList;

public class SalidaCuotasVO {

	private ArrayList<CuotasVO> listaCuotas = new ArrayList<CuotasVO>();
	private String codError;
	private String mensaje;

	public SalidaCuotasVO() {
	}

	public ArrayList<CuotasVO> getListaCuotas() {
		return listaCuotas;
	}

	public void setListaCuotas(ArrayList<CuotasVO> listaCuotas) {
		this.listaCuotas = listaCuotas;
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
