

/*
 * @(#) BFOPDFSigner.java    1.0 23-06-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.signers.implementations.faceless;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import java.security.KeyStore;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.faceless.pdf2.Form;
import org.faceless.pdf2.FormSignature;
import org.faceless.pdf2.PDF;
import org.faceless.pdf2.PDFReader;
import org.faceless.pdf2.SignatureHandlerFactory;

import cl.araucana.core.util.UserPrincipal;
import cl.araucana.core.util.logging.LogManager;

import cl.araucana.fpg.PDFDocument;

import cl.araucana.pdf.signers.PDFSigner;
import cl.araucana.pdf.signers.PDFSignerException;
import cl.araucana.pdf.signers.SignOptions;


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
 *            <TD> 23-06-2008 </TD>
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
public class BFOPDFSigner extends PDFSigner {

	private static Logger logger = LogManager.getLogger();

	private String handlerFactoryType;
	private KeyStore keyStore;
	private char[] keyStorePassword;
	private String alias;
	private char[] aliasPassword;

	private String logID;

	private SignatureHandlerFactory handlerFactory;

	public BFOPDFSigner(SignOptions options) throws PDFSignerException {

		super(options);

		logID = options.getLogID();

		if (logID == null) {
			logID = "";
		}

		String licenseKey = getPropertyValue("licenseKey");

		try {
			UserPrincipal userPrincipal =
					UserPrincipal.decodeUserCredentials(licenseKey);

			licenseKey = userPrincipal.getName() + userPrincipal.getPassword();
		} catch (Exception e) {
			throw new PDFSignerException("Invalid licenseKey.");
		}

		checkLicenseKey(licenseKey);

		logger.info(logID + "PDF BigFaceless Library Version: " + PDF.VERSION);

		String requiredBFOVersion = getPropertyValue("requiredBFOVersion");

		if (!PDF.VERSION.equals(requiredBFOVersion)) {
			String message =
					  "Mismatch BFO versions "
					+ "(found='" + PDF.VERSION + "'"
					+ ", "
					+ "required='" + requiredBFOVersion + "')";

			logger.severe(logID + message);

			throw new PDFSignerException(message);
		}

		String keystoreCredentials = getPropertyValue("keyStoreUserPrincipal");
		String aliasCredentials = getPropertyValue("aliasUserPrincipal");

		try {
			UserPrincipal userPrincipal =
					UserPrincipal.decodeUserCredentials(keystoreCredentials);

			if (!userPrincipal.getName().equals("keystore")) {
				throw new Exception();
			}

			keyStorePassword = userPrincipal.getPassword().toCharArray();
		} catch (Exception e) {
			throw new PDFSignerException("Invalid keystore credentials.");
		}

		try {
			UserPrincipal userPrincipal =
					UserPrincipal.decodeUserCredentials(aliasCredentials);

			alias = userPrincipal.getName();
			aliasPassword = userPrincipal.getPassword().toCharArray();
		} catch (Exception e) {
			throw new PDFSignerException("Invalid alias credentials.");
		}

		handlerFactoryType = getPropertyValue("handlerFactoryType");

		if (handlerFactoryType.equals("selfsign")) {
			handlerFactory = FormSignature.HANDLER_SELFSIGN;
		} else if (handlerFactoryType.equals("acrobat6")) {
			handlerFactory = FormSignature.HANDLER_ACROBATSIX;
		} else {
			String message =
					  "Unknown handler factory type "
					+ "'" + handlerFactoryType +  "'.";

			logger.severe(logID + message);

			throw new PDFSignerException(message);
		}

		String keyStoreFileName = getPropertyValue("keyStore");
		FileInputStream keyStoreInput = null;

		try {
			keyStore = KeyStore.getInstance("JKS");
			keyStoreInput = new FileInputStream(keyStoreFileName);

			keyStore.load(keyStoreInput, keyStorePassword);
		} catch (Exception e) {
			if (keyStoreInput != null) {
				try {
					keyStoreInput.close();
				} catch (IOException e1) {}
			}

			String message =
					  "Keystore initialization for "
					+ "'" + keyStoreFileName + "' failed";

			logger.log(
					Level.SEVERE,
					logID + ">< " + message + ":",
					e);

			throw new PDFSignerException(message);
		}

		logger.config(logID + "Configuration:");
		logger.config(logID + "    requiredBFOVersion = " + requiredBFOVersion);
		logger.config(logID + "    handlerFactoryType = " + handlerFactoryType);
		logger.config(logID + "    keyStore           = " + keyStoreFileName);
		logger.config(logID + "    alias              = " + alias);

		logger.config(logID + "Initialization completed.");
	}

	public boolean supportsFPG() {
		return false;
	}

	public void sign(PDFDocument document, String title, String reason)
			throws PDFSignerException {

		throw new UnsupportedOperationException();
	}

	public void sign(String pdfFileName, String title, String reason)
			throws PDFSignerException, IOException {

		String signedPdfFileName = pdfFileName + ".signed.pdf";
		File inputFile = new File(pdfFileName);
		File outputFile = new File(signedPdfFileName);
		FileInputStream input = null;
		FileOutputStream output = null;
		boolean signed = false;

		try {
			input = new FileInputStream(inputFile);
			output = new FileOutputStream(outputFile);

			FormSignature signature =
					new FormSignature(
							keyStore,
							alias,
							aliasPassword,
							handlerFactory);

			if (reason != null) {
				signature.setReason(reason);
			}

			PDFReader reader = new PDFReader(input);
			PDF pdf = new PDF(reader);
			Form form = pdf.getForm();

			if (title == null) {
				title = "Advanced Digital Signature";
			}

			form.addElement(title, signature);
			pdf.render(output);

			signed = true;
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			String message =
					"Document '" +  pdfFileName +"' couldn't be signed.";

			logger.log(Level.SEVERE, logID + ">< " + message);

			throw new PDFSignerException(message);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e2) {}
			}

			if (output != null) {
				try {
					output.close();
				} catch (IOException e2) {}
			}

			if (!signed) {
				outputFile.delete();
			} else {
				inputFile.delete();
				outputFile.renameTo(inputFile);
			}
		}
	}

	private void checkLicenseKey(String licenseKey) throws PDFSignerException {

		/*
		 * Uses standard error redirection to catch messages from BFO.
		 */
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		PrintStream stdErr = System.err;
		PrintStream temporalStdErr = new PrintStream(output);

		System.setErr(temporalStdErr);
		PDF.setLicenseKey(licenseKey);
		System.setErr(stdErr);
		temporalStdErr.close();

		byte[] buffer = output.toByteArray();

		if (buffer.length > 0) {
			String message = "Invalid BFO PDF Library License.";

			logger.severe(logID + ">< " + message);

			throw new PDFSignerException(message);
		}
	}
}
