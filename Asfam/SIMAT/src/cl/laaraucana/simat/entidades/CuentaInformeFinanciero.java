package cl.laaraucana.simat.entidades;

public class CuentaInformeFinanciero {
	private String nombreCuenta;
	private long resultadoBalance = 0;
	private long resultadoPlanos = 0;
	private String descripcion;
	
	public String getNombreCuenta() {
		return nombreCuenta;
	}
	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}
	public long getResultadoBalance() {
		return resultadoBalance;
	}
	public void setResultadoBalance(Long resultadoBalance) {
		this.resultadoBalance = resultadoBalance;
	}
	public long getResultadoPlanos() {
		return resultadoPlanos;
	}
	public void setResultadoPlanos(Long resultadoPlanos) {
		this.resultadoPlanos = resultadoPlanos;
	}
	public long getDiferencia() {
		return resultadoBalance - resultadoPlanos;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String toString(){
		return "Cta: " + nombreCuenta + ", res balance: "+ resultadoBalance + ", res planos: " + resultadoPlanos + ", desc: " + descripcion;
	}
}
