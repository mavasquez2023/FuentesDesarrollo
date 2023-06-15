package cl.laaraucana.simulacion.ibatis.vo;

import java.util.Date;

public class CreditoSolicitudVO {

	private String nombre;
	private int rutAfiliado;
	private String dvAfiliado;
	private String celular;
	private String telefono;
	private String email;
	private String tipoAfiliado;
	private long montoSimulado;
	private int numeroCuotasSimuladas;
	private String sucursal;
	private Date fecha;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getRutAfiliado() {
		return rutAfiliado;
	}

	public void setRutAfiliado(int rutAfiliado) {
		this.rutAfiliado = rutAfiliado;
	}

	public String getDvAfiliado() {
		return dvAfiliado;
	}

	public void setDvAfiliado(String dvAfiliado) {
		this.dvAfiliado = dvAfiliado;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipoAfiliado() {
		return tipoAfiliado;
	}

	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}

	public long getMontoSimulado() {
		return montoSimulado;
	}

	public void setMontoSimulado(long montoSimulado) {
		this.montoSimulado = montoSimulado;
	}

	public int getNumeroCuotasSimuladas() {
		return numeroCuotasSimuladas;
	}

	public void setNumeroCuotasSimuladas(int numeroCuotasSimuladas) {
		this.numeroCuotasSimuladas = numeroCuotasSimuladas;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
