

/*
 * @(#) EAN128BarCode.java    1.0 08-03-2010
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg.barcodes;


import java.io.ByteArrayOutputStream;


/**
 * EAN128 barcode represented as a block of drawing PDF statements.
 * This block can be included in the stream of a PDF Object.
 *
 * <p>
 * The following image shows an sample of EAN128 barcode:
 * </p>
 *
 * <p align="center">
 * <img src="{@docRoot}/extras/ean128barcode.jpg">
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
 *            <TD> 08-03-2010 </TD>
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
public class EAN128BarCode {

	public static final boolean dump = Boolean.getBoolean("ean128.dump");

	private static final int START_A = 103;
	private static final int START_B = 104;
	private static final int START_C = 105;
	private static final int STOP    = 106;
	private static final int FNC_1   = 102;

    private static final int[][] modules = {
        { 2, 1, 2, 2, 2, 2 },
        { 2, 2, 2, 1, 2, 2 },
        { 2, 2, 2, 2, 2, 1 },
        { 1, 2, 1, 2, 2, 3 },
        { 1, 2, 1, 3, 2, 2 },
        { 1, 3, 1, 2, 2, 2 },
        { 1, 2, 2, 2, 1, 3 },
        { 1, 2, 2, 3, 1, 2 },
        { 1, 3, 2, 2, 1, 2 },
        { 2, 2, 1, 2, 1, 3 },
        { 2, 2, 1, 3, 1, 2 },
        { 2, 3, 1, 2, 1, 2 },
        { 1, 1, 2, 2, 3, 2 },
        { 1, 2, 2, 1, 3, 2 },
        { 1, 2, 2, 2, 3, 1 },
        { 1, 1, 3, 2, 2, 2 },
        { 1, 2, 3, 1, 2, 2 },
        { 1, 2, 3, 2, 2, 1 },
        { 2, 2, 3, 2, 1, 1 },
        { 2, 2, 1, 1, 3, 2 },
        { 2, 2, 1, 2, 3, 1 },
        { 2, 1, 3, 2, 1, 2 },
        { 2, 2, 3, 1, 1, 2 },
        { 3, 1, 2, 1, 3, 1 },
        { 3, 1, 1, 2, 2, 2 },
        { 3, 2, 1, 1, 2, 2 },
        { 3, 2, 1, 2, 2, 1 },
        { 3, 1, 2, 2, 1, 2 },
        { 3, 2, 2, 1, 1, 2 },
        { 3, 2, 2, 2, 1, 1 },
        { 2, 1, 2, 1, 2, 3 },
        { 2, 1, 2, 3, 2, 1 },
        { 2, 3, 2, 1, 2, 1 },
        { 1, 1, 1, 3, 2, 3 },
        { 1, 3, 1, 1, 2, 3 },
        { 1, 3, 1, 3, 2, 1 },
        { 1, 1, 2, 3, 1, 3 },
        { 1, 3, 2, 1, 1, 3 },
        { 1, 3, 2, 3, 1, 1 },
        { 2, 1, 1, 3, 1, 3 },
        { 2, 3, 1, 1, 1, 3 },
        { 2, 3, 1, 3, 1, 1 },
        { 1, 1, 2, 1, 3, 3 },
        { 1, 1, 2, 3, 3, 1 },
        { 1, 3, 2, 1, 3, 1 },
        { 1, 1, 3, 1, 2, 3 },
        { 1, 1, 3, 3, 2, 1 },
        { 1, 3, 3, 1, 2, 1 },
        { 3, 1, 3, 1, 2, 1 },
        { 2, 1, 1, 3, 3, 1 },
        { 2, 3, 1, 1, 3, 1 },
        { 2, 1, 3, 1, 1, 3 },
        { 2, 1, 3, 3, 1, 1 },
        { 2, 1, 3, 1, 3, 1 },
        { 3, 1, 1, 1, 2, 3 },
        { 3, 1, 1, 3, 2, 1 },
        { 3, 3, 1, 1, 2, 1 },
        { 3, 1, 2, 1, 1, 3 },
        { 3, 1, 2, 3, 1, 1 },
        { 3, 3, 2, 1, 1, 1 },
        { 3, 1, 4, 1, 1, 1 },
        { 2, 2, 1, 4, 1, 1 },
        { 4, 3, 1, 1, 1, 1 },
        { 1, 1, 1, 2, 2, 4 },
        { 1, 1, 1, 4, 2, 2 },
        { 1, 2, 1, 1, 2, 4 },
        { 1, 2, 1, 4, 2, 1 },
        { 1, 4, 1, 1, 2, 2 },
        { 1, 4, 1, 2, 2, 1 },
        { 1, 1, 2, 2, 1, 4 },
        { 1, 1, 2, 4, 1, 2 },
        { 1, 2, 2, 1, 1, 4 },
        { 1, 2, 2, 4, 1, 1 },
        { 1, 4, 2, 1, 1, 2 },
        { 1, 4, 2, 2, 1, 1 },
        { 2, 4, 1, 2, 1, 1 },
        { 2, 2, 1, 1, 1, 4 },
        { 4, 1, 3, 1, 1, 1 },
        { 2, 4, 1, 1, 1, 2 },
        { 1, 3, 4, 1, 1, 1 },
        { 1, 1, 1, 2, 4, 2 },
        { 1, 2, 1, 1, 4, 2 },
        { 1, 2, 1, 2, 4, 1 },
        { 1, 1, 4, 2, 1, 2 },
        { 1, 2, 4, 1, 1, 2 },
        { 1, 2, 4, 2, 1, 1 },
        { 4, 1, 1, 2, 1, 2 },
        { 4, 2, 1, 1, 1, 2 },
        { 4, 2, 1, 2, 1, 1 },
        { 2, 1, 2, 1, 4, 1 },
        { 2, 1, 4, 1, 2, 1 },
        { 4, 1, 2, 1, 2, 1 },
        { 1, 1, 1, 1, 4, 3 },
        { 1, 1, 1, 3, 4, 1 },
        { 1, 3, 1, 1, 4, 1 },
        { 1, 1, 4, 1, 1, 3 },
        { 1, 1, 4, 3, 1, 1 },
        { 4, 1, 1, 1, 1, 3 },
        { 4, 1, 1, 3, 1, 1 },
        { 1, 1, 3, 1, 4, 1 },
        { 1, 1, 4, 1, 3, 1 },
        { 3, 1, 1, 1, 4, 1 },
        { 4, 1, 1, 1, 3, 1 },
        { 2, 1, 1, 4, 1, 2 },
        { 2, 1, 1, 2, 1, 4 },
        { 2, 1, 1, 2, 3, 2 },
        { 2, 3, 3, 1, 1, 1, 2 }
    };

	private static final int CHARSET_A = 0;
	private static final int CHARSET_B = 1;
	private static final int CHARSET_C = 2;

	private static final int[][] codes = {
		{ -1, 100, 99 },
		{ 101, -1, 99 },
		{ 101, 100, -1 }
	};

	private static final int BUFFER_REF_SIZE = 4096; // 4 KB.
	private static final int MAX_CHAR_LENGTH = 32;

	private  static final char SPACE = ' ';
	private  static final char BACK_SINGLE_QUOTE = '`';

	private static final int INDEX_OFFSET_A = 64;
	private static final int FACTOR_V = 103;

	private int charset;
	private int width;
	private int height;
	private int y0;
	private int x0;
	private boolean horizontal;

	private int length;
	private int[] characters;

	/**
	 * Constructs a horizontal PDF EAN128 barcode with the specified values.
	 * 
	 * @param charset Initial EAN128 character set (A, B or C).
	 * 
	 * @param width Bar's width in pdf units.
	 *
	 * @param height Bar's height in pdf units.
	 * 
	 * @param y0 Upper left corner 's coordinate Y. 
	 * 
	 * @param x0 Upper left corner 's coordinate X.
	 * 
	 * @see #EAN128BarCode(char, int, int, int, int, boolean)
	 */
	public EAN128BarCode(char charset, int width, int height, int y0, int x0) {
		this(charset, width, height, y0, x0, true);
	}

	/**
	 * Constructs a PDF EAN128 barcode with the specified values.
	 * 
	 * @param charset Initial EAN128 character set (A, B or C).
	 * 
	 * @param width Bar's width in pdf units.
	 *
	 * @param height Bar's height in pdf units.
	 * 
	 * @param y0 Upper left corner 's coordinate Y. 
	 * 
	 * @param x0 Upper left corner 's coordinate X.
	 * 
	 * @param horizontal Barcode orientation. <code>true</code> means
	 *        <u>horizontal</u>, otherwise <u>vertical</u>.
	 *        
	 * @see #EAN128BarCode(char, int, int, int, int)
	 */		
	public EAN128BarCode(char charset, int width, int height, int y0, int x0,
			boolean horizontal) {

		if (charset < 'A' || charset > 'C') {
			throw new IllegalArgumentException(
					"Unknown charset '" + charset + "'");
		}

		this.charset = charset - 'A';
		this.width = width;
		this.height = height;
		this.y0 = y0;
		this.x0 = x0;
		this.horizontal = horizontal;
	}

	/**
	 * Gets charset.
	 * 
	 * @return Charset.
	 */
	public char getCharset() {
		return (char) (charset + 'A');
	}
	
	/**
	 * Gets width.
	 * 
	 * @return Width.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets height.
	 * 
	 * @return Height.
	 */
	public int getHeight() {
		return height;
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
	 * Indicates if orientation for this EAN128 barcode is horizontal.
	 * 
	 * @return <code>true</code> if orientation is horizontal, otherwise
	 *         vertical.
	 */
	public boolean isHorizontal() {
		return horizontal;
	}

	/**
	 * Returns a string representation of the EAN128 barcode with each
	 * property indicating its value.
	 * 
	 * @return String representation.
	 */
	public String toString() {
		return
				  getCharset() + " "
				+ width + " "
				+ height + " "
				+ y0 + " "
				+ x0 + " "
				+ horizontal;
	}

	/**
	 * Generates EAN128 barcode's PDF representation for the specified text.
	 *  
	 * @param text Text to be encoded.
	 * 
	 * @return PDF EAN128 barcode representation.
	 */	
    public byte[] generate(String text) {
		if (text.length() == 0) {
			throw new IllegalArgumentException(
					"Unexpected text '" + text + "'");
		}

		characters = new int[MAX_CHAR_LENGTH];
		length = 0;

		switch (charset) {
			case CHARSET_A:

				addCharacter(START_A);
				addCharacter(FNC_1);
				generate_A(text);

				break;

			case CHARSET_B:

				addCharacter(START_B);
				addCharacter(FNC_1);
				generate_B(text);

				break;

			default: // CHARSET_C

				addCharacter(START_C);
				addCharacter(FNC_1);
				generate_C(text);
		}

		addCharacter(getV());
		addCharacter(STOP);

		if (dump) {
			dump();
		}

		/*
		 * PDF Code Segment Generation.
		 */
		ByteArrayOutputStream buffer =
				new ByteArrayOutputStream(BUFFER_REF_SIZE);

		if (horizontal) {
			int y = y0;
			int x = x0;

			for (int i = 0; i < length; i++) {
				int character = characters[i];
				int[] module = modules[character];

				for (int j = 0; j < module.length; j++) {
					int offset = module[j] * width;

					if (j % 2 == 0) { // Black module.
						fillBuffer(
								buffer,
								  "q\n"
								+ x + " "
								+ y + " "
								+ offset + " "
								+ height + " "
								+ "re\nf*\nQ\n\n");
					}

					x += offset;
				}
			}
		} else {
			int y = y0;
			int x = x0;

			for (int i = 0; i < length; i++) {
				int character = characters[i];
				int[] module = modules[character];

				for (int j = 0; j < module.length; j++) {
					int offset = module[j] * width;

					if (j % 2 == 0) { // Black module.
						fillBuffer(
								buffer,
								  "q\n"
								+ x + " "
								+ y + " "
								+ height + " "
								+ offset + " "
								+ "re\nf*\nQ\n\n");
					}

					y += offset;
				}
			}
		}

		return buffer.toByteArray();
	}

	private void fillBuffer(ByteArrayOutputStream buffer, String s) {
		byte[] bytes = s.getBytes();

		buffer.write(bytes, 0, bytes.length);
	}

    private void generate_A(String text) {
		generate_A(text, 0);
	}

    private int generate_A(String text, int offset) {
		while (offset < text.length()) {
			char nextChar = text.charAt(offset);

			if (nextChar < BACK_SINGLE_QUOTE) {
				addCharacter(nextChar - SPACE);

				offset++;
			} else {
				offset = switchFrom_A_To_B(nextChar, text, offset);
			}
		}

		return offset;
	}

	private int switchFrom_A_To_B(char firstChar, String text, int offset) {
		addCharacter(codes[CHARSET_A][CHARSET_B]);
		addCharacter(firstChar - SPACE);

		return generate_A(text, offset + 1);
	}

    private void generate_B(String text) {
		generate_B(text, 0);
	}

    private int generate_B(String text, int offset) {
		while (offset < text.length()) {
			char nextChar = text.charAt(offset);

			if (nextChar >= SPACE) {
				addCharacter(nextChar - SPACE);

				offset++;
			} else {
				offset = switchFrom_B_To_A(nextChar, text, offset);
			}
		}

		return offset;
	}

	private int switchFrom_B_To_A(char firstChar, String text, int offset) {
		addCharacter(codes[CHARSET_B][CHARSET_A]);
		addCharacter(firstChar + INDEX_OFFSET_A);

		return generate_A(text, offset + 1);
	}

    private void generate_C(String text) {
		generate_C(text, 0);
	}

    private int generate_C(String text, int offset) {
		while (offset < text.length()) {
			char firstChar = text.charAt(offset);

			if (Character.isDigit(firstChar)) {
				if (offset + 1 < text.length()) {
					char secondChar = text.charAt(offset + 1);

					if (Character.isDigit(secondChar)) {
						int character =
								10 * (firstChar - '0') + (secondChar - '0');

						addCharacter(character);

						offset += 2;
					} else {
						offset = switchFrom_C_To_BA(firstChar, text, offset);
					}
				} else {
					offset = switchFrom_C_To_BA(firstChar, text, offset);
				}
			} else {
				offset = switchFrom_C_To_BA(firstChar, text, offset);
			}
		}

		return offset;
	}

	private int switchFrom_C_To_BA(char firstChar, String text, int offset) {
		if (firstChar >= SPACE) { /* switch to charset B. */
			addCharacter(codes[CHARSET_C][CHARSET_B]);
			addCharacter(firstChar - SPACE);

			return generate_B(text, offset + 1);
		}

		/* switch to charset A. */
		addCharacter(codes[CHARSET_C][CHARSET_A]);
		addCharacter(firstChar + INDEX_OFFSET_A);

		return generate_A(text, offset + 1);
	}

	private void addCharacter(int index) {
		if (length == characters.length) {
			throw new IndexOutOfBoundsException(
					"Maximum characters for an EAN128 barcode was reached");
		}

		characters[length++] = index;
	}

	private void dump() {
		for (int i = 0; i < length; i++) {
			int character = characters[i];
			int[] module = modules[character];
			String s = "";

			for (int j = 0; j < module.length; j++) {
				s += module[j];

				if (j + 1 < module.length) {
					s += " ";
				}
			}

			System.err.println("    " + character + ": " + s);
		}
	}

	private int getV() {
		int sum = characters[0];

		for (int i = 1; i < length; i++) {
			int character = characters[i];

			sum += character * i;
		}

		int v = sum % FACTOR_V;

		// System.out.println("    V: sum=" + sum + " v=" + v);

		return v;
	}

	public static void main(String[] args) throws Exception {

		if (args.length != 7 || args[0].length() == 0
				|| "ABC".indexOf(args[0].charAt(0)) == -1) {

			System.err.println(
					  "usage: <charset> <width> <height>"
					+ "<y0> <x0> <horizontal> <text>");

			System.err.println("C 4 117 1732 169 true 1234567890");

			System.exit(1);
		}

		char charset = args[0].charAt(0);
		int width = Integer.parseInt(args[1]);
		int height = Integer.parseInt(args[2]);
		int y0 = Integer.parseInt(args[3]);
		int x0 = Integer.parseInt(args[4]);
		boolean horizontal = args[5].equals("true");
		String text = args[6];

		System.err.println("charset     = " + charset);
		System.err.println("width       = " + width);
		System.err.println("height      = " + height);
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
			EAN128BarCode ean128BarCode =
					new EAN128BarCode(
							charset, width, height, y0, x0, horizontal);

			for (int i = 0; i < times; i++) {
				code = ean128BarCode.generate(text);
			}
		} else {
			for (int i = 0; i < times; i++) {
				EAN128BarCode ean128BarCode =
						new EAN128BarCode(
								charset, width, height, y0, x0, horizontal);

				code = ean128BarCode.generate(text);
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
