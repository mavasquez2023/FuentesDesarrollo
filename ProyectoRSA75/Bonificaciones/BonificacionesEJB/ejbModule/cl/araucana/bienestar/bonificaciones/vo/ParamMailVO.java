package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class ParamMailVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;
	
	private String hostLocal;
	private String mailFrom;
	private String userMail;
	private String passMail;
	private String mailHostTo;
	private String mailPort;
	private String mailTo;
	private String fecha;
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHostLocal() {
		return hostLocal;
	}
	public void setHostLocal(String hostLocal) {
		this.hostLocal = hostLocal;
	}
	public String getMailFrom() {
		return mailFrom;
	}
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	public String getMailHostTo() {
		return mailHostTo;
	}
	public void setMailHostTo(String mailHostTo) {
		this.mailHostTo = mailHostTo;
	}
	public String getMailPort() {
		return mailPort;
	}
	public void setMailPort(String mailPort) {
		this.mailPort = mailPort;
	}
	public String getMailTo() {
		return mailTo;
	}
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	public String getPassMail() {
		return passMail;
	}
	public void setPassMail(String passMail) {
		this.passMail = passMail;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

}
