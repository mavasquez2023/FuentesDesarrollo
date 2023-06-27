

/*
 * @(#) UnpublishCommand.java    1.0 23-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.implementations.fpg.command;


import java.io.InputStream;
import java.io.IOException;
import java.io.PrintStream;

import cl.araucana.core.util.Exceptions;

import cl.araucana.pdf.publishers.PDFPublisher;
import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.Publisher;
import cl.araucana.pdf.publishers.PublishOptions;
import cl.araucana.pdf.publishers.Scope;

import cl.araucana.pdf.publishers.implementations.fpg.*;


/**
 * Unpublishes published PDF document instances of a document type.
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
 *            <b>unpublish [-verbose] [-v &lt;docVersion&gt;] [-y]
 *               &lt;docType&gt; [&lt;scope&gt;]</b>
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
 *            Number of unpublished PDF documents.
 *        </td>
 *        
 *        <td>
 *            No verbose.
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
 *            Any document version is considered in the unpublication.
 *        </td>
 *     </tr>
 *       
 *     <tr>
 *        <td><strong>y</strong></td>
 *        
 *        <td>
 *            Assumes <b>yes</b> to proceed with unpublication when scope is
 *            omitted.
 *        </td>
 *        
 *        <td>
 *            Prompt confirmation to the user.
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
 *            Required documents scope.
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
 *            <TD> 23-10-2008 </TD>
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
public class UnpublishCommand extends FPGPublisherSPICommand
		implements DocTypeConstants {

	/**
	 * Constructs a <b>unpublish SPI</b> command associated to the
	 * <code>shell</code>.
	 * 
	 * @param shell Associated <code>shell</code> instance.
	 */	
	public UnpublishCommand(FPGIntegratedPDFPublisherSPIShell shell) {
		super(shell);
	}

	/**
	 * {@inheritDoc}
	 */
	public void help(PrintStream err) {
		err.println(
				  "unpublish [-verbose] [-v <docVersion>] [-y] "
				+ "<docType> [<scope>]");
	}

	/**
	 * {@inheritDoc}
	 */
	public void execute(String[] args, InputStream in,
			PrintStream out, PrintStream err) {

		int docVersion = 0;
		String docTypeName = null;
		Scope scope = null;
		boolean verbose = false;
		boolean sure = false;
		boolean strict = false;
		boolean optionable = true;

		for (int i = 0; i < args.length; i++) {
			if (!optionable && args.length > 0 && args[i].charAt(0) == '-') {
				help(err);

				return;
			} else if (args[i].equals("-verbose")) {
				verbose = true;
			} else if (args[i].equals("-y")) {
				sure = true;
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
			} else {
				optionable = false;

				if (docTypeName == null) {
					docTypeName = args[i];
				} else if (scope == null) {
					if (!args[i].trim().equals("")) {
						scope = new Scope(args[i]);
					}
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

		if (scope == Scope.ALL && !sure) {
			String question =
					  "Do you really want unpublish all documents "
					+ "'" + docTypeName + "' [n]?";

			String answer = null;

			try {
				answer = promptQuestion(question, in, out);
			} catch (IOException e) {}

			if (answer == null || !answer.equals("y")) {
				out.println("Operation was cancelled by user.");

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
			publishOptions.setLogID("unpublish");

			pdfPublisher =
					PDFPublisher.newPDFPublisher(
							docTypeName, docVersion, publishOptions);

			int nUnpublishedDocuments =
					pdfPublisher.unpublish(scope, strict);

			if (verbose) {
				out.println(
						  "#unpublished documents="
						+ nUnpublishedDocuments + ".");
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
		}
	}
}
