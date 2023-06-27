

/*
 * @(#) ActionEvent.java    1.0 24-06-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
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
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 24-06-2009 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author  Germán Pavez I. (gpavez@hotmail.com)
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
