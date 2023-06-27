

/*
 * @(#) OnDemandDocumentModelProvider.java    1.0 24-06-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice.ondemand;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cl.araucana.pdfservice.DocumentType;
import cl.araucana.pdfservice.DocumentModelProvider;
import cl.araucana.pdfservice.PDFProcess;
import cl.araucana.pdfservice.PDFRequest;
import cl.araucana.pdfservice.PDFServiceException;

import cl.araucana.pdfservice.ondemand.ApplicationResources;
import cl.araucana.pdfservice.ondemand.resources.Resource;

import cl.araucana.fpg.DocumentModel;
import cl.araucana.fpg.DocumentModelList;

import cl.araucana.core.util.Queue;


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
public abstract class OnDemandDocumentModelProvider
		implements DocumentModelProvider, Constants {

	protected PDFProcess process;
	protected String queueName;
	protected int queueCapacity;
	protected Queue queue;

	protected Map parameterMap;
	protected ApplicationResources appResources;

	public void init(PDFProcess process) throws PDFServiceException {

		this.process = process;
		
		parameterMap = new HashMap();
		
		/*
		 * Gets input queue parameters.
		 */
		DocumentType docType = process.getDocumentType();
		String tag = docType.getTag();
		String[] params = tag.split(":");

		if (params.length < 2) {
			throw new PDFServiceException("Missed queue parameters");
		}

		queueName = params[0];

		try {
			queueCapacity = Integer.parseInt(params[1]);

			if (queueCapacity <= 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			throw new PDFServiceException("Invalid queue capacity");
		}

		queue = new Queue(queueCapacity);
		appResources = ApplicationResources.getInstance();

		appResources.addQueue(queueName, queue);
	}

	public Map getParameterMap() {
		return parameterMap;
	}

	protected final void execute() throws Exception {

		int nWorkUnits = 0;
		Action action;

		while ((action = getAction()) != null) {
			Resource resource = action.getResource();
			int sample = action.getSelector().getSample();
			DocumentModelList docModelList = null;

			try {
				resource.begin(process, action);
				
				String lastDocID = null;
				
				docModelList = getDocumentModelList(action);
			
				while (nWorkUnits < sample && docModelList.next()) {
					DocumentModel docModel = docModelList.getDocumentModel();
					PDFRequest request = new PDFRequest(PARAM_COUNT);

					lastDocID = docModel.docID();
					
					request.setParameter(PARAM_ACTION, action);
					request.setParameter(PARAM_DOCUMENT_MODEL, docModel);
					
					process.putProduceRequest(request);
					process.setWorkUnits(++nWorkUnits);
				}
				
				resource.end(lastDocID);
			} catch (Exception e) {
				if (e instanceof IOException) {
					notifyException((IOException) e);
				}
				
				resource.abort(ActionEvent.DMP_FAILED, e.getMessage());
				
				if (!(e instanceof IOException)) {
					throw e;
				}				
			} finally {
				if (docModelList != null) {
					docModelList.close();
				}
			}
		}

		process.putProduceRequest(null);
	}

	public void destroy() {
		if (queue != null) {
			queue.release();

			appResources.removeQueue(queueName);
			
			queue = null;
		}

		process = null;
	}

	protected abstract void notifyException(IOException e);
	
	protected abstract DocumentModelList getDocumentModelList(Action action)
			throws IOException;

	private Action getAction() throws InterruptedException {

		return (Action) queue.get();
	}
}
