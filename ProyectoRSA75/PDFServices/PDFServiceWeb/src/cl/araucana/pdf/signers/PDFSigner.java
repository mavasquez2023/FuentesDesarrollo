

/*
 * @(#) PDFSigner.java    1.0 20-06-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.pdf.signers;


import java.io.IOException;

import java.lang.reflect.Constructor;

import cl.araucana.core.util.Property;

import cl.araucana.fpg.PDFDocument;


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
public abstract class PDFSigner {

	protected Signer signer;
	protected SignOptions options;

	public static PDFSigner newPDFSigner(SignOptions options)
			throws PDFSignerException {

		if (options == null) {
			throw new PDFSignerException(
					"Signer options must be specified.");
		}

		Signer signer = options.getSigner();

		if (signer == null) {
			throw new PDFSignerException("Undefined signer.");
		}

		try {
			Class clazz = signer.getType();

			Constructor constructor =
					clazz.getConstructor(new Class[] { SignOptions.class });

			PDFSigner pdfSigner =
					(PDFSigner) constructor.newInstance(
							new Object[] { options });

			return pdfSigner;
		} catch (Exception e) {
			throw new PDFSignerException(
					"Cannot create new pdf signer instance.", e);
		}
	}

	protected PDFSigner(SignOptions options) throws PDFSignerException {

		this.options = options;

		signer = options.getSigner();

		if (signer == null) {
			throw new PDFSignerException("Undefined signer.");
		}
	}

	protected String getPropertyValue(String name)
			throws PDFSignerException {

		return getPropertyValue(name, null);
	}

	protected String getPropertyValue(String name, String defaultValue)
			throws PDFSignerException {

		Property property = signer.getProperty(name);

		if (property == null) {
			if (defaultValue == null) {
				throw new PDFSignerException(
						"Missed property '" + name + "'.");
			}

			return defaultValue;
		}

		return property.getValue();
	}

	public boolean supportsFPG() {
		return false;
	}

	public abstract void sign(PDFDocument document, String title, String reason)
			throws PDFSignerException;

	public abstract void sign(String pdfFileName, String title, String reason)
			throws PDFSignerException, IOException;
}
