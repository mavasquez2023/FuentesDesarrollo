package cl.laaraucana.capaservicios.xml.solicitud;

//import javax.xml.bind.annotation.XmlElement;

public class Solicitud {

	private String tipoCuentaBancaria;
	private String cuentaDestino;
	private String codigoBanco;
	private String glosaCartolaDestino;
	private String codigoMoneda;
	private String montoTotal;
	private ListaTEF listaTEF;
	
	
	public Solicitud(){}


	public Solicitud(String tipoCuentaBancaria, String cuentaDestino,
			String codigoBanco, String glosaCartolaDestino,
			String codigoMoneda, String montoTotal, ListaTEF listaTEF) {
		super();
		this.tipoCuentaBancaria = tipoCuentaBancaria;
		this.cuentaDestino = cuentaDestino;
		this.codigoBanco = codigoBanco;
		this.glosaCartolaDestino = glosaCartolaDestino;
		this.codigoMoneda = codigoMoneda;
		this.montoTotal = montoTotal;
		this.listaTEF = listaTEF;
	}


	public String getTipoCuentaBancaria() {
		return tipoCuentaBancaria;
	}


	public void setTipoCuentaBancaria(String tipoCuentaBancaria) {
		this.tipoCuentaBancaria = tipoCuentaBancaria;
	}


	public String getCuentaDestino() {
		return cuentaDestino;
	}


	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}


	public String getCodigoBanco() {
		return codigoBanco;
	}


	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}


	public String getGlosaCartolaDestino() {
		return glosaCartolaDestino;
	}


	public void setGlosaCartolaDestino(String glosaCartolaDestino) {
		this.glosaCartolaDestino = glosaCartolaDestino;
	}


	public String getCodigoMoneda() {
		return codigoMoneda;
	}


	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}


	public String getMontoTotal() {
		return montoTotal;
	}


	public void setMontoTotal(String montoTotal) {
		this.montoTotal = montoTotal;
	}


	public ListaTEF getListaTEF() {
		return listaTEF;
	}


	public void setListaTEF(ListaTEF listaTEF) {
		this.listaTEF = listaTEF;
	}
	
	

}
