

/*
 * @(#) DocumentIndexer.java    1.0 30-10-2006
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.ea.edocs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
public abstract class DocumentIndexer extends Thread implements TrackableTask {

	private DocumentType documentType;

	private String sourceDir;
	private String indexBaseDir;
	private IndexControl control;

	private int status = READY;
	private String abortMessage;
	private int workUnits;
	private int workUnitsDone;
	private Date beginTime;
	private Date endTime;
	
	private boolean cancel;
	
	private FileLoader fileLoader = new FileLoader();
		
	private File[] entries;
	private File currentEntry;
	
	private Map members = new TreeMap();
	private int nReadedRecords;
	
	protected Logger logger;
	
	protected DocumentIndexer(String documentTypeName, String sourceDir,
			String indexBaseDir) {
	
		this(documentTypeName, sourceDir, indexBaseDir, new IndexControl());
	}
		
	protected DocumentIndexer(String documentTypeName, String sourceDir,
			String indexBaseDir, IndexControl control) {
				
		documentType = DocumentTypes.getDocumentType(documentTypeName);
		
		this.sourceDir = sourceDir;
		this.indexBaseDir = indexBaseDir;
		this.control = control;
	}

	public String getDocumentTypeName() {
		return documentType.getName();
	}
	
	public DocumentType getDocumentType() {
		return documentType;
	}
	
	public IndexControl getIndexControl() {
		return control;
	}
	
	public String getKeyFieldValue(int index, String text) {
		int[] expandedKeyField = documentType.getExpandedKeyFields()[index];
		int offset = expandedKeyField[DocumentType.KEYFIELD_OFFSET];
		int length = expandedKeyField[DocumentType.KEYFIELD_LENGTH];
		
		return text.substring(offset, offset + length);
	}
	
	public int getStatus() {
		return status;
	}

	public String getAbortMessage() {
		return abortMessage;
	}

	public int getWorkUnits() {
		return workUnits;
	}

	public int getWorkUnitsDone() {
		return workUnitsDone;
	}

	public float getWorkPercentDone() {
		if (workUnits == 0) {
			return 0.0f;
		}
		
		return 100.0f * workUnitsDone / workUnits;
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
	
	public void cancel() {
		cancel = true;
	}
	
	public String getSourceDir() {
		return sourceDir;
	}

	public String getIndexBaseDir() {
		return indexBaseDir;
	}
	
	public Map getMembers() {
		return members;
	}
	
	public void run() {
		beginTime = new Date();
		
		logger = new Logger(documentType.getName() + "Indexer");
		
		logger.log("Starting ...");

		logger.log("indexBaseDir = " + indexBaseDir);
		logger.log("sourceDir = " + sourceDir);

		File dir = new File(indexBaseDir);
		
		if (!dir.isDirectory()) {
			abort("'" + indexBaseDir + "' is not a directory");
			
			return;
		}
		
		dir = new File(sourceDir);
		
		if (!dir.isDirectory()) {
			abort("'" + sourceDir + "' is not a directory");
			
			return;
		}
		
		entries = dir.listFiles();
		
		Arrays.sort(entries);
		
		workUnits = entries.length;
		status = RUNNING;

		try {
			
			// Control #1: Number of Files.
			if (control.getNFiles() != 0
					&& control.getNFiles() != entries.length) {
						
				abort(
						  "Control #1: Unexpected number of files. "
						+ "expected=" + control.getNFiles() + ", "
						+ "readed=" + entries.length);
						
				return;
			}
			
			execute();

			// Control #2: Number of Records.
			if (control.getNRecords() != 0
					&& control.getNRecords() != nReadedRecords) {
						
				abort(
						  "Control #2: Unexpected number of records. "
						+ "expected=" + control.getNRecords() + ", "
						+ "readed=" + nReadedRecords);
						
				return;
			}

			if (status == RUNNING) {
				endTime = new Date();
				status = ENDED;
			}
			
			logger.log(
					  "Ended. "
					+ workUnitsDone + " documents "
					+ "(" + nReadedRecords + " records) were processed. "
					+ "[time=" + (finalExecutionTime() / 1000L) + "s]");			
		} catch(Exception e) {
			if (currentEntry != null) {
				logger.log(
						"Exception ocurred processing '" + currentEntry + "'.");
			}
			
			logger.log(e);
			abort(e.getMessage());
		}
	}

	public void abort(String message) {	
		endTime = new Date();
		status = ABORTED;
			
		logger.log("*** ABORTED ***  [" + message + "]");
	}
	
	public void execute() throws Exception {
		
		currentEntry = null;
		
		// FULL.
		// for (int i = 0; i < Math.min(entries.length, 50); i++) {
		for (int i = 0; i < entries.length; i++) {
			currentEntry = entries[i];
			
			if (cancel) {
				throw new Exception("Cancelled by user/system intervention.");	
			}

			Document document = getDocument(currentEntry);

			if (document != null) {
				addDocument(document);
				saveDocument(document, currentEntry);
			} else {
				logger.log(
						  "Warning, unexpected first line in file "
						+ "'" + currentEntry + "'. File was skipped.");
			}
			
			workUnitsDone++;
			
			if ((workUnitsDone % 100) == 0) {
				Thread.yield();
			}
		}
	}
	
	public abstract Document getDocument(File file) throws IOException;
	
	public void addDocument(Document document) throws IOException {
		addDocument(document, -1);
	}
	
	public void addDocument(Document document, int index) throws IOException {

		Integer id = new Integer(document.getMemberId());
		List documents =(List) members.get(id);

		if (documents == null) {
			String dirName = indexBaseDir + "/" + id;
			File dir = new File(dirName);
		
			if (dir.exists()) {
				logger.log("Warning, directory '" + dirName + "' exists.");
			}
			
			// Rare errors in mkdirs()??
			int maxRetries = 5;
			int retries = 0;

			while (!dir.mkdirs()) {
				retries++;
				
				if (retries == maxRetries) {
					throw new IOException(
							  "Cannot add index member '" + id + "' "
							+ "to directory '" + dirName + "'.");
				}
				
				logger.log(
						"Warning, make directory '" + dirName + "' retry ...");

				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {}
			}
			
			documents = new ArrayList();
			
			members.put(id, documents);
		}
		
		if (index == -1) {
			documents.add(document);
		} else {
			documents.add(index, document);
		}
	}
	
	protected String getFirstDocumentLine(File file) throws IOException {
		
		BufferedReader reader = null;
		String line = null;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			line = reader.readLine();
		} catch(IOException e) {
			throw e;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch(IOException e) {}
			}
		}
		
		return (line != null && line.length() == documentType.getRecordLength())
				? line
				: null;
	}
	
	public void saveDocument(Document document, File file)	throws IOException {

		Integer id = new Integer(document.getMemberId());
		List documents =(List) members.get(id);

		if (documents == null) {
			throw new IOException("Unknown document member id '" + id + "'.");
		}
		
		String DocumentFileName =
				indexBaseDir + "/" + id + "/" + documents.size();
		
		fileLoader.setFileName(file.getPath());
		
		Collection lines = fileLoader.loadLines();
		Iterator iterator = lines.iterator();

		FileWriter txtWriter = null;
		FileWriter csvWriter = null;
		
		try {				
			txtWriter = new FileWriter(DocumentFileName + ".txt");
			csvWriter = new FileWriter(DocumentFileName + ".csv");
			
			final String lineSeparator = "\r\n";
			int recordLength = documentType.getRecordLength();			
			int lineNo = 0;
			
			while (iterator.hasNext()) {
				String line = (String) iterator.next();

				lineNo++;
				
				/*
				 * Checks record length. Skip possible "EOT" in the last line.
				 */
				if (line.length() == recordLength) {
					nReadedRecords++;
									
					txtWriter.write(line + lineSeparator);
					csvWriter.write(formatCSVFields(line) + lineSeparator);
				} else if (line.length() > 3 || lineNo != lines.size()) {
					throw new IOException(
							  "Unexpected record length "
							+ "'" + line.length() + "' "
							+ "at record '" + lineNo + "'");
				}
			}
		} catch(IOException e) {
			throw e;		
		} finally {
			if (txtWriter != null) {
				try {
					txtWriter.close();
				} catch(IOException e) {}
			}

			if (csvWriter != null) {
				try {
					csvWriter.close();
				} catch(IOException e) {}
			}
		}
	}
	
	private String formatCSVFields(String line) {
		int[] fieldLengths = documentType.getFieldLengths();
		StringBuffer formattedLine =
				new StringBuffer(line.length() + fieldLengths.length - 1);
				
		int fromIndex = 0;
		
		for (int i = 0; i < fieldLengths.length; i++) {
			String field =
					line.substring(fromIndex, fromIndex + fieldLengths[i]);
			
			// Filters commands.
			field = field.replaceAll(",", " ");
			
			formattedLine.append(field);
			
			if (i + 1 < fieldLengths.length) {
				formattedLine.append(",");
			}
			
			fromIndex += fieldLengths[i];
		}
		
		return formattedLine.toString();
	}
	
	public void waitEnd(long timeRetry) {
		while (status != ABORTED && status != ENDED && status != SUSPENDED) {
			try {
				Thread.sleep(timeRetry);
			} catch(InterruptedException e) {}
		}
	}
	
	private int getPeriod(long time) {
		Calendar calendar = new GregorianCalendar();
		
		calendar.setTime(new Date(time));
		return 100 * calendar.get(Calendar.YEAR)
				+ calendar.get(Calendar.MONTH) + 1;
	}
}
