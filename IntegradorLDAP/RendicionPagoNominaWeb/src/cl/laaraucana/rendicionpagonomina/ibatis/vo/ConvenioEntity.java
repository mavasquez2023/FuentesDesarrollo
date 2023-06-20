package cl.laaraucana.rendicionpagonomina.ibatis.vo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


public class ConvenioEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoConvenio;

	private String descripcionConvenio;

	private String codigoBanco;

	private String mailAdmin;

	private String plantilla;
	
	private String plantillaEnvio;

	private String convenioBanco;
	
	private int codigoBancoSuper;
	
	private String pathECOutput;
	
	private String emailEjecutivo;

	/**
	 * @return the codigoConvenio
	 */
	public String getCodigoConvenio() {
		return codigoConvenio;
	}

	/**
	 * @param codigoConvenio the codigoConvenio to set
	 */
	public void setCodigoConvenio(String codigoConvenio) {
		this.codigoConvenio = codigoConvenio;
	}

	/**
	 * @return the descripcionConvenio
	 */
	public String getDescripcionConvenio() {
		return descripcionConvenio;
	}

	/**
	 * @param descripcionConvenio the descripcionConvenio to set
	 */
	public void setDescripcionConvenio(String descripcionConvenio) {
		this.descripcionConvenio = descripcionConvenio;
	}

	/**
	 * @return the codigoBanco
	 */
	public String getCodigoBanco() {
		return codigoBanco;
	}

	/**
	 * @param codigoBanco the codigoBanco to set
	 */
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	/**
	 * @return the mailAdmin
	 */
	public String getMailAdmin() {
		return mailAdmin;
	}

	/**
	 * @param mailAdmin the mailAdmin to set
	 */
	public void setMailAdmin(String mailAdmin) {
		this.mailAdmin = mailAdmin;
	}

	/**
	 * @return the plantilla
	 */
	public String getPlantilla() {
		return plantilla;
	}

	/**
	 * @param plantilla the plantilla to set
	 */
	public void setPlantilla(String plantilla) {
		this.plantilla = plantilla;
	}

	
	/**
	 * @return the plantillaEnvio
	 */
	public String getPlantillaEnvio() {
		return plantillaEnvio;
	}

	/**
	 * @param plantillaEnvio the plantillaEnvio to set
	 */
	public void setPlantillaEnvio(String plantillaEnvio) {
		this.plantillaEnvio = plantillaEnvio;
	}

	/**
	 * @return the convenioBanco
	 */
	public String getConvenioBanco() {
		return convenioBanco;
	}

	/**
	 * @param convenioBanco the convenioBanco to set
	 */
	public void setConvenioBanco(String convenioBanco) {
		this.convenioBanco = convenioBanco;
	}

	/**
	 * @return the codigoBancoSuper
	 */
	public int getCodigoBancoSuper() {
		return codigoBancoSuper;
	}

	/**
	 * @param codigoBancoSuper the codigoBancoSuper to set
	 */
	public void setCodigoBancoSuper(int codigoBancoSuper) {
		this.codigoBancoSuper = codigoBancoSuper;
	}

	public String getPathECOutput() {
		return pathECOutput;
	}

	public void setPathECOutput(String pathECOutput) {
		this.pathECOutput = pathECOutput;
	}

	public String getEmailEjecutivo() {
		return emailEjecutivo;
	}

	public void setEmailEjecutivo(String emailEjecutivo) {
		this.emailEjecutivo = emailEjecutivo;
	}
	
	
}
