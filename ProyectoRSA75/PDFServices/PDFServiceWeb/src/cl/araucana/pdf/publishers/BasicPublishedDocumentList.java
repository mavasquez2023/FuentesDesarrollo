

/*
 * @(#) BasicPublishedDocumentList.java    1.0 22-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers;


import java.util.Collection;
import java.util.Iterator;


/**
 * This is an adapter class to support navigation over a
 * {@link java.util.Collection} of publisherd PDF document instances like
 * is required by {@link PublishedDocumentList}.
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
 *            <TD> 22-10-2008 </TD>
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
public class BasicPublishedDocumentList implements PublishedDocumentList {

	private Iterator iterator;

	/**
	 * Constructs an collection of documents from Collection
	 * <code>publishedDocuments</code>.
	 * 
	 * @param publishedDocuments Collection of documents.
	 */
	public BasicPublishedDocumentList(Collection publishedDocuments) {
		iterator = publishedDocuments.iterator();
	}

    /**
     * {@inheritDoc}
     */
	public boolean next() {
		return iterator.hasNext();
	}

    /**
     * {@inheritDoc}
     */
    public PublishedDocument getPublishedDocument()
    		throws PDFPublisherException {

		PublishedDocument publishedDocument =
				(PublishedDocument) iterator.next();

		if (publishedDocument == null) {
			throw new PDFPublisherException("No more published documents");
		}

		return publishedDocument;
	}

    /**
     * {@inheritDoc}
     */
    public void close() {
	}
}
