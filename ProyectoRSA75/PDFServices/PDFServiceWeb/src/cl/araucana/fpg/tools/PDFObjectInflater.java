

/*
 * @(#) PDFObjectInflater.java    1.0 21-01-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg.tools;


import java.io.File;
import java.io.IOException;

import java.util.zip.Inflater;
import java.util.zip.DataFormatException;

import cl.araucana.core.util.ByteArray;

import cl.araucana.fpg.FPGException;
import cl.araucana.fpg.PDFDictionary;
import cl.araucana.fpg.PDFObject;
import cl.araucana.fpg.PDFTemplate;


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
 *            <TD> 21-01-2009 </TD>
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
public class PDFObjectInflater {

	private static final String STREAM_PATTERN = "stream";

	public static void help() {
		usage();
	}

	public static void usage() {
		System.err.println("inflate <templateDir> <templateName> <objID>");
	}

	public static void main(String[] args) throws Exception {

		if (args.length != 3) {
			usage();

			return;
		}

		String templateDir = args[0];
		String templateName = args[1];
		int objID = Integer.parseInt(args[2]);

		File dir = new File(templateDir + "/" + templateName);

		if (!dir.isDirectory()
				|| !new File(dir, "source.pdf").exists()) {

			System.err.println("Invalid '" + dir + "' directory.");

			return;
		}

		PDFTemplate template = new PDFTemplate(templateDir, templateName);

		System.err.println(
				"Loading PDF Template '" + template. getFullName() + "' ...");

		template.setDebugMode(Boolean.getBoolean("pdf.debug"));
		template.load();

		PDFObject pdfObject;

		try {
			pdfObject = template.getObject(objID);
		} catch (IOException e) {
			System.err.println(
					  "PDF Object '" + objID + "' not found in "
					+ "PDF Template '" + template. getFullName() + "'.");

			return;
		}

		byte[] objectData = pdfObject.getData();

		// << /Filter /FlateDecode /Length 4081 >>
		int length = 0;

		try {
			PDFDictionary objHeaderDict =
					new PDFDictionary(new String(objectData));

			if (objHeaderDict == null) {
				System.err.println("PDF Object must be a dictionary.");

				return;
			}

			String objFilter =
					objHeaderDict.getValue("/Filter");

			if (objFilter != null) {
				if (objFilter.equals("/FlateDecode")) {
					length =
							objHeaderDict.getIntValue(
									"/Length");
				}
			}

			if (objFilter == null || length <= 0) {
				throw new FPGException();
			}
		} catch (FPGException e) {
			System.err.println("PDF Object is not inflatable.");

			return;
		}

		ByteArray objectContent = new ByteArray(objectData);
		int index = objectContent.indexOf(STREAM_PATTERN);

		if (index == -1) {
			System.err.println("PDF Object must be a data stream.");

			return;
		}

		int streamOffset = index + STREAM_PATTERN.length();

		/*
		 * Adjusts offset to EOL PDF markers.
		 */
		if (objectData[streamOffset] == '\r') {
			streamOffset++;

			if (streamOffset < objectData.length
					&& objectData[streamOffset] == '\n') {

				streamOffset++;
			}
		} else if (objectData[streamOffset] != '\n') {
			System.err.println("Bad formed PDF Object data stream.");

			return;
		} else {
			streamOffset++;
		}

		/*
		 *  Inflates specified PDF Object.
		 */
		Inflater inflater = new Inflater();

		inflater.setInput(objectData, streamOffset, length);

		byte[] decodedData = new byte[20 * length];

		try {
			int decodedLength =	inflater.inflate(decodedData);
			byte[] data = new byte[decodedLength];

			System.arraycopy(decodedData, 0, data, 0, data.length);

			template.saveData(
					pdfObject.getBaseType() + "/"	+ objID,
					data,
					PDFTemplate.EXTENSION_INFLATED);

			System.err.println(
					"PDF Object '" + objID + "' was inflated successfully.");
		} catch (IOException e) {
			System.err.println("Inflated PDF Object cannot be saved.");
		} catch (DataFormatException e) {

			/*
			 * PDF Object couldn't be uncompressed.
			 */
			 System.err.println(
					  "PDF Object '" + objID + "' "
					+ "couldn't be "
					+ "inflated. [cause="
					+ e.getMessage() + "] "
					+ "offset=" + streamOffset
					+ " "
					+ "length=" + length);
		} finally {
			inflater.end();
		}
	}
}
