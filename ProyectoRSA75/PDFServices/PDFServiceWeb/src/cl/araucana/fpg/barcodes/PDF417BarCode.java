

/*
 * @(#) PDF417BarCode.java    1.0 11-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg.barcodes;


import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import gnu.utils.pdf417.PDF417;


/**
 * PDF417 barcode represented as a block of drawing PDF statements.
 * This block can be included in the stream of a PDF Object.
 * 
 * <p>
 * The following image shows an sample of PDF417 barcode:
 * </p>
 * 
 * <p align="center">
 * <img src="{@docRoot}/extras/pdf417barcode.jpg">
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
 *            <TD> 11-04-2008 </TD>
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
public class PDF417BarCode {

	/**
	 * Default outline height in pdf units. 
	 */
	public static final int DEFAULT_HEIGHT = 11;
	
	/**
	 * Default outline width in pdf units.
	 */
	public static final int DEFAULT_WIDTH = 3;

	private PDF417 pdf417;

	private int codeColumns;
	private int errorLevel;
	private int y0;
	private int x0;
	private int height;
	private int width;
	private boolean horizontal;

	/**
	 * Constructs a horizontal PDF PDF417 barcode with the specified values and
	 * default outline dimensions.
	 * 
	 * @param codeColumns Number of code columns.
	 * 
	 * @param errorLevel Error level.
	 * 
	 * @param y0 Upper left corner 's coordinate Y. 
	 * 
	 * @param x0 Upper left corner 's coordinate X.
	 * 
	 * @see #PDF417BarCode(int, int, int, int, boolean)
	 * @see #PDF417BarCode(int, int, int, int, int, int, boolean)
	 */	
	public PDF417BarCode(int codeColumns, int errorLevel, int y0, int x0) {
		this(codeColumns, errorLevel, y0, x0, true);
	}

	/**
	 * Constructs a PDF PDF417 barcode with the specified values.
	 * 
	 * @param codeColumns Number of code columns.
	 * 
	 * @param errorLevel Error level.
	 * 
	 * @param y0 Upper left corner 's coordinate Y. 
	 * 
	 * @param x0 Upper left corner 's coordinate X.
	 * 
	 * @param horizontal Barcode orientation. <code>true</code> means
	 *        <u>horizontal</u>, otherwise <u>vertical</u>.
	 *        
	 * @see #PDF417BarCode(int, int, int, int)
	 * @see #PDF417BarCode(int, int, int, int, int, int, boolean)
	 */		
	public PDF417BarCode(int codeColumns, int errorLevel, int y0, int x0,
			boolean horizontal) {

		this(
				codeColumns,
				errorLevel,
				y0,
				x0,
				DEFAULT_HEIGHT,
				DEFAULT_WIDTH,
				horizontal);
	}

	/**
	 * Constructs a PDF PDF417 barcode with the specified values. Coordinates
	 * and dimensions are expressed in PDF units.
	 * 
	 * @param codeColumns Number of code columns.
	 * 
	 * @param errorLevel Error level.
	 * 
	 * @param y0 Upper left corner 's coordinate Y. 
	 * 
	 * @param x0 Upper left corner 's coordinate X.
	 * 
	 * @param height Barcode outline height.
	 * 
	 * @param width Barcode outline width.
	 * 
	 * @param horizontal Barcode orientation. <code>true</code> means
	 *        <u>horizontal</u>, otherwise <u>vertical</u>.
	 *        
	 * @see #PDF417BarCode(int, int, int, int)
	 * @see #PDF417BarCode(int, int, int, int, boolean)
	 */
	public PDF417BarCode(int codeColumns, int errorLevel, int y0, int x0,
			int height, int width, boolean horizontal) {

		this.codeColumns = codeColumns;
		this.errorLevel = errorLevel;
		this.y0 = y0;
		this.x0 = x0;
		this.height = height;
		this.width = width;
		this.horizontal = horizontal;

		pdf417 = new PDF417();

		pdf417.setOptions(
				PDF417.PDF417_FIXED_COLUMNS | PDF417.PDF417_USE_ERROR_LEVEL);

		pdf417.setCodeColumns(codeColumns);
		pdf417.setErrorLevel(errorLevel);
	}

	/**
	 * Gets number of code columns.
	 * 
	 * @return Number of code columns.
	 */
	public int getCodeColumns() {
		return codeColumns;
	}

	/**
	 * Gets error level.
	 * 
	 * @return Error level.
	 */
	public int getErrorLevel() {
		return errorLevel;
	}

	/**
	 * Gets upper left corner 's coordinate Y.
	 * 
	 * @return Upper left corner 's coordinate Y.
	 */
	public int getY0() {
		return y0;
	}

	/**
	 * Gets upper left corner 's coordinate X.
	 * 
	 * @return Upper left corner 's coordinate X.
	 */
	public int getX0() {
		return x0;
	}

	/**
	 * Indicates if orientation for this PDF417 barcode is horizontal.
	 * 
	 * @return <code>true</code> if orientation is horizontal, otherwise
	 *         vertical.
	 */
	public boolean isHorizontal() {
		return horizontal;
	}

	/**
	 * Returns a string representation of the PDF417 barcode with each
	 * property indicating its value.
	 * 
	 * @return String representation.
	 */
	public String toString() {
		return
				  codeColumns + " "
				+ errorLevel + " "
				+ y0 + " "
				+ x0 + " "
				+ horizontal;
	}

	/**
	 * Generates PDF417 barcode's PDF representation for the specified text.
	 *  
	 * @param text Text to be encoded.
	 * 
	 * @return PDF PDF417 barcode representation.
	 * 
	 * @throws UnsupportedEncodingException If cannot encode PDF417 barcode.
	 */
	public byte[] generate(String text) throws UnsupportedEncodingException {

		pdf417.setText(text);
		pdf417.paintCode();

		byte[] out = pdf417.getOutBits();
		int cols = (pdf417.getBitColumns() - 1) / 8 + 1;
		int rows = out.length / cols;

		ByteArrayOutputStream buffer =
				new ByteArrayOutputStream(192 * rows * cols);

		if (horizontal) {
			return generate_horizontal(buffer, out, cols, rows);
		}

		return generate_vertical(buffer, out, cols, rows);
	}

	private byte[] generate_horizontal(ByteArrayOutputStream buffer, byte[] out,
			int cols, int rows) {

		/*
		 * q
		 * 661 2250 3 11 re
		 * f*
		 * Q
		 *
		 * Y X HEIGHT WIDTH
		 */
		int y = y0;

		for (int row = 0; row < rows; row++) {
			int x = x0;
			int ones = 0;
			int zeroes = 0;

			for (int col = 0; col < cols; col++) {
				for (int bit = 7; bit >= 0; bit--) {
					int v = (out[cols * row + col] & 0xFF);
					int res = (v & (0x0000001 << bit)) == 0 ? 0 : 1;

					if (res == 1) {
						ones++;
					} else {
						if (ones > 0) {
							fillBuffer(
									buffer,
									  "q\n"
									+ y + " "
									+ x + " "
									+ height + " "
									+ width * ones + " "
									+ "re\nf*\nQ\n");

							x += width * ones;
							ones = 0;
							zeroes = 0;
						}

						zeroes++;
						x += width;
					}
				}
			}

			if (ones > 0) {
				fillBuffer(
						buffer,
						  "q\n"
						+ y + " "
						+ x + " "
						+ height + " "
						+ width * ones + " "
						+ "re\nf*\nQ\n");
			}

			y -= height;
		}

		return buffer.toByteArray();
	}

	private byte[] generate_vertical(ByteArrayOutputStream buffer, byte[] out,
			int cols, int rows) {

		/*
		 * q
		 * 661 2250 11 3 re
		 * f*
		 * Q
		 *
		 * Y X WIDTH HEIGHT
		 */
		int y = y0;

		for (int col = 0; col < cols; col++) {
			for (int bit = 7; bit >= 0; bit--) {
				int x = x0;
				int ones = 0;
				int zeroes = 0;

				for (int row = rows - 1; row >= 0; row--) {
					int v = (out[cols * row + col] & 0xFF);
					int res = (v & (0x00000001 << bit)) == 0 ? 0 : 1;

					// System.err.print(res);

					if (res == 1) {
						ones++;
					} else {
						if (ones > 0) {
							fillBuffer(
									buffer,
									  "q\n"
									+ y + " "
									+ x + " "
									+ width + " "
									+ height * ones + " "
									+ "re\nf*\nQ\n");

							x += height * ones;
							ones = 0;
							zeroes = 0;
						}

						zeroes++;
						x += height;
					}
				}

				// System.err.println();

				if (ones > 0) {
					fillBuffer(
							buffer,
							  "q\n"
							+ y + " "
							+ x + " "
							+ width + " "
							+ height * ones + " "
							+ "re\nf*\nQ\n");
				}

				y -= width;
			}
		}

		return buffer.toByteArray();
	}

	private void fillBuffer(ByteArrayOutputStream buffer, String s) {
		byte[] bytes = s.getBytes();

		buffer.write(bytes, 0, bytes.length);
	}

	/**
	 * PDF417 barcode demostration launcher.
	 * 
	 * @param args command line arguments.
	 * 
	 * @throws Exception If any exception occurs.
	 */
	public static void main(String[] args) throws Exception {

		if (args.length != 6) {
			System.err.println(
					  "usage: <codeColumns> <errorLevel> "
					+ "<y0> <x0> <horizontal> <text>");

			System.err.println("8 5 1200 1600 true \"This is an example\"");

			System.exit(1);
		}

		int codeColumns = Integer.parseInt(args[0]);
		int errorLevel = Integer.parseInt(args[1]);
		int y0 = Integer.parseInt(args[2]);
		int x0 = Integer.parseInt(args[3]);
		boolean horizontal = args[4].equals("true");
		String text = args[5];

		System.err.println("codeColumns = " + codeColumns);
		System.err.println("errorLevel  = " + errorLevel);
		System.err.println("y0          = " + y0);
		System.err.println("x0          = " + x0);
		System.err.println("horizontal  = " + horizontal);
		System.err.println("text        = " + text);

		int times = 1;
		String sTimes = System.getProperty("times");

		try {
			times = Integer.parseInt(sTimes);
		} catch (NumberFormatException e) {}

		int outer = 1;
		String sOuter = System.getProperty("outer");

		try {
			outer = Integer.parseInt(sOuter);
		} catch (NumberFormatException e) {}

		byte[] code = new byte[0];

		System.err.println("START TIME: " + new java.util.Date());

		long ti = System.currentTimeMillis();

		if (outer == 1) {
			PDF417BarCode pdf147BarCode =
					new PDF417BarCode(
							codeColumns, errorLevel, y0, x0, horizontal);

			for (int i = 0; i < times; i++) {
				code = pdf147BarCode.generate(text);
			}
		} else {
			for (int i = 0; i < times; i++) {
				PDF417BarCode pdf147BarCode =
						new PDF417BarCode(
								codeColumns, errorLevel, y0, x0, horizontal);

				code = pdf147BarCode.generate(text);
			}
		}

		long tf = System.currentTimeMillis();

		System.err.println("START TIME: " + new java.util.Date());

		double avgTime = (times > 0) ? ((tf - ti) / (double) times) : 0.0;

		System.err.println("TOTAL TIME   : " + (tf - ti) + " ms.");
		System.err.println("TIMES        : " + times);
		System.err.println("AVERAGE TIME : " + avgTime + " ms.");
		System.err.println("OUTER        : " + outer);

		System.out.write(code, 0, code.length);
	}
}
