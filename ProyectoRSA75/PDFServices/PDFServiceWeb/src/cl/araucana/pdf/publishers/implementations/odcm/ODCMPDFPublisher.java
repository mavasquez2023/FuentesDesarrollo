/**
 * 
 */
package cl.araucana.pdf.publishers.implementations.odcm;

/*
 * @(#) ODCMPDFPublisher.java    1.0 02-06-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import java.util.logging.Level;
import java.util.logging.Logger;

import cl.araucana.core.util.FileUtils;
import cl.araucana.core.util.Padder;
import cl.araucana.core.util.UserPrincipal;

import cl.araucana.core.util.logging.LogManager;

import cl.araucana.fpg.PDFDocument;

import cl.araucana.pdf.publishers.BadDocIndexPDFPublisherException;
import cl.araucana.pdf.publishers.EmptyPublishedDocumentList;
import cl.araucana.pdf.publishers.Field;
import cl.araucana.pdf.publishers.PDFPublisher;
import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.PublishedDocumentInfo;
import cl.araucana.pdf.publishers.PublishedDocumentList;
import cl.araucana.pdf.publishers.PublishOptions;
import cl.araucana.pdf.publishers.Scope;


/**
 * Provides a partial implementation of a <b>PDF Publisher</b> based on
 * <i>IBM OnDemand Content Manager</i> version 7.1.2.5 (<b>ODCM</b>). In the
 * actual state provides support to publication/unpublication operations and
 * PDF documents metadata retrieval operations. PDF documents content retrieval
 * operations are unsupported. These can be added in the future to complete it.
 *
 * <p> This implementation is constructed over <b>ODCM CLI</b> (<i>Command Line
 * Interface</i>) included with in the ODCM's client components. More specificly
 * it uses <b>arsdoc</b> and <b>arsload</b> line commands to support the
 * mentioned functionalities above.
 * </p>
 * 
 * <p> This class requires an ad-hoc publisher configuration
 * (see {@link cl.araucana.pdf.publishers.PublisherManager}) which Java class
 * has to be this one or a concrete subclass of it with the following mandatory
 * properties:
 * </p>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="60%" CELLPADDING="3" CELLSPACING="0">
 *     <tr>
 *        <th bgcolor="black">
 *            <font color="white"><strong>Name</strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>Description</strong></font>
 *        </th>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>onDemandCmdDir</strong>
 *        </td>
 *        
 *        <td>
 *            Base directory to ODCM client components.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>server</strong>
 *        </td>
 *        
 *        <td>
 *            ODCM's ip or name server.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>userPrincipal</strong>
 *        </td>
 *        
 *        <td>
 *            Encrypted ODCM's user credentials. Encryption method
 *            documentation is not included in this one.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>tmpDir</strong>
 *        </td>
 *        
 *        <td>
 *            Temporal directory used to create auxiliar files in publication
 *            and retrieval operations.
 *        </td>
 *     </tr>
 * </TABLE>
 * 
 * <BR>
 * <BR>
 * 
 * <p> To obtain high performance in publication operations this class uses
 * a pool of loader objects each one running in its own thread to load a
 * partition of the total collection of PDF documents to be published. This
 * pool can be configured with the following publisher properties:
 * </p>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="60%" CELLPADDING="3" CELLSPACING="0">
 *     <tr>
 *        <th bgcolor="black">
 *            <font color="white"><strong>Name</strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>Description</strong></font>
 *        </th>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>loaders</strong>
 *        </td>
 *        
 *        <td>
 *            Maximum number of active loader threads in a publication
 *            operation. Default is 1.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>partitionSize</strong>
 *        </td>
 *        
 *        <td>
 *            Maximum number of PDF documents to be loaded by a loader thread.
 *            Its value must be in the range [{@link #MIN_PARTITION_SIZE},
 *            {@link #UNLIMITED_PARTITION_SIZE}]. Default is
 *            {@link #UNLIMITED_PARTITION_SIZE}. If
 *            {@link #UNLIMITED_PARTITION_SIZE} size is used then only one
 *            loader thread will be acvited. 
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
 *            <TD> 02-06-2008 </TD>
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
public class ODCMPDFPublisher extends PDFPublisher {

	/**
	 * Control flag to backup ODCM index files used in
	 * publication operations. It can be enabled with
	 * the boolean system property <b>fpg.pdf.publisher.odcm</b>.
	 */
	public static boolean backupIndexFile =
			Boolean.getBoolean("fpg.pdf.publisher.odcm");

	/**
	 * Maximum maximum hits supported to retrieval operations. 
	 */	
	public static final int MAX_MAX_HITS = Integer.MAX_VALUE;
	
	/**
	 * Unlimited (maximum) load partition size.
	 */
	public static final int UNLIMITED_PARTITION_SIZE = Integer.MAX_VALUE;
	
	/**
	 * Minumum number of PDF documents in a load partition. 
	 */
	public static final int MIN_PARTITION_SIZE = 200;

	/**
	 * Platform dependent file separator.
	 */
	protected static final String fileSeparator =
			System.getProperty("file.separator");

	private static Logger logger = LogManager.getLogger();

	private static final int BUFFER_SIZE = 1024;  // 1KB.

	private static final Map supportedFeatures;

	private static int cmdNo = 0;
	private static int fileNo = 0;
	private static int indexFileNo = 0;
	private static int loaderID = 0;

	// Publisher properties.
	
	/**
	 * ODCM's ip or name server.
	 *   
	 */
	protected String server;
	
	/**
	 * Base directory to ODCM client components.
	 */
	protected String onDemandCmdDir;
	
	/**
	 * Temporal directory used to create auxiliar
	 * files in publication and retrieval operations.
	 */
	protected String tmpDir;

	// Derivated properties to local platform.
	/**
	 * Platform dependent base directory to ODCM client components.
	 */
	protected String _onDemandCmdDir;
	
	/**
	 * Platform dependent temporal directory used to create auxiliar
	 * files in publication and retrieval operations.
	 */
	protected String _tmpDir;

	// Derivated properties from userPrincipal.
	
	/**
	 * ODCM's username. 
	 */
	protected String userName;
	
	/**
	 *  ODCM's user password. 
	 */
	protected String password;

	// DocType's Index properties.
	
	/**
	 *  ODCM's document type application name. 
	 */
	protected String applicationName;
	
	/**
	 *  ODCM's document type application group name. 
	 */	
	protected String applicationGroupName;
	
	/**
	 *  ODCM's document type folder name. 
	 */	
	protected String folderName;
	
	/**
	 *  ODCM's document type hidden predicate. It's used
	 *  to restrict ODCM operations's scope to PDF format
	 *  only excluding other ones.  
	 */	
	protected String hiddenPredicate;

	// Derivated properties from DocType's Index properties.
	private String fieldsOrder;
	private String orderClause;

	private String docIDFieldsOrder;

	private boolean logged;
	private String logID;

	// Replace mode controls.
	private int replaceMode;
	private boolean replaceModeInitialized;
	private boolean replaceNoReplaceModes;

	// Loaders.
	private	int nLoaders;
	private	int partitionSize;
	private Map activeLoaders;
	private int nPartitions;
	private	int usedPartitionSize;
	private	int partitionNo;
	private boolean interruptedLoad;

	/*
	 *  Maps used for replace/noreplace modes.
	 */
	private Map publishedDocsInReplaceModeScope;
	private SortedMap publishedDocsToBeUnpublished;
	private Map docsToBePublished;

	// Stats.
	private int nAddedDocuments;
	private int nUpdatedDocuments;

	static {
		supportedFeatures = new HashMap();

		supportedFeatures.put("compressing", Boolean.TRUE);
		supportedFeatures.put("batch",       Boolean.TRUE);
	}

	/**
	 * Creates an ad-hoc instance of <b>ODCM PDF Publisher</b> according to
	 * specified publish options in <code>options</code>.
	 * 
	 * @param docType Document type.
	 * 
	 * @param docVersion Document type's version.
	 * 
	 * @param options Options to the <b>ODCM PDF Publisher</b>.
	 * 
	 * @throws PDFPublisherException If <code>options</code> is
	 *         <code>null</code> or the named publisher in options is unknown
	 *         or cannot create a new <b>ODCM PDF Publisher</b> instance.
	 */	
	public ODCMPDFPublisher(String docType, int docVersion,
			PublishOptions options) throws PDFPublisherException {

		super(docType, docVersion, options);

		logged = options.isLogged();
		replaceMode = options.getReplaceMode();

		logID = options.getLogID();

		if (logID == null) {
			logID = "";
		} else {
			logID = "[" + logID + "] ";
		}

		replaceNoReplaceModes =
				    replaceMode == PublishOptions.MODE_REPLACE
				 || replaceMode == PublishOptions.MODE_NO_REPLACE;

		onDemandCmdDir = getPropertyValue("onDemandCmdDir");
		server = getPropertyValue("server");

		String userCredentials = getPropertyValue("userPrincipal");

		tmpDir = getPropertyValue("tmpDir");

		_onDemandCmdDir = translateToLocalPath(onDemandCmdDir);
		_tmpDir = translateToLocalPath(tmpDir);

		try {
			UserPrincipal userPrincipal =
					UserPrincipal.decodeUserCredentials(userCredentials);

			userName = userPrincipal.getName();
			password = userPrincipal.getPassword();
		} catch (Exception e) {
			throw new PDFPublisherException(
					"Invalid user credentials '" + userCredentials);
		}

		applicationName = getIndexPropertyValue("applicationName");
		applicationGroupName = getIndexPropertyValue("applicationGroupName");
		folderName = getIndexPropertyValue("folderName");
		hiddenPredicate = getIndexPropertyValue("hiddenPredicate", "");

		String sNLoaders = getPropertyValue("loaders", "1");

		if (sNLoaders != null) {
			try {
				nLoaders = Integer.parseInt(sNLoaders);

				if (nLoaders <= 0) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				throw new PDFPublisherException(
						"Invalid loaders value '" + sNLoaders + "'");
			}
		}

		String sPartitionSize =
				getPropertyValue(
						"partitionSize", UNLIMITED_PARTITION_SIZE + "");

		if (sPartitionSize != null) {
			try {
				partitionSize = Integer.parseInt(sPartitionSize);

				if (partitionSize <= 0) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				throw new PDFPublisherException(
						  "Invalid partition size value "
						+ "'" + sPartitionSize + "'");
			}
		}

		if (partitionSize == UNLIMITED_PARTITION_SIZE && nLoaders > 1) {
			logger.warning(
					  logID
					+ "Loaders changed from '" + nLoaders + "' to '1' "
					+ "because partition size is UNLIMITED.");

			nLoaders = 1;
		} else if (partitionSize < MIN_PARTITION_SIZE) {
			logger.warning(
					  logID
					+ "Partition size changed from '" + partitionSize + "' "
					+ "to minimum partition size "
					+ "'" + MIN_PARTITION_SIZE + "'.");

			partitionSize = MIN_PARTITION_SIZE;
		}

		fieldsOrder = "";

		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];

			fieldsOrder += "(" + field.getName() + ")";
		}

		docIDFieldsOrder = "";
		orderClause = "ORDER BY ";

		for (int i = 0; i < docIDFields.length; i++) {
			Field field = docIDFields[i];

			docIDFieldsOrder += "(" + field.getName() + ")";
			orderClause += field.getName();

			if (i + 1 < docIDFields.length) {
				orderClause += ", ";
			}
		}

		logger.config(logID + "Document Options:");
		logger.config(logID + "    type    = " + docType);
		logger.config(logID + "    version = " + docVersion);
		logger.config(logID + "");

		logger.config(logID + "Publish Options:");
		logger.config(logID + "    " + options);
		logger.config(logID + "");

		logger.config(
				logID + "OnDemand Content Manager PDFPublisher Configuration:");

		logger.config(logID + "    server               = " + server);
		logger.config(logID + "    userPrincipal        = " + userCredentials);

		logger.config(
				  logID
				+ "    onDemandCmdDir       = " + onDemandCmdDir + " "
				+ "[" + _onDemandCmdDir + "]");

		logger.config(
				  logID
				+ "    tmpDir               = " + tmpDir + " "
				+ "[" + _tmpDir + "]");

		logger.config(logID + "    applicationName      = " + applicationName);

		logger.config(
				logID + "    applicationGroupName = " + applicationGroupName);

		logger.config(logID + "    folderName           = " + folderName);
		logger.config(logID + "    hiddenPredicate      = " + hiddenPredicate);
		logger.config(logID + "    fieldsOrder          = " + fieldsOrder);
		logger.config(logID + "    docIDFieldsOrder     = " + docIDFieldsOrder);
		logger.config(logID + "    loaders              = " + nLoaders);

		if (partitionSize == UNLIMITED_PARTITION_SIZE) {
			logger.config(
					logID + "    partitionSize        = UNLIMITED");
		} else {
			logger.config(
					logID + "    partitionSize        = " + partitionSize);
		}

		logger.config(logID + "");

		if (partitionSize == UNLIMITED_PARTITION_SIZE) {
			logger.warning(
					  logID
					+ "Publication can fail with UNLIMITED partition size.");
		}
	}

	public boolean supportsFeature(String name) {
		Boolean supported = (Boolean) supportedFeatures.get(name);

		return supported != null && supported.booleanValue();
	}

	public void publish(PDFDocument document) throws PDFPublisherException {

		throw new UnsupportedOperationException(
				"OnDemandContentManager unsupport PDFDocument class.");
	}

	public void publish(String docIndex, String pdfFileName)
			throws PDFPublisherException {

		addDocToBePublished(docIndex, pdfFileName);
	}

	public int unpublish(Scope scope) throws PDFPublisherException {

		arsdocDelete(scope);

		/*
		 * ODCM Command line API not report how many documents where deleted.
		 */
		return 0;
	}

	public int unpublish(Scope scope, boolean strict)
			throws PDFPublisherException {

		return unpublish(scope);
	}

	public void unpublish(PDFDocument document)	throws PDFPublisherException {

		throw new UnsupportedOperationException(
				"OnDemandContentManager unsupport PDFDocument class.");
	}

	public List getScopeInfo(Scope scope)	throws PDFPublisherException {

		return getScopeInfo(scope, false, MAX_MAX_HITS);
	}

	public List getScopeInfo(Scope scope, boolean strict)
			throws PDFPublisherException {

		return getScopeInfo(scope, strict, MAX_MAX_HITS);
	}

	public List getScopeInfo(Scope scope, int maxHits)
			throws PDFPublisherException {

		return getScopeInfo(scope, false, maxHits);
	}

	public List getScopeInfo(Scope scope, boolean strict,
			int maxHits) throws PDFPublisherException {

		SortedMap docInfos = getDocInfos(scope, maxHits);
		List pubDocInfos = new ArrayList(docInfos.size());
		Iterator iterator = docInfos.keySet().iterator();

		while (iterator.hasNext()) {
			String docID = (String) iterator.next();
			PublishedDocumentInfo pubDocInfo = new PublishedDocumentInfo();

			pubDocInfo.setID(docID);
			pubDocInfo.setIndex((String) docInfos.get(docID));
			pubDocInfo.setVersion(docVersion);

			pubDocInfos.add(pubDocInfo);
		}

		return pubDocInfos;
	}

	public PublishedDocumentList getDocuments(Scope scope)
			throws PDFPublisherException {

		return getDocuments(scope, false, MAX_MAX_HITS);
	}

	public PublishedDocumentList getDocuments(Scope scope,
			boolean strict) throws PDFPublisherException {

		return getDocuments(scope, strict, MAX_MAX_HITS);
	}

	public PublishedDocumentList getDocuments(Scope scope, int maxHits)
			throws PDFPublisherException {

		return getDocuments(scope, false, maxHits);
	}

	public PublishedDocumentList getDocuments(Scope scope, boolean strict,
			int maxHits) throws PDFPublisherException {

		// TODO: arsdocGet(scope);

		return new EmptyPublishedDocumentList();
	}

	public void reset() throws PDFPublisherException {

		replaceModeInitialized = false;
		nAddedDocuments = 0;
		nUpdatedDocuments = 0;

		checkReplaceMode();

		logger.info(logID + "Resetted.");
	}

	public void flush() throws PDFPublisherException {

		if (!replaceModeInitialized) {
			throw new PDFPublisherException("Flush without reset");
		}

		/*
		 * Phase #1: Unpublishes old documents to be replaced.
		 */
		int nDocsToBeUnpublished = publishedDocsToBeUnpublished.size();

		if (nDocsToBeUnpublished > 0) {
			logger.info(
					  logID
					+ "Unpublishing " + nDocsToBeUnpublished + " old documents "
					+ "in replace mode scope [" + replaceModeScope + "]");

			long ti = System.currentTimeMillis();

			unpublish(publishedDocsToBeUnpublished);

			long tf = System.currentTimeMillis();

			logger.info(
					  logID
					+ nDocsToBeUnpublished + " "
					+ "old documents were unpublished "
					+ "in " + (tf - ti) + " ms.");
		}

		/*
		 * Phase #2: Publishes new documents.
		 */
		int nDocsToBePublished = docsToBePublished.size();

		if (nDocsToBePublished == 0) {
			if (logged) {
				logger.warning(
						  logID
						+ "No documents were added to be published. "
						+ "Command 'arsload' will not be executed.");
			}

			return;
		}

		logger.info(
				  logID
				+ "Publishing " + nDocsToBePublished + " new documents "
				+ "in replace mode scope [" + replaceModeScope + "]");

		/*
		 * Creates partitioned index files to be loaded with arsload.
		 */
		int indexFileNo;

		synchronized (ODCMPDFPublisher.class) {
			indexFileNo = ++ODCMPDFPublisher.indexFileNo;
		}

		int nIndexedDocs = 0;
		String indexFileName = null;
		PrintStream out = null;

		Set docIDs = docsToBePublished.keySet();
		Iterator iterator = docIDs.iterator();

		activeLoaders = new HashMap();
		usedPartitionSize = partitionSize;

		nPartitions =
				(int) Math.ceil(docIDs.size() / (double) usedPartitionSize);

		/*
		 * Adjusts partition size in function of loaders.
		 */
		if (nLoaders > 1 && nPartitions < nLoaders) {
			usedPartitionSize =
					(int) Math.ceil(docIDs.size() / (double) nLoaders);

			if (usedPartitionSize < MIN_PARTITION_SIZE) {
				usedPartitionSize = MIN_PARTITION_SIZE;
			}

			nPartitions =
					(int) Math.ceil(docIDs.size()
							/ (double) usedPartitionSize);
		}

		int width = Integer.toString(nPartitions).length();
		partitionNo = 0;
		interruptedLoad = false;

		try {
			while (iterator.hasNext()) {

				/*
				 * Checks if it must start a new partition.
				 */
				if (nIndexedDocs % usedPartitionSize == 0) {

					/*
					 * Closes partition if there is one opened.
					 */
					if (out != null) {
						out.close();

						out = null;

						startLoader(indexFileName);
					}

					partitionNo++;

					indexFileName =
							  _tmpDir
							+ fileSeparator
							+ "load."
							+ indexFileNo
							+ "."
							+ Padder.lpad(partitionNo, width, '0')
							+ ".ind";

					out = new PrintStream(new FileOutputStream(indexFileName));

					/*
					 * Generates index file's header.
					 */
					out.println("COMMENT: OnDemand Generic Index File Format");

					/*for (int i = 0; i < fields.length; i++) {
						out.println(fields[i].getName());
					}*/
					out.println("COMMENT:");
					out.println("CODEPAGE:1252");
					out.println("COMMENT:");
					out.println("IGNORE_PREPROCESSING:1");
					out.println("IGNORE_DATA_EVAL:1");
					out.println("COMMENT:");
				}

				/*
				 * Indexes current document.
				 */
				String docID = (String) iterator.next();
				IDR idr = (IDR) docsToBePublished.get(docID);
				String[] values = getDocValues(idr.docIndex);

				for (int i = 0; i < values.length; i++) {
					out.println("GROUP_FIELD_NAME:" + fields[i].getName());
					out.println("GROUP_FIELD_VALUE:" + values[i]);
				}
				out.println("GROUP_OFFSET:0");
				out.println("GROUP_LENGTH:0");
				out.println("GROUP_FILENAME:" + idr.pdfFileName);

				nIndexedDocs++;
			}

			/*
			 * Closes last partition if there is one opened.
			 */
			if (out != null) {
				out.close();

				startLoader(indexFileName);
			}

			/*
			 * Waits that all loaders are ended.
			 */
			 waitLoaders();
		} catch (IOException e) {
			if (out != null) {
				out.close();

				File file = new File(indexFileName);

				file.delete();
			}

			logger.log(Level.SEVERE, logID + ">< Flush Exception:", e);

			throw new PDFPublisherException("Flush FAILED", e);
		} catch (InterruptedException e) {

			/*
			 * Stops all active loaders.
			 */
			stopLoaders();

			logger.log(Level.SEVERE, logID + ">< Flush Exception:", e);

			throw new PDFPublisherException("Flush FAILED", e);
		}

		logger.info(logID + "#added documents=" + nAddedDocuments + ".");
		logger.info(logID + "#updated documents=" + nUpdatedDocuments + ".");
		logger.info(logID + "Flushed OK.");
	}

	private void startLoader(String indexFileName) throws InterruptedException {

		Loader loader = new Loader(indexFileName, Thread.currentThread());

		synchronized (activeLoaders) {
			while (activeLoaders.size() >= nLoaders) {
				activeLoaders.wait();
			}

			activeLoaders.put(new Integer(loader.id), loader);
			activeLoaders.notifyAll();
		}

		int nDocuments;

		if (partitionNo < nPartitions) {
			nDocuments = usedPartitionSize;
		} else {
			int remainded = docsToBePublished.size() % usedPartitionSize;

			if (remainded > 0) {
				nDocuments = remainded;
			} else {
				nDocuments = usedPartitionSize;
			}
		}

		logger.info(
				  logID
				+ "Start loader for partition "
				+ partitionNo + "/" + nPartitions + ". "
				+ "(#documents=" + nDocuments + ")");

		loader.start();
	}

	private void waitLoaders() throws InterruptedException {

		synchronized (activeLoaders) {
			while (activeLoaders.size() > 0) {
				activeLoaders.wait();
			}

			activeLoaders.notifyAll();
		}
	}

	private void stopLoaders() {
		interruptedLoad = true;

		synchronized (activeLoaders) {
			Collection loaders = activeLoaders.values();
			Iterator iterator = loaders.iterator();

			while (iterator.hasNext()) {
				Loader loader = (Loader) iterator.next();

				loader.interrupt();
			}
		}
	}

	private class Loader extends Thread {

		private int id;
		private String indexFileName;
		private Thread parentThread;

		private Loader(String indexFileName, Thread parentThread) {
			id = ++loaderID;

			this.indexFileName = indexFileName;
			this.parentThread = parentThread;

			setName("Loader-" + id);
			setDaemon(true);
		}

		public void run() {
			boolean failed = false;

			/*
			 * Publishs documents in IBM OnDemand Content Manager.
			 */
			try {
				arsload(indexFileName);
			} catch (PDFPublisherException e) {
				File file = new File(indexFileName);

				file.delete();

				failed = true;
			} finally {
				synchronized (activeLoaders) {
					activeLoaders.remove(new Integer(id));
					activeLoaders.notifyAll();
				}
			}

			if (failed && !interruptedLoad) {
				parentThread.interrupt();
			}
		}
	}

	/**
	 * Unpublishes all published PDF document instances in the specified
	 * <code>docIDs</code> map. Documents are unpublished in document ID
	 * ascendent order.
	 *  
	 * @param docIDs published PDF document instances to be unpublished.
	 * 
	 * @throws PDFPublisherException If PDF documents cannot be unpublished.
	 */
	public void unpublish(SortedMap docIDs)	throws PDFPublisherException {

		Iterator iterator = docIDs.keySet().iterator();

		while (iterator.hasNext()) {
			String docID = (String) iterator.next();

			arsdocDelete(getDocIDScope(docID));
		}
	}

	public void discard() throws PDFPublisherException {
	}

	public void close() throws PDFPublisherException {

		replaceModeInitialized = false;

		publishedDocsInReplaceModeScope = null;
		publishedDocsToBeUnpublished = null;
		docsToBePublished = null;

		logger.info(logID + "Closed.");
	}

	public void reopen() throws PDFPublisherException {
	}

	private void checkReplaceMode() throws PDFPublisherException {

		if (replaceModeInitialized) {
			return;
		}

		if (replaceModeScope == null) {
			throw new PDFPublisherException("Undefined replace mode scope.");
		}

		if (replaceNoReplaceModes) {
			publishedDocsInReplaceModeScope =
					getDocIDs(replaceModeScope, MAX_MAX_HITS);

			if (logged) {
				logger.info(
						  logID
						+ publishedDocsInReplaceModeScope.size() + " "
						+ "documents were found in replace mode scope "
						+ "[" + replaceModeScope + "].");
			}
		} else { // MODE_UNPUBLISH_ALL.
			unpublish(replaceModeScope);

			publishedDocsInReplaceModeScope = new HashMap();
		}

		publishedDocsToBeUnpublished = new TreeMap();
		docsToBePublished = new HashMap();

		replaceModeInitialized = true;
	}

	private void addDocToBePublished(String docIndex, String pdfFileName)
			throws PDFPublisherException {

		String docID = deriveDocID(docIndex);

		if (docsToBePublished.get(docID) != null) {
			if (logged) {
				logger.warning(
						  logID
						+ "Document '" + docID + "' was "
						+ "added to be published previously. "
						+ "This instance will be ignored.");
			}

			return;
		}

		/*
		 *  Checks published docID for replace and noreplace modes.
		 */
		if (replaceNoReplaceModes) {
			if (publishedDocsInReplaceModeScope.get(docID) != null) {
				if (replaceMode == PublishOptions.MODE_NO_REPLACE) {
					if (logged) {
						logger.warning(
								  logID
								+ "Published document '" + docID + "' "
								+ "will not be republished.");
					}

					return;
				}

				/*
				 * Marks old published document to be unpublished.
				 */
				publishedDocsToBeUnpublished.put(docID, docID);

				nUpdatedDocuments++;
			} else {
				nAddedDocuments++;
			}
		} else {
			nAddedDocuments++;
		}

		docsToBePublished.put(docID, new IDR(docIndex, pdfFileName));
	}

	private SortedMap getDocInfos(Scope scope, int maxHits)
			throws PDFPublisherException {

		String outputFile =	"query." + ++fileNo + ".txt";

		String[] cmdArgs = {
			_onDemandCmdDir + fileSeparator + "arsdoc",	// arsdocPath
			"query",
			"-h",										// server
			server,
			"-u",										// userName
			userName,
			"-p",										// password
			password,
			"-f",										// folderName
			folderName,
			"-G",										// applicationGroupName
			applicationGroupName,
			"-v",
			"-d",										// outputDir
			_tmpDir,
			"-o",										// outputFile
			outputFile,
			"-e",										// field separator
			"/",
			"-N",										// fields order
			fieldsOrder,
			"-L",										// maximum hits
			Integer.toString(maxHits),
			"-i",										// whereClause
			getWhereClause(scope) + " " + orderClause
		};

		outputFile = _tmpDir + fileSeparator + outputFile;

		File file = new File(outputFile);

		if (file.exists()) {
			file.delete();
		}

		SortedMap docInfos = new TreeMap();

		try {
			int rc = executeARSCommand(cmdArgs, false, true);

			if (rc == -2 || rc == 1) {	// NO HITS. //1 en nuevo Ondemand
				return docInfos;
			}

			if (rc != 0) {
				throw new IOException("rc = " + rc);
			}
		} catch (Exception e) {	// checked: IOException, InterruptedException.
			throw new PDFPublisherException(
					"getDocInfos(\"" + scope + "\") FAILED", e);
		}

		/*
		 * Processes output file to extract docIDs.
		 */
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(outputFile));

			String docIndex;

			while ((docIndex = reader.readLine()) != null) {
				try {
					String docID = deriveDocID(docIndex);

					docInfos.put(docID, docIndex);
				} catch (BadDocIndexPDFPublisherException e) {
					logger.warning(
							  logID
							+ "Published document was discarded. "
							+ "[Bad docIndex '" + docIndex + "']");
				}
			}
		} catch (FileNotFoundException e) {
			// No published documents were found.
		} catch (IOException e) {
			throw new PDFPublisherException(
					"getDocInfos(\"" + scope + "\") FAILED", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {}
			}

			if (file.exists()) {
				file.delete();
			}
		}

		return docInfos;
	}

	private Map getDocIDs(Scope scope, int maxHits)
			throws PDFPublisherException {

		String outputFile =	"query." + ++fileNo + ".txt";

		String[] cmdArgs = {
			_onDemandCmdDir + fileSeparator + "arsdoc",	// arsdocPath
			"query",
			"-h",										// server
			server,
			"-u",										// userName
			userName,
			"-p",										// password
			password,
			"-f",										// folderName
			folderName,
			"-G",										// applicationGroupName
			applicationGroupName,
			"-v",
			"-d",										// outputDir
			_tmpDir,
			"-o",										// outputFile
			outputFile,
			"-e",										// field separator
			"/",
			"-N",										// fields order
			docIDFieldsOrder,
			"-L",										// maximum hits
			Integer.toString(maxHits),
			"-i",										// whereClause
			getWhereClause(scope)
		};

		outputFile = _tmpDir + fileSeparator + outputFile;

		File file = new File(outputFile);

		if (file.exists()) {
			file.delete();
		}

		Map docIDs = new HashMap();

		try {
			int rc = executeARSCommand(cmdArgs, false, true);

			if (rc == -2 || rc == 1) {	// NO HITS. //1 en nuevo Ondemand
				return docIDs;
			}

			if (rc != 0) {
				throw new IOException("rc = " + rc);
			}
		} catch (Exception e) {	// checked: IOException, InterruptedException.
			throw new PDFPublisherException(
					"getDocIDs(\"" + scope + "\") FAILED", e);
		}

		/*
		 * Processes output file to extract docIDs.
		 */
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(outputFile));

			String docID;

			while ((docID = reader.readLine()) != null) {
				docID = normalize(docID);

				docIDs.put(docID, docID);
			}
		} catch (FileNotFoundException e) {
			// No published documents were found.
		} catch (IOException e) {
			throw new PDFPublisherException(
					"getDocIDs(\"" + scope + "\") FAILED", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {}
			}

			if (file.exists()) {
				file.delete();
			}
		}

		return docIDs;
	}

	/**
	 * Retrieves all published PDF documents metadata in the specified
	 * <code>scope</code> using <b>arsdoc query</b> line command.
	 * 
	 * @param scope Published PDF documents´s scope to be retrieved.
	 * 
	 * @throws PDFPublisherException If <b>arsdoc query</b> line command fails.
	 */		
	public final List arsdocQuery(Scope scope, int maxHits)
			throws PDFPublisherException {

		return arsdocQuery(scope.toString(), maxHits);
	}

	/**
	 * Retrieves all published PDF documents metadata in the specified
	 * <code>scope</code> using <b>arsdoc query</b> line command.
	 * 
	 * @param scope Published PDF documents´s scope to be retrieved.
	 * 
	 * @throws PDFPublisherException If <b>arsdoc query</b> line command fails.
	 */	
	public final List arsdocQuery(String scope, int maxHits)
			throws PDFPublisherException {

		String outputFile =	"query." + ++fileNo + ".txt";

		String[] cmdArgs = {
			_onDemandCmdDir + fileSeparator + "arsdoc",	// arsdocPath
			"query",
			"-h",										// server
			server,
			"-u",										// userName
			userName,
			"-p",										// password
			password,
			"-f",										// folderName
			folderName,
			"-G",										// applicationGroupName
			applicationGroupName,
			"-v",
			"-d",										// outputDir
			_tmpDir,
			"-o",										// outputFile
			outputFile,
			"-e",										// field separator
			"/",
			"-N",										// fields order
			fieldsOrder,
			"-L",										// maximum hits
			Integer.toString(maxHits),
			"-i",										// whereClause
			getWhereClause(scope) + " " + orderClause
		};

		outputFile = _tmpDir + fileSeparator + outputFile;

		File file = new File(outputFile);

		if (file.exists()) {
			file.delete();
		}

		List docIndexes = new ArrayList();

		try {
			int rc = executeARSCommand(cmdArgs, false, true);

			if (rc == -2 || rc == 1) {	// NO HITS. //1 en nuevo Ondemand
				return docIndexes;
			}

			if (rc != 0) {
				throw new IOException("rc = " + rc);
			}
		} catch (Exception e) {	// checked: IOException, InterruptedException.
			throw new PDFPublisherException(
					"arsdocQuery(\"" + scope + "\") FAILED", e);
		}

		/*
		 * Processes output file to extract docIndexes.
		 */
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(outputFile));

			String line;

			while ((line = reader.readLine()) != null) {
				docIndexes.add(line);
			}
		} catch (FileNotFoundException e) {
			// No published documents were found.
		} catch (IOException e) {
			throw new PDFPublisherException(
					"arsdocQuery(\"" + scope + "\") FAILED", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {}
			}

			if (file.exists()) {
				file.delete();
			}
		}

		return docIndexes;
	}

	/**
	 * Deletes all published PDF documents in the specified <code>scope</code>
	 * using <b>arsdoc delete</b> line command.
	 * 
	 * @param scope Published PDF documents´s scope to be deleted.
	 * 
	 * @throws PDFPublisherException If <b>arsdoc delete</b> line command fails.
	 */
	public final void arsdocDelete(Scope scope) throws PDFPublisherException {

		arsdocDelete(scope.toString());
	}

	/**
	 * Deletes all published PDF documents in the specified <code>scope</code>
	 * using <b>arsdoc delete</b> line command.
	 * 
	 * @param scope Published PDF documents´s scope to be deleted.
	 * 
	 * @throws PDFPublisherException If <b>arsdoc delete</b> line command fails.
	 */	
	public final void arsdocDelete(String scope) throws PDFPublisherException {

		String[] cmdArgs = {
			_onDemandCmdDir + fileSeparator + "arsdoc",	// arsdocPath
			"delete",
			"-h",										// server
			server,
			"-u",										// userName
			userName,
			"-p",										// password
			password,
			"-f",										// folderName
			folderName,
			"-G",										// applicationGroupName
			applicationGroupName,
			"-v",
			"-i",										// whereClause
			getWhereClause(scope)
		};

		try {
			int rc = executeARSCommand(cmdArgs, false, true);

			if (rc != 0) {
				throw new IOException("rc = " + rc);
			}
		} catch (Exception e) {	// checked: IOException, InterruptedException.
			throw new PDFPublisherException(
					"arsdocDelete(\"" + scope + "\") FAILED", e);
		}
	}

	/**
	 * Loads all PDF documents specified in the ODCM index file
	 * <code>indexFileName</code> using <b>arsload</b> line command.
	 * 
	 * <p> If {@link #backupIndexFile} control flag is enabled then this
	 * method will backup ODCM index file in {@link #tmpDir} directory.
	 * </p>
	 * 
	 * @param indexFileName ODCM index file pathname.
	 * 
	 * @throws PDFPublisherException If <b>arsload</b> line command fails.
	 */
	public final void arsload(String indexFileName)
			throws PDFPublisherException {

		logger.info("backup Index File: " + backupIndexFile);
		if (backupIndexFile) {
			try {
				FileUtils.copyFile(indexFileName, indexFileName + ".bak");
			} catch (IOException e) {
				logger.warning(
						"Cannot backup index file '" + indexFileName + "'.");
			}
		}

		/*
		 * Trims file extension '.ind' if it's present.
		 */
		if (indexFileName.toLowerCase().endsWith(".ind")) {
			indexFileName =
					indexFileName.substring(0, indexFileName.length() - 4);
		}

		String[] cmdArgs = {
			_onDemandCmdDir + fileSeparator + "arsload", // arsloadPath
			"-h",										 // server
			server,
			"-u",										 // userName
			userName,
			"-p",										 // password
			password,
			"-a",										 // applicationName
			applicationName,
			"-g",										 // applicationGroupName
			applicationGroupName,
			"-f",
			"-v",
			indexFileName								 // indexFileName (.ind)
		};

		try {
			int rc = executeARSCommand(cmdArgs, true, true);

			if (rc != 0) {
				throw new IOException("rc = " + rc);
			}
		} catch (Exception e) {	// checked: IOException, InterruptedException.
			throw new PDFPublisherException(
					"arsload(\"" + indexFileName + "\") FAILED", e);
		}
	}

	/**
	 * Retrieves all published PDF documents content in the specified
	 * <code>scope</code> using <b>arsdoc get</b> line command.
	 * 
	 * <p><b>IMPORTANT NOTE: This method is not fully implemented. It returns
	 * a empty list always</b>.
	 * </p>
	 * 
	 * @param scope Published PDF documents´s scope to be retrieved.
	 * 
	 * @throws PDFPublisherException If <b>arsdoc get</b> line command fails.
	 */	
	public final List arsdocGet(Scope scope) throws PDFPublisherException {

		return arsdocGet(scope.toString());
	}

	/**
	 * Retrieves all published PDF documents content in the specified
	 * <code>scope</code> using <b>arsdoc get</b> line command.
	 * 
	 * <p><b>IMPORTANT NOTE: This method is not fully implemented. It returns
	 * a empty list always</b>.
	 * </p>
	 * 
	 * @param scope Published PDF documents´s scope to be retrieved.
	 * 
	 * @throws PDFPublisherException If <b>arsdoc get</b> line command fails.
	 */	
	public final List arsdocGet(String scope) throws PDFPublisherException {

		String outputFile =	"get." + ++fileNo + ".dat";

		String[] cmdArgs = {
			_onDemandCmdDir + fileSeparator + "arsdoc",	// arsdocPath
			"get",
			"-h",										// server
			server,
			"-u",										// userName
			userName,
			"-p",										// password
			password,
			"-f",										// folderName
			folderName,
			"-G",										// applicationGroupName
			applicationGroupName,
			"-v",
			"-d",										// outputDir
			_tmpDir,
			"-o",										// outputFile
			outputFile,
			"-i",										// whereClause
			getWhereClause(scope) + " " + orderClause
		};

		outputFile = _tmpDir + fileSeparator + outputFile;

		List publishedDocuments = new ArrayList();

		try {
			int rc = executeARSCommand(cmdArgs, false, true);

			if (rc == -2 || rc == 1) {	// NO HITS. //1 en nuevo Ondemand
				return publishedDocuments;
			}

			if (rc != 0) {
				throw new IOException("rc = " + rc);
			}
		} catch (Exception e) {	// checked: IOException, InterruptedException.
			throw new PDFPublisherException(
					"arsdocGet(\"" + scope + "\") FAILED", e);
		}

		/*
		 * Processes output files to extract docIDs and contents.
		 */
/* TODO
		File file = new File(outputFile);

		if (file.exists()) {
			file.delete();
		}
*/

		return publishedDocuments;
	}

	private String getWhereClause(Scope scope) {
		return getWhereClause(scope.toString());
	}

	private String getWhereClause(String scope) {
		if (scope.equals("")) {
			scope = "1 = 1";
		}

		String whereClause = "WHERE (" + scope + ")";

		if (hiddenPredicate != null && !hiddenPredicate.equals("")) {
			whereClause += " AND " + hiddenPredicate;
		}

		// System.out.println("* scope: " + scope);
		// System.out.println("* whereClause: " + whereClause);

		return whereClause;
	}

	private ByteArrayOutputStream[] getOutputStreams(boolean stdout,
			boolean stderr) {

		ByteArrayOutputStream[] outputStreams = new ByteArrayOutputStream[2];

		if (stdout) {
			outputStreams[0] = new ByteArrayOutputStream();
		}

		if (stderr) {
			outputStreams[1] = new ByteArrayOutputStream();
		}

		return outputStreams;
	}

	private int executeARSCommand(String[] args, boolean stdout,
			boolean stderr)	throws IOException, InterruptedException {

		ByteArrayOutputStream[] outputStreams =
				getOutputStreams(stdout, stderr);

		int cmdNo;

		synchronized (ODCMPDFPublisher.class) {
			cmdNo = ++ODCMPDFPublisher.cmdNo;
		}

		String cmd = "";
		boolean hide = false;

		for (int i = 0; i < args.length; i++) {
			if (hide) {
				cmd += "********";
			} else {
				cmd += args[i];
			}

			if (i + 1 < args.length) {
				cmd += " ";
			}

			hide = args[i].equals("-u") || args[i].equals("-p");
		}

		logger.info(
				  logID + "[" + cmdNo + "] "
				+ "Executing command \"" + cmd + "\" ...");

		long ti = System.currentTimeMillis();
		Runtime rt = Runtime.getRuntime();
		Process process = rt.exec(args);

		logger.info(logID + "[" + cmdNo + "] Waiting cmd response ... ");

		if (stdout) {
			cpio(process, true, outputStreams[0]);
			log(cmdNo, outputStreams[0]);
		}

		if (stderr) {
			cpio(process, false, outputStreams[1]);
			log(cmdNo, outputStreams[1]);
		}

		process.waitFor();

		int rc = process.exitValue();
		long tf = System.currentTimeMillis();

		logger.info(
				  logID + "[" + cmdNo + "] "
				+ "Command FINISHED with rc=" + rc + " "
				+ "in " + (tf - ti) + " ms.");

		return rc;
	}

	private void log(int cmdNo, ByteArrayOutputStream output) {
		BufferedReader reader = null;

		try {
			reader =
					new BufferedReader(
							new InputStreamReader(
									new ByteArrayInputStream(
											output.toByteArray())));

			String line;

			while ((line = reader.readLine()) != null) {
				if (!line.equals("")) {
					logger.info(logID + "[" + cmdNo + "] " + line);
				}
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, logID + ">< Unexpected exception:", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {}
			}
		}
	}

	private static void cpio(Process process, boolean stdout,
			ByteArrayOutputStream output) throws IOException {

		InputStream input =
				(stdout)
						? process.getInputStream()
						: process.getErrorStream();

		try {
			cpio(input, output);
		} finally {
			try {
				input.close();
			} catch (IOException e) {}
		}
	}

	private static void cpio(InputStream input, ByteArrayOutputStream output)
			throws IOException {

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesReaded;

		while ((bytesReaded = input.read(buffer)) != -1) {
			output.write(buffer, 0, bytesReaded);
			// System.out.write(buffer, 0, bytesReaded);
		}
	}

	private static String translateToLocalPath(String path) {
		if (fileSeparator.equals("\\")) {
			return path.replaceAll("/", "\\\\");
		}

		return path;
	}

	private class IDR {

		private String docIndex;
		private String pdfFileName;

		private IDR(String docIndex, String pdfFileName) {
			this.docIndex = docIndex;
			this.pdfFileName = pdfFileName;
		}
	}
}
