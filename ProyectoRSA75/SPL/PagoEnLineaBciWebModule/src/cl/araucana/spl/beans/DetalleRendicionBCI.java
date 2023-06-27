package cl.araucana.spl.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DetalleRendicionBCI {
	private BigDecimal idDetalleRend;
	private BigDecimal idRendicion;
	private BigDecimal idConvenio;
	private BigDecimal idPago;
	private BigDecimal pagadorRut;
	private String pagadorDv;
	private String pagadorNombre;
	private String idProducto;
	private BigDecimal precio;
	private BigDecimal cantidad;
	private BigDecimal total;
	private String formaPago;
	private BigDecimal comisionCompra;
	private BigDecimal estado;
	private BigDecimal codRechazo;
	private String codError; //Guarda motivo de alguna inconsistencia o problema detectado
	
	private List listErrorImportacion = new ArrayList();
	private List listErrorInconsistente = new ArrayList();

	
	public String toString() {
		return new StringBuffer("[DETALLERENDICIONBCI::idDetalleRend=").append(idDetalleRend)
			.append(",idrendicion=").append(idRendicion)
			.append(",idConvenio=").append(idConvenio)
			.append(",idPago=").append(idPago)
			.append(",pagadorRut=").append(pagadorRut)
			.append(",pagadorDv=").append(pagadorDv)
			.append(",pagadorNombre=").append(pagadorNombre)
			.append(",idProducto=").append(idProducto)
			.append(",precio=").append(precio)
			.append(",cantidad=").append(cantidad)
			.append(",total=").append(total)
			.append(",formaPago=").append(formaPago)
			.append(",comisionCompra=").append(comisionCompra)
			.append(",estado=").append(estado)
			.append(",codRechazo=").append(codRechazo)
			.append(",codError=").append(codError)
			.append("]").toString();
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
	 * @return the cantidad
	 */
	public BigDecimal getCantidad() {
		return cantidad;
	}


	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}


	/**
	 * @return the codRechazo
	 */
	public BigDecimal getCodRechazo() {
		return codRechazo;
	}


	/**
	 * @param codRechazo the codRechazo to set
	 */
	public void setCodRechazo(BigDecimal codRechazo) {
		this.codRechazo = codRechazo;
	}


	/**
	 * @return the comisionCompra
	 */
	public BigDecimal getComisionCompra() {
		return comisionCompra;
	}


	/**
	 * @param comisionCompra the comisionCompra to set
	 */
	public void setComisionCompra(BigDecimal comisionCompra) {
		this.comisionCompra = comisionCompra;
	}


	/**
	 * @return the estado
	 */
	public BigDecimal getEstado() {
		return estado;
	}


	/**
	 * @param estado the estado to set
	 */
	public void setEstado(BigDecimal estado) {
		this.estado = estado;
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
	 * @return the idProducto
	 */
	public String getIdProducto() {
		return idProducto;
	}


	/**
	 * @param idProducto the idProducto to set
	 */
	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
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
	 * @return the pagadorDv
	 */
	public String getPagadorDv() {
		return pagadorDv;
	}


	/**
	 * @param pagadorDv the pagadorDv to set
	 */
	public void setPagadorDv(String pagadorDv) {
		this.pagadorDv = pagadorDv;
	}


	/**
	 * @return the pagadorNombre
	 */
	public String getPagadorNombre() {
		return pagadorNombre;
	}


	/**
	 * @param pagadorNombre the pagadorNombre to set
	 */
	public void setPagadorNombre(String pagadorNombre) {
		this.pagadorNombre = pagadorNombre;
	}


	/**
	 * @return the pagadorRut
	 */
	public BigDecimal getPagadorRut() {
		return pagadorRut;
	}


	/**
	 * @param pagadorRut the pagadorRut to set
	 */
	public void setPagadorRut(BigDecimal pagadorRut) {
		this.pagadorRut = pagadorRut;
	}


	/**
	 * @return the precio
	 */
	public BigDecimal getPrecio() {
		return precio;
	}


	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}


	/**
	 * @return the total
	 */
	public BigDecimal getTotal() {
		return total;
	}


	/**
	 * @param total the total to set
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}


	/**
	 * @return the formaPago
	 */
	public String getFormaPago() {
		return formaPago;
	}


	/**
	 * @param formaPago the formaPago to set
	 */
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
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

}
