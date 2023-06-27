package cl.araucana.spl.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import cl.araucana.spl.util.Nulls;

public class Pago {
	private BigDecimal id;
	private String pagador = Nulls.STRING;
	private Estado estado;
	private Sistema sistema;
	private Convenio convenio;
	private Date fechaInicio;
	private Date fechaTransaccion = Nulls.DATE;
	private Date fechaContable = Nulls.DATE;
	private BigDecimal monto;
	private BigDecimal montoRendicion = Nulls.BIGDECIMAL;
	private Integer pagado = Nulls.INTEGER;
	private String glosa = Nulls.STRING;
	private String urlRetornoOrigen;
	private String urlNotificacionOrigen;
	private List detalles = new ArrayList();
	private Mensaje mensaje;
	private int estRegistroLibroBco;//0-No Registrado, 1-En línea, 2-Batch
	private int estCurseCompTes;//0-No Actualizado, 1-En línea

	public Convenio getConvenio() {
		return convenio;
	}
	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}
	public BigDecimal getIdConvenio() {
		return convenio.getId();
	}
	public BigDecimal getIdEstado() {
		return estado.getId();
	}
	public BigDecimal getIdSistema() {
		return sistema.getId();
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getGlosa() {
		return glosa;
	}
	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public Integer getPagado() {
		return pagado;
	}
	public void setPagado(Integer pagado) {
		this.pagado = pagado;
	}
	public String getPagador() {
		return pagador;
	}
	public void setPagador(String pagador) {
		this.pagador = pagador;
	}
	public Sistema getSistema() {
		return sistema;
	}
	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}
	
	public String toString() {
		return new StringBuffer("[PAGO::id=").append(id)
			.append(",pagador=").append(pagador)
			.append(",estado=").append(estado)
			.append(",sistema=").append(sistema)
			.append(",convenio=").append(convenio)
			.append(",fechaInicio=").append(fechaInicio)
			.append(",fechaTransaccion=").append(fechaTransaccion)
			.append(",fechaContable=").append(fechaContable)
			.append(",monto=").append(monto)
			.append(",montoRendicion=").append(montoRendicion)
			.append(",pagado=").append(pagado)
			.append(",glosa=").append(glosa)
			.append(",urlRetorno=").append(urlRetornoOrigen)
			.append(",urlNotificacion=").append(urlNotificacionOrigen)
			.append(",detalles=").append(detalles)
			.append("]").toString();
	}
	public List getDetalles() {
		return detalles;
	}
	public void setDetalles(List detalles) {
		this.detalles = detalles;
	}
	public BigDecimal getMontoTotal() {
		BigDecimal total = new BigDecimal(0);
		for (Iterator it = getDetalles().iterator(); it.hasNext(); ) {
			DetallePago detalle = (DetallePago) it.next();
			total = total.add(detalle.getMonto());
		}
		return total;
	}
	public String getCodigoBanco() {
		return getConvenio().getCodigoBanco();
	}
	public String getCodigoSistema() {
		return getSistema().getCodigo();
	}
	public int getCantidadItems() {
		return detalles.size();
	}
	public String getUrlCgi() {
		return getConvenio().getUrlCgi();
	}
	public Date getFechaContable() {
		return fechaContable;
	}
	public void setFechaContable(Date fechaContable) {
		this.fechaContable = fechaContable;
	}
	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}
	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}
	public BigDecimal getMontoRendicion() {
		return montoRendicion;
	}
	public void setMontoRendicion(BigDecimal montoRendicion) {
		this.montoRendicion = montoRendicion;
	}
	public String getUrlRetornoOrigen() {
		return urlRetornoOrigen;
	}
	public void setUrlRetornoOrigen(String urlRetornoOrigen) {
		this.urlRetornoOrigen = urlRetornoOrigen;
	}

	/**
	 * @return the mensaje
	 */
	public Mensaje getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}
	public String getUrlNotificacionOrigen() {
		return urlNotificacionOrigen;
	}
	public void setUrlNotificacionOrigen(String urlNotificacionOrigen) {
		this.urlNotificacionOrigen = urlNotificacionOrigen;
	}
	public int getEstRegistroLibroBco() {
		return estRegistroLibroBco;
	}
	public void setEstRegistroLibroBco(int estRegistroLibroBco) {
		this.estRegistroLibroBco = estRegistroLibroBco;
	}
	public int getEstCurseCompTes() {
		return estCurseCompTes;
	}
	public void setEstCurseCompTes(int estCurseCompTes) {
		this.estCurseCompTes = estCurseCompTes;
	}
}
