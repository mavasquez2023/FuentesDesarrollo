package cl.laaraucana.botonpago.web.database.ibatis.domain;

public class Mediopago {
	private Integer activo;
	private Integer codbanco;
	private String codigo;
	private String descripcion;
	private Integer idMediopago;
	private String imagen;
	private String urlInicioPago;

	public Integer getActivo() {
		return activo;
	}

	public Integer getCodbanco() {
		return codbanco;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Integer getIdMediopago() {
		return idMediopago;
	}

	public String getImagen() {
		return imagen;
	}

	public String getUrlInicioPago() {
		return urlInicioPago;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	public void setCodbanco(Integer codbanco) {
		this.codbanco = codbanco;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setIdMediopago(Integer idMediopago) {
		this.idMediopago = idMediopago;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public void setUrlInicioPago(String urlInicioPago) {
		this.urlInicioPago = urlInicioPago;
	}
}