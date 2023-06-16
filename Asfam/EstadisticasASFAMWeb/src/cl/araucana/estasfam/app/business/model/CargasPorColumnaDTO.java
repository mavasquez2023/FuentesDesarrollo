package cl.araucana.estasfam.app.business.model;

public class CargasPorColumnaDTO {
	
	private String codTipo;
	private Integer codColumna;
	private Integer cantidad;
	
	public CargasPorColumnaDTO(){}
	
	public CargasPorColumnaDTO(String codTipo, Integer codColumna, Integer cantidad) {
		super();
		this.codTipo = codTipo;
		this.codColumna = codColumna;
		this.cantidad = cantidad;
	}

	public String getCodTipo() {
		return codTipo;
	}

	public void setCodTipo(String codTipo) {
		this.codTipo = codTipo;
	}

	public Integer getCodColumna() {
		return codColumna;
	}

	public void setCodColumna(Integer codColumna) {
		this.codColumna = codColumna;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}
