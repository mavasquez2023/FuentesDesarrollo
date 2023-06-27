

/*
 * @(#) ImplementationsPDFServiceCommand.java    1.0 18-07-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice.command;


import java.io.InputStream;
import java.io.PrintStream;

import cl.araucana.pdfservice.DocumentType;
import cl.araucana.pdfservice.PDFService;


/**
 * Reports all available document type implementations.
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
 *        <td><b>implementations</b></td>
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
 *            <TD> 18-07-2008 </TD>
 *            <TD align="center">  1.0 </TD>
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
public class ImplementationsPDFServiceCommand extends PDFServiceCommand {

	/**
	 * Constructs a <b>Implementations PDFService</b> command associated to the
	 * <code>service</code>.
	 * 
	 * @param service Associated <code>service</code> instance.
	 */
	public ImplementationsPDFServiceCommand(PDFService service) {
		super(service);
	}

	public void help(PrintStream err) {
		err.println("implementations");
	}

	public void execute(String[] args, InputStream in, PrintStream out,
			PrintStream err) {

		if (args.length != 0) {
			help(err);

			return;
		}

		DocumentType[] aDocTypes = service.getDocTypes();
		String[] implementations = new String[aDocTypes.length];
		int nImplementations = 0;

		for (int i = 0; i < aDocTypes.length; i++) {
			DocumentType docType = aDocTypes[i];
			String docTypeImplementation = docType.getImplementation();
			boolean repeated = false;

			for (int j = 0; j < nImplementations; j++) {
				if (implementations[j].equals(docTypeImplementation)) {
					repeated = true;

					break;
				}
			}

			if (!repeated) {
				implementations[nImplementations++] = docTypeImplementation;
			}
		}

		for (int i = 0; i < nImplementations; i++) {
			out.println(implementations[i]);
		}
	}
}
