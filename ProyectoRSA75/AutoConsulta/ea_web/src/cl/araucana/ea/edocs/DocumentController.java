
/*
 * @(#) DocumentController.java    1.0 30-10-2006
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.ea.edocs;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
public class DocumentController {

	private static DocumentController instance = null;
	
	private final Logger logger = new Logger("DocController");
	
	private String documentBaseDir;
	private DocumentType[] documentTypes = DocumentTypes.documentTypes;
	private Map publishedPeriods = new TreeMap();
	private Map documentType_PeriodMap = new HashMap();
		
	private DocumentController(String documentBaseDir) {
		this.documentBaseDir = documentBaseDir;
		
		int currentPeriod = getCurrentPeriod();
		
		logger.log("currentPeriod = " + currentPeriod);
		logger.log("previousPeriod = " + getPreviousPeriod(currentPeriod));
		logger.log("documentBaseDir = " + documentBaseDir);
		
		loadAvailablePeriods();
	}
	
	public synchronized static DocumentController getInstance(
			String documentBaseDir) {
				
		if (instance == null) {
			instance = new DocumentController(documentBaseDir);
		}
		
		return instance;
	}

	public static DocumentController getInstance() {
		return instance;
	}
	
	protected String getDocumentBaseDir() {
		return documentBaseDir;
	}
	
	private void loadAvailablePeriods() {
		for (int i = 0; i < documentTypes.length; i++) {
			DocumentType documentType = documentTypes[i];
			String documentTypeName = documentType.getName();
			File documentTypeDir =
					new File(documentBaseDir + "/" + documentTypeName);
					
			if (!documentTypeDir.exists()) {
				if (!documentTypeDir.mkdir()) {
					logger.log("Cannot mkdir '" + documentTypeDir + "'.");
					
					continue;
				}
			} else if (!documentTypeDir.isDirectory()) {
				logger.log("'" + documentTypeDir + "' is not a directory.");
				
				continue;
			}
		
			File[] entries = documentTypeDir.listFiles();
			
			for (int j = 0; j < entries.length; j++) {
				String entryName = entries[j].getName();
				boolean dirtyEntry = true;
				
				// Searchs entries with the form YYYYMM.
				if (entryName.length() == 6) {
					try {
						int period = Integer.parseInt(entryName);
						
						if (isPublishablePeriod(period)) {
							Map map = getDocumentTypeMap(entries[j]);
								
							if (map != null) {
								Integer periodKey = new Integer(period);
								String composedKey =
										documentTypeName + "-" + periodKey;
										
								documentType_PeriodMap.put(composedKey, map);
								publishedPeriods.put(periodKey, periodKey);
								
								logger.log(
										  "Period '" + periodKey + "' found "
										+ "for document type "
										+ "'" + documentTypeName + "'.");
								
								dirtyEntry = false;
							}
						}
					} catch (NumberFormatException e) {}
				}
				
				// Cleanups dirty entries.
				if (dirtyEntry) {
					DirectoryCleaner cleaner = new DirectoryCleaner(entries[j]);
					
					cleaner.run();
				}
			}
		}
		
		String periods = "";
		Set keys = publishedPeriods.keySet();
		Iterator iterator = keys.iterator();
		
		while (iterator.hasNext()) {
			Integer period = (Integer) iterator.next();
			
			periods += period + " ";
		}
		
		periods = periods.trim();
		
		if (!periods.equals("")) {
			logger.log("Available Periods: " + periods + ".");
		} else {
			logger.log("Available Periods: <NONE>.");
		}
	}
	
	protected Map getDocumentTypeMap(File dir) {
		String documentTypeMapName = dir.getAbsolutePath() + "/doctype.map";
		ObjectInputStream ois = null;
		Map map = null;
		
		try {
			ois = new ObjectInputStream(
					new FileInputStream(documentTypeMapName));
			
			map = (Map) ois.readObject();
		} catch (Exception e) {
			logger.log(e.getMessage());
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch(IOException e) {}
			}
		}
				
		return map;
	}

	public void index(DocumentType documentType, String sourceDirName,
			int period, boolean replaceMode) {
				
		index(
				documentType,
				sourceDirName,
				period,
				replaceMode,
				new IndexControl());
	}
		
	public void index(DocumentType documentType, String sourceDirName,
			int period, boolean replaceMode, IndexControl control) {
			
		String documentTypeName = documentType.getName();
					
		logger.log("index:");
		logger.log("    doctype = " + documentTypeName);
		logger.log("    sourceDir = " + sourceDirName);
		logger.log("    period = " + period);
		logger.log("    replace mode = " + replaceMode);
		logger.log("    control = [" + control + "]");		
		
		if (!isValidPeriod(period)) {
			throw new IllegalArgumentException(
					"Unexpected period '" + period + "'.");				
		}
		
		String indexerPackageName =
				DocumentIndexer.class.getPackage().getName();

		String indexBaseDirName =
				  documentBaseDir + "/"
				+ documentTypeName + "/"
				+ documentTypeName + ".index";

		File indexBaseDir = new File(indexBaseDirName);
		
		// Checks duplicated or cancelled index requests.
		if (indexBaseDir.exists()) {
			logger.log(
					  "Fatal Error, index(): Unexpected existing directory "
					+ "'" + indexBaseDirName + "'.");
					
			return;
		}

		if (!indexBaseDir.mkdir()) {
			logger.log(
					  "Error, index(): Cannot create directory "
					+ "'" + indexBaseDirName + "'.");
					
			return;
		}
		
		DocumentIndexer indexer = null;
				
		try {
			String indexerClassName =
					indexerPackageName + "." + documentTypeName + "Indexer";
	
			logger.log(
					"Loading indexer class '" + indexerClassName + "' ...");

			Class indexerClass = Class.forName(indexerClassName);
			Class[] paramTypes = new Class[] {
					String.class, String.class, IndexControl.class };
			
			Constructor indexerConstructor =
					indexerClass.getConstructor(paramTypes);
			
			logger.log(
					  "Creating indexer instance for document type "
					+ "'" + documentTypeName + "' ...");
			
			Object[] paramValues =
					new Object[] { sourceDirName, indexBaseDirName, control };
				
			indexer =
					(DocumentIndexer)
							indexerConstructor.newInstance(paramValues);
		} catch (Exception e) {
			logger.log(e);
			
			if (!indexBaseDir.delete()) {
				logger.log(
						  "Fatal Error. index(): Cannot delete directory "
						+ "'" + indexBaseDirName + "'.");				
			}
			
			return;
		}
		
		IndexTaskMonitor monitor =
				new IndexTaskMonitor(indexer, period, replaceMode);
		
		monitor.start();
	}
	
	public boolean isAvailablePeriod(int period) {
		int[] availablePeriods = getPublishedPeriods();
		
		for (int i = 0; i < availablePeriods.length; i++) {
			if (period == availablePeriods[i]) {
				return true;
			}
		}
		
		return false;
	}
	
	public int[] getPublishedPeriods() {
		int currentPeriod = getCurrentPeriod();
		int previousPeriod = getPreviousPeriod(currentPeriod);
		Integer currentPeriodKey = new Integer(currentPeriod);
		Integer previousPeriodKey = new Integer(previousPeriod);
		int nPeriods = 0;
		
		if (publishedPeriods.get(currentPeriodKey) != null) {
			nPeriods++;
		} else {
			currentPeriod = previousPeriod;
		}

		if (publishedPeriods.get(previousPeriodKey) != null) {
			nPeriods++;
		}
		
		int[] periods = new int[nPeriods];
		
		if (periods.length > 0) {
			periods[0] = currentPeriod;
		}

		if (periods.length > 1) {
			periods[1] = previousPeriod;
		}
		
		return periods;
	}
	
	public String[] getDocumentTypes() {
		Set keySet = documentType_PeriodMap.keySet();
		
		return (String[]) keySet.toArray(new String[0]);		
	}
	
	public int getDocumentCount(String documentTypeName, int period,
			int memberId) {
				
		String composedKey = documentTypeName + "-" + period;
		Map documentTypeMap = (Map) documentType_PeriodMap.get(composedKey);
		
		if (documentTypeMap == null) {
			return 0;
			
			//throw new IllegalArgumentException(
			//		"Invalid doctype/period '" + composedKey +  "'.");
		}
		
		List documents = (List) documentTypeMap.get(new Integer(memberId));
		
		return (documents == null) ? 0 : documents.size();
	}
	
	public void saveMap(Map map, String baseDir) throws IOException {
				
		ObjectOutputStream oos = null;
		
		try {
			oos = new ObjectOutputStream(
					new FileOutputStream(baseDir + "/doctype.map"));
			
			oos.writeObject(map);
		} catch (IOException e) {
			e.printStackTrace();
			
			throw e;
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch(IOException e) {}
			}
		}
	}

	public void setMap(String documentTypeName, int period, Map map) {
		DocumentType documentType =
				DocumentTypes.getDocumentType(documentTypeName);
			
		String docTypeDirName =
					documentBaseDir + "/"
				  + documentTypeName + "/"
				  + period;
				  				
		File docTypeDir = new File(docTypeDirName);

		String docTypeIndexDirName =
				documentBaseDir + "/"
			  + documentTypeName + "/"
			  + documentTypeName + ".index";
		
		File docTypeIndexDir = new File(docTypeIndexDirName);
		
		if(docTypeDir.exists()) {
			if (!DirectoryCleaner.renameAndCleanup(docTypeDir)) {
				logger.log(
						  "Error, cannot cleanup directory "
						+ "'" + docTypeDirName + "'. Set map failed [1]");
				
				DirectoryCleaner.renameAndCleanup(docTypeIndexDir);
				
				return;
			}
		}
		
		if (!docTypeIndexDir.renameTo(docTypeDir)) {
			logger.log(
					  "Error, cannot rename directory "
					+ "'" + docTypeIndexDirName + "'. Set map failed [2]");
					
			DirectoryCleaner.renameAndCleanup(docTypeIndexDir);
			
			return;					
		}
		
		Integer periodKey = new Integer(period);
		
		if (publishedPeriods.get(periodKey) == null) {
			logger.log("New period available: " + periodKey + ".");
		}

		publishedPeriods.put(periodKey, periodKey);

		String composedKey = documentTypeName + "-" + periodKey;
		
		if (documentType_PeriodMap.get(composedKey) == null) {
			logger.log("New doctype/period available: " + composedKey + ".");
		}
		
		documentType_PeriodMap.put(composedKey, map);
		
		int currentPeriod = getCurrentPeriod();
		int previousPeriod = getPreviousPeriod(currentPeriod);
		
		Set periodKeys = publishedPeriods.keySet();
		Iterator iterator = periodKeys.iterator();
		
		while (iterator.hasNext()) {
			Integer currentPeriodKey = (Integer) iterator.next();
			
			// Removes periods before previous period.
			if (currentPeriodKey.intValue() < previousPeriod) {
				logger.log("Removing period '" + currentPeriodKey + "'.");
				iterator.remove();
		
				for (int i = 0; i < documentTypes.length; i++) {
					documentType = documentTypes[i];
					documentTypeName = documentType.getName();

					documentType_PeriodMap.remove(
							documentTypeName + "-" + currentPeriodKey);

					docTypeDirName =
								documentBaseDir + "/"
							  + documentTypeName + "/"
							  + currentPeriodKey;
					
					docTypeDir = new File(docTypeDirName);
					
					if (docTypeDir.exists()) {
						if (!DirectoryCleaner.renameAndCleanup(docTypeDir)) {
							logger.log(
									  "Error, cannot cleanup directory "
									+ "'" + docTypeDirName + "'.");
									
							continue;
						}
					}
				}
			}
		}
	}

	public static int getCurrentPeriod() {
		Calendar calendar = new GregorianCalendar();
		
		calendar.setTime(new Date());
		
		return	  100 * calendar.get(Calendar.YEAR)
				+ calendar.get(Calendar.MONTH) + 1;		
	}
	
	public static int getPreviousPeriod(int period) {
		int year = period / 100;
		int month = period % 100;
		
		if (month > 1) {
			month--;
		} else {
			year--;
			month = 12;
		}

		return 100 * year + month;		
	}

	public static int getNextPeriod(int period) {
		int year = period / 100;
		int month = period % 100;
		
		if (month < 12) {
			month++;
		} else {
			year++;
			month = 1;
		}

		return 100 * year + month;		
	}
	
	public static boolean isPublishablePeriod(int period) {
		int currentPeriod = getCurrentPeriod();
		int previousPeriod = getPreviousPeriod(currentPeriod);
		
		return previousPeriod <= period && period <= currentPeriod;
	}
	
	public static boolean isValidPeriod(int period) {
		return isPublishablePeriod(period);
	}

	public static String periodToString(int period) {
		return periodToString(period, false);
	}
	
	public static String periodToString(int period, boolean reversed) {
		int year = period / 100;
		int month = period % 100;
		NumberFormat nf4 = new DecimalFormat("0000");
		NumberFormat nf2 = new DecimalFormat("00");
		
		return (reversed) ? (nf4.format(year) + "/" + nf2.format(month))
						  : (nf2.format(month) + "/" + nf4.format(year));
	}
}
