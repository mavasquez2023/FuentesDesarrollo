

/*
 * @(#) GenericPDFProducer.java    1.0 30-08-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice.producers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;

import cl.araucana.pdfservice.Constants;
import cl.araucana.pdfservice.DocumentType;
import cl.araucana.pdfservice.PDFProcess;
import cl.araucana.pdfservice.PDFProducer;
import cl.araucana.pdfservice.PDFRequest;
import cl.araucana.pdfservice.PDFServiceException;

import cl.araucana.fpg.DocumentModel;
import cl.araucana.fpg.PDFDocument;
import cl.araucana.fpg.PDFGenerator;
import cl.araucana.fpg.PDFObject;
import cl.araucana.fpg.PDFPage;
import cl.araucana.fpg.PDFPages;
import cl.araucana.fpg.PDFTemplate;


import cl.araucana.core.util.logging.LogManager;


/**
 * Provides an abstraction for a PDF Process's Generic PDF producer. It's based
 * on the {@link PageType} concept. Every page in the corresponding
 * {@link cl.araucana.fpg.PDFTemplate} will be considered as a page type
 * identified by its page number. These pages will be released from memory
 * when PDF template is loaded and finally freezed to establish fixed PDF
 * contents.
 *
 * <p> A concrete {@link cl.araucana.pdfservice.PDFProducer} of this class must
 * to implement <code>initPageTypes(PageType[])</code> method to define how
 * a specific document type will need produce every page type.
 * </p>
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
 * @author  Germán Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public abstract class GenericPDFProducer implements PDFProducer, Constants {

	private static Logger logger = LogManager.getLogger();

	private PDFProcess process;
	private Class docModelClass;
	private int pdfReferenceSize;

	private PDFTemplate template;
	private PDFGenerator generator;

	private Map parameterMap;

	private	PageType[] pageTypes;

	private	PDFObject info;

	private String logID;

	/**
	 * Constructs an empty PDF producer instance.
	 */
	protected GenericPDFProducer() {
	}

	public void init(PDFProcess process) throws PDFServiceException {

		logID = "[" + process.getPID() + "] ";

		logger.info(logID + "Initializing ...");

		this.process = process;

		DocumentType docType = process.getDocumentType();

		docModelClass = docType.getDocumentModelClass();
		pdfReferenceSize = docType.getPdfReferenceSize();

		String templateDir = docType.getTemplateDir();
		String templateName = docType.getTemplateName();

		logger.config(logID + "Options:");

		logger.config(
				logID + "    docModelClass    = " + docModelClass.getName());

		logger.config(
				logID + "    pdfReferenceSize = " + pdfReferenceSize + " KB");

		logger.config(
					  logID
					+ "    PDF template = "
					+ templateDir + "::" + templateName);

		try {

			/*
			 * Prepares PDF template to be exploited by PDF Generator.
			 */
			template = new PDFTemplate(templateDir, templateName);

			template.setDebugMode(false);
			template.load();

		    /*
			 * Gets PDF page types.
			 */
			int nPages = template.getPageCount();

			pageTypes = new PageType[nPages];

			for (int i = 0; i < pageTypes.length; i++) {
				pageTypes[i] = new PageType(template, i);
			}

			/*
			 * Inits page types for production.
			 */
			initPageTypes(pageTypes);

		    /*
			 * Gets info object id to PDF document's properties.
			 */
			int infoObjID = template.getInfoObjID();

			/*
			 * Gets variable PDF objects required.
			 */
			info =
					template.getObject(
							infoObjID, PDFTemplate.EXTENSION_COMPILED);

			/*
			 * Releases variable PDF objects required from PDF template.
			 */
			template.releaseObject(infoObjID);

			for (int i = 0; i < pageTypes.length; i++) {
				PageType pageType = pageTypes[i];
				PDFObject background = pageType.getBG();
				PDFObject foreground = pageType.getFG();

				if (background != null) {
					template.releaseObject(pageType.getBGObjID());
				}

				if (foreground != null) {
					template.releaseObject(pageType.getFGObjID());
				}
			}

			/*
			 * Releases page types from PDF template.
			 */
			for (int i = 0; i < pageTypes.length; i++) {
				template.releasePage(pageTypes[i].getPage());
			}

			/*
			 * Releases PDFPages object from PDF template.
			 */
			PDFPages pdfPages = template.getPDFPages();

			template.releaseObject(pdfPages.getObjID());

			/*
			 * Release root catalog object from PDF template.
			 */
			template.releaseObject(template.getRootObjID());

			/*
			 * Adds and Establishes default font to PDF template
			 * (required to support signed PDF documents with FPG).
			 */
			int defaultFontEncodingObjID =
					template.addObject("helvetica.encoding");

			int defaultFontObjID =
					template.addFontObject(
							"Helvetica", defaultFontEncodingObjID);

			template.setDefaultFontObjID(defaultFontObjID);

			/*
			 * Freezes PDF template.
			 */
			template.freeze();

			/*
			 * Creates PDF Generator instance.
			 */
			generator = new PDFGenerator(template, docModelClass);

			/*
			 *  Links compiled PDF objects required for produce documents.
			 */
			List compiledObjects = new ArrayList(2 * nPages + 1);

			compiledObjects.add(info);

			for (int i = 0; i < pageTypes.length; i++) {
				PageType pageType = pageTypes[i];
				PDFObject background = pageType.getBG();
				PDFObject foreground = pageType.getFG();

				if (background != null) {
					compiledObjects.add(background);
				}

				if (foreground != null) {
					compiledObjects.add(foreground);
				}
			}

			generator.link(
					(PDFObject[]) compiledObjects.toArray(new PDFObject[0]));
		} catch (Exception e) {
			logger.log(
						Level.SEVERE,
						  logID + " "
						+ ">< Cannot initialize producer", e);

			throw new PDFServiceException(
					  "Cannot initialize producer for process "
					+ process.getPID(),
					e);
		}

		parameterMap = new HashMap();

		parameterMap.put("template", template);

		logger.info(logID + "Initialized.");
	}

	/**
	 * Initializes every PDF page type to be produced by this PDF producer.
	 *
	 * @param pageTypes Array of PDF page types.
	 *
	 * @throws PDFServiceException If cannot initializes PDF page types.
	 */
	protected abstract void initPageTypes(PageType[] pageTypes)
			throws PDFServiceException;

	public Map getParameterMap() {
		return parameterMap;
	}

	public void run() {
		logger.info(logID + "Running ...");

		PDFRequest request;

		try {
			while ((request = process.getProduceRequest()) != null) {
				DocumentModel docModel =
						(DocumentModel)
								request.getParameter(PARAM_DOCUMENT_MODEL);

				/*
				 * Produces PDF document.
				 */
				PDFDocument document =
						generator.newPDFDocument(docModel, pdfReferenceSize);

				/*
				 * Produces PDF info object.
				 */
				document.execute(info, false, true);
				document.setInfoObjID(info.getNewObjID());

				/*
				 * Produces Pages.
				 */
				while (docModel.hasMorePages()) {
					int pageTypeID = docModel.pageType();

					/*
					logger.finest(logID + " docModel pageType=" + pageTypeID);
					 */

					if (pageTypeID < 0 || pageTypeID >= pageTypes.length) {
						throw new PDFServiceException(
								"Unexpected page type '" + pageTypeID + "'.");
					}

					PageType pageType = pageTypes[pageTypeID];
					PDFObject background = pageType.getBG();
					PDFObject foreground = pageType.getFG();

					// Executes compiled PDF objects for current page.
					if (background != null) {
						document.execute(background);
						pageType.setFGObjID(background.getNewObjID());
					}

					if (foreground != null) {
						document.execute(foreground);
						pageType.setFGObjID(foreground.getNewObjID());
					}

					// Adds page to current PDF document.
					PDFPage newPage = new PDFPage(0);

					newPage.setHeader(pageType.getHeader());
					newPage.addContentObjIDs(pageType.getContentObjIDs());

					document.addNewPage(newPage);
				}

				if (document.pageCount() > 0) {
					request.setParameter(PARAM_PDF_DOCUMENT, document);
					process.putConsumeRequest(request);
				} else {
					logger.warning(
							  logID
							+ " empty document '" + document.getID() + "' "
							+ " was discarded.");
				}
			}

			process.putConsumeRequest(null);
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
		generator.close();

		process = null;
		template = null;
		generator = null;
		pageTypes = null;
	}
}
