

/*
 * @(#) PDFProducer.java    1.0 22-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice;


/**
 * Marker interface to identify PDF process's producer components.
 *
 * <p> A <b>PDFProducer</b> is the PDF process component responsible
 * of to process production requests from its queue. Each production
 * request contains a document model instance that will serve as
 * input to an ad-hoc {@link cl.araucana.fpg.PDFGenerator} instance associated
 * to a unique {@link cl.araucana.fpg.PDFTemplate} to generate corresponding
 * PDF document instance. Finally, this PDF document instance must be inserted
 * in the consumer queue as a consume request.
 * </p>
 * 
 * <p> When no more production requests are needed then a <code>null</code>
 * consume request must be inserted in the consumer queue and to finish.
 * </p>
 * 
 * <p> During the initialization of this component its parent {@link PDFProcess}
 * will provide configuration properties to init the
 * {@link cl.araucana.fpg.PDFGenerator} instance.
 * </p>
 * 
 * <p> <b>Notes:</b></p>
 * 
 * <ul>
 * <li> A <u>production request</u> (see {@link PDFRequest}) has only one
 *      parameter corresponding to the document model instance to produce.
 *      
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
public interface PDFProducer extends PDFCommonInterface {
}
