

/*
 * @(#) FPGPublisherSPICommand.java    1.0 16-07-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.implementations.fpg.command;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;

import cl.araucana.pdf.publishers.PDFPublisherException;

import cl.araucana.pdf.publishers.implementations.fpg.*;


/**
 * This is an abstract base class for all shell commands to be implemented.
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
 *            <TD> 16-07-2008 </TD>
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
public abstract class FPGPublisherSPICommand implements DocTypeConstants {

	/**
	 * Associated <b>shell</b> instance.
	 */
	protected FPGIntegratedPDFPublisherSPIShell shell;

	/**
	 * Constructs a <b>SPI</b> command associated to the <code>shell</code>.
	 * 
	 * @param shell Associated <code>shell</code> instance.
	 */
	public FPGPublisherSPICommand(FPGIntegratedPDFPublisherSPIShell shell) {
		this.shell = shell;
	}

	/**
	 * Gets associated <b>spi</b> instance.
	 * 
	 * @return Associated <b>spi</b> instance.
	 * 
	 * @throws PDFPublisherException If cannot get associated <b>spi</b>
	 *         instance.
	 */
	public FPGIntegratedPDFPublisherSPI getSPI() throws PDFPublisherException {
		return shell.getSPI();
	}

	/**
	 * Displays usage help to this command to the specified error output.
	 * 
	 * @param err Command error output.
	 */
	public abstract void help(PrintStream err);

	/**
	 * Executes this command with the specified <code>args</code> arguments
	 * and using the specified input and outputs.
	 * 
	 * @param args Command arguments.
	 * 
	 * @param in Command normal input.
	 * 
	 * @param out Command normal output.
	 * 
	 * @param err Command error output.
	 */
	public abstract void execute(String[] args, InputStream in,
			PrintStream out, PrintStream err);

	/**
	 * Utility method to commands requiring prompt a question to the user,
	 * for example, a previous confirmation to proceed.
	 * 
	 * @param question Prompt question.
	 * 
	 * @param in Prompt input.
	 * 
	 * @param out Prompt output.
	 * 
	 * @return user answer.
	 * 
	 * @throws IOException If an I/O error occurs. 
	 */
	public String promptQuestion(String question, InputStream in,
			PrintStream out) throws IOException {

		out.print(question);

		BufferedReader reader =	new BufferedReader(new InputStreamReader(in));

		return reader.readLine();
	}
}
