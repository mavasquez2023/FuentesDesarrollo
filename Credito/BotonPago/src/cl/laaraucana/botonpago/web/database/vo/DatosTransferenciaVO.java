package cl.laaraucana.botonpago.web.database.vo;

public class DatosTransferenciaVO {

	private String banco;
	private String numCtaCte;
	private String fechaMovimiento;
	private String monto;
	private String tipoAbono;
	private String numeroDeposito;
	private String codigoOperacionInterna;
	private String folioTesoreria;
	private String rutCliente;
	private String dvCliente;
	private String estadoCodigo;
	private boolean flag;

	public DatosTransferenciaVO() {
		this.banco = "";
		this.numCtaCte = "";
		this.fechaMovimiento = "";
		this.monto = "";
		this.tipoAbono = "";
		this.numeroDeposito = "";
		this.codigoOperacionInterna = "";
		this.folioTesoreria = "";
		this.rutCliente = "";
		this.dvCliente = "";
		this.estadoCodigo = "";
		this.flag = false;
		//false por defecto, solo si es ejecutada correctamente pasa a true.
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getNumCtaCte() {
		return numCtaCte;
	}

	public void setNumCtaCte(String numCtaCte) {
		this.numCtaCte = numCtaCte;
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

	public String getNumeroDeposito() {
		return numeroDeposito;
	}

	public void setNumeroDeposito(String numeroDeposito) {
		this.numeroDeposito = numeroDeposito;
	}

	public String getCodigoOperacionInterna() {
		return codigoOperacionInterna;
	}

	public void setCodigoOperacionInterna(String codigoOperacionInterna) {
		this.codigoOperacionInterna = codigoOperacionInterna;
	}

	public String getFolioTesoreria() {
		return folioTesoreria;
	}

	public void setFolioTesoreria(String folioTesoreria) {
		this.folioTesoreria = folioTesoreria;
	}

	public String getRutCliente() {
		return rutCliente;
	}

	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}

	public String getDvCliente() {
		return dvCliente;
	}

	public void setDvCliente(String dvCliente) {
		this.dvCliente = dvCliente;
	}

	public String getEstadoCodigo() {
		return estadoCodigo;
	}

	public void setEstadoCodigo(String estadoCodigo) {
		this.estadoCodigo = estadoCodigo;
	}

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}