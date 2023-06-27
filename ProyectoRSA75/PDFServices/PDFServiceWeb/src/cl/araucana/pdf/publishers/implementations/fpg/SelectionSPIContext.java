

/*
 * @(#) SelectionSPIContext.java    1.0 11-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.implementations.fpg;


import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cl.araucana.core.util.ZipUtils;

import cl.araucana.pdf.publishers.BasePDFPublisherFilter;
import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.PublishedDocument;
import cl.araucana.pdf.publishers.PublishedDocumentInfo;
import cl.araucana.pdf.publishers.PublishedDocumentList;
import cl.araucana.pdf.publishers.Scope;


/**
 * This is a concrete <b>SPI Context</b> implementation to provide support
 * for retrieval operations. Its main objetive is to obtain high retrieval
 * performance caching required document versions and its fixed contents.
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
public class SelectionSPIContext extends SPIContext {

	private static final int MAX_FETCH_SIZE = 1000;

	private static int nextContextID = 0;

	private static Map docTypeCache = new HashMap();

	private String cacheID;
	private String docIndexFieldNamesList;
	private String docIDFieldNamesList;

	/**
	 * Constructs a selection spi context associated to the <code>spi</code>
	 * to retrieval published PDF document instances of the specified document
	 * type and version.
	 * 
	 * @param spi Associated spi instance.
	 * 
	 * @param docTypeName Document type name to retrieve.
	 * 
	 * @param docVersion Document type version to retrieve.
	 * 
	 * @throws PDFPublisherException If cannot construct selection SPI context. 
	 */
	protected SelectionSPIContext(FPGIntegratedPDFPublisherSPI spi,
			String docTypeName, int docVersion)	throws PDFPublisherException {

		super(spi, docTypeName, docVersion);

		synchronized (SelectionSPIContext.class) {
			contextID = ++nextContextID;
		}

		log("openSelectionContext: id=" + contextID);

		/*
		 * docIndex's field names list.
		 */
		docIndexFieldNamesList = "";

		for (int i = 0; i < docFields.length; i++) {
			DocField field = docFields[i];

			docIndexFieldNamesList += "doc." + field.getName();

			if (i + 1 < docFields.length) {
				docIndexFieldNamesList += ",";
			}
		}

		/*
		 * docID's field names list.
		 */
		docIDFieldNamesList = "";

		for (int i = 0; i < docIDFieldNames.length; i++) {
			docIDFieldNamesList += "doc." + docIDFieldNames[i];

			if (i + 1 < docIDFieldNames.length) {
				docIDFieldNamesList += ",";
			}
		}

		cacheID = spi.id + ":" + docTypeName;
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
	protected List getScopeInfo(Scope scope, boolean strict, int maxHits,
			BasePDFPublisherFilter filter) throws PDFPublisherException {

		String predicate = scope.toString();

		String whereClause =
				(predicate.equals(""))
						? ""
						: "   AND (" + predicate + ")\n";

		if (strict) {
			whereClause += "   AND pub.idVersion = " + docVersion + "\n";
		}

		String sqlStmt =
				  "SELECT " + docIndexFieldNamesList + ",\n"
				+ "       CASE\n"
				+ "           WHEN idVersion IS NULL THEN\n"
				+ "               0\n"
				+ "           ELSE\n"
				+ "               idVersion\n"
				+ "       END AS docVersion\n"
				+ "  FROM " + spi.tn(baseName + "_DOCUMENT") + " doc,\n"
				+ "       " + spi.tn(baseName + "_PUBLICATION") + " pub\n"
				+ " WHERE doc.idPublication = pub.idPublication\n"
				+ "   AND pub.opened = 0\n"
				+ whereClause
				+ " ORDER BY " + docIDFieldNamesList;

		//System.out.println("\ngetScopeInfo: sqlStmt=\n" + sqlStmt);

		Statement stmt = null;
		ResultSet rs = null;
		List docInfoList = new LinkedList();
		int nDocuments = 0;

		try {
			stmt = spi.connection.createStatement();

			stmt.setFetchSize(Math.min(MAX_FETCH_SIZE, maxHits));

			rs = stmt.executeQuery(sqlStmt);

			while (nDocuments < maxHits && rs.next()) {
				PublishedDocumentInfo docInfo = new PublishedDocumentInfo();

				String docID = "";

				for (int i = 0; i < docIDFieldIndexes.length; i++) {
					docID += rs.getString(docIDFieldIndexes[i] + 1).trim();

					if (i + 1 < docIDFieldIndexes.length) {
						docID += "/";
					}
				}

				String docIndex = "";

				for (int i = 0; i < docIndexFieldTypes.length; i++) {
					docIndex += rs.getString(i + 1).trim();

					if (i + 1 < docIndexFieldTypes.length) {
						docIndex += "/";
					}
				}

				docInfo.setID(docID);
				docInfo.setIndex(docIndex);
				docInfo.setVersion(rs.getInt(docIndexFieldTypes.length + 1));

				if (filter.filter(docInfo)) {
					continue;
				}

				docInfoList.add(docInfo);

				nDocuments++;
			}

			return docInfoList;
		} catch (SQLException e) {
			throw new PDFPublisherException("Cannot get scope info", e);
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
	public PublishedDocumentList getDocuments(Scope scope,
			boolean strict, int maxHits, BasePDFPublisherFilter filter)
			throws PDFPublisherException {

		String predicate = scope.toString();

		String scopeWhereClause =
				(predicate.equals(""))
						? ""
						: "   AND (" + predicate + ")\n";

		if (strict) {
			scopeWhereClause += "   AND pub.idVersion = " + docVersion + "\n";
		}

		String docIDFieldsWhereClause =
				(scopeWhereClause.equals("")) ? "" : "   AND ";

		for (int i = 0; i < docIDFieldNames.length; i++) {
			docIDFieldsWhereClause +=
					  "doc." + docIDFieldNames[i]
					+ " = "
					+ "cont."+ docIDFieldNames[i];

			if (i + 1 < docIDFieldNames.length) {
				docIDFieldsWhereClause += " AND ";
			}
		}

		docIDFieldsWhereClause += "\n";

		String sqlStmt =
				  "SELECT " + docIndexFieldNamesList + ",\n"
				+ "       CASE\n"
				+ "           WHEN idVersion IS NULL THEN\n"
				+ "               0\n"
				+ "           ELSE\n"
				+ "               idVersion\n"
				+ "       END AS docVersion,\n"
				+ "       cont.voc, cont.vmc\n"
				+ "  FROM " + spi.tn(baseName + "_DOCUMENT") + " doc,\n"
				+ "       " + spi.tn(baseName + "_PUBLICATION") + " pub,\n"
				+ "       " + spi.tn(baseName + "_CONTENT") + " cont\n"
				+ " WHERE doc.idPublication = pub.idPublication\n"
				+ "   AND pub.opened = 0\n"
				+ scopeWhereClause
				+ docIDFieldsWhereClause
				+ " ORDER BY " + docIDFieldNamesList;

		//System.out.println("\ngetDocuments: sqlStmt=\n" + sqlStmt);

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = spi.connection.createStatement();

			stmt.setFetchSize(Math.min(MAX_FETCH_SIZE, maxHits));

			rs = stmt.executeQuery(sqlStmt);

			return new FPGPublishedDocumentList(stmt, rs, maxHits, filter);
		} catch (SQLException e) {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e1) {}
			}

			throw new PDFPublisherException("Cannot get documents", e);
		}
	}

	private class FPGPublishedDocumentList implements PublishedDocumentList,
			DocTypeConstants {

		private Statement stmt;
		private ResultSet rs = null;
		private int maxHits;
		private int nDocuments = 0;
		private BasePDFPublisherFilter filter;
		private PublishedDocument nextDocument;

		private FPGPublishedDocumentList(Statement stmt, ResultSet rs,
				int maxHits, BasePDFPublisherFilter filter) {

			this.stmt = stmt;
			this.rs = rs;
			this.maxHits = maxHits;
			this.filter = filter;
		}

		public boolean next() throws PDFPublisherException {

			try {
				while(nDocuments < maxHits && rs.next()) {
					nextDocument = _getPublishedDocument();

					if (nextDocument != null) {
						return true;
					}
				}

				return false;
			} catch (SQLException e) {
				throw new PDFPublisherException(
						"Internal Exception in PublishedDocumentList", e);
			}
		}

		public PublishedDocument getPublishedDocument()
						throws PDFPublisherException {

			return nextDocument;
		}

		private PublishedDocument _getPublishedDocument()
				throws PDFPublisherException {

			try {
				PublishedDocument publishedDocument = new PublishedDocument();

				String docID = "";

				for (int i = 0; i < docIDFieldIndexes.length; i++) {
					docID += rs.getString(docIDFieldIndexes[i] + 1).trim();

					if (i + 1 < docIDFieldIndexes.length) {
						docID += "/";
					}
				}

				String docIndex = "";

				for (int i = 0; i < docIndexFieldTypes.length; i++) {
					docIndex += rs.getString(i + 1).trim();

					if (i + 1 < docIndexFieldTypes.length) {
						docIndex += "/";
					}
				}

				publishedDocument.setID(docID);
				publishedDocument.setIndex(docIndex);

				int index = docIndexFieldTypes.length + 1;
				int docVersion = rs.getInt(index++);

				publishedDocument.setVersion(docVersion);

				if (filter.filter(publishedDocument)) {
					return null;
				}

				byte[][] content;

				if (docVersion != 0) {
					content = new byte[NPARTITIONS][];

					content[VOC] = getBlob(index++);
					content[VMC] = getBlob(index);

					byte[][] fixedContent;

					Integer iDocVersion = new Integer(docVersion);

					/*
					 * Critical code to manage document versions cache.
					 */
					synchronized (docTypeCache) {
						Map docVersionCache = (Map) docTypeCache.get(cacheID);

						if (docVersionCache != null) {
							fixedContent =
									(byte[][]) docVersionCache.get(iDocVersion);

							if (fixedContent == null) {
								fixedContent =
										spi.getRegisteredDocVersion(
												docTypeName, docVersion);

								docVersionCache.put(
										iDocVersion, fixedContent);

								//System.out.println(
								//		  "Caching fixed content for "
								//		+ "'" + cacheID + "' "
								//		+ "version " + docVersion + ". (2)");
							} else {
								//System.out.println(
								//		  "Using Cached fixed content for "
								//		+ "'" + cacheID + "' "
								//		+ "version " + docVersion + ".");
							}
						} else {
							fixedContent =
									spi.getRegisteredDocVersion(
											docTypeName, docVersion);

							docVersionCache = new HashMap();

							docVersionCache.put(iDocVersion, fixedContent);
							docTypeCache.put(cacheID, docVersionCache);

							//System.out.println(
							//		  "Caching fixed content for "
							//		+ "'" + cacheID + "' "
							//		+ "version " + docVersion + ". (1)");
						}
					}

					content[FOC] = fixedContent[0];
					content[FMC] = fixedContent[1];
				} else {
					content = new byte[NONVERSIONED_NPARTITIONS][];
					content[ALL_PDF_CONTENT] = ZipUtils.gunzip(getBlob(index));
				}

				publishedDocument.setContent(content);

				nDocuments++;

				return publishedDocument;
			} catch (SQLException e) {
				throw new PDFPublisherException(
						"Internal Exception in PublishedDocumentList", e);
			}
		}

		private byte[] getBlob(int index) throws SQLException {

			Blob blob = rs.getBlob(index);

			return blob.getBytes(1, (int) blob.length());
		}

		public void close() throws PDFPublisherException {

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
}
