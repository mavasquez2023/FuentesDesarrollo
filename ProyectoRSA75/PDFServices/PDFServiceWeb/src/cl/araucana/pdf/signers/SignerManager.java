

/*
 * @(#) SignerManager.java    1.0 22-06-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.signers;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java.util.Map;
import java.util.TreeMap;

import cl.araucana.core.util.Property;
import cl.araucana.core.util.XClass;

import cl.araucana.core.util.xml.XMLParserException;
import cl.araucana.core.util.xml.XMLPropertiesParser;


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
 *            <TD> 22-06-2008 </TD>
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
public class SignerManager {

	public static final String XML_DOC_SIGNERS = "/etc/pdf/signers.xml";

	private static SignerManager instance;

	private Map signers = new TreeMap();
	private Signer defaultSigner;

	public static synchronized SignerManager getInstance()
			throws PDFSignerException {

		if (instance == null) {
			instance = new SignerManager();
		}

		return instance;
	}

	public String[] getSignerNames() {
		return (String[]) signers.keySet().toArray(new String[0]);
	}

	public Signer getSigner(String name) {
		return (Signer) signers.get(name);
	}

	public Signer getDefaultSigner() {
		return defaultSigner;
	}

	private SignerManager() throws PDFSignerException {

		InputStream resourceInput =
				getClass().getResourceAsStream(XML_DOC_SIGNERS);

		if (resourceInput == null) {
			throw new PDFSignerException(
					"Cannot find resource '" + XML_DOC_SIGNERS + "'.");
		}

		Reader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(resourceInput));
			XMLPropertiesParser parser = new XMLPropertiesParser(reader, false);

			parser.setXMLDocumentName(XML_DOC_SIGNERS);
			parser.parse();
			checkSigners(parser);
		} catch (Exception e) {
			throw new PDFSignerException(
					"Parsing resource '" + XML_DOC_SIGNERS + "' failed.", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch(Exception e) {}
			}
		}
	}

	private void checkSigners(XMLPropertiesParser parser)
			throws XMLParserException {

		int signersCount = parser.getPropertyCount("signers.signer");

		for (int i = 0; i < signersCount; i++) {
			String $signer = "signers.signer[" + i + "]";
			String name = parser.getStringProperty($signer + ".name");

			/*
			 * Checks duplicated signer names.
			 */
			Signer signer = (Signer) signers.get(name);

			if (signer != null) {
				throw new XMLParserException(
						"duplicated signer '" + name + "'.");
			}

			String description =
					parser.getStringProperty($signer + ".description", "");

			String className = parser.getStringProperty($signer + ".class");

			/*
			 *  Checks specified class.
			 */
			Class pdfSignerClass = null;

			try {
				XClass pdfSignerXClass = new XClass(PDFSigner.class);

				pdfSignerClass =
						pdfSignerXClass.getSubClass(className, true);
			} catch (ClassNotFoundException e) {
				throw new XMLParserException(e.getMessage());
			}

        	signer = new Signer();

			signer.setName(name);
			signer.setDescription(description);
			signer.setType(pdfSignerClass);

			boolean defaultOne =
					parser.getBooleanProperty(
							$signer + ".default", new Boolean(false));

			if (defaultOne) {
				if (defaultSigner == null) {
					defaultSigner = signer;
				} else {
					throw new XMLParserException(
							"Too many default signers.");
				}
			}

			signer.setDefault(defaultOne);

			int signerPropertyCount =
					parser.getPropertyCount(
							$signer + ".properties.property");

			for (int j = 0; j < signerPropertyCount; j++) {
				String $property =
						$signer + ".properties.property[" + j + "]";

				String propertyName =
						parser.getStringProperty($property + ".name");

				String propertyValue =
						parser.getStringProperty($property + ".value");

				String propertyDescription =
						parser.getStringProperty(
								$property + ".description", "");

				Property property =
						new Property(
								propertyName,
								propertyValue,
								propertyDescription);

				signer.addProperty(property);
			}

			/*
			 * Registers new PDF signer.
			 */
         	signers.put(name, signer);
		}
	}
}
