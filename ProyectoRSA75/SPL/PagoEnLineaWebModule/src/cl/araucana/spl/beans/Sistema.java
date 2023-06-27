package cl.araucana.spl.beans;

import java.math.BigDecimal;

public class Sistema {
	private BigDecimal id;
	private String codigo;
	private String descripcion;
	private String clave;

	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
		return new StringBuffer("[SISTEMA::id=").append(id).
		append(",codigo=").append(codigo)
		.append(",descripcion=").append(descripcion)
		.append(",len(clave)=").append(clave != null ? clave.length() : 0)
		.append("]").toString();
	}
}
