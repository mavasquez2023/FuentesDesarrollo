package cl.araucana.wslme.integration.jaxrpc.ws;

public class LMEValEmpCCAFRequest {
	private String codigoOperador;
	private String claveOperador;
	private String codigoCCAF;
	private Integer rutTrabajadorNum; 
	private String rutTrabajadorDig;
	
	public String getClaveOperador() {
		return claveOperador;
	}
	public void setClaveOperador(String claveOperador) {
		this.claveOperador = claveOperador;
	}
	public String getCodigoCCAF() {
		return codigoCCAF;
	}
	public void setCodigoCCAF(String codigoCCAF) {
		this.codigoCCAF = codigoCCAF;
	}
	public String getCodigoOperador() {
		return codigoOperador;
	}
	public void setCodigoOperador(String codigoOperador) {
		this.codigoOperador = codigoOperador;
	}
	public String getRutTrabajadorDig() {
		return rutTrabajadorDig;
	}
	public void setRutTrabajadorDig(String rutTrabajadorDig) {
		this.rutTrabajadorDig = rutTrabajadorDig;
	}
	public Integer getRutTrabajadorNum() {
		return rutTrabajadorNum;
	}
	public void setRutTrabajadorNum(Integer rutTrabajadorNum) {
		this.rutTrabajadorNum = rutTrabajadorNum;
	}
}
