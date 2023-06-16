package cl.araucana.estasfam.app.business.model;

public class CargasPagosDirectoDTO {
	
	private Integer codActividad;
	private Integer codRegion;
	private Integer cantidad;
	
	public CargasPagosDirectoDTO(){}

	public CargasPagosDirectoDTO(Integer codActividad, Integer codRegion,
			Integer cantidad) {
		super();
		this.codActividad = codActividad;
		this.codRegion = codRegion;
		this.cantidad = cantidad;
	}

	public Integer getCodActividad() {
		return codActividad;
	}

	public void setCodActividad(Integer codActividad) {
		this.codActividad = codActividad;
	}

	public Integer getCodRegion() {
		return codRegion;
	}

	public void setCodRegion(Integer codRegion) {
		this.codRegion = codRegion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}
