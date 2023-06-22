package cl.laaraucana.simulacion.ibatis.vo;

public class PreAprobadoVO {
	
	private long rutCliente;
	private char dv;
	private int tipoCliente;
	private String montoPreAprobado;
	private String fechaValidez;
	
	public long getRutCliente() {
		return rutCliente;
	}
	public void setRutCliente(long rutCliente) {
		this.rutCliente = rutCliente;
	}
	public char getDv() {
		return dv;
	}
	public void setDv(char dv) {
		this.dv = dv;
	}
	public int getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(int tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public String getMontoPreAprobado() {
		return montoPreAprobado;
	}
	public void setMontoPreAprobado(String montoPreAprobado) {
		this.montoPreAprobado = montoPreAprobado;
	}
	public String getFechaValidez() {
		return fechaValidez;
	}
	public void setFechaValidez(String fechaValidez) {
		this.fechaValidez = fechaValidez;
	}
}
