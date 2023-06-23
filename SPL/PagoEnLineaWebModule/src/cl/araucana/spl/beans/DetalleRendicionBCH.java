package cl.araucana.spl.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetalleRendicionBCH {
	private BigDecimal idDetalleRend;
	private BigDecimal idRendicion;
	private BigDecimal idConvenio;
	private BigDecimal idPago;
	private BigDecimal codEmpresa;
	private BigDecimal oficinaCaptura;
	private Date fchPago;
	private BigDecimal montoPagado;
	private BigDecimal medioPago;
	private BigDecimal montoPagadoMonedaOrigen;
	private BigDecimal tipoCambio;
	private BigDecimal montoMora;
	private String camposAgregados;
	private BigDecimal montoInformado;
	
	private String codError; //Guarda motivo de alguna inconsistencia o problema detectado
	
	private List listErrorImportacion = new ArrayList();
	private List listErrorInconsistente = new ArrayList();

	/**
	 * @return the codEmpresa
	 */
	public BigDecimal getCodEmpresa() {
		return codEmpresa;
	}

	/**
	 * @param codEmpresa the codEmpresa to set
	 */
	public void setCodEmpresa(BigDecimal codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	/**
	 * @return the codError
	 */
	public String getCodError() {
		return codError;
	}

	/**
	 * @param codError the codError to set
	 */
	public void setCodError(String codError) {
		this.codError = codError;
	}

	/**
	 * @return the fchPago
	 */
	public Date getFchPago() {
		return fchPago;
	}

	/**
	 * @param fchPago the fchPago to set
	 */
	public void setFchPago(Date fchPago) {
		this.fchPago = fchPago;
	}

	/**
	 * @return the idConvenio
	 */
	public BigDecimal getIdConvenio() {
		return idConvenio;
	}

	/**
	 * @param idConvenio the idConvenio to set
	 */
	public void setIdConvenio(BigDecimal idConvenio) {
		this.idConvenio = idConvenio;
	}

	/**
	 * @return the idDetalleRend
	 */
	public BigDecimal getIdDetalleRend() {
		return idDetalleRend;
	}

	/**
	 * @param idDetalleRend the idDetalleRend to set
	 */
	public void setIdDetalleRend(BigDecimal idDetalleRend) {
		this.idDetalleRend = idDetalleRend;
	}

	/**
	 * @return the idPago
	 */
	public BigDecimal getIdPago() {
		return idPago;
	}

	/**
	 * @param idPago the idPago to set
	 */
	public void setIdPago(BigDecimal idPago) {
		this.idPago = idPago;
	}

	/**
	 * @return the idRendicion
	 */
	public BigDecimal getIdRendicion() {
		return idRendicion;
	}

	/**
	 * @param idRendicion the idRendicion to set
	 */
	public void setIdRendicion(BigDecimal idRendicion) {
		this.idRendicion = idRendicion;
	}

	/**
	 * @return the listErrorImportacion
	 */
	public List getListErrorImportacion() {
		return listErrorImportacion;
	}

	/**
	 * @param listErrorImportacion the listErrorImportacion to set
	 */
	public void setListErrorImportacion(List listErrorImportacion) {
		this.listErrorImportacion = listErrorImportacion;
	}

	/**
	 * @return the listErrorInconsistente
	 */
	public List getListErrorInconsistente() {
		return listErrorInconsistente;
	}

	/**
	 * @param listErrorInconsistente the listErrorInconsistente to set
	 */
	public void setListErrorInconsistente(List listErrorInconsistente) {
		this.listErrorInconsistente = listErrorInconsistente;
	}

	/**
	 * @return the medioPago
	 */
	public BigDecimal getMedioPago() {
		return medioPago;
	}

	/**
	 * @param medioPago the medioPago to set
	 */
	public void setMedioPago(BigDecimal medioPago) {
		this.medioPago = medioPago;
	}

	/**
	 * @return the montoInformado
	 */
	public BigDecimal getMontoInformado() {
		return montoInformado;
	}

	/**
	 * @param montoInformado the montoInformado to set
	 */
	public void setMontoInformado(BigDecimal montoInformado) {
		this.montoInformado = montoInformado;
	}

	/**
	 * @return the montoMonedaOrigen
	 */
	public BigDecimal getMontoPagadoMonedaOrigen() {
		return montoPagadoMonedaOrigen;
	}

	/**
	 * @param montoMonedaOrigen the montoMonedaOrigen to set
	 */
	public void setMontoPagadoMonedaOrigen(BigDecimal montoPagadoMonedaOrigen) {
		this.montoPagadoMonedaOrigen = montoPagadoMonedaOrigen;
	}

	/**
	 * @return the montoMora
	 */
	public BigDecimal getMontoMora() {
		return montoMora;
	}

	/**
	 * @param montoMora the montoMora to set
	 */
	public void setMontoMora(BigDecimal montoMora) {
		this.montoMora = montoMora;
	}

	/**
	 * @return the montoPagado
	 */
	public BigDecimal getMontoPagado() {
		return montoPagado;
	}

	/**
	 * @param montoPagado the montoPagado to set
	 */
	public void setMontoPagado(BigDecimal montoPagado) {
		this.montoPagado = montoPagado;
	}

	/**
	 * @return the oficinaCaptura
	 */
	public BigDecimal getOficinaCaptura() {
		return oficinaCaptura;
	}

	/**
	 * @param oficinaCaptura the oficinaCaptura to set
	 */
	public void setOficinaCaptura(BigDecimal oficinaCaptura) {
		this.oficinaCaptura = oficinaCaptura;
	}

	/**
	 * @return the tipoCambio
	 */
	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}

	/**
	 * @param tipoCambio the tipoCambio to set
	 */
	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	
	/**
	 * @return the camposAgregados
	 */
	public String getCamposAgregados() {
		return camposAgregados;
	}

	/**
	 * @param camposAgregados the camposAgregados to set
	 */
	public void setCamposAgregados(String camposAgregados) {
		this.camposAgregados = camposAgregados;
	}	

	public String toString() {
		return new StringBuffer("[DETALLERENDICIONBCH::idDetalleRend=").append(idDetalleRend)
			.append(",idrendicion=").append(idRendicion)
			.append(",idConvenio=").append(idConvenio)
			.append(",idPago=").append(idPago)
			.append(",codEmpresa=").append(codEmpresa)
			.append(",oficinaCaptura=").append(oficinaCaptura)
			.append(",fchPago=").append(fchPago)
			.append(",montoPagado=").append(montoPagado)
			.append(",medioPago=").append(medioPago)
			.append(",montoPagadoMonedaOrigen=").append(montoPagadoMonedaOrigen)
			.append(",tipoCambio=").append(tipoCambio)
			.append(",montoMora=").append(montoMora)
			.append(",camposAgregados=").append(camposAgregados)
			.append(",montoInformado=").append(montoInformado)
			.append(",codError=").append(codError)
			.append("]").toString();
	}
}
