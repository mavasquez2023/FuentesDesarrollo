package cl.araucana.estasfam.app.business.model;

public class PagosDirectoPorTipoDTO {
	
	private String codTipo;
	private Integer cantidad;
	private Double monto;
	
	public PagosDirectoPorTipoDTO(){}
	
	public PagosDirectoPorTipoDTO(String codTipo, Integer cantidad, Double monto) {
		super();
		this.codTipo = codTipo;
		this.cantidad = cantidad;
		this.monto = monto;
	}

	public String getCodTipo() {
		return codTipo;
	}

	public void setCodTipo(String codTipo) {
		this.codTipo = codTipo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}
}
