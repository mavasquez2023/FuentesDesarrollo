package cl.araucana.spl.beans;

import java.math.BigDecimal;
import java.util.Date;

import cl.araucana.spl.util.Nulls;

public class Bitacora {
	private BigDecimal id;
	private Estado estadoAnterior;
	private Integer pagadoAnterior = Nulls.INTEGER;
	private Pago pago;
	private Date fechaIngreso;
	private String usuario = Nulls.STRING;
	private String observacion = Nulls.STRING;
	
	public String toString() {
		return new StringBuffer("[BITACORA::id=").append(id)
			.append(",pago=").append(pago)
			.append(",estadoAnterior=").append(estadoAnterior)
			.append(",pagadoAnterior=").append(pagadoAnterior)
			.append(",fechaIngreso=").append(fechaIngreso)
			.append(",usuario=").append(usuario)
			.append(",observacion=").append(observacion)
			.append("]").toString();
	}

	/**
	 * @return the estadoAnterior
	 */
	public Estado getEstadoAnterior() {
		return estadoAnterior;
	}

	/**
	 * @param estadoAnterior the estadoAnterior to set
	 */
	public void setEstadoAnterior(Estado estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}

	/**
	 * @return the fechaIngreso
	 */
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param fechaIngreso the fechaIngreso to set
	 */
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * @return the id
	 */
	public BigDecimal getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(BigDecimal id) {
		this.id = id;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the pago
	 */
	public Pago getPago() {
		return pago;
	}

	/**
	 * @param pago the pago to set
	 */
	public void setPago(Pago pago) {
		this.pago = pago;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getPagadoAnterior() {
		return pagadoAnterior;
	}

	public void setPagadoAnterior(Integer pagadoAnterior) {
		this.pagadoAnterior = pagadoAnterior;
	}
	
}
