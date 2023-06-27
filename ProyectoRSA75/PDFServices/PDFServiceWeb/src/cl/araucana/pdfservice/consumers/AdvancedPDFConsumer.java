

/*
 * @(#) AdvancedPDFConsumer.java    1.0 30-08-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice.consumers;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.text.DecimalFormat;

import java.util.HashMap;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;

import cl.araucana.core.util.DirectoryCleaner;
import cl.araucana.core.util.Padder;
import cl.araucana.core.util.logging.LogManager;

import cl.araucana.fpg.DocumentModel;
import cl.araucana.fpg.FPGException;
import cl.araucana.fpg.PDFDocument;
import cl.araucana.fpg.PDFTemplate;

import cl.araucana.pdf.publishers.BadDocIndexPDFPublisherException;
import cl.araucana.pdf.publishers.PDFPublisher;
import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.Publisher;
import cl.araucana.pdf.publishers.PublisherManager;
import cl.araucana.pdf.publishers.PublishOptions;
import cl.araucana.pdf.publishers.Scope;

import cl.araucana.pdf.signers.PDFSigner;
import cl.araucana.pdf.signers.PDFSignerException;
import cl.araucana.pdf.signers.Signer;
import cl.araucana.pdf.signers.SignerManager;
import cl.araucana.pdf.signers.SignOptions;

import cl.araucana.pdfservice.Constants;
import cl.araucana.pdfservice.PDFConsumer;
import cl.araucana.pdfservice.PDFProcess;
import cl.araucana.pdfservice.PDFRequest;
import cl.araucana.pdfservice.PDFServiceException;


/**
 * Abstract advanced PDF consumer that supports signing, saving and publishing
 * PDF documents.
 * 
 * <p> Filename for each saved PDF document will be a unique sequence number
 * (starting from 1 per every <i>FPG</i> production process) formatted to
 * 8 digits of width and zeroes left padding. This behaviour can be changed
 * overridden {@link #getDocBaseName(PDFDocument, DocumentModel)} method.
 * </p>
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
 *            <b>
 * <pre>
 * [-nosave | [-clean] -outputDir &lt;outputDir&gt]
 * [-nosign | -signer &lt;signerName&gt]
 * [-nopublish | [ [-publisher &lt;publisherName&gt]
 *                 [-noreplace | -unpublishAll | -strategy &lt;strategy&gt]
 *                 [-remark &lt;remark&gt]
 *                 [-nologged]
 *               ]
 * </pre>
 *            </b>
 *        </td>
 *     </tr>
 * </TABLE>
 * 
 * <BR>
 * 
 * <p> This PDF consumer support the following options:
 * </p>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="90%" CELLPADDING="3" CELLSPACING="0">
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
 *        <td><strong>clean</strong></td>
 *        
 *        <td>
 *            Cleans synchorizedly output directory in a separated thread.
 *            It's optional but very recommended to avoid mixed PDF documents. 
 *        </td>
 *        
 *        <td>
 *            No cleans.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>outputDir</strong></td>
 *        
 *        <td>
 *            Output directory to save PDF documents. Output directory must be
 *            specified.
 *        </td>
 *        
 *        <td>
 *            &nbsp;
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>nosave</strong></td>
 *        
 *        <td>
 *            Specifies that PDF documents will not be saved to a output
 *            directory. However, some PDF signers and/or PDF publishers
 *            can require to save PDF documents previously to sign and/or
 *            publish them. This PDF consumer will report a message error
 *            when an conflict occurs with others consumer options.
 *        </td>
 *        
 *        <td>
 *            Save PDF documents to output directory.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>signer</strong></td>
 *        
 *        <td>
 *            Specifies a PDF signer name to sign PDF documents. This
 *            name can be <b>none</b> that will be equivalent to <b>nosign</b>
 *            option (see {@link cl.araucana.pdf.signers.SignerManager} to more
 *            information about PDF signers). This PDF consumer will report a
 *            message error when an conflict occurs with others consumer
 *            options. 
 *        </td>
 *        
 *        <td>
 *            Sign using default PDF signer.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>nosign</strong></td>
 *        
 *        <td>
 *            PDF documents no must be signed.
 *        </td>
 *        
 *        <td>
 *            &nbsp;
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>publisher</strong></td>
 *        
 *        <td>
 *            Specifies a PDF publisher name to publish PDF
 *            documents. This name can be <b>none</b> that will be equivalent
 *            to <b>nopublish</b>
 *            option (see {@link cl.araucana.pdf.publishers.PublisherManager}
 *            to more information about PDF publishers). This PDF consumer will
 *            report a message error when an conflict occurs with others
 *            consumer options. 
 *        </td>
 *        
 *        <td>
 *            Sign using default PDF publisher.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>nopublish</strong></td>
 *        
 *        <td>
 *            PDF documents no must be published.
 *        </td>
 *        
 *        <td>
 *            &nbsp;
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>noreplace</strong></td>
 *        
 *        <td>
 *            This publication option specifies that previously published
 *            PDF documents in the publication scope no must be republished
 *            again. This option implies other to publish. This PDF consumer
 *            will report a message error when an conflict occurs with others
 *            consumer options. This option cannot be used together with
 *            <b>unpublishAll</b> one.
 *        </td>
 *        
 *        <td>
 *            Republish previously published PDF documents.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>unpublishAll</strong></td>
 *        
 *        <td>
 *            This publication option specifies that all previously published
 *            PDF documents in the publication scope must be unpublished
 *            previously to start publication itself. This PDF consumer
 *            will report a message error when an conflict occurs with others
 *            consumer options. This option implies other to publish. This
 *            option cannot be used together with <b>noreplace</b> one.
 *        </td>
 *        
 *        <td>
 *            Republish previously published PDF documents.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>strategy</strong></td>
 *        
 *        <td>
 *            This publication option specifies a strategy to orient the
 *            publication of PDF documents. Valid strategies are: <b>insert</b>
 *            and <b>update</b>. If a PDF publisher unsupports strategies of
 *            publication then this option will be ignored.  This option implies
 *            other to publish. This option cannot be used together with
 *            <b>noreplace</b> or <b>unpublishAll</b> ones. This PDF consumer
 *            will report a message error when an conflict occurs with others
 *            consumer options.
 *        </td>
 *        
 *        <td>
 *            <b>insert</b> strategy.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>remark</strong></td>
 *        
 *        <td>
 *            Specifies a publication remark. This option implies other to
 *            publish.
 *        </td>
 *        
 *        <td>
 *            Empty publication remark.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td><strong>nologged</strong></td>
 *        
 *        <td>
 *            Specifies that some warning messages about publication operation
 *            will not logged.
 *        </td>
 *        
 *        <td>
 *            Log all publication  warning messages. 
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
 *            <TD> 30-08-2008 </TD>
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
public abstract class AdvancedPDFConsumer implements PDFConsumer, Constants {

	/**
	 * Control flag to provides logged statistics about consumer process. This
	 * flag can be setted through
	 * <b>pdfservice.advancedPDFConsumer.reportStat</b> boolean system property.
	 */
	public static boolean reportStat =
			Boolean.getBoolean("pdfservice.advancedPDFConsumer.reportStat");

	/*
	 * Consume cases.
	 */
	private static final int SIGN_FPG_AND_PUBLISH_FPG         = 0;
	private static final int SIGN_FPG_AND_PUBLISH_NOT_FPG     = 1;
	private static final int SIGN_NOT_FPG_AND_PUBLISH_FPG     = 2;
	private static final int SIGN_NOT_FPG_AND_PUBLISH_NOT_FPG = 3;

	private static final int SIGN_FPG_ONLY                    = 4;
	private static final int SIGN_NOT_FPG_ONLY                = 5;

	private static final int PUBLISH_FPG_ONLY                 = 6;
	private static final int PUBLISH_NOT_FPG_ONLY             = 7;

	private static final int NO_SIGN_AND_NO_PUBLISH           = 8;

	private static Logger logger = LogManager.getLogger();

	/**
	 * Associated PDF process to this PDF consumer. 
	 */
	protected PDFProcess process;
	
	/**
	 *  Output directory to save PDF documents. 
	 */
	protected String outputDir;

	private boolean nosave;

	private Map parameterMap;

	private PDFSigner pdfSigner;
	private PDFPublisher pdfPublisher;

	private String logID;
	private int caseID;
	private boolean mandatorySave;

	private int templateContentSize;

	private	String signTitle = "<Sign Title>";
	private	String signReason = "<Sign Reason>";

	/**
	 * Constructs an empty advanced PDF consumer.
	 */	
	protected AdvancedPDFConsumer() {
	}
	
	private String usage(String message) throws PDFServiceException {

		String usage = "";

		if (message == null) {
			message = "";
		} else {
			message += "\n";
		}

		usage =
				  message
				+ "\n"
				+ "Syntax for consumer:\n\n"
				+ "        -consumer <save_options> "
				+ "<sign_options> <publish_options>\n"
				+ "\n"
				+ "    save options:\n\n"
				+ "        [-nosave | [-clean] -outputDir <outputDir>]\n"
				+ "\n"
				+ "    sign options:\n\n"
				+ "        [-nosign | -signer <signerName>]\n"
				+ "\n"
				+ "    publish options:\n\n"
				+ "        [-nopublish | [ [-publisher <publisherName>]\n"
				+ "                        [-noreplace | -unpublishAll | "
				+ "-strategy <strategy>]\n"
				+ "                        [-remark <remark>]\n"
				+ "                        [-nologged]\n"
				+ "                      ]]\n"
				+ "Defaults:\n\n"
				+ "     save:    No clean.\n"
				+ "     sign:    Sign using default signer.\n"
				+ "     publish: Publish using default publisher in "
				+ "replace and logged modes.\n"
				+ "\n"
				+ "Notes:\n"
				+ "     '-signer none' is same that -nosign\n"
				+ "     '-publisher none' is same that -nopublish\n";

		throw new PDFServiceException(usage);
	}

	public void init(PDFProcess process) throws PDFServiceException {

		logID = "[" + process.getPID() + "] ";

		logger.info(logID + "Initializing ...");

		this.process = process;

		String[] options = process.getConsumerOptions();

		boolean cleanupOutputDir = false;
		boolean sign = true;
		boolean publish = true;
		int replaceMode = PublishOptions.MODE_REPLACE;
		int strategy = PublishOptions.STRATEGY_INSERT;
		String remark = null;
		boolean logged = true;
		String signerName = null;
		String publisherName = null;

		nosave = false;

		/*
		 * Validates consumer's options.
		 */
		for (int i = 0; i < options.length; i++) {
			if (options[i].equals("-nosave")) {
				if (cleanupOutputDir) {
					usage(
							  "Incompatible options -nosave and -clean "
							+ "for consumer.");
				}

				nosave = true;
			} else if (options[i].equals("-clean")) {
				if (nosave) {
					usage(
							  "Incompatible options -clean and -nosave "
							+ "for consumer.");
				}

				cleanupOutputDir = true;
			} else if (options[i].equals("-outputDir")) {
				if (nosave) {
					usage(
							  "Incompatible options -outputDir and -nosave "
							+ "for consumer.");
				}

				if (i + 1 < options.length) {
					outputDir = options[++i];
				} else {
					usage("Missed output directory for consumer.");
				}
			} else if (options[i].equals("-nosign")) {
				if (signerName != null) {
					usage(
							  "Incompatible options -nosign and -signer "
							+ "for consumer.");
				}

				sign = false;
			} else if (options[i].equals("-signer")) {
				if (!sign) {
					usage(
							  "Incompatible options -signer and -nosign "
							+ "for consumer.");
				}

				if (i + 1 < options.length) {
					signerName = options[++i];
				} else {
					usage("Missed signer name for consumer.");
				}

				if (signerName.equals("none")) {
					signerName = null;
					sign = false;
				}
			} else if (options[i].equals("-nopublish")) {
				if (publisherName != null) {
					usage(
						  "-nopublish incompatible with publish options "
						+ "for consumer.");
				}

				publish = false;
			} else if (options[i].equals("-publisher")) {
				if (!publish) {
					usage(
							  "-publisher incompatible with "
							+ "-nopublish option for consumer.");
				}

				if (i + 1 < options.length) {
					publisherName = options[++i];
				} else {
					usage("Missed publisher name for consumer.");
				}

				if (publisherName.equals("none")) {
					publisherName = null;
					publish = false;
				}
			} else if (options[i].equals("-noreplace")) {
				if (!publish) {
					usage(
							  "-noreplace incompatible with "
							+ "-nopublish option for consumer.");
				}

				if (replaceMode == PublishOptions.MODE_UNPUBLISH_ALL) {
					usage(
							  "-noreplace incompatible with "
							+ "-unpublishAll option for consumer.");
				}

				replaceMode = PublishOptions.MODE_NO_REPLACE;
			} else if (options[i].equals("-unpublishAll")) {
				if (!publish) {
					usage(
							  "-unpublishAll incompatible with "
							+ "-nopublish option for consumer.");
				}

				if (replaceMode == PublishOptions.MODE_NO_REPLACE) {
					usage(
							  "-unpublishAll incompatible with "
							+ "-noreplace option for consumer.");
				}

				replaceMode = PublishOptions.MODE_UNPUBLISH_ALL;
			} else if (options[i].equals("-strategy")) {
				if (!publish) {
					usage(
							  "-strategy incompatible with "
							+ "-nopublish option for consumer.");
				}

				if (replaceMode != PublishOptions.MODE_REPLACE) {
					usage(
							  "-strategy option is valid "
							+ "-with replace mode only.");
				}

				if (i + 1 < options.length) {
					String sStrategy = options[++i];

					strategy = PublishOptions.getStrategy(sStrategy);

					if (strategy == -1) {
						usage("Invalid strategy '" + sStrategy + "'.");
					}
				} else {
					usage("Missed replace strategy for consumer.");
				}
			} else if (options[i].equals("-remark")) {
				if (!publish) {
					usage(
							  "-remark option incompatible with "
							+ "-nopublish option for consumer.");
				}

				if (i + 1 < options.length) {
					remark = options[++i];
				} else {
					usage("Missed remark for consumer.");
				}
			} else if (options[i].equals("-nologged")) {
				if (!publish) {
					usage(
							  "-nologged incompatible with "
							+ "-nopublish option for consumer.");
				}

				logged = false;
			} else {
				usage("Invalid syntax.");
			}
		}

		if (!nosave) {
			if (outputDir == null) {
				usage("-outputDir option is mandatory in save mode.");
			}
		}

		/*
		 * Gets options from producer.
		 */
		parameterMap = process.getPDFProducer().getParameterMap();

		PDFTemplate template = (PDFTemplate) parameterMap.get("template");

		if (template == null) {
			throw new PDFServiceException(
					"Unknown document type/version to consume.");
		}

		String docType = template.getDocType();
		int docVersion = template.getDocVersion();

		templateContentSize =
				  template.getFixedObjectsContent().length
				+ template.getFixedXRefContent().length;

		/*
		 * Reports consumer´s options.
		 */
		logger.config(logID + "Options:");

		if (!nosave) {
			logger.config(logID + "    save          = " + true);
			logger.config(logID + "    clean         = " + cleanupOutputDir);
			logger.config(logID + "    outputDir     = " + outputDir);
		} else {
			logger.config(logID + "    nosave        = " + true);
		}

		logger.config(logID + "    docType       = " + docType);
		logger.config(logID + "    docVersion    = " + docVersion);
		logger.config(logID + "    templateSize  = " + templateContentSize);

		/*
		 * PDF Signer initialization.
		 */
		Signer signer = null;

		if (sign) {
			try {
				SignerManager signerManager = SignerManager.getInstance();

				if (signerName == null) {
					signer = signerManager.getDefaultSigner();

					if (signer == null) {
						throw new PDFServiceException(
								"Undefined default signer.");
					}
				} else {
					signer = signerManager.getSigner(signerName);

					if (signer == null) {
						throw new PDFServiceException(
								"Unknown signer '" + signerName + "'.");
					}
				}

				SignOptions signOptions = new SignOptions();

				signOptions.setSigner(signer);
				signOptions.setLogID("[" + process.getPID() + "] ");

				pdfSigner = PDFSigner.newPDFSigner(signOptions);
			} catch (PDFSignerException e) {
				logger.log(Level.SEVERE, ">< Signer failed:", e);

				throw new PDFServiceException("Signer failed.", e);
			}
		}

		/*
		 * PDF Publisher initialization.
		 */
		Publisher publisher = null;

		if (publish) {
			try {
				PublisherManager manager = PublisherManager.getInstance();

				if (publisherName == null) {
					publisher = manager.getDefaultPublisher();

					if (publisher == null) {
						throw new PDFServiceException(
								"Undefined default publisher.");
					}
				} else {
					publisher = manager.getPublisher(publisherName);

					if (publisher == null) {
						throw new PDFServiceException(
								"Unknown publisher '" + publisherName + "'.");
					}
				}

				publisher.setPDFTemplate(template);

				PublishOptions publishOptions = new PublishOptions();

				publishOptions.setPublisher(publisher);
				publishOptions.setBatchMode(getBatchMode());
				publishOptions.setPartitioned(false);
				publishOptions.setCompressed(true);
				publishOptions.setReplaceMode(replaceMode);
				publishOptions.setStrategy(strategy);
				publishOptions.setLogged(logged);
				publishOptions.setLogID(process.getPID() + "");
				publishOptions.setRemark(remark);

				pdfPublisher =
						PDFPublisher.newPDFPublisher(
								docType, docVersion, publishOptions);

				/*
				 * Determines replace mode scope to publish.
				 */
				String[] names = getNamesForReplaceModeScope(process);
				Object[] values = getValuesForReplaceModeScope(process);

				Scope scope = pdfPublisher.getScope(names, values);

				logger.info(logID + "Publish Scope [" + scope + "]");

				pdfPublisher.setReplaceModeScope(scope);
			} catch (PDFPublisherException e) {
				logger.log(Level.SEVERE, ">< Publisher failed:", e);

				throw new PDFServiceException("Publisher failed.", e);
			}
		}

		setCaseID();

		logger.finest(logID + "caseID = " + caseID);

		if (mandatorySave) {
			if (nosave) {
				throw new PDFServiceException(
					  "Incompatible 'nosave' mode with specified "
					+ "signer and/or publisher.");
			}
		}

		/*
		 * Checks if exist output directory and cleanups it if is neccesary.
		 */
		if (outputDir != null) {
			File dir = new File(outputDir);

			if (!dir.exists()) {
				if (dir.mkdirs()) {
					logger.info(
							  logID
							+ "OutputDir '" + outputDir + "' was created.");

					cleanupOutputDir = false;
				} else {
					throw new PDFServiceException(
							"OutputDir '" + outputDir + "' cannot be created.");
				}
			}

			if (cleanupOutputDir) {
				if (DirectoryCleaner.renameAndCleanup(outputDir)) {
					logger.info(
							  logID
							+ "Cleaning up directory '" + outputDir + "' ...");
				} else {
					throw new PDFServiceException(
							"Cannot clean up output directory in consumer.");
				}
			}
		}

		parameterMap = new HashMap();

		if (signer != null) {
			parameterMap.put("signer", signer);
		}

		if (publisher != null) {
			parameterMap.put("publisher", publisher);
		}

		logger.info(logID + "Initialized.");
	}

	/**
	 * Indicates if this PDF producer will execute in batch mode.
	 * 
	 * @return <code>true</code>.
	 * 
	 * @throws PDFPublisherException If cannot get batch mode consumer.
	 */
	protected boolean getBatchMode() throws PDFPublisherException {
		return true;
	}

	/**
	 * Gets names from a specified {@link cl.araucana.pdfservice.PDFProcess}
	 * to be used for replace mode scope in publication operation
	 * (see {@link cl.araucana.pdf.publishers.Scope} and
	 * {@link cl.araucana.pdf.publishers.PDFPublisher} to more information
	 * about}.
	 * 
	 * @param process PDF Process to obtain names.
	 * 
	 * @return Array of names for replace mode scope.
	 * 
	 * @throws PDFServiceException If cannot get names for replace mode scope.
	 * 
	 * @see #getValuesForReplaceModeScope(PDFProcess)
	 */
	protected abstract String[] getNamesForReplaceModeScope(PDFProcess process)
			throws PDFServiceException;

	/**
	 * Gets values from a specified {@link cl.araucana.pdfservice.PDFProcess}
	 * to be used for replace mode scope in publication operation
	 * (see {@link cl.araucana.pdf.publishers.Scope} and
	 * {@link cl.araucana.pdf.publishers.PDFPublisher} to more information
	 * about}.
	 * 
	 * @param process PDF Process to obtain values.
	 * 
	 * @return Array of values for replace mode scope.
	 * 
	 * @throws PDFServiceException If cannot get names for replace mode scope.
	 * 
	 * @see #getNamesForReplaceModeScope(PDFProcess)
	 */
	protected abstract Object[] getValuesForReplaceModeScope(PDFProcess process)
			throws PDFServiceException;

	public Map getParameterMap() {
		return parameterMap;
	}

	/**
	 * Sets a common sign title to be included in each signed PDF documents.
	 * 
	 * @param title Common Sign title.
	 * 
	 * @see #getSignTitle()
	 */
	public void setSignTitle(String title) {
		signTitle = title;
	}

	/**
	 * Gets a common sign title to be included in each signed PDF documents.
	 * 
	 * @return Common Sign title.
	 * 
	 * @see #setSignTitle(String)
	 */
	public String getSignTitle() {
		return signTitle;
	}

	/**
	 * Sets a common sign reason to be included in each signed PDF documents.
	 * 
	 * @param reason Common Sign reason.
	 * 
	 * @see #getSignReason()
	 */	
	public void setSignReason(String reason) {
		signReason = reason;
	}

	/**
	 * Gets a common sign reason to be included in each signed PDF documents.
	 * 
	 * @return Common Sign reason.
	 * 
	 * @see #setSignReason(String)
	 */	
	public String getSignReason() {
		return signReason;
	}

	/**
	 * Consumes all PDF documents from consume requests queue saving, signing
	 * and/or publishing them according to consume options for this PDF
	 * consumer.
	 */
	public void run() {
		logger.info(logID + "Running ...");

		int consumedDocuments = 0;
		int badDocIndexDocuments = 0;

		PDFRequest request;
		String pdfFileName;
		boolean aborted = false;

		final int CONTENT_SIZES = 3;

		long[] totalContentSizes = new long[CONTENT_SIZES];

		try {
			if (pdfPublisher != null) {
				pdfPublisher.reset();
			}

			while ((request = process.getConsumeRequest()) != null) {
				try {

					/*
					 * Consumes PDF document.
					 */
					PDFDocument document =
							(PDFDocument) request.getParameter(
									PARAM_PDF_DOCUMENT);

					DocumentModel docModel =
							(DocumentModel)
									request.getParameter(PARAM_DOCUMENT_MODEL);

					switch (caseID) {
						case SIGN_FPG_AND_PUBLISH_FPG:
							pdfSigner.sign(document, signTitle, signReason);
							pdfPublisher.publish(document);

							if (!nosave) {
								saveDocument(document, docModel);
							}

							break;

						case SIGN_FPG_AND_PUBLISH_NOT_FPG:
							pdfSigner.sign(document, signTitle, signReason);

							pdfFileName = saveDocument(document, docModel);

							pdfPublisher.publish(
									document.getIndex(), pdfFileName);

							break;

						case SIGN_NOT_FPG_AND_PUBLISH_FPG:
						case SIGN_NOT_FPG_AND_PUBLISH_NOT_FPG:
							document.close();

							pdfFileName = saveDocument(document, docModel);

							pdfSigner.sign(pdfFileName, signTitle, signReason);

							pdfPublisher.publish(
									document.getIndex(), pdfFileName);

							break;

						case SIGN_FPG_ONLY:
							pdfSigner.sign(document, signTitle, signReason);

							if (!nosave) {
								saveDocument(document, docModel);
							}

							break;

						case SIGN_NOT_FPG_ONLY:
							document.close();

							pdfFileName = saveDocument(document, docModel);

							pdfSigner.sign(pdfFileName, signTitle, signReason);

							break;

						case PUBLISH_FPG_ONLY:
							document.close();
							pdfPublisher.publish(document);

							if (!nosave) {
								saveDocument(document, docModel);
							}

							break;

						case PUBLISH_NOT_FPG_ONLY:
							document.close();

							pdfFileName = saveDocument(document, docModel);

							pdfPublisher.publish(
									document.getIndex(), pdfFileName);

							break;

						case NO_SIGN_AND_NO_PUBLISH:
							document.close();

							if (!nosave) {
								saveDocument(document, docModel);
							}

							break;

						default:
							throw new InternalError(
									"Unexpected case id '" + caseID + "'.");
					}

					if (reportStat) {
						int[] contentSizes = document.getContentSizes();

						totalContentSizes[PDFDocument.FIXED_CONTENT] +=
								contentSizes[PDFDocument.FIXED_CONTENT];

						totalContentSizes[PDFDocument.VARIABLE_CONTENT] +=
								contentSizes[PDFDocument.VARIABLE_CONTENT];

						totalContentSizes[PDFDocument.TOTAL_CONTENT] +=
								contentSizes[PDFDocument.TOTAL_CONTENT];
					}

					document.release();
					process.setProcessedWorkUnits(++consumedDocuments);
				} catch (BadDocIndexPDFPublisherException e) {
					logger.severe(
							  logID
							+ "Bad docIndex '" + e.getMessage() + "'. "
							+ "Document was discarded.");

					badDocIndexDocuments++;

					continue;
				}
			} // publish loop.

			if (pdfPublisher != null) {
				pdfPublisher.flush();
			}

			logger.info(
					logID + "#consumed documents=" + consumedDocuments + ".");

			if (badDocIndexDocuments > 0) {
				logger.warning(
						  logID
						+ "#bad docindex documents="
						+ badDocIndexDocuments + ".");
			}

			/*
			 *  Reports documents content statistics.
			 */
			if (reportStat
					&& totalContentSizes[PDFDocument.TOTAL_CONTENT] > 0) {

				final int CONTENT_WIDTH = 12;

				double variablePercent =
						100.0 * totalContentSizes[PDFDocument.VARIABLE_CONTENT]
								/ totalContentSizes[PDFDocument.TOTAL_CONTENT];

				DecimalFormat percentDf = new DecimalFormat("#.00");

				logger.info(
						  logID + "Content Statistics [bytes]: "
						+ "    template        fixed     variable        total "
						+ "   %variable\n"
						+ logID + "docstat:                    "
						+ Padder.lpad(templateContentSize, CONTENT_WIDTH)
						+ " "
						+ Padder.lpad(
								totalContentSizes[
										PDFDocument.FIXED_CONTENT],
								CONTENT_WIDTH)
						+ " "
						+ Padder.lpad(
								totalContentSizes[
										PDFDocument.VARIABLE_CONTENT],
								CONTENT_WIDTH)
						+ " "
						+ Padder.lpad(
								totalContentSizes[
										PDFDocument.TOTAL_CONTENT],
								CONTENT_WIDTH)
						+ " "
						+ Padder.lpad(
								percentDf.format(variablePercent),
								CONTENT_WIDTH));
			}

			logger.info(logID + "Ended.");
		} catch (InterruptedException e) {
			logger.warning(logID + "Aborted.");

			aborted = true;
		} catch (Exception e) {
			logger.log(Level.SEVERE, logID + ">< Unexpected exception:", e);

			close(true);

			logger.severe(logID + "Aborted.");

			process.abort("Caught exception.");
		} finally {
			close(aborted);
		}
	}

	public void destroy() {
		process = null;
		pdfSigner = null;
		pdfPublisher = null;
	}

	private void close(boolean aborted) {
		if (pdfPublisher != null) {
			try {
				if (aborted) {
					pdfPublisher.discard();
				}
			} catch (PDFPublisherException e) {}

			try {
				pdfPublisher.close();
			} catch (PDFPublisherException e) {}
		}
	}

	private void setCaseID() {

		mandatorySave = false;

		/*
		 * SIGN + PUBLISH.
		 *
		 *                   +------------------+------------------+
		 *                   |                  |                  |
		 *                   |  PUBLISH FPG     |  PUBLISH ~FPG    |
		 *                   |                  |                  |
		 *      +------------+------------------+------------------+
		 *      |            |  sign(FPG)       |  sign(FPG)       |
		 *      |  SIGN FPG  |  publish(FPG)    |  save()          |
		 *      |            | [save()]         |  publish(saved)  |
		 *      +------------+------------------+------------------+
		 *      |            |  close()         |  close()         |
		 *      |            |  save()          |  save()          |
		 *      |  SIGN ~FPG |  sign(saved)     |  sign(saved)     |
		 *      |            |  publish(saved)  |  publish(saved)  |
		 *      +------------+------------------+------------------+
		 */
		if (pdfSigner != null && pdfPublisher != null) {
			if (pdfSigner.supportsFPG()) {
				if (pdfPublisher.supportsFeature("fpg")) {
					caseID = SIGN_FPG_AND_PUBLISH_FPG;
				} else {
					caseID = SIGN_FPG_AND_PUBLISH_NOT_FPG;
					mandatorySave = true;
				}
			} else {
				if (pdfPublisher.supportsFeature("fpg")) {
					caseID = SIGN_NOT_FPG_AND_PUBLISH_FPG;
				} else {
					caseID = SIGN_NOT_FPG_AND_PUBLISH_NOT_FPG;
				}

				mandatorySave = true;
			}

			return;
		}

		/*
		 * SIGN ONLY.
		 *
		 *      +------------+------------------+
		 *      |            |  sign(FPG)       |
		 *      |  SIGN FPG  |  [save()]        |
		 *      |            |                  |
		 *      +------------+------------------+
		 *      |            |  close()         |
		 *      |  SIGN ~FPG |  save()          |
		 *      |            |  sign(saved)     |
		 *      +------------+------------------+
		 */
		if (pdfSigner != null) {
			if (pdfSigner.supportsFPG()) {
				caseID = SIGN_FPG_ONLY;
			} else {
				caseID = SIGN_NOT_FPG_ONLY;
				mandatorySave = true;
			}

			return;
		}

		/*
		 * PUBLISH ONLY.
		 *
		 *      +---------------+------------------+
		 *      |               |  close()         |
		 *      |  PUBLISH FPG  |  publish(FPG)    |
		 *      |               | [save()]         |
		 *      +---------------+------------------+
		 *      |               |  close()         |
		 *      |  PUBLISH ~FPG |  save()          |
		 *      |               |  publish(saved)  |
		 *      +---------------+------------------+
		 */
		if (pdfPublisher != null) {
			if (pdfPublisher.supportsFeature("fpg")) {
				caseID = PUBLISH_FPG_ONLY;
			} else {
				caseID = PUBLISH_NOT_FPG_ONLY;
				mandatorySave = true;
			}

			return;
		}

		/*
		 * NO SIGN AND NO PUBLISH.
		 */
		caseID = NO_SIGN_AND_NO_PUBLISH;
	}

	private String saveDocument(PDFDocument document, DocumentModel docModel)
			throws IOException, FPGException {

		String pdfFileName =
					outputDir
				  + "/"
				  + getDocBaseName(document, docModel)
				  + ".pdf";

		FileOutputStream output = null;

		try {
			output = new FileOutputStream(pdfFileName);

			document.writeTo(output);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {}
			}
		}

		return pdfFileName;
	}

	/**
	 * Determines an unique document base name to save in function of
	 * specified PDF document and its asocciated document model.
	 * 
	 * @param document Document base name to be saved.
	 * 
	 * @param docModel Document Model.
	 * 
	 * @return Document 
	 */	
	protected String getDocBaseName(PDFDocument document,
			DocumentModel docModel) {

		return Padder.lpad(document.getSequenceNumber(), 8, '0');
	}
}
