

/*
 * @(#) AbortPDFServiceCommand.java    1.0 25-04-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.pdfservice.command;


import java.io.InputStream;
import java.io.PrintStream;

import cl.araucana.pdfservice.PDFService;
import cl.araucana.pdfservice.PDFProcess;


/**
 * Aborts one or more PDF processes.
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
 *        <td><b>abort -all | &lt;pid&gt; ...</b></td>
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
 *        <td><strong>all</strong></td>
 *        
 *        <td>
 *            Aborts all PDF processes not finished.
 *        </td>
 *        
 *        <td>
 *            &nbsp;
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
 *        <td><strong>pid</strong></td>
 *        
 *        <td>
 *            Process ID to the process to be aborted. It can be specified
 *            zero or more times.
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
 *            <TD align="center"> <B>Versi�n</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripci�n</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 25-04-2008 </TD>
 *            <TD align="center">  2.0 </TD>
 *            <TD> Germ�n Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versi�n inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Germ�n Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class AbortPDFServiceCommand extends PDFServiceCommand {

	/**
	 * Constructs a <b>Abort PDFService</b> command associated to the
	 * <code>service</code>.
	 * 
	 * @param service Associated <code>service</code> instance.
	 */
	public AbortPDFServiceCommand(PDFService service) {
		super(service);
	}

	public void help(PrintStream err) {
		err.println("abort -all | <pid> ...");
	}

	public void execute(String[] args, InputStream in, PrintStream out,
			PrintStream err) {

		if (args.length == 0) {
			help(err);

			return;
		}

		if (args[0].equals("-all")) {
			if (args.length != 1) {
				help(err);

				return;
			}

			PDFProcess.Info[] infos = service.getAllProcessesInfo();

			for (int i = 0; i < infos.length; i++) {
				PDFProcess.Info info = infos[i];

				if (!info.isEnded()) {
					service.abort(infos[i].pid, "aborted by user request");
				}
			}

			return;
		}

		for (int i = 0; i < args.length; i++) {
			int pid;

			try {
				pid = Integer.parseInt(args[i]);
			} catch(NumberFormatException e) {
				err.println("Invalid process id '" + args[i] + "'.");

				continue;
			}

			PDFProcess.Info info = service.getInfo(pid);

			if (info == null) {
				err.println("Invalid process " + pid + ".");
			} else if (!info.isEnded()) {
				service.abort(info.pid, "aborted by user request");
			}
		}
	}
}
