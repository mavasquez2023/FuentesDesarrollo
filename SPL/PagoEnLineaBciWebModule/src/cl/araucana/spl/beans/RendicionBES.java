
/*
 * @(#) RendicionBES.java    1.0 06-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */

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
 *            <TD> 06-08-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Jorge Landeros <BR> jlandero@schema.cl </TD>
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
 * @author Lorge Landeros (jlandero@schema.cl)
 *
 * @version 1.0
 */

public class RendicionBES {
	private BigDecimal idRendicion;
	private String checksum;
	private Date fchImportacion;
	private Date fchCaptura;
	private BigDecimal nroPagos;
	private BigDecimal montoTotal;
	private ControlRendicionBES controlRendicionBES;
	private EncabezadoRendicionBES encabezadoRendicionBES;
	
	private List listErrorImportacion = new ArrayList();
	
	public String toString() {
		return new StringBuffer("[RENDICIONBCI::idRendicion=").append(idRendicion)
			.append(",checksum=").append(checksum)
			.append(",fchImportacion=").append(fchImportacion)
			.append(",nroPagos=").append(nroPagos)
			.append(",montoTotal=").append(montoTotal)
			.append("]").toString();
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public Date getFchImportacion() {
		return fchImportacion;
	}

	public void setFchImportacion(Date fchImportacion) {
		this.fchImportacion = fchImportacion;
	}

	public BigDecimal getIdRendicion() {
		return idRendicion;
	}

	public void setIdRendicion(BigDecimal idRendicion) {
		this.idRendicion = idRendicion;
	}

	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	public BigDecimal getNroPagos() {
		return nroPagos;
	}

	public void setNroPagos(BigDecimal nroPagos) {
		this.nroPagos = nroPagos;
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
	 * @return the controlRendicionBES
	 */
	public ControlRendicionBES getControlRendicionBES() {
		return controlRendicionBES;
	}

	/**
	 * @param controlRendicionBES the controlRendicionBES to set
	 */
	public void setControlRendicionBES(ControlRendicionBES controlRendicionBES) {
		this.controlRendicionBES = controlRendicionBES;
	}

	/**
	 * @return the encabezadoRendicionBES
	 */
	public EncabezadoRendicionBES getEncabezadoRendicionBES() {
		return encabezadoRendicionBES;
	}

	/**
	 * @param encabezadoRendicionBES the encabezadoRendicionBES to set
	 */
	public void setEncabezadoRendicionBES(
			EncabezadoRendicionBES encabezadoRendicionBES) {
		this.encabezadoRendicionBES = encabezadoRendicionBES;
	}

	/**
	 * @return the fchCaptura
	 */
	public Date getFchCaptura() {
		return fchCaptura;
	}

	/**
	 * @param fchCaptura the fchCaptura to set
	 */
	public void setFchCaptura(Date fchCaptura) {
		this.fchCaptura = fchCaptura;
	}

}
