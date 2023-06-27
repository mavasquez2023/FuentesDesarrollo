

/*
 * @(#) PublishersCommand.java    1.0 02-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.implementations.fpg.command;


import java.io.InputStream;
import java.io.PrintStream;

import cl.araucana.core.util.Padder;

import cl.araucana.pdf.publishers.PDFPublisherException;

import cl.araucana.pdf.publishers.implementations.fpg.*;


/**
 * Lists all registered publisher agents. Additionally it permits to
 * set a publisher agent as default one to be used in publication operations.
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
 *        <td><b>publishers [-set &lt;name&gt;]</b></td>
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
 *        <td><strong>set</strong></td>
 *        
 *        <td>
 *            Set default publisher agent to publication operations. Publisher 
 *            agent name must be specified.
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
public class PublishersCommand extends FPGPublisherSPICommand
		implements DocTypeConstants {

	private static final int CMD_SET_PUBLISHER     = 0;
	private static final int CMD_REPORT_PUBLISHERS = 1;

	/**
	 * Constructs a <b>publishers SPI</b> command associated to the
	 * <code>shell</code>.
	 * 
	 * @param shell Associated <code>shell</code> instance.
	 */
	public PublishersCommand(FPGIntegratedPDFPublisherSPIShell shell) {
		super(shell);
	}

	/**
	 * {@inheritDoc}
	 */
	public void help(PrintStream err) {
		err.println("publishers [-set <name>]");
	}

	/**
	 * {@inheritDoc}
	 */
	public void execute(String[] args, InputStream in,
			PrintStream out, PrintStream err) {

			int cmd = CMD_REPORT_PUBLISHERS;
			String name = null;

			if (args.length > 0) {
				if (args[0].equals("-set")) {
					if (args.length != 2) {
						help(err);

						return;
					}
				} else {
					help(err);

					return;
				}

				cmd = CMD_SET_PUBLISHER;
				name = args[1];
			}

			FPGIntegratedPDFPublisherSPI spi = null;

			try {
				spi = shell.getSPI();
			} catch (PDFPublisherException e) {
				err.println(e.getMessage());

				return;
			}

			if (cmd == CMD_SET_PUBLISHER) {
				setPublisher(spi, out, err, name);
			} else {
				reportPublishers(spi, out, err);
		}
	}

	private void setPublisher(FPGIntegratedPDFPublisherSPI spi, PrintStream out,
			PrintStream err, String name) {

		try {
			spi.setPublisherName(name);

			out.println("Current publisher is '" + name + "'.");
		} catch (PDFPublisherException e) {
			err.println("Publisher '" + name + "' cannot be setted.");
		}
	}

	private void reportPublishers(FPGIntegratedPDFPublisherSPI spi,
			PrintStream out, PrintStream err) {

		try {
			String publisherName = spi.getPublisherName();
			String[][] publishers = spi.getPublishers();

			out.println(
					  " "
					+ Padder.rpad("ID", PUB_ID_MAX_LENGTH, ' ')
					+ " "
					+ Padder.rpad("Name", PUB_NAME_MAX_LENGTH, ' '));

			out.println(
					  " "
					+ Padder.rpad(
							"",
							  PUB_ID_MAX_LENGTH
							+ PUB_NAME_MAX_LENGTH
							+ 1,
							'-'));

			for (int i = 0; i < publishers.length; i++) {
				String id = publishers[i][0];
				String name = publishers[i][1];
				String mark =
						(publisherName.equals(name))
								? "*"
								: " ";

				out.println(
						  mark
						+ Padder.rpad(id, PUB_ID_MAX_LENGTH, ' ')
						+ " "
						+ Padder.rpad(name, PUB_NAME_MAX_LENGTH, ' '));
			}
		} catch (PDFPublisherException e) {
			err.println("Cannot get publishers.");
		}
	}
}
