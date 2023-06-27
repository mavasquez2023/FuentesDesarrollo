

package cl.araucana.fpg.compiled.instructions;


/*
 * @(#) TextInstruction.java    1.0 08-04-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


import cl.araucana.fpg.FPGException;

import cl.araucana.fpg.compiled.CompiledPDFObject;
import cl.araucana.fpg.compiled.FlowControl;
import cl.araucana.fpg.compiled.FPGCode;


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
 *            <TD> 08-04-2008 </TD>
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
public class TextInstruction extends Instruction {

	private static final int DEFAULT_SPACE_PADDING_WIDTH = 317;

	private int propIndex;
	private int spacePaddingWidth;

	public TextInstruction(String label, int propIndex) {
		this(label, propIndex, 0);
	}

	public TextInstruction(String label, int propIndex,	int spacePaddingWidth) {
		super(
				(spacePaddingWidth == 0) ? FPGCode.TEXT : FPGCode.TEXT_PAD,
				label,
				true);

		this.propIndex = propIndex;
		this.spacePaddingWidth = spacePaddingWidth;
	}

	public byte[] toByteArray() {
		if (spacePaddingWidth == 0) {
			return (propIndex + "\n").getBytes();
		}

		return (propIndex + " " + spacePaddingWidth + "\n").getBytes();
	}

	public void execute(CompiledPDFObject compiledObject,
			FlowControl flowControl) throws FPGException {

		String s = compiledObject.getStringProperty(propIndex);

		if (s == null) {
			compiledObject.write("[()]\nTJ\n");

			return;
		}

		if (spacePaddingWidth != 0) {
			int nSpaces = 0;

			for (int i = 0; i < s.length() && s.charAt(i) == ' '; i++) {
				nSpaces++;
			}

			if (nSpaces > 0) {
				int width = nSpaces * spacePaddingWidth;

				compiledObject.write(
						  "[()-" + width
						+ " (" + s.substring(nSpaces) + ")]\nTJ\n");
			} else {
				compiledObject.write("[(" + s + ")]\nTJ\n");
			}

			return;
		}

		int nChars = 0;
		int nSpaces = 0;
		int indexChar = -1;
		int nSegments = 0;

		compiledObject.write("[");

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				if (nSpaces == 0) {
					if (nChars > 0) {
						if (nSegments > 0) {
							compiledObject.write(" ");
						}

						compiledObject.write(
								  "("
								+ s.substring(indexChar,  indexChar + nChars)
								+ ")-" + DEFAULT_SPACE_PADDING_WIDTH);

						nChars = 0;
						nSegments++;
						nSpaces--;
					}
				}

				nSpaces++;
			} else {
				if (nChars == 0) {
					indexChar = i;

					if (nSpaces > 0) {
						if (nSegments > 0) {
							compiledObject.write(" ");
						}

						int width = nSpaces * DEFAULT_SPACE_PADDING_WIDTH;

						compiledObject.write("()-" + width);

						nSpaces = 0;
						nSegments++;
					}
				}

				nChars++;
			}
		}

		if (nChars > 0) {
			if (nSegments > 0) {
				compiledObject.write(" ");
			}

			compiledObject.write(
					"(" + s.substring(indexChar, indexChar + nChars) + ")");

		} else if (nSpaces > 0) {
			if (nSegments > 0) {
				compiledObject.write(" ");
			}

			int width = nSpaces * DEFAULT_SPACE_PADDING_WIDTH;

			compiledObject.write("()-" + width);
		}

		compiledObject.write("]\nTJ\n");
	}
}
