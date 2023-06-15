package cl.laaraucana.botonpago.web.database.ibatis.domain;

public class Pago {
	private String fchContable;
	private String fchInicio;
	private String fchTransaccion;
	private String glosa;
	private String idConvenio;
	private String idEstado;
	private String idPago;
	private String idSistema;
	private String monto;
	private String montoRendicion;
	private String pagado;
	private String pagador;
	private String urlNotificacionOrigen;
	private String urlRetornoOrigen;

	public String getFchContable() {
		return fchContable.trim();
	}

	public String getFchInicio() {
		return fchInicio.trim();
	}

	public String getFchTransaccion() {
		return fchTransaccion.trim();
	}

	public String getGlosa() {
		return glosa.trim();
	}

	public String getIdConvenio() {
		return idConvenio.trim();
	}

	public String getIdEstado() {
		return idEstado.trim();
	}

	public String getIdPago() {
		return idPago.trim();
	}

	public String getIdSistema() {
		return idSistema.trim();
	}

	public String getMonto() {
		return monto.trim();
	}

	public String getMontoRendicion() {
		return montoRendicion.trim();
	}

	public String getPagado() {
		return pagado.trim();
	}

	public String getPagador() {
		return pagador.trim();
	}

	public String getUrlNotificacionOrigen() {
		return urlNotificacionOrigen.trim();
	}

	public String getUrlRetornoOrigen() {
		return urlRetornoOrigen.trim();
	}

	public void setFchContable(String fchContable) {
		this.fchContable = fchContable;
	}

	public void setFchInicio(String fchInicio) {
		this.fchInicio = fchInicio;
	}

	public void setFchTransaccion(String fchTransaccion) {
		this.fchTransaccion = fchTransaccion;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	public void setIdConvenio(String idConvenio) {
		this.idConvenio = idConvenio;
	}

	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}

	public void setIdPago(String idPago) {
		this.idPago = idPago;
	}

	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public void setMontoRendicion(String montoRendicion) {
		this.montoRendicion = montoRendicion;
	}

	public void setPagado(String pagado) {
		this.pagado = pagado;
	}

	public void setPagador(String pagador) {
		this.pagador = pagador;
	}

	public void setUrlNotificacionOrigen(String urlNotificacionOrigen) {
		this.urlNotificacionOrigen = urlNotificacionOrigen;
	}

	public void setUrlRetornoOrigen(String urlRetornoOrigen) {
		this.urlRetornoOrigen = urlRetornoOrigen;
	}
}