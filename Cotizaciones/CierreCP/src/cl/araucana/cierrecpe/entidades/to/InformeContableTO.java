

/*
 * @(#) InformeContableTO.java    1.0 11-12-2012
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.entidades.to;

import cl.araucana.core.business.TO.TransferObject;
import cl.araucana.core.util.Rut;


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
 *            <TD> 11-12-2012 </TD>
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
public class InformeContableTO implements TransferObject {
private int periodo;
private String clave;
private int conceptoTesoreria;
private Rut rutEntidad;
private int folioEgreso;
private char tipoNomina;
private long montoEgreso;
private Rut rutEmpresa;
private int folioIngreso;
private long montoIngreso;
/**
 * @return el clave
 */
public String getClave() {
	return clave;
}
/**
 * @param clave el clave a establecer
 */
public void setClave(String clave) {
	this.clave = clave;
}
/**
 * @return el conceptoTesoreria
 */
public int getConceptoTesoreria() {
	return conceptoTesoreria;
}
/**
 * @param conceptoTesoreria el conceptoTesoreria a establecer
 */
public void setConceptoTesoreria(int conceptoTesoreria) {
	this.conceptoTesoreria = conceptoTesoreria;
}
/**
 * @return el folioEgreso
 */
public int getFolioEgreso() {
	return folioEgreso;
}
/**
 * @param folioEgreso el folioEgreso a establecer
 */
public void setFolioEgreso(int folioEgreso) {
	this.folioEgreso = folioEgreso;
}
/**
 * @return el folioIngreso
 */
public int getFolioIngreso() {
	return folioIngreso;
}
/**
 * @param folioIngreso el folioIngreso a establecer
 */
public void setFolioIngreso(int folioIngreso) {
	this.folioIngreso = folioIngreso;
}
/**
 * @return el montoEgreso
 */
public long getMontoEgreso() {
	return montoEgreso;
}
/**
 * @param montoEgreso el montoEgreso a establecer
 */
public void setMontoEgreso(long montoEgreso) {
	this.montoEgreso = montoEgreso;
}
/**
 * @return el montoIngreso
 */
public long getMontoIngreso() {
	return montoIngreso;
}
/**
 * @param montoIngreso el montoIngreso a establecer
 */
public void setMontoIngreso(long montoIngreso) {
	this.montoIngreso = montoIngreso;
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
 * @return el rutEmpresa
 */
public Rut getRutEmpresa() {
	return rutEmpresa;
}
/**
 * @param rutEmpresa el rutEmpresa a establecer
 */
public void setRutEmpresa(Rut rutEmpresa) {
	this.rutEmpresa = rutEmpresa;
}
/**
 * @return el rutEntidad
 */
public Rut getRutEntidad() {
	return rutEntidad;
}
/**
 * @param rutEntidad el rutEntidad a establecer
 */
public void setRutEntidad(Rut rutEntidad) {
	this.rutEntidad = rutEntidad;
}
/**
 * @return el tipoNomina
 */
public char getTipoNomina() {
	return tipoNomina;
}
/**
 * @param tipoNomina el tipoNomina a establecer
 */
public void setTipoNomina(char tipoNomina) {
	this.tipoNomina = tipoNomina;
}
}

