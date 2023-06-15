package cl.laaraucana.compromisototal.VO;

public class Otorgamiento {

	private String folio;
	private String fecha;

	public Otorgamiento(String folio, String fecha) {
		this.folio = folio;
		this.fecha = fecha;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
