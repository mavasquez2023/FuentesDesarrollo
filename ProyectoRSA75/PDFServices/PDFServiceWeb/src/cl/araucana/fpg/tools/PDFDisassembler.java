// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 16-12-2021 19:41:02
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PDFDisassembler.java

package cl.araucana.fpg.tools;

import cl.araucana.core.util.FileUtils;
import cl.araucana.fpg.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class PDFDisassembler {

	private static final boolean FORWARD_DIRECTION = true;
	private static final boolean BACKWARD_DIRECTION = false;

	private static final byte[] BEGIN_TEXT = { '\n', 'B', 'T', '\n' };
	private static final byte[] END_TEXT = { '\n', 'E', 'T', '\n' };

	private String name;
	private String baseName;

	protected byte[] content;

	private boolean debug;
	private int offset = 0;

	private int beginIndex;
	private int endIndex;

	private int startxref;

	private XRefEntry[] xRefEntries;
	private Trailer trailer;

	public PDFDisassembler(String pdfFileName, String templateDir)
			throws IOException {

		this(pdfFileName, templateDir, null);
	}

	public PDFDisassembler(String pdfFileName, String templateDir,
			String templateName) throws IOException {

		if (!pdfFileName.endsWith(".pdf") && !pdfFileName.endsWith(".PDF")) {
			throw new IOException(
					"Unexpected PDF filename '" + pdfFileName + "'.");
		}

		FileInputStream input = null;
		File file = new File(pdfFileName);

		content = new byte[(int) file.length()];

		try {
			input = new FileInputStream(file);

			if (input.read(content) != file.length()) {
				throw new IOException("Unexpected mismatch size.");
			}
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {}
			}
		}

		if (templateName == null) {
			name = file.getName();
			name = name.substring(0, name.length() - 4);
		} else {
			name = templateName;
		}

		baseName = templateDir + "/" + name;

		debug = Boolean.getBoolean("pdf.debug");

		log("pdfFileName = " + pdfFileName);
		log("name = " + name);
		log("basename = " + baseName);
	}

	public void disassemble() throws IOException {

		createStruct(baseName);
	}

	public void createStruct(String baseName) throws IOException {

		// Makes directories.
		String[] dirNames = {
			"object",
			"text",
			"image",
			"font",
			"page",
			"pages",
			"catalog",
			"xobject",
			"void"
		};

		for (int i = 0; i < dirNames.length; i++) {
			File dir = new File(baseName + "/" + dirNames[i]);

			if (dir.mkdirs()) {
				log("'" + dir + "' directory created.");
			} else {
				throw new IOException("Cannot create directory '" + dir + "'.");
			}
		}

		log("copying source pdf document ...");

		FileOutputStream output = null;

		try {
			output = new FileOutputStream(baseName + "/source.pdf");

			output.write(content);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {}
			}
		}

		writeFile("void/0.txt", "endobj\n");
	}

	/* UNUSED.
	private void readLine() {
		readLine(FORWARD_DIRECTION);
	}
	*/

	private void readLine(boolean forward) {

		/* PDF EOL markers:
		 *
		 *      CR
		 *      CR LF
		 *      LF
		 */
		if (forward) {
			if (offset >= content.length) {
				beginIndex = endIndex = -1;

				return;
			}

			int index = offset;

			beginIndex = offset;

			while (content[index] != '\r' && content[index] != '\n') {
				index++;
			}

			endIndex = index - 1;

			if (content[index] == '\r') {
				index++;

				if (index < content.length && content[index] == '\n') {
					index++;
				}
			} else {
				index++;
			}

			offset = index;
		} else {
			if (offset <= 0) {
				beginIndex = endIndex = -1;

				return;
			}

			int index = offset;

			if (content[index] == '\r') {
				index--;
			} else {		// LF
				index--;

				if (index >= 0 && content[index] == '\r') {
					index--;
				}
			}

			endIndex = index;

			while (index >= 0 && content[index] != '\r'
					&& content[index] != '\n') {

				index--;
			}

			if (index < 0) {
				beginIndex = 0;
				offset = 0;
			} else {
				beginIndex = index + 1;
				offset = index;
			}
		}
	}

	public void gotoEndOfContent() {
		offset = content.length - 1;
	}

	public byte[] getLine() {
		return getLine(FORWARD_DIRECTION);
	}

	public byte[] getLine(boolean forward) {
		readLine(forward);

		if (endIndex == beginIndex && endIndex == -1) {
			return null;
		}

		byte[] line = new byte[endIndex - beginIndex + 1];

		System.arraycopy(content, beginIndex, line, 0, line.length);

		return line;
	}

	public String getStringLine() {
		return getStringLine(FORWARD_DIRECTION);
	}

	public String getStringLine(boolean forward) {
		byte[] line = getLine(forward);

		if (line == null) {
			return null;
		}

		return new String(line);
	}

	public void writeFile(String fileName, String text) throws IOException {

		byte[] data = text.getBytes();

		writeFile(fileName, data, 0, data.length);
	}

	public void writeFile(String fileName, int beginIndex, int endIndex)
			throws IOException {

		writeFile(fileName, content, beginIndex, endIndex - beginIndex + 1);
	}

	public void writeFile(String fileName, byte[] data, int offset, int size)
			throws IOException {

		FileOutputStream output = null;

		try {
			output = new FileOutputStream(baseName + "/" + fileName);

			output.write(data, offset, size);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {}
			}
		}
	}

	private boolean getStartXRef(String line) {
		try {
			startxref = Integer.parseInt(line);

			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean isTextStream(byte[] data, int length) {
		int beginIndex = indexOf(BEGIN_TEXT, data, 0, length);

		if (beginIndex < 0) {
			return false;
		}

		return
				indexOf(
						END_TEXT,
						data,
						beginIndex + BEGIN_TEXT.length,
						length) > 0;
	}

	protected int indexOf(byte[] pattern, byte[] data, int begin, int end) {

		outer:
		for (int i = begin; i < end; i++) {
			int k = 0;

			for (int j = 0; j < pattern.length; j++) {
				if (data[i + k++] != pattern[j]) {
					continue outer;
				}
			}

			return i;
		}

		return -1;
	}

	/* UNUSED.
	private int getTokenIndex(String[] tokens, String pattern) {

		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].equals(pattern)) {
				return i;
			}
		}

		return -1;
	}
	*/

	/* UNUSED.
	private void log() {
		log("");
	}
	*/

	private void log(String message) {
		if (debug) {
			System.err.println(message);
		}
	}

	public static void help() {
		usage();
	}

	public static void usage() {
		System.err.println(
				  "dis [-i] <pdfFileName> <docType> <docVersion> "
				+ "<templateDir> [<templateName>]");
	}

	public static void main(String[] args) {

		boolean inflateAlways = false;

		String pdfFileName = null;
		String docType = null;
		String docVersion = null;
		String templateDir = null;
		String templateName = null;

		PDFDisassembler disassembler = null;

		try {
			if (args.length < 4 || args.length > 6) {
				usage();

				return;
			}

			int index = 0;

			if (args[0].equals("-i")) {
				inflateAlways = true;
				index = 1;
			}

			pdfFileName = args[index++];
			docType = args[index++];
			docVersion = args[index++];
			templateDir = args[index++];

			// Checks document version.
			try {
				Integer.parseInt(docVersion);
			} catch (NumberFormatException e) {
				System.err.println(
						"Invalid document version '" + docVersion + "'.");

				return;
			}

			if (index < args.length) {
				templateName = args[index];
			}

			disassembler =
					new PDFDisassembler(pdfFileName, templateDir, templateName);

			disassembler.remove();
			disassembler.disassemble();

			disassembler.writeFile(
					"docProperties.txt",
					"type=" + docType + "\nversion=" + docVersion + "\n");

			byte[] line;

			while ((line = disassembler.getLine(FORWARD_DIRECTION)) != null
					&& line[0] == '%') {

				String sLine = new String(line);

				disassembler.log("prolog: |" +  sLine + "|");
			}

			disassembler.writeFile(
					"prolog.txt", 0, disassembler.offset - line.length - 2);

			disassembler.gotoEndOfContent();

			/*
			 * |trailer|
			 * |<<|
			 * |/Info 1 0 R|
			 * |/Root 312 0 R|
			 * |/Size 313 |
			 * |>>|
			 * |startxref|
			 * |134805 |
			 * |%%EOF|
			 */
			String textLine = disassembler.getStringLine(BACKWARD_DIRECTION);

			if (textLine == null || !textLine.equals("%%EOF")) {
				throw new IOException("Unexpected BOF. [%%EOF was expected]");
			}

			textLine = disassembler.getStringLine(BACKWARD_DIRECTION);

			if (textLine == null
					|| !disassembler.getStartXRef(textLine.trim())) {

				throw new IOException(
						"Unexpected BOF. [startxref offset was expected]");
			}

			disassembler.log("startxref = " + disassembler.startxref);

			textLine = disassembler.getStringLine(BACKWARD_DIRECTION);

			if (textLine == null || !textLine.equals("startxref")) {
				throw new IOException(
						"Unexpected BOF. [startxref was expected]");
			}

			/*
			 * Gets PDF Document Trailer.
			 */
			List trailerLines = new ArrayList();

			do {
				textLine = disassembler.getStringLine(BACKWARD_DIRECTION);

				if (textLine == null) {
					throw new IOException(
							"Unexpected BOF. [trailer was expected]");
				}

				if (textLine.equals("trailer")) {
					disassembler.log("trailer found");

					break;
				}

				if (!textLine.startsWith("%")) {
					trailerLines.add(textLine.trim());
				}
			} while (true);

			String trailerText = "";

			for (int i = trailerLines.size() - 1; i >= 0; i--) {
				trailerText += ((String) trailerLines.get(i)) + " ";
			}

			disassembler.log("trailer = |" + trailerText + "|?");

			PDFDictionary trailerDict = new PDFDictionary(trailerText);

			disassembler.log("trailer: " + trailerDict);

			Trailer trailer = new Trailer();

			trailer.nObjects = trailerDict.getIntValue("/Size");
			trailer.rootObjID = trailerDict.getObjIDRefValue("/Root");
			trailer.infoObjID = trailerDict.getObjIDRefValue("/Info");

			disassembler.trailer = trailer;

			disassembler.log("trailer ok [" + trailer + "]");

			disassembler.writeFile(
					"trailer.txt",
					  "size=" + trailer.nObjects + "\n"
					+ "root=" + trailer.rootObjID + "\n"
					+ "info=" + trailer.infoObjID + "\n");

			/*
			 *  xref
			 *  0 313
			 */
			disassembler.log("xref:");

			disassembler.offset = disassembler.startxref;

			textLine = disassembler.getStringLine(FORWARD_DIRECTION);

			if (!textLine.equals("xref")) {
				throw new IOException("Xref not found.");
			}

			textLine = disassembler.getStringLine(FORWARD_DIRECTION);

			String[] tokens = textLine.trim().split(" ");

			if (tokens.length != 2) {
				throw new IOException("Unexpected xref.");
			}

			int xRefSize;

			try {
				xRefSize = Integer.parseInt(tokens[1]);
			} catch (NumberFormatException e) {
				throw new IOException("Invalid xref size '" + tokens[1] + "'");
			}

			if (xRefSize != trailer.nObjects) {
				throw new IOException("Mismatched trailer and xref sizes.");
			}

			disassembler.xRefEntries = new XRefEntry[xRefSize];

			// Loads xref table.
			for (int i = 0; i < xRefSize; i++) {
				textLine = disassembler.getStringLine(FORWARD_DIRECTION);
				tokens = textLine.trim().split(" ");

				XRefEntry xRefEntry = new XRefEntry();

				xRefEntry.offset = Integer.parseInt(tokens[0]);
				xRefEntry.value = Integer.parseInt(tokens[1]);
				xRefEntry.mark = tokens[2].charAt(0);

				if (xRefEntry.mark == 'f') {
					xRefEntry.objID = i;
					xRefEntry.offset = i;
				}

				disassembler.xRefEntries[i] = xRefEntry;
			}

			// Determines object ID to every xref entry.
			for (int i = 0; i < disassembler.xRefEntries.length; i++) {
				XRefEntry xRefEntry = disassembler.xRefEntries[i];

				if (xRefEntry.mark == 'n') {
					disassembler.offset = xRefEntry.offset;
					textLine = disassembler.getStringLine();

					tokens = textLine.trim().split(" ");
					xRefEntry.objID = Integer.parseInt(tokens[0]);
				}
			}

			disassembler.log("ObjID Offset Value Mark");

			String xRefText = "# index = ObjID Offset Value Mark Type\n";

			for (int i = 0; i < disassembler.xRefEntries.length; i++) {
				XRefEntry xRefEntry = disassembler.xRefEntries[i];

				if (xRefEntry.mark == 'n') {
					disassembler.offset = xRefEntry.offset;
					textLine = disassembler.getStringLine();

					while(!textLine.equals("endobj")) {
						textLine = disassembler.getStringLine();
					}

					int offset = disassembler.offset - 1;
					String headerText = "";

					disassembler.offset = xRefEntry.offset;

					do {
						textLine = disassembler.getStringLine();
						headerText += textLine.trim() + "\n";
					} while(!textLine.endsWith("stream")
							&& !textLine.endsWith("endobj"));

					disassembler.log(
								"\n"
							  + xRefEntry.objID
							  + ": header = |" + headerText + "|");

					int beginIndex = headerText.indexOf("<<");

					if (xRefEntry.objID != disassembler.trailer.infoObjID) {

						/*
						 * Classifies current PDF object analyzing
						 * its PDF dictionary. Default PDF Object type
						 * is 'text'.
						 */
						String baseType = "text";
						PDFDictionary objHeaderDict = null;

						if (headerText.indexOf("<<") > 0) {
							objHeaderDict =	new PDFDictionary(headerText);

							String objType = objHeaderDict.getValue("/Type");

							if (objType != null) {
								if (objType.equals("/Page")) {
									baseType = "page";
								} else if (objType.equals("/Pages")) {
									baseType = "pages";
								} else if (objType.equals("/Font")) {
									baseType = "font";
								} else if (objType.equals("/Catalog")) {
									baseType = "catalog";
								} else if (objType.equals("/XObject")) {
									String objSubType =
											objHeaderDict.getValue("/Subtype");

									if (objSubType != null
											&& objSubType.equals("/Image")) {

										baseType = "image";
									} else {
										baseType = "xobject";
									}
								}
							} else {

								// << /Filter /FlateDecode /Length 4081 >>
								int length = 0;

								String objFilter =
										objHeaderDict.getValue("/Filter");

								if (objFilter != null) {
									if (objFilter.equals("/FlateDecode")) {
										length =
												objHeaderDict.getIntValue(
														"/Length");
									}
								}

								if (length > 0) {
									disassembler.offset = xRefEntry.offset;

									do {
										textLine =
												disassembler
														.getStringLine();
									} while(!textLine.endsWith("stream"));

									int streamOffset = disassembler.offset;

									Inflater inflater = new Inflater();

									inflater.setInput(
											disassembler.content,
											streamOffset,
											length);

									byte[] decodedData = new byte[20 * length];

									try {
										int decodedLength =
												inflater.inflate(decodedData);

										if (inflateAlways
												|| disassembler.isTextStream(
														decodedData,
														decodedLength)) {

											disassembler.writeFile(
													  "text/"
													+ xRefEntry.objID
													+ ".txt.inflated",
													decodedData,
													0,
													decodedLength);
										}
									} catch (DataFormatException e) {

										/*
										 * PDF Object couldn't be uncompressed.
										 */
										 System.err.println(
											 	  "WARNING: "
											 	+ "PDF Object '" + i + "' "
											 	+ "couldn't be "
											 	+ "uncompressed. [cause="
											 	+ e.getMessage() + "] "
											 	+ "offset=" + streamOffset
											 	+ " "
											 	+ "length=" + length);
									} finally {
										inflater.end();
									}
								}
							}
						}

						xRefEntry.baseType = baseType;

						disassembler.writeFile(
								baseType + "/" + xRefEntry.objID + ".txt",
								xRefEntry.offset + beginIndex,
								offset);

						if (objHeaderDict != null) {
							disassembler.log(
									i + ": " + xRefEntry + " " + objHeaderDict);
						} else {
							disassembler.log(i + ": " + xRefEntry);
						}
					} else {
						xRefEntry.baseType = "info";

						disassembler.writeFile(
								"info.txt",
								xRefEntry.offset + beginIndex,
								offset);

						disassembler.log(xRefEntry.objID + ": [info]");
					}
				}

				xRefText +=
						  i
						+ "="
						+ xRefEntry.objID + " "
						+ xRefEntry.offset + " "
						+ xRefEntry.value + " "
						+ xRefEntry.mark + " "
						+ xRefEntry.baseType + "\n";
			}

			disassembler.writeFile("xref.txt", xRefText);
		} catch (Exception e) {
			e.printStackTrace();

			if (disassembler != null) {
				disassembler.remove();
			}
		}
	}

	private void remove() {
		try {
			FileUtils.removeDir(baseName);
		} catch (Exception e) {}
	}
}
