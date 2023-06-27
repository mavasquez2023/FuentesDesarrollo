

/*
 * @(#) PublicationSPIContext.java    1.0 11-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.implementations.fpg;


import java.sql.BatchUpdateException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import cl.araucana.core.util.sql.BasicBlob;

import cl.araucana.pdf.publishers.BadDocIndexPDFPublisherException;
import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.PublishOptions;


/**
 * This is a concrete <b>SPI Context</b> implementation to provide support
 * for publication operations. Its main objetive is to support transaccional
 * publications and to obtain high publication performance using prepared and
 * batched JDBC statements plus publication strategies to reduce the number
 * of duplication and nonexist cases with PDF documents to be published. 
 *
 * <p> This class support the following publication strategies defined in
 * {@link PublishOptions}:
 * </p>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="80%" CELLPADDING="3" CELLSPACING="0">
 *     <tr>
 *        <th bgcolor="black">
 *            <font color="white"><strong>Strategy</strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>Description</strong></font>
 *        </th>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <b>INSERT</b>
 *        </td>
 *        
 *        <td>
 *            This strategy is recommendable when <u>all or major part of</u>
 *            PDF document instances to be published <u>aren't published
 *            previously</u>. All valid PDF document instances will be
 *            published. However those that were published previously will have
 *            additional overhead to be <u>republished</u>.       
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <b>UPDATE</b>
 *        </td>
 *        
 *        <td>
 *            This strategy is recommendable when <u>all or major part of</u>
 *            PDF document instances to be published <u>are published
 *            previously</u>. All valid PDF document instances will be
 *            republished. However those that were not published previously
 *            will have additional overhead to be <u>published</u>. 
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
 *            <TD> 11-10-2008 </TD>
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
public class PublicationSPIContext extends SPIContext {

	private static final int MAX_BATCH_SIZE = 100;

	private static Pattern pattern;

	private int strategy;
	private boolean batchStopOnFirstFault;

	private int[] nonDocIDFieldTypes;
	private int[] nonDocIDFieldIndexes;

	private PreparedStatement insertDocPreparedStmt;
	private PreparedStatement insertContentPreparedStmt;

	private PreparedStatement updateDocPreparedStmt;
	private PreparedStatement updateContentPreparedStmt;

	private IPD[] insertedIPD = new IPD[MAX_BATCH_SIZE];
	private IPD[] updatedIPD = new IPD[MAX_BATCH_SIZE];

	private int insertBatchSize;
	private int updateBatchSize;

	// Stats.
	private int nAddedDocuments;
	private int nUpdatedDocuments;

	private int[][] insertRowCounts = new int[2][];
	private int[][] updateRowCounts = new int[2][];

	static {
		try {
			pattern = Pattern.compile("/");
		} catch (PatternSyntaxException e) {}
	}

	/**
	 * Constructs a publication spi context associated to the <code>spi</code>
	 * to publish PDF document instances with the specified document type and
	 * version.
	 * 
	 * @param spi Associated spi instance.
	 * 
	 * @param docTypeName Document type name to publish.
	 * 
	 * @param docVersion Document type version to publish.
	 * 
	 * @param replaceMode Replace mode to be used.
	 *        (<b>RESERVED FOR FUTURE USES</b>)
	 * 
	 * @param strategy Publication strategy to publish.
	 * 
	 * @param remark Publication remark or comment.
	 * 
	 * @param batchStopOnFirstFault Batch stop control flag.
	 *   (See <a href="FPGIntegratedPDFPublisherSPI.html#batchStopOnFirstFault">
	 *   to more information)
	 * 
	 * @throws PDFPublisherException If cannot construct publication SPI
	 *         context. 
	 */
	protected PublicationSPIContext(FPGIntegratedPDFPublisherSPI spi,
			String docTypeName, int docVersion,	int replaceMode, int strategy,
			String remark, boolean batchStopOnFirstFault)
			throws PDFPublisherException {

		super(spi, docTypeName, docVersion);

		this.strategy = strategy;
		this.batchStopOnFirstFault = batchStopOnFirstFault;

		try {
			spi.connection.setAutoCommit(false);

			contextID = getNewPublication(remark);
		} catch (SQLException e) {
			throw new PDFPublisherException(
					"Cannot open publication context", e);
		} catch (PDFPublisherException e) {
			try {
				spi.connection.setAutoCommit(true);
			} catch (SQLException e2) {}

			throw e;
		}

		log(
				  "openPublicationContext: id=" + contextID + " "
				+ "strategy=" + strategy + " "
				+ "batchStopOnFirstFault=" + batchStopOnFirstFault);

		/*
		 *  SQL INSERT for a new document and content.
		 */
		String docFieldNames = "";
		String docFieldValues = "";

		for (int i = 0; i < docFields.length; i++) {
			DocField field = docFields[i];

			docFieldNames += field.getName() + ",";
			docFieldValues += "?,";
		}

		docFieldNames += "idPublication, vocsize, vmcsize";
		docFieldValues += contextID + ",?,";

		if (docVersion == 0) {
			docFieldValues += "0";
		} else {
			docFieldValues += "?";
		}

		String insertDocSQLStmt =
				  "INSERT INTO " + spi.tn(baseName + "_DOCUMENT") + "\n"
				+ "           (" + docFieldNames + ")\n"
				+ "     VALUES(" + docFieldValues + ")\n";

		//System.out.println(
		//		  "PublicationSPIContext: "
		//		+ docTypeName + ", insertDocSQLStmt :: "
		//		+ insertDocSQLStmt);

		String contentFieldNames = "";
		String contentFieldValues = "";

		for (int i = 0; i < docIDFieldNames.length; i++) {
			String name = docIDFieldNames[i];

			contentFieldNames += name + ",";
			contentFieldValues += "?,";
		}

		contentFieldNames += "voc, vmc";
		contentFieldValues += "?,";

		if (docVersion == 0) {
			contentFieldValues += "NULL";
		} else {
			contentFieldValues += "?";
		}

		String insertContentSQLStmt =
				  "INSERT INTO " + spi.tn(baseName + "_CONTENT") + "\n"
				+ "           (" + contentFieldNames + ")\n"
				+ "     VALUES(" + contentFieldValues + ")\n";

		//System.out.println(
		//		  "PublicationSPIContext: "
		//		+ docTypeName + ", insertContentSQLStmt :: "
		//		+ insertContentSQLStmt);

		/*
		 *  SQL UPDATE for replace an old document and content.
		 */
		nonDocIDFieldIndexes =
				new int[docFields.length - docIDFieldNames.length];

		nonDocIDFieldTypes =
				new int[docFields.length - docIDFieldNames.length];

		int n = 0;
		String fieldDocSetters = "";

		for (int i = 0; i < docFields.length; i++) {
			DocField field = docFields[i];
			String fieldName = field.getName();
			boolean found = false;

			for (int j = 0; j < docIDFieldNames.length; j++) {
				String docIDFieldName = docIDFieldNames[j];

				if (field.getName().equals(docIDFieldName)) {
					found = true;

					break;
				}
			}

			if (!found) {
				nonDocIDFieldIndexes[n] = i;
				nonDocIDFieldTypes[n++] = field.getType();

				fieldDocSetters += fieldName + " = ?, ";
			}
		}

		fieldDocSetters +=
				  "idPublication = " + contextID + ", "
				+ "vocsize = ?, vmcsize = ";

		if (docVersion == 0) {
			fieldDocSetters += "0";
		} else {
			fieldDocSetters += "?";
		}

		String docIDFieldsWhereClause = "";

		for (int i = 0; i < docIDFieldNames.length; i++) {
			docIDFieldsWhereClause += docIDFieldNames[i] + " = ?";

			if (i + 1 < docIDFieldNames.length) {
				docIDFieldsWhereClause += " AND ";
			}
		}

		String updateDocSQLStmt =
				  "UPDATE " + spi.tn(baseName + "_DOCUMENT") + "\n"
				+ "   SET " + fieldDocSetters + "\n"
				+ " WHERE " + docIDFieldsWhereClause + "\n";

		//System.out.println(
		//		  "PublicationSPIContext: "
		//		+ docTypeName + ", updateDocSQLStmt :: "
		//		+ updateDocSQLStmt);

		String fieldContentSetters = "voc = ?, vmc = ";

		if (docVersion == 0) {
			fieldContentSetters += "NULL";
		} else {
			fieldContentSetters += "?";
		}

		String updateContentSQLStmt =
				  "UPDATE " + spi.tn(baseName + "_CONTENT") + "\n"
				+ "   SET " + fieldContentSetters + "\n"
				+ " WHERE " + docIDFieldsWhereClause + "\n";

		//System.out.println(
		//		  "PublicationSPIContext: "
		//		+ docTypeName + ", updateContentSQLStmt :: "
		//		+ updateContentSQLStmt);

		try {
			insertDocPreparedStmt =
					spi.connection.prepareStatement(insertDocSQLStmt);

			insertContentPreparedStmt =
					spi.connection.prepareStatement(insertContentSQLStmt);

			updateDocPreparedStmt =
					spi.connection.prepareStatement(updateDocSQLStmt);

			updateContentPreparedStmt =
					spi.connection.prepareStatement(updateContentSQLStmt);
		} catch (SQLException e) {
			try {
				spi.connection.setAutoCommit(true);
			} catch (SQLException e2) {}

			throw new PDFPublisherException(
					"Cannot create publication context", e);
		}
	}

	/**
	 * Publishes a PDF document instance with its specified index and
	 * the corresponding variable contents. The document will be publish
	 * using the publication strategy indicated to this context.
	 *  
	 * @param docIndex Document index.
	 * 
	 * @param voc Variable PDF Objects Content.
	 * 
	 * @param vmc Variable PDF Metadata Content.
	 * 
	 * @throws PDFPublisherException If cannot publish the PDF document
	 *         instance. 
	 */
	protected void publish(String docIndex, byte[] voc, byte[] vmc)
			throws PDFPublisherException {

		String[] indexValues = pattern.split(docIndex);

		if (indexValues.length != docIndexFieldTypes.length) {
			throw new BadDocIndexPDFPublisherException(docIndex);
		}

		try {
			if (strategy == PublishOptions.STRATEGY_INSERT) {
				insert(indexValues, voc, vmc, true);
			} else {					// STRATEGY_UPDATE.
				update(indexValues, voc, vmc, true);
			}
		} catch (SQLException e) {
			throw new PDFPublisherException(
					"Cannot publish document '" + docIndex + "'", e);
		}
	}

	/**
	 * Closes transactionally this publication. If <code>commit</code>
	 * transaction control flag is <code>true</code> then publication will try
	 * to be commited, otherwise it will be rollbacked.
	 * 
	 * @param commit Commit transaction control flag.
	 * 
	 * @return Array of two counters corresponding to the nuumber of added
	 *         and updated PDF document instances when <code>commit</code> is
	 *         <code>true</code>, otherwise <code>null</code>.
	 * 
	 * @throws PDFPublisherException If publication cannot be commited.
	 */
	protected int[] close(boolean commit) throws PDFPublisherException {

		log("closePublicationContext() commit=" + commit);

		try {
			if (commit) {
				if (strategy == PublishOptions.STRATEGY_INSERT) {
					while (insertBatchSize > 0) {
						executeInsertBatch();
					}

					executeUpdateBatch();
				} else {					// STRATEGY_UPDATE.
					while (updateBatchSize > 0) {
						executeUpdateBatch();
					}

					executeInsertBatch();
				}

				closePublication();
			}
		} catch (SQLException e) {
			throw new PDFPublisherException(
					"closePublicationContext failed", e);
		} finally {
			try {
				if (commit) {
					spi.connection.commit();
				} else {
					spi.connection.rollback();
				}

				spi.connection.setAutoCommit(true);
			} catch (SQLException e) {
				throw new PDFPublisherException(
					"closePublicationContext failed", e);
			}
		}

		if (commit) {
			return new int[] {
					nAddedDocuments, nUpdatedDocuments };
		}

		return null;
	}

	private void insert(String[] indexValues, byte[] voc, byte[] vmc,
			boolean translatable) throws SQLException {

		if (insertBatchSize == MAX_BATCH_SIZE) {
			executeInsertBatch();
		}

		insertedIPD[insertBatchSize++] =
				new IPD(indexValues, voc, vmc, translatable);

		/*
		 * Inserts document.
		 */
		int paramIndex = 1;

		for (int i = 0; i < docIndexFieldTypes.length; i++) {
			int fieldType = docIndexFieldTypes[i];

			switch (fieldType) {
				case FIELD_TYPE_INT:

					insertDocPreparedStmt.setInt(
							paramIndex++, Integer.parseInt(indexValues[i]));

					break;

				case FIELD_TYPE_STRING:
				case FIELD_TYPE_CHAR:

					insertDocPreparedStmt.setString(
							paramIndex++, indexValues[i]);

					break;
			}
		}

		insertDocPreparedStmt.setInt(paramIndex++, voc.length);

		if (docVersion != 0) {
			insertDocPreparedStmt.setInt(paramIndex, vmc.length);
		}

		insertDocPreparedStmt.addBatch();

		/*
		 * Inserts content.
		 */
		paramIndex = 1;

		for (int i = 0; i < docIDFieldTypes.length; i++) {
			int fieldType = docIDFieldTypes[i];

			switch (fieldType) {
				case FIELD_TYPE_INT:

					insertContentPreparedStmt.setInt(
							paramIndex++,
							Integer.parseInt(
									indexValues[docIDFieldIndexes[i]]));

					break;

				case FIELD_TYPE_STRING:
				case FIELD_TYPE_CHAR:

					insertContentPreparedStmt.setString(
							paramIndex++, indexValues[docIDFieldIndexes[i]]);

					break;
			}
		}

		insertContentPreparedStmt.setBlob(paramIndex++, new BasicBlob(voc));

		if (docVersion != 0) {
			insertContentPreparedStmt.setBlob(paramIndex, new BasicBlob(vmc));
		}

		insertContentPreparedStmt.addBatch();
	}

	private void update(String[] indexValues, byte[] voc, byte[] vmc,
			boolean translatable) throws SQLException {

		if (updateBatchSize == MAX_BATCH_SIZE) {
			executeUpdateBatch();
		}

		updatedIPD[updateBatchSize++] =
				new IPD(indexValues, voc, vmc, translatable);

		/*
		 * Updates document.
		 */
		int paramIndex = 1;

		for (int i = 0; i < nonDocIDFieldTypes.length; i++) {
			int fieldIndex = nonDocIDFieldIndexes[i];
			int fieldType = nonDocIDFieldTypes[i];

			switch (fieldType) {
				case FIELD_TYPE_INT:

					updateDocPreparedStmt.setInt(
							paramIndex++,
							Integer.parseInt(indexValues[fieldIndex]));

					break;

				case FIELD_TYPE_STRING:
				case FIELD_TYPE_CHAR:

					updateDocPreparedStmt.setString(
							paramIndex++, indexValues[fieldIndex]);

					break;
			}
		}

		updateDocPreparedStmt.setInt(paramIndex++, voc.length);

		if (docVersion != 0) {
			updateDocPreparedStmt.setInt(paramIndex++, vmc.length);
		}

		for (int i = 0; i < docIDFieldTypes.length; i++) {
			int fieldIndex = docIDFieldIndexes[i];
			int fieldType = docIDFieldTypes[i];

			switch (fieldType) {
				case FIELD_TYPE_INT:

					updateDocPreparedStmt.setInt(
							paramIndex++,
							Integer.parseInt(indexValues[fieldIndex]));

					break;

				case FIELD_TYPE_STRING:
				case FIELD_TYPE_CHAR:

					updateDocPreparedStmt.setString(
							paramIndex++, indexValues[fieldIndex]);

					break;
			}
		}

		updateDocPreparedStmt.addBatch();

		/*
		 * Updates content.
		 */
		paramIndex = 1;

		updateContentPreparedStmt.setBlob(paramIndex++, new BasicBlob(voc));

		if (docVersion != 0) {
			updateContentPreparedStmt.setBlob(paramIndex++, new BasicBlob(vmc));
		}

		for (int i = 0; i < docIDFieldTypes.length; i++) {
			int fieldType = docIDFieldTypes[i];

			switch (fieldType) {
				case FIELD_TYPE_INT:

					updateContentPreparedStmt.setInt(
							paramIndex++,
							Integer.parseInt(
									indexValues[docIDFieldIndexes[i]]));

					break;

				case FIELD_TYPE_STRING:
				case FIELD_TYPE_CHAR:

					updateContentPreparedStmt.setString(
							paramIndex++, indexValues[docIDFieldIndexes[i]]);

					break;
			}
		}

		updateContentPreparedStmt.addBatch();
	}

	private void executeInsertBatch() throws SQLException {

		if (insertBatchSize == 0) {
			return;
		}

		log("executeInsertBatch() ...");

		BatchUpdateException insertDocBatchUpdateException = null;
		BatchUpdateException insertContentBatchUpdateException = null;

		try {
			insertRowCounts[0] = insertDocPreparedStmt.executeBatch();
		} catch (BatchUpdateException e) {
			insertDocBatchUpdateException = e;
		}

		try {
			insertRowCounts[1] = insertContentPreparedStmt.executeBatch();
		} catch (BatchUpdateException e) {
			insertContentBatchUpdateException = e;
		}

		/*
		 * Checks if both batches terminated OK.
		 */
		if (insertDocBatchUpdateException == null
				&& insertContentBatchUpdateException == null) {

			insertBatchSize = 0;
			nAddedDocuments += insertRowCounts[0].length;

			log("insertBatch() OK. rowCounts=" + insertRowCounts[0].length);

			return;
		}

		if (insertDocBatchUpdateException != null
				&& insertContentBatchUpdateException == null) {

			insertDocBatchUpdateException.printStackTrace();

			throw new SQLException(
					"insertBatch: Unexpected exception [Only doc failed]");
		} else if (insertDocBatchUpdateException == null
				&& insertContentBatchUpdateException != null) {

			insertContentBatchUpdateException.printStackTrace();

			throw new SQLException(
					"insertBatch: Unexpected exception [Only content failed]");
		}

		log("insertBatch() BatchUpdateException.");

		insertDocPreparedStmt.clearBatch();
		insertContentPreparedStmt.clearBatch();

		int[] docRowCounts = insertDocBatchUpdateException.getUpdateCounts();

		int[] contentRowCounts =
				insertDocBatchUpdateException.getUpdateCounts();

		if (docRowCounts.length != contentRowCounts.length) {
			throw new SQLException(
					  "insertBatch: Unexpected exception "
					+ "[mismatch row counts length]");
		}

		if (batchStopOnFirstFault) {
			log("insertBatch() OK. rowCounts=" + docRowCounts.length);

			translateUpdate(insertedIPD[docRowCounts.length]);

			int _insertBatchSize = insertBatchSize;

			insertBatchSize = 0;

			for (int i = docRowCounts.length + 1; i < _insertBatchSize; i++) {
				IPD ipd = insertedIPD[i];

				insert(ipd.indexValues, ipd.voc, ipd.vmc, true);
			}
		} else {
			insertBatchSize = 0;

			if (FPGIntegratedPDFPublisherSPI.debug) {
				int okRows = 0;

				for (int i = 0; i < docRowCounts.length; i++) {
					if (docRowCounts[i] != Statement.EXECUTE_FAILED) {
						okRows++;
					}
				}

				log("insertBatch() OK. rowCounts=" + okRows);
			}

			for (int i = 0; i < docRowCounts.length; i++) {
				if (docRowCounts[i] == Statement.EXECUTE_FAILED) {
					if (contentRowCounts[i] != Statement.EXECUTE_FAILED) {
						throw new SQLException(
								  "insertBatch: Unexpected exception "
								+ "[mismatch row counts states]");
					}

					translateUpdate(insertedIPD[i]);
				}
			}
		}
	}

	private void executeUpdateBatch() throws SQLException {

		if (updateBatchSize == 0) {
			return;
		}

		log("executeUpdateBatch() ...");

		BatchUpdateException updateDocBatchUpdateException = null;
		BatchUpdateException updateContentBatchUpdateException = null;

		try {
			updateRowCounts[0] = updateDocPreparedStmt.executeBatch();
		} catch (BatchUpdateException e) {
			updateDocBatchUpdateException = e;
		}

		try {
			updateRowCounts[1] = updateContentPreparedStmt.executeBatch();
		} catch (BatchUpdateException e) {
			updateContentBatchUpdateException = e;
		}

		/*
		 * Checks if both batches terminated OK.
		 */
		if (updateDocBatchUpdateException == null
				&& updateContentBatchUpdateException == null) {

			updateBatchSize = 0;

			boolean ok = true;

			for (int i = 0; i < updateRowCounts[0].length; i++) {
				if (updateRowCounts[0][i] == 0) {
					translateInsert(updatedIPD[i]);

					ok = false;
				} else if (updateRowCounts[0][i] == 1) {
					nUpdatedDocuments++;
				} else {
					throw new SQLException(
							"updateBatch: Unexpected row count"
							+ "'" + updateRowCounts[0][i] + "'.");
				}
			}

			if (ok) {
				log("updateBatch() OK. rowCounts=" + updateRowCounts[0].length);
			}

			return;
		}

		if (updateDocBatchUpdateException != null
				&& updateContentBatchUpdateException == null) {

			updateDocBatchUpdateException.printStackTrace();

			throw new SQLException(
					"updateBatch: Unexpected exception [Only doc failed]");
		} else if (updateDocBatchUpdateException == null
				&& updateContentBatchUpdateException != null) {

			updateContentBatchUpdateException.printStackTrace();

			throw new SQLException(
					"updateBatch: Unexpected exception [Only content failed]");
		}

		log("updateBatch() BatchUpdateException.");

		updateDocPreparedStmt.clearBatch();
		updateContentPreparedStmt.clearBatch();

		int[] docRowCounts = updateDocBatchUpdateException.getUpdateCounts();

		int[] contentRowCounts =
				updateContentBatchUpdateException.getUpdateCounts();

		if (docRowCounts.length != contentRowCounts.length) {
			throw new SQLException(
					  "updateBatch: Unexpected exception "
					+ "[mismatch row counts length]");
		}

		if (batchStopOnFirstFault) {
			log("updateBatch() OK. rowCounts=" + docRowCounts.length);

			translateInsert(updatedIPD[docRowCounts.length]);

			int _updateBatchSize = updateBatchSize;

			updateBatchSize = 0;

			for (int i = docRowCounts.length + 1; i < _updateBatchSize; i++) {
				IPD ipd = updatedIPD[i];

				update(ipd.indexValues, ipd.voc, ipd.vmc, true);
			}
		} else {
			updateBatchSize = 0;

			if (FPGIntegratedPDFPublisherSPI.debug) {
				int okRows = 0;

				for (int i = 0; i < docRowCounts.length; i++) {
					if (docRowCounts[i] != Statement.EXECUTE_FAILED) {
						okRows++;
					}
				}

				log("updateBatch() OK. rowCounts=" + okRows);
			}

			for (int i = 0; i < docRowCounts.length; i++) {
				if (docRowCounts[i] == Statement.EXECUTE_FAILED) {
					if (contentRowCounts[i] != Statement.EXECUTE_FAILED) {
						throw new SQLException(
								  "updateBatch: Unexpected exception "
								+ "[mismatch row counts states]");
					}

					translateInsert(updatedIPD[i]);
				}
			}
		}
	}

	private void translateUpdate(IPD ipd) throws SQLException {

		if (!ipd.translatable) {
			throw new SQLException("Translate update failed.");
		}

		log(
				  "insert for "
				+ "'" + getDocID(ipd.indexValues) + "' "
				+ "translated to update.");

		update(ipd.indexValues, ipd.voc, ipd.vmc, false);
	}

	private void translateInsert(IPD ipd) throws SQLException {

		if (!ipd.translatable) {
			throw new SQLException("Translate insert failed.");
		}

		log(
				  "update for "
				+ "'" + getDocID(ipd.indexValues) + "' "
				+ "translated to insert.");

		insert(ipd.indexValues, ipd.voc, ipd.vmc, false);
	}

	private int getNewPublication(String remark) throws PDFPublisherException {

		String fieldValues = spi.sourceSystemID + "," + spi.publisherID + ",";

		if (docVersion == 0) {
				fieldValues += "NULL,";
		} else {
			fieldValues += docVersion + ",";
		}

		if (remark == null) {
				fieldValues += "NULL,";
		} else {
			fieldValues += "'" + remark + "',";
		}

		fieldValues += "1";

		String sqlStmt =
				  "INSERT INTO " + spi.tn(baseName + "_PUBLICATION") + "\n"
				+ "       (idSystem, idPublisher, idVersion, remark, opened)\n"
				+ "       VALUES(" + fieldValues + ")\n";

		// System.out.println("getNewPublication() " + sqlStmt);

		Statement insertStmt = null;
		Statement selectStmt = null;
		ResultSet rs = null;

		try {
			insertStmt = spi.connection.createStatement();

			insertStmt.executeUpdate(sqlStmt);

			sqlStmt =
					spi.generatedKeyHandler.getStmt(baseName + "_PUBLICATION");
			
			selectStmt = spi.connection.createStatement();
			rs = selectStmt.executeQuery(sqlStmt);					
			
			rs.next();

			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();

			throw new PDFPublisherException(
					"Cannot get new publication for '" + baseName + "'", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

			if (insertStmt != null) {
				try {
					insertStmt.close();
				} catch (SQLException e) {}
			}

			if (selectStmt != null) {
				try {
					selectStmt.close();
				} catch (SQLException e) {}
			}
		}
	}

	private void closePublication() throws PDFPublisherException {

		String sqlStmt =
				  "UPDATE " + spi.tn(baseName + "_PUBLICATION") + "\n"
				+ "   SET opened = 0\n"
				+ " WHERE idPublication = " + contextID + "\n";

		//System.out.println("closePublication() " + sqlStmt);

		Statement updateStmt = null;

		try {
			updateStmt = spi.connection.createStatement();

			updateStmt.executeUpdate(sqlStmt);
		} catch (SQLException e) {
			throw new PDFPublisherException(
					  "Cannot close publication '" + contextID + "' "
					+ "for '" + baseName + "'", e);
		} finally {
			if (updateStmt != null) {
				try {
					updateStmt.close();
				} catch (SQLException e) {}
			}
		}
	}

	private class IPD {
		private String[] indexValues;
 		private byte[] voc;
 		private byte[] vmc;
 		private boolean translatable;

		private IPD(String[] indexValues, byte[] voc, byte[] vmc,
				boolean translatable) {

			this.indexValues = indexValues;
			this.voc = voc;
			this.vmc = vmc;
			this.translatable = translatable;
		}
	}
}
