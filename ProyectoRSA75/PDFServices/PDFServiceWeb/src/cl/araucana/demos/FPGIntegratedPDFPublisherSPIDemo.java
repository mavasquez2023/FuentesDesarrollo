

/*
 * @(#) FPGIntegratedPDFPublisherSPIDemo.java    1.0 26-11-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.demos;

import java.sql.SQLException;

import javax.sql.DataSource;

import cl.araucana.core.util.JDBCUtils;

import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.Publisher;
import cl.araucana.pdf.publishers.PublisherManager;

import cl.araucana.pdf.publishers.implementations.fpg.*;


/**
 * ...
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
 *            <TD> 26-11-2008 </TD>
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
public class FPGIntegratedPDFPublisherSPIDemo  {

	private static final int CMD_NONE               = -1;
	private static final int CMD_LIST_FPG_PROVIDERS =  0;
	private static final int CMD_LIST_DOC_TYPES     =  1;
	private static final int CMD_DESCRIBE_DOC_TYPE  =  2;

	private String pubName;
	private FPGIntegratedPDFPublisherSPI spi;

	public static void usage() {
		System.err.println(
				  "usage: {   -listFPGProviders\n"
				+ "         | -listDocTypes <providerName>\n"
				+ "         | -describeDocType <providerName> <docType> }\n");

		System.exit(1);
	}

	public static void noFPGProvider() {
		System.err.println("Provider must be FPGIntegrated one.");

		System.exit(2);
	}

	public static void main(String[] args) {

		if (args.length == 0) {
			usage();
		}

		int cmd = CMD_NONE;
		String providerName = null;
		String docTypeName = null;

		if (args[0].equals("-listFPGProviders")) {
			if (args.length != 1) {
				usage();
			}

			cmd = CMD_LIST_FPG_PROVIDERS;
		} else if (args[0].equals("-listDocTypes")) {
			if (args.length != 2) {
				usage();
			}

			cmd = CMD_LIST_DOC_TYPES;
			providerName = args[1];
		} else if (args[0].equals("-describeDocType")) {
			if (args.length != 3) {
				usage();
			}

			cmd = CMD_DESCRIBE_DOC_TYPE;
			providerName = args[1];
			docTypeName = args[2];
		} else {
			usage();
		}

		PublisherManager manager = null;
		Publisher publisher = null;
		FPGIntegratedPDFPublisherSPIDemo demo = null;

		try {
			manager = PublisherManager.getInstance();

			if (cmd != CMD_LIST_FPG_PROVIDERS) {
				publisher = manager.getPublisher(providerName);

				/*
				 * Checks a FPG PDFPublisher.
				 */
				if (publisher.getType() != FPGIntegratedPDFPublisher.class) {
					noFPGProvider();
				}

				demo = new FPGIntegratedPDFPublisherSPIDemo(publisher);
			} else {
				demo = new FPGIntegratedPDFPublisherSPIDemo();
			}

			switch (cmd) {
				case CMD_LIST_FPG_PROVIDERS:
					demo.listFPGProviders(manager);

					break;

				case CMD_LIST_DOC_TYPES:
					demo.listDocTypes();

					break;

				case CMD_DESCRIBE_DOC_TYPE:
					demo.describeDocType(docTypeName);

					break;
			}
		} catch (PDFPublisherException e) {
			e.printStackTrace();

			System.exit(3);
		} finally {
			if (demo != null) {
				demo.close();
			}
		}

		System.exit(0);
	}

	public FPGIntegratedPDFPublisherSPIDemo() {
	}

	public FPGIntegratedPDFPublisherSPIDemo(Publisher publisher)
			throws PDFPublisherException {

		pubName = publisher.getName();

		String dataSourceName =	publisher.getPropertyValue("dataSourceName");

		if (dataSourceName == null) {
			throw new PDFPublisherException(
					"dataSourceName property is mandatory.");
		}

		String schemaName = publisher.getPropertyValue("schemaName");

		if (schemaName == null) {
			throw new PDFPublisherException(
					"schemaName property is mandatory.");
		}

		DataSource dataSource;

		try {
			dataSource = JDBCUtils.getDataSource(dataSourceName);
		} catch(SQLException e) {
			throw new PDFPublisherException(
					"Cannot open FPG spi '" + pubName + "'", e);
		}

		//System.out.println("Connecting to FPG spi '" + pubName + "' ...");

		spi = new FPGIntegratedPDFPublisherSPI(dataSource, schemaName);

		//System.out.println("Connected to FPG spi.");
	}

	public void listFPGProviders(PublisherManager manager)
			throws PDFPublisherException {

		System.out.println("Available FPG Providers:");

		String[] publisherNames = manager.getPublisherNames();

		for (int i = 0; i < publisherNames.length; i++) {
			Publisher publisher = manager.getPublisher(publisherNames[i]);

			/*
			 * Checks a FPG PDFPublisher.
			 */
			if (publisher.getType() == FPGIntegratedPDFPublisher.class) {
				System.out.println(
						  "    "
						+ publisher.getName()
						+ " -- "
						+ publisher.getDescription());
			}
		}
	}

	public void listDocTypes() throws PDFPublisherException {

		DocType[] docTypes = spi.getDocTypes();

		System.out.println("Available document types:");

		for (int i = 0; i < docTypes.length; i++) {
			DocType docType = docTypes[i];

			System.out.println("    " + docType.getName());
		}
	}

	public void describeDocType(String docTypeName)
			throws PDFPublisherException {

		DocField[] docFields = spi.getDocFields(docTypeName);

		System.out.println("Document type '" + docTypeName + "':");

		for (int i = 0; i < docFields.length; i++) {
			DocField docField = docFields[i];

			System.out.println(
					  "    "
					+ docField.getName()
					+ ", "
					+ docField.getTypeName()
					+ ", "
					+ docField.getLength()
					+ ", "
					+ docField.getLabelName()
					+ ", "
					+ docField.getRemark());
		}
	}

	public void close() {
		if (spi != null) {
			//System.out.println(
			//		"Disconnecting from FPG spi '" + pubName + "' ...");

			spi.disconnect();

			//System.out.println("Disconnected from FPG spi.");

			spi = null;
		}
	}
}
