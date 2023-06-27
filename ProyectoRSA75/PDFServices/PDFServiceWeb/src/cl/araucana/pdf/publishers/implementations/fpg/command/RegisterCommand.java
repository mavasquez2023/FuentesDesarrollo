

/*
 * @(#) RegisterCommand.java    1.0 02-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.implementations.fpg.command;


import java.io.InputStream;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import cl.araucana.core.util.PropertiesLoader;
import cl.araucana.core.util.PropertiesLoaderException;

import cl.araucana.pdf.publishers.PDFPublisherException;

import cl.araucana.pdf.publishers.implementations.fpg.*;


/**
 * Registers a new source system, publisher agent or document type.
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
 *            <b>register -system &lt;name&gt; [&lt;description&gt;]
 *            | -publisher &lt;name&gt; | -docType &lt;dtdFileName&gt;</b>
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
 *        <td><strong>system</strong></td>
 *        
 *        <td>
 *            Indicates a new source system to register. Its name is mandatory
 *            and the description is optional. <b>SYSTEM</b> source system name
 *            is reserved.
 *        </td>
 *        
 *        <td> &nbsp; </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>publisher</strong></td>
 *        
 *        <td>
 *            Indicates a new publisher agent to register. Its name is
 *            mandatory. <b>SYSTEM</b> publisher agent name is reserved.
 *        </td>
 *        
 *        <td> &nbsp; </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>docType</strong></td>
 *        
 *        <td>
 *            Indicates a new document type to register. A properties file
 *            pathname corresponding to the
 *            <a href="#DTD">Document Type Descriptor</a> is required.
 *        </td>
 *        
 *        <td> &nbsp; </td>
 *     </tr>
 * </TABLE>
 * 
 * <a name="DTD">
 * <p><b>Document Type Descriptor</b></p>
 * 
 * <p>
 * A Document type descriptor file has properties file format. The supported
 * properties are the following:
 * </p>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="80%" CELLPADDING="3" CELLSPACING="0">
 *     <tr>
 *        <th bgcolor="black">
 *            <font color="white"><strong>Property</strong></font>
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
 *        <td><strong>name</strong></td>
 *        
 *        <td>
 *            Documenty type name. It's mandatory.
 *        </td>
 *        
 *        <td> &nbsp; </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>baseName</strong></td>
 *        
 *        <td>
 *            Physical table names prefix in the <i>FPG</i> schema for this
 *            document type. It's mandatory.
 *        </td>
 *        
 *        <td> &nbsp; </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>remark</strong></td>
 *        
 *        <td>
 *            Document type remark. It's optional.
 *        </td>
 *        
 *        <td> &nbsp; </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>voc</strong></td>
 *        
 *        <td>
 *            Maximum size in kilobytes to <i>Variable PDF Objects Content</i>.
 *            It's mandatory.
 *        </td>
 *        
 *        <td> &nbsp; </td>
 *     </tr>
 *     <tr>
 *        <td><strong>vmc</strong></td>
 *        
 *        <td>
 *            Maximum size in kilobytes to <i>Variable PDF Metadata Content</i>.
 *            It's mandatory.
 *        </td>
 *        
 *        <td> &nbsp; </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>foc</strong></td>
 *        
 *        <td>
 *            Maximum size in kilobytes to <i>Fixed PDF Objects Content</i>.
 *            It's mandatory.
 *        </td>
 *        
 *        <td> &nbsp; </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>fmc</strong></td>
 *        
 *        <td>
 *            Maximum size in kilobytes to <i>Fixed PDF Metadata Content</i>.
 *            It's mandatory.
 *        </td>
 *        
 *        <td> &nbsp; </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>field.</strong>&laquo;index&raquo;<strong>.name</strong>
 *        </td>
 *        
 *        <td>
 *            Document type &laquo;index&raquo; field's name. Each field name
 *            will be part of <b>docIndex</b>. It's mandatory.
 *        </td>
 *        
 *        <td> &nbsp; </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>field.</strong>&laquo;index&raquo;<strong>.type</strong>
 *        </td>
 *        
 *        <td>
 *            Document type &laquo;index&raquo; field's type. Supported types
 *            are: <b>int</b>, <b>ndate</b>, <b>char</b> and <b>string</b>.
 *            It's mandatory.
 *        </td>
 *        
 *        <td> &nbsp; </td>
 *     </tr>
 *      
 *     <tr>
 *        <td>
 *            <strong>
 *                field.</strong>&laquo;index&raquo;<strong>.length
 *            </strong>
 *        </td>
 *        
 *        <td>
 *            Document type &laquo;index&raquo; field's type length in
 *            characters. Only <b>string</b> field types requires
 *            a specified length.
 *        </td>
 *        
 *        <td>
 *            Depends on field type. (<b>1</b> for <b>char</b>,
 *            <b>10</b> for <b>int</b> and <b>5</b> for <b>ndate</b>)
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>
 *                field.</strong>&laquo;index&raquo;<strong>.labelName
 *            </strong>
 *        </td>
 *        
 *        <td>
 *            Document type &laquo;index&raquo; field's label name.
 *            It's optional.
 *        </td>
 *        
 *        <td> Field name.  </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>field.</strong>&laquo;index&raquo;<strong>.remark</strong>
 *        </td>
 *        
 *        <td>
 *            Document type &laquo;index&raquo; field's remark. It's optional.
 *        </td>
 *        
 *        <td> Field label name. </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>
 *                docID.</strong>&laquo;index&raquo;<strong>.fieldName
 *            </strong>
 *        </td>
 *        
 *        <td>
 *            Defines that named document type field will be
 *            &laquo;index&raquo; part of document type <b>docID</b>.
 *            It's optional.
 *        </td>
 *        
 *        <td> &nbsp; </td>
 *     </tr> 
 * </TABLE>
 * 
 * <p><b>Notes:</b></p>
 * 
 * <ul>
 * <li> &laquo;index&raquo; starts from 0.
 * <li> If none field is defined for <b>docID</b> then <b>docIndex</b>
 *      will be assumed to be <b>docID</b>.
 * </ul>
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
public class RegisterCommand extends FPGPublisherSPICommand
		implements DocTypeConstants {

	/**
	 * Constructs a <b>register SPI</b> command associated to the
	 * <code>shell</code>.
	 * 
	 * @param shell Associated <code>shell</code> instance.
	 */	
	public RegisterCommand(FPGIntegratedPDFPublisherSPIShell shell) {
		super(shell);
	}

	/**
	 * {@inheritDoc}
	 */
	public void help(PrintStream err) {
		err.print("register -system <name> [<description>] ");
		err.println("| -publisher <name> | -docType <dtdFileName>");
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

		if (args.length > 0) {
			if (args[0].equals("-system")) {
				if (args.length != 2 && args.length != 3) {
					help(err);

					return;
				}

				String name = args[1];
				String description = (args.length == 3) ? args[2] : "";

				registerSystem(spi, out, err, name, description);
			} else if (args[0].equals("-publisher")) {
				if (args.length != 2) {
					help(err);

					return;
				}

				String name = args[1];

				registerPublisher(spi, out, err, name);
			} else if (args[0].equals("-docType")) {
				if (args.length != 2) {
					help(err);

					return;
				}

				String dtdFileName = args[1];

				registerDocType(spi, out, err, dtdFileName);
			} else {
				help(err);
			}
		} else {
			help(err);
		}
	}

	private void registerSystem(FPGIntegratedPDFPublisherSPI spi,
			PrintStream out, PrintStream err, String name, String description) {

		try {
			spi.registerSourceSystem(name, description);

			out.println("System '" + name + "' was registered.");
		} catch (PDFPublisherException e) {
			err.println("System '" + name + "' cannot be registered.");
		}
	}

	private void registerPublisher(FPGIntegratedPDFPublisherSPI spi,
			PrintStream out, PrintStream err, String name) {

		try {
			spi.registerPublisher(name);

			out.println("Publisher '" + name + "' was registered.");
		} catch (PDFPublisherException e) {
			err.println("Publisher '" + name + "' cannot be registered.");
		}
	}

	private void registerDocType(FPGIntegratedPDFPublisherSPI spi,
			PrintStream out, PrintStream err, String dtdFileName) {

		/*
		 * DocType's Properties File structure:
		 *     name=PlanillaIsapre
		 *     baseName=PLANILLA_ISAPRE
		 *     remark=Planilla de ISAPRE
		 *
		 *     voc=192
		 *     vmc=32
		 *     foc=128
		 *     fmc=32
		 *
		 *     field.0.name=FechaProceso
		 *     field.0.type=int
		 *     field.0.labelName=Fecha Proceso
		 *     field.0.remark=Periodo de Cotizacion
		 *
		 *     field.1.name=Holding
		 *     field.1.type=int
		 *
		 *     field.2.name=RazonSocial
		 *     field.2.type=string
		 *     field.2.length=40
		 *          ...
		 *     docID.0.fieldName=FechaProceso
		 *     docID.1.fieldName=NombreIsapre
		 *     docID.2.fieldName=FolioPlanilla
		 */
		PropertiesLoader loader = new PropertiesLoader();
		Properties properties = null;
		String docTypeName;

		try {
			properties = loader.load(dtdFileName);

			docTypeName = properties.getProperty("name");

			if (docTypeName == null) {
				err.println("Missed document type name.");

				return;
			}

			/*
			 * Checks duplicated document type name.
			 */
			DocType docType = spi.getDocType(docTypeName);

			if (docType != null) {
				err.println(
						"Document type '" + docTypeName + "' exists.");

				return;
			}

			String baseName = properties.getProperty("baseName");

			if (baseName == null) {
				err.println("Missed document type base name.");

				return;
			}

			String remark = properties.getProperty("remark");

			if (remark == null) {
				remark = "";
			}

			int[] maxPartitionSizes = new int[NPARTITIONS];

			for (int i = 0; i < partitionNames.length; i++) {
				String sMaxPartitionSize =
						properties.getProperty(partitionNames[i]);

				try {
					maxPartitionSizes[i] = Integer.parseInt(sMaxPartitionSize);

					if (maxPartitionSizes[i] <= 0) {
						throw new NumberFormatException();
					}
				} catch (NumberFormatException e) {
					err.println(
							  "Invalid size for "
							+ "partition '" + partitionNames[i] + "'.");

					return;
				}
			}

			List fields = new ArrayList();
			String fieldName;
			int fieldNo = 0;

			while ((fieldName =
					properties.getProperty("field." + fieldNo + ".name"))
							!= null) {

				String typeName =
						properties.getProperty("field." + fieldNo + ".type");

				if (typeName == null) {
					err.println("Missed type for field '" + fieldName + "'.");

					return;
				}

				String sLength =
						properties.getProperty("field." + fieldNo + ".length");

				int length = 0;

				if (sLength != null) {
					try {
						length = Integer.parseInt(sLength);

						if (length <= 0) {
							throw new NumberFormatException();
						}
					} catch (NumberFormatException e) {
						err.println(
								  "Invalid length for field "
								+ "'" + fieldName + "'.");

						return;
					}
				}

				String labelName =
						properties.getProperty(
								"field." + fieldNo + ".labelName");

				if (labelName == null) {
					labelName = new String(fieldName);
				}

				String fieldRemark =
						properties.getProperty("field." + fieldNo + ".remark");

				if (fieldRemark == null) {
					fieldRemark = new String(labelName);
				}

				DocField field = new DocField();

				field.setName(fieldName);
				field.setType(DocField.getType(typeName));
				field.setLength(length);
				field.setLabelName(labelName);
				field.setRemark(fieldRemark);

				fields.add(field);

				fieldNo++;
			}

			List docIDFieldNames = new ArrayList();
			String docIDFieldName;

			fieldNo = 0;

			while ((docIDFieldName =
					properties.getProperty("docID." + fieldNo + ".fieldName"))
							!= null) {

				docIDFieldNames.add(docIDFieldName);

				fieldNo++;
			}

			docType = new DocType();

			docType.setName(docTypeName);
			docType.setBaseName(baseName);
			docType.setRemark(remark);

			spi.registerDocumentType(
					docType,
					(DocField[]) fields.toArray(new DocField[0]),
					(String[]) docIDFieldNames.toArray(new String[0]),
					maxPartitionSizes);

			out.println("DocType '" + docTypeName + "' was registered.");
		} catch (PropertiesLoaderException e) {
			err.println(e.getMessage());
		} catch (PDFPublisherException e) {
			e.printStackTrace(err);

			err.println(
					    "Document type cannot be registered. "
					  + "[" + e.getMessage() + "]");
		}
	}
}
