

/*
 * @(#) PublicationsCommand.java    1.0 23-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.implementations.fpg.command;


import java.io.InputStream;
import java.io.PrintStream;

import cl.araucana.core.util.Exceptions;
import cl.araucana.core.util.Padder;

import cl.araucana.pdf.publishers.PDFPublisherException;

import cl.araucana.pdf.publishers.implementations.fpg.*;


/**
 * Reports registered publications of a document type.
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
 *            <b>publications [-o=[-]{ v | p | s | P}] [-O]
 *                            [-m &lt;maxHits&gt;] &lt;docType&gt;</b>
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
 *        <td><strong>o</strong></td>
 *        
 *        <td>
 *            Orders publications specifying <u>one only</u> of the
 *            following criteria:
 *            
 *            <BR>
 *            <BR>
 *            
 *            <TABLE ALIGN="CENTER" BORDER="1" WIDTH="80%"
 *                   CELLPADDING="3" CELLSPACING="0">
 *               <tr>
 *                   <th bgcolor="black">
 *                       <font color="white"><strong>Criteria</strong></font>
 *                   </th>
 *        
 *                   <th bgcolor="black">
 *                      <font color="white"><strong>Description</strong></font>
 *                   </th>
 *               </tr>
 *               
 *               <tr>
 *                    <td>v</td>
 *                    <td>Document version</td>
 *               </tr>
 *               
 *               <tr>
 *                    <td>p</td>
 *                    <td>Publication</td>
 *               </tr>
 *               
 *               <tr>
 *                    <td>s</td>
 *                    <td>Source system</td>
 *               </tr>
 *               
 *               <tr>
 *                    <td>P</td>
 *                    <td>Publisher agent</td>
 *               </tr>
 *            </TABLE>
 *            
 *            <BR>
 *            <BR>
 *            Order is ascendent by default. If descendent one is required
 *            then prefixes order criteria with '<b>-</b>' sign.
 *        </td>
 *        
 *        <td>
 *            Publication.
 *        </td>
 *     </tr>
 *     <tr>
 *        <td><strong>O</strong></td>
 *        
 *        <td>
 *            Reports <b>opened</b> publications only.
 *        </td>
 *        
 *        <td>
 *            &nbsp;
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
public class PublicationsCommand extends FPGPublisherSPICommand
		implements DocTypeConstants {

	/**
	 * Constructs a <b>publications SPI</b> command associated to the
	 * <code>shell</code>.
	 * 
	 * @param shell Associated <code>shell</code> instance.
	 */	
	public PublicationsCommand(FPGIntegratedPDFPublisherSPIShell shell) {
		super(shell);
	}

	/**
	 * {@inheritDoc}
	 */
	public void help(PrintStream err) {
		err.println(
				  "publications [-o=[-]{ v | p | s | P}] [-O] "
				+ "[-m <maxHits>] <docType>");
	}

	/**
	 * {@inheritDoc}
	 */
	public void execute(String[] args, InputStream in,
			PrintStream out, PrintStream err) {

		String docTypeName = null;
		int criteria = FPGIntegratedPDFPublisherSPI.CRITERIA_PUBLICATION;
		boolean descendent = false;
		boolean openedOnly = false;
		int maxHits = FPGIntegratedPDFPublisher.MAX_MAX_HITS;

		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("-o=")) {
				if (args[i].length() != 4 && args[i].length() != 5) {
					help(err);

					return;
				}

				int index = 3;

				if (args[i].charAt(index) == '-') {
					descendent = true;

					index = 4;
				}

				criteria = "vpsP".indexOf(args[i].charAt(index));

				if (criteria == -1) {
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
			} else if (args[i].equals("-O")) {
				openedOnly = true;
			} else if (!args[i].startsWith("-")) {
				docTypeName = args[i];
			} else {
				help(err);

				return;
			}
		}

		if (docTypeName == null) {
			help(err);

			return;
		}

		FPGIntegratedPDFPublisherSPI spi = null;

		try {
			spi = shell.getSPI();
		} catch (PDFPublisherException e) {
			err.println(e.getMessage());

			return;
		}

		reportPublications(
				spi,
				out,
				err,
				docTypeName,
				criteria,
				descendent,
				maxHits,
				openedOnly);
	}

	private void reportPublications(FPGIntegratedPDFPublisherSPI spi,
			PrintStream out, PrintStream err, String docTypeName,
			int criteria, boolean descendent, int maxHits,
			boolean openedOnly) {

		try {
			Publication[] publications =
					spi.getPublications(
							docTypeName, criteria, descendent, maxHits);

			out.println(
					  Padder.rpad("Publication", 11)
					+ " "
					+ Padder.rpad("System", 14)
					+ " "
					+ Padder.rpad("Publisher", 14)
					+ " "
					+ "Creation Date Time "
					+ " "
					+ Padder.lpad("Version", 7)
					+ " "
					+ Padder.lpad("#Docs", 10)
					+ " "
					+ Padder.rpad("Status", 10)
					+ " "
					+ "Remark");

			out.println(Padder.rpad("", 132, '-'));

			for (int i = 0; i < publications.length; i++) {
				Publication publication = publications[i];

				if (openedOnly && !publication.isOpened()) {
					continue;
				}

				String remark = publication.getRemark();

				if (remark == null) {
					remark = "";
				}

				String status =
						(publication.isOpened()) ? "OPENED ***" : "CLOSED";

				out.println(
						  Padder.rpad(publication.getID(), 11)
						+ " "
						+ Padder.rpad(publication.getSystemName(), 14)
						+ " "
						+ Padder.rpad(publication.getPublisherName(), 14)
						+ " "
						+ publication.getDateTime()
						+ " "
						+ Padder.lpad(
								Padder.padSeparators(
										publication.getVersion()), 7)
						+ " "
						+ Padder.lpad(
								Padder.padSeparators(
										publication.getNDocuments()), 10)
						+ " "
						+ Padder.rpad(status, 10)
						+ " "
						+ remark);
			}
		} catch (PDFPublisherException e) {
			err.println(
					"Error: " + e.getMessage() + " " + Exceptions.getCauses(e));
		}
	}
}
