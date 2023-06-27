

/*
 * @(#) PDFPublisher.java    1.0 02-06-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers;


import java.lang.reflect.Constructor;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.core.util.Mapping;
import cl.araucana.core.util.Property;

import cl.araucana.fpg.PDFDocument;


/**
 * An abstract class to represent a general <b>PDF Publisher</b> capable of
 * publish, unpublish and retrieve indexed PDF documents from a documental
 * repository.
 * 
 * <p> PDF documents are organized in different document types. A particular
 * document type will have many PDF document instances and each one of them
 * has an unique ID named <b>docID</b>, a value to be indexed (to
 * support retrieval operations) named <b>docIndex</b> and a version to
 * represent an specific layout/format in the lifetime of a document type that
 * reflect a level of changes made with it in the time. 
 * </p>
 *  
 * <p> <b>docID</b> and <b>docIndex</b> values must complaint with the syntax
 * defined in the {@link cl.araucana.fpg.PDFDocument} general interface.
 * </p>
 * 
 * <p> Document version support is optional to a specific implementation of
 * <b>PDF Publisher</b>. In this case PDF document versions will be always zero.
 * </p>
 *
 * <p>By default a <b>PDF Publisher</b> will realize its operations assuming
 * the document type and version specified to it in its constructor or
 * {@link #newPDFPublisher(String, int,	PublishOptions)} method. Some operations
 * can support the <u>strict document version</u> flag to control if different
 * document versions are wanted to the corresponding document type.
 * </p>
 * 
 * <p> Retrieval operations are affected by a filter in order to determine
 * if a PDF document instance is retrieve finally. This filter permits to
 * implement diverse external access control mechanisms that a particular
 * application can require. By default the filter applied permits access to
 * <strong>all PDF document instances retrieved internally</strong>. To change
 * this filter see {@link #setFilter(BasePDFPublisherFilter)}.
 * </p>
 * 
 * <p> A <b>PDF Publisher</b> can support zero or more the following general
 * features:
 * 
 * <BR>
 * <BR>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="60%" CELLPADDING="3" CELLSPACING="0">
 *     <tr>
 *        <th bgcolor="black">
 *            <font color="white"><strong>Feature/strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>Description</strong></font>
 *        </th>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>fpg</strong>
 *        </td>
 *        
 *        <td>
 *            Publisher is integrated to <strong>FPG</strong> framework.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>docVersion</strong>
 *        </td>
 *        
 *        <td>
 *            Different document versions for a same document type.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>strict</strong>
 *        </td>
 *        
 *        <td>
 *            Strict document version logic is available.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>partitioning</strong>
 *        </td>
 *        
 *        <td>
 *            Partitioned PDF documents can be published.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>compressing</strong>
 *        </td>
 *        
 *        <td>
 *            Non partitioned PDF documents are compressed in publication
 *            operations.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *             <strong>batch</strong>
 *        </td>
 *        
 *        <td>
 *             Batch publication with transactional behaviour.
 *        </td>
 *     </tr>  
 * </TABLE>
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
 * 
 * @see		cl.araucana.pdf.publishers.PublisherManager
 */
public abstract class PDFPublisher {

	/**
	 * Supportable features. Each specific <b>PDF Publisher</b> can support
	 * zero or more of them. 
	 */
	public static final String[] supportableFeatureNames = {
		"fpg",
		"docVersion",
		"strict",
		"partitioning",
		"compressing",
		"batch"
	};

	private static Pattern pattern;

	/**
	 *  Document type.
	 */
	protected String docType;
	
	/**
	 * Document version. 
	 */
	protected int docVersion;
	
	/**
	 *  General options to publish operations.  
	 */
	protected PublishOptions options;

	/**
	 *  Publisher properties. 
	 */
	protected Publisher publisher;
	
	/**
	 * Document type's index. 
	 */
	protected Index docTypeIndex;
	
	/**
	 *  Document type's mapped index. 
	 */
	protected MappedIndex docTypeMappedIndex;

	/**
	 *  Document type fields. 
	 */
	protected Field[] fields;
	
	/**
	 *  Scope uses to publish with replace mode options. 
	 */
	protected Scope replaceModeScope;

