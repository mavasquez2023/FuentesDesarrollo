package cl.laaraucana.botonpago.web.database.ibatis.domain;

public class Detpago {

	private String descripcion;
	private String folio;
	private String idDetallePago;
	private String idPago;
	private String monto;

	public String getDescripcion() {
		return descripcion.trim();
	}

	public String getFolio() {
		return folio.trim();
	}

	public String getIdDetallePago() {
		return idDetallePago.trim();
	}

	public String getIdPago() {
		return idPago.trim();
	}

	public String getMonto() {
		return monto.trim();
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public void setIdDetallePago(String idDetallePago) {
		this.idDetallePago = idDetallePago;
	}

	public void setIdPago(String idPago) {
		this.idPago = idPago;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}
}