package cl.laaraucana.ventafullweb.dto;

import java.io.Serializable;
import java.util.Date;

public class BitacoraSimulacionDto implements Serializable {
	
	
	private int rutAfiliado;
	private String dvAfiliado;
	private String nombre;
	private String celular;
	private int montoCampana;
	private int montoSolicitado;
	private int nroCuotas;
	private int montoCuota;
	private int montoMensualSeguroDesgravamen;
	private int montoMensualSeguroCesantia;
	private String tasaInteresMensual;
	private String cae;
	private int impuestoLTE;
	private int gastoNotarial;
	private int costoTotalCredito;
	private Date fechaPrimerVencimiento;
	private String sucursal;
	/**
	 * @return the rutAfiliado
	 */
	public int getRutAfiliado() {
		return rutAfiliado;
	}
	/**
	 * @param rutAfiliado the rutAfiliado to set
	 */
	public void setRutAfiliado(int rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}
	/**
	 * @return the dvAfiliado
	 */
	public String getDvAfiliado() {
		return dvAfiliado;
	}
	/**
	 * @param dvAfiliado the dvAfiliado to set
	 */
	public void setDvAfiliado(String dvAfiliado) {
		this.dvAfiliado = dvAfiliado;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}
	/**
	 * @param celular the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}
	/**
	 * @return the montoCampana
	 */
	public int getMontoCampana() {
		return montoCampana;
	}
	/**
	 * @param montoCampana the montoCampana to set
	 */
	public void setMontoCampana(int montoCampana) {
		this.montoCampana = montoCampana;
	}
	/**
	 * @return the montoSolicitado
	 */
	public int getMontoSolicitado() {
		return montoSolicitado;
	}
	/**
	 * @param montoSolicitado the montoSolicitado to set
	 */
	public void setMontoSolicitado(int montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}
	/**
	 * @return the nroCuotas
	 */
	public int getNroCuotas() {
		return nroCuotas;
	}
	/**
	 * @param nroCuotas the nroCuotas to set
	 */
	public void setNroCuotas(int nroCuotas) {
		this.nroCuotas = nroCuotas;
	}
	/**
	 * @return the montoCuota
	 */
	public int getMontoCuota() {
		return montoCuota;
	}
	/**
	 * @param montoCuota the montoCuota to set
	 */
	public void setMontoCuota(int montoCuota) {
		this.montoCuota = montoCuota;
	}
	/**
	 * @return the montoMensualSeguroDesgravamen
	 */
	public int getMontoMensualSeguroDesgravamen() {
		return montoMensualSeguroDesgravamen;
	}
	/**
	 * @param montoMensualSeguroDesgravamen the montoMensualSeguroDesgravamen to set
	 */
	public void setMontoMensualSeguroDesgravamen(int montoMensualSeguroDesgravamen) {
		this.montoMensualSeguroDesgravamen = montoMensualSeguroDesgravamen;
	}
	/**
	 * @return the montoMensualSeguroCesantia
	 */
	public int getMontoMensualSeguroCesantia() {
		return montoMensualSeguroCesantia;
	}
	/**
	 * @param montoMensualSeguroCesantia the montoMensualSeguroCesantia to set
	 */
	public void setMontoMensualSeguroCesantia(int montoMensualSeguroCesantia) {
		this.montoMensualSeguroCesantia = montoMensualSeguroCesantia;
	}
	/**
	 * @return the tasaInteresMensual
	 */
	public String getTasaInteresMensual() {
		return tasaInteresMensual;
	}
	/**
	 * @param tasaInteresmensual the tasaInteresMensual to set
	 */
	public void setTasaInteresMensual(String tasaInteresMensual) {
		this.tasaInteresMensual = tasaInteresMensual;
	}
	/**
	 * @return the cae
	 */
	public String getCae() {
		return cae;
	}
	/**
	 * @param cae the cae to set
	 */
	public void setCae(String cae) {
		this.cae = cae;
	}
	/**
	 * @return the impuestoLTE
	 */
	public int getImpuestoLTE() {
		return impuestoLTE;
	}
	/**
	 * @param impuestoLTE the impuestoLTE to set
	 */
	public void setImpuestoLTE(int impuestoLTE) {
		this.impuestoLTE = impuestoLTE;
	}
	/**
	 * @return the gastoNotarial
	 */
	public int getGastoNotarial() {
		return gastoNotarial;
	}
	/**
	 * @param gastoNotarial the gastoNotarial to set
	 */
	public void setGastoNotarial(int gastoNotarial) {
		this.gastoNotarial = gastoNotarial;
	}
	/**
	 * @return the costoTotalCredito
	 */
	public int getCostoTotalCredito() {
		return costoTotalCredito;
	}
	/**
	 * @param costoTotalCredito the costoTotalCredito to set
	 */
	public void setCostoTotalCredito(int costoTotalCredito) {
		this.costoTotalCredito = costoTotalCredito;
	}
	/**
	 * @return the fechaPrimerVencimiento
	 */
	public Date getFechaPrimerVencimiento() {
		return fechaPrimerVencimiento;
	}
	/**
	 * @param fechaPrimerVencimiento the fechaPrimerVencimiento to set
	 */
	public void setFechaPrimerVencimiento(Date fechaPrimerVencimiento) {
		this.fechaPrimerVencimiento = fechaPrimerVencimiento;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	
	
	
	
	
	
}
