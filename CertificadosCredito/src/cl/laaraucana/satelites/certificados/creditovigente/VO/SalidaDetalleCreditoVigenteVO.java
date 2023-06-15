package cl.laaraucana.satelites.certificados.creditovigente.VO;

public class SalidaDetalleCreditoVigenteVO {

	private String nCuota;
	private String fecVencimiento;
	// private String fecPago;
	// private String oficina;
	// private String docPago;//Documento Pago;
	private double monto;
	private String estCuota; // estado pago;
	private double montoGravamen;
	private String folio;
	// private String glosaEstadoPago;
	private String tipoMoneda;
	
	
	
	

	public SalidaDetalleCreditoVigenteVO(String nCuota, String fecVencimiento, double monto, String estCuota, double montoGravamen, String folio, String tipoMoneda) {
		super();
		this.nCuota = nCuota;
		this.fecVencimiento = fecVencimiento;
		this.monto = monto;
		this.estCuota = estCuota;
		this.montoGravamen = montoGravamen;
		this.folio = folio;
		this.tipoMoneda = tipoMoneda;
	}

	public String getnCuota() {
		return nCuota;
	}

	public void setnCuota(String nCuota) {
		this.nCuota = nCuota;
	}

	public String getFecVencimiento() {
		return fecVencimiento;
	}

	public void setFecVencimiento(String fecVencimiento) {
		this.fecVencimiento = fecVencimiento;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public double getMontoGravamen() {
		return montoGravamen;
	}

	public void setMontoGravamen(double montoGravamen) {
		this.montoGravamen = montoGravamen;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public SalidaDetalleCreditoVigenteVO() {
	}

	public String getEstCuota() {
		return estCuota;
	}

	public void setEstCuota(String estCuota) {
		this.estCuota = estCuota;
	}

	public String getTipoMoneda() {
		return tipoMoneda;
	}

	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}

}
