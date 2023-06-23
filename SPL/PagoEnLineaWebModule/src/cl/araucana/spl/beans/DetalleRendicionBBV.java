package cl.araucana.spl.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DetalleRendicionBBV {
	private BigDecimal idDetalleRend;
	private BigDecimal idRendicion;
	private BigDecimal idConvenio;
	private BigDecimal idPago;
	private String codTransBanco;
	private String codTransComercio;
	private BigDecimal numTransaccion;
	private String idCliente;
	private String idDocumento;
	private String rutClientePagador;
	private BigDecimal montoPago;
	private BigDecimal fechaPago;
	private BigDecimal horaPago;
	private BigDecimal fechaRendicion;
	
	private String codError; //Guarda motivo de alguna inconsistencia o problema detectado
	
	private List listErrorImportacion = new ArrayList();
	private List listErrorInconsistente = new ArrayList();

	public String toString() {
		return new StringBuffer("[DetalleRendicionBBV::idDetalleRend=").append(idDetalleRend)
			.append(",idrendicion=").append(idRendicion)
			.append(",idConvenio=").append(idConvenio)
			.append(",idPago=").append(idPago)
			.append(",codTransBanco=").append(codTransBanco)
			.append(",codTransComercio=").append(codTransComercio)
			.append(",numTransaccion=").append(numTransaccion)
			.append(",idCliente=").append(idCliente)
			.append(",idDocumento=").append(idDocumento)
			.append(",rutClientePagador=").append(rutClientePagador)
			.append(",montoPago=").append(montoPago)
			.append(",fechaPago=").append(fechaPago)
			.append(",horaPago=").append(horaPago)
			.append(",fechaRecaudacion=").append(fechaRendicion)
			.append(",codError=").append(codError)
			.append("]").toString();
	}

	public String getCodError() {
		return codError;
	}

	public void setCodError(String codError) {
		this.codError = codError;
	}

	public String getCodTransBanco() {
		return codTransBanco;
	}

	public void setCodTransBanco(String codTransBanco) {
		this.codTransBanco = codTransBanco;
	}

	public String getCodTransComercio() {
		return codTransComercio;
	}

	public void setCodTransComercio(String codTransComercio) {
		this.codTransComercio = codTransComercio;
	}

	public BigDecimal getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(BigDecimal fechaPago) {
		this.fechaPago = fechaPago;
	}

		public BigDecimal getHoraPago() {
		return horaPago;
	}

	public void setHoraPago(BigDecimal horaPago) {
		this.horaPago = horaPago;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public BigDecimal getIdConvenio() {
		return idConvenio;
	}

	public void setIdConvenio(BigDecimal idConvenio) {
		this.idConvenio = idConvenio;
	}

	public BigDecimal getIdDetalleRend() {
		return idDetalleRend;
	}

	public void setIdDetalleRend(BigDecimal idDetalleRend) {
		this.idDetalleRend = idDetalleRend;
	}

	public String getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}

	public BigDecimal getIdPago() {
		return idPago;
	}

	public void setIdPago(BigDecimal idPago) {
		this.idPago = idPago;
	}

	public BigDecimal getIdRendicion() {
		return idRendicion;
	}

	public void setIdRendicion(BigDecimal idRendicion) {
		this.idRendicion = idRendicion;
	}

	public List getListErrorImportacion() {
		return listErrorImportacion;
	}

	public void setListErrorImportacion(List listErrorImportacion) {
		this.listErrorImportacion = listErrorImportacion;
	}

	public List getListErrorInconsistente() {
		return listErrorInconsistente;
	}

	public void setListErrorInconsistente(List listErrorInconsistente) {
		this.listErrorInconsistente = listErrorInconsistente;
	}

	public BigDecimal getMontoPago() {
		return montoPago;
	}

	public void setMontoPago(BigDecimal montoPago) {
		this.montoPago = montoPago;
	}

	public BigDecimal getNumTransaccion() {
		return numTransaccion;
	}

	public void setNumTransaccion(BigDecimal numTransaccion) {
		this.numTransaccion = numTransaccion;
	}

	public String getRutClientePagador() {
		return rutClientePagador;
	}

	public void setRutClientePagador(String rutClientePagador) {
		this.rutClientePagador = rutClientePagador;
	}

	public BigDecimal getFechaRendicion() {
		return fechaRendicion;
	}

	public void setFechaRendicion(BigDecimal fechaRendicion) {
		this.fechaRendicion = fechaRendicion;
	}
	
}
