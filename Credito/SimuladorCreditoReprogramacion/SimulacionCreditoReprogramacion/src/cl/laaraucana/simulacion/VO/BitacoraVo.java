package cl.laaraucana.simulacion.VO;

import java.util.Date;



public class BitacoraVo {
	
	//params Entrada
	private String rutEjecutivo;
	private String dvEjecutivo;
	private String rutAfiliado;
	private String dvAfiliado;
	private String contrato;
	private String tipoAfiliado;
	private String producto;
	private String plazo;
	private String mesesGracia;
	private String montoAbono;
	private String renta;
	private String conSeguroCesantia;
	private String conSeguroDesgravamen;
	
	//params Salida
	private String montoAdeudado;
	private String montoCuota;
	private String cae;
	private String tasaMensual;
	private String tasaAnual;
	private String costoTotal;
	private String montoGravamenesCondonado;
	private String montoGastosCobCondonado;
	private String montoInteresesCondonado;
	private Date fechaPrimerVencimiento;
	private String montoSeguroCesantia;
	private String montoSeguroDesgravamen;
	private String porcentajeMaximoEndeudamiento;
	private String porcentajeEndeudamientoSimulado;
	private Date fechaSimulacion;
	
	//paramsSalida Acuerdo
	private String capitalAdeudado;
	private String cuotasxPagar;
	private String porcentajeCondonacionCapital;
	private String capitalComprometido;
	private String capitalCondonado;
	
	
	/**
	 * @return the rutEjecutivo
	 */
	public String getRutEjecutivo() {
		return rutEjecutivo;
	}
	/**
	 * @param rutEjecutivo the rutEjecutivo to set
	 */
	public void setRutEjecutivo(String rutEjecutivo) {
		this.rutEjecutivo = rutEjecutivo;
	}
	/**
	 * @return the dvEjecutivo
	 */
	public String getDvEjecutivo() {
		return dvEjecutivo;
	}
	/**
	 * @param dvEjecutivo the dvEjecutivo to set
	 */
	public void setDvEjecutivo(String dvEjecutivo) {
		this.dvEjecutivo = dvEjecutivo;
	}
	/**
	 * @return the rutAfiliado
	 */
	public String getRutAfiliado() {
		return rutAfiliado;
	}
	/**
	 * @param rutAfiliado the rutAfiliado to set
	 */
	public void setRutAfiliado(String rutAfiliado) {
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
	 * @return the contrato
	 */
	public String getContrato() {
		return contrato;
	}
	/**
	 * @param contrato the contrato to set
	 */
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	/**
	 * @return the tipoAfiliado
	 */
	public String getTipoAfiliado() {
		return tipoAfiliado;
	}
	/**
	 * @param tipoAfiliado the tipoAfiliado to set
	 */
	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}
	/**
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}
	/**
	 * @param productothe producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
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
	 * @return the mesesGracia
	 */
	public String getMesesGracia() {
		return mesesGracia;
	}
	/**
	 * @param mesesGracia the mesesGracia to set
	 */
	public void setMesesGracia(String mesesGracia) {
		this.mesesGracia = mesesGracia;
	}
	/**
	 * @return the montoAbono
	 */
	public String getMontoAbono() {
		return montoAbono;
	}
	/**
	 * @param montoAbono the montoAbono to set
	 */
	public void setMontoAbono(String montoAbono) {
		this.montoAbono = montoAbono;
	}
	/**
	 * @return the renta
	 */
	public String getRenta() {
		return renta;
	}
	/**
	 * @param renta the renta to set
	 */
	public void setRenta(String renta) {
		this.renta = renta;
	}
	/**
	 * @return the conSeguroCesantia
	 */
	public String getConSeguroCesantia() {
		return conSeguroCesantia;
	}
	/**
	 * @param conSeguroCesantia the conSeguroCesantia to set
	 */
	public void setConSeguroCesantia(String conSeguroCesantia) {
		this.conSeguroCesantia = conSeguroCesantia;
	}
	/**
	 * @return the conSeguroDesgravamen
	 */
	public String getConSeguroDesgravamen() {
		return conSeguroDesgravamen;
	}
	/**
	 * @param conSeguroDesgravamen the conSeguroDesgravamen to set
	 */
	public void setConSeguroDesgravamen(String conSeguroDesgravamen) {
		this.conSeguroDesgravamen = conSeguroDesgravamen;
	}
	/**
	 * @return the montoAdeudado
	 */
	public String getMontoAdeudado() {
		return montoAdeudado;
	}
	/**
	 * @param montoAdeudado the montoAdeudado to set
	 */
	public void setMontoAdeudado(String montoAdeudado) {
		this.montoAdeudado = montoAdeudado;
	}
	/**
	 * @return the montoCuota
	 */
	public String getMontoCuota() {
		return montoCuota;
	}
	/**
	 * @param montoCuota the montoCuota to set
	 */
	public void setMontoCuota(String montoCuota) {
		this.montoCuota = montoCuota;
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
	 * @return the tasaMensual
	 */
	public String getTasaMensual() {
		return tasaMensual;
	}
	/**
	 * @param tasaMensual the tasaMensual to set
	 */
	public void setTasaMensual(String tasaMensual) {
		this.tasaMensual = tasaMensual;
	}
	/**
	 * @return the tasaAnual
	 */
	public String getTasaAnual() {
		return tasaAnual;
	}
	/**
	 * @param tasaAnual the tasaAnual to set
	 */
	public void setTasaAnual(String tasaAnual) {
		this.tasaAnual = tasaAnual;
	}
	/**
	 * @return the costoTotal
	 */
	public String getCostoTotal() {
		return costoTotal;
	}
	/**
	 * @param costoTotal the costoTotal to set
	 */
	public void setCostoTotal(String costoTotal) {
		this.costoTotal = costoTotal;
	}
	/**
	 * @return the montoGravamenesCondonado
	 */
	public String getMontoGravamenesCondonado() {
		return montoGravamenesCondonado;
	}
	/**
	 * @param montoGravamenesCondonado the montoGravamenesCondonado to set
	 */
	public void setMontoGravamenesCondonado(String montoGravamenesCondonado) {
		this.montoGravamenesCondonado = montoGravamenesCondonado;
	}
	/**
	 * @return the montoGastosCobCondonado
	 */
	public String getMontoGastosCobCondonado() {
		return montoGastosCobCondonado;
	}
	/**
	 * @param montoGastosCobCondonado the montoGastosCobCondonado to set
	 */
	public void setMontoGastosCobCondonado(String montoGastosCobCondonado) {
		this.montoGastosCobCondonado = montoGastosCobCondonado;
	}
	/**
	 * @return the montoInteresesCondonado
	 */
	public String getMontoInteresesCondonado() {
		return montoInteresesCondonado;
	}
	/**
	 * @param montoInteresesCondonado the montoInteresesCondonado to set
	 */
	public void setMontoInteresesCondonado(String montoInteresesCondonado) {
		this.montoInteresesCondonado = montoInteresesCondonado;
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
	/**
	 * @return the montoSeguroCesantia
	 */
	public String getMontoSeguroCesantia() {
		return montoSeguroCesantia;
	}
	/**
	 * @param montoSeguroCesantia the montoSeguroCesantia to set
	 */
	public void setMontoSeguroCesantia(String montoSeguroCesantia) {
		this.montoSeguroCesantia = montoSeguroCesantia;
	}
	/**
	 * @return the montoSeguroDesgravamen
	 */
	public String getMontoSeguroDesgravamen() {
		return montoSeguroDesgravamen;
	}
	/**
	 * @param montoSeguroDesgravamen the montoSeguroDesgravamen to set
	 */
	public void setMontoSeguroDesgravamen(String montoSeguroDesgravamen) {
		this.montoSeguroDesgravamen = montoSeguroDesgravamen;
	}
	/**
	 * @return the porcentajeMaximoEndeudamiento
	 */
	public String getPorcentajeMaximoEndeudamiento() {
		return porcentajeMaximoEndeudamiento;
	}
	/**
	 * @param porcentajeMaximoEndeudamiento the porcentajeMaximoEndeudamiento to set
	 */
	public void setPorcentajeMaximoEndeudamiento(
			String porcentajeMaximoEndeudamiento) {
		this.porcentajeMaximoEndeudamiento = porcentajeMaximoEndeudamiento;
	}
	/**
	 * @return the porcentajeEndeudamientoSimulado
	 */
	public String getPorcentajeEndeudamientoSimulado() {
		return porcentajeEndeudamientoSimulado;
	}
	/**
	 * @param porcentajeEndeudamientoSimulado the porcentajeEndeudamientoSimulado to set
	 */
	public void setPorcentajeEndeudamientoSimulado(
			String porcentajeEndeudamientoSimulado) {
		this.porcentajeEndeudamientoSimulado = porcentajeEndeudamientoSimulado;
	}
	
	/**
	 * @return the fechaSimulacion
	 */
	public Date getFechaSimulacion() {
		return fechaSimulacion;
	}
	/**
	 * @param fechaSimulacion the fechaSimulacion to set
	 */
	public void setFechaSimulacion(Date fechaSimulacion) {
		this.fechaSimulacion = fechaSimulacion;
	}
	/**
	 * @return the capitalAdeudado
	 */
	public String getCapitalAdeudado() {
		return capitalAdeudado;
	}
	/**
	 * @param capitalAdeudado the capitalAdeudado to set
	 */
	public void setCapitalAdeudado(String capitalAdeudado) {
		this.capitalAdeudado = capitalAdeudado;
	}
	/**
	 * @return the cuotasxPagar
	 */
	public String getCuotasxPagar() {
		return cuotasxPagar;
	}
	/**
	 * @param cuotasxPagar the cuotasxPagar to set
	 */
	public void setCuotasxPagar(String cuotasxPagar) {
		this.cuotasxPagar = cuotasxPagar;
	}
	/**
	 * @return the porcentajeCondonacionCapital
	 */
	public String getPorcentajeCondonacionCapital() {
		return porcentajeCondonacionCapital;
	}
	/**
	 * @param porcentajeCondonacionCapital the porcentajeCondonacionCapital to set
	 */
	public void setPorcentajeCondonacionCapital(String porcentajeCondonacionCapital) {
		this.porcentajeCondonacionCapital = porcentajeCondonacionCapital;
	}
	/**
	 * @return the capitalComprometido
	 */
	public String getCapitalComprometido() {
		return capitalComprometido;
	}
	/**
	 * @param capitalComprometido the capitalComprometido to set
	 */
	public void setCapitalComprometido(String capitalComprometido) {
		this.capitalComprometido = capitalComprometido;
	}
	/**
	 * @return the capitalCondonado
	 */
	public String getCapitalCondonado() {
		return capitalCondonado;
	}
	/**
	 * @param capitalCondonado the capitalCondonado to set
	 */
	public void setCapitalCondonado(String capitalCondonado) {
		this.capitalCondonado = capitalCondonado;
	}

	
	
	}
	
	

