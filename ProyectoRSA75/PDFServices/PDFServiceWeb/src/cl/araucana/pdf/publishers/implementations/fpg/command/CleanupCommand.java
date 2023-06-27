

/*
 * @(#) CleanupCommand.java    1.0 18-11-2008
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

import cl.araucana.pdf.publishers.implementations.fpg.*;


/**
 * Cleanups all empty publications of a document type.
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
 *        <td><b>cleanup [-verbose] [-y] &lt;docType&gt;</b></td>
 *     </tr>
 * </TABLE>
 * 
 * <BR>
 * 
 * <p> The following are supported options:
 * </p>
 * 
 * <BR>
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
 *            Reports number of empty publications that were cleanuped.
 *        </td>
 *        
 *        <td>
 *            No verbose.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>y</strong></td>
 *        
 *        <td>
 *            Assumes <b>yes</b> to proceed with cleanup.
 *        </td>
 *        
 *        <td>
 *            Answer confirmation to the user.
 *        </td>
 *     </tr>
 * </TABLE>
 *
 * <BR>
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
public class CleanupCommand extends FPGPublisherSPICommand
		implements DocTypeConstants {

	/**
	 * Constructs a <b>cleanup SPI</b> command associated to the
	 * <code>shell</code>.
	 * 
	 * @param shell Associated <code>shell</code> instance.
	 */	
	public CleanupCommand(FPGIntegratedPDFPublisherSPIShell shell) {
		super(shell);
	}

	/**
	 * {@inheritDoc}
	 */
	public void help(PrintStream err) {
		err.println("cleanup [-verbose] [-y] <docType>");
	}

	/**
	 * {@inheritDoc}
	 */
	public void execute(String[] args, InputStream in,
			PrintStream out, PrintStream err) {

		String docTypeName = null;
		boolean verbose = false;
		boolean sure = false;

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-verbose")) {
				verbose = true;
			} else if (args[i].equals("-y")) {
				sure = true;
			} else {
				docTypeName = args[i];
			}
		}

		if (docTypeName == null) {
			help(err);

			return;
		}

		if (!sure) {
			String question =
					  "Do you really want cleanup empty publications for "
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

		FPGIntegratedPDFPublisherSPI spi = null;

		try {
			spi = shell.getSPI();
			int nCleanedUpPublications = spi.cleanup(docTypeName);

			if (verbose) {
				out.println(
						  nCleanedUpPublications + " empty publications "
						+ "were deleted for '" + docTypeName + "'.");
			}
		} catch (Exception e) {
			err.println(
					"Error: " + e.getMessage() + " " + Exceptions.getCauses(e));
		}
	}
}
