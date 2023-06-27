package cl.araucana.spl.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 13-01-2014 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Gonzalo Mallea Lorca <BR> gmallea@schema.cl </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Gonzalo Mallea Lorca (gmallea@schema.cl)
 *
 * @version 1.0
 */

public class DetalleRendicionBIT {
	private BigDecimal idDetalleRend;
	private BigDecimal idRendicion;
	private BigDecimal idPago;
	private BigDecimal idConvenio;
	private BigDecimal numProducto;
	private BigDecimal numCliente;
	private Date fchExpiracion;
	private String descripcionProducto;
	private BigDecimal montoProducto;
	private Date fechaOperacion;
	private String codError;
	
	
	private List listErrorImportacion = new ArrayList();
	private List listErrorInconsistente = new ArrayList();


	public String toString() {
		return new StringBuffer("[DetalleRendicionBIT::idDetalleRend=").append(idDetalleRend)
			.append(",idRendicion=").append(idRendicion)
			.append(",idConvenio=").append(idConvenio)
			.append(",idPago=").append(idPago)
			.append(",numProducto=").append(numProducto)
			.append(",numCliente=").append(numCliente)
			.append(",fchExpiracion=").append(fchExpiracion)
			.append(",descripcionProducto=").append(descripcionProducto)
			.append(",montoProducto=").append(montoProducto)
			.append(",fechaOperacion=").append(fechaOperacion)
			.append(",codError=").append(codError)
			.append("]").toString();
	}


	public String getCodError() {
		return codError;
	}


	public void setCodError(String codError) {
		this.codError = codError;
	}


	public String getDescripcionProducto() {
		return descripcionProducto;
	}


	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}


	public Date getFechaOperacion() {
		return fechaOperacion;
	}


	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
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


	public BigDecimal getMontoProducto() {
		return montoProducto;
	}


	public void setMontoProducto(BigDecimal montoProducto) {
		this.montoProducto = montoProducto;
	}


	public BigDecimal getNumCliente() {
		return numCliente;
	}


	public void setNumCliente(BigDecimal numCliente) {
		this.numCliente = numCliente;
	}


	public BigDecimal getNumProducto() {
		return numProducto;
	}


	public void setNumProducto(BigDecimal numProducto) {
		this.numProducto = numProducto;
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


	public Date getFchExpiracion() {
		return fchExpiracion;
	}


	public void setFchExpiracion(Date fchExpiracion) {
		this.fchExpiracion = fchExpiracion;
	}
}
