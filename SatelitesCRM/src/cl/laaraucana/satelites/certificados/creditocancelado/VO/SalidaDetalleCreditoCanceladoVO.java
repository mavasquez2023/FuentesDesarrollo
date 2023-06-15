package cl.laaraucana.satelites.certificados.creditocancelado.VO;

public class SalidaDetalleCreditoCanceladoVO {
	private String nCuota;
	private String fecVencimiento;
	private String fecPago;
	private String oficina;
	private String docPago;//Documento Pago;
	private double monto;
	private String estPago; //estado pago;.
	private String tipoMoneda;
	
	public SalidaDetalleCreditoCanceladoVO(String nCuota,
			String fecVencimiento, String fecPago, String oficina,
			String docPago, double monto, String estPago) {
		super();
		this.nCuota = nCuota;
		this.fecVencimiento = fecVencimiento;
		this.fecPago = fecPago;
		this.oficina = oficina;
		this.docPago = docPago;
		this.monto = monto;
		this.estPago = estPago;
	}
	
	public SalidaDetalleCreditoCanceladoVO() {
		super();
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
	public String getFecPago() {
		return fecPago;
	}
	public void setFecPago(String fecPago) {
		this.fecPago = fecPago;
	}
	public String getOficina() {
		return oficina;
	}
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	public String getDocPago() {
		return docPago;
	}
	public void setDocPago(String docPago) {
		this.docPago = docPago;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public String getEstPago() {
		return estPago;
	}
	public void setEstPago(String estPago) {
		this.estPago = estPago;
	}

	public String getTipoMoneda() {
		return tipoMoneda;
	}

	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}
	
	
}