	/**
	 *  Document ID's fields. 
	 */
	protected Field[] docIDFields;
	
	/**
	 * Document ID's field indexes. 
	 */
	protected int[] docIDFieldIndexes;

	/**
	 *  Filter to use in retrieval operations. 
	 */
	protected BasePDFPublisherFilter filter;

	static {
		try {
			pattern = Pattern.compile("/");
		} catch (PatternSyntaxException e) {}
	}

	/**
	 * Creates an ad-hoc instance of <b>PDF Publisher</b> according to
	 * specified publish options in <code>options</code>. A connection
	 * is opened to the corresponding documental repository.
	 * 
	 * @param docType Document type.
	 * 
	 * @param docVersion Document type's version.
	 * 
	 * @param options Options to the required <b>PDF Publisher</b>.
	 * 
	 * @return An ad-hoc instance of required <b>PDF Publisher</b>.
	 *  
	 * @throws PDFPublisherException If <code>options</code> is
	 *         <code>null</code>
	 *         or the named publisher in options is unknown
	 *         or cannot create a new <b>PDF Publisher</b> instance.
	 */
	public static PDFPublisher newPDFPublisher(String docType, int docVersion,
			PublishOptions options)	throws PDFPublisherException {

		if (options == null) {
			throw new PDFPublisherException(
					"Publish options must be specified.");
		}

		Publisher publisher = options.getPublisher();

		if (publisher == null) {
			throw new PDFPublisherException("Undefined publisher.");
		}

		try {
			Class clazz = publisher.getType();

			Constructor constructor =
					clazz.getConstructor(
							new Class[] {
									String.class,
									int.class,
									PublishOptions.class});

			PDFPublisher pdfPublisher =
					(PDFPublisher) constructor.newInstance(
							new Object[] {
									docType,
									new Integer(docVersion),
									options});

			return pdfPublisher;
		} catch (Exception e) {
			throw new PDFPublisherException(
					"Cannot create new pdf publisher instance.", e);
		}
	}

	protected PDFPublisher(String docType, int docVersion,
			PublishOptions options) throws PDFPublisherException {

		if (docType == null) {
			throw new PDFPublisherException("Undefined document type.");
		}

		this.docType = docType;
		this.docVersion = docVersion;
		this.options = options;

		publisher = options.getPublisher();

		if (publisher == null) {
			throw new PDFPublisherException("Undefined publisher.");
		}

		docTypeIndex = publisher.getIndex(docType);

		if (docTypeIndex == null) {
			throw new PDFPublisherException(
					"Missed index for document type '" + docType + "'.");
		}

		List fields = docTypeIndex.getFields();

		this.fields = new Field[fields.size()];

		for (int i = 0; i < fields.size(); i++) {
			Field field = (Field) fields.get(i);

			this.fields[i] = field;
		}

		docTypeMappedIndex = publisher.getMappedIndex(docType);

		if (docTypeMappedIndex == null) {
			docTypeMappedIndex = new MappedIndex(docType);

			for (int i = 0; i < this.fields.length; i++) {
				Field field = this.fields[i];

				Mapping mapping =
						new Mapping(field.getName(), field.getName());

				docTypeMappedIndex.addMapping(mapping);
			}
		}

		docIDFieldIndexes = docTypeIndex.getDocIDFieldIndexes();
		docIDFields = new Field[docIDFieldIndexes.length];

		for (int i = 0; i < docIDFields.length; i++) {
			docIDFields[i] = (Field) fields.get(docIDFieldIndexes[i]);
		}

		setFilter(new UnrestrictedPDFPublisherFilter());
	}

	/**
	 * Gets the value of specified property to this <b>PDF Publisher</b>.
	 *  
	 * @param name Property name.
	 * 
	 * @return Property value.
	 * 
	 * @throws PDFPublisherException If property value cannot be obtained.
	 * 
	 * @see		#getPropertyValue(String, String)
	 */
	protected String getPropertyValue(String name)
			throws PDFPublisherException {

		return getPropertyValue(name, null);
	}

