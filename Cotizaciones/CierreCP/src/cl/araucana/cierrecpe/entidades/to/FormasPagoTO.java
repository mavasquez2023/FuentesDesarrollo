

/*
 * @(#) FormaspagoTO.java    1.0 19/08/2010
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.entidades.to;

import java.util.Collection;


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
 *            <TD> 19/08/2010 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZORÍN <BR> clillo@laaraucana.cl </TD>
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
 * @author CLAUDIO LILLO AZORÍN (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class FormasPagoTO {
private int formaPago;
private int numComprobantes;
private int numDYNP;
private long totalComprobantes;
private long totalTesoreria;
private int numDescuadraturas;
private Collection descuadraturas;
private Collection comprobantesCP;
/**
 * @return el formaPago
 */
public int getFormaPago() {
	return formaPago;
}
/**
 * @param formaPago el formaPago a establecer
 */
public void setFormaPago(int formaPago) {
	this.formaPago = formaPago;
}
/**
 * @return el numComprobantes
 */
public int getNumComprobantes() {
	return numComprobantes;
}
/**
 * @param numComprobantes el numComprobantes a establecer
 */
public void setNumComprobantes(int numComprobantes) {
	this.numComprobantes = numComprobantes;
}
/**
 * @return el numDescuadraturas
 */
public int getNumDescuadraturas() {
	return numDescuadraturas;
}
/**
 * @param numDescuadraturas el numDescuadraturas a establecer
 */
public void setNumDescuadraturas(int numDescuadraturas) {
	this.numDescuadraturas = numDescuadraturas;
}
/**
 * @return el descuadraturas
 */
public Collection getDescuadraturas() {
	return descuadraturas;
}
/**
 * @param descuadraturas el descuadraturas a establecer
 */
public void setDescuadraturas(Collection descuadraturas) {
	this.descuadraturas = descuadraturas;
}
/**
 * @return el totalComprobantes
 */
public long getTotalComprobantes() {
	return totalComprobantes;
}
/**
 * @param totalComprobantes el totalComprobantes a establecer
 */
public void setTotalComprobantes(long totalComprobantes) {
	this.totalComprobantes = totalComprobantes;
}
/**
 * @return el totalTesoreria
 */
public long getTotalTesoreria() {
	return totalTesoreria;
}
/**
 * @param totalTesoreria el totalTesoreria a establecer
 */
public void setTotalTesoreria(long totalTesoreria) {
	this.totalTesoreria = totalTesoreria;
}
/**
 * @return el numDYNP
 */
public int getNumDYNP() {
	return numDYNP;
}
/**
 * @param numDYNP el numDYNP a establecer
 */
public void setNumDYNP(int numDYNP) {
	this.numDYNP = numDYNP;
}
/**
 * @return el comprobantesCP
 */
public Collection getComprobantesCP() {
	return comprobantesCP;
}
/**
 * @param comprobantesCP el comprobantesCP a establecer
 */
public void setComprobantesCP(Collection comprobantesCP) {
	this.comprobantesCP = comprobantesCP;
}

}

