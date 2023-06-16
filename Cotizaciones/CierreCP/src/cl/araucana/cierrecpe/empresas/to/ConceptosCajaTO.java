

/*
 * @(#) ConceptosCajaTO.java    1.0 20-01-2016
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.to;


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
 *            <TD> 20-01-2016 </TD>
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
public class ConceptosCajaTO {
private long asfam;
private long aporte;
private long credito;
private long leasing;
private long vida;
private long dental;
private long sfe;
private String caja;
/**
 * @return el aporte
 */
public long getAporte() {
	return aporte;
}
/**
 * @param aporte el aporte a establecer
 */
public void setAporte(long aporte) {
	this.aporte = aporte;
}
/**
 * @return el asfam
 */
public long getAsfam() {
	return Math.abs(asfam)*-1;
}
/**
 * @param asfam el asfam a establecer
 */
public void setAsfam(long asfam) {
	this.asfam = asfam;
}
/**
 * @return el credito
 */
public long getCredito() {
	return credito;
}
/**
 * @param credito el credito a establecer
 */
public void setCredito(long credito) {
	this.credito = credito;
}
/**
 * @return el dental
 */
public long getDental() {
	return dental;
}
/**
 * @param dental el dental a establecer
 */
public void setDental(long dental) {
	this.dental = dental;
}
/**
 * @return el leasing
 */
public long getLeasing() {
	return leasing;
}
/**
 * @param leasing el leasing a establecer
 */
public void setLeasing(long leasing) {
	this.leasing = leasing;
}
/**
 * @return el sfe
 */
public long getSfe() {
	return Math.abs(sfe)*-1;
}
/**
 * @param sfe el sfe a establecer
 */
public void setSfe(long sfe) {
	this.sfe = sfe;
}
/**
 * @return el vida
 */
public long getVida() {
	return vida;
}
/**
 * @param vida el vida a establecer
 */
public void setVida(long vida) {
	this.vida = vida;
}
/**
 * @return el caja
 */
public String getCaja() {
	return caja;
}
/**
 * @param caja el caja a establecer
 */
public void setCaja(String caja) {
	this.caja = caja;
}
}

