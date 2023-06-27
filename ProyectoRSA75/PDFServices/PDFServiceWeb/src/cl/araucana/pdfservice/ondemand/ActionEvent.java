

/*
 * @(#) ActionEvent.java    1.0 24-06-2009
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.pdfservice.ondemand;


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
 *            <TD align="center"> <B>Versi�n</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripci�n</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 24-06-2009 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Germ�n Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versi�n inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author  Germ�n Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class ActionEvent {

	public static final int CONSUMED         = 0;
	public static final int UNKNOWN_USER     = 1;
	public static final int UNKNOWN_SYSTEM   = 2;
	public static final int UNKNOWN_DOCTYPE  = 3;
	public static final int UNKNOWN_RESOURCE = 4;
	public static final int MISSED_DOCID     = 5;	
	public static final int ACCESS_DENIED    = 6;
	public static final int DMP_FAILED       = 7;
	public static final int RESOURCE_FAILED  = 8;

	private static final String[] messages = {
		"Consumed",
		"Unknown user",
		"Unknown system",
		"Unknown document type",
		"Unknown resource",
		"Missed document ID",
		"Access denied",
		"DMP failed",
		"Resource failed"
	};
	
	private int reason;
	private String cause;
	private String docType;
	private Action action;
	
	public ActionEvent() {
	}

	public ActionEvent(int reason, String cause, String docType,
			Action action) {
		
		this.reason = reason;
		this.cause = cause;
		this.docType = docType;
		this.action = action;
	}
	
	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	public String getCause() {
		return cause;
	}
	
	public void setCause(String cause) {
		this.cause = cause;
	}
	
	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}
	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public String getMessage() {
		return messages[reason];
	}
	
	public static String getMessage(int reason) {
		return messages[reason];
	}	
}
