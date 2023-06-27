package conector.vo;

import conector.lme.ws.cliente.operador.RespuestaDetalleLicencia;

public class SalidaLMEDetLcc {

	private boolean error1 = false;
	private boolean error2 = false;
	private long tiempo1 = 0;
	private long tiempo2 = 0;
	RespuestaDetalleLicencia respuesta;

	public boolean isError1() {
		return error1;
	}

	public void setError1(boolean error1) {
		this.error1 = error1;
	}

	public boolean isError2() {
		return error2;
	}

	public void setError2(boolean error2) {
		this.error2 = error2;
	}

	public RespuestaDetalleLicencia getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(RespuestaDetalleLicencia respuesta) {
		this.respuesta = respuesta;
	}

	public long getTiempo1() {
		return tiempo1;
	}

	public void setTiempo1(long tiempo1) {
		this.tiempo1 = tiempo1;
	}

	public long getTiempo2() {
		return tiempo2;
	}

	public void setTiempo2(long tiempo2) {
		this.tiempo2 = tiempo2;
	}

}
