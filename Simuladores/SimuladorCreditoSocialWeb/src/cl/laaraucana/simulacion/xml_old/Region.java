package cl.laaraucana.simulacion.xml_old;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class Region {

	private String idRegion;
	private String nombreRegion;

	public String getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(String idRegion) {
		this.idRegion = idRegion;
	}

	public String getNombreRegion() {
		return nombreRegion;
	}

	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
	}

}
