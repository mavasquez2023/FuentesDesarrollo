
/*
 * @(#) LogManager.java    1.0 30-10-2006
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
 

package cl.araucana.ea.edocs.logging;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


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
 *            <TD> 30-10-2006 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
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
 * @author Germán Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class LogManager {

	public static String APP_NAME = "application";
	public static String LOG_DIR = "/application/logs";	
	
	private static String LOG_APP_PREFIX = APP_NAME + ": ";
	private static LogManager instance;

	private PrintWriter logWriter;
	private PrintWriter requestWriter;
	private PrintWriter sessionWriter;	

	private Calendar calendar = new GregorianCalendar();
	private NumberFormat nf2 = new DecimalFormat("00");
	private NumberFormat nf4 = new DecimalFormat("0000");	

	private LogManager() {
		
		File logDir = new File(LOG_DIR);
		
		if (!logDir.exists()) {
			if (!logDir.mkdirs()) {
				System.err.println(
						  LOG_APP_PREFIX
						+ "Cannot create log dir '" + LOG_DIR + "'.");
				
				return;
			}
		}

		try {
			String appName = APP_NAME.toLowerCase();
			
			logWriter =
					new PrintWriter(
							new FileWriter(
									LOG_DIR + "/" + appName + ".log",
									true),
							true);		
		} catch (IOException e) {
			System.err.println(
					  LOG_APP_PREFIX			
					+ "Cannot create log file "
					+ "'" + LOG_DIR + "/" + APP_NAME.toLowerCase() + ".log'. "
					+ "[" + e.getMessage() + "]");					
		}

		try {
			requestWriter =
					new PrintWriter(
							new FileWriter(LOG_DIR + "/requests.log", true),
							true);		
		} catch (IOException e) {
			System.err.println(
					  LOG_APP_PREFIX
					+ "Cannot create log file "
					+ "'" + LOG_DIR + "/requests.log'. "
					+ "[" + e.getMessage() + "]");
		}
		
		try {
			sessionWriter =
					new PrintWriter(
							new FileWriter(LOG_DIR + "/sessions.log", true),
							true);		
		} catch (IOException e) {
			System.err.println(
					  LOG_APP_PREFIX
					+ "Cannot create log file "
					+ "'" + LOG_DIR + "/sessions.log'. "
					+ "[" + e.getMessage() + "]");
		}
	}
	
	public synchronized static LogManager getInstance() {
		if (instance == null) {
			instance = new LogManager();
		}
		
		return instance;
	}
	
	public void log(String moduleName, String message) {
		if (logWriter != null) {
			logWriter.println(
					getDateTime() + " " + moduleName + " " + message);
		}
	}

	public void log(String moduleName, Throwable throwable) {
		
		log(moduleName, "Exception thrown:");
		
		if (logWriter != null) {
			throwable.printStackTrace(logWriter);			
		}
	}

	public void logRequest(String request) {
		logRequest(request, new Date());		
	}
		
	public void logRequest(String request, Date requestDate) {
		if (requestWriter != null) {
			requestWriter.println(
					getDateTime(requestDate, ",") + "," + request);
		}
	}

	public void logSession(int nSessions) {
		if (sessionWriter != null) {
			sessionWriter.println(getDateTime(",") + "," + nSessions);
		}
	}

	private synchronized String getDateTime() {
		return getDateTime(new Date());
	}
	
	private synchronized String getDateTime(String separator) {
		return getDateTime(new Date(), separator);
	}
	
	private synchronized String getDateTime(Date date) {
		return getDateTime(date, " ");
	}
		
	private synchronized String getDateTime(Date date, String separator) {
		calendar.setTime(date);
		
		return
				  nf2.format(calendar.get(Calendar.DAY_OF_MONTH)) + "/"
				+ nf2.format(calendar.get(Calendar.MONTH) + 1) + "/"
				+ nf4.format(calendar.get(Calendar.YEAR)) + separator
				+ nf2.format(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
				+ nf2.format(calendar.get(Calendar.MINUTE)) + ":"
				+ nf2.format(calendar.get(Calendar.SECOND));
	}
	
	public void close() {
		if (logWriter != null) {
			logWriter.close();
			logWriter = null;						
		}

		if (requestWriter != null) {
			requestWriter.close();
			requestWriter = null;						
		}

		if (sessionWriter != null) {
			sessionWriter.close();
			sessionWriter = null;						
		}
	}
}
