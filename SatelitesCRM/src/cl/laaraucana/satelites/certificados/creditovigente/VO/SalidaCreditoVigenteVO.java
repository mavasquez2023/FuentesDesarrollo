package cl.laaraucana.satelites.certificados.creditovigente.VO;

import cl.laaraucana.satelites.Utils.Utils;

@SuppressWarnings("unused")
public class SalidaCreditoVigenteVO {
	private String folio;
	private double montoSolicitado;
	private String fechaOtorgamiento;
	private double montoCuota;
	private String plazo;
	private String tipoMoneda;
	private String cuotasMorosas;
	private String gastosCobranza;
	private String gastosCobranzaConTipo;
	private String rolAsociado;

	/**
	 * Atributo para diferenciar si el credito viene desde as400 0 banking.
	 * 0: As400
	 * 1: Banking
	 */
	private String flagTipoCredito;
	
	//nuevos
	private String cantidadCuotas;
	private double montoAdaudado;
	private String tipoAfiliado;
	private String tipoCredito;
	private String tipoProducto;	
	private String montoSolConTipo;
	private String montoCuoConTipo;

	
	public SalidaCreditoVigenteVO(){}
	
	public SalidaCreditoVigenteVO(String folio,
			double montoSolicitado, String fechaOtorgamiento
			, double montoCuota, String plazo) {
		super();
		this.folio = folio;
		this.montoSolicitado = montoSolicitado;
		this.fechaOtorgamiento = fechaOtorgamiento;
		this.montoCuota = montoCuota;
		this.plazo = plazo;
	}

	
	public String getFlagTipoCredito() {
		return flagTipoCredito;
	}

	public void setFlagTipoCredito(String flagTipoCredito) {
		this.flagTipoCredito = flagTipoCredito;
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

	public String getCantidadCuotas() {
		return cantidadCuotas;
	}

	public void setCantidadCuotas(String cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}


	public double getMontoAdaudado() {
		return montoAdaudado;
	}

	public void setMontoAdaudado(double montoAdaudado) {
		this.montoAdaudado = montoAdaudado;
	}

	public String getTipoAfiliado() {
		return tipoAfiliado;
	}

	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}

	public String getTipoCredito() {
		return tipoCredito;
	}

	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getTipoMoneda() {
		return tipoMoneda;
	}

	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}
	
	public String getMontoSolConTipo() {
		if (this.tipoMoneda != null && this.tipoMoneda.equals("UF"))
			return this.montoSolConTipo = "UF " + Utils.formateaDobleConDecimal(this.montoSolicitado);
		else
			return this.montoSolConTipo = "$ " + Utils.formateaDobleSinDecimal(this.montoSolicitado);
	}

	public String getMontoCuoConTipo() {
		if (this.tipoMoneda != null && this.tipoMoneda.equals("UF"))
			return this.montoCuoConTipo = "UF " + Utils.formateaDobleConDecimal(this.montoCuota);
		else
			return this.montoCuoConTipo = "$ " + Utils.formateaDobleSinDecimal(this.montoCuota);
	}

	public String getGastosCobranzaConTipo() {
		if (this.gastosCobranza != null)
			return this.gastosCobranzaConTipo = "$ " + Utils.formateaDobleSinDecimal(Double.parseDouble(this.gastosCobranza));
		else
			return "";
	}
  
	public String getCuotasMorosas() {
		if (this.cuotasMorosas != null)
			return this.cuotasMorosas = Utils.formateaDobleSinDecimal(Double.parseDouble(this.cuotasMorosas));
		else
			return "";
	}

	public void setCuotasMorosas(String cuotasMorosas) {
		this.cuotasMorosas = cuotasMorosas;
	}

	public String getGastosCobranza() {
		return gastosCobranza;
	}

	public void setGastosCobranza(String gastosCobranza) {
		this.gastosCobranza = gastosCobranza;
	}
	
	public String getRolAsociado() {
		return rolAsociado;
	}

	public void setRolAsociado(String rolAsociado) {
		this.rolAsociado = rolAsociado;
	}
}
