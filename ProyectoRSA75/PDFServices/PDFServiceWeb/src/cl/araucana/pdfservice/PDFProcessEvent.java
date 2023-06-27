

/*
 * @(#) PDFProcessEvent.java    1.0 25-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice;


/**
 * Encapsulates information about an aborted or ended {@link PDFProcess} event.
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
 *            <TD> 25-04-2008 </TD>
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
public class PDFProcessEvent {

	private int pid;
	private boolean aborted;
	private String message;

	/**
	 * Constructs an empty event instance.
	 * 
	 * @see #PDFProcessEvent(int, boolean, String)
	 */
	public PDFProcessEvent() {
	}

	/**
	 * Constructs an event instance with the specified <code>pid</code>,
	 * <code>aborted</code> control flag and detail <code>message</code>.
	 * 
	 * @param pid Process ID.
	 * 
	 * @param aborted Aborted control flag.
	 * 
	 * @param message Detail message.
	 * 
	 * @see #PDFProcessEvent()
	 */
	public PDFProcessEvent(int pid, boolean aborted, String message) {
		this.pid = pid;
		this.aborted = aborted;
		this.message = message;
	}

	/**
	 * Sets process ID.
	 * 
	 * @param pid Process ID.
	 * 
	 * @see #getPID()
	 */
	public void setPID(int pid) {
		this.pid = pid;
	}

	/**
	 * Gets process ID.
	 * 
	 * @return Process ID.
	 * 
	 * @see #setPID(int)
	 */
	public int getPID() {
		return pid;
	}

	/**
	 * Sets detail message.
	 * 
	 * @param message Detail message.
	 * 
	 * @see #getMessage()
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets detail message.
	 * 
	 * @return Detail message.
	 * 
	 * @see #setMessage(String)
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets <code>aborted</code> control flag.
	 * 
	 * @param aborted Aborted control flag.
	 */
	public void setAborted(boolean aborted) {
		this.aborted = aborted;
	}

	/**
	 * Indicates if associated PDF process was aborted or not.
	 * 
	 * @return <code>true</code> if associated PDF process was aborted,
	 *         otherwise <code>false</code>.
	 */
	public boolean wasAborted() {
		return aborted;
	}
}
