

package cl.araucana.fpg;


/*
 * @(#) PDFArray.java    1.0 19-01-2009
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
 *            <TD> 19-01-2009 </TD>
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
public class PDFArray {

	private static final String WHITE_SPACES = " \t\r\n";

	private List values = new ArrayList();

	public PDFArray() {
	}

	public PDFArray(String text) throws FPGException {

		int beginIndex = text.indexOf("[");
		int endIndex = text.lastIndexOf("]");

		if (beginIndex >= 0 && endIndex >= 0) {
			text = text.substring(beginIndex + 1, endIndex - 1);
		}

		int i = 0;
		int j = text.length();

		char ch;
		String token;

		while(i < j) {
			ch = text.charAt(i);

			if (WHITE_SPACES.indexOf(ch) >= 0) {
				i++;
			} else {
				token = ch + "";

				while(++i < j) {
					ch = text.charAt(i);

					if (WHITE_SPACES.indexOf(ch) >= 0) {
						break;
					}

					token += ch;
				}

				values.add(token);
			}
		}
	}

	public void addValue(String value) {
		values.add(value);
	}

	public int size() {
		return values.size();
	}

	public String getValue(int index) {
		checkIndex(index);

		return (String) values.get(index);
	}

	public int getIntValue(int index) throws NumberFormatException {

		return Integer.parseInt(getValue(index));
	}

	public String toString() {

		String s = "array[";

		for (int i = 0; i < values.size(); i++) {
			s += values.get(i);

			if (i + 1 < values.size()) {
				s += " ";
			}
		}

		return s + "]";
	}

	public byte[] assemble() {

		String aux = "[";

		for (int i = 0; i < values.size(); i++) {
			aux += values.get(i);

			if (i + 1 < values.size()) {
				aux += " ";
			}
		}

		aux += "]\n";

		return aux.getBytes();
	}

	private void checkIndex(int index) {
		if (index < 0 || index >= values.size()) {
			throw new IndexOutOfBoundsException(index + "");
		}
	}
}
