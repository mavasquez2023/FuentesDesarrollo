

/*
 * @(#) DocTypesCommand.java    1.0 01-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.implementations.fpg.command;


import java.io.InputStream;
import java.io.PrintStream;

import cl.araucana.core.util.AbsoluteDateTime;
import cl.araucana.core.util.Padder;

import cl.araucana.pdf.publishers.PDFPublisherException;

import cl.araucana.pdf.publishers.implementations.fpg.*;


/**
 * Lists registered document type names and its metadata.
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
 *            <b>doctypes [-l | -L] [&lt;docType&gt; ... &lt;docType&gt;]</b>
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
 *        <td><strong>l</strong></td>
 *        
 *        <td>
 *            Lists metadata for specified document types.
 *            (<b>DETAILED FORMAT</b>)
 *        </td>
 *        
 *        <td>
 *            &nbsp;
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>L</strong></td>
 *        
 *        <td>
 *            Lists names and remarks only for specified document types.
 *            (<b>SUMMARIED FORMAT</b>) 
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
 *            Document type to report. Zero or more document types can be
 *            specified.
 *        </td>
 *        
 *        <td>
 *            All registered document types.
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
 *            <TD> 01-10-2008 </TD>
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
public class DocTypesCommand extends FPGPublisherSPICommand
		implements DocTypeConstants {

	public static final int REPORT_NAMES_ONLY = 0;
	public static final int REPORT_DETAILED   = 1;
	public static final int REPORT_SUMMARIED  = 2;

	/**
	 * Constructs a <b>doctypes SPI</b> command associated to the
	 * <code>shell</code>.
	 * 
	 * @param shell Associated <code>shell</code> instance.
	 */	
	public DocTypesCommand(FPGIntegratedPDFPublisherSPIShell shell) {
		super(shell);
	}

	/**
	 * {@inheritDoc}
	 */
	public void help(PrintStream err) {
		err.println("doctypes [-l | -L] [<docType> ... <docType>]");
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
			err.println(">< " + e.getMessage());
			e.printStackTrace(err);			

			return;
		}

		int reportType = REPORT_NAMES_ONLY;

		if (args.length > 0) {
			if (args[0].equals("-l")) {
				reportType = REPORT_DETAILED;
			} else if (args[0].equals("-L")) {
				reportType = REPORT_SUMMARIED;
			}
		}

		int index = 0;
		int nDocTypes = args.length;

		if (reportType != REPORT_NAMES_ONLY) {
			index = 1;

			nDocTypes--;
		}

		if (reportType == REPORT_SUMMARIED) {
			out.println(
					  Padder.rpad("Name", DOC_TYPE_NAME_MAX_LENGTH)
					+ " "
					+ "Remark");

			out.println(
					Padder.rpad(
							"",
							  DOC_TYPE_NAME_MAX_LENGTH
							+ DOC_TYPE_REMARK_MAX_LENGTH
							+ 1
							, '-'));
		}

		try {
			DocType[] docTypes = spi.getDocTypes();

			if (nDocTypes == 0) {
				for (int i = 0; i < docTypes.length; i++) {
					DocType docType = docTypes[i];
					String docTypeName = docType.getName();
					DocField[] fields = spi.getDocFields(docTypeName);

					String[] docIDFieldNames =
							spi.getDocIDFieldNames(docTypeName);

					printDocType(
							out, docType, fields, docIDFieldNames, reportType);
				}
			} else {
				for (int i = 1; i <= nDocTypes; i++) {
					String docTypeName = args[index++];
					boolean found = false;

					for (int j = 0; j < docTypes.length; j++) {
						DocType docType = docTypes[j];

						if (docType.getName().equals(docTypeName)) {
							DocField[] fields =	spi.getDocFields(docTypeName);

							String[] docIDFieldNames =
									spi.getDocIDFieldNames(docTypeName);

							printDocType(
									out,
									docType,
									fields,
									docIDFieldNames,
									reportType);

							found = true;

							break;
						}
					}

					if (!found) {
						err.println(
								"Unknown document type '" + docTypeName + "'.");
					}
				}
			}
		} catch (PDFPublisherException e) {
			err.println(">< " + e.getMessage());
			e.printStackTrace(err);
		}
	}

	private void printDocType(PrintStream out, DocType docType,
			DocField[] fields, String[] docIDFieldNames, int reportType) {

		String docTypeName = docType.getName();

		if (reportType == REPORT_DETAILED) {
			out.println();
			out.println(docTypeName + ":");

			out.println("    id               = " + docType.getID());
			out.println("    baseName         = " + docType.getBaseName());

			out.println(
					"    sourceSystemName = " + docType.getSourceSystemName());

			out.println("    publisherName    = " + docType.getPublisherName());

			out.println(
					  "    creationDate     = "
					+ new AbsoluteDateTime(docType.getCreated()));

			out.println("    remark           = " + docType.getRemark());

			int[] maxPartitionSizes = docType.getMaxPartitionSizes();

			out.println();
			out.println("    maxPartitionSizes:");

			out.println("        voc = " + maxPartitionSizes[VOC] + "k");
			out.println("        vmc = " + maxPartitionSizes[VMC] + "k");
			out.println("        foc = " + maxPartitionSizes[FOC] + "k");
			out.println("        fmc = " + maxPartitionSizes[FMC] + "k");

			out.println();
			out.println("    docFields:");

			for (int j = 0; j < fields.length; j++) {
				DocField docField = fields[j];
				int length = docField.getLength();
				String sLength = "";

				if (length > 0) {
					sLength = "[" + length + "]";
				}

				String sType = fieldTypeNames[docField.getType()];
				String tab = "        ";

				out.println(
						  tab
						+ "name      = " + docField.getName() + "\n"
						+ tab
						+ "type      = " + sType + sLength + "\n"
						+ tab
						+ "labelName = " + docField.getLabelName() + "\n"
						+ tab
						+ "remark    = " + docField.getRemark() + "\n");
			}

			out.println("    docIDFieldNames:");

			for (int j = 0; j < docIDFieldNames.length; j++) {
				out.println("        " + docIDFieldNames[j]);
			}
		} else if (reportType == REPORT_NAMES_ONLY) {
			out.println(docTypeName);
		} else {	// REPORT_SUMMARIED.
			out.println(
					  Padder.rpad(docTypeName, DOC_TYPE_NAME_MAX_LENGTH)
					+ " "
					+ docType.getRemark());
		}
	}
}
