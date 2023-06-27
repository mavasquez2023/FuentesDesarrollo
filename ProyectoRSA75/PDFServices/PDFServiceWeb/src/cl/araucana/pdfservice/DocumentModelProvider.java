

/*
 * @(#) DocumentModelProvider.java    1.0 22-04-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.pdfservice;


/**
 * Marker interface to identify PDF process's document model
 * provider components.
 *
 * <p> A <b>DocumentModelProvider (DMP)</b> is the PDF process component
 * responsible of to provide document model instances that will serve as input
 * to produce and to consume PDF document instances. For example, these
 * document model instances could be constructed from any data source; or
 * received from external synchronous or asynchronous mechanisms.
 * </p>
 * 
 * <p> Document model instances to process must be inserted as
 * {@link PDFRequest} in the corresponding PDF process's producer queue in order
 * to don't break pipeline execution. When no more production requests are
 * needed then a <code>null</code> production request must be inserted and
 * to finish.
 * </p>
 * 
 * <p> During the initialization of this component its parent {@link PDFProcess}
 * will provide configuration properties needed.
 * </p>
 * 
 * <p> <b>Note:</b> A <u>production request</u> (see {@link PDFRequest}) has
 * only one parameter corresponding to the document model instance to produce.
 * </p>
 * 
 * <BR>
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
 *            <TD> 22-04-2008 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Germ�n Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versi�n inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Germ�n Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public interface DocumentModelProvider extends PDFCommonInterface {
}
