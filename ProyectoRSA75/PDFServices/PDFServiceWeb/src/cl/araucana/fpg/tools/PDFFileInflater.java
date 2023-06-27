

/*
 * @(#) PDFFileInflater.java    1.0 29-07-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg.tools;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.zip.Inflater;


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
 *            <TD> 29-07-2008 </TD>
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
public class PDFFileInflater {

	public static void usage() {
		System.err.println("?? <inputFileName> <outputFileName>");
	}

	public static void main(String[] args) throws Exception {

		if (args.length != 2) {
			usage();

			return;
		}

		String inputFileName = args[0];
		String outputFileName = args[1];

		FileInputStream input = null;
		FileOutputStream output = null;

		try {
			int inputFileSize = (int) new File(inputFileName).length();

			input = new FileInputStream(inputFileName);
			output = new FileOutputStream(outputFileName);

			byte[] buffer = new byte[inputFileSize];

			Inflater inflater = new Inflater();

			inflater.setInput(buffer, 0, buffer.length);

			byte[] decodedData = new byte[20 * buffer.length];

			int decodedDataLength =
					inflater.inflate(decodedData);

			inflater.end();

			output.write(decodedData, 0, decodedDataLength);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {}
			}

			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {}
			}
		}
	}
}
