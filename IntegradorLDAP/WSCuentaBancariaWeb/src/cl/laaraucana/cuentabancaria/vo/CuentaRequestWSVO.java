package cl.laaraucana.cuentabancaria.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

 
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "cuentaBanco",
		propOrder = {"idmandato", "rutafi", "dvafi", "nomafi", "celular", "telefono", "email", "codbanco", "numcuenta", "codtipocuenta", "tipoprod"  })
public class CuentaRequestWSVO implements Serializable{
	@XmlElement(name="ID_MANDATO", required=true, nillable=false)
	private long idmandato;
	@XmlElement(name="RUT", required=true, nillable=false)
	private int rutafi;
	@XmlElement(name="DV", required=true, nillable=false)
	private String dvafi;
	@XmlElement(name="NOMBRE_AFILIADO", required=true, nillable=false)
	private String nomafi;
	@XmlElement(name="COD_BANCO", required=true, nillable=false)
	private int codbanco;
	@XmlElement(name="NUM_CUENTA", required=true, nillable=false)
	private String numcuenta;
	@XmlElement(name="COD_TIPO_CUENTA", required=true, nillable=false)
	private int codtipocuenta;
	@XmlElement(name="TELEFONO", required=true, nillable=false)
	private String telefono;
	@XmlElement(name="CELULAR", required=true, nillable=false)
	private String celular;
	@XmlElement(name="EMAIL", required=true, nillable=false)
	private String email;
	@XmlElement(name="TIPO_PRODUCTO", required=true, nillable=false)
	private int tipoprod;
	
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
