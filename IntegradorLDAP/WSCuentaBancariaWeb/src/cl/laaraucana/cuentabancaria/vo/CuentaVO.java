package cl.laaraucana.cuentabancaria.vo;

import java.io.Serializable;

public class CuentaVO implements Serializable{
	private long idmandato;
	private int rutafi;
	private String dvafi;
	private String nomafi;
	private int codbanco;
	private String numcuenta;
	private int codtipocuenta;
	private String telefono;
	private String celular;
	private String email;
	private int tipoprod;
	private int codcanal;
	
	public int getRutafi() {
		return rutafi;
	}

	public void setRutafi(int rutafi) {
		this.rutafi = rutafi;
	}

	public String getDvafi() {
		return dvafi;
	}

	public void setDvafi(String dvafi) {
		this.dvafi = dvafi;
	}

	public String getNomafi() {
		return nomafi;
	}

	public void setNomafi(String nomafi) {
		this.nomafi = nomafi;
	}

	public int getCodbanco() {
		return codbanco;
	}

	public void setCodbanco(int codbanco) {
		this.codbanco = codbanco;
	}

	public String getNumcuenta() {
		return numcuenta;
	}

	public void setNumcuenta(String numcuenta) {
		this.numcuenta = numcuenta;
	}

	public int getCodtipocuenta() {
		return codtipocuenta;
	}

	public void setCodtipocuenta(int codtipocuenta) {
		this.codtipocuenta = codtipocuenta;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTipoprod() {
		return tipoprod;
	}

	public void setTipoprod(int tipoprod) {
		this.tipoprod = tipoprod;
	}

	/**
	 * @return the codcanal
	 */
	public int getCodcanal() {
		return codcanal;
	}

	/**
	 * @param codcanal the codcanal to set
	 */
	public void setCodcanal(int codcanal) {
		this.codcanal = codcanal;
	}

	/**
	 * @return the idmandato
	 */
	public long getIdmandato() {
		return idmandato;
	}

	/**
	 * @param idmandato the idmandato to set
	 */
	public void setIdmandato(long idmandato) {
		this.idmandato = idmandato;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * @param celular the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	

}
