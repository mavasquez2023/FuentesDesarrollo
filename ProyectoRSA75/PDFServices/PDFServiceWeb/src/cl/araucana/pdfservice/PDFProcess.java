

/*
 * @(#) PDFProcess.java    1.0 22-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice;


import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import cl.araucana.core.util.Array;
import cl.araucana.core.util.JVM;
import cl.araucana.core.util.Padder;
import cl.araucana.core.util.Queue;

import cl.araucana.core.util.logging.LogManager;


/**
 * A process to produce and to consume PDF documents of a same type.
 *
 * <p>
 * A <b>PDFProcess</b> is composed of three main components: document model
 * provider, PDF producer and PDF consumer. These components are initialized
 * first and then executed in pipeline and wrapped in its respective worker
 * threads under {@link PDFService} supervision. This is the big picture:
 * </p>
 *
 * <p align="center">
 * <img src="{@docRoot}/extras/PDFProcess.gif">
 * </p>
 *
 * <p> Coupling between provider/producer and producer/consumer is through
 * production and consume requests queues.
 * </p>
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
 *            <TD> 22-04-2008 </TD>
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
public class PDFProcess implements Runnable {

	/**
	 * PDF Process was created (state).
	 */
	public static final int CREATED = 0;

	/**
	 * PDF Process was started (state).
	 */
	public static final int STARTED = 1;

	/**
	 * PDF Process was ended (state).
	 */
	public static final int ENDED   = 2;

	/**
	 * PDF Process was aborted (state).
	 */
	public static final int ABEND   = 3;

	/**
	 * PDF Process state names.
	 */
	public static final String[] stateNames = {
		"CREATED",
		"STARTED",
		"ENDED",
		"ABEND"
	};

	public static final String DEFAULT_NAME_PREFIX = "pdfserviceprocess";
		
	private static Logger logger = LogManager.getLogger();
	private static String namePrefix = DEFAULT_NAME_PREFIX;
	
	private PDFService service;
	private int pid;
	private int sessionID;
	private DocumentType docType;

	private String[] providerOptions;
	private String[] producerOptions;
	private String[] consumerOptions;

	private int state = CREATED;
	private int _state;
	private long beginTime = System.currentTimeMillis();
	private long endTime;
	private String owner;

	private int workUnits;
	private int processedWorkUnits;

	private Queue producerRequestQueue;
	private Queue consumerRequestQueue;

	private PDFThreadGroup processThreadGroup;
	private Thread monitorThread;

	private DocumentModelProvider provider;
	private PDFProducer producer;
	private PDFConsumer consumer;

	private PDFThread documentModelProviderThread;
	private PDFThread pdfProducerThread;
	private PDFThread pdfConsumerThread;

	private String abortMessage;
	private Throwable throwable;

	/**
	 * Collection of listeners of this process.
	 */
	protected List listeners = new ArrayList();

	/**
	 * Sets a prefix for threads group name of processes.
	 *
	 * @param namePrefix Name prefix.
	 */	
	public static void setNamePrefix(String prefix) {
		namePrefix = prefix;
	}
	
	/**
	 * Gets prefix for threads group name of processes.
	 *
	 * @return Name prefix.
	 */		
	public static String getNamePrefix() {
		return namePrefix;
	}
	
	/**
	 * Constructs a process instance to be executed by <code>service</code>.
	 * Process components will not have specified options.
	 *
	 * @param service Associated PDFService.
	 *
	 * @param pid Process ID.
	 *
	 * @param sessionID Session ID.
	 *
	 * @param docType Document type to process.
	 *
	 * @param owner Processs's owner.
	 *
	 * @throws PDFServiceException If cannot construct PDF Process.
	 */
	protected PDFProcess(PDFService service, int pid, int sessionID,
			DocumentType docType, String owner) throws PDFServiceException {

		this.service = service;
		this.pid = pid;
		this.sessionID = sessionID;
		this.docType = docType;
		this.owner = owner;

		producerRequestQueue =
				new Queue(docType.getProducerRequestQueueCapacity());

		consumerRequestQueue =
				new Queue(docType.getConsumerRequestQueueCapacity());

		/*
		 *  Creates base component instances for this process.
		 */
		try {
			Class baseClass;

			baseClass = docType.getDocumentModelProviderClass();
			provider = (DocumentModelProvider) baseClass.newInstance();

			baseClass = docType.getPdfProducerClass();
			producer = (PDFProducer) baseClass.newInstance();

			baseClass = docType.getPdfConsumerClass();
			consumer = (PDFConsumer) baseClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets associated PDFService.
	 *
	 * @return Associated PDFService.
	 */
	public PDFService getService() {
		return service;
	}

	/**
	 * Gets process ID.
	 *
	 * @return Process ID.
	 */
	public int getPID() {
		return pid;
	}

	/**
	 * Gets document type.
	 *
	 * @return Document type.
	 */
	public DocumentType getDocumentType() {
		return docType;
	}

	/**
	 * Gets process owner.
	 *
	 * @return Process owner.
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Sets provider process component options.
	 * @param options provider process component options.
	 */
	public void setProviderOptions(String[] options) {
		providerOptions = options;
	}

	/**
	 * Gets provider process component options.
	 *
	 * @return provider process component options.
	 */
	public String[] getProviderOptions() {
		return providerOptions;
	}

	/**
	 * Sets producer process component options.
	 * @param options producer process component options.
	 */
	public void setProducerOptions(String[] options) {
		producerOptions = options;
	}

	/**
	 * Gets producer process component options.
	 *
	 * @return producer process component options.
	 */
	public String[] getProducerOptions() {
		return producerOptions;
	}

	/**
	 * Sets consumer process component options.
	 * @param options consumer process component options.
	 */
	public void setConsumerOptions(String[] options) {
		consumerOptions = options;
	}

	/**
	 * Gets consumer process component options.
	 *
	 * @return consumer process component options.
	 */
	public String[] getConsumerOptions() {
		return consumerOptions;
	}

	/**
	 * Gets provider process component.
	 *
	 * @return provider process component.
	 */
	public DocumentModelProvider getDocumentModelProvider() {
		return provider;
	}

	/**
	 * Gets producer process component.
	 *
	 * @return producer process component.
	 */
	public PDFProducer getPDFProducer() {
		return producer;
	}

	/**
	 * Gets consumer process component.
	 *
	 * @return consumer process component.
	 */
	public PDFConsumer getPDFConsumer() {
		return consumer;
	}

	/**
	 * Gets current process state.
	 *
	 * @return Current process state.
	 */
	public int getState() {
		return state;
	}

	/**
	 * Indicates if thos process is ended.
	 *
	 * @return <code>true</code> if process is ended, otherwise
	 *         <code>false</code>.
	 */
	public final boolean isEnded() {
		return state == ENDED || state == ABEND;
	}

	/**
	 * Gets process begin or start time.
	 *
	 * @return Process begin time.
	 *
	 * @see #endTime()
	 * @see #elapsedTime()
	 */
	public long beginTime() {
		return beginTime;
	}

	/**
	 * Gets process end time. If process is not ended or aborted then process
	 * end time will be <u>the epoch</u>.
	 *
	 * @return Process end time.
	 *
	 * @see #beginTime()
	 * @see #elapsedTime()
	 */
	public long endTime() {
		return endTime;
	}

	/**
	 * Gets elapsed process time in milliseconds.
	 *
	 * @return Elapsed process time. <code>0</code> is process state is
	 *         {@link #CREATED}.
	 *
	 *  @see #beginTime()
	 *  @see #elapsedTime()
	 */
	public long elapsedTime() {
		if (state == CREATED) {
			return 0L;
		}

		if (isEnded()) {
			return endTime - beginTime;
		}

		return System.currentTimeMillis() - beginTime;
	}

	/**
	 * Sets total process work units.
	 *
	 * @param units Total process work units.
	 *
	 * @see #getWorkUnits()
	 */
	public void setWorkUnits(int units) {
		workUnits = units;
	}

	/**
	 * Gets total process work units.
	 *
	 * @return Total process work units.
	 *
	 * @see #setWorkUnits(int)
	 */
	public int getWorkUnits() {
		return workUnits;
	}

	/**
	 * Sets current processed process work units. This property would be
	 * lower or equals to total process work units.
	 *
	 * @param units Current processed process work units.
	 *
	 * @see #getProcessedWorkUnits()
	 */
	public void setProcessedWorkUnits(int units) {
		processedWorkUnits = units;
	}

	/**
	 * Gets current processed process work units.
	 *
	 * @return units Current processed process work units.
	 *
	 * @see #setProcessedWorkUnits(int)
	 */
	public int getProcessedWorkUnits() {
		return processedWorkUnits;
	}

	/**
	 * Gets current process information.
	 *
	 * @return Current process information.
	 */
	public Info getInfo() {
		return new Info();
	}

	/**
	 * Gets uncaught process error or exception.
	 *
	 * @return Uncaught process error or exception, <code>null</code> if no
	 *         error or exception was thrown.
	 */
	public Throwable getThrowable() {
		return throwable;
	}

	/**
	 * Initializes each process component. This method will be called by
	 * associated PDFService.
	 *
	 * @throws PDFServiceException If cannot initializes process components.
	 */
	public void init() throws PDFServiceException {

		provider.init(this);
		producer.init(this);
		consumer.init(this);
	}

	/**
	 * Starts each process component's worker thread. It assumes that
	 * <code>init()</code> method was called previously.
	 */
	public synchronized void start() {
		String sPID = Padder.lpad(pid, 5, '0');
		
		/*
		 * Destroys PDFServiceProcess's empty threadGroups created previously.
		 */
		ThreadGroup currentThreadGroup =
				Thread.currentThread().getThreadGroup();

		int activeGroupsCount = currentThreadGroup.activeGroupCount();
		ThreadGroup[] childrenThreadGroups = new ThreadGroup[activeGroupsCount];
			
		currentThreadGroup.enumerate(childrenThreadGroups);
		
		String fullNamePrefix = namePrefix + "-";
		
		for (int i = 0; i < childrenThreadGroups.length; i++) {
			ThreadGroup threadGroup = childrenThreadGroups[i];
			
			if (threadGroup != null) {
				String threadGroupName = threadGroup.getName();
				
				if (threadGroupName.startsWith(fullNamePrefix)) {
					int activeThreadsCount = threadGroup.activeCount();
					
					if (activeThreadsCount == 0) {
						try {
							threadGroup.destroy();
						} catch (IllegalThreadStateException e) {}
					}
				}
			}
		}

		/*
		 * Creates PDFServiceProcess's ThreadGroup.
		 */
		String processThreadGroupName =
				namePrefix + "-" + sPID + " [" + docType.getName() + "]";
		
		processThreadGroup = new PDFThreadGroup(this, processThreadGroupName);

		documentModelProviderThread =
				new PDFThread(
						processThreadGroup,
						"provider-" + fullNamePrefix + sPID,
						provider);

		pdfProducerThread =
				new PDFThread(
						processThreadGroup,
						"producer-" + fullNamePrefix + sPID,
						producer);
		
		pdfConsumerThread =
				new PDFThread(
						processThreadGroup,
						"consumer-" + fullNamePrefix + sPID,
						consumer);

		beginTime = System.currentTimeMillis();
		state = STARTED;

		monitorThread = new Thread(processThreadGroup, this);

		monitorThread.setName("monitor-" + sPID);
		monitorThread.setDaemon(true);

		documentModelProviderThread.start();
		pdfProducerThread.start();
		pdfConsumerThread.start();

		monitorThread.start();
	}

	/**
	 * Requests end this process. This request is processed asynchronically.
	 */
	public void end() {
		stop(false, null);
	}

	/**
	 * Requests abort this process. This request is processed asynchronically.
	 */
	protected void abort() {
		state = ABEND;
		endTime = System.currentTimeMillis();
		abortMessage = "Initialization failed";
	}

	/**
	 * Aborts this process due to a uncaught process error or exception.
	 *
	 * @param thread Aborted worker thread.
	 *
	 * @param e uncaught error or exception
	 */
	public void abort(Thread thread, Throwable e) {
		this.throwable = e;

		stop(true, "Uncaught thrown by thread '" + thread.getName() + "'");

		logger.log(
				Level.SEVERE,
				">< Uncaught thrown by thread '" + thread.getName() + "'",
				e);

		logger.severe("[" + pid + "] " + JVM.memoryReport());
	}

	/**
	 * Requests abort this process. This request is processed asynchronically.
	 *
	 * @param message Reason to abort.
	 */
	public void abort(String message) {
		stop(true, message);
	}

	private void stop(boolean abort, String message) {
		if (isEnded()) {
			return;
		}

		if (abort) {
			_state = ABEND;
			abortMessage = message;
		}

		documentModelProviderThread.interrupt();
		pdfProducerThread.interrupt();
		pdfConsumerThread.interrupt();
	}

	/**
	 * Inserts a production request in producer's queue.
	 *
	 * @param request Production request.
	 *
	 * @throws InterruptedException If another worker thread
	 *         has interrupted the current one.
	 *
	 * @see #getProduceRequest()
	 */
	public void putProduceRequest(PDFRequest request)
			throws InterruptedException {

		producerRequestQueue.put(request);
	}

	/**
	 * Extracts a production request from producer's queue.
	 *
	 * @return Production request.
	 *
	 * @throws InterruptedException If another worker thread
	 *         has interrupted the current one.
	 *
	 * @see #putProduceRequest(PDFRequest)
	 */
	public PDFRequest getProduceRequest()
			throws InterruptedException {

		return (PDFRequest) producerRequestQueue.get();
	}

	/**
	 * Inserts a consume request in producer's queue.
	 *
	 * @param request Consume request.
	 *
	 * @throws InterruptedException If another worker thread
	 *         has interrupted the current one.
	 *
	 * @see #getConsumeRequest()
	 */
	public void putConsumeRequest(PDFRequest request)
			throws InterruptedException {

		consumerRequestQueue.put(request);
	}

	/**
	 * Extracts a consume request from consumer's queue.
	 *
	 * @return Consume request.
	 *
	 * @throws InterruptedException If another worker thread
	 *         has interrupted the current one.
	 *
	 * @see #putConsumeRequest(PDFRequest)
	 */
	public PDFRequest getConsumeRequest()
			throws InterruptedException {

		return (PDFRequest) consumerRequestQueue.get();
	}

	/**
	 * Monitors execution process worker threads. Updates process state when
	 * is necessary and releases allocated resources when process is finished.
	 *
	 * <p> Process listeners are notified too.
	 * </p>
	 */
	public void run() {
		try {
			do {
				documentModelProviderThread.join(100);
			} while (documentModelProviderThread.isAlive());

			do {
				pdfProducerThread.join(100);
			} while (pdfProducerThread.isAlive());

			do {
				pdfConsumerThread.join(100);
			} while (pdfConsumerThread.isAlive());
		} catch (InterruptedException e) {}

		endTime = System.currentTimeMillis();

		String message;

		if (_state == ABEND) {
			state = ABEND;
			message = "aborted";
		} else {
			state = ENDED;
			message = "ended";
		}

		if (listeners.size() > 0) {
			PDFProcessEvent event =
					new PDFProcessEvent(pid, state == ABEND, message);

			for (int i = 0; i < listeners.size(); i++) {
				PDFProcessListener listener =
						(PDFProcessListener) listeners.get(i);

				if (state == ENDED) {
					listener.processEnded(event);
				} else {
					listener.processAborted(event);
				}
			}
		}

		if (state == ENDED) {
			logger.info("[" + pid + "] Ended.");
		} else {
			logger.info(
					"[" + pid + "] Aborted. [cause=\"" + abortMessage + "\"]");
		}

		/*
		 * Critical memory release.
		 */
		producerRequestQueue.release();
		consumerRequestQueue.release();

		provider.destroy();
		producer.destroy();
		consumer.destroy();

		processThreadGroup = null;

		monitorThread = null;
		documentModelProviderThread = null;
		pdfProducerThread = null;
		pdfConsumerThread = null;

		provider = null;
		producer = null;
		consumer = null;

		producerRequestQueue = null;
		consumerRequestQueue = null;

		listeners.clear();

		listeners = null;

		logger.info("[" + pid + "] " + JVM.memoryReport());
	}

	/**
	 * Encapsulates detailed process information caught in a instant of time.
	 * This class permits to monitor the progress of a process in the time.
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
	 *            <TD> 22-04-2008 </TD>
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
	public class Info {

		/**
		 *  Process ID.
		 */
		public int pid;

		/**
		 *  Process session ID.
		 */
		public int sessionID;

		/**
		 *  Process document type.
		 */
		public String docTypeName;

		/**
		 *  Process owner.
		 */
		public String owner;

		/**
		 *  Process state.
		 */
		public int state;

		/**
		 * Provider process component's options.
		 */
		public String[] providerOptions;

		/**
		 * Producer process component's options.
		 */
		public String[] producerOptions;

		/**
		 * Consumer process component's options.
		 */
		public String[] consumerOptions;

		/**
		 * Process begin time (based on the epoch).
		 */
		public long beginTime;

		/**
		 * Process end time (based on the epoch).
		 */
		public long endTime;

		/**
		 * Process execution elapsed time in milliseconds.
		 */
		public long elapsedTime;

		/**
		 * Process total work units.
		 */
		public int workUnits;

		/**
		 * Process processed work units.
		 */
		public int processedWorkUnits;

		/**
		 * Production requests queue size.
		 */
		public int producerRequestQueueSize;

		/**
		 * Consume requests queue size.
		 */
		public int consumerRequestQueueSize;

		/**
		 * Production requests queue capacity.
		 */
		public int producerRequestQueueCapacity;

		/**
		 * Consume requests queue capacity.
		 */
		public int consumerRequestQueueCapacity;

		/**
		 * Abort cause message whn process was aborted.
		 */
		public String abortMessage;

		/**
		 * Uncaught process error or exception.
		 */
		public Throwable throwable;

		private Info() {
			pid = PDFProcess.this.pid;
			sessionID = PDFProcess.this.sessionID;

			docTypeName = docType.getName();

			owner = PDFProcess.this.owner;
			state = PDFProcess.this.state;

			providerOptions =
					(String[]) Array.clone(PDFProcess.this.providerOptions);

			producerOptions =
					(String[]) Array.clone(PDFProcess.this.producerOptions);

			consumerOptions =
					(String[]) Array.clone(PDFProcess.this.consumerOptions);

			beginTime = PDFProcess.this.beginTime;
			endTime = PDFProcess.this.endTime;
			elapsedTime = elapsedTime();

			workUnits = PDFProcess.this.workUnits;
			processedWorkUnits = PDFProcess.this.processedWorkUnits;

			try {
				producerRequestQueueSize = producerRequestQueue.size();

				producerRequestQueueCapacity =
						producerRequestQueue.capacity();
			} catch (NullPointerException e) {
				// Current process has finished.
			}

			try {
				consumerRequestQueueSize = consumerRequestQueue.size();
				consumerRequestQueueCapacity = consumerRequestQueue.capacity();
			} catch (NullPointerException e) {
				// Current process has finished.
			}

			abortMessage = PDFProcess.this.abortMessage;
			throwable = PDFProcess.this.throwable;
		}

		/**
		 * Indicates if process is finished (aborted or ended states).
		 *
		 * @return <code>true</code> when process finished, otherwise
		 *         <code>false</code>.
		 */
		public boolean isEnded() {
			return state == ENDED || state == ABEND;
		}
	}
}
