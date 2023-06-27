package cl.laaraucana.capaservicios.database.vo;

public class CuentaCorrienteVO {
	private long rutAfi;
	private String dvAfi;
	private String nroCuenta;
	private String tipoCuenta;
	private String codBanco;
	
	public CuentaCorrienteVO(){
	}
	public CuentaCorrienteVO(long rut, String dvAfi){
		setRutAfi(rut);
		setDvAfi(dvAfi);
	}
	
	/**
	 * Constructor
	 * @param rut
	 * @param dvAfi
	 * @param nroCuenta
	 * @param tipoCuenta
	 * @param codBanco
	 */
	public CuentaCorrienteVO(long rut, String dvAfi, String nroCuenta, String tipoCuenta, String codBanco){
		setRutAfi(rut);
		setDvAfi(dvAfi);
		setNroCuenta(nroCuenta);
		setTipoCuenta(tipoCuenta);
		setCodBanco(codBanco);
	}

	public String getDvAfi() {
		return dvAfi;
	}
	public void setDvAfi(String dvAfi) {
		this.dvAfi = dvAfi;
	}
	public String getNroCuenta() {
		return nroCuenta;
	}
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public String getCodBanco() {
		return codBanco;
	}
	public void setCodBanco(String codBanco) {
		this.codBanco = codBanco;
	}
	public long getRutAfi() {
		return rutAfi;
	}
	public void setRutAfi(long rutAfi) {
		this.rutAfi = rutAfi;
	}
}
