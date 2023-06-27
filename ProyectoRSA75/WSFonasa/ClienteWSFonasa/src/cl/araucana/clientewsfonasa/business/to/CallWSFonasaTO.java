package cl.araucana.clientewsfonasa.business.to;

import java.util.Date;

public class CallWSFonasaTO {
	
	private Integer idCall;
	private Date fechaHora;
	private Short estado;
	private Short tipo;
	private Short codReturn;
	private String msjReturn;
	
	private RequestWSFonasaTO request;
	private ResponseWSFonasaTO response;
	
	public CallWSFonasaTO(){}
	
	public CallWSFonasaTO(Date fechaHora, Short estado, Short tipo, RequestWSFonasaTO request){
		this.fechaHora = fechaHora;
		this.estado = estado;
		this.tipo = tipo;
		this.request = request;
	}
	
	public CallWSFonasaTO(Integer idCall, Date fechaHora, Short estado, Short tipo, Short codReturn, String msjReturn, RequestWSFonasaTO request, ResponseWSFonasaTO response) {
		super();
		this.idCall = idCall;
		this.fechaHora = fechaHora;
		this.estado = estado;
		this.tipo = tipo;
		this.codReturn = codReturn;
		this.msjReturn = msjReturn;
		this.request = request;
		this.response = response;
	}

	public Short getCodReturn() {
		return codReturn;
	}

	public void setCodReturn(Short codReturn) {
		this.codReturn = codReturn;
	}

	public Short getEstado() {
		return estado;
	}

	public void setEstado(Short estado) {
		this.estado = estado;
	}

	public Short getTipo() {
		return tipo;
	}

	public void setTipo(Short tipo) {
		this.tipo = tipo;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Integer getIdCall() {
		return idCall;
	}

	public void setIdCall(Integer idCall) {
		this.idCall = idCall;
	}

	public String getMsjReturn() {
		return msjReturn;
	}

	public void setMsjReturn(String msjReturn) {
		this.msjReturn = msjReturn;
	}

	public RequestWSFonasaTO getRequest() {
		return request;
	}

	public void setRequest(RequestWSFonasaTO request) {
		this.request = request;
	}

	public ResponseWSFonasaTO getResponse() {
		return response;
	}

	public void setResponse(ResponseWSFonasaTO response) {
		this.response = response;
	}
	
	
}
