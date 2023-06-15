package cl.laaraucana.simulacion.xml_old;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class Ciudad {

	private String idCiudad;
	private String nombreCiudad;
	private Region region;

	public String getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(String idCiudad) {
		this.idCiudad = idCiudad;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}
