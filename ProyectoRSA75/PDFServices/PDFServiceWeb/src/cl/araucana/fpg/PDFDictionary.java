

package cl.araucana.fpg;


/*
 * @(#) PDFDictionary.java    1.0 18-01-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


import java.util.ArrayList;
import java.util.List;


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
 *            <TD> 18-01-2009 </TD>
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
public class PDFDictionary {

	private static final String WHITE_SPACES = " \t\r\n";
	private static final String DICT_WORD_SEPARATORS = "/\t\r\n";
	private static final String DICT_WORD_CHARACTERS = "-+";

	private List words = new ArrayList();
	private List values = new ArrayList();

	private int index;

	public PDFDictionary() {
	}

	public PDFDictionary(String text) throws FPGException {

		int beginIndex = text.indexOf("<<");
		int endIndex = text.lastIndexOf(">>");

		if (beginIndex == -1 || endIndex == -1) {
			throwFPGException(text);
		}

		text = text.substring(beginIndex + 2, endIndex + 2);

		int i = 0;
		int j = text.length();

		char ch;
		String token;
		String word = null;

		while(i < j) {
			ch = text.charAt(i);

			if (ch == '/') {
				token = "/";

				while(++i < j) {
					ch = text.charAt(i);

					if (!isValidWordCharacter(ch)) {
						break;
					}

					token += ch;
				}

				if (word == null) {
					word = token;
				} else {
					addWord(word, token);

					word = null;
				}
			} else if (WHITE_SPACES.indexOf(ch) >= 0) {
				i++;
			} else if (ch == '[') {
				if (word == null) {
					throwFPGException(text);
				}

				token = "[";

				while(++i < j && ch != ']') {
					ch = text.charAt(i);

					token += ch;
				}

				addWord(word, token);

				word = null;
			} else if (ch == '(') {
				if (word == null) {
					throwFPGException(text);
				}

				boolean escaped = false;

				token = "(";

				while(++i < j) {
					ch = text.charAt(i);

					if (escaped) {
						token += ch;
						escaped = false;
					} else if (ch == '\\') {
						escaped = true;
					} else {
						token += ch;

						if (ch == ')') {
							i++;

							break;
						}
					}
				}

				addWord(word, token);

				word = null;
			} else {
				int k = i;

				token = ch + "";

				while(++i < j) {
					ch = text.charAt(i);

					if (DICT_WORD_SEPARATORS.indexOf(ch) >= 0) {
						break;
					}

					token += ch;
				}

				int index = token.indexOf(">>");

				if (index >= 0) {
					if (word != null) {
						addWord(word, token.substring(0, index));
					} else if (index > 0) {
						throwFPGException(text);
					}

					this.index = k + index + 2;

					return;
				}

				if (word == null) {
					throwFPGException(text);
				}

				index = token.indexOf("<<");

				if (index >= 0) {
					if (index > 0) {
						throwFPGException(text);
					}

					String subText = text.substring(k);
					PDFDictionary subDict = new PDFDictionary(subText);

					addWord(word, subDict);

					i = k + 2 + subDict.index;
				} else {
					addWord(word, token);
				}

				word = null;
			}
		}

		if (word != null) {
			throwFPGException(text);
		}
	}

	public void addWord(String word, Object value) throws FPGException {

		checkWord(word);

		if (words.contains(word)) {
			throw new FPGException("Duplicated word '" + word + "'");
		}

		if (value == null) {
			throw new FPGException("Word value cannot be null");
		}

		if (value instanceof String) {
			value = ((String) value).trim();

			if (value.equals("")) {
				throw new FPGException("Word value cannot be empty");
			}
		}

		words.add(word);
		values.add(value);

		// System.out.println("add word=|" + word + "| value=|" + value + "|");
	}

	public void removeWord(String word) {
		int index = words.indexOf(word);

		if (index >= 0) {
			words.remove(index);
			values.remove(index);
		}
	}

	public int wordCount() {
		return words.size();
	}

	public String getWord(int index) {
		if (index < 0 || index >= words.size()) {
			throw new IndexOutOfBoundsException(index + "");
		}

		return (String) words.get(index);
	}

	public int getWordIndex(String word) {
		return words.indexOf(word);
	}

	public Object getObjectValue(String word) {
		int index = words.indexOf(word);

		if (index == -1) {
			return null;
		}

		return values.get(index);
	}

	public String getValue(String word) {
		int index = words.indexOf(word);

		if (index == -1) {
			return null;
		}

		return (String) values.get(index);
	}

	public PDFDictionary getPDFDictionary(String word) {
		int index = words.indexOf(word);

		if (index == -1) {
			return null;
		}

		return (PDFDictionary) values.get(index);
	}

	public int getIntValue(String word) throws NumberFormatException {

		String value = getValue(word);

		if (value == null) {
			throw new IllegalArgumentException("Unknown word '" + word + "'");
		}

		return Integer.parseInt(value);
	}

	public int getObjIDRefValue(String word) throws NumberFormatException {

		String value = getValue(word);

		if (value == null) {
			throw new IllegalArgumentException("Unknown word '" + word + "'");
		}

		String[] tokens = value.split(" ");

		if (tokens.length != 3 || !tokens[1].equals("0")
				|| !tokens[2].equals("R")) {

			throw new NumberFormatException(
					"Invalid objID reference '" + value + "'");
		}

		return Integer.parseInt(tokens[0]);
	}

	public String toString() {

		String s = "dict{";

		for (int i = 0; i < words.size(); i++) {
			s += words.get(i) + "=" + values.get(i);

			if (i + 1 < words.size()) {
				s += " ";
			}
		}

		return s + "}";
	}

	public byte[] assemble() {
		return assemble(true, true);
	}

	public byte[] assemble(boolean opened, boolean closed) {
		String aux;

		if (opened) {
			aux = "<<\n";
		} else {
			aux = "";
		}

		for (int i = 0; i < words.size(); i++) {
			Object value = values.get(i);

			aux += words.get(i) + " ";

			if (value instanceof PDFDictionary) {
				PDFDictionary dict = (PDFDictionary) value;

				aux += new String(dict.assemble());
			} else {
				aux += value + "\n";
			}
		}

		if (closed) {
			aux += ">>\n";
		}

		return aux.getBytes();
	}

	private void checkWord(String word) throws FPGException {

		boolean ok = true;

		if (word == null || !word.startsWith("/")) {
			ok = false;
		} else {
			for (int i = 1; i < word.length(); i++) {
				char ch = word.charAt(i);

				if (!isValidWordCharacter(ch)) {
					ok = false;

					break;
				}
			}
		}

		if (!ok) {
			throw new FPGException("Invalid word '" + word + "'");
		}
	}

	private boolean isValidWordCharacter(char ch) {

		return Character.isLetterOrDigit(ch)
				|| DICT_WORD_CHARACTERS.indexOf(ch) >= 0;
	}

	private void throwFPGException(String text) throws FPGException {

		throw new FPGException("Unexpected PDF dictionary '" + text + "'");
	}
}
