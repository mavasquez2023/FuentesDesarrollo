

/*
 * @(#) UnregisterCommand.java    1.0 02-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.implementations.fpg.command;


import java.io.InputStream;
import java.io.PrintStream;


import cl.araucana.pdf.publishers.PDFPublisherException;

import cl.araucana.pdf.publishers.implementations.fpg.*;


/**
 * Unregisters a source system, publisher agent or document type.
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
 *            <b>unregister -system &lt;name&gt; | -publisher &lt;name&gt;
 *            | -docType &lt;name&gt;</b>
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
 *        <td><strong>system</strong></td>
 *        
 *        <td>
 *            Indicates a source system to unregister. Its name is mandatory.
 *            <b>SYSTEM</b> source system cannot be unregistered.
 *        </td>
 *        
 *        <td> &nbsp; </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>publisher</strong></td>
 *        
 *        <td>
 *            Indicates a publisher agent to unregister. Its name is
 *            mandatory. <b>SYSTEM</b> publisher agent name cannot be
 *            unregistered.
 *        </td>
 *        
 *        <td> &nbsp; </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>docType</strong></td>
 *        
 *        <td>
 *            Indicates a document type to unregister. Its name is mandatory.
 *        </td>
 *        
 *        <td> &nbsp; </td>
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
 *            <TD> 02-10-2008 </TD>
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
public class UnregisterCommand extends FPGPublisherSPICommand
		implements DocTypeConstants {

	/**
	 * Constructs a <b>unregister SPI</b> command associated to the
	 * <code>shell</code>.
	 * 
	 * @param shell Associated <code>shell</code> instance.
	 */	
	public UnregisterCommand(FPGIntegratedPDFPublisherSPIShell shell) {
		super(shell);
	}

	/**
	 * {@inheritDoc}
	 */
	public void help(PrintStream err) {
		err.print("unregister -system <name> ");
		err.println("| -publisher <name> | -docType <name>");
	}

	/**
	 * {@inheritDoc}
	 */
	public void execute(String[] args, InputStream in,
			PrintStream out, PrintStream err) {

		FPGIntegratedPDFPublisherSPI spi = null;

		try {
			spi = shell.getSPI();
		} catch (PDFPublisherException e) {
			err.println(e.getMessage());

			return;
		}

		if (args.length > 0) {
			if (args[0].equals("-system")) {
				if (args.length != 2) {
					help(err);

					return;
				}

				String name = args[1];

				unregisterSystem(spi, out, err, name);
			} else if (args[0].equals("-publisher")) {
				if (args.length != 2) {
					help(err);

					return;
				}

				String name = args[1];

				unregisterPublisher(spi, out, err, name);
			} else if (args[0].equals("-docType")) {
				if (args.length != 2) {
					help(err);

					return;
				}

				String name = args[1];

				unregisterDocType(spi, out, err, name);
			} else {
				help(err);
			}
		} else {
			help(err);
		}
	}

	private void unregisterSystem(FPGIntegratedPDFPublisherSPI spi,
			PrintStream out, PrintStream err, String name) {

		try {
			spi.unregisterSourceSystem(name);

			out.println("System '" + name + "' was unregistered.");
		} catch (Exception e) {
			err.println(
					  "System '" + name + "' cannot be unregistered. "
					+ "[" + e.getMessage() + "]");
		}
	}

	private void unregisterPublisher(FPGIntegratedPDFPublisherSPI spi,
			PrintStream out, PrintStream err, String name) {

		try {
			spi.unregisterPublisher(name);

			out.println("Publisher '" + name + "' was unregistered.");
		} catch (Exception e) {
			err.println(
					  "Publisher '" + name + "' cannot be unregistered. "
					+ "[" + e.getMessage() + "]");
		}
	}

	private void unregisterDocType(FPGIntegratedPDFPublisherSPI spi,
			PrintStream out, PrintStream err, String name) {

		try {
			spi.unregisterDocumentType(name);

			out.println("Document type '" + name + "' was unregistered.");
		} catch (Exception e) {
			err.println(
					  "Document type '" + name + "' cannot be unregistered. "
					+ "[" + e.getMessage() + "]");
		}
	}
}
