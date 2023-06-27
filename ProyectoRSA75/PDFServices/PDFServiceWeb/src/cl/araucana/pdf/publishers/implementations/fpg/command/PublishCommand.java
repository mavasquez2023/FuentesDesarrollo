

/*
 * @(#) PublishCommand.java    1.0 04-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.implementations.fpg.command;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.PrintStream;

import cl.araucana.core.util.Exceptions;
import cl.araucana.core.util.Padder;

import cl.araucana.pdf.publishers.PDFPublisher;
import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.Publisher;
import cl.araucana.pdf.publishers.PublishOptions;

import cl.araucana.pdf.publishers.implementations.fpg.*;


/**
 * Publishes a set of PDF files in a document type.
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
 *            <b>publish [-verbose] &lt;docType&gt; &lt;strategy&gt;
 *                       &lt;indexFileName&gt; [&lt;remark&gt;]</b>
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
 *            Displays a message for each PDF documents to be published and
 *            number of published PDF documents.
 *        </td>
 *        
 *        <td>
 *            No verbose.
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
 *        <td><strong>strategy</strong></td>
 *        
 *        <td>
 *            Publication strategy. Valid strategies are: <b>insert</b> and
 *            <b>update</b>. It's mandatory.
 *        </td>
 *        
 *        <td>
 *            &nbsp;
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>indexFileName</strong></td>
 *        
 *        <td>
 *            Index file pathname which content specifies PDF files to be
 *            published. It's mandatory.
 *            
 *            <BR>
 *            <BR>
 *            An index file is a text one that must have two lines per each
 *            PDF file to be published. The first one is its pathname
 *            (<b>content</b>) and the second one is the corresponding document
 *            index. (<b>docIndex</b>)
 *        </td>
 *        
 *        <td>
 *            &nbsp;
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>remark</strong></td>
 *        
 *        <td>
 *            Publication remark. It's optional.
 *        </td>
 *        
 *        <td>
 *            No remark.
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
 *            <TD> 04-10-2008 </TD>
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
public class PublishCommand extends FPGPublisherSPICommand
		implements DocTypeConstants {

	/**
	 * Constructs a <b>publish SPI</b> command associated to the
	 * <code>shell</code>.
	 * 
	 * @param shell Associated <code>shell</code> instance.
	 */
	public PublishCommand(FPGIntegratedPDFPublisherSPIShell shell) {
		super(shell);
	}

	/**
	 * {@inheritDoc}
	 */
	public void help(PrintStream err) {
		err.println(
				  "publish [-verbose] <docType> <strategy> <indexFileName> "
				+ "[<remark>]  -- <strategy>: insert | update");
	}

	/**
	 * {@inheritDoc}
	 */
	public void execute(String[] args, InputStream in,
			PrintStream out, PrintStream err) {

		if (args.length < 3 || args.length > 5) {
			help(err);

			return;
		}

		boolean verbose = args[0].equals("-verbose");
		int index = (verbose) ? 1 : 0;

		if (verbose && args.length == 3) {
			help(err);

			return;
		}

		String docTypeName = args[index++];
		String sStrategy = args[index++];
		String indexFileName = args[index++];
		String remark = (index < args.length) ? args[index] : null;

		int strategy = PublishOptions.getStrategy(sStrategy);

		if (strategy == -1) {
			help(err);

			return;
		}

		PDFPublisher pdfPublisher = null;

		try {
			Publisher publisher = shell.getDefaultProvider();
			PublishOptions publishOptions = new PublishOptions();

			publishOptions.setPublisher(publisher);
			publishOptions.setBatchMode(true);
			publishOptions.setPartitioned(false);
			publishOptions.setCompressed(true);
			publishOptions.setReplaceMode(PublishOptions.MODE_REPLACE);
			publishOptions.setStrategy(strategy);
			publishOptions.setLogged(true);
			publishOptions.setLogID("load");
			publishOptions.setRemark(remark);

			pdfPublisher =
					PDFPublisher.newPDFPublisher(
							docTypeName, 0, publishOptions);

			publish(pdfPublisher, out, err, indexFileName, verbose);
		} catch (Exception e) {
			e.printStackTrace(err);

			err.println(
					"Error: " + e.getMessage() + " " + Exceptions.getCauses(e));
		} finally {
			if (pdfPublisher != null) {
				try {
					pdfPublisher.close();
				} catch (PDFPublisherException e) {}
			}
		}
	}

	private int publish(PDFPublisher publisher, PrintStream out,
			PrintStream err, String indexFileName, boolean verbose)
			throws PDFPublisherException {

		BufferedReader reader = null;
		int nLoadedDocuments = 0;
		boolean publishing = false;
		boolean commit = false;
		long ti = System.currentTimeMillis();

		try {
			reader = new BufferedReader(new FileReader(indexFileName));

			String docFileName;
			String docIndex;

			publisher.reset();

			publishing = true;

			if (verbose) {
				out.println(
						"Loading from index file '" + indexFileName + "' ...");

				out.println();
			}

			do {
				if ((docFileName = reader.readLine()) == null) {
					break;
				}

				if ((docIndex = reader.readLine()) == null) {
					throw new PDFPublisherException("Unexpected EOF.");
				}

				if (verbose) {
					out.println("Loading " + docFileName + " -> " + docIndex);
				}

				publisher.publish(docIndex, docFileName);

				nLoadedDocuments++;
			} while (true);

			commit = true;
		} catch (IOException e) {
			throw new PDFPublisherException("Load was aborted", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {}
			}

			if (publishing) {
				if (commit) {
					publisher.flush();
				} else {
					publisher.discard();
				}
			}
		}

		if (commit && verbose) {
			long tf = System.currentTimeMillis();
			long dt = tf - ti;
			String rate = "0.00";

			if (nLoadedDocuments > 0) {
				rate = Padder.toString(nLoadedDocuments / (dt / 1000.0), 2);
			}

			out.println();
			out.println(
					  nLoadedDocuments + " documents loaded in "
					+ dt + " ms. (" + rate + " docs/s)");
		}

		return nLoadedDocuments;
	}
}
