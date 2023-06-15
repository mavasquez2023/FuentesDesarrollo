package cl.laaraucana.simat.entidades;

public class CuentaPaseContableVO {
	
	public static String TIPO_IMPORTE_DEBITO ="DEBITO";
	public static String TIPO_IMPORTE_CREDITO = "CREDITO";
	
	private String periodo;
	private String nroCuenta;
	private long monto;
	private String tipoImporte;
	private String descConcepto;
	private String claveContabilidad;
	private String cuentaVigente;
	
	public String getNroCuenta() {
		return nroCuenta;
	}
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public String getDescConcepto() {
		return descConcepto;
	}
	public void setDescConcepto(String descConcepto) {
		this.descConcepto = descConcepto;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getTipoImporte() {
		return tipoImporte;
	}
	public void setTipoImporte(String tipoImporte) {
		this.tipoImporte = tipoImporte;
	}
	public String getClaveContabilidad() {
		return claveContabilidad;
	}
	public void setClaveContabilidad(String claveContabilidad) {
		this.claveContabilidad = claveContabilidad;
	}
	public String getCuentaVigente() {
		return cuentaVigente;
	}
	public void setCuentaVigente(String cuentaVigente) {
		this.cuentaVigente = cuentaVigente;
	}
	public long getMonto() {
		return monto;
	}
	public void setMonto(long monto) {
		this.monto = monto;
	}
}	
