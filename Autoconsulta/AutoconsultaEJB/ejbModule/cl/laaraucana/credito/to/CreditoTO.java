package cl.laaraucana.credito.to;

import java.io.Serializable;
import java.util.Date;

public class CreditoTO extends CreditoBaseTO {
	private Date fechaOtorgamiento;
	private float montoNominal;
	private float montoReajustado;
	private float tasa;
	private int cantidadCuotas;
	private String indicadorSeguroCesantia;
	private String indicadorSeguroDesgravamen;
	private String indicadorSeguroInvalidez;
	private float montoCuota;
	private float montoPagado;
	private Date proximoVencimiento;
	private int proximaCuota;
	private int estadoUltimaCuota;
	private int tipoOperacion;
	private long rutAval1;
	private long rutAval2;
	private long rutTitular;
	private long rutEmpresa;
	private int cantidadCuotasPagadas;
	private int estadoCobranza;
	private long impuesto;
	private String linea;
	
	
	public CreditoTO() {
		this.oficina = ""; 
		this.folio = 0;
		this.fechaOtorgamiento = new Date();
		this.estado = 0;
		this.montoNominal = 0;
		this.montoReajustado = 0;
		this.tasa = 0;
		this.cantidadCuotas = 0;
		this.indicadorSeguroCesantia = "";
		this.indicadorSeguroDesgravamen = "";
		this.indicadorSeguroInvalidez = "";
		this.montoCuota = 0;
		this.proximoVencimiento = new Date();
		this.proximaCuota = 0;
		this.estadoUltimaCuota = 0;
		this.tipoOperacion = 0;
/*
		this.aval1 = new PersonaVO();
		this.aval2 = new PersonaVO();
		this.titular = new PersonaVO();
		this.empresa = new PersonaVO();
*/
		this.cantidadCuotasPagadas = 0;
	
	}
	
	public int getCantidadCuotasImpagas() {
		return (this.cantidadCuotas - this.cantidadCuotasPagadas );
	}
	
	public int getCantidadCuotas() {
		return cantidadCuotas;
	}
	public void setCantidadCuotas(int cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}
	
	public int getEstado() {
		return estado;
	}
	
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getEstadoUltimaCuota() {
		return estadoUltimaCuota;
	}
	public void setEstadoUltimaCuota(int estadoUltimaCuota) {
		this.estadoUltimaCuota = estadoUltimaCuota;
	}
	public Date getFechaOtorgamiento() {
		return fechaOtorgamiento;
	}
	public void setFechaOtorgamiento(Date fechaOtorgamiento) {
		this.fechaOtorgamiento = fechaOtorgamiento;
	}
	public long getFolio() {
		return folio;
	}
	public void setFolio(long folio) {
		this.folio = folio;
	}
	public String getIndicadorSeguroCesantia() {
		return indicadorSeguroCesantia;
	}
	public void setIndicadorSeguroCesantia(String indicadorSeguroCesantia) {
		this.indicadorSeguroCesantia = indicadorSeguroCesantia;
	}
	public String getIndicadorSeguroDeshaucio() {
		return indicadorSeguroDesgravamen;
	}
	public void setIndicadorSeguroDeshaucio(String indicadorSeguroDeshaucio) {
		this.indicadorSeguroDesgravamen = indicadorSeguroDeshaucio;
	}
	public String getIndicadorSeguroInvalidez() {
		return indicadorSeguroInvalidez;
	}
	public void setIndicadorSeguroInvalidez(String indicadorSeguroInvalidez) {
		this.indicadorSeguroInvalidez = indicadorSeguroInvalidez;
	}
	public float getMontoCuota() {
		return montoCuota;
	}
	public void setMontoCuota(float montoCuota) {
		this.montoCuota = montoCuota;
	}
	public float getMontoNominal() {
		return montoNominal;
	}
	public void setMontoNominal(float montoNominal) {
		this.montoNominal = montoNominal;
	}
	public float getMontoReajustado() {
		return montoReajustado;
	}
	public void setMontoReajustado(float montoReajustado) {
		this.montoReajustado = montoReajustado;
	}
	public String getOficina() {
		return oficina;
	}
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	public int getProximaCuota() {
		return proximaCuota;
	}
	public void setProximaCuota(int proximaCuota) {
		this.proximaCuota = proximaCuota;
	}
	public Date getProximoVencimiento() {
		return proximoVencimiento;
	}
	public void setProximoVencimiento(Date proximoVencimiento) {
		this.proximoVencimiento = proximoVencimiento;
	}
	public float getTasa() {
		return tasa;
	}
	public void setTasa(float tasa) {
		this.tasa = tasa;
	}
	public int getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(int tipoCredito) {
		this.tipoOperacion = tipoCredito;
	}
	public int getCantidadCuotasPagadas() {
		return cantidadCuotasPagadas;
	}
	public void setCantidadCuotasPagadas(int cantidadCuotasPagadas) {
		this.cantidadCuotasPagadas = cantidadCuotasPagadas;
	}

	public long getRutAval1() {
		return rutAval1;
	}

	public void setRutAval1(long rutAval1) {
		this.rutAval1 = rutAval1;
	}

	public long getRutAval2() {
		return rutAval2;
	}

	public void setRutAval2(long rutAval2) {
		this.rutAval2 = rutAval2;
	}

	public long getRutEmpresa() {
		return rutEmpresa;
	}

	public void setRutEmpresa(long rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	public long getRutTitular() {
		return rutTitular;
	}

	public void setRutTitular(long rutTitular) {
		this.rutTitular = rutTitular;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public float getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(float montoPagado) {
		this.montoPagado = montoPagado;
	}

	public int getEstadoCobranza() {
		return estadoCobranza;
	}

	public void setEstadoCobranza(int estadoCobranza) {
		this.estadoCobranza = estadoCobranza;
	}

	public long getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(long impuesto) {
		this.impuesto = impuesto;
	}
	
	public void printThis() {
		System.out.println ("CreditoVO printThis ...." );
		System.out.println ("this.oficina " + this.oficina);
		System.out.println ("folio " + this.folio);

		System.out.println ("fechaOtorgamiento " + this.fechaOtorgamiento);
		System.out.println ("estado " + this.estado);
		System.out.println ("montoNominal " + this.montoNominal);
		System.out.println ("montoReajustado " + this.montoReajustado);

		System.out.println ("tasa " + this.tasa);
		System.out.println ("cantidadCuotas " + this.cantidadCuotas);
		System.out.println ("indicadorSeguroCesantia " + this.indicadorSeguroCesantia);

		System.out.println ("indicadorSeguroDeshaucio " + this.indicadorSeguroDesgravamen);
		System.out.println ("indicadorSeguroInvalidez " + this.indicadorSeguroInvalidez);
		System.out.println ("montoCuota " + this.montoCuota);
		System.out.println ("proximoVencimiento " + this.proximoVencimiento);
		System.out.println ("proximaCuota " + this.proximaCuota);
		System.out.println ("estadoUltimaCuota " + this.estadoUltimaCuota);
		System.out.println ("tipoCredito " + this.tipoOperacion);

	
	}
	

}
