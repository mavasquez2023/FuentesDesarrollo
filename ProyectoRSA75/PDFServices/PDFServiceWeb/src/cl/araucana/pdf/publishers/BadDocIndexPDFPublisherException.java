

package cl.araucana.pdf.publishers;


/*
 * @(#) BadDocIndexPDFPublisherException.java    1.0 21-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


/**
 * This class denotes that an invalid PDF document ID was intented to use. A
 * document ID is a {@link java.lang.String} composed of multiples component
 * parts separated by character <b>'/'</b>. Number and type of these component
 * parts depends of the corresponding document type.
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
 *            <TD> 21-10-2008 </TD>
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
public class BadDocIndexPDFPublisherException extends PDFPublisherException {

	private static final long serialVersionUID = -5219573462880713908L;

    /**
     * Constructs a new exception with <code>null</code> as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */	
	public BadDocIndexPDFPublisherException() {
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message  The detail message. The detail message is saved for 
     *        later retrieval by the {@link #getMessage()} method.
     */	
    public BadDocIndexPDFPublisherException(String message) {
    	super(message);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of <tt>(cause==null ? null : cause.toString())</tt> (which
     * typically contains the class and detail message of <tt>cause</tt>).
     * 
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables.
     *
     * @param  cause The cause (which is saved for later retrieval by the
     *         {@link #getCause()} method). (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown)
     */    
    public BadDocIndexPDFPublisherException(Throwable cause) {
    	super(cause);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.
     * 
     * <p> Note that the detail message associated with
     * <code>cause</code> is <i>not</i> automatically incorporated in
     * this exception's detail message.
     * </p>
     *
     * @param  message The detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     *         
     * @param  cause The cause (which is saved for later retrieval by the
     *         {@link #getCause()} method). (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown)
     */    
    public BadDocIndexPDFPublisherException(String message, Throwable cause) {
    	super(message, cause);
    }
}
