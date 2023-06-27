

/*
 * @(#) SelectCommand.java    1.0 10-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.implementations.fpg.command;


import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.PrintStream;

import java.util.List;
import java.util.Iterator;

import cl.araucana.core.util.Exceptions;

import cl.araucana.pdf.publishers.PDFPublisher;
import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.PublishedDocumentInfo;
import cl.araucana.pdf.publishers.Publisher;
import cl.araucana.pdf.publishers.PublishOptions;
import cl.araucana.pdf.publishers.Scope;

import cl.araucana.pdf.publishers.implementations.fpg.*;


/**
 * Retrieves published PDF documents metadata of a document type.
 * 
 * <BR>
 * <BR>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="80%" CELLPADDING="3" CELLSPACING="0">
 *     <tr>
 *        <th bgcolor="black">
 *            <font color="white"><strong>Syntax</strong></font>
 *        </th>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <b>select [-verbose] [-o &lt;outputFileName&gt;]
 *                      [-m &lt;maxHits&gt;] [-v &lt;docVersion&gt;]
 *                      [-l] &lt;docType&gt; [&lt;scope&gt;]</b>
 *        </td>
 *     </tr>
 * </TABLE>
 * 
 * <p> The following are supported options:
 * </p>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="80%" CELLPADDING="3" CELLSPACING="0">
 *     <tr>
 *        <th bgcolor="black">
 *            <font color="white"><strong>Option</strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>Description</strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>Default</strong></font>
 *        </th>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>verbose</strong></td>
 *        
 *        <td>
 *            Reports number of retrieved PDF documents.
 *        </td>
 *        
 *        <td>
 *            No verbose.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>o</strong></td>
 *        
 *        <td>
 *            Redirects output to the specified file pathname.
 *        </td>
 *        
 *        <td>
 *            Reports to select command normal output.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>m</strong></td>
 *        
 *        <td>
 *            Maximum number of hits or retrieved PDF documents.
 *        </td>
 *        
 *        <td>
 *            {@link cl.araucana.pdf.publishers.implementations.fpg.FPGIntegratedPDFPublisher#MAX_MAX_HITS}
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>v</strong></td>
 *        
 *        <td>
 *            Specifies a particular document version to be considered.
 *        </td>
 *        
 *        <td>
 *            Any document version is considered in the retrieval operation.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>l</strong></td>
 *        
 *        <td valign="top">
 *            Detailed report per each retrieved PDF document.
 *            
 *            <BR>
 *            <BR>
 *            Outputs a line per each retrieved PDF document as following:
 *            <u>docID</u><b>::</b><u>docVersion</u><b>::</b><u>docIndex</u>.
 *            </td>
 *        
 *        <td>
 *            Minimal report per each retrieved PDF document.
 *            
 *            <BR>
 *            <BR>
 *            Outputs a line per each retrieved PDF document with
 *            <u>docIndex</u>.
 *            <BR>
 *            <BR>
 *        </td>
 *     </tr>
 * </TABLE>
 * 
 * <p> The following are supported arguments:
 * </p>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="80%" CELLPADDING="3" CELLSPACING="0">
 *     <tr>
 *        <th bgcolor="black">
 *            <font color="white"><strong>Argument</strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>Description</strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>Default</strong></font>
 *        </th>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>docType</strong></td>
 *        
 *        <td>
 *            Document type. It's mandatory.
 *        </td>
 *        
 *        <td>
 *            &nbsp;
 *        </td>
 *     </tr>
 *    
 *     <tr>
 *        <td><a href="{@docRoot}/extras/scope.html">scope</a></td>
 *        
 *        <td>
 *            Selection documents scope. It's optional.
 *        </td>
 *        
 *        <td>
 *            {@link cl.araucana.pdf.publishers.Scope#ALL}
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
 *            <TD> 10-10-2008 </TD>
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
public class SelectCommand extends FPGPublisherSPICommand
		implements DocTypeConstants {

	/**
	 * Constructs a <b>select SPI</b> command associated to the
	 * <code>shell</code>.
	 * 
	 * @param shell Associated <code>shell</code> instance.
	 */	
	public SelectCommand(FPGIntegratedPDFPublisherSPIShell shell) {
		super(shell);
	}

	/**
	 * {@inheritDoc}
	 */
	public void help(PrintStream err) {
		err.println(
				  "select [-verbose] [-o <outputFileName>] [-m <maxHits>] "
				+ "[-v <docVersion>] [-l] <docType> [<scope>]");
	}

	/**
	 * {@inheritDoc}
	 */
	public void execute(String[] args, InputStream in,
			PrintStream out, PrintStream err) {

		String outputFileName = null;
		int maxHits = FPGIntegratedPDFPublisher.MAX_MAX_HITS;
		int docVersion = 0;
		String docTypeName = null;
		Scope scope = null;
		boolean full = false;
		boolean verbose = false;

		PrintStream output = out;
		boolean strict = false;
		boolean optionable = true;

		for (int i = 0; i < args.length; i++) {
			if (!optionable && args.length > 0 && args[i].charAt(0) == '-') {
				help(err);

				return;
			} else if (args[i].equals("-verbose")) {
				verbose = true;
			} else if (args[i].equals("-o")) {
				if (i + 1 < args.length) {
					outputFileName = args[++i];
				} else {
					help(err);

					return;
				}
			} else if (args[i].equals("-m")) {
				if (i + 1 < args.length) {
					String sMaxHits = args[++i];

					try {
						maxHits = Integer.parseInt(sMaxHits);

						if (maxHits <= 0) {
							throw new NumberFormatException();
						}
					} catch (NumberFormatException e) {
						help(err);

						return;
					}
				} else {
					help(err);

					return;
				}
			} else if (args[i].equals("-v")) {
				if (i + 1 < args.length) {
					String sDocVersion = args[++i];

					try {
						docVersion = Integer.parseInt(sDocVersion);

						if (docVersion < 0) {
							throw new NumberFormatException();
						}

						strict = docVersion != 0;
					} catch (NumberFormatException e) {
						help(err);

						return;
					}
				} else {
					help(err);

					return;
				}
			} else if (args[i].equals("-l")) {
				full = true;
			} else {
				optionable = false;

				if (docTypeName == null) {
					docTypeName = args[i];
				} else if (scope == null) {
					scope = new Scope(args[i]);
				} else {
					help(err);

					return;
				}
			}
		}

		if (docTypeName == null) {
			help(err);

			return;
		}

		if (scope == null) {
			scope = Scope.ALL;
		}

		if (outputFileName != null) {
			try {
				output =
						new PrintStream (
								new FileOutputStream(outputFileName));
			} catch(IOException e) {
				err.println(
						"Cannot open output file '" + outputFileName + "'.");

				return;
			}
		}

		PDFPublisher pdfPublisher = null;

		try {
			Publisher publisher = shell.getDefaultProvider();
			PublishOptions publishOptions = new PublishOptions();

			publishOptions.setPublisher(publisher);
			publishOptions.setBatchMode(true);
			publishOptions.setPartitioned(true);
			publishOptions.setCompressed(true);
			publishOptions.setReplaceMode(PublishOptions.MODE_REPLACE);
			publishOptions.setStrategy(PublishOptions.STRATEGY_INSERT);
			publishOptions.setReadOnly(true);
			publishOptions.setLogged(true);
			publishOptions.setLogID("select");

			pdfPublisher =
					PDFPublisher.newPDFPublisher(
							docTypeName, docVersion, publishOptions);

			// pdfPublisher.setFilter(new TestPDFPublisherFilter());

			List docInfoList =
					pdfPublisher.getScopeInfo(scope, strict, maxHits);

			Iterator iterator = docInfoList.iterator();

			while (iterator.hasNext()) {
				PublishedDocumentInfo docInfo =
						(PublishedDocumentInfo) iterator.next();

				if (full) {
					output.println(
							  docInfo.getID() + "::"
							+ docInfo.getVersion() + "::"
							+ docInfo.getIndex());
				} else {
					output.println(docInfo.getIndex());
				}
			}

			if (verbose) {
				out.println(
						"\n#selected documents=" + docInfoList.size() + ".");
			}
		} catch (Exception e) {
			err.println(
					"Error: " + e.getMessage() + " " + Exceptions.getCauses(e));
		} finally {
			if (pdfPublisher != null) {
				try {
					pdfPublisher.close();
				} catch (PDFPublisherException e) {}
			}

			if (outputFileName != null) {
				output.close();
			}
		}
	}
}
