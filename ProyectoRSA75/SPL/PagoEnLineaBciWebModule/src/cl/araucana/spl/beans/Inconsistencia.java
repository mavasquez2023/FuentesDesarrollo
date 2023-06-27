package cl.araucana.spl.beans;

import java.math.BigDecimal;
import java.util.Date;

import cl.araucana.spl.base.Constants;
import cl.araucana.spl.util.Nulls;
import cl.araucana.spl.util.Renderer;


public class Inconsistencia {
	private static Renderer renderer = new Renderer();
	private BigDecimal idPago;
	private String pagador;
	private Estado estado;
	private String sistema;
	private String medio;
	private Date fechaTransaccion;
	private Date fechaContable;
	private BigDecimal pagado;
	private BigDecimal monto;
	private BigDecimal montoRendicion;
	private Integer cantidadCambios;
	
	public boolean isEstadoCambiable() {
		return
			Constants.ESTADO_PAGO_CORREGIDO.equals(estado.getId())
			|| Constants.ESTADO_PAGO_INCONSISTENTE.equals(estado.getId());
	}

	public BigDecimal getIdPago() {
		return idPago;
	}
	public void setIdPago(BigDecimal idPago) {
		this.idPago = idPago;
	}
	public String getMedio() {
		return medio;
	}
	public void setMedio(String medio) {
		this.medio = medio;
	}
	public String getMonto() {
		return renderer.formatSimple(monto);
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public String getMontoRendicion() {
		return renderer.formatSimple(montoRendicion);
	}
	public void setMontoRendicion(BigDecimal montoRendicion) {
		this.montoRendicion = montoRendicion;
	}
	public String getPagador() {
		return pagador;
	}
	public void setPagador(String pagador) {
		this.pagador = pagador;
	}
	public String getSistema() {
		return sistema;
	}
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	public Date getFechaContable() {
		if (Nulls.isNotNull(fechaContable)) {
			return fechaContable;
		}
		return null;
	}
	public void setFechaContable(Date fechaContable) {
		this.fechaContable = fechaContable;
	}
	public Date getFechaTransaccion() {
		if (Nulls.isNotNull(fechaTransaccion)) {
			return fechaTransaccion;
		}
		return null;
	}
	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}
	public Integer getCantidadCambios() {
		return cantidadCambios;
	}
	public void setCantidadCambios(Integer cantidadCambios) {
		this.cantidadCambios = cantidadCambios;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public BigDecimal getPagado() {
		return pagado;
	}

	public void setPagado(BigDecimal pagado) {
		this.pagado = pagado;
	}

}
