package cl.araucana.estasfam.app.business.model;

public class RegionDTO {
	
	private Integer codRegion;
	private String descRegion;
	
	public RegionDTO(){}

	public RegionDTO(Integer codRegion, String descRegion) {
		super();
		this.codRegion = codRegion;
		this.descRegion = descRegion;
	}

	public Integer getCodRegion() {
		return codRegion;
	}

	public void setCodRegion(Integer codRegion) {
		this.codRegion = codRegion;
	}

	public String getDescRegion() {
		return descRegion;
	}

	public void setDescRegion(String descRegion) {
		this.descRegion = descRegion;
	}
}
