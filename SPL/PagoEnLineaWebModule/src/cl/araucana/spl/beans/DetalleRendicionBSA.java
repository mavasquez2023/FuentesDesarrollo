package cl.araucana.spl.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetalleRendicionBSA {
	private BigDecimal idDetalleRend;
	private BigDecimal idRendicion;
	private BigDecimal idConvenio;
	private BigDecimal idPago;
	private String idCarro;
	private BigDecimal nroProducto;
	private String nroCliente;
	private Date fchExpiracion;
	private String descProducto;
	private BigDecimal montoProducto;
	private Date fchHoraOperacion;
	
	private String codError; //Guarda motivo de alguna inconsistencia o problema detectado
	
	private List listErrorImportacion = new ArrayList();
	private List listErrorInconsistente = new ArrayList();

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

	public String toString() {
		return new StringBuffer("[DETALLERENDICIONBSA::idDetalleRend=").append(idDetalleRend)
			.append(",idrendicion=").append(idRendicion)
			.append(",idConvenio=").append(idConvenio)
			.append(",idPago=").append(idPago)
			.append(",idCarro=").append(idCarro)
			.append(",nroProducto=").append(nroProducto)
			.append(",nroCliente=").append(nroCliente)
			.append(",fchExpiracion=").append(fchExpiracion)
			.append(",descProducto=").append(descProducto)
			.append(",montoProducto=").append(montoProducto)
			.append(",fchHoraOperacion=").append(fchHoraOperacion)
			.append(",codError=").append(codError)
			.append("]").toString();
	}

	/**
	 * @return the descProducto
	 */
	public String getDescProducto() {
		return descProducto;
	}

	/**
	 * @param descProducto the descProducto to set
	 */
	public void setDescProducto(String descProducto) {
		this.descProducto = descProducto;
	}

	/**
	 * @return the fchExpiracion
	 */
	public Date getFchExpiracion() {
		return fchExpiracion;
	}

	/**
	 * @param fchExpiracion the fchExpiracion to set
	 */
	public void setFchExpiracion(Date fchExpiracion) {
		this.fchExpiracion = fchExpiracion;
	}

	/**
	 * @return the fchHoraOperacion
	 */
	public Date getFchHoraOperacion() {
		return fchHoraOperacion;
	}

	/**
	 * @param fchHoraOperacion the fchHoraOperacion to set
	 */
	public void setFchHoraOperacion(Date fchHoraOperacion) {
		this.fchHoraOperacion = fchHoraOperacion;
	}

	/**
	 * @return the idCarro
	 */
	public String getIdCarro() {
		return idCarro;
	}

	/**
	 * @param idCarro the idCarro to set
	 */
	public void setIdCarro(String idCarro) {
		this.idCarro = idCarro;
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
	 * @return the montoProducto
	 */
	public BigDecimal getMontoProducto() {
		return montoProducto;
	}

	/**
	 * @param montoProducto the montoProducto to set
	 */
	public void setMontoProducto(BigDecimal montoProducto) {
		this.montoProducto = montoProducto;
	}

	/**
	 * @return the nroCliente
	 */
	public String getNroCliente() {
		return nroCliente;
	}

	/**
	 * @param nroCliente the nroCliente to set
	 */
	public void setNroCliente(String nroCliente) {
		this.nroCliente = nroCliente;
	}

	/**
	 * @return the nroProducto
	 */
	public BigDecimal getNroProducto() {
		return nroProducto;
	}

	/**
	 * @param nroProducto the nroProducto to set
	 */
	public void setNroProducto(BigDecimal nroProducto) {
		this.nroProducto = nroProducto;
	}

}
