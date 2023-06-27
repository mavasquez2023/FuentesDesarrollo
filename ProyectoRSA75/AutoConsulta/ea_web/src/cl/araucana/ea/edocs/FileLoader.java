
/*
 * @(#) FileLoader.java    1.0 30-10-2006
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.ea.edocs;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;


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
 *            <TD> 30-10-2006 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
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
public class FileLoader {

	private static final int MAX_TEXT_SIZE = 128 * 1024;	// 128 KB.

	private String fileName;

	public FileLoader() {
	}

	public FileLoader(String fileName) {
		this.fileName = fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return fileName;
	}

	public byte[] loadBytes() throws IOException {

		File file = new File(fileName);
		int fileLength = (int) file.length();
		byte[] buffer = new byte[fileLength];
		FileInputStream input = null;

		try {
			input = new FileInputStream(fileName);

			if (input.read(buffer) != fileLength) {
				throw new IOException(
						"read failed for file '" + fileName + "'.");
			}
		} catch(IOException e) {
			throw e;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch(IOException e) { }
			}
		}

		return buffer;
	}

	public Collection loadLines() throws IOException {

		FileReader fileReader = null;
		BufferedReader reader = null;

		Collection lines = new LinkedList();
		String line;

		try {
			fileReader = new FileReader(fileName);
			reader = new BufferedReader(fileReader);
	
			while ((line=reader.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {}
			}

			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {}
			}
		}

		return lines;
	}

	public String loadText() throws IOException {

		return loadText(MAX_TEXT_SIZE);
	}

	public String loadText(int maxTextSize) throws IOException {

		Collection lines = loadLines();
		StringBuffer text = new StringBuffer(maxTextSize);
		Iterator iterator = lines.iterator();

		while (iterator.hasNext()) {
			String line = (String) iterator.next();

			text.append(line);
			text.append('\n');
		}

		return text.toString();
	}
}