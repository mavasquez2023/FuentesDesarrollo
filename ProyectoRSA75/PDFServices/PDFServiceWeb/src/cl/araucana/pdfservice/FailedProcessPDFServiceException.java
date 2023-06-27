

/*
 * @(#) FailedProcessPDFServiceException.java    1.0 18-10-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.pdfservice;


/**
 * Indicates that a PDF process identified by its <b>pid</b> has failed. 
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
 *            <TD> 18-10-2008 </TD>
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
public class FailedProcessPDFServiceException extends PDFServiceException {

	private static final long serialVersionUID = -302690329653342236L;
	
	private int pid;

    /**
     * Constructs a new exception with the specified <code>pid</code> and detail
     * message. The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     *
     * @param pid Failed process ID.
     * 
     * @param message  The detail message. The detail message is saved for 
     *        later retrieval by the {@link #getMessage()} method.
     */		
    public FailedProcessPDFServiceException(int pid, String message) {
		super(message);

		this.pid = pid;
    }

    /**
     * Gets failed process ID.
     * 
     * @return Failed process ID.
     */
    public int getFailedPID() {
		return pid;
	}
}
