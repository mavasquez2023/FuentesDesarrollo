

/*
 * @(#) FileResource.java    1.0 02-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice.ondemand.resources;


import java.io.File;

import java.util.logging.Level;
import java.util.logging.Logger;

import cl.araucana.core.util.logging.LogManager;

import cl.araucana.fpg.FPGException;
import cl.araucana.fpg.PDFDocument;

import cl.araucana.pdfservice.PDFProcess;
import cl.araucana.pdfservice.PDFServiceException;
import cl.araucana.pdfservice.ondemand.Action;
import cl.araucana.pdfservice.ondemand.ActionEvent;


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
 *            <TD> 02-07-2009 </TD>
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
public class FileResource extends Resource {

	private static final long serialVersionUID = 1008971828636017010L;

	private static Logger logger = LogManager.getLogger();
	
	public FileResource(String name, String address, String params,
			String extras) {
		
		super(name, address, params, "");
	}
	
	public void begin(PDFProcess process, Action action)
			throws PDFServiceException {
		
		super.begin(process, action);
		
		logger.info(
				  logID
				+ "action: "
				+ action.toString().replaceAll("\n", " "));
	}

	public boolean consume(PDFDocument document, boolean dispose)
			throws PDFServiceException {

		String docID = document.getDocumentModel().docID();
		boolean consume = super.consume(document, false);
		
		try {
			if (consume) {
				saveDocument(document);
				
				logger.info(
						  logID
						+ "action: id=" + action.getID() + " docID=" + docID);
				
				if (docID.equals(lastDocID)) {
					notifyListener(ActionEvent.CONSUMED);
				}				
			}
			
			return consume;
		} catch (PDFServiceException e) {
			abort(ActionEvent.RESOURCE_FAILED, e.getMessage());
			
			throw e;
		} finally {
			if (dispose) {
				try {
					document.close();
					document.release();
				} catch (FPGException e) {
					abort(ActionEvent.RESOURCE_FAILED, e.getMessage());
				
					throw new PDFServiceException(
							"Document '" + docID + "' cannot be closed", e);
				}
			}
		}
	}
	
	protected void saveDocument(PDFDocument document)
			throws PDFServiceException {
		
		File dir = new File(address);
		
		if (!dir.exists()) {
			if (!dir.mkdirs()) {
				throw new PDFServiceException(
						"Cannot create directory '" + address + "'");
			}
		}
		
		consumer.saveDocument(document, address);
	}

	public void abort(int reason, String cause) {
		logger.severe(
				  logID
				+ "action: id=" + action.getID() + " "
				+ "reason=\"" + ActionEvent.getMessage(reason) + "\".");
		
		super.abort(reason, cause);
	}
	
	public void end(String lastDocID) {
		logger.info(
				  logID
				+ "action: id=" + action.getID() + " docID=" + lastDocID);
		
		super.end(lastDocID);
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
}
