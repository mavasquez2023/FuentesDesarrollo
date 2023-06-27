/*
 * Created on 14-10-2011
 *
 */
package com.microsystem.lme.ibatis.domain;

/**
 * @author microsystem
 *
 */
public class Ilfe011VO extends IlfeBaseVO {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1524482223286628552L;
	protected Integer periodo; //PERIODO
	protected Integer montoApagarSubsidio; //MTOAPOSUB
	protected Integer montoAportePensiones; //MTOAPOPEN
	protected Integer montoSubsidioDiario; //MTOSUBDIA
	protected Integer montoAporteSalud; //MTOAPOSAL
	protected Integer montoSeguroCesantia; //MTOAPOCES
	protected Integer ndiasApagarSubsidios; //NUMDIASUB
	protected Integer ndiasApagarPrevision; //NUMDIAAPO
	protected String marca; //MARCA
	protected String fecProce; //FECPROCE
	protected String fecPropag;//FECPROPAG
	protected String fecDesde;//FECDESDE
	protected String fecHasta;//FECHASTA
	protected String fechaEstado; //FECHAEST
	protected String horaEstado; //HORAEST
	protected Integer tipoLiquidacion; //TIPO
	protected Integer eventoLiquidacion;//EVENTO

	
	/**
	 * @return the fechaEstado
	 */
	public String getFechaEstado() {
		return fechaEstado;
	}
	/**
	 * @return the horaEstado
	 */
	public String getHoraEstado() {
		return horaEstado;
	}
	/**
	 * @param fechaEstado the fechaEstado to set
	 */
	public void setFechaEstado(String fechaEstado) {
		this.fechaEstado = fechaEstado;
	}
	/**
	 * @param horaEstado the horaEstado to set
	 */
	public void setHoraEstado(String horaEstado) {
		this.horaEstado = horaEstado;
	}
	/**
	 * @return Returns the fecProce.
	 */
	public String getFecProce() {
		return fecProce;
	}

	/**
	 * @param fecProce The fecProce to set.
	 */
	public void setFecProce(String fecProce) {
		this.fecProce = fecProce;
	}

	/**
	 * @return Returns the marca.
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca The marca to set.
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return Returns the montoApagarSubsidio.
	 */
	public Integer getMontoApagarSubsidio() {
		return montoApagarSubsidio;
	}

	/**
	 * @param montoApagarSubsidio The montoApagarSubsidio to set.
	 */
	public void setMontoApagarSubsidio(Integer montoApagarSubsidio) {
		this.montoApagarSubsidio = montoApagarSubsidio;
	}

	/**
	 * @return Returns the montoAportePensiones.
	 */
	public Integer getMontoAportePensiones() {
		return montoAportePensiones;
	}

	/**
	 * @param montoAportePensiones The montoAportePensiones to set.
	 */
	public void setMontoAportePensiones(Integer montoAportePensiones) {
		this.montoAportePensiones = montoAportePensiones;
	}

	/**
	 * @return Returns the montoAporteSalud.
	 */
	public Integer getMontoAporteSalud() {
		return montoAporteSalud;
	}

	/**
	 * @param montoAporteSalud The montoAporteSalud to set.
	 */
	public void setMontoAporteSalud(Integer montoAporteSalud) {
		this.montoAporteSalud = montoAporteSalud;
	}

	/**
	 * @return Returns the montoSeguroCesantia.
	 */
	public Integer getMontoSeguroCesantia() {
		return montoSeguroCesantia;
	}

	/**
	 * @param montoSeguroCesantia The montoSeguroCesantia to set.
	 */
	public void setMontoSeguroCesantia(Integer montoSeguroCesantia) {
		this.montoSeguroCesantia = montoSeguroCesantia;
	}

	/**
	 * @return Returns the montoSubsidioDiario.
	 */
	public Integer getMontoSubsidioDiario() {
		return montoSubsidioDiario;
	}

	/**
	 * @param montoSubsidioDiario The montoSubsidioDiario to set.
	 */
	public void setMontoSubsidioDiario(Integer montoSubsidioDiario) {
		this.montoSubsidioDiario = montoSubsidioDiario;
	}

	/**
	 * @return Returns the ndiasApagarPrevision.
	 */
	public Integer getNdiasApagarPrevision() {
		return ndiasApagarPrevision;
	}

	/**
	 * @param ndiasApagarPrevision The ndiasApagarPrevision to set.
	 */
	public void setNdiasApagarPrevision(Integer ndiasApagarPrevision) {
		this.ndiasApagarPrevision = ndiasApagarPrevision;
	}

	/**
	 * @return Returns the ndiasApagarSubsidios.
	 */
	public Integer getNdiasApagarSubsidios() {
		return ndiasApagarSubsidios;
	}

	/**
	 * @param ndiasApagarSubsidios The ndiasApagarSubsidios to set.
	 */
	public void setNdiasApagarSubsidios(Integer ndiasApagarSubsidios) {
		this.ndiasApagarSubsidios = ndiasApagarSubsidios;
	}

	/**
	 * @return Returns the periodo.
	 */
	public Integer getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo The periodo to set.
	 */
	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	/**
	 * @return Returns the fecPropag.
	 */
	public String getFecPropag() {
		return fecPropag;
	}

	/**
	 * @param fecPropag The fecPropag to set.
	 */
	public void setFecPropag(String fecPropag) {
		this.fecPropag = fecPropag;
	}

	/**
	 * @return Returns the fecDesde.
	 */
	public String getFecDesde() {
		return fecDesde;
	}

	/**
	 * @param fecDesde The fecDesde to set.
	 */
	public void setFecDesde(String fecDesde) {
		this.fecDesde = fecDesde;
	}

	/**
	 * @return Returns the fecHasta.
	 */
	public String getFecHasta() {
		return fecHasta;
	}

	/**
	 * @param fecHasta The fecHasta to set.
	 */
	public void setFecHasta(String fecHasta) {
		this.fecHasta = fecHasta;
	}

	/**
	 * @return Returns the tipoLiquidacion.
	 */
	public Integer getTipoLiquidacion() {
		return tipoLiquidacion;
	}

	/**
	 * @param tipoLiquidacion The tipoLiquidacion to set.
	 */
	public void setTipoLiquidacion(Integer tipoLiquidacion) {
		this.tipoLiquidacion = tipoLiquidacion;
	}

	public Integer getEventoLiquidacion() {
		return eventoLiquidacion;
	}

	public void setEventoLiquidacion(Integer eventoLiquidacion) {
		this.eventoLiquidacion = eventoLiquidacion;
	}
}