	/**
	 * Gets the value of specified property to this <b>PDF Publisher</b>.
	 * If property is undefined the value returned is
	 * <code>defaultValue</code>.
	 * 
	 * @param name Property name.
	 * 
	 * @param defaultValue Property default value.
	 * 
	 * @return Property value.
	 * 
	 * @throws PDFPublisherException If property is undefined
	 *         and <code>defaultValue<code> is <code>null</code>.
	 *
	 * @see		#getPropertyValue(String)
	 */
	protected String getPropertyValue(String name, String defaultValue)
			throws PDFPublisherException {

		Property property = publisher.getProperty(name);

		if (property == null) {
			if (defaultValue == null) {
				throw new PDFPublisherException(
						"Missed property '" + name + "'.");
			}

			return defaultValue;
		}

		return property.getValue();
	}

	/**
	 * Gets the value of specified document index's property to this
	 * <b>PDF Publisher</b>.
	 *  
	 * @param name Property name.
	 * 
	 * @return Property value.
	 * 
	 * @throws PDFPublisherException If property value cannot be obtained.
	 * 
	 * @see		#getIndexPropertyValue(String, String)
	 */
	protected String getIndexPropertyValue(String name)
			throws PDFPublisherException {

		return getIndexPropertyValue(name, null);
	}

	/**
	 * Gets the value of specified document index's property to this
	 * <b>PDF Publisher</b>. If property is undefined the value returned is
	 * <code>defaultValue</code>.
	 * 
	 * @param name Property name.
	 * 
	 * @param defaultValue Property default value.
	 * 
	 * @return Property value.
	 * 
	 * @throws PDFPublisherException If property is undefined
	 *         and <code>defaultValue<code> is <code>null</code>.
	 *
	 * @see		#getIndexPropertyValue(String)
	 */
	protected String getIndexPropertyValue(String name, String defaultValue)
			throws PDFPublisherException {

		Property property = docTypeIndex.getProperty(name);

		if (property == null) {
			if (defaultValue == null) {
				throw new PDFPublisherException(
						  "Missed property '" + name + "' "
						+ "for index '" + docType + "'.");
			}

			return defaultValue;
		}

		return property.getValue();
	}

	/**
	 * Parses the string <code>s</code> to obtain component parts according
	 * to the syntaxis used to document IDs and indexes.
	 * 
	 * @param s String to be parsed.
	 * 
	 * @return array of component parts.
	 */
	protected String[] getDocValues(String s) {

		String[] values = pattern.split(s);

		for (int i = 0; i < values.length; i++) {
			values[i] = values[i].trim();
		}

		return values;
	}

	/**
	 * Nomalizes the string <code>docID</code> trimming space characters in its
	 * component parts.
	 * 
	 * @param docID Unnormalized document ID.
	 * 
	 * @return Normalized document ID.
	 */
	protected String normalize(String docID) {
		String[] values = pattern.split(docID);
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < values.length; i++) {
			sb.append(values[i].trim());

			if (i + 1 < values.length) {
				sb.append("/");
			}
		}

