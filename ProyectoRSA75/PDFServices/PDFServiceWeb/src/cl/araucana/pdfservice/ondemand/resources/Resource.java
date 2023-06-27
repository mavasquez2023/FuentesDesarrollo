

/*
 * @(#) Resource.java    1.0 24-06-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice.ondemand.resources;


import java.io.Serializable;

import cl.araucana.fpg.PDFDocument;

import cl.araucana.pdfservice.PDFProcess;
import cl.araucana.pdfservice.PDFServiceException;
import cl.araucana.pdfservice.ondemand.Action;
import cl.araucana.pdfservice.ondemand.ActionEvent;
import cl.araucana.pdfservice.ondemand.ActionListener;
import cl.araucana.pdfservice.ondemand.OnDemandPDFConsumer;


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
 *            <TD> 24-06-2009 </TD>
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
public abstract class Resource implements Serializable {

	protected String name;
	protected String address;
	protected String params;
	protected String extras;
	
	protected String logID;
	protected OnDemandPDFConsumer consumer;
	protected Action action;
	
	protected String lastDocID;
	
	private boolean aborted = false;
	private boolean abortDetected = false;
	
	protected int reason;
	protected String cause;
	
	public Resource(String name, String address, String params, String extras) {
		this.name = name;
		this.address = address;
		this.params = params;
		this.extras = extras;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getParams() {
		return params;
	}

	public void setExtras(String extras) {
		this.extras = extras;
	}

	public String getExtras() {
		return extras;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
	public String toString() {
		return
				  "name=" + name + "\n"
				+ "address=" + address + "\n"
				+ "params=[" + params + "]\n"
				+ "extras=[" + extras + "]";
	}
	
	public void begin(PDFProcess process, Action action)
			throws PDFServiceException {
		
		logID = "[" + process.getPID() + "] ";
		consumer = (OnDemandPDFConsumer) process.getPDFConsumer();
		this.action = action;
	}
	
	public boolean consume(PDFDocument document, boolean dispose)
			throws PDFServiceException {
		
		if (wasAborted()) {
			if (!abortDetected) {
				notifyListener(reason);
				
				abortDetected = true;
			}
			
			return false;
		}
		
		return true;
	}
	
	public void end(String lastDocID) {
		this.lastDocID = lastDocID;
	}

	public void abort(int reason, String cause) {
		if (wasAborted()) {
			return;
		}
		
		aborted = true;
		this.reason = reason;
		this.cause = cause;
		
		notifyListener(reason);
	}
	
	public boolean wasAborted() {
		return aborted;
	}
	
	protected void notifyListener(int reason) {
		ActionListener actionListener = action.getActionListener();
		
		if (actionListener == null) {
			return;
		}
		
		ActionEvent event =
				new ActionEvent(
						reason, cause, consumer.getDocTypeName(), action);
		
		Thread listenerThread = new ActionListenerThread(actionListener, event);
		
		listenerThread.start();
	}
	
	protected void actionListenerFailed(Action action, Throwable t) {
		System.err.println("*** ACTION LISTENER FAILED ***");
		
		System.err.println(
				  "ACTION: " + action.toString().replaceAll("\n", " "));
		
		System.err.println("-----------------  BEGIN TRACE  -----------------");
		t.printStackTrace();
		System.err.println("-----------------   END TRACE   -----------------");
	}
	
	private class ActionListenerThread extends Thread {
		
		private ActionListener actionListener;
		private ActionEvent event;
		
		private ActionListenerThread(ActionListener actionListener,
				ActionEvent event) {
			
			this.actionListener = actionListener;
			this.event = event;
			
			setDaemon(true);
			setName("ActionListenerThread-" + event.getAction().getID());
		}
		
		public void run() {
			try {
				actionListener.documentActioned(event);
			} catch (Throwable t) {
				actionListenerFailed(event.getAction(), t);
			}
		}
	}
}
