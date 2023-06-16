package cl.araucana.estasfam.app.business.model;

public class ActividadEconomicaDTO {
	
	private Integer codActividad;
	private String descActividad;
	
	public ActividadEconomicaDTO(){}

	public ActividadEconomicaDTO(Integer codActividad, String descActividad) {
		super();
		this.codActividad = codActividad;
		this.descActividad = descActividad;
	}

	public Integer getCodActividad() {
		return codActividad;
	}

	public void setCodActividad(Integer codActividad) {
		this.codActividad = codActividad;
	}

	public String getDescActividad() {
		return descActividad;
	}

	public void setDescActividad(String descActividad) {
		this.descActividad = descActividad;
	}
}
