package cl.laaraucana.capaservicios.xml.notificacion;

public class Transferencia {

	private String codigoBanco;
	private String cuentaDestino;
	private String monto;
	private String codMoneda;
	private String estadoPago;
	private String glosaPago;
	private String folioInternoSistemaOrigen;
	private String folioTesoreria;
	
	public Transferencia (){}
	
	public Transferencia (String codigoBanco, String cuentaDestino, String monto,
			String codMoneda, String estadoPago, String glosaPago, String folioInternoSistemaOrigen,
			String folioTesoreria){
		this.codigoBanco=codigoBanco;
		this.cuentaDestino=cuentaDestino;
		this.monto=monto;
		this.codMoneda=codMoneda;
		this.estadoPago=estadoPago;
		this.glosaPago=glosaPago;
		this.folioInternoSistemaOrigen=folioInternoSistemaOrigen;
		this.folioTesoreria=folioTesoreria;
	}
	
	public String getCodigoBanco() {
		return codigoBanco;
	}
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}
	public String getCuentaDestino() {
		return cuentaDestino;
	}
	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}
	public String getEstadoPago() {
		return estadoPago;
	}
	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}
	public String getGlosaPago() {
		return glosaPago;
	}
	public void setGlosaPago(String glosaPago) {
		this.glosaPago = glosaPago;
	}
	public String getFolioInternoSistemaOrigen() {
		return folioInternoSistemaOrigen;
	}
	public void setFolioInternoSistemaOrigen(String folioInternoSistemaOrigen) {
		this.folioInternoSistemaOrigen = folioInternoSistemaOrigen;
	}
	public String getFolioTesoreria() {
		return folioTesoreria;
	}
	public void setFolioTesoreria(String folioTesoreria) {
		this.folioTesoreria = folioTesoreria;
	}
}
