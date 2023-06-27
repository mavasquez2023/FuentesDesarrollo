

/*
 * @(#) CustomPDFProcessListener.java    1.0 28-05-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.pdfservice.command;


import cl.araucana.pdfservice.PDFProcessEvent;
import cl.araucana.pdfservice.PDFProcessListener;


/**
 * Cutomized PDF Process listener used by some shell commands.
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
 *            <TD> 28-05-2008 </TD>
 *            <TD align="center">  2.0 </TD>
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
public class CustomPDFProcessListener implements PDFProcessListener {

	private PDFProcessEvent event;

	/**
	 * Constructs an empty process listener.
	 * 
	 */
	public CustomPDFProcessListener() {
	}

	public void processAborted(PDFProcessEvent event) {
		notified(event);
	}

	public void processEnded(PDFProcessEvent event) {
		notified(event);
	}

	/**
	 * Indicates if listener was notified previously.
	 * 
	 * @return <code>true</code> if lister was notified, otherwise
	 *         <code>false</code>.
	 */
	public boolean notified() {
		return event != null;
	}

	/**
	 * Gets PDF process event.
	 * 
	 * @return PDF process event.
	 */
	public PDFProcessEvent getEvent() {
		return event;
	}

	private void notified(PDFProcessEvent event) {
		synchronized (this) {
			this.event = event;

			notifyAll();
		}
	}
}
