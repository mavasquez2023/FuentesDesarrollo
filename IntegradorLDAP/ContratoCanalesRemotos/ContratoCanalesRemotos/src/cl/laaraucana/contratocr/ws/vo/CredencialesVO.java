package cl.laaraucana.contratocr.ws.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "AUTENTICACION",
		propOrder = { "usuario", "password" })
public class CredencialesVO implements Serializable{
	
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
