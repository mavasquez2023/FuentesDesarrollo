

/*
 * @(#) ComprobantesTO.java    1.0 25/02/2010
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.to.tgr;

import java.util.Collection;

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
 *            <TD> 25/02/2010 </TD>
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
public class ComprobantesTO {
private Rut rutEmpresa;
private int convenio;
private long idCodigoBarra;
private String tipoNomina;
private Collection entidades;
private int periodo;
private int folioTesoreria;
/**
 * @return el convenio
 */
public int getConvenio() {
	return convenio;
}
/**
 * @param convenio el convenio a establecer
 */
public void setConvenio(int convenio) {
	this.convenio = convenio;
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
 * @return el idCodigoBarra
 */
public long getIdCodigoBarra() {
	return idCodigoBarra;
}
/**
 * @param idCodigoBarra el idCodigoBarra a establecer
 */
public void setIdCodigoBarra(long idCodigoBarra) {
	this.idCodigoBarra = idCodigoBarra;
}
/**
 * @return el tipoNomina
 */
public String gettipoNomina() {
	return tipoNomina;
}
/**
 * @param tipoNomina el tipoNomina a establecer
 */
public void settipoNomina(String tipoNomina) {
	this.tipoNomina = tipoNomina;
}
/**
 * @return el entidades
 */
public Collection getEntidades() {
	return entidades;
}
/**
 * @param entidades el entidades a establecer
 */
public void setEntidades(Collection entidades) {
	this.entidades = entidades;
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
 * @return el folioTesoreria
 */
public int getFolioTesoreria() {
	return folioTesoreria;
}
/**
 * @param folioTesoreria el folioTesoreria a establecer
 */
public void setFolioTesoreria(int folioTesoreria) {
	this.folioTesoreria = folioTesoreria;
}

}

