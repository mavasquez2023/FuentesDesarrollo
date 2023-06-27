

/*
 * @(#) VersionsCommand.java    1.0 19-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.implementations.fpg.command;


import java.io.InputStream;
import java.io.PrintStream;

import cl.araucana.core.util.Exceptions;
import cl.araucana.core.util.Padder;

import cl.araucana.pdf.publishers.PDFPublisherException;

import cl.araucana.pdf.publishers.implementations.fpg.*;


/**
 * Lists registered versions of a document type.
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
 *        <td><b>versions &lt;docType&gt;</b></td>
 *     </tr>
 * </TABLE>
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
 *            <TD> 19-10-2008 </TD>
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
public class VersionsCommand extends FPGPublisherSPICommand
		implements DocTypeConstants {

	/**
	 * Constructs a <b>versions SPI</b> command associated to the
	 * <code>shell</code>.
	 * 
	 * @param shell Associated <code>shell</code> instance.
	 */	
	public VersionsCommand(FPGIntegratedPDFPublisherSPIShell shell) {
		super(shell);
	}

	/**
	 * {@inheritDoc}
	 */
	public void help(PrintStream err) {
		err.println("versions <docType>");
	}

	/**
	 * {@inheritDoc}
	 */
	public void execute(String[] args, InputStream in,
			PrintStream out, PrintStream err) {

		if (args.length != 1) {
			help(err);

			return;
		}

		FPGIntegratedPDFPublisherSPI spi = null;

		try {
			spi = shell.getSPI();
		} catch (PDFPublisherException e) {
			err.println(">< " + e.getMessage());
			e.printStackTrace(err);

			return;
		}

		reportVersions(spi, out, err, args[0]);
	}

	private void reportVersions(FPGIntegratedPDFPublisherSPI spi,
			PrintStream out, PrintStream err, String docTypeName) {

		try {
			DocVersion[] docVersions = spi.getVersions(docTypeName);

			out.println(
					  Padder.rpad("Version", 7)
					+ " "
					+ Padder.rpad("Publisher", 14)
					+ " "
					+ "Creation Date Time "
					+ " "
					+ Padder.lpad("FOCSize", 12)
					+ " "
					+ Padder.lpad("FMCSize", 12)
					+ " "
					+ "Remark");

			out.println(Padder.rpad("", 109, '-'));

			for (int i = 0; i < docVersions.length; i++) {
				DocVersion docVersion = docVersions[i];

				String remark = docVersion.getRemark();

				if (remark == null) {
					remark = "";
				}

				out.println(
						  Padder.rpad(docVersion.getID(), 7)
						+ " "
						+ Padder.rpad(docVersion.getPublisherName(), 14)
						+ " "
						+ docVersion.getDateTime()
						+ " "
						+ Padder.lpad(
								Padder.padSeparators(
										docVersion.getFOCSize()), 12)
						+ " "
						+ Padder.lpad(
								Padder.padSeparators(
										docVersion.getFMCSize()), 12)
						+ " "
						+ remark);
			}
		} catch (PDFPublisherException e) {
			err.println(
					"Error: " + e.getMessage() + " " + Exceptions.getCauses(e));
		}
	}
}
