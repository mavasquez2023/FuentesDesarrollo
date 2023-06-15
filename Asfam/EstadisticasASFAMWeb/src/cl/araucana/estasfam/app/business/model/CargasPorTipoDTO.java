package cl.araucana.estasfam.app.business.model;

public class CargasPorTipoDTO {
	
	private String codTipo;
	private Integer codTramo;
	private Integer cantidad;
	private Integer monto;
	
	public CargasPorTipoDTO(){}
	
	public CargasPorTipoDTO(String codTipo, Integer codTramo,
			Integer cantidad, Integer monto) {
		super();
		this.codTipo = codTipo;
		this.codTramo = codTramo;
		this.cantidad = cantidad;
		this.monto = monto;
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

	public Integer getMonto() {
		return monto;
	}

	public void setMonto(Integer monto) {
		this.monto = monto;
	}
}
