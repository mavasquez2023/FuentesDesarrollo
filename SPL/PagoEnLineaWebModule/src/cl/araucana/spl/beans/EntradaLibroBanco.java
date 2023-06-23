package cl.araucana.spl.beans;

public class EntradaLibroBanco {
	private String banco;
	private String nroCuentaCorriente;
	private String fechaMovimiento;
	private String monto;
	private String tipoAbono;
	private String nroDeposito;
	private String codOperacionInterna;
	private String descripcion;
	
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getNroCuentaCorriente() {
		return nroCuentaCorriente;
	}
	public void setNroCuentaCorriente(String nroCuentaCorriente) {
		this.nroCuentaCorriente = nroCuentaCorriente;
	}
	public String getFechaMovimiento() {
		return fechaMovimiento;
	}
	public void setFechaMovimiento(String fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getTipoAbono() {
		return tipoAbono;
	}
	public void setTipoAbono(String tipoAbono) {
		this.tipoAbono = tipoAbono;
	}
	public String getNroDeposito() {
		return nroDeposito;
	}
	public void setNroDeposito(String nroDeposito) {
		this.nroDeposito = nroDeposito;
	}
	public String getCodOperacionInterna() {
		return codOperacionInterna;
	}
	public void setCodOperacionInterna(String codOperacionInterna) {
		this.codOperacionInterna = codOperacionInterna;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

