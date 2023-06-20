package cl.laaraucana.cuentabancaria.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "cuentaDescripcion",
		propOrder = {"idmandato", "nomafi", "nombreBanco", "numcuenta", "tipoCuenta", "telefono", "celular", "email", "tipoproddesc", "estadodesc"  })
@XmlRootElement(name="CUENTA")
public class CuentaDescripcionVO implements Serializable{
	
	@XmlElement(name="ID_MANDATO", required=true)
	private long idmandato;
	@XmlElement(name="NOMBRE_AFILIADO", required=true)
	private String nomafi;
	@XmlElement(name="NOMBRE_BANCO", required=true)
	private String nombreBanco;
	@XmlElement(name="NUM_CUENTA", required=true)
	private String numcuenta;
	@XmlElement(name="DESCRIPCION_TIPO_CUENTA", required=true)
	private String tipoCuenta;
	@XmlElement(name="TELEFONO", required=true)
	private String telefono;
	@XmlElement(name="CELULAR", required=true)
	private String celular;
	@XmlElement(name="EMAIL", required=true)
	private String email;
	@XmlElement(name="PRODUCTO", required=true)
	private String tipoproddesc;
	@XmlElement(name="ESTADO", required=true)
	private String estadodesc;
	
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
