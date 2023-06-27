

/*
 * @(#) FPGIntegratedPDFPublisher.java    1.0 30-06-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.implementations.fpg;


import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.logging.Logger;

import javax.sql.DataSource;

import cl.araucana.core.util.JDBCUtils;

import cl.araucana.core.util.logging.LogManager;

import cl.araucana.fpg.PDFDocument;
import cl.araucana.fpg.PDFTemplate;

import cl.araucana.pdf.publishers.Field;
import cl.araucana.pdf.publishers.PDFPublisher;
import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.PublishedDocumentList;
import cl.araucana.pdf.publishers.Publisher;
import cl.araucana.pdf.publishers.PublishOptions;
import cl.araucana.pdf.publishers.Scope;


/**
 * This class implements a <b>PDF Publisher</b> integrated to framework
 * <i>FPG</i> encapsulating and adapting a {@link FPGIntegratedPDFPublisherSPI}
 * instance that provides the corresponding <b>Service Provider Interface</b>
 * (SPI). It exposes only neccesary functionalities to be a
 * <b>PDF Publisher</b> plus few additional utility methods.
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
 *            <TD> 30-06-2008 </TD>
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
public final class FPGIntegratedPDFPublisher extends PDFPublisher {

	/**
	 * Maximum maximum hits supported to retrieval operations. 
	 */
	public static final int MAX_MAX_HITS = Integer.MAX_VALUE;

	private static Logger logger = LogManager.getLogger();

	private static final Map supportedFeatures;

	private String logID;
	private FPGIntegratedPDFPublisherSPI spi;

	private int replaceMode;
	private int strategy;
	private String remark;

	private boolean batchStopOnFirstFault;

	private int publicationID;
	private int selectionID;

	private DocType $docType;
	private DocField[] docFields;

	static {
		supportedFeatures = new HashMap();

		supportedFeatures.put("fpg",          Boolean.TRUE);
		supportedFeatures.put("docVersion",   Boolean.TRUE);
		supportedFeatures.put("strict",       Boolean.TRUE);
		supportedFeatures.put("partitioning", Boolean.TRUE);
		supportedFeatures.put("compressing",  Boolean.TRUE);
		supportedFeatures.put("batch",        Boolean.TRUE);
	}

	/**
	 * Gets document type names registered in the specified
	 * <code>publisher</code>. Publisher must be configured to be used by
	 * <b>FPG PDF Publisher</b> type.
	 * 
	 * @param publisher Publisher.
	 * 
	 * @return Array of registered document type names.
	 * 
	 * @throws PDFPublisherException If cannot get document type names.
	 */
	public static String[] getDocTypeNames(Publisher publisher)
			throws PDFPublisherException {

		DocType[] docTypes = getDocTypes(publisher);
		String[] docTypeNames = new String[docTypes.length];

		for (int i = 0; i < docTypes.length; i++) {
			docTypeNames[i] = docTypes[i].getName();
		}

		return docTypeNames;
	}

	/**
	 * Gets document type registered in the specified <code>publisher</code>.
	 * Publisher must be configured to be used by <b>FPG PDF Publisher</b> type.
	 * 
	 * @param publisher Publisher.
	 * 
	 * @return Array of registered document types.
	 * 
	 * @throws PDFPublisherException If cannot get document types.
	 */	
	public static DocType[] getDocTypes(Publisher publisher)
			throws PDFPublisherException {

		String pubName = publisher.getName();

		String dataSourceName =	publisher.getPropertyValue("dataSourceName");

		if (dataSourceName == null) {
			throw new PDFPublisherException(
					"dataSourceName property is mandatory.");
		}

		String schemaName = publisher.getPropertyValue("schemaName");

		if (schemaName == null) {
			throw new PDFPublisherException(
					"schemaName property is mandatory.");
		}

		DataSource dataSource;

		try {
			dataSource = JDBCUtils.getDataSource(dataSourceName);
		} catch(SQLException e) {
			throw new PDFPublisherException(
					"Cannot open FPG spi '" + pubName + "'", e);
		}

		FPGIntegratedPDFPublisherSPI spi = null;
		DocType[] docTypes = null;

		try {
			spi = new FPGIntegratedPDFPublisherSPI(dataSource, schemaName);
			docTypes = spi.getDocTypes();
		} finally {
			if (spi != null) {
				spi.disconnect();
			}
		}

		return docTypes;
	}

	/**
	 * Creates an ad-hoc instance of <b>FPG PDF Publisher</b> according to
	 * specified publish options in <code>options</code>. A connection
	 * is opened to the corresponding documental repository.
	 * 
	 * @param docType Document type.
	 * 
	 * @param docVersion Document type's version.
	 * 
	 * @param options Options to the <b>FPG PDF Publisher</b>.
	 * 
	 * @throws PDFPublisherException If <code>options</code> is
	 *         <code>null</code>
	 *         or the named publisher in options is unknown
	 *         or cannot create a new <b>FPG PDF Publisher</b> instance.
	 */	
	public FPGIntegratedPDFPublisher(String docType, int docVersion,
			PublishOptions options) throws PDFPublisherException {

		super(docType, docVersion, options);

		logID = options.getLogID();
		replaceMode = options.getReplaceMode();
		strategy = options.getStrategy();
		remark = options.getRemark();

		if (logID == null) {
			logID = "";
		} else {
			logID = "[" + logID + "] ";
		}

		String dataSourceName = getPropertyValue("dataSourceName");

		if (dataSourceName == null) {
			throw new PDFPublisherException(
					"dataSourceName property is mandatory.");
		}

		String schemaName = getPropertyValue("schemaName");

		if (schemaName == null) {
			throw new PDFPublisherException(
					"schemaName property is mandatory.");
		}

		String sBatchStopOnFirstFault =
				getPropertyValue("batchStopOnFirstFault");

		if (sBatchStopOnFirstFault == null) {
			throw new PDFPublisherException(
					"batchStopOnFirstFault property is mandatory.");
		}

		if (!sBatchStopOnFirstFault.equals("true")
				&& !sBatchStopOnFirstFault.equals("false")) {

			throw new PDFPublisherException(
					  "Invalid batchStopOnFirstFault property value "
					+ "'" + sBatchStopOnFirstFault + "'.");
		}

		batchStopOnFirstFault =
				new Boolean(sBatchStopOnFirstFault).booleanValue();

		logger.config(logID + "dataSourceName        = " + dataSourceName);
		logger.config(logID + "schemaName            = " + schemaName);

		logger.config(
				logID + "batchStopOnFirstFault = " + batchStopOnFirstFault);

		try {
			DataSource dataSource = JDBCUtils.getDataSource(dataSourceName);

			spi = new FPGIntegratedPDFPublisherSPI(dataSource, schemaName);

			String sourceSystemName = getPropertyValue("sourceSystemName");

			if (sourceSystemName != null) {
				logger.config(
						logID + "sourceSystemName      = " + sourceSystemName);

				spi.setSourceSystemName(sourceSystemName);
			}

			String publisherName = getPropertyValue("publisherName");

			if (publisherName != null) {
				logger.config(
						logID + "publisherName         = " + publisherName);

				spi.setPublisherName(publisherName);
			}

			if (options.isReadOnly()) {
				if (docVersion != 0) {
					if (!spi.isRegisteredDocVersion(docType, docVersion)) {
						throw new PDFPublisherException(
								"Unregistered document version "
								+ "'" + docVersion + "' for document type "
								+ "'" + docType + "'");
					}
				}
			} else if (docVersion != 0) {
				if (!spi.isRegisteredDocVersion(docType, docVersion)) {
					PDFTemplate template =
							options.getPublisher().getPDFTemplate();

					String remark =
							  template.getName()
							+ ": "
							+ template.getDocDescription();

					spi.registerDocVersion(
							docType,
							docVersion,
							remark,
							template.getFixedObjectsContent(),
							template.getFixedXRefContent());
				}
			}
		} catch (Exception e) {
			throw new PDFPublisherException(
					"Publisher cannot be initialized", e);
		}

		/*
		 *  Translates 'ndate' fields to 'int' ones.
		 */
		translateNDateFieldsToIntOnes(fields);
		translateNDateFieldsToIntOnes(docIDFields);
	}

	/**
	 * Gets wrapped @link FPGIntegratedPDFPublisherSPI} instance by this
	 * <b>FPG PDF Publisher</b>. This instance wouldn't to be used in general,
	 * but it's available to special cases requiring it.
	 * 
	 * @return Wrapped spi instance.
	 * 
	 * @throws PDFPublisherException If cannot get spi instance.
	 */
	public FPGIntegratedPDFPublisherSPI getSPI() throws PDFPublisherException {

		return spi;
	}

	/**
	 * Gets document type assigned to this <b>FPG PDF Publisher</b>.
	 * 
	 * @return Document type instance.
	 * 
	 * @throws PDFPublisherException If cannot get document type instance.
	 */
	public DocType get$DocType() throws PDFPublisherException {

		/*
		 *  Caches document type.
		 */
		if ($docType != null) {
			return $docType;
		}

		return $docType = spi.getDocType(docType);
	}

	/**
	 * Gets <i>FPG</i> document fields to the document type assigned to this
	 * <b>FPG PDF Publisher</b>.
	 * 
	 * @return Array of <i>FPG</i> document fields.
	 * 
	 * @throws PDFPublisherException
	 */
	public DocField[] getDocFields() throws PDFPublisherException {

		/*
		 *  Caches document fields.
		 */
		if (docFields != null) {
			return docFields;
		}

		return docFields = spi.getDocFields(docType);
	}

	public void publish(PDFDocument document) throws PDFPublisherException {

		spi.publish(document);
	}

	public void publish(String docIndex, String pdfFileName)
			throws PDFPublisherException {

		spi.publish(docIndex, pdfFileName);
	}

	public int unpublish(Scope scope) throws PDFPublisherException {

		return unpublish(scope, false);
	}

	public int unpublish(Scope scope, boolean strict)
			throws PDFPublisherException {

		return spi.unpublish(docType, (strict) ? docVersion : 0, scope);
	}

	public void unpublish(PDFDocument document)	throws PDFPublisherException {

		unpublish(getDocIDScope(document.getID()), false);
	}

	public List getScopeInfo(Scope scope) throws PDFPublisherException {

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

		if (selectionID == 0) {
			selectionID = spi.openSelectionContext(docType, docVersion);

			logger.finest(
					  logID
					+ "selectionContext " + selectionID + " "
					+ "setted. [getScopeInfo]");
		}

		return spi.getScopeInfo(scope, strict, maxHits, filter);
	}

	public boolean supportsFeature(String name) {
		Boolean supported = (Boolean) supportedFeatures.get(name);

		return supported != null && supported.booleanValue();
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

		if (selectionID == 0) {
			selectionID = spi.openSelectionContext(docType, docVersion);

			logger.finest(
					  logID
					+ "selectionContext " + selectionID + " "
					+ "setted. [getDocuments]");
		}

		return spi.getDocuments(scope, strict, maxHits, filter);
	}

	public void reset() throws PDFPublisherException {

		publicationID =
				spi.openPublicationContext(
						docType, docVersion, replaceMode, strategy, remark,
						batchStopOnFirstFault);
	}

	public void flush() throws PDFPublisherException {

		int[] pubStats = spi.closePublicationContext(true);

		logger.info(
				  logID
				+ "publication: id=" + publicationID + " OK. "
				+ "docType=" + docType + " "
				+ "#addedDocuments=" + pubStats[0] + " "
				+ "#updatedDocuments=" + pubStats[1] + ".");
	}

	public void discard() throws PDFPublisherException {

		spi.closePublicationContext(false);
	}

	public void reopen() throws PDFPublisherException {

		spi.reconnect();

		logger.info(logID + "Reopened.");
	}

	public void close() throws PDFPublisherException {

		spi.disconnect();

		logger.info(logID + "Closed.");
	}

	private void translateNDateFieldsToIntOnes(Field[] fields) {
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];

			if (field.getType() == Field.TYPE_NDATE) {
				logger.warning(
						  "Field type for '" + field.getName() + "' "
						+ "was translated from 'ndate' to 'int'.");

				field.setType(Field.TYPE_INT);
			}
		}
	}
}
