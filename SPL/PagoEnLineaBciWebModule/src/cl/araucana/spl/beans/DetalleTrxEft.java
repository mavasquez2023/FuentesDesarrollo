package cl.araucana.spl.beans;

import java.math.BigDecimal;

public abstract class DetalleTrxEft {
	private BigDecimal id;
	private TransaccionEft trxEft;
	private BigDecimal servRecaudacion;
	private BigDecimal monto;
	private String cantidad;
	private String precio;
	private String datosAdicionales;
	
	protected abstract String getNombreObjeto();
	
	public String toString() {
		return new StringBuffer("[" + getNombreObjeto() +"::id=").append(id)
			.append(",trxEft=").append(trxEft)
			.append(",servRecaudacion=").append(servRecaudacion)
			.append(",monto=").append(monto)
			.append(",cantidad=").append(cantidad)
			.append(",precio=").append(precio)
			.append(",datosAdicionales=").append(datosAdicionales)			
			.append("]").toString();
	}
	
	/**
	 * @return the cantidad
	 */
	public String getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
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
	 * @return the monto
	 */
	public BigDecimal getMonto() {
		return monto;
	}
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	/**
	 * @return the precio
	 */
	public String getPrecio() {
		return precio;
	}
	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	/**
	 * @return the servRecaudacion
	 */
	public BigDecimal getServRecaudacion() {
		return servRecaudacion;
	}
	/**
	 * @param servRecaudacion the servRecaudacion to set
	 */
	public void setServRecaudacion(BigDecimal servRecaudacion) {
		this.servRecaudacion = servRecaudacion;
	}
	/**
	 * @return the trxEft
	 */
	public TransaccionEft getTrxBChile() {
		return trxEft;
	}
	/**
	 * @param trxEft the trxEft to set
	 */
	public void setTrxEft(TransaccionEft trxEft) {
		this.trxEft = trxEft;
	}
	/**
	 * @return the datosAdicionales
	 */
	public String getDatosAdicionales() {
		return datosAdicionales;
	}
	/**
	 * @param datosAdicionales the datosAdicionales to set
	 */
	public void setDatosAdicionales(String datosAdicionales) {
		this.datosAdicionales = datosAdicionales;
	}
	
	public BigDecimal getIdTrx() {
		return trxEft.getId();
	}	
	
}
