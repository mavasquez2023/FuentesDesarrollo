package cl.araucana.wsatento.business.to;

import java.util.Date;

public class LicenciaTO {
	private String codSucPago;
	private Date fechaPago;
	private Integer compin;
	private Integer tipo;
	private Integer dias;
	private Date fechaDesde;
	
	public LicenciaTO(){}

	public LicenciaTO(String codSucPago, Date fechaPago, Integer compin, Integer tipo, Integer dias, Date fechaDesde) {
		super();
		this.codSucPago = codSucPago;
		this.fechaPago = fechaPago;
		this.compin = compin;
		this.tipo = tipo;
		this.dias = dias;
		this.fechaDesde = fechaDesde;
	}

	public String getCodSucPago() {
		return codSucPago;
	}

	public void setCodSucPago(String codSucPago) {
		this.codSucPago = codSucPago;
	}

	public Integer getCompin() {
		return compin;
	}

	public void setCompin(Integer compin) {
		this.compin = compin;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	
}
