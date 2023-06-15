package cl.laaraucana.satelites.certificados.finiquito.VO;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaDetalleFiniquitoVO extends AbstractSalidaVO {

	private String nCuota;
	private double monto;
	private String estadoCuota; // estado pago;
	private double montoGravamen;
	private String folio;
	private int cuotaCount;
	private String fechaVencimiento;
	private String cantidadTotalCuotas;

	public SalidaDetalleFiniquitoVO() {
	}

	public SalidaDetalleFiniquitoVO(String nCuota, double monto, double montoGravamen) {
		super();
		this.nCuota = nCuota;
		this.monto = monto;
		this.montoGravamen = montoGravamen;
	}

	public String getnCuota() {
		return nCuota;
	}

	public void setnCuota(String nCuota) {
		this.nCuota = nCuota;
	}

	

	public String getEstadoCuota() {
		return estadoCuota;
	}

	public void setEstadoCuota(String estadoCuota) {
		this.estadoCuota = estadoCuota;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
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

	public int getCuotaCount() {
		return cuotaCount;
	}

	public void setCuotaCount(int cuotaCount) {
		this.cuotaCount = cuotaCount;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getCantidadTotalCuotas() {
		return cantidadTotalCuotas;
	}

	public void setCantidadTotalCuotas(String cantidadTotalCuotas) {
		this.cantidadTotalCuotas = cantidadTotalCuotas;
	}

}
