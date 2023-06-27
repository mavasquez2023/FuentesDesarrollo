

/*
 * @(#) FPGPDFSigner.java    1.0 20-06-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.pdf.signers.implementations.fpg;


import java.security.MessageDigest;

import java.util.logging.Level;
import java.util.logging.Logger;

import cl.araucana.core.util.UserPrincipal;
import cl.araucana.core.util.logging.LogManager;
import cl.araucana.core.util.security.pkcs7.PKCS7Signer;

import cl.araucana.fpg.PDFDocument;
import cl.araucana.fpg.PDFTemplate;

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
 *            <TD align="center"> <B>Versi�n</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripci�n</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 20-06-2008 </TD>
 *            <TD align="center">  2.0 </TD>
 *            <TD> Germ�n Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versi�n inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Germ�n Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class FPGPDFSigner extends PDFSigner {

	private static Logger logger = LogManager.getLogger();

	private PKCS7Signer pkcs7Signer;
	private PDFTemplate template;
	private MessageDigest baseMessageDigest;

	private String logID;

	public FPGPDFSigner(SignOptions options) throws PDFSignerException {

		super(options);

		String storeType = getPropertyValue("storeType");
		String keyStore = getPropertyValue("keyStore");
		String keyStoreCredentials = getPropertyValue("keyStoreUserPrincipal");
		String aliasCredentials = getPropertyValue("aliasUserPrincipal");

		String digestAlgorithm =
				getPropertyValue("digestAlgorithm", "MD5");

		String signAlgorithm =
				getPropertyValue("signAlgorithm", "MD5withRSA");

		String keyStorePassword = null;
		String alias = null;
		String aliasPassword = null;

		try {
			UserPrincipal userPrincipal =
					UserPrincipal.decodeUserCredentials(keyStoreCredentials);

			if (!userPrincipal.getName().equals("keystore")) {
				throw new Exception();
			}

			keyStorePassword = userPrincipal.getPassword();
		} catch (Exception e) {
			throw new PDFSignerException("Invalid keystore credentials.");
		}

		try {
			UserPrincipal userPrincipal =
					UserPrincipal.decodeUserCredentials(aliasCredentials);

			alias = userPrincipal.getName();
			aliasPassword = userPrincipal.getPassword();
		} catch (Exception e) {
			throw new PDFSignerException("Invalid alias credentials.");
		}

		logID = options.getLogID();

		if (logID == null) {
			logID = "";
		}

		logger.config(logID + "Configuration:");
		logger.config(logID + "    storeType       = " + storeType);
		logger.config(logID + "    keyStore        = " + keyStore);
		logger.config(logID + "    alias           = " + alias);
		logger.config(logID + "    digestAlgorithm = " + digestAlgorithm);
		logger.config(logID + "    signAlgorithm   = " + signAlgorithm);

		try {
			pkcs7Signer = PKCS7Signer.getInstance(
					storeType,
					keyStore,
					keyStorePassword,
					alias,
					aliasPassword,
					digestAlgorithm,
					signAlgorithm);
		} catch(Exception e) {
			logger.log(Level.SEVERE, logID + ">< Initialization failed:", e);

			throw new PDFSignerException("Initialization failed.", e);
		}

		logger.config(logID + "Initialization completed.");
	}

	public boolean supportsFPG() {
		return true;
	}

	public void sign(PDFDocument document, String title, String reason)
			throws PDFSignerException {

		PDFTemplate template = document.getTemplate();

		/*
		 *  Determines if it's a different PDF template
		 *  to sign its fixed objects content.
		 */
		if (this.template != template) {
			sign(template);
		}

		try {
			MessageDigest messageDigester =
					(MessageDigest) baseMessageDigest.clone();

			pkcs7Signer.setMessageDigest(messageDigester);
			document.sign(pkcs7Signer, title, reason);
		} catch (Exception e) {
			logger.log(
					Level.SEVERE,
					  logID
					+ " >< Document '" + document.getID() + "' "
					+ "couldn't be signed.",
					e);

			throw new PDFSignerException(
					"Document '" + document.getID() + "' couldn't be signed.",
					e);
		}
	}

	public void sign(String pdfFileName, String title, String reason) {
		throw new UnsupportedOperationException();
	}

	private void sign(PDFTemplate template) throws PDFSignerException {

		if (!template.isFreezed()) {
			logger.severe(
					  logID
					+ "PDF template '" + template.getFullName() + "' "
					+ "not freezed.");

			throw new PDFSignerException(
					  "PDF template '" + template.getFullName() + "' "
					+ "not freezed.");
		}

		byte[] fixedObjectsContent = template.getFixedObjectsContent();

		pkcs7Signer.sign(fixedObjectsContent);

		baseMessageDigest = pkcs7Signer.getMessageDigest();

		this.template = template;
	}
}
