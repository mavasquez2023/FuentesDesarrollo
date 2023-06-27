

/*
 * @(#) EstadisticaPagoTO.java    1.0 15-05-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.entidades.to;

import cl.araucana.core.business.TO.TransferObject;


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
 *            <TD> 15-05-2009 </TD>
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
public class EstadisticaPagoTO implements TransferObject{
private long totalCheque;
private long totalSPL;
private int periodo;
private int cantidadPagosCheque;
private int cantidadPagosSPL;
/**
 * @return el cantidadPagosCheque
 */
public int getCantidadPagosCheque() {
	return cantidadPagosCheque;
}
/**
 * @param cantidadPagosCheque el cantidadPagosCheque a establecer
 */
public void setCantidadPagosCheque(int cantidadPagosCheque) {
	this.cantidadPagosCheque = cantidadPagosCheque;
}
/**
 * @return el cantidadPagosSPL
 */
public int getCantidadPagosSPL() {
	return cantidadPagosSPL;
}
/**
 * @param cantidadPagosSPL el cantidadPagosSPL a establecer
 */
public void setCantidadPagosSPL(int cantidadPagosSPL) {
	this.cantidadPagosSPL = cantidadPagosSPL;
}
/**
 * @return el periodo
 */
public int getPeriodo() {
	return periodo;
}
/**
 * @param periodo el periodo a establecer
 */
public void setPeriodo(int periodo) {
	this.periodo = periodo;
}
/**
 * @return el totalCheque
 */
public long getTotalCheque() {
	return totalCheque;
}
/**
 * @param totalCheque el totalCheque a establecer
 */
public void setTotalCheque(long totalCheque) {
	this.totalCheque = totalCheque;
}
/**
 * @return el totalSPL
 */
public long getTotalSPL() {
	return totalSPL;
}
/**
 * @param totalSPL el totalSPL a establecer
 */
public void setTotalSPL(long totalSPL) {
	this.totalSPL = totalSPL;
}

}

