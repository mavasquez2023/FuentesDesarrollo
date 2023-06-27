

/*
 * @(#) ContadorTO.java    1.0 05-07-2012
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
 *            <TD> 05-07-2012 </TD>
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
public class ContadorTO {
private int countPrevio;
private int countPost;
private int countInsert;
/**
 * @return el countInsert
 */
public int getCountInsert() {
	return countInsert;
}
/**
 * @param countInsert el countInsert a establecer
 */
public void setCountInsert(int countInsert) {
	this.countInsert = countInsert;
}
/**
 * @return el countPost
 */
public int getCountPost() {
	return countPost;
}
/**
 * @param countPost el countPost a establecer
 */
public void setCountPost(int countPost) {
	this.countPost = countPost;
}
/**
 * @return el countPrevio
 */
public int getCountPrevio() {
	return countPrevio;
}
/**
 * @param countPrevio el countPrevio a establecer
 */
public void setCountPrevio(int countPrevio) {
	this.countPrevio = countPrevio;
}
}

