

/*
 * @(#) ResumenDetalleRendicionBES.java    1.0 06-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.spl.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
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
 *            <TD> 06-08-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Jorge Landeros <BR> jlandero@schema.cl </TD>
 *            <TD> Versión inicial. Contiene la información que llega en el archivo de conciliación batch
 *            en el resumen de los detalles.
 *            </TD>
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
 * @author Lorge Landeros (jlandero@schema.cl)
 *
 * @version 1.0
 */
public class ResumenDetalleRendicionBES {
	
	//** Resumen del Detalle **//
	
	private DetalleRendicionBES[] detalleRendicionBES;
	
	private BigDecimal idRendicion;
	private BigDecimal idResDetalleRend;
	private String resumenTipoRegistro;
	private int resumenSubTipo;
	private long resumenTxBanco;
	private int resumenNumPagos;
	private BigDecimal resumenMontoPago;
	private String resumenFiller;
	
	
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
	/**
	 * @return the resumenFiller
	 */
	public String getResumenFiller() {
		return resumenFiller;
	}
	/**
	 * @param resumenFiller the resumenFiller to set
	 */
	public void setResumenFiller(String resumenFiller) {
		this.resumenFiller = resumenFiller;
	}
	/**
	 * @return the resumenMontoPago
	 */
	public BigDecimal getResumenMontoPago() {
		return resumenMontoPago;
	}
	/**
	 * @param resumenMontoPago the resumenMontoPago to set
	 */
	public void setResumenMontoPago(BigDecimal resumenMontoPago) {
		this.resumenMontoPago = resumenMontoPago;
	}
	/**
	 * @return the resumenNumPagos
	 */
	public int getResumenNumPagos() {
		return resumenNumPagos;
	}
	/**
	 * @param resumenNumPagos the resumenNumPagos to set
	 */
	public void setResumenNumPagos(int resumenNumPagos) {
		this.resumenNumPagos = resumenNumPagos;
	}
	/**
	 * @return the resumenSubTipo
	 */
	public int getResumenSubTipo() {
		return resumenSubTipo;
	}
	/**
	 * @param resumenSubTipo the resumenSubTipo to set
	 */
	public void setResumenSubTipo(int resumenSubTipo) {
		this.resumenSubTipo = resumenSubTipo;
	}
	/**
	 * @return the resumenTipoRegistro
	 */
	public String getResumenTipoRegistro() {
		return resumenTipoRegistro;
	}
	/**
	 * @param resumenTipoRegistro the resumenTipoRegistro to set
	 */
	public void setResumenTipoRegistro(String resumenTipoRegistro) {
		this.resumenTipoRegistro = resumenTipoRegistro;
	}
	/**
	 * @return the resumenTxBanco
	 */
	public long getResumenTxBanco() {
		return resumenTxBanco;
	}
	/**
	 * @param resumenTxBanco the resumenTxBanco to set
	 */
	public void setResumenTxBanco(long resumenTxBanco) {
		this.resumenTxBanco = resumenTxBanco;
	}
	/**
	 * @return the detalleRendicionPagosBES
	 */
	public DetalleRendicionBES[] getDetalleRendicionBES() {
		return detalleRendicionBES;
	}
	/**
	 * @param detalleRendicionPagosBES the detalleRendicionPagosBES to set
	 */
	public void setDetalleRendicionBES(
			DetalleRendicionBES[] detalleRendicionBES) {
		this.detalleRendicionBES = detalleRendicionBES;
	}
	public BigDecimal getIdResDetalleRend() {
		return idResDetalleRend;
	}
	public void setIdResDetalleRend(BigDecimal idResDetalleRend) {
		this.idResDetalleRend = idResDetalleRend;
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
}

