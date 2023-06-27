
/*
 * @(#) Logger.java    1.0 30-10-2006
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */
 
 
package cl.araucana.ea.edocs.logging;

import java.util.Date;


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
 *            <TD> 30-10-2006 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Germ�n Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versi�n inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
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
public class Logger {
	
	public static int MAX_MODULE_NAME_LENGTH = 20;
	
	private String moduleName;
	
	private LogManager logManager;
		
	public Logger(String moduleName) {
		if (moduleName.length() > MAX_MODULE_NAME_LENGTH) {
			this.moduleName = moduleName.substring(0, MAX_MODULE_NAME_LENGTH);			
		} else {
			int padLength = MAX_MODULE_NAME_LENGTH - moduleName.length();
			
			this.moduleName = moduleName;
						
			for (int i = 0; i < padLength; i++) {
				this.moduleName += " ";
			}
		}
		
		logManager = LogManager.getInstance();
	}

	public void logRequest(String request) {
		logManager.logRequest(request);		
	}

	public void logRequest(String request, Date requestDate) {
		logManager.logRequest(request, requestDate);		
	}

	public void logSession(int nSessions) {
		logManager.logSession(nSessions);
	}
			
	public void log(String message) {
		logManager.log(moduleName, message);		
	}

	public void log(Throwable throwable) {
		logManager.log(moduleName, throwable);		
	}
}
