

/*
 * @(#) DummyPDFDocumentSaver.java    1.0 14-10-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.pdf.publishers.util;


import java.io.IOException;
import java.io.PrintStream;

import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.PublishedDocument;
import cl.araucana.pdf.publishers.PublishedDocumentList;


/**
 * This class implements a dummy PDF documents saver for prototype purposes. No
 * saves PDF documents, just displays generated PDF document pathname for each
 * document to the specified output.
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
 *            <TD align="center"> <B>Versi�n</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripci�n</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 14-10-2008 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Germ�n Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versi�n inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Germ�n Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class DummyPDFDocumentSaver extends PDFDocumentSaver {

	private PrintStream out;

	/**
	 * Constructs a saver instance to save <code>publishedDocumentList</code>
	 * collection of PDF document instances generation their pathnames with
	 * <code>generator</code>.
	 * 
	 * @param publishedDocumentList Collection of PDF document instances to be
	 *        saved.
	 *        
	 * @param generator PDF document pathname generator.
	 * 
	 * @param out Output to displays generated pathnames.
	 */	
	public DummyPDFDocumentSaver(PublishedDocumentList publishedDocumentList,
			PDFDocumentNameGenerator generator, PrintStream out) {

		super(publishedDocumentList, generator);

		this.out = out;
	}

	/**
	 * {@inheritDoc}
	 */
	public void save(PublishedDocument publishedDocument)
			throws PDFPublisherException, IOException {

		out.println(
				  generator.generateName(publishedDocument.getIndex())
				+ " : "
				+ publishedDocument.getIndex());
	}

	/**
	 * Closes this saver.
	 */	
	public void close() {
	}
}
