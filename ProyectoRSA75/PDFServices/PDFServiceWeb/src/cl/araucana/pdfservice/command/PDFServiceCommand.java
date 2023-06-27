

/*
 * @(#) PDFServiceCommand.java    1.0 24-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice.command;


import java.io.InputStream;
import java.io.PrintStream;

import cl.araucana.pdfservice.PDFService;


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
 *            <TD> 24-04-2008 </TD>
 *            <TD align="center">  2.0 </TD>
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
public abstract class PDFServiceCommand {

	/**
	 * Associated <b>service</b> instance.
	 */	
	protected PDFService service;

	/**
	 * Constructs a shell command instance associated to a <code>service</code>.
	 * 
	 * @param service Associated service.
	 */
	public PDFServiceCommand(PDFService service) {
		this.service = service;
	}

	/**
	 * Gets associated service.
	 * 
	 * @return Associated service.
	 */
	public PDFService getPDFService() {
		return service;
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
	 * Destroys this command releasing allocated resources. Command will be
	 * disassociated from its service shell instance.
	 */
	public void destroy() {
		service = null;
	}
}
