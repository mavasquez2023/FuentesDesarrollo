package cl.laaraucana.simat.documentos.JasperReport;

public class ValoresVO {

	private String capital;
	private String seguros;
	private String interes;
	private String gastos_cobranza;
	private String descuentos;
	private String total_pagar;

	public ValoresVO() {
	}

	public ValoresVO(String capital, String seguros, String interes, String gastos_cobranza, String descuentos, String total_pagar) {
		super();
		this.capital = capital;
		this.seguros = seguros;
		this.interes = interes;
		this.gastos_cobranza = gastos_cobranza;
		this.descuentos = descuentos;
		this.total_pagar = total_pagar;
	}

	public String getCapital() {
		return capital;
	}

	public String getSeguros() {
		return seguros;
	}

	public String getInteres() {
		return interes;
	}

	public String getGastos_cobranza() {
		return gastos_cobranza;
	}

	public String getDescuentos() {
		return descuentos;
	}

	public String getTotal_pagar() {
		return total_pagar;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public void setSeguros(String seguros) {
		this.seguros = seguros;
	}

	public void setInteres(String interes) {
		this.interes = interes;
	}

	public void setGastos_cobranza(String gastos_cobranza) {
		this.gastos_cobranza = gastos_cobranza;
	}

	public void setDescuentos(String descuentos) {
		this.descuentos = descuentos;
	}

	public void setTotal_pagar(String total_pagar) {
		this.total_pagar = total_pagar;
	}

}
