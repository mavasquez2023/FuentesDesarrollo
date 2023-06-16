

/*
 * @(#) CompensacionCaja.java    1.0 21-03-2012
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.entidades.to;


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
 *            <TD> 21-03-2012 </TD>
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
public class EntidadCCAFTO {
private int idCCAF;
private boolean compensaAporte=false;
private boolean compensaAsfam=false;
private boolean compensaCredito=false;
private boolean compensaLeasing=false;
private boolean compensaDental=false;
private boolean compensaSeguro=false;
/**
 * @return el compensaAporte
 */
public boolean isCompensaAporte() {
	return compensaAporte;
}
/**
 * @param compensaAporte el compensaAporte a establecer
 */
public void setCompensaAporte(boolean compensaAporte) {
	this.compensaAporte = compensaAporte;
}
/**
 * @return el compensaAsfam
 */
public boolean isCompensaAsfam() {
	return compensaAsfam;
}
/**
 * @param compensaAsfam el compensaAsfam a establecer
 */
public void setCompensaAsfam(boolean compensaAsfam) {
	this.compensaAsfam = compensaAsfam;
}
/**
 * @return el compensaCredito
 */
public boolean isCompensaCredito() {
	return compensaCredito;
}
/**
 * @param compensaCredito el compensaCredito a establecer
 */
public void setCompensaCredito(boolean compensaCredito) {
	this.compensaCredito = compensaCredito;
}
/**
 * @return el compensaDental
 */
public boolean isCompensaDental() {
	return compensaDental;
}
/**
 * @param compensaDental el compensaDental a establecer
 */
public void setCompensaDental(boolean compensaDental) {
	this.compensaDental = compensaDental;
}
/**
 * @return el compensaLeasing
 */
public boolean isCompensaLeasing() {
	return compensaLeasing;
}
/**
 * @param compensaLeasing el compensaLeasing a establecer
 */
public void setCompensaLeasing(boolean compensaLeasing) {
	this.compensaLeasing = compensaLeasing;
}
/**
 * @return el compensaSeguro
 */
public boolean isCompensaSeguro() {
	return compensaSeguro;
}
/**
 * @param compensaSeguro el compensaSeguro a establecer
 */
public void setCompensaSeguro(boolean compensaSeguro) {
	this.compensaSeguro = compensaSeguro;
}
/**
 * @return el idCCAF
 */
public int getIdCCAF() {
	return idCCAF;
}
/**
 * @param idCCAF el idCCAF a establecer
 */
public void setIdCCAF(int idCCAF) {
	this.idCCAF = idCCAF;
}

}

