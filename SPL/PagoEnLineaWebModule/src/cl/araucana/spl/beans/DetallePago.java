package cl.araucana.spl.beans;

import java.math.BigDecimal;

public class DetallePago {
	private BigDecimal id;
	private Pago pago;
	private BigDecimal folio;
	private String descripcion;
	private BigDecimal monto;

	public String toString() {
		return new StringBuffer("[DETPAGO::id=").append(id)
			.append(",idPago=").append(getIdPago())
			.append(",folio=").append(folio)
			.append(",descripcion=").append(descripcion)
			.append(",monto=").append(monto)
			.append("]").toString();
	}
	
	public BigDecimal getIdPago() {
		if (pago != null)
			return pago.getId();
		return null;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigDecimal getFolio() {
		return folio;
	}
	public void setFolio(BigDecimal folio) {
		this.folio = folio;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public Pago getPago() {
		return pago;
	}
	public void setPago(Pago pago) {
		this.pago = pago;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	
	
}
