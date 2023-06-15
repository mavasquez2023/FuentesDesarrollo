package cl.laaraucana.simulacion.xml_old;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class Comuna {

	private String idComuna;
	private String nombreComuna;
	private Ciudad ciudad;

	public String getIdComuna() {
		return idComuna;
	}

	public void setIdComuna(String idComuna) {
		this.idComuna = idComuna;
	}

	public String getNombreComuna() {
		return nombreComuna;
	}

	public void setNombreComuna(String nombreComuna) {
		this.nombreComuna = nombreComuna;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

}
