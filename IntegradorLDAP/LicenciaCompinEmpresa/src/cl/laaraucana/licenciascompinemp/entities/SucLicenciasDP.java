package cl.laaraucana.licenciascompinemp.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sucLicenciasDP")
public class SucLicenciasDP implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "CODSUCURSAL")
	private String codigoSucursal;
	@Column(name = "NOMSUCURSAL")
	private String nombreSucursal;
	@Column(name = "EMAIL")
	private String emailSucursal;
	/**
	 * @return the codigoSucursal
	 */
	public String getCodigoSucursal() {
		return codigoSucursal;
	}
	/**
	 * @param codigoSucursal the codigoSucursal to set
	 */
	public void setCodigoSucursal(String codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}
	/**
	 * @return the nombreSucursal
	 */
	public String getNombreSucursal() {
		return nombreSucursal;
	}
	/**
	 * @param nombreSucursal the nombreSucursal to set
	 */
	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}
	/**
	 * @return the emailSucursal
	 */
	public String getEmailSucursal() {
		return emailSucursal;
	}
	/**
	 * @param emailSucursal the emailSucursal to set
	 */
	public void setEmailSucursal(String emailSucursal) {
		this.emailSucursal = emailSucursal;
	}
	
	

}
