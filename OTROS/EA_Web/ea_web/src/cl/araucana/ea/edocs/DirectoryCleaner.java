
/*
 * @(#) DirectoryCleaner.java    1.0 30-10-2006
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.ea.edocs;


import java.io.File;
import java.io.IOException;
import java.util.Date;

import cl.araucana.ea.edocs.logging.Logger;


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
public class DirectoryCleaner extends Thread implements TrackableTask {

	private File directory;
	private int status = READY;
	private String abortMessage;
	private int workUnitsDone;
	private Date beginTime;
	private Date endTime;

	private final Logger logger = new Logger("DirectoryCleaner");

	public DirectoryCleaner(File directory) {
		this.directory = directory;
	}
		
	public DirectoryCleaner(File directory, int priority) {
		this(directory);
		
		setPriority(priority);
	}

	public static File renameToDelete(String dirName) {
		return renameToDelete(new File(dirName));
	}
	
	public static File renameToDelete(File dir) {
		long currentSecond = (System.currentTimeMillis() / 1000) % 8600L;
		File auxDir = new File(dir.getPath() + "." + currentSecond);
		
		if (dir.renameTo(auxDir)) {
			return auxDir;		
		}
		
		return null;
	}

	public static boolean renameAndCleanup(String dirName) {
		return renameAndCleanup(new File(dirName));
	}
	
	public static boolean renameAndCleanup(File dir) {
		File dirToDelete = DirectoryCleaner.renameToDelete(dir);
		
		if (dirToDelete != null) {
			DirectoryCleaner cleaner = new DirectoryCleaner(dirToDelete);
								
			cleaner.start();
			
			return true;
		}
		
		return false;
	}
	
	public int getStatus() {
		return status;
	}

	public String getAbortMessage() {
		return abortMessage;
	}

	public int getWorkUnits() {
		return 0;
	}

	public int getWorkUnitsDone() {
		return workUnitsDone;
	}

	public float getWorkPercentDone() {
		return 0.0f;
	}
	
	public Date getBeginTime() {
		return beginTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}	

	public long elapsedTime() {
		if (endTime != null ) {
			return finalExecutionTime();
		}
		
		if (beginTime == null) {
			return 0L;
		}
		
		Date now = new Date();
		
		return now.getTime() - beginTime.getTime();
	}

	public long finalExecutionTime() {
		return endTime.getTime() - beginTime.getTime();
	}

	public void waitEnd(long timeRetry) {
		while (status != ABORTED && status != ENDED && status != SUSPENDED) {
			try {
				Thread.sleep(timeRetry);
			} catch(InterruptedException e) {}
		}
	}
	
	public void run() {
		beginTime = new Date();
		status = RUNNING;
		
		try {
			logger.log("Starting cleanup directory '" + directory + "'...");
					
			cleanup();

			endTime = new Date();
			status = ENDED;

			logger.log(
					  "Cleanup directory '" + directory + "' ended. "
					+ "[" + (elapsedTime() / 1000L) + "s]");
		} catch(IOException e) {
			endTime = new Date();
			status = ABORTED;
			abortMessage = e.getMessage();

			logger.log(
					  "Cleanup directory '" + directory + "' aborted. "
					+ "[" + abortMessage + "]");
		}
	}
	
	private void cleanup() throws IOException {
		
		cleanup(directory);
		
		if (!directory.delete()) {
			throw new IOException(
					  "Cannot delete directory '" + directory + "'.");
		}
	}
		
	private void cleanup(File baseDir) throws IOException {
		File[] entries = baseDir.listFiles();
		
		for (int i = 0; i < entries.length; i++) {
			if (entries[i].isFile()) {
				if (!entries[i].delete()) {
					throw new IOException(
							  "Delete failed to file '" + entries[i] + "'.");
				}
			} else if (entries[i].isDirectory()) {
				cleanup(entries[i]);

				if (!entries[i].delete()) {
					throw new IOException(
							  "Delete failed to dir '" + entries[i] + "'.");
				}
			}
			
			workUnitsDone++;
		}	
	}
}
