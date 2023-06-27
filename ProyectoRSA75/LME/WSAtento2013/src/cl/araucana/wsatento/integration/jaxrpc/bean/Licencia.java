package cl.araucana.wsatento.integration.jaxrpc.bean;

import java.util.Date;

public class Licencia {
	private String codigoSucursalPago;
	private Date fechaPago;
	private Integer compin;
	private Integer tipo;
	private Integer dias;
	private Date fechaDesde;
	
	public Licencia(){}

	public Licencia(String codigoSucursalPago, Date fechaPago, Integer compin, Integer tipo, Integer dias, Date fechaDesde) {
		this.codigoSucursalPago = codigoSucursalPago;
		this.fechaPago = fechaPago;
		this.compin = compin;
		this.tipo = tipo;
		this.dias = dias;
		this.fechaDesde = fechaDesde;
	}

	public String getCodigoSucursalPago() {
		return codigoSucursalPago;
	}

	public void setCodigoSucursalPago(String codigoSucursalPago) {
		this.codigoSucursalPago = codigoSucursalPago;
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

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	
}
