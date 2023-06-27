package cl.araucana.adminCpe.hibernate.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

public class SPLPagoVO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long idPago;
	private String pagador;
	private int idEstado;
	private int idSistema;
	private int idConvenio;
	private Timestamp fechaInicio;	
	private Timestamp fechaTransaccion;
	private Date fechaContable;
	private int monto;
	private int montoRendicion;
	private int pagado; 
	private String glosa;
	private String urlRetornoOrigen;
	private String urlNotificacionOrigen;
	private String ctaCte;
	private int idMedioPago;
	private int codBanco;
	Set detallePago;

	public SPLPagoVO()
	{
		super();
	}
	
	public Set getDetallePago() {
		return detallePago;
	}


	public void setDetallePago(Set detallePago) {
		this.detallePago = detallePago;
	}


	public int getCodBanco() {
		return codBanco;
	}

	public void setCodBanco(int codBanco) {
		this.codBanco = codBanco;
	}

	public String getCtaCte() {
		return ctaCte;
	}

	public void setCtaCte(String ctaCte) {
		this.ctaCte = ctaCte;
	}

	public int getIdMedioPago() {
		return idMedioPago;
	}

	public void setIdMedioPago(int idMedioPago) {
		this.idMedioPago = idMedioPago;
	}

	public Date getFechaContable() {
		return this.fechaContable;
	}

	public void setFechaContable(Date fechaContable) {
		this.fechaContable = fechaContable;
	}

	public Timestamp getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Timestamp getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	public void setFechaTransaccion(Timestamp fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public String getGlosa() {
		return this.glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	public int getIdConvenio() {
		return this.idConvenio;
	}

	public void setIdConvenio(int idConvenio) {
		this.idConvenio = idConvenio;
	}

	public int getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public long getIdPago() {
		return this.idPago;
	}

	public void setIdPago(long idPago) {
		this.idPago = idPago;
	}

	public int getIdSistema() {
		return this.idSistema;
	}

	public void setIdSistema(int idSistema) {
		this.idSistema = idSistema;
	}

	public int getMonto() {
		return this.monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public int getMontoRendicion() {
		return this.montoRendicion;
	}

	public void setMontoRendicion(int montoRendicion) {
		this.montoRendicion = montoRendicion;
	}

	public int getPagado() {
		return this.pagado;
	}

	public void setPagado(int pagado) {
		this.pagado = pagado;
	}

	public String getPagador() {
		return this.pagador;
	}

	public void setPagador(String pagador) {
		this.pagador = pagador;
	}

	public String getUrlNotificacionOrigen() {
		return this.urlNotificacionOrigen;
	}

	public void setUrlNotificacionOrigen(String urlNotificacionOrigen) {
		this.urlNotificacionOrigen = urlNotificacionOrigen;
	}

	public String getUrlRetornoOrigen() {
		return this.urlRetornoOrigen;
	}

	public void setUrlRetornoOrigen(String urlRetornoOrigen) {
		this.urlRetornoOrigen = urlRetornoOrigen;
	}

}
