package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.Date;

public class CreditoVO  implements Serializable {
	private int oficina; 
	private long folio;
	private Date fechaOtorgamiento;
	private int estado;
	private float montoNominal;
	private float montoReajustado;
	private float tasa;
	private int cantidadCuotas;
	private String indicadorSeguroCesantia;
	private String indicadorSeguroDeshaucio;
	private String indicadorSeguroInvalidez;
	private float montoCuota;
	private Date proximoVencimiento;
	private int proximaCuota;
	private int estadoUltimaCuota;
	private int tipoCredito;
/*
	private PersonaVO aval1;
	private PersonaVO aval2;
	private PersonaVO titular;
	private PersonaVO empresa;
*/
	private int cantidadCuotasPagadas;
	
	public CreditoVO() {
		this.oficina = 0; 
		this.folio = 0;
		this.fechaOtorgamiento = new Date();
		this.estado = 0;
		this.montoNominal = 0;
		this.montoReajustado = 0;
		this.tasa = 0;
		this.cantidadCuotas = 0;
		this.indicadorSeguroCesantia = "";
		this.indicadorSeguroDeshaucio = "";
		this.indicadorSeguroInvalidez = "";
		this.montoCuota = 0;
		this.proximoVencimiento = new Date();
		this.proximaCuota = 0;
		this.estadoUltimaCuota = 0;
		this.tipoCredito = 0;
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
/*
	public PersonaVO getAval1() {
		return aval1;
	}
	public void setAval1(PersonaVO aval1) {
		this.aval1 = aval1;
	}
	public PersonaVO getAval2() {
		return aval2;
	}
	public void setAval2(PersonaVO aval2) {
		this.aval2 = aval2;
	}
*/
	
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
		return indicadorSeguroDeshaucio;
	}
	public void setIndicadorSeguroDeshaucio(String indicadorSeguroDeshaucio) {
		this.indicadorSeguroDeshaucio = indicadorSeguroDeshaucio;
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
	public int getOficina() {
		return oficina;
	}
	public void setOficina(int oficina) {
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
	public int getTipoCredito() {
		return tipoCredito;
	}
	public void setTipoCredito(int tipoCredito) {
		this.tipoCredito = tipoCredito;
	}
	public int getCantidadCuotasPagadas() {
		return cantidadCuotasPagadas;
	}
	public void setCantidadCuotasPagadas(int cantidadCuotasPagadas) {
		this.cantidadCuotasPagadas = cantidadCuotasPagadas;
	}

/*
	public PersonaVO getEmpresa() {
		return empresa;
	}
	public void setEmpresa(PersonaVO empresa) {
		this.empresa = empresa;
	}
	public PersonaVO getTitular() {
		return titular;
	}
	public void setTitular(PersonaVO titular) {
		this.titular = titular;
	}
*/
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

		System.out.println ("indicadorSeguroDeshaucio " + this.indicadorSeguroDeshaucio);
		System.out.println ("indicadorSeguroInvalidez " + this.indicadorSeguroInvalidez);
		System.out.println ("montoCuota " + this.montoCuota);
		System.out.println ("proximoVencimiento " + this.proximoVencimiento);
		System.out.println ("proximaCuota " + this.proximaCuota);
		System.out.println ("estadoUltimaCuota " + this.estadoUltimaCuota);
		System.out.println ("tipoCredito " + this.tipoCredito);

	
	}
	
	
}
