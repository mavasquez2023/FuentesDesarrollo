package cl.laaraucana.cuentabancaria.vo;

import cl.laaraucana.cuentabancaria.out.SalidaVo;

public class CuentaDescripcionVo {

	
	private String nomafi;
	private String nombreBanco;
	private String numcuenta;
	private String tipoCuenta;
	private String email;
	private String tipoproddesc;
	private String estadodesc;
    private SalidaVo salida;
	
	public String getNomafi() {
		return nomafi;
	}

	public void setNomafi(String nomafi) {
		this.nomafi = nomafi;
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public String getNumcuenta() {
		return numcuenta;
	}

	public void setNumcuenta(String numcuenta) {
		this.numcuenta = numcuenta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipoproddesc() {
		return tipoproddesc;
	}

	public void setTipoproddesc(String tipoproddesc) {
		this.tipoproddesc = tipoproddesc;
	}

	public String getEstadodesc() {
		return estadodesc;
	}

	public void setEstadodesc(String estadodesc) {
		this.estadodesc = estadodesc;
	}

	public SalidaVo getSalida() {
		return salida;
	}

	public void setSalida(SalidaVo salida) {
		this.salida = salida;
	}

	 
	

}
