package cl.araucana.clientewsfonasa.business.to;

import java.util.Date;

public class RequestWSFonasaTO {
	
	private Date fechaHora;
	private String rutBeneficiario;
	private Date fecCertifica;
	private String rutInstitucion;
	private String codigoUsuario;
	private String claveUsuario;
	private short codigoAsegurador;
	
	public RequestWSFonasaTO(){}

	public RequestWSFonasaTO(String rutBeneficiario){
		this.rutBeneficiario = rutBeneficiario;
	}
	
	public RequestWSFonasaTO(Date fechaHora, String rutBeneficiario, Date fecCertifica, String rutInstitucion, String codigoUsuario, String claveUsuario) {
		super();
		this.fechaHora = fechaHora;
		this.rutBeneficiario = rutBeneficiario;
		this.fecCertifica = fecCertifica;
		this.rutInstitucion = rutInstitucion;
		this.codigoUsuario = codigoUsuario;
		this.claveUsuario = claveUsuario;
	}

	public String getClaveUsuario() {
		return claveUsuario;
	}
	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public Date getFecCertifica() {
		return fecCertifica;
	}
	public void setFecCertifica(Date fecCertifica) {
		this.fecCertifica = fecCertifica;
	}
	public String getRutBeneficiario() {
		return rutBeneficiario;
	}
	public void setRutBeneficiario(String rutBeneficiario) {
		this.rutBeneficiario = rutBeneficiario;
	}
	public String getRutInstitucion() {
		return rutInstitucion;
	}
	public void setRutInstitucion(String rutInstitucion) {
		this.rutInstitucion = rutInstitucion;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * @return the codigoAsegurador
	 */
	public short getCodigoAsegurador() {
		return codigoAsegurador;
	}

	/**
	 * @param codigoAsegurador the codigoAsegurador to set
	 */
	public void setCodigoAsegurador(short codigoAsegurador) {
		this.codigoAsegurador = codigoAsegurador;
	}
	
}
