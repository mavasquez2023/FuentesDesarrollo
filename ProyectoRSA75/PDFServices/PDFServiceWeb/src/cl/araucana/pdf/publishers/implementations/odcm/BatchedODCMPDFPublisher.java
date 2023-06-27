

/*
 * @(#) BatchedODCMPDFPublisher.java    1.0 25-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.implementations.odcm;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.PublishOptions;


/**
 * This is an abstract extension of {@link ODCMPDFPublisher} that provides
 * best performance in publication operations. Performance is enhanced using
 * additionally a pool of unloader objects each one running in its own thread
 * to unload a <u>controlled batch</u> of published PDF documents previously to
 * be republished. This class is ideal for <b>REPLACE</b> replace mode with
 * ODCM.
 * 
 * <p> A concrete implementation of this class must to define a criteria
 * in function of document ID to group published PDF documents in a same batch
 * instance. When this batch is full or there is not more published PDF
 * documents, batched documents will be deleted together executing only one
 * instance of <b>arsdoc delete</b>.
 * </p>
 *  
 * <p> The pool of unloader objects can be configured with the following
 * publisher properties:
 * </p>
 *
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="60%" CELLPADDING="3" CELLSPACING="0">
 *     <tr>
 *        <th bgcolor="black">
 *            <font color="white"><strong>Name</strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>Description</strong></font>
 *        </th>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>unloaders</strong>
 *        </td>
 *        
 *        <td>
 *            Number of unloader threads in a publication operation.
 *            Default is 1.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <strong>maxScopeLength</strong>
 *        </td>
 *        
 *        <td>
 *            Maximum batch scope length in characters. This is a
 *            critical property which value is dependent of platform
 *            and shell used to execute <b>ars</b> commands in it.
 *            
 *            <BR>
 *            <BR>
 *            
 *            To <i>Microsoft Windows</i> systems is recommended not
 *            to exceed <b>2800 characters</b>. Other platforms haven't
 *            been tested in this area still. 
 *        </td>
 *     </tr>
 * </TABLE>
 * 
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
 *            <TD> 25-10-2008 </TD>
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
public abstract class BatchedODCMPDFPublisher extends ODCMPDFPublisher {

	private static Pattern pattern;
	private static int unloaderID = 0;

	private int maxScopeLength;

	// Unloaders.
	private int nUnloaders;
	private Map activeUnloaders;
	private boolean interruptedUnload;

	static {
		try {
			pattern = Pattern.compile("/");
		} catch (PatternSyntaxException e) {}
	}

	/**
	 * Creates an ad-hoc instance according to specified publish options
	 * in <code>options</code>.
	 * 
	 * @param docType Document type.
	 * 
	 * @param docVersion Document type's version.
	 * 
	 * @param options Options to the <b>ODCM PDF Publisher</b>.
	 * 
	 * @throws PDFPublisherException If <code>options</code> is
	 *         <code>null</code> or the named publisher in options is unknown
	 *         or cannot create a new <b>Batched ODCM PDF Publisher</b>
	 *         instance.
	 */		
	public BatchedODCMPDFPublisher(String docType, int docVersion,
			PublishOptions options) throws PDFPublisherException {

		super(docType, docVersion, options);

		/*
		 * Validates maxScopeLength property.
		 */
		String sMaxScopeLength = getPropertyValue("maxScopeLength");

		if (sMaxScopeLength == null) {
			throw new PDFPublisherException("Missed 'maxScopeLength' property");
		}

		try {
			maxScopeLength = Integer.parseInt(sMaxScopeLength);

			if (maxScopeLength <= 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			throw new PDFPublisherException(
					"Invalid maxScopeLength '" + maxScopeLength + "'");
		}

		/*
		 * Validates unloaders property.
		 */
		String sUnloaders = getPropertyValue("unloaders", "1");

		if (sUnloaders != null) {
			try {
				nUnloaders = Integer.parseInt(sUnloaders);

				if (nUnloaders <= 0) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				throw new PDFPublisherException(
						"Invalid unloaders value '" + sUnloaders + "'");
			}
		}
	}

	/**
	 * Unpublishes all published PDF document instances in the specified
	 * <code>docIDs</code> map. Documents are unpublished in document ID
	 * ascendent order and grouped in one or more batches.
	 *  
	 * @param docIDs published PDF document instances to be unpublished.
	 * 
	 * @throws PDFPublisherException If PDF documents cannot be unpublished.
	 * 
	 * @see #prepareBatches(String, int)
	 * @see #addBatch(String[])
	 * @see #closeBatches()
	 */	
	public void unpublish(SortedMap docIDs)	throws PDFPublisherException {

		// System.out.println("unpublish STARTED");

		String scope = replaceModeScope.toString();

		prepareBatches(scope, maxScopeLength);

		activeUnloaders = new HashMap();

		try {
			Iterator iterator = docIDs.keySet().iterator();

			while (iterator.hasNext()) {
				String docID = (String) iterator.next();
				String batch = addBatch(pattern.split(docID));

				if (batch != null) {
					startUnloader(scope + batch);
				}
			}

			String batch = closeBatches();

			if (batch != null) {
				startUnloader(scope + batch);
			}

			/*
			 * Waits that all loaders are ended.
			 */
			// System.out.println("unpublish FINAL waitUnloaders ...");

			 waitUnloaders();
		} catch (Exception e) {

			/*
			 * Stops all active unloaders.
			 */
			// System.out.println("unpublish stopUnloaders ...");
			stopUnloaders();

			throw new PDFPublisherException("Unpublish FAILED", e);
		}

		// System.out.println("unpublish ENDED");
	}

	private void startUnloader(String scope) throws InterruptedException {

		Unloader unloader = new Unloader(scope, Thread.currentThread());

		synchronized (activeUnloaders) {
			if (activeUnloaders.size() >= nUnloaders) {
				// System.out.println("unpublish waitUnloaders ...");

				do {
					activeUnloaders.wait();
				} while (activeUnloaders.size() > 0);
			}

			activeUnloaders.put(new Integer(unloader.id), unloader);
			activeUnloaders.notifyAll();
		}

		unloader.start();
	}

	private void waitUnloaders() throws InterruptedException {

		synchronized (activeUnloaders) {
			while (activeUnloaders.size() > 0) {
				activeUnloaders.wait();
			}

			activeUnloaders.notifyAll();
		}
	}

	private void stopUnloaders() {
		interruptedUnload = true;

		synchronized (activeUnloaders) {
			Collection unloaders = activeUnloaders.values();
			Iterator iterator = unloaders.iterator();

			while (iterator.hasNext()) {
				Unloader unloader = (Unloader) iterator.next();

				unloader.interrupt();
			}
		}
	}

	private class Unloader extends Thread {

		private int id;
		private String scope;
		private Thread parentThread;

		private Unloader(String scope, Thread parentThread) {
			id = ++unloaderID;

			this.scope = scope;
			this.parentThread = parentThread;

			setName("Unloader-" + id);
			setDaemon(true);
		}

		public void run() {
			boolean failed = false;

			/*
			 * Unpublishs scope in IBM OnDemand Content Manager.
			 */
			try {
				arsdocDelete(scope);
			} catch (PDFPublisherException e) {
				failed = true;
			} finally {
				synchronized (activeUnloaders) {
					activeUnloaders.remove(new Integer(id));
					activeUnloaders.notifyAll();
				}

				if (failed && !interruptedUnload) {
					parentThread.interrupt();
				}
			}
		}
	}

	/**
	 * Prepares this instance to batch documents in the specified
	 * replace mode scope. This scope must be accounted in the total
	 * batch scope length.
	 * 
	 * @param replaceModeScope Replace mode scope.
	 * 
	 * @param maxBatchLength Maximum batch scope length in characters.
	 * 
	 * @see #unpublish(SortedMap)
	 */
	public abstract void prepareBatches(String replaceModeScope,
			int maxBatchLength);

	/**
	 * Adds a published PDF document to the current batch.
	 * 
	 * @param docIDComponents published PDF document's document ID component
	 *        parts.
	 *         
	 * @return Current batch if was completed, otherwise <code>null</code>.
	 * 
	 * @see #unpublish(SortedMap)
	 */
	public abstract String addBatch(String[] docIDComponents);

	/**
	 * Closes batched unpublication operation. 
	 * 
	 * @return Last uncompleted batch, otherwise <code>null</code>.
	 * 
	 * @see #unpublish(SortedMap)
	 */
	public abstract String closeBatches();
}
