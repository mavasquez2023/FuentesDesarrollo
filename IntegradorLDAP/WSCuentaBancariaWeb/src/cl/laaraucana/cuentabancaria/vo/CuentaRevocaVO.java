package cl.laaraucana.cuentabancaria.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "cuentaEdit",
		propOrder = {"rutafi", "dvafi", "codbanco", "numcuenta", "tipoprod"})
public class CuentaRevocaVO implements Serializable{

	@XmlElement(name="RUT", required=true)
	private int rutafi;
	@XmlElement(name="DV", required=true)
	private String dvafi;
	@XmlElement(name="COD_BANCO", required=true)
	private int codbanco;
	@XmlElement(name="NUM_CUENTA", required=true)
	private String numcuenta;
	@XmlElement(name="TIPO_PRODUCTO", required=true)
	private String tipoprod;
	/**
	 * @return the rutafi
	 */
	public int getRutafi() {
		return rutafi;
	}
	/**
	 * @param rutafi the rutafi to set
	 */
	public void setRutafi(int rutafi) {
		this.rutafi = rutafi;
	}
	/**
	 * @return the dvafi
	 */
	public String getDvafi() {
		return dvafi;
	}
	/**
	 * @param dvafi the dvafi to set
	 */
	public void setDvafi(String dvafi) {
		this.dvafi = dvafi;
	}
	/**
	 * @return the codbanco
	 */
	public int getCodbanco() {
		return codbanco;
	}
	/**
	 * @param codbanco the codbanco to set
	 */
	public void setCodbanco(int codbanco) {
		this.codbanco = codbanco;
	}
	/**
	 * @return the numcuenta
	 */
	public String getNumcuenta() {
		return numcuenta;
	}
	/**
	 * @param numcuenta the numcuenta to set
	 */
	public void setNumcuenta(String numcuenta) {
		this.numcuenta = numcuenta;
	}
	/**
	 * @return the tipoprod
	 */
	public String getTipoprod() {
		return tipoprod;
	}
	/**
	 * @param tipoprod the tipoprod to set
	 */
	public void setTipoprod(String tipoprod) {
		this.tipoprod = tipoprod;
	}
	
}
