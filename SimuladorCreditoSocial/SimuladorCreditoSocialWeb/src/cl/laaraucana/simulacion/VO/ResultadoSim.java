package cl.laaraucana.simulacion.VO;

import java.util.Date;

import cl.laaraucana.simulacion.webservices.model.AbstractSalidaVO;

public class ResultadoSim extends AbstractSalidaVO {


	private String montoSolicitado;
	private String tipoProductoStr;
	private String tipoProducto;
	private String cuotas;
	private String oficina;
	private String oficinaDesc;
	private String tipoAfiliado;
	private String rut;
	private String nombreAfiliado;
	private Date fechaPrimerVencimiento;
	private String segCesantia = "";
	private String tasaMensual;

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombreAfiliado() {
		return nombreAfiliado;
	}

	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}

	public String getMontoSolicitado() {
		return montoSolicitado;
	}

	public void setMontoSolicitado(String montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}

	public String getTipoProductoStr() {
		return tipoProductoStr;
	}

	public void setTipoProductoStr(String tipoProductoStr) {
		this.tipoProductoStr = tipoProductoStr;
	}

	public String getCuotas() {
		return cuotas;
	}

	public void setCuotas(String cuotas) {
		this.cuotas = cuotas;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public Date getFechaPrimerVencimiento() {
		return fechaPrimerVencimiento;
	}

	public void setFechaPrimerVencimiento(Date fechaPrimerVencimiento) {
		this.fechaPrimerVencimiento = fechaPrimerVencimiento;
	}

	public String getOficinaDesc() {
		return oficinaDesc;
	}

	public void setOficinaDesc(String oficinaDesc) {
		this.oficinaDesc = oficinaDesc;
	}

	public String getTipoAfiliado() {
		return tipoAfiliado;
	}

	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}

	public String getOficina() {
		return oficina;
	}

	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	public String getSegCesantia() {
		return segCesantia;
	}

	public void setSegCesantia(String segCesantia) {
		this.segCesantia = segCesantia;
	}

	public String getTasaMensual() {
		return tasaMensual;
	}

	public void setTasaMensual(String tasaMensual) {
		this.tasaMensual = tasaMensual;
	}

	
}
