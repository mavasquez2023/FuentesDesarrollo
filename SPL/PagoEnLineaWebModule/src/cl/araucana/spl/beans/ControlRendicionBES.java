

/*
 * @(#) ControlRendicionBES.java    1.0 06-08-2009
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
 *            en las líneas de control de detalle.
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
public class ControlRendicionBES {
	
	private String tipoRegistro;
	private long totalRegistros;
	private BigDecimal montoTotal;
	private long registrosAceptados;
	private BigDecimal montoAceptado;
	private long numMultipago;
	private long numTransacciones;
	private String filler;
	
	private String codError; //Guarda motivo de alguna inconsistencia o problema detectado
	
	private List listErrorImportacion = new ArrayList();
	private List listErrorInconsistente = new ArrayList();
	
	
	/**
	 * @return the filler
	 */
	public String getFiller() {
		return filler;
	}
	/**
	 * @param filler the filler to set
	 */
	public void setFiller(String filler) {
		this.filler = filler;
	}
	/**
	 * @return the montoAceptado
	 */
	public BigDecimal getMontoAceptado() {
		return montoAceptado;
	}
	/**
	 * @param montoAceptado the montoAceptado to set
	 */
	public void setMontoAceptado(BigDecimal montoAceptado) {
		this.montoAceptado = montoAceptado;
	}
	/**
	 * @return the montoTotal
	 */
	public BigDecimal getMontoTotal() {
		return montoTotal;
	}
	/**
	 * @param montoTotal the montoTotal to set
	 */
	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}
	/**
	 * @return the numMultipago
	 */
	public long getNumMultipago() {
		return numMultipago;
	}
	/**
	 * @param numMultipago the numMultipago to set
	 */
	public void setNumMultipago(long numMultipago) {
		this.numMultipago = numMultipago;
	}
	/**
	 * @return the numTransacciones
	 */
	public long getNumTransacciones() {
		return numTransacciones;
	}
	/**
	 * @param numTransacciones the numTransacciones to set
	 */
	public void setNumTransacciones(long numTransacciones) {
		this.numTransacciones = numTransacciones;
	}
	/**
	 * @return the registrosAceptados
	 */
	public long getRegistrosAceptados() {
		return registrosAceptados;
	}
	/**
	 * @param registrosAceptados the registrosAceptados to set
	 */
	public void setRegistrosAceptados(long registrosAceptados) {
		this.registrosAceptados = registrosAceptados;
	}
	/**
	 * @return the tipoRegistro
	 */
	public String getTipoRegistro() {
		return tipoRegistro;
	}
	/**
	 * @param tipoRegistro the tipoRegistro to set
	 */
	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	/**
	 * @return the totalRegistros
	 */
	public long getTotalRegistros() {
		return totalRegistros;
	}
	/**
	 * @param totalRegistros the totalRegistros to set
	 */
	public void setTotalRegistros(long totalRegistros) {
		this.totalRegistros = totalRegistros;
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
}

