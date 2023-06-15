package cl.laaraucana.satelites.webservices.client.EarlyPayOffSimulation2.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaEarlyPayOffSimulation2 extends AbstractSalidaVO {

	private String montoSeguros;
	private String montoComPrep;
	private String montoIntMoroso;
	private String montoIntDVG;
	private String cuotasMorosas;

	// nuevos
	private String segDesgravamen;
	private String segCesantia;
	private String tipoBp;
	private String lineaCredito;
	private String moroso;
	
	private String cuotaDesde;
	private String montoCuotasEnTransito;
	private String numCuotasEnTransito;
	private String cuotasInformadas;
	private String montoCuota;
	
	//agregado J-Factory 26-09-2017
	private String cuotaHasta;
    private String totalDiferido;
    private String montoEPO;
    private String totalCuotasInformadas;
    private String gastoCobranza;
    private String saldoCapital;
    private String gravamenes;
    
  //agregado J-Factory 20-07-2018
    private String honorarios;
    
    //agregado J-Factory 04-12-2019
    private String estadoCredito;
    private String saldoCapitalCondonado; 
    
  //agregado J-Factory 30-06-2020
    private String saldoAdeudado;
    private String saldoCapitalCuotasFuturas;
    private String montoFinalAdeudado;
    private String totalAPagar;
    private String tasaInteres;
    
	private List<SalidaDetalleCuotasEarlyPayOff2> detalleCuotas = new ArrayList<SalidaDetalleCuotasEarlyPayOff2>();

	public String getMontoSeguros() {
		return montoSeguros;
	}

	public void setMontoSeguros(String montoSeguros) {
		this.montoSeguros = montoSeguros;
	}

	public String getMontoComPrep() {
		return montoComPrep;
	}

	public void setMontoComPrep(String montoComPrep) {
		this.montoComPrep = montoComPrep;
	}

	public String getMontoIntMoroso() {
		return montoIntMoroso;
	}

	public void setMontoIntMoroso(String montoIntMoroso) {
		this.montoIntMoroso = montoIntMoroso;
	}

	public String getMontoIntDVG() {
		return montoIntDVG;
	}

	public void setMontoIntDVG(String montoIntDVG) {
		this.montoIntDVG = montoIntDVG;
	}

	public String getCuotasMorosas() {
		return cuotasMorosas;
	}

	public void setCuotasMorosas(String cuotasMorosas) {
		this.cuotasMorosas = cuotasMorosas;
	}

	public List<SalidaDetalleCuotasEarlyPayOff2> getDetalleCuotas() {
		return detalleCuotas;
	}

	public void setDetalleCuotas(
			List<SalidaDetalleCuotasEarlyPayOff2> detalleCuotas) {
		this.detalleCuotas = detalleCuotas;
	}

	public String getSegDesgravamen() {
		return segDesgravamen;
	}

	public void setSegDesgravamen(String segDesgravamen) {
		this.segDesgravamen = segDesgravamen;
	}

	public String getSegCesantia() {
		return segCesantia;
	}

	public void setSegCesantia(String segCesantia) {
		this.segCesantia = segCesantia;
	}

	public String getTipoBp() {
		return tipoBp;
	}

	public void setTipoBp(String tipoBp) {
		this.tipoBp = tipoBp;
	}

	public String getLineaCredito() {
		return lineaCredito;
	}

	public void setLineaCredito(String lineaCredito) {
		this.lineaCredito = lineaCredito;
	}

	public String getMoroso() {
		return moroso;
	}

	public void setMoroso(String moroso) {
		this.moroso = moroso;
	}

	public String getCuotaDesde() {
		return cuotaDesde;
	}

	public void setCuotaDesde(String cuotaDesde) {
		this.cuotaDesde = cuotaDesde;
	}

	public String getMontoCuotasEnTransito() {
		return montoCuotasEnTransito;
	}

	public void setMontoCuotasEnTransito(String montoCuotasEnTransito) {
		this.montoCuotasEnTransito = montoCuotasEnTransito;
	}

	public String getNumCuotasEnTransito() {
		return numCuotasEnTransito;
	}

	public void setNumCuotasEnTransito(String numCuotasEnTransito) {
		this.numCuotasEnTransito = numCuotasEnTransito;
	}

	public String getCuotasInformadas() {
		return cuotasInformadas;
	}

	public void setCuotasInformadas(String cuotasInformadas) {
		this.cuotasInformadas = cuotasInformadas;
	}

	public String getMontoCuota() {
		return montoCuota;
	}

	public void setMontoCuota(String montoCuota) {
		this.montoCuota = montoCuota;
	}

	/**
	 * @return the cuotaHasta
	 */
	public String getCuotaHasta() {
		return cuotaHasta;
	}

	/**
	 * @param cuotaHasta the cuotaHasta to set
	 */
	public void setCuotaHasta(String cuotaHasta) {
		this.cuotaHasta = cuotaHasta;
	}

	/**
	 * @return the totalDiferido
	 */
	public String getTotalDiferido() {
		return totalDiferido;
	}

	/**
	 * @param totalDiferido the totalDiferido to set
	 */
	public void setTotalDiferido(String totalDiferido) {
		this.totalDiferido = totalDiferido;
	}

	/**
	 * @return the montoEPO
	 */
	public String getMontoEPO() {
		return montoEPO;
	}

	/**
	 * @param montoEPO the montoEPO to set
	 */
	public void setMontoEPO(String montoEPO) {
		this.montoEPO = montoEPO;
	}

	/**
	 * @return the totalCuotasInformadas
	 */
	public String getTotalCuotasInformadas() {
		return totalCuotasInformadas;
	}

	/**
	 * @param totalCuotasInformadas the totalCuotasInformadas to set
	 */
	public void setTotalCuotasInformadas(String totalCuotasInformadas) {
		this.totalCuotasInformadas = totalCuotasInformadas;
	}

	/**
	 * @return the gastoCobranza
	 */
	public String getGastoCobranza() {
		return gastoCobranza;
	}

	/**
	 * @param gastoCobranza the gastoCobranza to set
	 */
	public void setGastoCobranza(String gastoCobranza) {
		this.gastoCobranza = gastoCobranza;
	}

	/**
	 * @return the saldoCapital
	 */
	public String getSaldoCapital() {
		return saldoCapital;
	}

	/**
	 * @param saldoCapital the saldoCapital to set
	 */
	public void setSaldoCapital(String saldoCapital) {
		this.saldoCapital = saldoCapital;
	}

	/**
	 * @return the gravamenes
	 */
	public String getGravamenes() {
		return gravamenes;
	}

	/**
	 * @param gravamenes the gravamenes to set
	 */
	public void setGravamenes(String gravamenes) {
		this.gravamenes = gravamenes;
	}

	/**
	 * @return the honorarios
	 */
	public String getHonorarios() {
		return honorarios;
	}


	public void setHonorarios(String honorarios) {
		this.honorarios = honorarios;
	}

	/**
	 * @return the estadoCredito
	 */
	public String getEstadoCredito() {
		return estadoCredito;
	}

	/**
	 * @param estadoCredito the estadoCredito to set
	 */
	public void setEstadoCredito(String estadoCredito) {
		this.estadoCredito = estadoCredito;
	}

	/**
	 * @return the saldoCapitalCondonado
	 */
	public String getSaldoCapitalCondonado() {
		return saldoCapitalCondonado;
	}

	/**
	 * @param saldoCapitalCondonado the saldoCapitalCondonado to set
	 */
	public void setSaldoCapitalCondonado(String saldoCapitalCondonado) {
		this.saldoCapitalCondonado = saldoCapitalCondonado;
	}

	/**
	 * @return the saldoAdeudado
	 */
	public String getSaldoAdeudado() {
		return saldoAdeudado;
	}

	/**
	 * @param saldoAdeudado the saldoAdeudado to set
	 */
	public void setSaldoAdeudado(String saldoAdeudado) {
		this.saldoAdeudado = saldoAdeudado;
	}

	/**
	 * @return the saldoCapitalCuotasFuturas
	 */
	public String getSaldoCapitalCuotasFuturas() {
		return saldoCapitalCuotasFuturas;
	}

	/**
	 * @param saldoCapitalCuotasFuturas the saldoCapitalCuotasFuturas to set
	 */
	public void setSaldoCapitalCuotasFuturas(String saldoCapitalCuotasFuturas) {
		this.saldoCapitalCuotasFuturas = saldoCapitalCuotasFuturas;
	}

	/**
	 * @return the montoFinalAdeudado
	 */
	public String getMontoFinalAdeudado() {
		return montoFinalAdeudado;
	}

	/**
	 * @param montoFinalAdeudado the montoFinalAdeudado to set
	 */
	public void setMontoFinalAdeudado(String montoFinalAdeudado) {
		this.montoFinalAdeudado = montoFinalAdeudado;
	}

	/**
	 * @return the totalAPagar
	 */
	public String getTotalAPagar() {
		return totalAPagar;
	}

	/**
	 * @param totalAPagar the totalAPagar to set
	 */
	public void setTotalAPagar(String totalAPagar) {
		this.totalAPagar = totalAPagar;
	}

	/**
	 * @return the tasaInteres
	 */
	public String getTasaInteres() {
		return tasaInteres;
	}

	/**
	 * @param tasaInteres the tasaInteres to set
	 */
	public void setTasaInteres(String tasaInteres) {
		this.tasaInteres = tasaInteres;
	}
	
}
