package cl.araucana.tesoreria.modelo;

import java.io.Serializable;

public class ComprobanteIngreso implements Serializable {
	private static final long serialVersionUID = 1L;
	private String oficinaCodigo;
	private String oficinaNombre;
	private String folio;
	private String codigoBarra;
	private String monto;
	private String areaNegocio;
	private String fecha;
	private String nombre;
	private String rut;
	private String estado;
	private String fechaDesde;
	private String fechaHasta;

	public String getOficinaCodigo() {
		return oficinaCodigo;
	}

	public void setOficinaCodigo(String oficinaCodigo) {
		this.oficinaCodigo = oficinaCodigo;
	}

	public String getOficinaNombre() {
		return oficinaNombre;
	}

	public void setOficinaNombre(String oficinaNombre) {
		this.oficinaNombre = oficinaNombre;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getAreaNegocio() {
		return areaNegocio;
	}

	public void setAreaNegocio(String areaNegocio) {
		this.areaNegocio = areaNegocio;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
}
