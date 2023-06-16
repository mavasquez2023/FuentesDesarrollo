package cl.laaraucana.simat.entidades;

import java.util.List;

public class ResultadoPaseContable {
	private String mensaje;
	private String codigo;
	private List<CuentaPaseContableVO> cuentasPaseContable;
	private long totalDebito;
	private long totalCredito;
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public List<CuentaPaseContableVO> getCuentasPaseContable() {
		return cuentasPaseContable;
	}
	public void setCuentasPaseContable(List<CuentaPaseContableVO> cuentasPaseContable) {
		this.cuentasPaseContable = cuentasPaseContable;
	}
	public long getTotalDebito() {
		return totalDebito;
	}
	public void setTotalDebito(long totalDebito) {
		this.totalDebito = totalDebito;
	}
	public long getTotalCredito() {
		return totalCredito;
	}
	public void setTotalCredito(long totalCredito) {
		this.totalCredito = totalCredito;
	}
}
