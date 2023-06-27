

/*
 * @(#) StartPDFServiceCommand.java    1.0 24-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice.command;


import java.io.InputStream;
import java.io.PrintStream;

import cl.araucana.pdfservice.DocumentType;
import cl.araucana.pdfservice.FailedProcessPDFServiceException;
import cl.araucana.pdfservice.PDFService;
import cl.araucana.pdfservice.PDFServiceException;
import cl.araucana.pdfservice.PDFProcessEvent;


/**
 * Starts a new PDF process to produce/consume a document type.
 * 
 * <BR>
 * <BR>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="90%" CELLPADDING="3" CELLSPACING="0">
 *     <tr>
 *        <th bgcolor="black">
 *            <font color="white"><strong>Syntax</strong></font>
 *        </th>
 *     </tr>
 *
 *     <tr>
 *        <td> <b>start &lt;docType&gt; [-spawn]
 *                   [-provider &lt;provider_options&gt;]
 *                   [-producer &lt;producer_options&gt;]
 *                   [-consumer &lt;consumer_options&gt;]</b>
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
 *        <td><strong>spawn</strong></td>
 *        
 *        <td>
 *            Process is executed in background.
 *        </td>
 *        
 *        <td>
 *            Process is executed in foreground.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>provider</strong></td>
 *        
 *        <td>
 *            Specific options for document model provider component. <b>(1)</b>
 *        </td>
 *        
 *        <td>
 *            &nbsp;
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>producer</strong></td>
 *        
 *        <td>
 *            Specific options for PDF producer component. <b>(1)</b>
 *        </td>
 *        
 *        <td>
 *            &nbsp;
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>consumer</strong></td>
 *        
 *        <td>
 *             Specific options for PDF consumer component. <b>(1)</b>
 *        </td>
 *        
 *        <td>
 *            &nbsp;
 *        </td>
 *     </tr>
 * </TABLE>
 *
 * <p><b>Notes: (1)</b> These options are specific of every component
 * implementation. To more information you must to read its specific
 * documentation.
 * </p>
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
 *            Document type to execute. It's mandatory.
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
public class StartPDFServiceCommand extends PDFServiceCommand {

	private int shellID;
	private boolean spawnable;

	/**
	 * Constructs a <b>Start PDFService</b> command associated to the
	 * <code>service</code>.
	 * 
	 * @param service Associated <code>service</code> instance.
	 */
	public StartPDFServiceCommand(PDFService service, int shellID,
			boolean spawnable) {

		super(service);

		this.shellID = shellID;
		this.spawnable = spawnable;
	}

	public void help(PrintStream err) {
		err.println(
				  "start <docType> [-spawn] "
				+ "[-provider <provider_options>] "
				+ "[-producer <producer_options>] "
				+ "[-consumer <consumer_options>]");
	}

	public void execute(String[] args, InputStream in, PrintStream out,
			PrintStream err) {

		if (args.length == 0) {
			err.println("Must specify document type name.");

			return;
		}

		String docTypeName = args[0];
		DocumentType docType = service.getDocumentType(docTypeName);

		if (docType == null) {
			err.println("Unknown document type '" + docTypeName + "'.");

			return;
		}

		boolean spawnWasSpecified = false;

		String[] providerOptions = null;
		String[] producerOptions = null;
		String[] consumerOptions = null;

		for (int i = 1; i < args.length; i++) {
			if (args[i].equals("-spawn")) {
				if (spawnWasSpecified) {
					err.println("Duplicated option '-spawn'.");

					return;
				}

				spawnWasSpecified = true;
			} else if (args[i].equals("-provider")) {
				if (providerOptions != null) {
					err.println("Duplicated option '-provider'.");

					return;
				}

				int nProviderOptions = 0;

				for (int j = i + 1; j < args.length; j++) {
					if (args[j].equals("-producer")
							|| args[j].equals("-consumer")) {

						break;
					}

					nProviderOptions++;
				}

				if (nProviderOptions == 0) {
					err.println("Missed provider options.");

					return;
				}

				providerOptions = new String[nProviderOptions];

				System.arraycopy(
						args,
						i + 1,
						providerOptions,
						0,
						providerOptions.length);

				i += nProviderOptions;
			} else if (args[i].equals("-producer")) {
				if (producerOptions != null) {
					err.println("Duplicated option '-producer'.");

					return;
				}

				int nProducerOptions = 0;

				for (int j = i + 1; j < args.length; j++) {
					if (args[j].equals("-consumer")) {
						break;
					}

					nProducerOptions++;
				}

				if (nProducerOptions == 0) {
					err.println("Missed producer options.");

					return;
				}

				producerOptions = new String[nProducerOptions];

				System.arraycopy(
						args,
						i + 1,
						producerOptions,
						0,
						producerOptions.length);

				i += nProducerOptions;
			} else if (args[i].equals("-consumer")) {
				if (consumerOptions != null) {
					err.println("Duplicated option '-consumer'.");

					return;
				}

				int nConsumerOptions = 0;

				for (int j = i + 1; j < args.length; j++) {
					if (args[j].equals("-producer")
							|| args[j].equals("-consumer")) {

						break;
					}

					nConsumerOptions++;
				}

				if (nConsumerOptions == 0) {
					err.println("Missed consumer options.");

					return;
				}

				consumerOptions = new String[nConsumerOptions];

				System.arraycopy(
						args,
						i + 1,
						consumerOptions,
						0,
						consumerOptions.length);

				i += nConsumerOptions;
			} else {
				err.println("Unknown option '" + args[i] + "'.");

				return;
			}
		}

		if (providerOptions == null) {
			providerOptions = new String[0];
		}

		if (producerOptions == null) {
			producerOptions = new String[0];
		}

		if (consumerOptions == null) {
			consumerOptions = new String[0];
		}

		if (!spawnable && spawnWasSpecified) {
			err.println(
					  "spawnable processes support is disabled in this shell.\n"
					+ "This process will be executed in foreground.");

			spawnWasSpecified = false;
		}

		int failedPID = 0;

		try {
			CustomPDFProcessListener listener = null;

			if (!spawnWasSpecified) {
				listener = new CustomPDFProcessListener();
			}

			int pid = service.createProcess(
					docType,
					providerOptions,
					producerOptions,
					consumerOptions,
					shellID,
					listener);

			service.startProcess(pid);

			out.println(
					  "Process for document type '" + docTypeName + "' "
					+ "started with pid " + pid + ".");

			if (listener != null) {
				synchronized (listener) {
					while (!listener.notified()) {
						try {
							listener.wait();
						} catch (InterruptedException e) {}
					}

					PDFProcessEvent event = listener.getEvent();

					out.println(
							  "Process " + event.getPID() + " "
							+ event.getMessage());

					out.println();

					if (event.wasAborted()) {
						failedPID = event.getPID();
					}
				}
			}
		} catch (FailedProcessPDFServiceException e) {
			failedPID = e.getFailedPID();

			err.println(
					"Initialization for process '" + failedPID + "' failed.");

			err.println(e.getMessage());
		} catch (PDFServiceException e) {
			err.println(e.getMessage());
		}

		if (failedPID != 0) {
			service.setFailedPID(failedPID);
		}
	}
}
