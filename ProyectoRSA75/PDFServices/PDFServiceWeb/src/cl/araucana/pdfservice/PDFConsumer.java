

/*
 * @(#) PDFConsumer.java    1.0 22-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice;


/**
 * Marker interface to identify PDF process's consumer components.
 *
 * <p> A <b>PDFConsumer</b> is the PDF process component responsible
 * of to process consume requests from its queue. Each consume
 * request contains a PDF document instance that can be consumed as
 * needed. For example, signing it, saving it to disk, publishining it
 * in a indexed documental repository, printing it, and so on (@see
 * cl.araucana.pdfservice.consumers} package for available implementations).
 * </p>
 * 
 * <p> When no more consume requests are needed (<code>null</code> consume
 * request) then this component must to finish.
 * </p>
 * 
 * <p> During the initialization of this component its parent {@link PDFProcess}
 * will provide configuration properties needed.
 * </p>
 * 
 * <p> <b>Note:</b></p>
 * 
 * <ul>
 * <li> A <u>consume request</u> (see {@link PDFRequest}) has the following
 *      two parameters:
 *      <b>(1)</b> document model instance to produce and
 *      <b>(2)</b> PDF document instance to consume.
 * </ul>
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
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 22-04-2008 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Germán Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public interface PDFConsumer extends PDFCommonInterface {
}
