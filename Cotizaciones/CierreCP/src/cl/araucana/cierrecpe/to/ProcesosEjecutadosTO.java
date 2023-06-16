

/*
 * @(#) ProcesosActivosTO.java    1.0 29/03/2011
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.cierrecpe.to;


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
 *            <TD align="center"> <B>Versi�n</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripci�n</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 29/03/2011 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZOR�N <BR> clillo@laaraucana.cl </TD>
 *            <TD> Versi�n inicial. </TD>
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
 * @author CLAUDIO LILLO AZOR�N (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class ProcesosEjecutadosTO {
private int periodo;
private int cierre;
private String proceso;
private String inicio;
private String termino;
private double duracion;
/**
 * @return el cierre
 */
public int getCierre() {
	return cierre;
}
/**
 * @param cierre el cierre a establecer
 */
public void setCierre(int cierre) {
	this.cierre = cierre;
}
/**
 * @return el inicio
 */
public String getInicio() {
	return inicio;
}
/**
 * @param inicio el inicio a establecer
 */
public void setInicio(String inicio) {
	this.inicio = inicio;
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
 * @return el proceso
 */
public String getProceso() {
	return proceso;
}
/**
 * @param proceso el proceso a establecer
 */
public void setProceso(String proceso) {
	this.proceso = proceso;
}
/**
 * @return el termino
 */
public String getTermino() {
	return termino;
}
/**
 * @param termino el termino a establecer
 */
public void setTermino(String termino) {
	this.termino = termino;
}
/**
 * @return el duracion
 */
public double getDuracion() {
	return duracion;
}
/**
 * @param duracion el duracion a establecer
 */
public void setDuracion(double duracion) {
	this.duracion = duracion;
}
}

