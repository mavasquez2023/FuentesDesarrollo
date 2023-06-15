package cl.laaraucana.satelites.certificados.creditocancelado.VO;

import cl.laaraucana.satelites.Utils.Utils;
@SuppressWarnings("unused")
public class SalidaCreditoCanceladoVO {
	private String folio;
	private double montoSolicitado;
	private String fechaOtorgamiento;
	private String fechaCancelacion;
	private double montoCuota;
	private String plazo;
	private String tipoMoneda;
	//private String montoConTipo;	
	private String montoSolConTipo;
	private String montoCuoConTipo;


	/**
	 * Atributo para diferenciar si el credito viene desde as400 0 banking.
	 * 0: As400
	 * 1: Banking
	 */
	private String flagTipoCredito;
	
	public SalidaCreditoCanceladoVO(){}
	
	public SalidaCreditoCanceladoVO(String folio, double montoSolicitado,
			String fechaOtorgamiento, String fechaCancelacion,
			double montoCuota, String plazo) {
		super();
		this.folio = folio;
		this.montoSolicitado = montoSolicitado;
		this.fechaOtorgamiento = fechaOtorgamiento;
		this.fechaCancelacion = fechaCancelacion;
		this.montoCuota = montoCuota;
		this.plazo = plazo;
	}
	
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public double getMontoSolicitado() {
		return montoSolicitado;
	}
	public void setMontoSolicitado(double montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}
	public String getFechaOtorgamiento() {
		return fechaOtorgamiento;
	}
	public void setFechaOtorgamiento(String fechaOtorgamiento) {
		this.fechaOtorgamiento = fechaOtorgamiento;
	}
	public String getFechaCancelacion() {
		return fechaCancelacion;
	}
	public void setFechaCancelacion(String fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}
	public double getMontoCuota() {
		return montoCuota;
	}
	public void setMontoCuota(double montoCuota) {
		this.montoCuota = montoCuota;
	}
	public String getPlazo() {
		return plazo;
	}
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	public String getFlagTipoCredito() {
		return flagTipoCredito;
	}

	public void setFlagTipoCredito(String flagTipoCredito) {
		this.flagTipoCredito = flagTipoCredito;
	}
	
	public String getTipoMoneda() {
		return tipoMoneda;
	}

	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}
	
	public String getMontoSolConTipo() {
        if(this.tipoMoneda != null && this.tipoMoneda.equals("UF"))
        	return this.montoSolConTipo="UF "+ Utils.formateaDobleConDecimal(this.montoSolicitado);
        else
        	return  this.montoSolConTipo="$ "+ Utils.formateaDobleSinDecimal(this.montoSolicitado);
  }

  public String getMontoCuoConTipo() {
        if(this.tipoMoneda != null && this.tipoMoneda.equals("UF"))
        	return this.montoCuoConTipo="UF "+ Utils.formateaDobleConDecimal(this.montoCuota);
        else
             return this.montoCuoConTipo="$ "+ Utils.formateaDobleSinDecimal(this.montoCuota);
  }
	
}
