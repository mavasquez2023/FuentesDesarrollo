package cl.laaraucana.simat.entidades;

import java.util.List;

public class ResultadoPreBalance {
	List<CuentaInformeFinanciero> resultPreBalance;
	private String mensaje;
	private String codigo;
	
	public List<CuentaInformeFinanciero> getResultPreBalance() {
		return resultPreBalance;
	}
	public void setResultPreBalance(List<CuentaInformeFinanciero> resultPreBalance) {
		this.resultPreBalance = resultPreBalance;
	}
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
}
