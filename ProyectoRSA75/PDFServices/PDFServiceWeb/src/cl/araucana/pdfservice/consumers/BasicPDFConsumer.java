

/*
 * @(#) BasicPDFConsumer.java    1.0 30-08-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice.consumers;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;

import cl.araucana.pdfservice.Constants;
import cl.araucana.pdfservice.PDFConsumer;
import cl.araucana.pdfservice.PDFProcess;
import cl.araucana.pdfservice.PDFRequest;
import cl.araucana.pdfservice.PDFServiceException;

import cl.araucana.fpg.DocumentModel;
import cl.araucana.fpg.PDFDocument;
import cl.araucana.fpg.PDFTemplate;

import cl.araucana.core.util.DirectoryCleaner;
import cl.araucana.core.util.Padder;
import cl.araucana.core.util.logging.LogManager;


/**
 * Basic PDF consumer to save produced PDF documents to a output directory.
 *
 * <p> Filename for each saved PDF document will be a unique sequence number
 * (starting from 1 per every <i>FPG</i> production process) formatted to
 * 8 digits of width and zeroes left padding. This behaviour can be changed
 * overridden {@link #getDocBaseName(PDFDocument, DocumentModel)} method.
 * </p>
 * 
 * <BR>
 * <BR>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="80%" CELLPADDING="3" CELLSPACING="0">
 *     <tr>
 *        <th bgcolor="black">
 *            <font color="white"><strong>Syntax</strong></font>
 *        </th>
 *     </tr>
 *     
 *     <tr>
 *        <td><b>[-clean] -outputDir &lt;outputDir&gt;</b></td>
 *     </tr>
 * </TABLE>
 * 
 * <BR>
 * 
 * <p> This PDF consumer support the following options:
 * </p>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="90%" CELLPADDING="3" CELLSPACING="0">
 *     <tr>
 *        <th bgcolor="black">
 *            <font color="white"><strong>Option</strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>Description</strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>Default</strong></font>
 *        </th>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>clean</strong></td>
 *        
 *        <td>
 *            Cleans synchorizedly output directory in a separated thread.
 *            It's optional but very recommended to avoid mixed PDF documents. 
 *        </td>
 *        
 *        <td>
 *            No cleans.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>outputDir</strong></td>
 *        
 *        <td>
 *            Output directory to save PDF documents. Output directory must be
 *            specified.
 *        </td>
 *        
 *        <td>
 *            &nbsp;
 *        </td>
 *     </tr>
 * </TABLE>
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
 *            <TD> 30-08-2008 </TD>
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
public class BasicPDFConsumer implements PDFConsumer, Constants {

	private static Logger logger = LogManager.getLogger();

	private PDFProcess process;
	private String outputDir;

	private Map parameterMap;

	private String docType;
	private int docVersion;

	private String logID;

	/**
	 * Constructs an empty basic PDF consumer.
	 */
	public BasicPDFConsumer() {
	}
	
	private String usage() throws PDFServiceException {

		String usage =
				  "\n"
				+ "Invalid syntax for consumer. Usage:\n"
				+ "   -consumer [ -clean ]\n"
				+ "               -outputDir <outputDir>\n";

		throw new PDFServiceException(usage);
	}

	public void init(PDFProcess process) throws PDFServiceException {

		logID = "[" + process.getPID() + "] ";

		logger.info(logID + "Initializing ...");

		this.process = process;

		String[] options = process.getConsumerOptions();

		boolean cleanupOutputDir = false;

		for (int i = 0; i < options.length; i++) {
			if (options[i].equals("-clean")) {
				cleanupOutputDir = true;
			} else if (options[i].equals("-outputDir")) {
				if (i + 1 < options.length) {
					outputDir = options[++i];
				} else {
					throw new PDFServiceException(
							"Missed output directory in consumer.");
				}
			} else {
				usage();
			}
		}

		if (outputDir == null) {
			usage();
		}

		logger.info(logID + "Options:");
		logger.info(logID + "    outputDir = " + outputDir);

		File dir = new File(outputDir);

		if (!dir.exists()) {
			if (dir.mkdirs()) {
				logger.info(
						  logID + "outputDir "
						+ "'" + outputDir + "' was created.");

				cleanupOutputDir = false;
			} else {
				throw new PDFServiceException(
						"outputDir '" + outputDir + "' cannot be created.");
			}
		}

		if (cleanupOutputDir) {
			if (DirectoryCleaner.renameAndCleanup(outputDir)) {
				logger.info(
						  logID
						+ "cleaning up directory '" + outputDir + "' ...");
			} else {
				throw new PDFServiceException(
						"Cannot clean up output directory in consumer.");
			}
		}

		Map parameterMap = process.getPDFProducer().getParameterMap();

		PDFTemplate template = (PDFTemplate) parameterMap.get("template");

		if (template == null) {
			throw new PDFServiceException(
					"Unknown document type/version to consume.");
		}

		docType = template.getDocType();
		docVersion = template.getDocVersion();

		logger.info(logID + "    docType    = " + docType);
		logger.info(logID + "    docVersion = " + docVersion);

		parameterMap = new HashMap();

		logger.info(logID + "Initialized.");
	}

	public Map getParameterMap() {
		return parameterMap;
	}

	/**
	 * Consumes all PDF documents from consume requests queue saving them
	 * to the specified output directory.
	 */	
	public void run() {
		logger.info(logID + "Running ...");

		int consumedDocuments = 0;
		PDFRequest request;

		try {
			while ((request = process.getConsumeRequest()) != null) {
				PDFDocument document =
						(PDFDocument) request.getParameter(PARAM_PDF_DOCUMENT);

				/*
				 * Consumes PDF document.
				 */
				DocumentModel docModel =
						(DocumentModel) request.getParameter(
								PARAM_DOCUMENT_MODEL);

				document.close();

				String pdfFileName =
						    outputDir
						  + "/"
						  + getDocBaseName(document, docModel)
						  + ".pdf";

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

				document.release();
				process.setProcessedWorkUnits(++consumedDocuments);
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
		process = null;
	}

	/**
	 * Determines an unique document base name to save in function of
	 * specified PDF document and its asocciated document model.
	 * 
	 * @param document Document base name to be saved.
	 * 
	 * @param docModel Document Model.
	 * 
	 * @return Document 
	 */
	protected String getDocBaseName(PDFDocument document,
			DocumentModel docModel) {

		return Padder.lpad(document.getSequenceNumber(), 8, '0');
	}
}
