

/*
 * @(#) PDFServiceException.java    1.0 22-04-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.pdfservice;


/**
 * This is the root exception class for operations supported by
 * {@link PDFService}.
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
public class PDFServiceException extends Exception {

	private static final long serialVersionUID = -4202970464513176975L;

    /**
     * Constructs a new exception with <code>null</code> as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */	
	public PDFServiceException() {
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message  The detail message. The detail message is saved for 
     *        later retrieval by the {@link #getMessage()} method.
     */		
    public PDFServiceException(String message) {
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
    public PDFServiceException(Throwable cause) {
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
    public PDFServiceException(String message, Throwable cause) {
    	super(message, cause);
    }
}
