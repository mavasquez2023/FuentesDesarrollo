

/*
 * @(#) SystemsCommand.java    1.0 02-10-2008
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
 * Lists all registered source systems. Additionally it permits to set a
 * source system as default one to be used in publication operations.
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
 *        <td><b>systems [-set &lt;name&gt;]</b></td>
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
 *            Set default source system to publication operations. Source
 *            system name must be specified.
 *        </td>
 *        
 *        <td>
 *            No verbose.
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
public class SystemsCommand extends FPGPublisherSPICommand
		implements DocTypeConstants {

	private static final int CMD_SET_SYSTEM     = 0;
	private static final int CMD_REPORT_SYSTEMS = 1;

	/**
	 * Constructs a <b>systems SPI</b> command associated to the
	 * <code>shell</code>.
	 * 
	 * @param shell Associated <code>shell</code> instance.
	 */	
	public SystemsCommand(FPGIntegratedPDFPublisherSPIShell shell) {
		super(shell);
	}

	/**
	 * {@inheritDoc}
	 */
	public void help(PrintStream err) {
		err.println("systems [-set <name>]");
	}

	/**
	 * {@inheritDoc}
	 */
	public void execute(String[] args, InputStream in,
			PrintStream out, PrintStream err) {

		int cmd = CMD_REPORT_SYSTEMS;
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

			cmd = CMD_SET_SYSTEM;
			name = args[1];
		}

		FPGIntegratedPDFPublisherSPI spi = null;

		try {
			spi = shell.getSPI();
		} catch (PDFPublisherException e) {
			err.println(e.getMessage());

			return;
		}

		if (cmd == CMD_SET_SYSTEM) {
			setSystem(spi, out, err, name);
		} else {
			reportSystems(spi, out, err);
		}
	}

	private void setSystem(FPGIntegratedPDFPublisherSPI spi, PrintStream out,
			PrintStream err, String name) {

		try {
			spi.setSourceSystemName(name);

			out.println("Current system is '" + name + "'.");
		} catch (PDFPublisherException e) {
			err.println("System '" + name + "' cannot be setted.");
		}
	}

	private void reportSystems(FPGIntegratedPDFPublisherSPI spi,
			PrintStream out, PrintStream err) {

		try {
			int sourceSystemID = spi.getSourceSystemID();
			SourceSystem[] sourceSystems = spi.getSourceSystems();

			out.println(
					  " "
					+ Padder.lpad("ID", SS_ID_MAX_LENGTH, ' ')
					+ " "
					+ Padder.rpad("Name", SS_NAME_MAX_LENGTH, ' ')
					+ " "
					+ Padder.rpad(
					  		"Description", SS_DESCRIPTION_MAX_LENGTH, ' '));

			out.println(
					  " "
					+ Padder.rpad(
							"",
							  SS_ID_MAX_LENGTH
							+ SS_NAME_MAX_LENGTH
							+ SS_DESCRIPTION_MAX_LENGTH
							+ 2,
							'-'));

			for (int i = 0; i < sourceSystems.length; i++) {
				SourceSystem sourceSystem = sourceSystems[i];
				String mark =
						(sourceSystem.getID() == sourceSystemID)
								? "*"
								: " ";

				out.println(
						  mark
						+ Padder.lpad(
							  	sourceSystem.getID(),
							  	SS_ID_MAX_LENGTH, ' ')
						+ " "
						+ Padder.rpad(
							  	sourceSystem.getName(),
							  	SS_NAME_MAX_LENGTH, ' ')
						+ " "
						+ Padder.rpad(
						  		sourceSystem.getDescription(),
						  		SS_DESCRIPTION_MAX_LENGTH, ' '));
			}
		} catch (PDFPublisherException e) {
			err.println("Cannot get systems.");
		}
	}
}
