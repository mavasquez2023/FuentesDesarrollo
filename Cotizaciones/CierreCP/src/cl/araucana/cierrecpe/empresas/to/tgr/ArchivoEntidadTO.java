

/*
 * @(#) ArchivoEntidadTO.java    1.0 14/04/2010
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.to.tgr;

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
 *            <TD> 14/04/2010 </TD>
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
public class ArchivoEntidadTO {
private String tipoSeccion;
private Collection tipoDetalle;
private String formato;
/**
 * @return el formato
 */
public String getFormato() {
	return formato;
}
/**
 * @param formato el formato a establecer
 */
public void setFormato(String formato) {
	this.formato = formato;
}
/**
 * @return el tipoDetalle
 */
public Collection getTipoDetalle() {
	return tipoDetalle;
}
/**
 * @param tipoDetalle el tipoDetalle a establecer
 */
public void setTipoDetalle(Collection tipoDetalle) {
	this.tipoDetalle = tipoDetalle;
}
/**
 * @return el tipoSeccion
 */
public String getTipoSeccion() {
	return tipoSeccion;
}
/**
 * @param tipoSeccion el tipoSeccion a establecer
 */
public void setTipoSeccion(String tipoSeccion) {
	this.tipoSeccion = tipoSeccion;
}


}

