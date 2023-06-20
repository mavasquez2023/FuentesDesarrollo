package cl.laaraucana.licenciasivr.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "credencialesWS",
		propOrder = { "usuario", "password" })
public class CredencialesWS implements Serializable{
	
	@XmlElement(name="USUARIO", required=true)
	private String usuario;
	@XmlElement(name="CLAVE", required=true)
	private String password;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