		return sb.toString();
	}

	/**
	 * Gets document type associated to this <b>PDF Publisher</b>.
	 *  
	 * @return document type.
	 */
	public String getDocType() {
		return docType;
	}

	/**
	 * Gets document type's versions associated to this <b>PDF Publisher</b>.
	 *  
	 * @return document type.
	 */
	public int getDocVersion() {
		return docVersion;
	}

	/**
	 * Gets publish options used by this <b>PDF Publisher</b>.
	 *  
	 * @return document type.
	 */
	public PublishOptions getPublishOptions() {
		return options;
	}

	/**
	 * Derives the document ID to a PDF document from its index.
	 * 
	 * @param docIndex Document index value.
	 * 
	 * @return Document ID.
	 * 
	 * @throws BadDocIndexPDFPublisherException If <code>docIndex</code>
	 *         no match with expected number of document fields. 
	 */
	public String deriveDocID(String docIndex)
			throws BadDocIndexPDFPublisherException {

		String[] values = getDocValues(docIndex);

		if (values.length != fields.length) {
			throw new BadDocIndexPDFPublisherException(docIndex);
		}

		String docID = "";

		for (int i = 0; i < docIDFieldIndexes.length; i++) {
			docID += values[docIDFieldIndexes[i]];

			if (i + 1 < docIDFieldIndexes.length) {
				docID += "/";
			}
		}

		return docID;
	}

	/**
	 * Gets the <code>scope</code> to use during a publication in replace
	 * mode to replace old published documents. A specific <b>PDF Publisher</b>
	 * implementation could not require to use or support replace mode.
	 * 
	 * @param scope Replace mode scope.
	 * 
	 * @see #getReplaceModeScope()
	 * 
	 */
	public void setReplaceModeScope(Scope scope) {
		replaceModeScope = scope;
	}

	/**
	 * Gets the replace mode scope.
	 *  
	 * @return Replace mode scope.
	 * 
	 * @see #setReplaceModeScope(Scope)
	 */
	public Scope getReplaceModeScope() {
		return replaceModeScope;
	}

	/**
	 * Translates a document ID to its corresponding scope without prefix.
	 * 
	 * <p> It's equivalent to call to <code>getDocIDScope(docID, "")</code>.</p>
	 * 
	 * @param docID Document ID.
	 * 
	 * @return document ID's scope.
	 * 
	 * @throws PDFPublisherException If document ID is invalid.
	 * 
	 * @see #getDocIDScope(String, String)
	 */
	public Scope getDocIDScope(String docID) throws PDFPublisherException {

		return getDocIDScope(docID, "");
	}

	/**
	 * Translates a document ID to its corresponding scope using
	 * <code>prefix</code> to qualify each one of its fields.
	 * 
	 * @param docID Document ID.
	 * @param prefix Prefix to be applied.
	 * 
	 * @return document ID's scope.
	 * 
	 * @throws PDFPublisherException If document ID is invalid.
	 */
	public Scope getDocIDScope(String docID, String prefix)
			throws PDFPublisherException {

		String[] values = getDocValues(docID);

		if (values.length != docIDFields.length) {
			throw new PDFPublisherException("Bad document ID '" + docID + "'.");
		}

		Object[] $values = new Object[docIDFields.length];

		try {
			for (int i = 0; i < docIDFields.length; i++) {
				Field field = docIDFields[i];
				int value;

				switch (field.getType()) {
					case Field.TYPE_INT:
						$values[i] = new Integer(Integer.parseInt(values[i]));

						break;

					case Field.TYPE_DATE:
						$values[i] = new AbsoluteDate(values[i]);

						break;

					case Field.TYPE_NDATE:
						if (values[i].charAt(0) == '#') {	// #99999.
							value = Integer.parseInt(values[i]);

							if (value <= 0) {
								throw new Exception(
										  "Invalid ndate value "
										+ "'" + values[i] + "'.");
							}

							$values[i] = values[i];
						} else if (values[i].length() == 6) { // YYYYMM.
							value = Integer.parseInt(values[i]);

							if (value <= 0) {
								throw new Exception(
										  "Invalid ndate value "
										+ "'" + values[i] + "'.");
							}

							$values[i] = new Integer(value);
						} else {	// YYYY-MM-DD.
							$values[i] =
									new AbsoluteDate(
											values[i], AbsoluteDate.YMD);
						}

						break;

					case Field.TYPE_STRING:
						$values[i] = values[i];

						break;

					default: // Field.TYPE_CHAR
						if (values[i].length() != 1) {
							throw new Exception(
									"Character field length must be 1.");
						}

						$values[i] = new Character(values[i].charAt(0));
				}
			}
		} catch (Exception e) {
			throw new PDFPublisherException(
					"Bad document ID '" + docID + "'.", e);
		}

		Scope scope = new Scope(docIDFields);

		scope.setValues($values, prefix);

		return scope;
	}

	/**
	 * Constructs an instance of <code>Scope</code> using the specified names
	 * and them corresponding values.
	 * 
	 * @param names Scope's names.
	 * 
	 * @param values Scope's values.
	 * 
	 * @return Corresponding scope.
	 * 
	 * @throws PDFPublisherException If some name specified in
	 *         <code>names</code> is unknown or its corresponding value has
	 *         an unexpected type.
	 */
	public Scope getScope(String[] names, Object[] values)
			throws PDFPublisherException {

		Mapping[] mappings = new Mapping[names.length];

		for (int i = 0; i < names.length; i++) {
			mappings[i] = docTypeMappedIndex.getMapping(names[i]);

			if (mappings[i] == null) {
				throw new PDFPublisherException(
						"Unmapped name '" + names[i] + "'.");
			}

			// System.out.println(
			// names[i] + " -> " + mappings[i].getMappedName());
		}

		Field[] fields = new Field[mappings.length];

		for (int i = 0; i < mappings.length; i++) {
			String fieldName = mappings[i].getMappedName();
			fields[i] = docTypeIndex.getField(fieldName);

			if (fields[i] == null) {
				throw new PDFPublisherException(
						  "Missed index's field name "
						+ "'" + fieldName + "' "
						+ "for document type '" + docType + "'.");
			}
		}

		Scope scope = new Scope(fields);

		scope.setValues(values);

		return scope;
	}

	/**
	 * Gets fields array of the document index corresponding to the document
	 * type used by this <b>PDF Publisher</b>.
	 * 
	 * @return Document Index's fields array.
	 * 
	 * @see #getDocIDFields()
	 */
	public Field[] getDocIndexFields() {
		return fields;
	}

	/**
	 * Gets fields array of the document ID corresponding to the document
	 * type used by this <b>PDF Publisher</b>.
	 * 
	 * @return Document ID's fields array.
	 * 
	 * @see #getDocIndexFields()
	 */
	public Field[] getDocIDFields() {
		return docIDFields;
	}

	/**
	 * Gets field names array of the document index corresponding to the
	 * document type used by this <b>PDF Publisher</b>.
	 * 
	 * @return Document Index's field names array.
	 * 
	 * @see #getDocIDFieldNames()
	 */
	public String[] getDocIndexFieldNames() {
		return getFieldNames(fields);
	}
	
	/**
	 * Gets field names array of the document index corresponding to the
	 * document type used by this <b>PDF Publisher</b>.
	 * 
	 * @return Document ID's field names array.
	 * 
	 * @see #getDocIndexFieldNames()
	 */
	public String[] getDocIDFieldNames() {
		return getFieldNames(docIDFields);
	}

	private String[] getFieldNames(Field[] fields) {
		String[] fieldNames = new String[fields.length];

		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];

			fieldNames[i] = field.getName();
		}

		return fieldNames;
	}

	/**
	 * Unpublishes instance of PDF document identified by <code>docID</code>.
	 *  
	 * @param docID Document ID.
	 * 
	 * @throws PDFPublisherException If PDF document cannot be unpublished.
	 */
	public void unpublish(String docID) throws PDFPublisherException {

		unpublish(getDocIDScope(docID));
	}

	/**
	 * Retrieves instance of published PDF document identified by
	 * <code>docID</code>.
	 * 
	 * @param docID Document ID.
	 * 
	 * @return published document.
	 * 
	 * @throws PDFPublisherException If PDF document cannot be retrieved.
	 */
	public PublishedDocument getDocument(String docID)
			throws PDFPublisherException {

		PublishedDocument publishedDocument = null;

		PublishedDocumentList publishedDocuments =
				getDocuments(getDocIDScope(docID));

		if (publishedDocuments.next()) {
			publishedDocument = publishedDocuments.getPublishedDocument();
		}

		publishedDocuments.close();

		if (publishedDocument == null) {
			throw new PDFPublisherException(
					"Document '" + docID + "' not found.");
		}

		return publishedDocument;
	}

	/**
	 * Retrieves a list of instances of published PDF documents identified by
	 * <code>docIDs</code>.
	 * 
	 * @param docIDs Array of document IDs.
	 * 
	 * @return List of published documents retrieved.
	 * 
	 * @throws PDFPublisherException If PDF documents cannot be retrieved.
	 * 
	 * @see #getDocuments(String[])
	 */
	public List getDocuments(List docIDs)
			throws PDFPublisherException {

		return getDocuments((String[]) docIDs.toArray(new String[0]));
	}

	/**
	 * Retrieves a list of instances of published PDF documents identified by
	 * <code>docIDs</code>.
	 * 
	 * @param docIDs Array of document IDs.
	 * 
	 * @return List of published documents retrieved.
	 * 
	 * @throws PDFPublisherException If PDF documents cannot be retrieved.
	 * 
	 * @see #getDocuments(List)
	 */
	public List getDocuments(String[] docIDs)
			throws PDFPublisherException {

		List publishedDocumentList = new ArrayList();

		for (int i = 0; i < docIDs.length; i++) {
			PublishedDocument publishedDocument = getDocument(docIDs[i]);

			if (publishedDocument != null) {
				publishedDocumentList.add(publishedDocument);
			}
		}

		return publishedDocumentList;
	}

	/**
	 * Defines a <code>filter</code> to be applied to this <b>PDF Publisher</b>
	 * in retrieve operations.
	 * 
	 * A specific <b>PDF Publisher</b> implementation could not require to
	 * support filtering facility.
	 * 
	 * @param filter Filter to be applied.
	 * 
	 * @see #getScopeInfo(Scope)
	 * @see #getDocuments(Scope)
	 */
	public void setFilter(BasePDFPublisherFilter filter) {
		this.filter = filter;

		filter.setPDFPublisher(this);
	}

	/**
	 * Publishes the PDF document instance specified by <code>document</code>.
	 * If this document instance exists previously then it will be replaced or
	 * ignored depending of the replace mode specified to this
	 * <b>PDF Publisher</b>.
	 * 
	 * @param document PDF document to be published.
	 * 
	 * @throws PDFPublisherException If PDF document cannot be published.
	 * 
	 * @see #publish(String, String)
	 */
	public abstract void publish(PDFDocument document)
			throws PDFPublisherException;

	/**
	 * Publishes the PDF file specified by <code>pdfFileName</code> indexing it 
	 * with the value <code>docIndex</code>. If this document instance exists
	 * previously then it will be replaced or ignored depending of the replace
	 * mode specified to this <b>PDF Publisher</b>.
	 * 
	 * @param docIndex Document index value.
	 * 
	 * @param pdfFileName PDF file pathname to be published.
	 * 
	 * @throws PDFPublisherException If PDF document cannot be published.
	 * 
	 * @see #publish(PDFDocument)
	 */
	public abstract void publish(String docIndex, String pdfFileName)
			throws PDFPublisherException;

	/**
	 * Unpublishes all published PDF document instances in the specified
	 * <code>scope</code>.
	 * 
	 * @param scope Unpublication scope.
	 * 
	 * @return Count of unpublished PDF documents.
	 * 
	 * @throws PDFPublisherException If PDF documents cannot be unpublished.
	 * 
	 * @see #unpublish(Scope, boolean)
	 */
	public abstract int unpublish(Scope scope) throws PDFPublisherException;

	/**
	 * Unpublishes all published PDF document instances in the specified
	 * <code>scope</code>. If <code>strict</code> is <code>true</code> then the
	 * corresponding version to every published PDF document instance will must
	 * additionally to match with the document version specified to this
	 * <b>PDF Publisher</b> to be considered in the unpublication.
	 * 
	 * @param scope Unpublication scope.
	 * 
	 * @param strict Strict document version match.
	 * 
	 * @return Count of unpublished PDF documents.
	 * 
	 * @throws PDFPublisherException If PDF documents cannot be unpublished.
	 */
	public abstract int unpublish(Scope scope, boolean strict)
			throws PDFPublisherException;

	/**
	 * Unpublishes the published PDF document instance specified by
	 * <code>document</code>.
	 * 
	 * @param document PDF document to be unpublished.
	 * 
	 * @throws PDFPublisherException If PDF document cannot be unpublished.
	 */
	public abstract void unpublish(PDFDocument document)
			throws PDFPublisherException;

	/**
	 * Retrieves document ID, index and version for all published PDF document
	 * instances in the specified <code>scope></code>.
	 * 
	 * <p> Retrieved PDF documents's info will be ordered by document ID.</p>
	 * 
	 * @param scope Retrieval scope.
	 * 
	 * @return A list of {@link PublishedDocumentInfo} instances.
	 * 
	 * @throws PDFPublisherException If PDF documents's info cannot be
	 *         retrieved.
	 *         
	 * @see #getScopeInfo(Scope, boolean)
	 * @see #getScopeInfo(Scope, boolean, int)
	 */
	public abstract List getScopeInfo(Scope scope)
			throws PDFPublisherException;

	/**
	 * Retrieves document ID, index and version for all published PDF document
	 * instances in the specified <code>scope</code>. If <code>strict</code>
	 * is <code>true</code> then the corresponding version to every published
	 * PDF document instance will must additionally to match with the document
	 * version specified to this <b>PDF Publisher</b> to be considered in the
	 * retrieval operation.
	 * 
	 * <p> Retrieved PDF documents's info will be ordered by document ID.</p>
	 * 
	 * @param scope Retrieval scope.
	 * 
	 * @param strict Strict document version match.
	 * 
	 * @return A list of {@link PublishedDocumentInfo} instances.
	 * 
	 * @throws PDFPublisherException If PDF documents's info cannot be
	 *         retrieved.
	 *         
	 * @see #getScopeInfo(Scope)
	 * @see #getScopeInfo(Scope, boolean, int)
	 */
	public abstract List getScopeInfo(Scope scope, boolean strict)
			throws PDFPublisherException;

	/**
	 * Retrieves document ID, index and version for at most <code>maxHits</code>
	 * published PDF document instances in the specified <code>scope</code>.
	 * 
	 * <p> Retrieved PDF documents's info will be ordered by document ID.</p>
	 * 
	 * @param scope Retrieval scope.
	 * 
	 * @return A list of {@link PublishedDocumentInfo} instances.
	 * 
	 * @throws PDFPublisherException If PDF documents's info cannot be
	 *         retrieved.
	 *         
	 * @see #getScopeInfo(Scope)
	 * @see #getScopeInfo(Scope, boolean, int)
	 */
	public abstract List getScopeInfo(Scope scope, int maxHits)
			throws PDFPublisherException;

	/**
	 * Retrieves document ID, index and version for at most <code>maxHits</code>
	 * published PDF document instances in the specified <code>scope</code>. If
	 * <code>strict</code> is <code>true</code> then the corresponding version
	 * to every published PDF document instance will must additionally to match
	 * with the document version specified to this <b>PDF Publisher</b> to be
	 * considered in the retrieval operation.
	 * 
	 * <p> Retrieved PDF documents's info will be ordered by document ID.</p>
	 * 
	 * @param scope Retrieval scope.
	 * 
	 * @param strict Strict document version match.
	 * 
	 * @param maxHits Maximum number of document hits.
	 * 
	 * @return A list of {@link PublishedDocumentInfo} instances.
	 * 
	 * @throws PDFPublisherException If PDF documents's info cannot be
	 *         retrieved.
	 *         
	 * @see #getScopeInfo(Scope)
	 * @see #getScopeInfo(Scope, boolean)         
	 */	
	public abstract List getScopeInfo(Scope scope, boolean strict,
			int maxHits) throws PDFPublisherException;

	/**
	 * Reports if <code>name</code> feature is supported by this particular
	 * PDFPublisher implementation.
	 * 
	 * @param name Feature name.
	 * 
	 * @return <code>true</code> is named specific feature is supported.
	 */
	public abstract boolean supportsFeature(String name);

	/**
	 * Retrieves all published PDF document instances in the specified
	 * <code>scope</code>.
	 * 
	 * <p> Retrieved PDF documents will be ordered by document ID.</p>
	 * 
	 * @param scope Retrieval scope.
	 * 
	 * @return A list of {@link PublishedDocument} instances.
	 * 
	 * @throws PDFPublisherException If PDF documents cannot be retrieved.
	 *         
	 * @see #getDocuments(Scope, boolean)
     * @see #getDocuments(Scope, int) 
	 * @see #getDocuments(Scope, boolean, int)        
	 */
	public abstract PublishedDocumentList getDocuments(Scope scope)
			throws PDFPublisherException;

	/**
	 * Retrieves all published PDF document instances in the specified
	 * <code>scope</code>. If <code>strict</code> is <code>true</code> then the
	 * corresponding version to every published PDF document instance will must
	 * additionally to match with the document version specified to this
	 * <b>PDF Publisher</b> to be considered in the retrieval operation.
	 * 
	 * <p> Retrieved PDF documents will be ordered by document ID.</p>
	 * 
	 * @param scope Retrieval scope.
	 * 
	 * @param strict Strict document version match.
	 * 
	 * @return A list of {@link PublishedDocument} instances.
	 * 
	 * @throws PDFPublisherException If PDF documents cannot be retrieved.
	 *         
	 * @see #getDocuments(Scope)
	 * @see #getDocuments(Scope, boolean, int)        
	 */	
	public abstract PublishedDocumentList getDocuments(Scope scope,
			boolean strict) throws PDFPublisherException;

	/**
	 * Retrieves at most <code>maxHits</code> published PDF document instances
	 * in the specified <code>scope</code>.
	 * 
	 * <p> Retrieved PDF documents will be ordered by document ID.</p>
	 * 
	 * @param scope Retrieval scope.
	 * 
	 * @param maxHits Maximum number of document hits.
	 * 
	 * @return A list of {@link PublishedDocument} instances.
	 * 
	 * @throws PDFPublisherException If PDF documents cannot be retrieved.
	 *         
	 * @see #getDocuments(Scope)
	 * @see #getDocuments(Scope, boolean, int)
	 */
	public abstract PublishedDocumentList getDocuments(Scope scope,
			int maxHits) throws PDFPublisherException;

	/**
	 * Retrieves at most <code>maxHits</code> published PDF document instances
	 * in the specified <code>scope</code>. If <code>strict</code> is
	 * <code>true</code> then the corresponding version to every published PDF
	 * document instance will must additionally to match with the document
	 * version specified to this <b>PDF Publisher</b> to be considered in the
	 * retrieval operation.
	 * 
	 * <p> Retrieved PDF documents will be ordered by document ID.</p>
	 * 
	 * @param scope Retrieval scope.
	 * 
	 * @param strict Strict document version match.
	 * 
	 * @param maxHits Maximum number of document hits.
	 * 
	 * @return A list of {@link PublishedDocument} instances.
	 * 
	 * @throws PDFPublisherException If PDF documents cannot be retrieved.
	 *         
	 * @see #getDocuments(Scope)
	 * @see #getDocuments(Scope, boolean)
	 * @see #getDocuments(Scope, int)        
	 */
	public abstract PublishedDocumentList getDocuments(Scope scope,
			boolean strict, int maxHits) throws PDFPublisherException;

	/**
	 * Opens a new transactional publication to publish zeroes or
	 * more PDF document instances. This method must be used together
	 * with {@link #flush()} or {@link #discard()} ones to close the publication
	 * commiting or discarding changes made.
	 * 
	 * <p> Nested publications are unsupported.</p>
	 *  
	 * @throws PDFPublisherException If cannot open publication.
	 * 
	 * @see #flush()
	 * @see #discard()
	 */
	public abstract void reset() throws PDFPublisherException;

	/**
	 * Closes the current transactional publication commiting changes made.
	 * This method must be used together with {@link #reset()} to open a new
	 * transactional publication.
	 *  
	 * <p> Nested publications are unsupported.</p>
	 *  
	 * @throws PDFPublisherException If cannot flush publication.
	 * 
	 * @see #reset()
	 * @see #discard()
	 */
	public abstract void flush() throws PDFPublisherException;

	/**
	 * Closes the current transactional publication discarding changes made.
	 * This method must be used together with {@link #reset()} to open a new
	 * transactional publication.
	 *  
	 * <p> Nested publications are unsupported.</p>
	 *  
	 * @throws PDFPublisherException If cannot discard publication.
	 * 
	 * @see #reset()
	 * @see #flush()
	 */
	public abstract void discard() throws PDFPublisherException;

	/**
	 * Closes connection to this <b>PDF Publisher</b>. Connection can be
	 * reopened with {@link #reopen()}.
	 * 
	 * @throws PDFPublisherException If cannot close connection.
	 * 
	 * @see #reopen()
	 */
	public abstract void close() throws PDFPublisherException;

	/**
	 * Opens connection to this <b>PDF Publisher</b>. If connection was opened
	 * this will reopen.
	 * 
	 * @throws PDFPublisherException If cannot close connection.
	 * 
	 * @see #close()
	 */	
	public abstract void reopen() throws PDFPublisherException;
}
