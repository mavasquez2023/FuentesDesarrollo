

/*
 * @(#) PrintResource.java    1.0 06-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice.ondemand.resources;


import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import cl.araucana.core.util.FederalQueue;
import cl.araucana.core.util.FederalQueueFactory;
import cl.araucana.core.util.Queue;

import cl.araucana.core.util.logging.LogManager;

import cl.araucana.fpg.DocumentModel;
import cl.araucana.fpg.FPGException;
import cl.araucana.fpg.PDFDocument;

import cl.araucana.pdfservice.PDFProcess;
import cl.araucana.pdfservice.PDFServiceException;

import cl.araucana.pdfservice.ondemand.Action;
import cl.araucana.pdfservice.ondemand.ActionEvent;

import cl.araucana.pps.print.AcrobatPrintAgent;
import cl.araucana.pps.print.PrintAgent;
import cl.araucana.pps.print.RemotePrintAgent;


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
 *            <TD> 06-07-2009 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author  Germán Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class PrintResource extends Resource implements Runnable {

	public static final int ACROBAT_PRINT_AGENT = 0;
	public static final int REMOTE_PRINT_AGENT  = 1;
	
	private static final long serialVersionUID = -7193741792632376515L;

	private static final String[] agents = {
		"acrobat",
		"rpa"
	};
	
	private static Logger logger = LogManager.getLogger();
	
	private PrintAgent printAgent;
	private int agent;
	private FederalQueue federalQueue;
	private int federalID;
	private Queue queue;
	private Thread printMonitor;
	private String _address;
	
	public PrintResource(String name, String address, String params, 
			String extras) {
		
		super(name, address, params, extras);
		
		_address = address;
		
		int capacity;
		int agent;
		
		try {
			String[] args = extras.split(" ");
			
			if (args.length != 2) {
				throw new IllegalArgumentException(
						"Two extra parameters are required");
			}
			
			capacity = Integer.parseInt(args[0]);
			agent = Integer.parseInt(args[1]);
		} catch(Exception e) {
			throw new IllegalArgumentException("Unexpected extra parameters");
		}
		
		switch (agent) {
		
			case ACROBAT_PRINT_AGENT:
				
				printAgent = new AcrobatPrintAgent(params);
				
				break;
				
			case REMOTE_PRINT_AGENT:
				
				printAgent = new RemotePrintAgent(params);
				
				/*
				 * RPA adaption to permit both local and remote
				 * printing to windows printers. 
				 */
				if (address.startsWith("\\\\")) {
					this.address = "\\\\.\\" + address;
				}
				
				break;
				
			default:
				
				throw new IllegalArgumentException(
						"Unknown print agent '" + agent + "'");
		}
		
		this.agent = agent;
		queue = new Queue(capacity);
	}
	
	public void begin(PDFProcess process, Action action)
			throws PDFServiceException {
		
		super.begin(process, action);
		
		logger.info(
				  logID
				+ "action: "
				+ action.toString().replaceAll("\n", " "));
		
		FederalQueueFactory federalQueueFactory =
				consumer.getFederalQueueFactory();
		
		federalQueue = federalQueueFactory.getInstance(name);
		federalID = federalQueue.addQueue(queue);
		printMonitor = new Thread(this);
		
		printMonitor.setName(name);
		printMonitor.setDaemon(true);
		printMonitor.start();
	}

	public boolean consume(PDFDocument document, boolean dispose)
			throws PDFServiceException {

		boolean consume = super.consume(document, false);
		
		try {
			if (consume) {
				DocInfo docInfo = new DocInfo();
				
				docInfo.document = document;
				docInfo.dispose = dispose;
				
				federalQueue.put(federalID, docInfo);
			}
			
			return consume;
		} catch (InterruptedException e) {
			abort(ActionEvent.RESOURCE_FAILED, "interrupted");
			
			throw new PDFServiceException("Resource was interrupted");
		}
	}
	
	public void abort(int reason, String cause) {
		logger.severe(
				  logID
				+ "action: id=" + action.getID() + " "
				+ "reason=\"" + ActionEvent.getMessage(reason) + "\".");
		
		super.abort(reason, cause);
		printMonitor.interrupt();
	}
	
	public void end(String lastDocID) {
		logger.info(
				  logID
				+ "action: id=" + action.getID() + " docID=" + lastDocID);
		
		super.end(lastDocID);
	}
	
	public void run() {
		DocInfo docInfo;
		
		try {
			while ((docInfo = getDocInfo()) != null && print(docInfo));
			
			notifyListener(ActionEvent.CONSUMED);
		} catch (InterruptedException e) {
			logger.severe(logID + "Resource was interrupted.");
		} catch (Exception e) {
			logger.log(Level.SEVERE, logID + ">< Unexpected exception:", e);
		} finally {
			federalQueue.removeQueue(federalID);
			queue.release();
		}
	}

	public String toString() {
		return
				  "name=" + name + "\n"
				+ "address=" + _address + "\n"
				+ "params=[" + params + "]";
	}
	
	protected void notifyListener(int reason) {
		logger.info(
				logID + "action: id=" + action.getID() + " listener notified.");
		
		super.notifyListener(reason);
	}
	
	protected void actionListenerFailed(Action action, Throwable t) {
		logger.log(
				Level.SEVERE,
				  logID
				+ "action: " + action.toString().replaceAll("\n", " ") + " "
				+ ">< Unexpected throwable:", t);
	}

	private boolean print(DocInfo docInfo) {
		PDFDocument document = docInfo.document;
		boolean dispose = docInfo.dispose;
		String docID = document.getDocumentModel().docID();
		
		try {
			String spooledFileName = consumer.spoolDocument(document);
			int rc = printAgent.print(spooledFileName, address, null, null);
			
			if (rc != 0) {
				throw new IOException("Print failed with rc=" + rc + ".");
			}
			
			logger.info(
					  logID
					+ "action: id=" + action.getID() + " "
					+ "docID=" + docID + " "
					+ "agent=["	+ agents[agent]+ ", \"" + _address + "\"] "
					+ "spooledFile=" + spooledFileName + ".");
			
			if (dispose) {
				document.close();
				document.release();
			}
			
			return !docID.equals(lastDocID);
		} catch (PDFServiceException e) {
			logger.log(
					Level.SEVERE,
					logID + ">< Document '" + docID + "' cannot be spooled:",
					e);
		} catch (IOException e) {
			logger.log(
					Level.SEVERE,
					  logID
					+ ">< Document '" + docID + "' cannot be printed. "
					+ "Resource: " + toString().replaceAll("\n", ", "),
					e);
		} catch (FPGException e) {
			logger.log(
					Level.SEVERE,
					logID + "Document '" + docID + "' cannot be closed:",
					e);
		}
		
		/*
		 * Abort method will interrupt the current print monitor
		 * hence return value is irrelevant.
		 */
		abort(ActionEvent.RESOURCE_FAILED, "interrupted");
		
		return true;
	}
	
	private DocInfo getDocInfo() throws InterruptedException {
		
		return (DocInfo) federalQueue.get(federalID);
	}
	
	private class DocInfo {
		
		private PDFDocument document;
		private boolean dispose;
		
		public String toString() {
			if (document != null) {
				DocumentModel docModel = document.getDocumentModel();
				
				if (docModel != null) {
					return docModel.docID();
				}
			}
			
			return null;
		}
	}
}
