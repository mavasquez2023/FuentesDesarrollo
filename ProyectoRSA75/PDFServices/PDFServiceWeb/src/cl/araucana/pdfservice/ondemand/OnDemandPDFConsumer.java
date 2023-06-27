

/*
 * @(#) OnDemandPDFConsumer.java    1.0 24-06-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice.ondemand;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;

import cl.araucana.pdfservice.DocumentType;
import cl.araucana.pdfservice.PDFConsumer;
import cl.araucana.pdfservice.PDFProcess;
import cl.araucana.pdfservice.PDFRequest;
import cl.araucana.pdfservice.PDFServiceException;
import cl.araucana.pdfservice.ondemand.resources.Resource;

import cl.araucana.fpg.PDFDocument;
import cl.araucana.fpg.PDFTemplate;

import cl.araucana.core.util.DirectoryCleaner;
import cl.araucana.core.util.FederalQueueFactory;
import cl.araucana.core.util.Padder;
import cl.araucana.core.util.logging.LogManager;


/**
 * ...
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
 * @author Germán Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class OnDemandPDFConsumer implements PDFConsumer, Constants {

	private static final int MAX_SPOOL_ID = 32767;

	private static Logger logger = LogManager.getLogger();
	private static Map spoolIDs = new HashMap();
	
	private PDFProcess process;
	private Map parameterMap;
	private String spoolDir;
	private String docTypeName;
	private int docVersion;
	
	private String logID;
	
	private FederalQueueFactory federalQueueFactory;
	
	public OnDemandPDFConsumer() {
	}

	public void init(PDFProcess process) throws PDFServiceException {

		logID = "[" + process.getPID() + "] ";

		logger.info(logID + "Initializing ...");

		this.process = process;

		Map parameterMap = process.getPDFProducer().getParameterMap();
		PDFTemplate template = (PDFTemplate) parameterMap.get("template");

		if (template == null) {
			throw new PDFServiceException(
					"Unknown document type/version to consume.");
		}

		docTypeName = template.getDocType();
		docVersion = template.getDocVersion();
		
		DocumentType docType = process.getDocumentType();
		String systemName = docType.getSystem();
		
		spoolDir = "/" + systemName + "/spool/" + docTypeName;
		
		logger.info(logID + "Configuration:");
		logger.info(logID + "    spoolDir  = " + spoolDir);
		
		prepareDir(spoolDir, "spool");
		
		federalQueueFactory = new FederalQueueFactory();
		
		synchronized (spoolIDs) {
			Integer iSpoolID = (Integer) spoolIDs.get(docTypeName);
			
			if (iSpoolID == null) {
				spoolIDs.put(docTypeName, new Integer(0));
			}
		}
		
		parameterMap = new HashMap();
	}

	public Map getParameterMap() {
		return parameterMap;
	}

	public String getDocTypeName() {
		return docTypeName;
	}
	
	public int getDocVersion() {
		return docVersion;
	}	

	public String getSpoolDir() {
		return spoolDir;
	}

	public FederalQueueFactory getFederalQueueFactory() {
		return federalQueueFactory;
	}
	
	public void run() {
		logger.info(logID + "Running ...");
		
		int consumedDocuments = 0;
		PDFRequest request;

		try {
			while ((request = process.getConsumeRequest()) != null) {
				
				/*
				 * Consumes document.
				 */				
				PDFDocument document =
						(PDFDocument) request.getParameter(PARAM_PDF_DOCUMENT);
	
				Action action = (Action) request.getParameter(PARAM_ACTION);
				Resource resource = action.getResource();
				String docID = document.getDocumentModel().docID();
				
				try {
					if (resource.consume(document, true)) {
						process.setProcessedWorkUnits(++consumedDocuments);
					}
				} catch (PDFServiceException e) {
					logger.log(
							Level.SEVERE,
							  logID
							+ ">< " + "action id=" + action.getID() + " "
							+ "cannot consume document '" + docID + "':",
							e);
				}
			}

			logger.info(logID + "Ended.");
		} catch (InterruptedException e) {
			logger.warning(logID + "Aborted.");
		} catch (Exception e) {
			logger.log(Level.SEVERE, logID + ">< Unexpected exception:", e);
			logger.severe(logID + "Aborted.");
	
			process.abort("Caught exception.");
		}		
	}
	
	public void destroy() {
		federalQueueFactory.release();
		
		federalQueueFactory = null;
		process = null;
	}
	
	public String spoolDocument(PDFDocument document)
			throws PDFServiceException {
		
		int spoolID;
		
		synchronized (spoolIDs) {
			Integer iSpoolID = (Integer) spoolIDs.get(docTypeName);
			
			spoolID = iSpoolID.intValue();
			spoolID = spoolID % MAX_SPOOL_ID + 1;
			
			spoolIDs.put(docTypeName, new Integer(spoolID));
		}
		
		return
				saveDocument(
						document, spoolDir, Padder.lpad(spoolID, 5, '0'));
	}
	
	public String saveDocument(PDFDocument document, String dirName)
			throws PDFServiceException {

		return
				saveDocument(
						document, dirName, document.getDocumentModel().docID());
	}
	
	public String saveDocument(PDFDocument document, String dirName,
			String name) throws PDFServiceException {

		String pdfFileName = dirName + "/" + name + ".PDF";
		
		try {
			document.close();
	
			FileOutputStream output = null;
	
			try {
				output = new FileOutputStream(pdfFileName);
	
				document.writeTo(output);
			} finally {
				if (output != null) {
					try {
						output.close();
					} catch (IOException e) {}
				}
			}
		} catch (Exception e) {
			throw new PDFServiceException(
					"Cannot save document '" + pdfFileName + "'");
		}
		
		return pdfFileName;
	}
	
	private void prepareDir(String dirname, String dirType)
			throws PDFServiceException {
		
		prepareDir(dirname, dirType, false);
	}
	
	private void prepareDir(String dirname, String dirType, boolean cleanup)
			throws PDFServiceException {

		File dir = new File(dirname);

		if (!dir.exists()) {
			if (dir.mkdirs()) {
				logger.info(
						  logID + dirType + " directory "
						+ "'" + dirname + "' was created.");

				cleanup = false;
			} else {
				throw new PDFServiceException(
						  dirType + " directory "
						+ "'" + dirname + "' cannot be created.");
			}
		}

		if (cleanup) {
			if (DirectoryCleaner.renameAndCleanup(dirname)) {
				logger.info(
						  logID
						+ "cleaning up " + dirType + " directory "
						+ "'" + dirname + "' ...");
			} else {
				throw new PDFServiceException(
						  dirType + " directory "
						+ "'" + dirname + "' cannot be cleanup.");
			}
		}
	}
}
