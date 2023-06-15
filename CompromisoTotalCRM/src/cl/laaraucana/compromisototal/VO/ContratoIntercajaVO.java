package cl.laaraucana.compromisototal.VO;

public class ContratoIntercajaVO {

	private String periodo;
	private String codigoCajaOrigen;
	private String codigoCajaDestino;
	private String numeroPagare;
	private String identEmpresa;
	private String identDeudor;
	private String nombreDeudor;
	private String identAval;
	private String sujetoDescuento;
	private String montoOfertaCompra;
	private String montoCuotaDeudor;

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public void setCodigoCajaOrigen(String codigoCajaOrigen) {
		this.codigoCajaOrigen = codigoCajaOrigen;
	}

	public void setCodigoCajaDestino(String codigoCajaDestino) {
		this.codigoCajaDestino = codigoCajaDestino;
	}

	public void setNumeroPagare(String numeroPagare) {
		this.numeroPagare = numeroPagare;
	}

	public void setIdentEmpresa(String identEmpresa) {
		this.identEmpresa = identEmpresa;
	}

	public void setIdentDeudor(String identDeudor) {
		this.identDeudor = identDeudor;
	}

	public void setNombreDeudor(String nombreDeudor) {
		this.nombreDeudor = nombreDeudor;
	}

	public void setIdentAval(String identAval) {
		this.identAval = identAval;
	}

	public void setSujetoDescuento(String sujetoDescuento) {
		this.sujetoDescuento = sujetoDescuento;
	}

	public void setMontoOfertaCompra(String montoOfertaCompra) {
		this.montoOfertaCompra = montoOfertaCompra;
	}

	public String getPeriodo() {
		return periodo;
	}

	public String getCodigoCajaOrigen() {
		return codigoCajaOrigen;
	}

	public String getCodigoCajaDestino() {
		return codigoCajaDestino;
	}

	public String getNumeroPagare() {
		return numeroPagare;
	}

	public String getIdentEmpresa() {
		return identEmpresa;
	}

	public String getIdentDeudor() {
		return identDeudor;
	}

	public String getNombreDeudor() {
		return nombreDeudor;
	}

	public String getIdentAval() {
		return identAval;
	}

	public String getSujetoDescuento() {
		return sujetoDescuento;
	}

	public String getMontoOfertaCompra() {
		return montoOfertaCompra;
	}

	/**
	 * @return the montoCuotaDeudor
	 */
	public String getMontoCuotaDeudor() {
		return montoCuotaDeudor;
	}

	/**
	 * @param montoCuotaDeudor the montoCuotaDeudor to set
	 */
	public void setMontoCuotaDeudor(String montoCuotaDeudor) {
		this.montoCuotaDeudor = montoCuotaDeudor;
	}
	
}
