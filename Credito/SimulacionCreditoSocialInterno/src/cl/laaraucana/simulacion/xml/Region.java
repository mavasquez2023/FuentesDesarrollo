package cl.laaraucana.simulacion.xml;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class Region {
	
	private String idRegion;
	private String nombreRegion;
	private List<Comuna> comuna;

	public List<Comuna> getComuna() {
		return comuna;
	}

	public void setComuna(List<Comuna> comuna) {
		this.comuna = comuna;
	}

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
