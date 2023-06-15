package cl.laaraucana.compromisototal.webservice.client.queryContractCashFlow.VO;

import java.util.Date;

import cl.laaraucana.compromisototal.utils.Utils;
import cl.laaraucana.compromisototal.webservice.models.AbstractSalidaVO;

public class SalidaCashFlowVO extends AbstractSalidaVO {

	private Date fechaVencCuota;
	private String nroCuota;
	private String estadoCuota;
	private Double montoCapital;
	private Double montoInteres;
	private Double montoServAdic;
	private Double montoSeguros;
	private Double totalCuota;
	private Double montoGravamenes;
	private Double montoAbono;
	private String ultFechaPago;
	private String oficinaPago;
	private String folioPago;
	private String transactionType;
	private String estadoPago;
	private String moneda;
	private Double capitalRestante;
	//new
	private String ultFechaContPago;
	private String viaCotizDescripcion;

	public SalidaCashFlowVO(Date fechaVencCuota, String nroCuota,
			String estadoCuota, Double montoCapital, Double montoInteres,
			Double montoServAdic, Double montoSeguro, Double totalCuota, Double montoGravamenes,
			Double montoAbono, String ultFechaPago, String oficinaPago,
			String folioPago, String transactionType, String estadoPago,
			String moneda, Double capitalRestante, String ultFechaContPago, String viaCotizDescripcion) {
		super();
		this.fechaVencCuota = fechaVencCuota;
		this.nroCuota = nroCuota;
		this.estadoCuota = estadoCuota;
		this.montoCapital = montoCapital;
		this.montoInteres = montoInteres;
		this.montoServAdic = montoServAdic;
		this.montoSeguros = montoSeguro;
		this.totalCuota = totalCuota;
		this.montoGravamenes = montoGravamenes;
		this.montoAbono = montoAbono;
		this.ultFechaPago = ultFechaPago;
		this.oficinaPago = oficinaPago;
		this.folioPago = folioPago;
		this.transactionType = transactionType;
		this.estadoPago = estadoPago;
		this.moneda = moneda;
		this.capitalRestante = capitalRestante;
		this.ultFechaContPago= ultFechaContPago;
		this.viaCotizDescripcion= viaCotizDescripcion;
	}
	

	public String getMontoSeguros() {
		//return montoSeguros;
		if(this.moneda != null && this.moneda.equals("UF"))
        	return "UF "+Utils.formateaDobleConDecimal(this.montoSeguros);
        else
        	return Utils.formateaDobleSinDecimal(this.montoSeguros);
	}


	public void setMontoSeguros(Double montoSeguros) {
		this.montoSeguros = montoSeguros;
	}


	public Date getFechaVencCuota() {
		return fechaVencCuota;
	}

	public void setFechaVencCuota(Date fechaVencCuota) {
		this.fechaVencCuota = fechaVencCuota;
	}

	public String getNroCuota() {
		return nroCuota;
	}

	public void setNroCuota(String nroCuota) {
		this.nroCuota = nroCuota;
	}

	public String getEstadoCuota() {
		return estadoCuota;
	}

	public void setEstadoCuota(String estadoCuota) {
		this.estadoCuota = estadoCuota;
	}

	public String getMontoCapital() {
		
		if(this.moneda != null && this.moneda.equals("UF"))
        	return "UF "+Utils.formateaDobleConDecimal(this.montoCapital);
        else
        	return  Utils.formateaDobleSinDecimal(this.montoCapital);
		//return montoCapital;
	}

	public void setMontoCapital(Double montoCapital) {
		this.montoCapital = montoCapital;
	}

	public String getMontoInteres() {
		if(this.moneda != null && this.moneda.equals("UF"))
        	return "UF "+Utils.formateaDobleConDecimal(this.montoInteres);
        else
        	return  Utils.formateaDobleSinDecimal(this.montoInteres);
		//return montoInteres;
	}

	public void setMontoInteres(Double montoInteres) {
		this.montoInteres = montoInteres;
	}

	public String getMontoServAdic() {
		if(this.moneda != null && this.moneda.equals("UF"))
        	return "UF "+Utils.formateaDobleConDecimal(this.montoServAdic);
        else
        	return  Utils.formateaDobleSinDecimal(this.montoServAdic);
		//return montoServAdic;
	}

	public void setMontoServAdic(Double montoServAdic) {
		this.montoServAdic = montoServAdic;
	}

	public String getTotalCuota() {
		//return totalCuota;
		if(this.moneda != null && this.moneda.equals("UF"))
        	return "UF "+Utils.formateaDobleConDecimal(this.totalCuota);
        else
        	return  Utils.formateaDobleSinDecimal(this.totalCuota);
	}

	public void setTotalCuota(Double totalCuota) {
		this.totalCuota = totalCuota;
	}

	public String getMontoGravamenes() {
		if(this.moneda != null && this.moneda.equals("UF"))
        	return "UF "+Utils.formateaDobleConDecimal(this.montoGravamenes);
        else
        	return  Utils.formateaDobleSinDecimal(this.montoGravamenes);
		//return montoGravamenes;
	}

	public void setMontoGravamenes(Double montoGravamenes) {
		this.montoGravamenes = montoGravamenes;
	}

	public String getMontoAbono() {
		if(this.moneda != null && this.moneda.equals("UF"))
        	return "UF "+Utils.formateaDobleConDecimal(this.montoAbono);
        else
        	return  Utils.formateaDobleSinDecimal(this.montoAbono);
		//return montoAbono;
	}

	public void setMontoAbono(Double montoAbono) {
		this.montoAbono = montoAbono;
	}

	public String getUltFechaPago() {
		return ultFechaPago;
	}

	public void setUltFechaPago(String ultFechaPago) {
		this.ultFechaPago = ultFechaPago;
	}

	public String getOficinaPago() {
		return oficinaPago;
	}

	public void setOficinaPago(String oficinaPago) {
		this.oficinaPago = oficinaPago;
	}

	public String getFolioPago() {
		return folioPago;
	}

	public void setFolioPago(String folioPago) {
		this.folioPago = folioPago;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getEstadoPago() {
		return estadoPago;
	}

	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getCapitalRestante() {
		if(this.moneda != null && this.moneda.equals("UF"))
        	return "UF "+Utils.formateaDobleConDecimal(this.capitalRestante);
        else
        	return  "$ "+ Utils.formateaDobleSinDecimal(this.capitalRestante);
		//return capitalRestante;
	}

	public void setCapitalRestante(Double capitalRestante) {
		this.capitalRestante = capitalRestante;
	}


	/**
	 * @return the ultFechaContPago
	 */
	public String getUltFechaContPago() {
		return ultFechaContPago;
	}


	/**
	 * @param ultFechaContPago the ultFechaContPago to set
	 */
	public void setUltFechaContPago(String ultFechaContPago) {
		this.ultFechaContPago = ultFechaContPago;
	}


	/**
	 * @return the viaCotizDescripcion
	 */
	public String getViaCotizDescripcion() {
		return viaCotizDescripcion;
	}


	/**
	 * @param viaCotizDescripcion the viaCotizDescripcion to set
	 */
	public void setViaCotizDescripcion(String viaCotizDescripcion) {
		this.viaCotizDescripcion = viaCotizDescripcion;
	}
	

}
