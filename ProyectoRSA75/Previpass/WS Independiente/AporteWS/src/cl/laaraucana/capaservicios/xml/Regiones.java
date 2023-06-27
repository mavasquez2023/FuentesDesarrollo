package cl.laaraucana.capaservicios.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class Regiones {
	List<Region> region = new ArrayList<Region>();

	public List<Region> getRegion() {
		return region;
	}

	public void setRegion(List<Region> region) {
		this.region = region;
	}
}
