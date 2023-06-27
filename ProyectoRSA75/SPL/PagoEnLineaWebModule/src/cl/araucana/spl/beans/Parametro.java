package cl.araucana.spl.beans;

import java.math.BigDecimal;

public class Parametro {
	private BigDecimal id;
	private String nombre;
	private String tipoParametro;
	private String tipoValor;
	private String descripcion;
	private String valor;
	
	public String toString() {
		return new StringBuffer("[Parametro::id=").append(id)
			.append(",nombre=").append(nombre)
			.append(",tipoParametro=").append(tipoParametro)
			.append(",tipoValor=").append(tipoValor)
			.append(",descripcion=").append(descripcion)
			.append(",valor=").append(valor)
			.append("]").toString();
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoParametro() {
		return tipoParametro;
	}

	public void setTipoParametro(String tipoParametro) {
		this.tipoParametro = tipoParametro;
	}

	public String getTipoValor() {
		return tipoValor;
	}

	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	

}
