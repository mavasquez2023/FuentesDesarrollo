

/*
 * @(#) SaveCommand.java    1.0 13-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.implementations.fpg.command;


import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import java.text.DecimalFormat;

import cl.araucana.core.util.Exceptions;
import cl.araucana.core.util.Padder;

import cl.araucana.pdf.publishers.PDFPublisher;
import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.PublishedDocumentList;
import cl.araucana.pdf.publishers.Publisher;
import cl.araucana.pdf.publishers.PublishOptions;
import cl.araucana.pdf.publishers.Scope;

import cl.araucana.pdf.publishers.implementations.fpg.*;


import cl.araucana.pdf.publishers.util.DirectoryPDFDocumentSaver;
import cl.araucana.pdf.publishers.util.PDFDocumentNameGenerator;
import cl.araucana.pdf.publishers.util.PDFDocumentSaver;
import cl.araucana.pdf.publishers.util.ZippedPDFDocumentSaver;


/**
 * Retrieves and saves published PDF document instances of a document type.
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
 *            <b>save [-verbose] -o &lt;outputDirName&gt;
 *                    [-z &lt;zipFileName&gt;
 *                    [-M]] [-m &lt;maxHits&gt;] [-v &lt;docVersion&gt;]
 *                    -p &lt;pattern&gt; &lt;docType&gt; [&lt;scope&gt;]</b>
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
 *            Output directory to save retrieved PDF documents. It's mandatory.
 *        </td>
 *        
 *        <td>
 *            &nbsp;
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>z</strong></td>
 *        
 *        <td>
 *            Output zip file pathname to save retrieved PDF documents
 *            compressed. When this option is used the output directory
 *            specified with <b>-o</b> will be the prefix to the compressed
 *            PDF document pathnames.
 *        </td>
 *        
 *        <td>
 *            &nbsp;
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>M</strong></td>
 *        
 *        <td>
 *            Zip compression will be in <u>memory</u> and then save to disk to
 *            obtain best performance. Note that if compressed output is very
 *            large then JVM can need customized heap memory settings to work
 *            fine. This option can be used only if <b>-z</b> one was specified.
 *        </td>
 *        
 *        <td>
 *            Zip compression will be to disk using a minimal buffer.
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
 *        <td><strong>p</strong></td>
 *        
 *        <td>
 *            Specifies the retrieved PDF documents pathname
 *            {@link cl.araucana.pdf.publishers.util.PDFDocumentNameGenerator
 *            pattern}. This pattern must be defined in function of the
 *            document type field names.          
 *        <td>
 *             &nbsp;
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
 *        <td>
 *            <a href="{@docRoot}/extras/scope.html">scope</a>
 *        </td>
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
 *            <TD> 13-10-2008 </TD>
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
public class SaveCommand extends FPGPublisherSPICommand
		implements DocTypeConstants {

	private static final int INITIAL_ZIP_SIZE = 10485760;	// 10 MB.

	/**
	 * Constructs a <b>save SPI</b> command associated to the
	 * <code>shell</code>.
	 * 
	 * @param shell Associated <code>shell</code> instance.
	 */	
	public SaveCommand(FPGIntegratedPDFPublisherSPIShell shell) {
		super(shell);
	}

	/**
	 * {@inheritDoc}
	 */
	public void help(PrintStream err) {
		err.println(
				  "save [-verbose] -o <outputDirName> [-z <zipFileName> [-M]] "
				+ "[-m <maxHits>] [-v <docVersion>] -p <pattern> "
				+ "<docType> [<scope>]");
	}

	/**
	 * {@inheritDoc}
	 */
	public void execute(String[] args, InputStream in,
			PrintStream out, PrintStream err) {

		String outputDirName = null;
		String zipFileName = null;
		boolean zippedInMemory = false;
		int maxHits = FPGIntegratedPDFPublisher.MAX_MAX_HITS;
		int docVersion = 0;
		String pattern = null;
		String docTypeName = null;
		Scope scope = null;
		boolean verbose = false;

		boolean strict = false;
		boolean optionable = true;

		for (int i = 0; i < args.length; i++) {
			if (!optionable && args.length > 0 && args[i].charAt(0) == '-') {
				help(err);

				return;
			} else if (args[i].equals("-verbose")) {
				verbose = true;
			} else if (args[i].equals("-o")) {
				if (zipFileName != null) {
					help(err);

					return;
				}

				if (i + 1 < args.length) {
					outputDirName = args[++i];
				} else {
					help(err);

					return;
				}
			} else if (args[i].equals("-z")) {
				if (outputDirName == null) {
					help(err);

					return;
				}

				if (i + 1 < args.length) {
					zipFileName = args[++i];
				} else {
					help(err);

					return;
				}
			} else if (args[i].equals("-M")) {
				if (zipFileName == null) {
					help(err);

					return;
				}

				zippedInMemory = true;
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
			} else if (args[i].equals("-p")) {
				if (i + 1 < args.length) {
					pattern = args[++i];
				} else {
					help(err);

					return;
				}
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

		/*
		 * Checks outputDirName.
		 */
		if (outputDirName == null) {
			help(err);

			return;
		}

		/*
		 * Checks mandatories docTypeName and pattern args.
		 */
		if (docTypeName == null || pattern == null) {
			help(err);

			return;
		}

		if (scope == null) {
			scope = Scope.ALL;
		}

		PDFPublisher pdfPublisher = null;
		OutputStream zippedOutput = null;

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

			PublishedDocumentList publishedDocumentList =
					pdfPublisher.getDocuments(scope, strict, maxHits);

			String[] fieldNames = pdfPublisher.getDocIndexFieldNames();

			PDFDocumentNameGenerator generator =
					new PDFDocumentNameGenerator(fieldNames, pattern);

			PDFDocumentSaver saver;

			if (zipFileName == null) {
				if (verbose) {
					out.println();

					out.println(
							  "Saving PDF documents in directory "
							+ "'" + outputDirName + "' ...");
				}

				saver =
						new DirectoryPDFDocumentSaver(
								publishedDocumentList,
								generator,
								outputDirName);
			} else {
				if (verbose) {
					out.println();

					out.println(
							  "Zipping PDF documents to "
							+ "'" + zipFileName + "' ...");
				}

				if (zippedInMemory) {
					zippedOutput = new ByteArrayOutputStream(INITIAL_ZIP_SIZE);
				} else {
					zippedOutput = new FileOutputStream(zipFileName);
				}

				saver =
						new ZippedPDFDocumentSaver(
								publishedDocumentList,
								generator,
								zippedOutput,
								outputDirName);
			}

			saver.save();
			saver.close();

			DecimalFormat df = new DecimalFormat("#.00");

			String sNDocuments =
					Padder.padSeparators((int) saver.getSavedDocumentCount());

			String sSavedSize =
					Padder.padSeparators((int) saver.getSavedSize());

			String sElapsedTime =
					Padder.padSeparators((int) saver.elapsedTime());

			String sRate = df.format(saver.saveRate());

			if (zipFileName != null) {
				ZippedPDFDocumentSaver zippedSaver =
						(ZippedPDFDocumentSaver) saver;

				if (zippedInMemory) {
					ByteArrayOutputStream binOutput =
							(ByteArrayOutputStream) zippedOutput;

					binOutput.close();

					zippedOutput = new FileOutputStream(zipFileName);

					binOutput.writeTo(zippedOutput);
				}

				String sCompression =
						df.format(zippedSaver.compressionPercent());

				String sSavedZippedSize =
						Padder.padSeparators(
								(int) zippedSaver.getSavedZippedSize());

				if (verbose) {
					out.println(
							  "#saved documents=" + sNDocuments + ", "
							+ "saved size=" + sSavedSize + " bytes, "
							+ "zipped size=" + sSavedZippedSize + " bytes, "
							+ "time=" + sElapsedTime + " ms, "
							+ "rate=" + sRate + " docs/s, "
							+ "compression=" + sCompression + "%.");
				}
			} else {
				if (verbose) {
					out.println(
							  "#saved documents=" + sNDocuments + ", "
							+ "saved size=" + sSavedSize + " bytes, "
							+ "time=" + sElapsedTime + " ms, "
							+ "rate=" + sRate + " docs/s.");
				}
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

			if (zippedOutput != null) {
				try {
					zippedOutput.close();
				} catch (IOException e) {}
			}
		}
	}
}
