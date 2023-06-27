package cl.araucana.spl.beans;

import java.math.BigDecimal;

public class Estado {
	private BigDecimal id;
	private String descripcion;
	
	public Estado() {
	}
	public Estado(BigDecimal id) {
		this(id, null);
	}
	public Estado(BigDecimal id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String toString() {
		return new StringBuffer("[ESTADO::id=").append(id)
			.append(",descripcion=").append(descripcion)
			.append("]").toString();
	}
}
