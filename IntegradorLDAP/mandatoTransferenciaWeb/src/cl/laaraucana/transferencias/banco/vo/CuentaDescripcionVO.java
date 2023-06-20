package cl.laaraucana.transferencias.banco.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "cuentaDescripcion",
		propOrder = {"idmandato", "nomafi", "codigoBanco", "numcuenta", "tipoCuenta", "telefono", "celular", "email"  })
@XmlRootElement(name="MANDATO")
public class CuentaDescripcionVO implements Serializable{
	
	@XmlElement(name="ID_MANDATO", required=true)
	private long idmandato;
	@XmlElement(name="NOMBRE_AFILIADO", required=true)
	private String nomafi;
	@XmlElement(name="CODIGO_BANCO", required=true)
	private int codigoBanco;
	@XmlElement(name="NUM_CUENTA", required=true)
	private String numcuenta;
	@XmlElement(name="TIPO_CUENTA", required=true)
	private int tipoCuenta;
	@XmlElement(name="TELEFONO", required=true)
	private String telefono;
	@XmlElement(name="CELULAR", required=true)
	private String celular;
	@XmlElement(name="EMAIL", required=true)
	private String email;

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

	public String getNomafi() {
		return nomafi;
	}

	public void setNomafi(String nomafi) {
		this.nomafi = nomafi;
	}

	/**
	 * @return the codigoBanco
	 */
	public int getCodigoBanco() {
		return codigoBanco;
	}

	/**
	 * @param codigoBanco the codigoBanco to set
	 */
	public void setCodigoBanco(int codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public String getNumcuenta() {
		return numcuenta;
	}

	public void setNumcuenta(String numcuenta) {
		this.numcuenta = numcuenta;
	}

	public int getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(int tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
