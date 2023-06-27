

/*
 * @(#) FPGIntegratedPDFPublisherSPI.java    1.0 30-06-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.implementations.fpg;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.reflect.Constructor;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;

import cl.araucana.core.util.AbsoluteDateTime;
import cl.araucana.core.util.FileUtils;
import cl.araucana.core.util.Padder;
import cl.araucana.core.util.PropertiesLoader;
import cl.araucana.core.util.PropertiesLoaderException;
import cl.araucana.core.util.ZipUtils;

import cl.araucana.core.util.logging.LogManager;

import cl.araucana.core.util.sql.BasicBlob;

import cl.araucana.fpg.FPGException;
import cl.araucana.fpg.PDFDocument;

import cl.araucana.pdf.publishers.BasePDFPublisherFilter;
import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.PublishedDocument;
import cl.araucana.pdf.publishers.PublishedDocumentInfo;
import cl.araucana.pdf.publishers.PublishedDocumentList;
import cl.araucana.pdf.publishers.Scope;


/**
 * This class implements a <b>PDF Publisher Service Provider Interface</b> (SPI)
 * integrated to framework <i>FPG</i> to provide maximum performance (published
 * documents rate) and reduced storage costs exploiting its optimized internal
 * separation between fixed and variable PDF objects and metadata contents.
 * 
 * <p> The following figure shows the documental repository conceptual model
 * used by this <b>PDF Publisher</b>:
 * </p>
 * 
 * <p align="center">
 * <img src="{@docRoot}/extras/FPG_PDFPublisher.gif">
 * </p>
 * 
 * <BR>
 *
 * <p> Each <i>Document Type</i> must be registered in the publisher before
 * can publish PDF document instances. The first step is to define each
 * document type's field that will be part of its document Index
 * (<strong>docIndex</strong>) and its document ID (<strong>docID</strong>).
 * Note that the second set must be a subset of first one in order to have a
 * derivable <strong>docID</strong>. The second step is to dimension the
 * maximum size of each one following four partitions to organize PDF document
 * instances's content storage:
 * 
 * <BR>
 * <BR>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="40%" CELLPADDING="3" CELLSPACING="0">
 *     <tr>
 *        <th bgcolor="black">
 *            <font color="white"><strong>Partition</strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>Description</strong></font>
 *        </th>
 *     </tr>
 *     <tr>
 *        <td>
 *            <strong>FOC</strong>
 *        </td>
 *        
 *        <td>
 *            Fixed PDF Objects Content.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>FMC</strong>
 *        </td>
 *        
 *        <td>
 *            Fixed PDF Metadata Content.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>VOC</strong>
 *        </td>
 *        
 *        <td>
 *            Variable PDF Objects Content.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>VMC</strong>
 *        </td>
 *        
 *        <td>
 *            Variable PDF Metadata Content.
 *        </td>
 *     </tr>
 * </TABLE>
 * 
 * <BR>
 * <BR>
 * 
 * <p> Documental repository is implemented into a relational database schema.
 * It's for this reason that a publisher configuration
 * (see {@link cl.araucana.pdf.publishers.PublisherManager}) must to have
 * the following defined properties:
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
 *            <strong>dataSourceName</strong>
 *        </td>
 *        
 *        <td>
 *            JDBC data source name to access FPG documental repository.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>schemaName</strong>
 *        </td>
 *        
 *        <td>
 *            Relational schema name in <strong>dataSourceName</strong>
 *            for FPG documental repository. It must exists previously.
 *        </td>
 *     </tr>
 * </TABLE>
 * 
 * <p> Publisher's class must be this one or a concrete subclass of it. </p>
 * 
 * <BR>
 * <BR>
 * 
 * <p> Additionally to do publication operations is recommended to define the
 * following publisher properties to audit involved source system and
 * publisher agent:
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
 *            <strong>sourceSystemName</strong>
 *        </td>
 *        
 *        <td>
 *            Source system name that will publish. Default is <u>SYSTEM</u>.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>publisherName</strong>
 *        </td>
 *        
 *        <td>
 *            Publish agent name that will publish. Default is <u>SYSTEM</u>.
 *        </td>
 *     </tr>
 * </TABLE>
 * 
 * <BR>
 * <BR>
 * 
 * <p> Theses names must be registered in <i>FPG</i> schema previuosly.
 * </p>
 * 
 * <p>
 * This <b>PDF Publisher</b> has been tested successfully with the following
 * <i>RDBMS</i>:
 * </p>
 * 
 * <ul>
 * <li> Apache Derby Network Server 10.4.2.0
 * <li> DB/2 UDB for iSeries V5R3
 * </ul>
 * 
 * <A NAME="batchStopOnFirstFault">
 * 
 * <p> This <b>PDF Publisher</b> publishes PDF document instances in the context
 * of a transactional publication optimized with JDBC prepared statements and
 * batches. To provide an ad-hoc batch support requires to have configured the
 * <strong>batchStopOnFirstFault</strong> flag property to indicate if JDBC
 * driver used <u>stops a batch execute on the first fault</u> (failed batched
 * statement) or not. The following table provides more details to the previous
 * supported RDBMS list:
 * </p>
 * 
 * <BR>
 * <BR>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0">
 * 	   
 *     <tr>
 *        <th bgcolor="black">
 *            <font color="white"><strong>RDBMS</strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>JDBC Driver</strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>Software</strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>batchStopOnFirstFault</strong></font>
 *        </th>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            Apache Derby Network Server 10.4.2.0
 *        </td>
 *        
 *        <td>
 *            org.apache.derby.jdbc.ClientDriver
 *        </td>
 *        
 *        <td>
 *            JavaDB
 *        </td>
 *        
 *        <td>
 *            false
 *        </td>        
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            DB/2 UDB for iSeries V5R3
 *        </td>
 *        
 *        <td>
 *            com.ibm.as400.access.AS400JDBCDriver
 *            
 *        <td>
 *            IBM Toolbox for Java
 *        </td>
 *        
 *        <td>
 *            true
 *        </td>        
 *     </tr>     
 * </TABLE>
 * 
 * <BR>
 * <BR>
 * 
 * <p> Publication operations must be do within an <u>opened publication
 * context</u> to provide best performance and transactional support. For more
 * details see
 * {@link #openPublicationContext(String, int, int, int, String, boolean)}
 * and {@link #closePublicationContext(boolean)} methods.
 * </p>
 * 
 * <p> Retrieval operations must be do within an <u>opened selection context</u>
 * to provide best performance. For more details see
 * {@link #openSelectionContext(String, int)} and
 * {@link #closeSelectionContext()} methods.
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
public class FPGIntegratedPDFPublisherSPI implements DocTypeConstants {

	/**
	 * Unspecified grouping criteria.
	 */		
	public static final int CRITERIA_NONE        = -1;
	
	/**
	 * Document version grouping criteria.
	 */
	public static final int CRITERIA_VERSION     = 0;
	
	/**
	 * Publication grouping criteria.
	 */	
	public static final int CRITERIA_PUBLICATION = 1;
	
	/**
	 * Source system grouping criteria.
	 */	
	public static final int CRITERIA_SYSTEM      = 2;
	
	/**
	 * Publisher agent grouping criteria.
	 */	
	public static final int CRITERIA_PUBLISHER   = 3;

	/**
	 * JDBC Drivers properties resource.
	 */
	public static final String JDBC_DRIVERS_PROPERTIES_RESOURCE =
			"/etc/pdf/fpg/jdbc_drivers.properties";

	/**
	 *  List of reserved field names that cannot be used as document type's
	 *  fields. 
	 */
	protected static final String[] reservedFieldNames = {
		"docVersion",
		"fmc",
		"foc",
		"idPublication",
		"idVersion",
		"vmc",
		"vmcsize",
		"voc",
		"vocsize"
	};

	/**
	 *  Controls debug mode. It can be enabled with the boolean system property
	 *  <b>fps.debug</b>.
	 */
	protected static final boolean debug = Boolean.getBoolean("fps.debug");

	private static final int DEFAULT_SOURCE_SYSTEM = 1;
	private static final int DEFAULT_PUBLISHER     = 1;

	private static final int UNKNOWN_SOURCE_SYSTEM = -1;
	private static final int UNKNOWN_PUBLISHER     = -1;
	private static final int UNKNOWN_DOCTYPE       = -1;

	private static final int REMARK_MAX_LENGTH = 40;

	private static Logger logger = LogManager.getLogger();

	private DataSource dataSource;
	private String schema;

	protected String driverKey;
	protected String functionBlobLength;
	protected boolean functionIdentity;
	protected GeneratedKeyHandler generatedKeyHandler;
	protected boolean strictedDelete;

	/**
	 *  <i>FPG</i> schema numeric identifier. 
	 */
	protected long id;

	/**
	 *  Source system ID used to audit publication operations. 
	 */
	protected int sourceSystemID = DEFAULT_SOURCE_SYSTEM;
	
	
	/**
	 *  Publisher agent ID used to audit publication operations. 
	 */
	protected int publisherID = DEFAULT_PUBLISHER;

	/**
	 *  Source system name used to audit publication operations. 
	 */
	protected String sourceSystemName = "SYSTEM";
	
	/**
	 *  Publisher agent name used to audit publication operations. 
	 */	
	protected String publisherName = "SYSTEM";

	/**
	 *  JDBC connection to the  <i>FPG</i> schema.
	 */
	protected Connection connection;

	private PublicationSPIContext publicationContext;
	private SelectionSPIContext selectionContext;

	// auxiliary variables used to manage fixed contents length.
	private static int[] zeroesFixedContentsLength = { 0, 0 };

	private DocVersion[] versions;
	private int lastVersion;
	private int[] fixedContentsLength = new int[2];

	/**
	 * Constructs an instance connected to the specified <i>FPG</i> schema in
	 * the data source <code>dataSource</code>. If documental repository model
	 * don't exists in <code>schema</code> then <b>SPI</b> will try to create
	 * it.
	 * 
	 * @param dataSource <i>FPG</i> Data source.
	 * 
	 * @param schema <i>FPG</i> schema.
	 * 
	 * @throws PDFPublisherException If cannot connect or recognize or create
	 *         the <i>FPG</i> schema.
	 */
	public FPGIntegratedPDFPublisherSPI(DataSource dataSource, String schema)
			throws PDFPublisherException {

		this.dataSource = dataSource;
		this.schema = schema;

		try {
			setConnection();
			loadJDBCDriverProperties();

			if (!isFPGSchema()) {
				createFPGSchema();
			}
		} catch (Exception e) {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e2) {}

				connection = null;
			}

			throw new PDFPublisherException("SPI couldn't be initialized", e);
		}

		logger.info("Using FPG_ID '" + id + "'.");
	}

	/**
	 *  Disconnects from the <i>FPG</i> schema. <b>SPI</b> will can to reconnect
	 *  it using {@link #reconnect()}.
	 */
	public void disconnect() {

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
			}

			connection = null;
		}
	}

	/**
	 * Reconnects to the <i>FPG</i> schema.
	 * 
	 * @throws PDFPublisherExceptionIf cannot reconnect.
	 */
	public void reconnect() throws PDFPublisherException {

		disconnect();

		try {
			setConnection();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e2) {}

				connection = null;
			}

			throw new PDFPublisherException("SPI reconnect failed", e);
		}
	}

	/**
	 * Defines the source system name to audit publication operations.
	 * 
	 * @param name Source system name.
	 * 
	 * @throws PDFPublisherExceptionIf <code>name</code> is unknown or
	 *         cannot check it.
	 */
	public void setSourceSystemName(String name) throws PDFPublisherException {

		int sourceSystemID = getSourceSystemID(name);

		if (sourceSystemID == UNKNOWN_SOURCE_SYSTEM) {
			throw new PDFPublisherException(
					"Source system name '" + name + "' not found");
		}

		this.sourceSystemID = sourceSystemID;
		this.sourceSystemName = name;
	}

	/**
	 * Gets current source system name.
	 * 
	 * @return Source system name.
	 */
	public String getSourceSystemName() {
		return sourceSystemName;
	}

	/**
	 * Gets current source system ID.
	 * 
	 * @return Source system ID.
	 */
	public int getSourceSystemID() {
		return sourceSystemID;
	}

	/**
	 * Gets array of registered source systems.
	 * 
	 * @return source systems array.
	 * 
	 * @throws PDFPublisherExceptionIf cannot get source systems array.
	 */
	public SourceSystem[] getSourceSystems() throws PDFPublisherException {

		Statement stmt = null;
		ResultSet rs = null;
		List sourceSystems = new ArrayList();

		try {
			String sqlStmt =
					  "SELECT idSystem, name, remark\n"
					+ "  FROM " + tn("SOURCE_SYSTEM") + " ss\n"
					+ " ORDER BY idSystem\n";

			logger.finest(getSQLTraceText("Get Source Systems", sqlStmt));

			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlStmt);

			while (rs.next()) {
				SourceSystem sourceSystem = new SourceSystem();

				sourceSystem.setID(rs.getInt(1));
				sourceSystem.setName(rs.getString(2).trim());
				sourceSystem.setDescription(rs.getString(3).trim());

				sourceSystems.add(sourceSystem);
			}

			return (SourceSystem[]) sourceSystems.toArray(new SourceSystem[0]);
		} catch (SQLException e) {
			throw new PDFPublisherException("Cannot get source systems", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
	}

	/**
	 * Defines the publisher agent name to audit publication operations.
	 * 
	 * @param name Publisher agent name.
	 * 
	 * @throws PDFPublisherExceptionIf <code>name</code> is unknown or
	 *         cannot check it.
	 */
	public void setPublisherName(String name) throws PDFPublisherException {

		int publisherID = getPublisherID(name);

		if (publisherID == UNKNOWN_PUBLISHER) {
			throw new PDFPublisherException(
					"Publisher name '" + name + "' not found");
		}

		this.publisherID = publisherID;
		this.publisherName = name;
	}

	/**
	 * Gets current publisher agent name.
	 * 
	 * @return publisher agent name.
	 */
	public String getPublisherName() {
		return publisherName;
	}

	/**
	 * Gets current publisher agent ID.
	 * 
	 * @return publisher agent ID.
	 */
	public int getPublisherID() {
		return publisherID;
	}
	
	/**
	 * Gets array of registered publisher agents.
	 * 
	 * @return publisher agents array. For each publisher agent returns a
	 *         <code>String[]</code> with two elements that contains its id
	 *         and name. 
	 * 
	 * @throws PDFPublisherExceptionIf cannot get publisher agents array.
	 */
	public String[][] getPublishers() throws PDFPublisherException {

		Statement stmt = null;
		ResultSet rs = null;
		List publisherNames = new ArrayList();

		try {
			String sqlStmt =
					  "SELECT idPublisher, name\n"
					+ "  FROM " + tn("PUBLISHER") + " pub\n"
					+ " ORDER BY idPublisher\n";

			logger.finest("getPublishers:");
			logger.finest(sqlStmt);

			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlStmt);

			while (rs.next()) {
				String idPublisher = rs.getString(1);
				String publisherName = rs.getString(2).trim();

				publisherNames.add(new String[] { idPublisher, publisherName });
			}

			return (String[][]) publisherNames.toArray(new String[0][]);
		} catch (SQLException e) {
			throw new PDFPublisherException("Cannot get publisher names", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
	}

	private void setConnection() throws SQLException {

		connection = dataSource.getConnection();

		connection.setTransactionIsolation(
				Connection.TRANSACTION_READ_UNCOMMITTED);
	}

	private void loadJDBCDriverProperties()
			throws SQLException, PropertiesLoaderException {

		DatabaseMetaData dbMetaData = connection.getMetaData();
		String driverName = dbMetaData.getDriverName();
		String driverVersion = dbMetaData.getDriverVersion();
		PropertiesLoader loader = new PropertiesLoader();
		
		Properties jdbcDriverProperties =
				loader.load(JDBC_DRIVERS_PROPERTIES_RESOURCE, getClass());
		
		String mainPropertyName =
				driverName.replace(' ', '_') + "__" + driverVersion;
		
		driverKey = jdbcDriverProperties.getProperty(mainPropertyName);
		
		if (driverKey == null) {
			driverKey = "standard";
		}
		
		functionBlobLength =
				jdbcDriverProperties.getProperty(
						driverKey + ".function.blob.length");
		
		String generatedKeyHandlerClassName = 
				jdbcDriverProperties.getProperty(
						driverKey + ".identity.handler");
		
		strictedDelete = 
				jdbcDriverProperties.getProperty(
						driverKey + ".delete.stricted").equals("true");		
		
		logger.finest("JDBC Driver:");
		logger.finest("     Name = " + driverName);
		logger.finest("     Version = " + driverVersion);
		logger.finest("     Main Property = " + mainPropertyName);
		logger.finest("     Key = " + driverKey);
		logger.finest("     Blob Length Function = " + functionBlobLength);
		
		logger.finest(
				  "     Generated Key Handler Class = "
				+ generatedKeyHandlerClassName);
		
		logger.finest("     strictedDelete = " + strictedDelete);

		try {
			Class GeneratedKeyHandlerClazz =
					Class.forName(generatedKeyHandlerClassName);
			
			Class[] parameterTypes = { FPGIntegratedPDFPublisherSPI.class };
			
			Constructor GeneratedKeyHandlerConstructor =
					GeneratedKeyHandlerClazz.getConstructor(parameterTypes);
			
			Object[] args = { this };

			generatedKeyHandler =
					(GeneratedKeyHandler)
							GeneratedKeyHandlerConstructor.newInstance(args);
		} catch (Exception e) {
			throw new PropertiesLoaderException(
					  "Cannot create generated key handler for class "
					+ "'" + generatedKeyHandlerClassName + "'");
		}
	}

	private boolean isFPGSchema() throws SQLException {

		ResultSet rs = null;

		try {
			rs = connection.getMetaData().getTables(
					null, schema, "FPG_ID", null);

			if (!rs.next()) {
				return false;
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
		}

		rs = null;

		Statement stmt = null;

		try {
			String sqlStmt =
					  "SELECT idFPG\n"
					+ "  FROM " + tn("FPG_ID") + "\n"
					+ " ORDER BY idFPG DESC\n";

			logger.finest("isFPGSchema:");
			logger.finest(sqlStmt);

			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlStmt);

			if (!rs.next()) {
				throw new SQLException("FPG schema is corrupted");
			}

			id = rs.getLong(1);

			return true;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
	}

	private void createFPGSchema() throws IOException, SQLException {

		id = System.currentTimeMillis();

		runScript("FPG_SYSTEM", new String[] { schema, Long.toString(id) });
	}

	public String getStrictedDelete(String tableName) {
		if (!strictedDelete) {
			return "";
		}
		
		return "  FROM " + tn(tableName);
		
	}
	
	/**
	 * Registers a new document type in the documental repository.
	 * 
	 * @param docType Document type name.
	 * 
	 * @param docFields Document Index's fields.
	 * 
	 * @param docIDFieldNames Document ID's fields.
	 * 
	 * @param maxPartitionSizes Maximum size (in kilobytes) for each partition
	 *        to store document content.
	 * 
	 * @throws PDFPublisherExceptionIf document type cannot be registered.
	 */
	public void registerDocumentType(DocType docType, DocField[] docFields,
			String[] docIDFieldNames, int[] maxPartitionSizes)
			throws PDFPublisherException {

		String docTypeName = docType.getName();

		if (maxPartitionSizes.length != NPARTITIONS) {
			throw new IllegalArgumentException(
					  "maxPartitionSizes[] requires "
					+ "'" + NPARTITIONS + "' values.");
		}

		/*
		 * Checks consistency for docIDFieldNames.
		 */
		if (docIDFieldNames.length == 0
				|| docIDFieldNames.length > docFields.length) {

			throw new IllegalArgumentException("Invalid docIDFields.");
		}

		for (int i = 0; i < docIDFieldNames.length; i++) {
			boolean found = false;

			for (int j = 0; j < docFields.length; j++) {
				if (docIDFieldNames[i].equals(docFields[j].getName())) {
					found = true;

					break;
				}
			}

			if (!found) {
				throw new IllegalArgumentException(
						  "docIDField '" + docIDFieldNames[i] + "' "
						+ "not found in document fields.");
			}
		}

		for (int i = 0; i < docIDFieldNames.length; i++) {
			String docIDFieldName = docIDFieldNames[i];

			for (int j = 0; j < docIDFieldNames.length; j++) {
				if (i != j && docIDFieldName.equals(docIDFieldNames[j])) {
					throw new IllegalArgumentException(
							"Duplicated docIDField '" + docIDFieldName + "'.");
				}
			}
		}

		/*
		 * Generates metadata.
		 */
		Map names = new HashMap();
		String fieldsMetaData = "";
		String primaryKeyMetaData = "";
		String fieldMetaData = "";
		int nPKFields = 0;

		for (int i = 0; i < docFields.length; i++) {
			DocField field = docFields[i];
			String name = field.getName();

			if (isReservedFieldName(name)) {
				throw new PDFPublisherException(
						"Reserved field name '" + name + "'");
			}

			if (names.get(name) != null) {
				throw new PDFPublisherException(
						"Duplicated field name '" + name + "'");
			}

			names.put(name, name);

			fieldMetaData = "       ";

			switch (field.getType()) {
				case FIELD_TYPE_INT:
					fieldMetaData = name + " INTEGER";

					break;

				case FIELD_TYPE_STRING:
					int length = field.getLength();

					if (length <= 0) {
						throw new IllegalArgumentException(
								  "Invalid field length "
								+ "'" + length + "' for a string field type.");
					}

					fieldMetaData = name + " CHAR(" + length + ")";

					break;

				case FIELD_TYPE_CHAR:
					fieldMetaData = name + " CHAR(1)";

					break;

				case FIELD_TYPE_NDATE:
					throw new IllegalArgumentException(
							"Unsupported 'ndate' field type.");

				default:
					throw new IllegalArgumentException(
							"Unknown field type '" + field.getType() + "'.");
			}

			fieldMetaData += " NOT NULL,";
			fieldsMetaData += fieldMetaData;

			if (i + 1 < docFields.length) {
				fieldsMetaData += "\n";
			}

			boolean found = false;

			for (int j = 0; j < docIDFieldNames.length; j++) {
				if (docIDFieldNames[j].equals(name)) {
					found = true;

					break;
				}
			}

			if (found) {
				primaryKeyMetaData += fieldMetaData;

				nPKFields++;

				if (nPKFields < docIDFieldNames.length) {
					primaryKeyMetaData += "\n";
				}
			}
		}

		/*
		 * Generates primary key field list and triggerClause
		 * for document content.
		 */
		String primaryKeyFieldList = "";
		String triggerClause = "";

		for (int i = 0; i < docIDFieldNames.length; i++) {
			String fieldName = docIDFieldNames[i];
			primaryKeyFieldList += fieldName;
			triggerClause += fieldName + " = DELETED_ROW." + fieldName;

			if (i + 1 < docIDFieldNames.length) {
				primaryKeyFieldList += ", ";
				triggerClause += " AND ";
			}
		}

		boolean autoCommitSetted = false;
		boolean commit = false;

		try {
			connection.setAutoCommit(false);
			autoCommitSetted = true;

			_registerDocumentType(
					docType, docFields, docIDFieldNames, maxPartitionSizes);

			String[] args = new String[] {
				schema,
				docType.getBaseName(),
				Integer.toString(maxPartitionSizes[VOC]),
				Integer.toString(maxPartitionSizes[VMC]),
				Integer.toString(maxPartitionSizes[FOC]),
				Integer.toString(maxPartitionSizes[FMC]),
				fieldsMetaData,
				primaryKeyFieldList,
				primaryKeyMetaData,
				triggerClause
			};

			runScript("DOCUMENT", args);
			createDocumentTypeIndexes(docType, docFields, docIDFieldNames[0]);

			commit = true;
		} catch (Exception e) {
			e.printStackTrace();

			commit = false;
		} finally {
			boolean txFailed = !commit;

			try {
				if (autoCommitSetted) {
					if (commit) {
						connection.commit();
					} else {
						connection.rollback();
					}

					connection.setAutoCommit(true);
				}
			} catch (SQLException e) {
				txFailed = true;
			}

			if (txFailed) {
				throw new PDFPublisherException(
						  "Document type "
						+ "'" + docTypeName + "' couldn't be registered");
			}
		}
	}

	/**
	 * Unregisters the <code>name</code> source system. Default source system
	 * cannot be unregistered.
	 * 
	 * @param name Source system name.
	 * 
	 * @throws PDFPublisherExceptionIf source system cannot be unregistered.
	 */
	public void unregisterSourceSystem(String name)
			throws PDFPublisherException {

		if (name.toLowerCase().equals("SYSTEM")) {
			throw new PDFPublisherException(
					"Source system 'SYSTEM' cannot be unregistered");
		}

		boolean isDefaultOne = getSourceSystemName().equals(name);
		Statement stmt = null;

		try {
			String sqlStmt =
					  "DELETE FROM " + tn("SOURCE_SYSTEM") + "\n"
					+ " WHERE name = '" + name + "'\n";
			
			logger.finest("unregisterSourceSystem:");
			logger.finest(sqlStmt);
			
			stmt = connection.createStatement();

			int rowCount = stmt.executeUpdate(sqlStmt);

			if (rowCount == 0) {
				throw new PDFPublisherException(
						"Unknown source system '" + name + "'");
			}

			if (isDefaultOne) {
				setSourceSystemName("SYSTEM");
			}
		} catch (SQLException e) {
			throw new PDFPublisherException(
					"Cannot unregister source system '" + name + "'", e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
	}

	/**
	 * Unregisters the <code>name</code> publisher agent. Default publisher
	 * agent cannot be unregistered.
	 * 
	 * @param name Publisher agent name.
	 * 
	 * @throws PDFPublisherExceptionIf publisher agent cannot be
	 *         unregistered.
	 */
	public void unregisterPublisher(String name)
			throws PDFPublisherException {

		if (name.toLowerCase().equals("SYSTEM")) {
			throw new PDFPublisherException(
					"Publisher 'SYSTEM' cannot be unregistered");
		}

		boolean isDefaultOne = getPublisherName().equals(name);
		Statement stmt = null;

		try {
			String sqlStmt =
					  "DELETE FROM " + tn("PUBLISHER") + "\n"
					+ " WHERE name = '" + name + "'\n";

			logger.finest("unregisterPublisher:");
			logger.finest(sqlStmt);

			stmt = connection.createStatement();

			int rowCount = stmt.executeUpdate(sqlStmt);

			if (rowCount == 0) {
				throw new PDFPublisherException(
						"Unknown publisher '" + name + "'");
			}

			if (isDefaultOne) {
				setPublisherName("SYSTEM");
			}
		} catch (SQLException e) {
			throw new PDFPublisherException(
					"Cannot unregister publisher '" + name + "'", e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
	}

	/**
	 * Unregisters the <code>name</code> document type.
	 * 
	 * @param name Document type name.
	 * 
	 * @throws PDFPublisherExceptionIf document type cannot be
	 *         unregistered.
	 */
	public void unregisterDocumentType(String name)
			throws PDFPublisherException {

		DocType docType = getDocType(name);

		if (docType == null) {
			throw new IllegalArgumentException(
					"Document type '" + name + "' not found");
		}

		String docTypeName = name;
		boolean autoCommitSetted = false;
		boolean commit = false;

		try {
			connection.setAutoCommit(false);
			autoCommitSetted = true;

			runScript(
					"DROP_DOCUMENT",
					new String[] {
							schema,
							docType.getBaseName(),
							docType.getID() + "" });

			commit = true;
		} catch (Exception e) {
			commit = false;
		} finally {
			boolean txFailed = !commit;

			try {
				if (autoCommitSetted) {
					if (commit) {
						connection.commit();
					} else {
						connection.rollback();
					}

					connection.setAutoCommit(true);
				}
			} catch (SQLException e) {
				txFailed = true;
			}

			if (txFailed) {
				throw new PDFPublisherException(
						  "Document type "
						+ "'" + docTypeName + "' couldn't be unregistered");
			}
		}
	}

	/**
	 * Gets ID for <code>name</code> source system.
	 * 
	 * @param name Source system name.
	 * 
	 * @return Source system ID.
	 * 
	 * @throws PDFPublisherExceptionIf cannot get source system ID.
	 */
	public int getSourceSystemID(String name) throws PDFPublisherException {

		Statement stmt = null;
		ResultSet rs = null;

		try {
			String sqlStmt =
					  "SELECT idSystem\n"
					+ "  FROM " + tn("SOURCE_SYSTEM") + "\n"
					+ " WHERE name = '" + name + "'\n";

			logger.finest("getSourceSystemID:");
			logger.finest(sqlStmt);

			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlStmt);

			if (rs.next()) {
				return rs.getInt(1);
			}

			return UNKNOWN_SOURCE_SYSTEM;
		} catch (SQLException e) {
			throw new PDFPublisherException(
					"Cannot get id for source system '" + name + "'", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
	}

	/**
	 * Gets ID for <code>name</code> publisher agent.
	 * 
	 * @param name Publisher agent name.
	 * 
	 * @return publisher agent ID.
	 * 
	 * @throws PDFPublisherExceptionIf cannot get publisher agent ID.
	 */
	public int getPublisherID(String name) throws PDFPublisherException {

		Statement stmt = null;
		ResultSet rs = null;

		try {
			String sqlStmt =
					  "SELECT idPublisher\n"
					+ "  FROM " + tn("PUBLISHER") + "\n"
					+ " WHERE name = '" + name + "'\n";

			logger.finest("getPublisherID:");
			logger.finest(sqlStmt);

			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlStmt);

			if (rs.next()) {
				return rs.getInt(1);
			}

			return UNKNOWN_PUBLISHER;
		} catch (SQLException e) {
			throw new PDFPublisherException(
					"Cannot get id for publisher '" + name + "'", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
	}

	/**
	 * Gets metadata for <code>name</code> document type.
	 * 
	 * @param name Document type name.
	 * 
	 * @return document type metadata.
	 * 
	 * @throws PDFPublisherExceptionIf cannot get document type metadata.
	 */
	public DocType getDocType(String name) throws PDFPublisherException {

		Statement stmt = null;
		ResultSet rs = null;

		try {
			String sqlStmt =
					  "SELECT idDocType, name, baseName,\n"
					+ "       (SELECT name\n"
					+ "           FROM " + tn("SOURCE_SYSTEM") + "\n"
					+ "          WHERE dt.idSystem = idSystem\n"
					+ "       ) AS systemName,\n"
					+ "       (SELECT name\n"
					+ "           FROM " + tn("PUBLISHER") + "\n"
					+ "          WHERE dt.idPublisher = idPublisher\n"
					+ "       ) AS publisherName,\n"
					+ "       created, remark,\n"
					+ "       focMaxSize, vocMaxSize,\n"
					+ "       fmcMaxSize, vmcMaxSize\n"
					+ "  FROM " + tn("DOCTYPE") + " dt\n"
					+ " WHERE name = '" + name + "'\n";

			logger.finest("getDocType:");
			logger.finest(sqlStmt);
			
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlStmt);

			if (rs.next()) {
				DocType docType = new DocType();

				docType.setID(rs.getInt(1));
				docType.setName(rs.getString(2).trim());
				docType.setBaseName(rs.getString(3).trim());
				docType.setSourceSystemName(rs.getString(4).trim());
				docType.setPublisherName(rs.getString(5).trim());
				docType.setCreated(rs.getTimestamp(6).getTime());
				docType.setRemark(rs.getString(7).trim());

				int[] maxPartitionSizes = {
					rs.getInt(8),
					rs.getInt(9),
					rs.getInt(10),
					rs.getInt(11)
				};

				docType.setMaxPartitionSizes(maxPartitionSizes);

				return docType;
			}

			return null;
		} catch (SQLException e) {
			throw new PDFPublisherException(
					"Cannot get document type '" + name + "'", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
	}

	/**
	 * Gets ID for <code>name</code> document type.
	 * 
	 * @param name Document type name.
	 * 
	 * @return document type ID.
	 * 
	 * @throws PDFPublisherException If cannot get document type ID.
	 */	
	public int getDocTypeID(String name) throws PDFPublisherException {

		DocType docType = getDocType(name);

		if (docType == null) {
			return UNKNOWN_DOCTYPE;
		}

		return docType.getID();
	}

	/**
	 * Gets metadata for each registered document type.
	 * 
	 * @return Array of document types metadata.
	 * 
	 * @throws PDFPublisherException If cannot get document types metadata.
	 */
	public DocType[] getDocTypes() throws PDFPublisherException {

		Statement stmt = null;
		ResultSet rs = null;
		List docTypes = new ArrayList();

		try {
			String sqlStmt =
					  "SELECT idDocType, name, baseName,\n"
					+ "       (SELECT name\n"
					+ "           FROM " + tn("SOURCE_SYSTEM") + "\n"
					+ "          WHERE dt.idSystem = idSystem\n"
					+ "       ) AS systemName,\n"
					+ "       (SELECT name\n"
					+ "           FROM " + tn("PUBLISHER") + "\n"
					+ "          WHERE dt.idPublisher = idPublisher\n"
					+ "       ) AS publisherName,\n"
					+ "       created, remark,\n"
					+ "       focMaxSize, vocMaxSize,\n"
					+ "       fmcMaxSize, vmcMaxSize\n"
					+ "  FROM " + tn("DOCTYPE") + " dt\n"
					+ " ORDER BY name\n";
			
			logger.finest("getDocTypes:");
			logger.finest(sqlStmt);
			
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlStmt);

			while (rs.next()) {
				DocType docType = new DocType();

				docType.setID(rs.getInt(1));
				docType.setName(rs.getString(2).trim());
				docType.setBaseName(rs.getString(3).trim());
				docType.setSourceSystemName(rs.getString(4).trim());
				docType.setPublisherName(rs.getString(5).trim());
				docType.setCreated(rs.getTimestamp(6).getTime());
				docType.setRemark(rs.getString(7).trim());

				int[] maxPartitionSizes = {
					rs.getInt(8),
					rs.getInt(9),
					rs.getInt(10),
					rs.getInt(11)
				};

				docType.setMaxPartitionSizes(maxPartitionSizes);

				docTypes.add(docType);
			}

			return (DocType[]) docTypes.toArray(new DocType[0]);
		} catch (SQLException e) {
			throw new PDFPublisherException(
					"Cannot get document types", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
	}

	/**
	 * Registers a new source system. Default source system is reserved.
	 * 
	 * @param name Source system name.
	 * 
	 * @param remark Source system comment or description.
	 * 
	 * @throws PDFPublisherException If source system cannot be registered.
	 */
	public void registerSourceSystem(String name, String remark)
			throws PDFPublisherException {

		Statement stmt = null;

		try {
			String sqlStmt =
					  "INSERT INTO " + tn("SOURCE_SYSTEM") + "\n"
					+ "     (name, remark)\n"
					+ "     VALUES (\n"
					+ "             '" + name + "',\n"
					+ "             '" + truncateRemark(remark) + "'\n"
					+ "            )\n";

			logger.finest("registerSourceSystem:");
			logger.finest(sqlStmt);
			
			stmt = connection.createStatement();
			stmt.executeUpdate(sqlStmt);
		} catch (SQLException e) {
			throw new PDFPublisherException(
					"Cannot register source system '" + name + "'", e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
	}

	/**
	 * Registers a new publisher agent. Default publisher agent is reserved.
	 * 
	 * @param name Publisher agent name.
	 * 
	 * @throws PDFPublisherException If publisher agent cannot be registered.
	 */
	public void registerPublisher(String name) throws PDFPublisherException {

		Statement stmt = null;

		try {
			String sqlStmt =
					  "INSERT INTO " + tn("PUBLISHER") + "\n"
					+ "     (name)\n"
					+ "     VALUES ('" + name + "')\n";

			logger.finest("registerPublisher:");
			logger.finest(sqlStmt);

			stmt = connection.createStatement();
			stmt.executeUpdate(sqlStmt);
		} catch (SQLException e) {
			throw new PDFPublisherException(
					"Cannot register publisher '" + name + "'", e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
	}

	/**
	 * Gets document index's fields for the <code>name</code> document type.
	 * 
	 * @param name Document type name.
	 * 
	 * @return Array of document index's fields.
	 * 
	 * @throws PDFPublisherException If cannot get document index's fields.
	 */
	public DocField[] getDocFields(String name)
			throws PDFPublisherException {

		return getDocFields(getDocTypeID(name));
	}

	/**
	 * Gets document index's fields for the <code>docTypeID</code> document
	 * type.
	 * 
	 * @param docTypeID Document type ID.
	 * 
	 * @return Array of document index's fields.
	 * 
	 * @throws PDFPublisherException If cannot get document index's fields.
	 */
	public DocField[] getDocFields(int docTypeID)
			throws PDFPublisherException {

		Statement stmt = null;
		ResultSet rs = null;
		List docFields = new ArrayList();

		try {
			String sqlStmt =
					  "SELECT name, ftype, flength, labelName, remark\n"
					+ "  FROM " + tn("DOCTYPE_FIELD") + "\n"
					+ " WHERE idDocType = " + docTypeID + "\n"
					+ " ORDER BY sequence ASC\n";

			logger.finest("getDocFields:");
			logger.finest(sqlStmt);
			
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlStmt);

			while (rs.next()) {
				DocField docField = new DocField();

				docField.setName(rs.getString(1).trim());
				docField.setType(DocField.getType(rs.getString(2).trim()));
				docField.setLength(rs.getInt(3));
				docField.setLabelName(rs.getString(4).trim());
				docField.setRemark(rs.getString(5).trim());

				docFields.add(docField);
			}

			if (docFields.size() == 0) {
				throw new PDFPublisherException("Unknown document type");
			}

			return (DocField[]) docFields.toArray(new DocField[0]);
		} catch (SQLException e) {
			throw new PDFPublisherException(
					"Cannot get fields for document id '" + docTypeID + "'",
					e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
	}

	/**
	 * Gets document ID's fields for the <code>name</code> document type.
	 * 
	 * @param name Document type name.
	 * 
	 * @return Array of document ID's fields.
	 * 
	 * @throws PDFPublisherException If cannot get document ID's fields.
	 */	
	public String[] getDocIDFieldNames(String name)
			throws PDFPublisherException {

		return getDocIDFieldNames(getDocTypeID(name));
	}

	/**
	 * Gets document ID's fields for the <code>docTypeID</code> document type.
	 * 
	 * @param docTypeID Document type ID.
	 * 
	 * @return Array of document ID's fields.
	 * 
	 * @throws PDFPublisherException If cannot get document ID's fields.
	 */
	public String[] getDocIDFieldNames(int docTypeID)
			throws PDFPublisherException {

		Statement stmt = null;
		ResultSet rs = null;
		List docIDFieldNames = new ArrayList();

		try {
			String sqlStmt =
					  "SELECT name\n"
					+ "  FROM " + tn("DOCTYPE_FIELD") + "\n"
					+ " WHERE idDocType = " + docTypeID + "\n"
					+ "   AND pkSequence <> 0\n"
					+ " ORDER BY pkSequence ASC\n";

			logger.finest("getDocIDFieldNames:");
			logger.finest(sqlStmt);

			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlStmt);

			while (rs.next()) {
				docIDFieldNames.add(rs.getString(1).trim());
			}

			if (docIDFieldNames.size() == 0) {
				throw new PDFPublisherException("Unknown document type");
			}

			return (String[]) docIDFieldNames.toArray(new String[0]);
		} catch (SQLException e) {
			throw new PDFPublisherException(
					  "Cannot get docID field names for document id "
					+ "'" + docTypeID + "'", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
	}

	/**
	 * Gets all registered versions for the named document type.
	 * 
	 * @param docTypeName Document type name.
	 * 
	 * @return Array of registered versions.
	 * 
	 * @throws PDFPublisherException If cannot get registered versions.
	 */
	public DocVersion[] getVersions(String docTypeName)
			throws PDFPublisherException {

		DocType docType = getDocType(docTypeName);

		if (docType == null) {
			throw new PDFPublisherException(
					"Unknown document type '" + docTypeName + "'");
		}

		String baseName = docType.getBaseName();

		Statement stmt = null;
		ResultSet rs = null;
		List docVersions = new ArrayList();

		try {
			String sqlStmt =
					  "SELECT idVersion,\n"
				    + "       " + blobLength("foc") + ",\n"
				    + "       " + blobLength("fmc") + ",\n"
					+ "       remark, created,\n"
					+ "       (SELECT name\n"
					+ "         FROM " + tn("PUBLISHER") + "\n"
					+ "        WHERE idPublisher = dv.idPublisher\n"
					+ "       ) AS publisherName\n"
					+ "  FROM " + tn(baseName + "_VERSION") + " dv\n"
					+ " ORDER BY idVersion\n";

			logger.finest("getVersions:");
			logger.finest(sqlStmt);

			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlStmt);

			while (rs.next()) {
				DocVersion docVersion = new DocVersion();

				docVersion.setID(rs.getInt(1));
				docVersion.setFOCSize(rs.getInt(2));
				docVersion.setFMCSize(rs.getInt(3));

				String remark = rs.getString(4);

				if (!rs.wasNull()) {
					docVersion.setRemark(remark.trim());
				}

				docVersion.setDateTime(
						new AbsoluteDateTime(rs.getTimestamp(5)));

				docVersion.setPublisherName(rs.getString(6).trim());

				docVersions.add(docVersion);
			}

			return (DocVersion[]) docVersions.toArray(new DocVersion[0]);
		} catch (SQLException e) {
			throw new PDFPublisherException(
					  "Cannot get versions for document type "
					+ "'" + docTypeName + "'",
					e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	/**
	 * Gets registered publications for the named document type.
	 * 
	 * @param docTypeName Document type name.
	 * 
	 * @param criteria Publication grouping criteria.
	 * 
	 * @param descendent Publications order. <code>true</code> specifies
	 *        descendent, ascendent otherwise.
	 * 
	 * @param maxHits Maximum number of publications.
	 * 
	 * @return Array of registered publications.
	 * 
	 * @throws PDFPublisherException If cannot get registered publications.
	 * 
	 * @see #CRITERIA_VERSION
	 * @see #CRITERIA_PUBLICATION
	 * @see #CRITERIA_SYSTEM
	 * @see #CRITERIA_PUBLISHER
	 */
	public Publication[] getPublications(String docTypeName, int criteria,
			boolean descendent,	int maxHits) throws PDFPublisherException {

		DocType docType = getDocType(docTypeName);

		if (docType == null) {
			throw new PDFPublisherException(
					"Unknown document type '" + docTypeName + "'");
		}

		String baseName = docType.getBaseName();

		String sqlOrderCriteria;

		switch (criteria) {
			case CRITERIA_VERSION:
				sqlOrderCriteria = "version,";

				break;

			case CRITERIA_PUBLISHER:
				sqlOrderCriteria = "publisherName,";

				break;

			case CRITERIA_SYSTEM:
				sqlOrderCriteria = "systemName,";

				break;

			case CRITERIA_PUBLICATION:
				sqlOrderCriteria = "";

				break;

			default:

				throw new PDFPublisherException("Unknown criteria");
		}

		String direction = (descendent) ? "DESC" : "ASC";

 		sqlOrderCriteria += "idPublication " + direction;

		Statement stmt = null;
		ResultSet rs = null;
		List publications = new ArrayList();

		try {
			String sqlStmt =
					  "SELECT idPublication,\n"
					+ "       (SELECT name\n"
					+ "          FROM "+ tn("SOURCE_SYSTEM") + " ss\n"
					+ "         WHERE ss.idSystem = pub.idSystem\n"
					+ "       ) AS systemName,\n"
					+ "       (SELECT name\n"
					+ "          FROM " + tn("PUBLISHER") + " publisher\n"
					+ "         WHERE publisher.idPublisher = pub.idPublisher\n"
					+ "       ) AS publisherName,\n"
					+ "       (CASE\n"
					+ "            WHEN idVersion IS NULL THEN\n"
					+ "                 0\n"
					+ "            ELSE\n"
					+ "                 idVersion\n"
					+ "        END) AS version,\n"
					+ "       created,\n"
					+ "       remark,\n"
					+ "       opened,\n"
					+ "       (SELECT COUNT(*)\n"
					+ "          FROM " + tn(baseName + "_DOCUMENT") + " doc\n"
					+ "         WHERE doc.idPublication = pub.idPublication\n"
					+ "       ) AS nDocs\n"
					+ "  FROM " + tn(baseName + "_PUBLICATION") + " pub\n"
					+ " ORDER BY " + sqlOrderCriteria + "\n";

			logger.finest("getPublications:");
			logger.finest(sqlStmt);

			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlStmt);

			int nPublications = 0;

			while (nPublications++ < maxHits && rs.next()) {
				Publication publication = new Publication();

				publication.setID(rs.getInt(1));
				publication.setSystemName(rs.getString(2).trim());
				publication.setPublisherName(rs.getString(3).trim());
				publication.setVersion(rs.getInt(4));

				publication.setDateTime(
						new AbsoluteDateTime(rs.getTimestamp(5)));

				String remark = rs.getString(6);

				if (!rs.wasNull()) {
					publication.setRemark(remark.trim());
				}

				publication.setOpened(rs.getInt(7) == 1);
				publication.setNDocuments(rs.getInt(8));

				publications.add(publication);
			}

			return (Publication[]) publications.toArray(new Publication[0]);
		} catch (SQLException e) {
			throw new PDFPublisherException(
					  "Cannot get publications for document type "
					+ "'" + docTypeName + "'",
					e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
	}

	/**
	 * Gets summaried statistics for the named document type. Only PDF document
	 * instances in <code>scope</code> are accounted. One statistics record is
	 * provided by each grouping item according to the grouping
	 * <code>criteria</code>.
	 * 
	 * @param docTypeName Document type name.
	 * 
	 * @param criteria Statistics grouping criteria.
	 * 
	 * @param scope Statistics scope.
	 * 
	 * @return List of {@link DocStat} instances for document type statistics.
	 * 
	 * @throws PDFPublisherException If cannot get statistics.
	 * 
	 * @see #CRITERIA_VERSION
	 * @see #CRITERIA_PUBLICATION
	 * @see #CRITERIA_SYSTEM
	 * @see #CRITERIA_PUBLISHER 
	 */
	public List getStats(String docTypeName, int criteria, Scope scope)
			throws PDFPublisherException {

		DocType docType = getDocType(docTypeName);

		if (docType == null) {
			throw new PDFPublisherException(
					"Unknown document type '" + docTypeName + "'");
		}

		String baseName = docType.getBaseName();

		if (criteria < 0 || criteria > CRITERIA_PUBLISHER) {
			throw new PDFPublisherException("Unknown criteria");
		}

		String scopeCriteria = scope.toString();

		if (!scopeCriteria.equals("")) {
			scopeCriteria = "   AND (" + scopeCriteria + ")\n";
		}

		versions = getVersions(docTypeName);
		lastVersion = 0;

		String sqlStmt;

		switch (criteria) {
			case CRITERIA_VERSION:
				sqlStmt =
						  "SELECT\n"
						+ "      CASE\n"
						+ "          WHEN pub.idVersion IS NULL THEN\n"
						+ "               0\n"
						+ "          ELSE\n"
						+ "               pub.idVersion\n"
						+ "      END,\n"
						+ "      CASE\n"
						+ "          WHEN pub.idVersion IS NULL THEN\n"
						+ "               0\n"
						+ "          ELSE\n"
						+ "               pub.idVersion\n"
						+ "      END,\n"
						+ "      COUNT(*) AS nDocs,\n"
						+ "      SUM(CAST(vocsize AS BIGINT)) AS grpVOCSize,\n"
						+ "      SUM(CAST(vmcsize AS BIGINT)) AS grpVMCSize\n"
						+ "  FROM " + tn(baseName + "_DOCUMENT") + " doc,\n"
						+ "       " + tn(baseName + "_PUBLICATION") + " pub\n"
						+ " WHERE doc.idPublication = pub.idPublication\n"
						+ "   AND pub.opened = 0\n"
						+ scopeCriteria
						+ " GROUP BY pub.idVersion, pub.idVersion\n"
						+ " ORDER BY 1, 2\n";

				break;

			case CRITERIA_PUBLICATION:
				sqlStmt =
						  "SELECT\n"
						+ "      pub.idPublication,\n"
						+ "      CASE\n"
						+ "          WHEN pub.idVersion IS NULL THEN\n"
						+ "               0\n"
						+ "          ELSE\n"
						+ "               pub.idVersion\n"
						+ "      END,\n"
						+ "      COUNT(*) AS nDocs,\n"
						+ "      SUM(CAST(vocsize AS BIGINT)) AS grpVOCSize,\n"
						+ "      SUM(CAST(vmcsize AS BIGINT)) AS grpVMCSize\n"
						+ "  FROM " + tn(baseName + "_DOCUMENT") + " doc,\n"
						+ "       " + tn(baseName + "_PUBLICATION") + " pub\n"
						+ " WHERE doc.idPublication = pub.idPublication\n"
						+ "   AND pub.opened = 0\n"
						+ scopeCriteria
						+ " GROUP BY pub.idPublication, pub.idVersion\n"
						+ " ORDER BY 1, 2\n";

				break;

			case CRITERIA_SYSTEM:
				sqlStmt =
						  "SELECT\n"
						+ "      pub.idSystem,\n"
						+ "      CASE\n"
						+ "          WHEN pub.idVersion IS NULL THEN\n"
						+ "               0\n"
						+ "          ELSE\n"
						+ "               pub.idVersion\n"
						+ "      END,\n"
						+ "      COUNT(*) AS nDocs,\n"
						+ "      SUM(CAST(vocsize AS BIGINT)) AS grpVOCSize,\n"
						+ "      SUM(CAST(vmcsize AS BIGINT)) AS grpVMCSize\n"
						+ "  FROM " + tn(baseName + "_DOCUMENT") + " doc,\n"
						+ "       " + tn(baseName + "_PUBLICATION") + " pub,\n"
						+ "       " + tn("SOURCE_SYSTEM") + " sys\n"
						+ " WHERE doc.idPublication = pub.idPublication\n"
						+ "   AND pub.opened = 0\n"
						+ "   AND pub.idSystem = sys.idSystem\n"
						+ scopeCriteria
						+ " GROUP BY pub.idSystem, pub.idVersion\n"
						+ " ORDER BY 1, 2\n";

				break;

			default:	//CRITERIA_PUBLISHER
				sqlStmt =
						  "SELECT\n"
						+ "      pub.idPublisher,\n"
						+ "      CASE\n"
						+ "          WHEN pub.idVersion IS NULL THEN\n"
						+ "               0\n"
						+ "          ELSE\n"
						+ "               pub.idVersion\n"
						+ "      END,\n"
						+ "      COUNT(*) AS nDocs,\n"
						+ "      SUM(CAST(vocsize AS BIGINT)) AS grpVOCSize,\n"
						+ "      SUM(CAST(vmcsize AS BIGINT)) AS grpVMCSize\n"
						+ "  FROM " + tn(baseName + "_DOCUMENT") + " doc,\n"
						+ "       " + tn(baseName + "_PUBLICATION") + " pub,\n"
						+ "       " + tn("PUBLISHER") + " publisher\n"
						+ " WHERE doc.idPublication = pub.idPublication\n"
						+ "   AND pub.opened = 0\n"
						+ "   AND pub.idPublisher = publisher.idPublisher\n"
						+ scopeCriteria
						+ " GROUP BY pub.idPublisher, pub.idVersion\n"
						+ " ORDER BY 1, 2\n";

				break;
		}

		logger.finest("getStats:");
		logger.finest(sqlStmt);

		Statement stmt = null;
		ResultSet rs = null;
		List stats = new ArrayList();

		final int N_ACCUMULATORS         = 7;
		final int N_DOCS                 = 0;
		final int N_PARTITIONED_DOCS     = 1;
		final int N_NON_PARTITIONED_DOCS = 2;
		final int TOTAL_VOC_SIZE         = 3;
		final int TOTAL_VMC_SIZE         = 4;
		final int FOC_SIZE               = 5;
		final int FMC_SIZE               = 6;

		try {
			long[] accumulators = new long[N_ACCUMULATORS];

			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlStmt);

			while (rs.next()) {
				String key = rs.getString(1);
				int version = rs.getInt(2);
				int nDocuments = rs.getInt(3);
				long groupVOCSize = rs.getLong(4);
				long groupVMCSize = rs.getLong(5);

				int[] fixedContentsLength = getFixedContentsLength(version);

				/*
				 * Adds a new doc stats.
				 */
				DocStat stat = new DocStat();

				stat.setKey(key);
				stat.setVersion(version);
				stat.setDocumentCount(nDocuments);

				if (version != 0) {
					stat.setPartitionedDocumentCount(nDocuments);
				} else {
					stat.setNonPartitionedDocumentCount(nDocuments);
				}

				stat.setVOCSize(groupVOCSize);
				stat.setVMCSize(groupVMCSize);

				stat.setFOCSize(fixedContentsLength[0]);
				stat.setFMCSize(fixedContentsLength[1]);

				stats.add(stat);

				/*
				 * Accumulates total partial stats.
				 */
				accumulators[N_DOCS] += nDocuments;

				if (version != 0) {
					accumulators[N_PARTITIONED_DOCS] +=	nDocuments;
				} else {
					accumulators[N_NON_PARTITIONED_DOCS] += nDocuments;
				}

				accumulators[TOTAL_VOC_SIZE] +=	groupVOCSize;
				accumulators[TOTAL_VMC_SIZE] += groupVMCSize;

				accumulators[FOC_SIZE] += fixedContentsLength[0];
				accumulators[FMC_SIZE] += fixedContentsLength[1];
			}

			/*
			 * Adds total stats.
			 */
			DocStat totalStat = new DocStat();

			totalStat.setKey(null);
			totalStat.setVersion(0);

			totalStat.setDocumentCount((int) accumulators[N_DOCS]);

			totalStat.setPartitionedDocumentCount(
					(int) accumulators[N_PARTITIONED_DOCS]);

			totalStat.setNonPartitionedDocumentCount(
						(int) accumulators[N_NON_PARTITIONED_DOCS]);

			totalStat.setVOCSize(accumulators[TOTAL_VOC_SIZE]);
			totalStat.setVMCSize(accumulators[TOTAL_VMC_SIZE]);

			totalStat.setFOCSize(accumulators[FOC_SIZE]);
			totalStat.setFMCSize(accumulators[FMC_SIZE]);

			stats.add(totalStat);
		} catch (SQLException e) {
			throw new PDFPublisherException(
					  "Cannot get stats for document type "
					+ "'" + docTypeName + "'",
					e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}

		return stats;
	}

	private int[] getFixedContentsLength(int version) {

		if (version == 0) {
			return zeroesFixedContentsLength;
		}

		if (version == lastVersion) {
			return fixedContentsLength;
		}

		for (int i = 0; i < versions.length; i++) {
			DocVersion docVersion = versions[i];

			if (docVersion.getID() == version) {
				fixedContentsLength[0] = docVersion.getFOCSize();
				fixedContentsLength[1] = docVersion.getFMCSize();

				lastVersion = version;

				return fixedContentsLength;
			}
		}

		/*
		 *  This case is very rare but can happen.
		 */
		lastVersion = 0;

		return zeroesFixedContentsLength;
	}

	private void _registerDocumentType(DocType docType, DocField[] docFields,
			String[] docIDFieldNames, int[] maxPartitionSizes)
			throws SQLException {

		Statement stmt = null;

		try {
			String sqlStmt =
					  "INSERT INTO " + tn("DOCTYPE") + "\n"
					+ "     (name, baseName, idSystem, idPublisher, remark,\n"
					+ "      focMaxSize, vocMaxSize,\n"
					+ "      fmcMaxSize, vmcMaxSize)\n"
					+ "     VALUES (\n"
					+ "             '" + docType.getName() + "',\n"
					+ "             '" + docType.getBaseName() + "',\n"
					+ "             " + sourceSystemID + ",\n"
					+ "             " + publisherID + ",\n"
					+ "             '" + docType.getRemark() + "',\n"
					+ "             " + maxPartitionSizes[FOC] + ",\n"
					+ "             " + maxPartitionSizes[VOC] + ",\n"
					+ "             " + maxPartitionSizes[FMC] + ",\n"
					+ "             " + maxPartitionSizes[VMC] + "\n"
					+ "            )\n";

			logger.finest("registerDocumentType:");
			logger.finest(sqlStmt);

			stmt = connection.createStatement();
			stmt.executeUpdate(sqlStmt);

			int docTypeID = 0;

			try {
				docTypeID = getDocTypeID(docType.getName());
			} catch (PDFPublisherException e) {
				throw new SQLException(
						  "Cannot create document type "
						+ "'" + docType.getName() + "'");
			}

			int pksequence = 0;

			for (int i = 0; i < docFields.length; i++) {
				DocField field = docFields[i];
				String fieldName = field.getName();
				boolean fieldInPK = false;

				for (int j = 0; j < docIDFieldNames.length; j++) {
					if (fieldName.equals(docIDFieldNames[j])) {
						fieldInPK = true;

						break;
					}
				}

				int _pksequence = (fieldInPK) ? ++pksequence : 0;

				sqlStmt =
						  "INSERT INTO " + tn("DOCTYPE_FIELD") + "\n"
						+ "     (idDocType, name, sequence,\n"
						+ "      ftype, flength, pksequence,\n"
						+ "      labelName, remark)\n"
						+ "     VALUES (\n"
						+ "             " + docTypeID + ",\n"
						+ "             '" + field.getName() + "',\n"
						+ "             " + (i + 1) + ",\n"
						+ "             '" + field.getTypeName() + "',\n"
						+ "             " + field.getLength() + ",\n"
						+ "             " + _pksequence + ",\n"
						+ "             '" + field.getLabelName() + "',\n"
						+ "             '" + field.getRemark() + "'\n"
						+ "            )\n";

				logger.finest("add doctype_field:");
				logger.finest(sqlStmt);

				stmt.executeUpdate(sqlStmt);
			}
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
	}

	private void createDocumentTypeIndexes(DocType docType,
		DocField[] docFields, String firstDocIDFieldName)
		throws IOException, SQLException {

		String[] args = {
			schema,
			docType.getBaseName(),
			null
		};

		final int FIELD_NAME_ARG_INDEX = 2;

		for (int i = 0; i < docFields.length; i++) {
			String fieldName = docFields[i].getName();

			if (fieldName.equals(firstDocIDFieldName)) {
				continue;
			}

			args[FIELD_NAME_ARG_INDEX] = fieldName;

			runScript("DOCUMENT_INDEX", args);
		}
	}

	/**
	 * Gets table name in the documental repository to the named document type.
	 * 
	 * @param name Document type name.
	 * 
	 * @return Table name.
	 */
	protected String tn(String name) {
		return schema + "." + name;
	}

	private String blobLength(String fieldName) {
		return functionBlobLength + "(" + fieldName + ")";
	}
	
	private boolean isReservedFieldName(String name) {
		for (int i = 0; i < reservedFieldNames.length; i++) {
			if (reservedFieldNames[i].equalsIgnoreCase(name)) {
				return true;
			}
		}

		return false;
	}

	private String getSQLTraceText(String label, String sqlStmt) {
		String s = "------------------  " + label + "  --------------------";
		
		return
				  s + "\n"
				+ sqlStmt
				+ Padder.lpad("", s.length(), '-') + "\n";		
	}

	private void runScript(String name, String[] args)
			throws IOException, SQLException {

		String content = getScriptContent(name);

		for (int i = args.length - 1; i >= 0; i--) {
			content = content.replaceAll("%" + (i + 1), args[i]);
		}

		logger.finest("RUN script=" + driverKey + "/" + name + ".sql");
		logger.finest("    args[]:");

		for (int i = 0; i < args.length; i++) {
			logger.finest("           %" + (i + 1) + " = \"" + args[i] + "\"");
		}

		logger.finest("------------------  BEGIN SCRIPT  --------------------");
		logger.finest(content);
		logger.finest("------------------  END SCRIPT    -----------------");

		String[] commands = content.split(";");
		Statement stmt = null;

		try {
			stmt = connection.createStatement();

			for (int i = 0; i < commands.length; i++) {
				commands[i] = commands[i].trim();

				if (commands[i].length() > 0) {
					stmt.executeUpdate(commands[i]);
				}
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, ">< Script execution FAILED:", e);

			throw e;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}

		logger.finest("Script execution OK.");
	}

	private String getScriptContent(String name) throws IOException {

		String resourceName =
				"/etc/pdf/fpg/scripts/" + driverKey + "/" + name + ".sql";

		Class clazz = getClass();
		InputStream input = null;
		BufferedReader reader = null;

		try {
			input =	clazz.getResourceAsStream(resourceName);

			if (input == null) {
				throw new IOException(
						"Cannot found resource '" + resourceName + "'.");
			}

			reader = new BufferedReader(new InputStreamReader(input));

			String content = "";
			String line;

			while ((line = reader.readLine()) != null) {

				/*
				 * Skips commented lines.
				 */
				if (line.startsWith("--") || line.startsWith("#")) {
					continue;
				}

				content += line + "\n";
			}

			return content;
		} finally {
			if (reader != null) {
				try {
					reader.close();

					input = null;
				} catch (IOException e) {}
			}

			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {}
			}
		}
	}

	private String truncateRemark(String remark) {
		if (remark.length() <= REMARK_MAX_LENGTH) {
			return remark;
		}

		return remark.substring(0, REMARK_MAX_LENGTH);
	}

	/*
	 * Register document versions.
	 */

	/**
	 * Checks is the specified version for the named document type is
	 * registered.
	 * 
	 * @param docTypeName Document type name.
	 * 
	 * @param docVersion Document type version.
	 * 
	 * @return <code>true</code> is document version is registered,
	 *         <code>false</code> otherwise.
	 *         
	 * @throws PDFPublisherException If cannot check document version.
	 */
	public boolean isRegisteredDocVersion(String docTypeName, int docVersion)
			throws PDFPublisherException {

		return getRegisteredDocVersion(docTypeName, docVersion, false) != null;
	}

	/**
	 * Gets fixed contents to the specified version for the named document type.
	 * 
	 * @param docTypeName Document type name.
	 * 
	 * @param docVersion Document type version. It must be great than zero.
	 * 
	 * @return A <code>byte[][]</code> of two elements (partitioned content),
	 *         the first one is <i>Fixed PDF Objects Content</i> and the second
	 *         one is <i>Fixed PDF Metadata Content</i>.
	 *         
	 * @throws PDFPublisherException If cannot get fixed contents.
	 */
	public byte[][] getRegisteredDocVersion(String docTypeName, int docVersion)
			throws PDFPublisherException {

		return getRegisteredDocVersion(docTypeName, docVersion, true);
	}

	/**
	 * Registers a new version for the named document type with its associated
	 * fixed contents.
	 * 
	 * @param docTypeName Document type name.
	 * 
	 * @param docVersion Document type version. It must be great than zero.
	 * 
	 * @param remark Document type comment or description.
	 * 
	 * @param foc Fixed PDF Objects Content.
	 * 
	 * @param fmc Fixed PDF Metadata Content.
	 * 
	 * @throws PDFPublisherException If cannot register document version.
	 */
	public void registerDocVersion(String docTypeName, int docVersion,
			String remark, byte[] foc, byte[] fmc)
			throws PDFPublisherException {

		DocType docType = getDocType(docTypeName);

		if (docType == null) {
			throw new PDFPublisherException(
					"Unknown document type '" + docTypeName + "'");
		}

		String baseName = docType.getBaseName();

		if (docVersion <= 0) {
			throw new PDFPublisherException(
					"Invalid document version '" + docVersion + "'");
		}

		PreparedStatement stmt = null;

		if (remark == null) {
			remark = "NULL";
		} else {
			remark = "'" + truncateRemark(remark) + "'";
		}

		try {
			String sqlStmt =
					  "INSERT INTO " + tn(baseName + "_VERSION")
					+ "     (idVersion,idPublisher,remark,foc,fmc)"
					+ "     VALUES (?,?," + remark + ",?,?)";

			stmt = connection.prepareStatement(sqlStmt);

			stmt.setInt(1, docVersion);
			stmt.setInt(2, publisherID);
			stmt.setBlob(3, new BasicBlob(foc));
			stmt.setBlob(4, new BasicBlob(fmc));

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new PDFPublisherException(
					  "Cannot register version '" + docVersion + "' "
					+ "for document type '" + docTypeName + "'",
					e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
	}

	private byte[][] getRegisteredDocVersion(String docTypeName, int docVersion,
			boolean loadContent) throws PDFPublisherException {

		DocType docType = getDocType(docTypeName);

		if (docType == null) {
			throw new PDFPublisherException(
					"Unknown document type '" + docTypeName + "'");
		}

		String baseName = docType.getBaseName();

		if (docVersion <= 0) {
			throw new PDFPublisherException(
					"Invalid document version '" + docVersion + "'");
		}

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sqlStmt =
					  "SELECT foc, fmc"
					+ "  FROM " + tn(baseName + "_VERSION")
					+ " WHERE idVersion = ?";

			// System.out.println(sqlStmt);

			stmt = connection.prepareStatement(sqlStmt);

			stmt.setInt(1, docVersion);

			rs = stmt.executeQuery();

			if (rs.next()) {
				if (!loadContent) {
					return new byte[0][];
				}

				Blob focBlob = rs.getBlob(1);
				Blob fmcBlob = rs.getBlob(2);

				byte[][] contents = {
					focBlob.getBytes(1, (int) focBlob.length()),
					fmcBlob.getBytes(1, (int) fmcBlob.length())
				};

				return contents;
			}

			return null;
		} catch (SQLException e) {
			throw new PDFPublisherException(
					  "Cannot check registered version '" + docVersion + "' "
					+ "for document type '" + docTypeName + "'",
					e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
	}

	/*
	 * Publication and Selection Contexts Support.
	 */

	/**
	 * Opens a new publication context required to do publication operations.
	 * Nested publication contexts are unsupported.
	 * 
	 * @param docTypeName Document type name.
	 * 
	 * @param docVersion Document type version.
	 * 
	 * @param replaceMode Replace mode used in the publication.
	 * 
	 * @param strategy Publication strategy to be used.
	 * 
	 * @param remark Publication comment.
	 * 
	 * @param batchStopOnFirstFault <code>true</code> is batched statements
	 *        will stop on first fault, <code>false</code> otherwise.
	 *        
	 * @return Publication context ID.
	 * 
	 * @throws PDFPublisherException If cannot open publication context.
	 * 
	 * @see #closePublicationContext(boolean)
	 */
	public int openPublicationContext(String docTypeName, int docVersion,
			int replaceMode, int strategy, String remark,
			boolean batchStopOnFirstFault) throws PDFPublisherException {

		publicationContext =
				new PublicationSPIContext(
						this,
						docTypeName,
						docVersion,
						replaceMode,
						strategy,
						remark,
						batchStopOnFirstFault);

		return publicationContext.getContextID();
	}

	/**
	 * Publishes the PDF file specified by <code>pdfFileName</code> indexing it 
	 * with the value <code>docIndex</code>. The document will be tried as
	 * nonpartitioned one and its content will be compressed in <b>GZIP</b>
	 * format to reduce required storage space. If this document instance exists
	 * previously then it will be replaced. Publication context must be opened
	 * previuosly to can publish.
	 * 
	 * @param docIndex Document index value.
	 * 
	 * @param pdfFileName PDF file pathname to be published.
	 * 
	 * @throws PDFPublisherException If PDF document cannot be published.
	 * 
	 * @see #publish(PDFDocument)
	 */	
	public void publish(String docIndex, String pdfFileName)
			throws PDFPublisherException {

		try {
			byte[] content = FileUtils.loadFile(pdfFileName);
			byte[] gzContent = ZipUtils.gzip(content);

			publicationContext.publish(docIndex, gzContent, null);
		} catch (IOException e) {
			throw new PDFPublisherException("Publish failed", e);
		}
	}
	
	/**
	 * Publishes the PDF document instance specified by <code>document</code>.
	 * Its internal <i>FPG</i> partitioning will be preserved in the repository
	 * to best performance. If its version is uregistered then this will be
	 * registered first. If this document instance exists previously then it
	 * will be replaced. Publication context must be opened previuosly to can
	 * publish.
	 * 
	 * @param document PDF document to be published.
	 * 
	 * @throws PDFPublisherException If PDF document cannot be published.
	 * 
	 * @see #publish(String, String)
	 */
	public void publish(PDFDocument document) throws PDFPublisherException {

		try {
			publicationContext.publish(
					document.getIndex(),
					document.getVariableObjectsContent(),
					document.getVariableMetaDataContent());
		} catch (FPGException e) {
			throw new PDFPublisherException("Publish failed", e);
		}
	}

	/**
	 * Unpublishes all published PDF document instances in the specified
	 * <code>scope</code> for the named document type with <code>version>/code>
	 * document version.
	 * 
	 * @param docTypeName Document type name.
	 * 
	 * @param docVersion Document type version.
	 * 
	 * @param scope Unpublication scope.
	 * 
	 * @return Count of unpublished PDF documents.
	 * 
	 * @throws PDFPublisherException If PDF documents cannot be unpublished.
	 */
	public int unpublish(String docTypeName, int docVersion, Scope scope)
			throws PDFPublisherException {

		DocType docType = getDocType(docTypeName);

		if (docType == null) {
			throw new IllegalArgumentException(
					"Document type '" + docTypeName + "' not found");
		}

		String baseName = docType.getBaseName();
		String scopePredicate = scope.toString();
		Statement stmt = null;

		String pubQuery =
			  "EXISTS(\n"
			+ "    SELECT *\n"
			+ "      FROM " + tn(baseName + "_PUBLICATION") + " pub\n"
			+ "     WHERE pub.idPublication = doc.idPublication\n"
			+ "       AND pub.opened = 0";

		if (docVersion != 0) {
			pubQuery += " AND pub.idVersion = " + docVersion;
		}

		pubQuery += ")\n";

		if (scopePredicate.equals("")) {
			scopePredicate = pubQuery;
		} else {
			scopePredicate = "(" + scopePredicate + ") AND " + pubQuery;
		}

		try {
			String sqlStmt =
					  "DELETE\n"
				    + getStrictedDelete(baseName + "_DOCUMENT") + "\n"
				    + "  FROM " + tn(baseName + "_DOCUMENT") + " doc\n"
					+ " WHERE " + scopePredicate + "\n";

			logger.finest("unpublish:");
			logger.finest(sqlStmt);
			
			stmt = connection.createStatement();

			try {
				connection.setAutoCommit(true);
			} catch (SQLException e2) {}

			return stmt.executeUpdate(sqlStmt);
		} catch (SQLException e) {
			e.printStackTrace();

			throw new PDFPublisherException(
					"Unpublish for document type '" + docTypeName + "' failed",
					e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
	}

	/**
	 * Cleanups all empty and closed publications for the named document type.
	 * A publication can be empty because none PDF document instance was
	 * published in it or all published PDF document instances in it were
	 * unpublished later to itself.
	 * 
	 * @param docTypeName Document type name.
	 * 
	 * @return Number of cleanuped publications.
	 * 
	 * @throws PDFPublisherException If cannot cleanup publications.
	 */
	public int cleanup(String docTypeName) throws PDFPublisherException {

		DocType docType = getDocType(docTypeName);

		if (docType == null) {
			throw new IllegalArgumentException(
					"Document type '" + docTypeName + "' not found");
		}

		String baseName = docType.getBaseName();

		Statement stmt = null;

		try {
			String sqlStmt =
					  "DELETE\n"
			        + getStrictedDelete(baseName + "_PUBLICATION") + "\n"				
					+ "  FROM " + tn(baseName + "_PUBLICATION") + " pub\n"
					+ " WHERE\n"
					+ "    NOT EXISTS(\n"
					+ "        SELECT *\n"
					+ "          FROM " + tn(baseName + "_DOCUMENT") + " doc\n"
					+ "         WHERE doc.idPublication = pub.idPublication)\n";

			logger.finest("unpublish:");
			logger.finest(sqlStmt);

			stmt = connection.createStatement();

			return stmt.executeUpdate(sqlStmt);
		} catch (SQLException e) {
			throw new PDFPublisherException(
					  "Cannot cleanup empty publications for document type "
					+ "'" + docTypeName + "'",
					e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
	}

	/**
	 * Closes current publication context. If <code>commit</code> control flag
	 * is <code>true</code> this method will try to commit transactional
	 * publication, otherwise it will be rollbacked.
	 * 
	 * @param commit Publication commit control flag.
	 * 
	 * @return A <code>int[]</code> of two elements if <code>commit</code> is
	 *         <code>true</code> corresponding to the number of PDF document
	 *         instances added and updated respectively. Otherwise
	 *         <code>null</code>.
	 * 
	 * @throws PDFPublisherException If cannot close publication context.
	 * 
	 * @see #openPublicationContext(String, int, int, int, String, boolean)
	 */
	public int[] closePublicationContext(boolean commit)
			throws PDFPublisherException {

		int[] docstats = publicationContext.close(commit);

		publicationContext = null;

		return docstats;
	}

	/**
	 * Opens a new selection context required to do retrieval operations.
	 * Nested selection contexts are unsupported.
	 * 
	 * @param docTypeName Document type name.
	 * 
	 * @param docVersion Document type version.
	 * 
	 * @return Selection context ID.
	 * 
	 * @throws PDFPublisherException If cannot open selection context.
	 * 
	 * @see #closeSelectionContext()
	 */
	public int openSelectionContext(String docTypeName, int docVersion)
			throws PDFPublisherException {

		selectionContext =
				new SelectionSPIContext(this, docTypeName, docVersion);

		return selectionContext.getContextID();
	}

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
	 * <p> Finally each PDF document instance must be approved by the
	 * <code>filter</code> in order to be retrieved.
	 * </p>
	 * 
	 * @param scope Retrieval scope.
	 * 
	 * @param strict Strict document version match.
	 * 
	 * @param maxHits Maximum number of document hits.
	 * 
	 * @param filter Retrieval filter.
	 * 
	 * @return A list of {@link PublishedDocumentInfo} instances.
	 * 
	 * @throws PDFPublisherException If PDF documents's info cannot be
	 *         retrieved.
	 */	
	public List getScopeInfo(Scope scope, boolean strict, int maxHits,
			BasePDFPublisherFilter filter) throws PDFPublisherException {

		return selectionContext.getScopeInfo(scope, strict, maxHits, filter);
	}

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
	 * <p> Finally each PDF document instance must be approved by the
	 * <code>filter</code> in order to be retrieved.
	 * </p>
	 * 
	 * @param scope Retrieval scope.
	 * 
	 * @param strict Strict document version match.
	 * 
	 * @param maxHits Maximum number of document hits.
	 * 
	 * @param filter Retrieval filter.
	 * 
	 * @return A list of {@link PublishedDocument} instances.
	 * 
	 * @throws PDFPublisherException If PDF documents cannot be retrieved.
	 */	
	public PublishedDocumentList getDocuments(Scope scope, boolean strict,
			int maxHits, BasePDFPublisherFilter filter)
			throws PDFPublisherException {

		return selectionContext.getDocuments(scope, strict, maxHits, filter);
	}

	/**
	 * Closes current selection context.
	 * 
	 * @throws PDFPublisherException If cannot close selection context.
	 * 
	 * see {@link #openSelectionContext(String, int)}
	 */
	public void closeSelectionContext() throws PDFPublisherException {

		selectionContext = null;
	}
}
