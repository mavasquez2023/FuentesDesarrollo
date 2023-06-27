

/*
 * @(#) PDFService.java    1.0 22-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import java.util.logging.Logger;
import java.util.logging.Level;

import cl.araucana.core.util.Array;
import cl.araucana.core.util.JVM;

import cl.araucana.core.util.constraint.Constraint;
import cl.araucana.core.util.constraint.IntRangeConstraint;

import cl.araucana.core.util.logging.LogManager;

import cl.araucana.core.util.xml.XMLParserException;
import cl.araucana.core.util.xml.XMLPropertiesParser;

import cl.araucana.core.util.XClass;


/**
 * This service coordinates execution of one or more
 * {@link PDFProcess} to produce and to consume PDF documents of a same type.
 * This is the big picture:
 *
 * <p align="center">
 * <img src="{@docRoot}/extras/PDFService.gif">
 * </p>
 *
 * <p> The service can be exploited from its API directly or interactively
 * using {@link PDFServiceShell} custom shell.
 * </p>
 *
 * <p> The collection of document types available to production/consume must
 * be configured in the XML document {@link #XML_DOC_DOCTYPES}. This document
 * is found as Java resource. Document type configuration is
 * represented by {@link DocumentType} class.
 * </p>
 *
 * <p> To execute a {@link PDFProcess} with a document type a user must be
 * authorized to the corresponding source system. The
 * {@link #setAuthorizedSystems(Map)} method establishes authorized source
 * systems for the user owner of the <b>PDFService</b> instance. By default,
 * all source systems are authorized.
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
 *            <TD> 22-04-2008 </TD>
 *            <TD align="center"> 1.0 </TD>
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
public class PDFService {

	/**
	 *  XML document with configured document types.
	 */
	public static final String XML_DOC_DOCTYPES =
			"/etc/pdfservice/docTypes.xml";

	/**
	 * Administrator user with all privileges.
	 */
	public static final String ADMIN_USER = "root";

	private static Logger logger = LogManager.getLogger();

	private static final Constraint	pdfReferenceSizeConstraint =
			new IntRangeConstraint(1, 4096);

	private static final Constraint	requestQueueCapacityConstraint =
			new IntRangeConstraint(64, 65536);

	private static boolean initialized;
	private static PDFServiceException initializationException;

	private static final String osUserName;
	private static Map docTypes = new TreeMap();
	private static Map processes = new TreeMap();
	private static int pid = 0;

	private String userName;
	private Map authorizedSystems = new TreeMap();

	private int failedPID;

	static {
		String defaultUserName = "unknown";

		try {
			defaultUserName = System.getProperty("user.name").toLowerCase();
		} catch (SecurityException e) {}

		osUserName = defaultUserName;

		InputStream resourceInput =
				PDFService.class.getResourceAsStream(XML_DOC_DOCTYPES);

		if (resourceInput == null) {
			initializationException =
					new PDFServiceException(
							"Cannot find resource '" + XML_DOC_DOCTYPES + "'.");
		} else {
			Reader reader = null;

			try {
				reader =
						new BufferedReader(
								new InputStreamReader(resourceInput));

				XMLPropertiesParser parser =
						new XMLPropertiesParser(reader, false);

				parser.setXMLDocumentName(XML_DOC_DOCTYPES);
				parser.parse();
				checkDocTypes(parser);

				initialized = true;
				logger.info("PDFService version 1.0 25/04/2008 initialized.");
				logger.info(JVM.getAllSystemProperties());
			} catch (Exception e) {
				initializationException =
						new PDFServiceException(
								  "Parsing resource "
								+ "'" + XML_DOC_DOCTYPES + "' failed.", e);
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch(Exception e) {}
				}
			}
		}
	}

	/**
	 * Constructs a default service instance. This instance will own to
	 * administrator user ({@link #ADMIN_USER}).
	 *
	 * @throws PDFServiceException If cannot constructs service instance.
	 *
	 * @see #PDFService(String)
	 */
	public PDFService() throws PDFServiceException {

		this(osUserName);
	}

	/**
	 * Constructs a service instance that will own to <code>userName</code>.
	 *
	 *
	 * @param userName Service instance owner.
	 *
	 * @throws PDFServiceException If cannot constructs service instance.
	 *
	 * @see #PDFService()
	 */
	public PDFService(String userName) throws PDFServiceException {

		if (!initialized) {
			logger.severe(
					  "Initialization failed. "
					+ "[" + initializationException.getMessage() + "]");

			throw initializationException;
		}

		this.userName = userName;
	}

	/**
	 * Gets service instance owner.
	 *
	 * @return Service instance owner.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets collection of authorized source system names of service instance
	 * owner.
	 *
	 * @param systems Collection of authorized source systems.
	 */
	public void setAuthorizedSystems(Map systems) {
		authorizedSystems = systems;
	}

	/**
	 * Gets collection of authorized source system names of service instance
	 * owner.
	 *
	 * @return Collection of authorized source systems.
	 */
	public Map getAuthorizedSystems() {
		return authorizedSystems;
	}

	/**
	 * Gets a document type instance from its <code>name</code>.
	 *
	 * @param name Document type name.
	 *
	 * @return Named document type.
	 */
	public DocumentType getDocumentType(String name) {
		return (DocumentType) docTypes.get(name);
	}

	/**
	 * Gets collection of document types available to production/consume.
	 *
	 * @return Array of document types available to production/consume.
	 */
	public DocumentType[] getDocTypes() {
		Collection values = docTypes.values();

		return (DocumentType[]) values.toArray(new DocumentType[0]);
	}

	/**
	 * Creates and initializes a new {@link PDFProcess}.
	 *
	 * @param docType Document type.
	 *
	 * @param providerOptions Options for <b>DMP</b>.
	 *
	 * @param producerOptions Options for PDF Producer.
	 *
	 * @param consumerOptions Options for PDF Consumer.
	 *
	 * @param sessionID Associated session ID.
	 *
	 * @param listener Process listener or <code>null</code> if it isn't
	 *        required.
	 *
	 * @return Process ID.
	 *
	 * @throws PDFServiceException If cannot create process.
	 */
	public int createProcess(DocumentType docType, String[] providerOptions,
			String[] producerOptions, String[] consumerOptions, int sessionID,
			PDFProcessListener listener) throws PDFServiceException {

		/*
		 * Checks if current user has permission to create a
		 * process using the specified document type.
		 *
		 * Administrator user will have permission to process
		 * any document type.
		 */
		if (!userName.equals(ADMIN_USER)) {
			String docTypeSystem = docType.getSystem();

			/*
			 * Document types not associated to a system
			 * will give permission to process any
			 * document type to any user.
			 */
			if (!docTypeSystem.equals("<unknown>")) {

				/*
				 * No authorized systems gives permission to
				 * process any document type to any user.
				 */
				if (authorizedSystems.size() > 0) {
					String system =
							(String) authorizedSystems.get(docTypeSystem);

					/*
					 * Permission denied to process the specified document type.
					 */
					if (system == null) {
						logger.warning(
								  "Permission denied to process "
								+ "document type '" + docType.getName() + "' "
								+ "with the user '" + userName + "'.");

						throw new PDFServiceException(
								  "Permission denied to process "
								+ "document type '" + docType.getName() + "'");
					}
				}
			}
		}

		/*
		 * Process creation to produce and consume PDFs from
		 * the specified document type.
		 */
		PDFProcess process =
				new PDFProcess(this, getPID(), sessionID, docType, userName);

		process.setProviderOptions(providerOptions);
		process.setProducerOptions(producerOptions);
		process.setConsumerOptions(consumerOptions);

		if (listener != null) {
			process.listeners.add(listener);
		}

		synchronized (processes) {
			processes.put(new Integer(process.getPID()), process);
		}

		try {
			process.init();
		} catch (PDFServiceException e) {
			process.abort();

			logger.log(
					Level.SEVERE,
					"[" + process.getPID() + "] Initialization failed:",
					e);

			throw new FailedProcessPDFServiceException(
					process.getPID(), e.getMessage());
		}

		logger.info("process [" + process.getPID() + "] Started with:");
		logger.info("    docType = " + docType.getName());
		logger.info("    owner = " + userName);

		logger.info(
				  "    provider options = ["
				+ Array.toString(providerOptions) + "]");

		logger.info(
				  "    producer options = ["
				+ Array.toString(producerOptions) + "]");

		logger.info(
				  "    consumer options = ["
				+ Array.toString(consumerOptions) + "]");

		return process.getPID();
	}

	/**
	 * Starts process identified by <code>pid</code>.
	 *
	 * @param pid Process ID.
	 */
	public void startProcess(int pid) {
		PDFProcess process = getPDFProcess(pid);

		if (!userHasAccess(process)) {
			return;
		}

		process.start();
	}

	/**
	 * Gets information of process identified by <code>pid</code>.
	 *
	 * @param pid Process ID.
	 *
	 * @return Process information.
	 */
	public PDFProcess.Info getInfo(int pid) {
		PDFProcess process = getPDFProcess(pid);

		if (!userHasAccess(process)) {
			return null;
		}

		return process.getInfo();
	}

	/**
	 * Gets information of all processes.
	 *
	 * @return Array of processes information.
	 */
	public PDFProcess.Info[] getAllProcessesInfo() {
		synchronized (processes) {
			Integer[] pids =
					(Integer[]) processes.keySet().toArray(new Integer[0]);

			int countAuthorizedProcesses = 0;

			for (int i = 0; i < pids.length; i++) {
				PDFProcess process = getPDFProcess(pids[i].intValue());

				if (userHasAccess(process)) {
					countAuthorizedProcesses++;
				}
			}

			PDFProcess.Info[] infos =
					new PDFProcess.Info[countAuthorizedProcesses];

			int j = 0;

			for (int i = 0; i < pids.length; i++) {
				PDFProcess.Info info = getInfo(pids[i].intValue());

				if (info != null) {
					infos[j++] = info;
				}
			}

			return infos;
		}
	}

	/**
	 * Cleans or removes all ended or aborted processes from service.
	 */
	public void clean() {
		synchronized (processes) {
			Integer[] pids =
					(Integer[]) processes.keySet().toArray(new Integer[0]);

			for (int i = 0; i < pids.length; i++) {
				Integer pid = pids[i];
				PDFProcess process = (PDFProcess) processes.get(pid);

				if (userHasAccess(process) && process.isEnded()) {
					processes.remove(pid);
				}
			}
		}
	}

	/**
	 * Aborts process identified by <code>pid</code> specifying
	 * <code>message</code> as cause.
	 *
	 * @param pid Process ID.
	 *
	 * @param message Abort cause message.
	 */
	public void abort(int pid, String message) {
		PDFProcess process = getPDFProcess(pid);

		if (!userHasAccess(process) || process.isEnded()) {
			return;
		}

		logger.info("[" + process.getPID() + "] Abort request.");

		process.abort(message);
	}

	/**
	 * Cleans last failed process ID.
	 *
	 * @see #setFailedPID(int)
	 * @see #getFailedPID()
	 */
	public void resetFailedPID() {
		failedPID = 0;
	}

	/**
	 * Gets last failed process ID.
	 *
	 * @return Last failed process ID.
	 *
	 * @see #setFailedPID(int)
	 * @see #resetFailedPID()
	 */
	public int getFailedPID() {
		return failedPID;
	}

	/**
	 * Sets last failed process ID.
	 *
	 * @param pid Process ID.
	 *
	 * @see #getFailedPID()
	 * @see #resetFailedPID()
	 */
	public void setFailedPID(int pid) {
		failedPID = pid;
	}

	private boolean userHasAccess(PDFProcess process) {
		if (process == null) {
			return false;
		}

		if (userName.equals(ADMIN_USER)) {
			return true;
		}

		return userName.equals(process.getOwner());
	}

	private PDFProcess getPDFProcess(int pid) {
		synchronized (processes) {
			return (PDFProcess) processes.get(new Integer(pid));
		}
	}

	private static void checkDocTypes(XMLPropertiesParser parser)
			throws XMLParserException {

		XClass docModelXClass =	new XClass(cl.araucana.fpg.DocumentModel.class);
		XClass docModelProviderXClass = new XClass(DocumentModelProvider.class);
		XClass pdfProducerXClass = new XClass(PDFProducer.class);
		XClass pdfConsumerXClass = new XClass(PDFConsumer.class);

		int docTypeCount = parser.getPropertyCount("docTypes.docType");

		for (int i = 0; i < docTypeCount; i++) {
			String $docType = "docTypes.docType[" + i + "]";

			String name = parser.getStringProperty($docType + ".name");

			String description =
					parser.getStringProperty($docType + ".description", "");

			String system =
					parser.getStringProperty($docType + ".system", "<unknown>");

			String implementation =
					parser.getStringProperty($docType + ".implementation");

			String tag = parser.getStringProperty($docType + ".tag");

			String $pdfTemplate = $docType + ".pdfTemplate";

			String pdfTemplateDir =
					parser.getStringProperty($pdfTemplate + ".dir");

			String pdfTemplateName =
					parser.getStringProperty($pdfTemplate + ".name");

			String docModelClassName =
					parser.getStringProperty($docType + ".documentModelClass");

			String docModelProviderClassName =
					parser.getStringProperty(
							$docType + ".documentModelProviderClass");

			String pdfProducerClassName =
					parser.getStringProperty($docType + ".pdfProducerClass");

			String pdfConsumerClassName =
					parser.getStringProperty($docType + ".pdfConsumerClass");

			int pdfReferenceSize =
					parser.getIntProperty(
							$docType + ".pdfReferenceSize",
							pdfReferenceSizeConstraint);

			int producerRequestQueueCapacity =
					parser.getIntProperty(
							$docType + ".producerRequestQueueCapacity",
							requestQueueCapacityConstraint);

			int consumerRequestQueueCapacity =
					parser.getIntProperty(
							$docType + ".consumerRequestQueueCapacity",
							requestQueueCapacityConstraint);

			/*
			 *  Checks PDF template.
			 */
			File dir = new File(pdfTemplateDir + "/" + pdfTemplateName);

			if (!dir.isDirectory()
					|| !new File(dir, "source.pdf").exists()) {

				throw new XMLParserException(
						  "Invalid PDF template "
						+ "'" + pdfTemplateDir + " " + pdfTemplateName + "'.");
			}

			/*
			 *  Checks specified classes.
			 */
			Class documentModelClass = null;
			Class documentModelProviderClass = null;
			Class pdfProducerClass = null;
			Class pdfConsumerClass = null;

			try {
				documentModelClass =
						docModelXClass.getImplementationClass(
								docModelClassName);

				documentModelProviderClass =
						docModelProviderXClass.getImplementationClass(
								docModelProviderClassName);

				pdfProducerClass =
						pdfProducerXClass.getImplementationClass(
								pdfProducerClassName);

				pdfConsumerClass =
						pdfConsumerXClass.getImplementationClass(
								pdfConsumerClassName);
			} catch (ClassNotFoundException e) {
				throw new XMLParserException(e.getMessage());
			}

			/*
			 * Registers new document type.
			 */
         	DocumentType docType = new DocumentType();

			docType.setName(name);
			docType.setDescription(description);
			docType.setSystem(system);
			docType.setImplementation(implementation);
			docType.setTag(tag);
			docType.setTemplateDir(pdfTemplateDir);
			docType.setTemplateName(pdfTemplateName);
			docType.setDocumentModelClass(documentModelClass);
			docType.setDocumentModelProviderClass(documentModelProviderClass);
			docType.setPdfProducerClass(pdfProducerClass);
			docType.setPdfConsumerClass(pdfConsumerClass);
			docType.setPdfReferenceSize(pdfReferenceSize);

			docType.setProducerRequestQueueCapacity(
					producerRequestQueueCapacity);

			docType.setConsumerRequestQueueCapacity(
					consumerRequestQueueCapacity);

         	docTypes.put(name, docType);
		}
	}

	private static synchronized int getPID() {
		return ++pid;
	}
}
