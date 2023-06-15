package cl.laaraucana.botonpago.web.vo;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlElement;

@XmlRootElement
public class RolesXML {

	ArrayList<RolXML> rol = new ArrayList<RolXML>();

	public ArrayList<RolXML> getRol() {
		return rol;
	}

	@XmlElement
	public void setRol(ArrayList<RolXML> rol) {
		this.rol = rol;
	}

}
