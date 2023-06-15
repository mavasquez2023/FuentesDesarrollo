package cl.laaraucana.satelites.certificados.prepago.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.webservices.client.EarlyPayOffSimulation2.VO.SalidaDetalleCuotasEarlyPayOff2;
import cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.VO.CondonacionCashFlowVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaCreditoPrepagoVO extends AbstractSalidaVO {

	private String folio;
	private String desdeCuota;
	private String hastaCuota;
	private String fechaOtorgamiento;
	private double montoOtorgado;
	private double montoCuota;
	// private String tasaImpuestoLTE;
	private double montoImpuestoLTE;
	private String folioForm24;
	private double saldoCapital;
	private double gravamenes;
	// private double gastosDeCobranza;
	private String gastosDeCobranza;
	// private double comisionPrepago;
	private String tipoCredito;
	private double total;
	// private double totalSumaFinal;
	private String nroCuotaActual;
	private String sumaCuotas = "";

	// nuevos
	private double montoSeguros = 0;
	private double montoComisionPrepagos = 0;
	private double montoInteresMoroso = 0;
	private double montoInteresDevengado = 0;
	private double cantidadCuotasMorosas = 0;

	// nuevos 2
	private double primaSegDesgravamen;
	private double primaSegCesantia;
	private String tipoBp;
	private String lineaCredito;
	private String moroso;

	private double montoCuotasEntransito;
	private String numCuotasEnTransito;
	
	private List<SalidaDetalleCuotasEarlyPayOff2> listaFechaPagar = new ArrayList<SalidaDetalleCuotasEarlyPayOff2>();
	
	private double montoEPO;
	private String cuotasInformadas;
	private double totalCuotasInformadas;
	private double gastoCobranza;
	private double totalDiferido;
	
	//agregado J-Factory 20-07-2018
	private double honorarios;
	
	//agregado J-Factory 04-12-2019
	//Desde EarlyPay
	private String estado;
	private double saldoCapitalCondonado;
	//Desde CashFlow
	private String plazo;
	private String cuotasPagadas;
	private String cuotasMorosas;
	private String cuotasFuturas;
	//agregado J-Factory 11-12-2019
	private double saldoAdeudado;
	private double saldoCapitalFuturo;
	//Desde CompContHeader
	private String rol;
	private String pagador;
	//Desde CompContHeader
	private String castigo;
	
	private double montoFinalAdeudado;
	private CondonacionCashFlowVO condonacion;
	
	//agregado J-Factory 30-06-2020
	private double tasaInteres;
	
	//agregado J-Factory 17-01-2021
	private boolean isLiquidacion;
	
	public SalidaCreditoPrepagoVO() {
	}

	/*public SalidaCreditoPrepagoVO(String folio, String desdeCuota,
			String hastaCuota, String fechaOtorgamiento, double montoCuota,
			double montoImpuestoLTE, String folioForm24, double saldoCapital,
			double gravamenes, double gastosDeCobranza,
			String estadoCredito, String tipoCredito, double totalMonto) {
		super();
		this.folio = folio;
		this.desdeCuota = desdeCuota;
		this.hastaCuota = hastaCuota;
		this.fechaOtorgamiento = fechaOtorgamiento;
		// this.montoOtorgado = montoOtorgado;
		// this.plazo = plazo;
		this.montoCuota = montoCuota;
		// this.tasaImpuestoLTE = tasaImpuestoLTE;
		this.montoImpuestoLTE = montoImpuestoLTE;
		this.folioForm24 = folioForm24;
		this.saldoCapital = saldoCapital;
		this.gravamenes = gravamenes;
		// this.gastosDeCobranza = gastosDeCobranza;
		// this.comisionPrepago = comisionPrepago;
		this.estadoCredito = estadoCredito;
		this.tipoCredito = tipoCredito;
		this.total = totalMonto;
	}*/

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getFechaOtorgamiento() {
		return fechaOtorgamiento;
	}

	public void setFechaOtorgamiento(String fechaOtorgamiento) {
		this.fechaOtorgamiento = fechaOtorgamiento;
	}

	// public double getMontoOtorgado() {
	// return montoOtorgado;
	// }
	//
	// public void setMontoOtorgado(double montoOtorgado) {
	// this.montoOtorgado = montoOtorgado;
	// }
	//
	// public String getPlazo() {
	// return plazo;
	// }
	//
	// public void setPlazo(String plazo) {
	// this.plazo = plazo;
	// }

	// public String getTasaImpuestoLTE() {
	// return tasaImpuestoLTE;
	// }
	//
	// public void setTasaImpuestoLTE(String tasaImpuestoLTE) {
	// this.tasaImpuestoLTE = tasaImpuestoLTE;
	// }

	public String getFolioForm24() {
		return folioForm24;
	}

	public void setFolioForm24(String folioForm24) {
		this.folioForm24 = folioForm24;
	}

	public String getTipoCredito() {
		return tipoCredito;
	}

	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}

	public String getDesdeCuota() {
		return desdeCuota;
	}

	public void setDesdeCuota(String desdeCuota) {
		this.desdeCuota = desdeCuota;
	}

	public String getHastaCuota() {
		return hastaCuota;
	}

	public void setHastaCuota(String hastaCuota) {
		this.hastaCuota = hastaCuota;
	}

	public double getMontoCuota() {
		return montoCuota;
	}

	public void setMontoCuota(double montoCuota) {
		this.montoCuota = montoCuota;
	}

	public double getMontoImpuestoLTE() {
		return montoImpuestoLTE;
	}

	public void setMontoImpuestoLTE(double montoImpuestoLTE) {
		this.montoImpuestoLTE = montoImpuestoLTE;
	}

	public double getSaldoCapital() {
		return saldoCapital;
	}

	public void setSaldoCapital(double saldoCapital) {
		this.saldoCapital = saldoCapital;
	}

	public double getGravamenes() {
		return gravamenes;
	}

	public void setGravamenes(double gravamenes) {
		this.gravamenes = gravamenes;
	}

	// public double getGastosDeCobranza() {
	// return gastosDeCobranza;
	// }
	//
	// public void setGastosDeCobranza(double gastosDeCobranza) {
	// this.gastosDeCobranza = gastosDeCobranza;
	// }

	// public double getComisionPrepago() {
	// return comisionPrepago;
	// }
	//
	// public void setComisionPrepago(double comisionPrepago) {
	// this.comisionPrepago = comisionPrepago;
	// }

	public double getTotal() {
		return total;
	}

	public String getGastosDeCobranza() {
		return gastosDeCobranza;
	}

	public void setGastosDeCobranza(String gastosDeCobranza) {
		this.gastosDeCobranza = gastosDeCobranza;
	}

	public String getSumaCuotas() {
		return sumaCuotas;
	}

	public void setSumaCuotas(String sumaCuotas) {
		this.sumaCuotas = sumaCuotas;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getNroCuotaActual() {
		return nroCuotaActual;
	}

	public void setNroCuotaActual(String nroCuotaActual) {
		this.nroCuotaActual = nroCuotaActual;
	}

	public double getMontoSeguros() {
		return montoSeguros;
	}

	public void setMontoSeguros(double montoSeguros) {
		this.montoSeguros = montoSeguros;
	}

	public double getMontoComisionPrepagos() {
		return montoComisionPrepagos;
	}

	public void setMontoComisionPrepagos(double montoComisionPrepagos) {
		this.montoComisionPrepagos = montoComisionPrepagos;
	}

	public double getMontoInteresMoroso() {
		return montoInteresMoroso;
	}

	public void setMontoInteresMoroso(double montoInteresMoroso) {
		this.montoInteresMoroso = montoInteresMoroso;
	}

	public double getMontoInteresDevengado() {
		return montoInteresDevengado;
	}

	public void setMontoInteresDevengado(double montoInteresDevengado) {
		this.montoInteresDevengado = montoInteresDevengado;
	}

	public double getCantidadCuotasMorosas() {
		return cantidadCuotasMorosas;
	}

	public void setCantidadCuotasMorosas(double cantidadCuotasMorosas) {
		this.cantidadCuotasMorosas = cantidadCuotasMorosas;
	}

	public List<SalidaDetalleCuotasEarlyPayOff2> getListaFechaPagar() {
		return listaFechaPagar;
	}

	public void setListaFechaPagar(List<SalidaDetalleCuotasEarlyPayOff2> listaFechaPagar) {
		this.listaFechaPagar = listaFechaPagar;
	}

	public double getPrimaSegDesgravamen() {
		return primaSegDesgravamen;
	}

	public void setPrimaSegDesgravamen(double primaSegDesgravamen) {
		this.primaSegDesgravamen = primaSegDesgravamen;
	}

	public double getPrimaSegCesantia() {
		return primaSegCesantia;
	}

	public void setPrimaSegCesantia(double primaSegCesantia) {
		this.primaSegCesantia = primaSegCesantia;
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

	public String getCuotasInformadas() {
		return cuotasInformadas;
	}

	public void setCuotasInformadas(String cuotasInformadas) {
		this.cuotasInformadas = cuotasInformadas;
	}

	public String getMoroso() {
		return moroso;
	}

	public void setMoroso(String moroso) {
		this.moroso = moroso;
	}

	public double getMontoOtorgado() {
		return montoOtorgado;
	}

	public void setMontoOtorgado(double montoOtorgado) {
		this.montoOtorgado = montoOtorgado;
	}

	public double getMontoCuotasEntransito() {
		return montoCuotasEntransito;
	}

	public void setMontoCuotasEntransito(double montoCuotasEntransito) {
		this.montoCuotasEntransito = montoCuotasEntransito;
	}

	public String getNumCuotasEnTransito() {
		return numCuotasEnTransito;
	}

	public void setNumCuotasEnTransito(String numCuotasEnTransito) {
		this.numCuotasEnTransito = numCuotasEnTransito;
	}

	/**
	 * @return the montoEPO
	 */
	public double getMontoEPO() {
		return montoEPO;
	}

	/**
	 * @param montoEPO the montoEPO to set
	 */
	public void setMontoEPO(double montoEPO) {
		this.montoEPO = montoEPO;
	}

	/**
	 * @return the totalCuotasInformadas
	 */
	public double getTotalCuotasInformadas() {
		return totalCuotasInformadas;
	}

	/**
	 * @param totalCuotasInformadas the totalCuotasInformadas to set
	 */
	public void setTotalCuotasInformadas(double totalCuotasInformadas) {
		this.totalCuotasInformadas = totalCuotasInformadas;
	}

	/**
	 * @return the gastoCobranza
	 */
	public double getGastoCobranza() {
		return gastoCobranza;
	}

	/**
	 * @param gastoCobranza the gastoCobranza to set
	 */
	public void setGastoCobranza(double gastoCobranza) {
		this.gastoCobranza = gastoCobranza;
	}

	/**
	 * @return the totalDiferido
	 */
	public double getTotalDiferido() {
		return totalDiferido;
	}

	/**
	 * @param totalDiferido the totalDiferido to set
	 */
	public void setTotalDiferido(double totalDiferido) {
		this.totalDiferido = totalDiferido;
	}

	/**
	 * @return the honorarios
	 */
	public double getHonorarios() {
		return honorarios;
	}


	public void setHonorarios(double honorarios) {
		this.honorarios = honorarios;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the saldoCapitalCondonado
	 */
	public double getSaldoCapitalCondonado() {
		return saldoCapitalCondonado;
	}

	/**
	 * @param saldoCapitalCondonado the saldoCapitalCondonado to set
	 */
	public void setSaldoCapitalCondonado(double saldoCapitalCondonado) {
		this.saldoCapitalCondonado = saldoCapitalCondonado;
	}

	/**
	 * @return the cuotasPagadas
	 */
	public String getCuotasPagadas() {
		return cuotasPagadas;
	}

	/**
	 * @param cuotasPagadas the cuotasPagadas to set
	 */
	public void setCuotasPagadas(String cuotasPagadas) {
		this.cuotasPagadas = cuotasPagadas;
	}

	/**
	 * @return the cuotasMorosas
	 */
	public String getCuotasMorosas() {
		return cuotasMorosas;
	}

	/**
	 * @param cuotasMorosas the cuotasMorosas to set
	 */
	public void setCuotasMorosas(String cuotasMorosas) {
		this.cuotasMorosas = cuotasMorosas;
	}

	/**
	 * @return the cuotasFuturas
	 */
	public String getCuotasFuturas() {
		return cuotasFuturas;
	}

	/**
	 * @param cuotasFuturas the cuotasFuturas to set
	 */
	public void setCuotasFuturas(String cuotasFuturas) {
		this.cuotasFuturas = cuotasFuturas;
	}

	/**
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/**
	 * @return the pagador
	 */
	public String getPagador() {
		return pagador;
	}

	/**
	 * @param pagador the pagador to set
	 */
	public void setPagador(String pagador) {
		this.pagador = pagador;
	}

	/**
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * @param plazo the plazo to set
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * @return the castigo
	 */
	public String getCastigo() {
		return castigo;
	}

	/**
	 * @param castigo the castigo to set
	 */
	public void setCastigo(String castigo) {
		this.castigo = castigo;
	}

	/**
	 * @return the saldoAdeudado
	 */
	public double getSaldoAdeudado() {
		return saldoAdeudado;
	}

	/**
	 * @param saldoAdeudado the saldoAdeudado to set
	 */
	public void setSaldoAdeudado(double saldoAdeudado) {
		this.saldoAdeudado = saldoAdeudado;
	}

	/**
	 * @return the saldoCapitalFuturo
	 */
	public double getSaldoCapitalFuturo() {
		return saldoCapitalFuturo;
	}

	/**
	 * @param saldoCapitalFuturo the saldoCapitalFuturo to set
	 */
	public void setSaldoCapitalFuturo(double saldoCapitalFuturo) {
		this.saldoCapitalFuturo = saldoCapitalFuturo;
	}

	/**
	 * @return the montoFinalAdeudado
	 */
	public double getMontoFinalAdeudado() {
		return montoFinalAdeudado;
	}

	/**
	 * @param montoFinalAdeudado the montoFinalAdeudado to set
	 */
	public void setMontoFinalAdeudado(double montoFinalAdeudado) {
		this.montoFinalAdeudado = montoFinalAdeudado;
	}

	/**
	 * @return the condonacion
	 */
	public CondonacionCashFlowVO getCondonacion() {
		return condonacion;
	}

	/**
	 * @param condonacion the condonacion to set
	 */
	public void setCondonacion(CondonacionCashFlowVO condonacion) {
		this.condonacion = condonacion;
	}

	/**
	 * @return the tasaInteres
	 */
	public double getTasaInteres() {
		return tasaInteres;
	}

	/**
	 * @param tasaInteres the tasaInteres to set
	 */
	public void setTasaInteres(double tasaInteres) {
		this.tasaInteres = tasaInteres;
	}

	/**
	 * @return the isLiquidacion
	 */
	public boolean isLiquidacion() {
		return isLiquidacion;
	}

	/**
	 * @param isLiquidacion the isLiquidacion to set
	 */
	public void setLiquidacion(boolean isLiquidacion) {
		this.isLiquidacion = isLiquidacion;
	}
	
	
}
