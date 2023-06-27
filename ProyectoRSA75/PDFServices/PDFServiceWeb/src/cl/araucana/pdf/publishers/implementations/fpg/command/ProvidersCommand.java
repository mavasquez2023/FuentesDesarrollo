

/*
 * @(#) ProvidersCommand.java    1.0 11-10-2008
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
import cl.araucana.pdf.publishers.Publisher;

import cl.araucana.pdf.publishers.implementations.fpg.*;


/**
 * Lists all configured <i>FPG Integrated PDF Publisher SPI</i>.
 * Addtionally it permits to set a provider as default one to be used by the
 * remainding commands.
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
 *        <td><b>providers [-set &lt;name&gt;]</b></td>
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
 *            Set default provider to remainding commands. Provider name
 *            must be specified.
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
 *            <TD> 11-10-2008 </TD>
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
public class ProvidersCommand extends FPGPublisherSPICommand {

	private static final int CMD_SET_PROVIDER     = 0;
	private static final int CMD_REPORT_PROVIDERS = 1;

	/**
	 * Constructs a <b>providers SPI</b> command associated to the
	 * <code>shell</code>.
	 * 
	 * @param shell Associated <code>shell</code> instance.
	 */
	public ProvidersCommand(FPGIntegratedPDFPublisherSPIShell shell) {
		super(shell);
	}

	/**
	 * {@inheritDoc}
	 */
	public void help(PrintStream err) {
		err.println("providers [-set <name>]");
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void execute(String[] args, InputStream in,
			PrintStream out, PrintStream err) {

		int cmd = CMD_REPORT_PROVIDERS;
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

			cmd = CMD_SET_PROVIDER;
			name = args[1];
		}

		if (cmd == CMD_SET_PROVIDER) {
			setProvider(out, err, name);
		} else {
			reportProviders(out, err);
		}
	}

	private void setProvider(PrintStream out, PrintStream err,
			String name) {

		try {
			shell.setDefaultProviderName(name);

			out.println("Current provider is '" + name + "'.");
		} catch (PDFPublisherException e) {
			err.println("Default provider '" + name + "' cannot be setted.");
		}
	}

	private void reportProviders(PrintStream out, PrintStream err) {
		String[] providerNames = shell.getProviderNames();
		String defaultProviderName = shell.getDefaultProviderName();

		out.println(
				  " "
				+ Padder.rpad("Name", 25) + "  "
				+ Padder.rpad("Description", 80));

		out.println(
				  " "
				+ Padder.lpad("", 25, '-') + "  "
				+ Padder.lpad("", 80, '-'));

		for (int i = 0; i < providerNames.length; i++) {
			String providerName = providerNames[i];
			Publisher publisher = shell.getProvider(providerName);

			String mark =
					(providerName.equals(defaultProviderName))
							? "*"
							: " ";

			out.println(
					  mark
					+ Padder.rpad(providerName, 25) + "  "
					+ publisher.getDescription());
		}
	}
}
