package cl.araucana.estasfam.app.business.model;

public class AfiliadosPorTipoDTO {
	
	private String codTipo;
	private Integer codTramo;
	private Integer cantidad;
	
	public AfiliadosPorTipoDTO(){}
	
	public AfiliadosPorTipoDTO(String codTipo, Integer codTramo,
			Integer cantidad) {
		super();
		this.codTipo = codTipo;
		this.codTramo = codTramo;
		this.cantidad = cantidad;
	}

	public String getCodTipo() {
		return codTipo;
	}

	public void setCodTipo(String codTipo) {
		this.codTipo = codTipo;
	}

	public Integer getCodTramo() {
		return codTramo;
	}

	public void setCodTramo(Integer codTramo) {
		this.codTramo = codTramo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}
