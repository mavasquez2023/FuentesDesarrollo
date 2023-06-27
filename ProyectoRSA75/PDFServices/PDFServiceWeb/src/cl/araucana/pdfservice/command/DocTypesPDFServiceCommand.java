

/*
 * @(#) DocTypesPDFServiceCommand.java    1.0 24-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice.command;


import java.io.InputStream;
import java.io.PrintStream;

import cl.araucana.core.util.Padder;

import cl.araucana.pdfservice.DocumentType;
import cl.araucana.pdfservice.PDFService;


/**
 * Reports available document types to produce/consume.
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
 *        <td> <b>doctypes [-l] [-i &lt;implementation&gt;]
 *                         [&lt;doctype&gt; ...]</b>
 *        </td>
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
 *        <td><strong>l</strong></td>
 *        
 *        <td>
 *            Detailed report format is used.
 *        </td>
 *        
 *        <td>
 *            Only document type names are reported.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>i</strong></td>
 *        
 *        <td>
 *            Only the specified implementation is considered.
 *        </td>
 *        
 *        <td>
 *            All implementations are considered.
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
 *            Document type. It can be specified zero or more times.
 *        </td>
 *        
 *        <td>
 *            All document types are considered.
 *        </td>
 *     </tr>
 * </TABLE>
 *
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
public class DocTypesPDFServiceCommand extends PDFServiceCommand {

	private static final int DOCTYPE_NAME_LENGTH = 25;
	private static final int DOCTYPE_IMPLEMENTATION_LENGTH = 25;

	/**
	 * Constructs a <b>DocTypes PDFService</b> command associated to the
	 * <code>service</code>.
	 * 
	 * @param service Associated <code>service</code> instance.
	 */
	public DocTypesPDFServiceCommand(PDFService service) {
		super(service);
	}

	public void help(PrintStream err) {
		err.println("doctypes [-l] [-i <implementation>] [<doctype> ...]");
	}

	public void execute(String[] args, InputStream in, PrintStream out,
			PrintStream err) {

		boolean longReport = false;
		String implementation = null;
		int doctypeIndex = -1;

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-l")) {
				longReport = true;
			} else if (args[i].equals("-i")) {
				if (i + 1 == args.length) {
					help(err);

					return;
				}

				implementation = args[++i];
			} else if (doctypeIndex == -1) {
				doctypeIndex = i;
			}
		}

		int nDocTypes = (doctypeIndex == -1) ? 0 : args.length - doctypeIndex;

		if (nDocTypes > 0) {
			if (!longReport) {
				out.println(
						  Padder.rpad("Name", DOCTYPE_NAME_LENGTH, ' ')
						+ "Implementation           "
						+ "Description");

				out.println(
						  Padder.rpad("", DOCTYPE_NAME_LENGTH, '-')
						+ "----------------------------------------------------"
						+ "--------------------------------------------------");
			}

			for (int i = doctypeIndex; i < args.length; i++) {
				DocumentType docType = service.getDocumentType(args[i]);

				if (docType != null) {
					String docTypeImplementation = docType.getImplementation();

					/*
					 * Filters document types by implementation.
					 */
					if (implementation != null
							&& !docTypeImplementation.equals(implementation)) {

						continue;
					}

					if (longReport) {
						out.println(docType);
					} else {
						out.println(
								  Padder.rpad(docType.getName(),
								  		DOCTYPE_NAME_LENGTH, ' ')
								+ Padder.rpad(docType.getImplementation(),
								  		DOCTYPE_IMPLEMENTATION_LENGTH, ' ')
								+ docType.getDescription());
					}
				} else {
					err.println("Unknown document type '" + args[i] +"'.");
				}
			}

			return;
		}

		DocumentType[] aDocTypes = service.getDocTypes();

		if (!longReport && aDocTypes.length > 0) {
			out.println(
					  Padder.rpad("Name", DOCTYPE_NAME_LENGTH, ' ')
					+ "Implementation           "
					+ "Description");

			out.println(
					  Padder.rpad("", DOCTYPE_NAME_LENGTH, '-')
					+ "---------------------------------------------------"
					+ "--------------------------------------------------");
		}

		for (int i = 0; i < aDocTypes.length; i++) {
			DocumentType docType = aDocTypes[i];
			String docTypeImplementation = docType.getImplementation();

			/*
			 * Filters document types by implementation.
			 */
			if (implementation != null
					&& !docTypeImplementation.equals(implementation)) {

				continue;
			}

			if (longReport) {
				out.println(docType);
			} else {
				out.println(
						  Padder.rpad(docType.getName(),
								DOCTYPE_NAME_LENGTH, ' ')
						+ Padder.rpad(docType.getImplementation(),
								DOCTYPE_IMPLEMENTATION_LENGTH, ' ')
						+ docType.getDescription());
			}
		}
	}
}
