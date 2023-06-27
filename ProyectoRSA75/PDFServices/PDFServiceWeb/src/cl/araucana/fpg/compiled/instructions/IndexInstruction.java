

package cl.araucana.fpg.compiled.instructions;


/*
 * @(#) IndexInstruction.java    1.0 09-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


import cl.araucana.fpg.FPGException;

import cl.araucana.fpg.compiled.CompiledPDFObject;
import cl.araucana.fpg.compiled.Expression;
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
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 09-04-2008 </TD>
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
public class IndexInstruction extends Instruction {

	private Expression expr;
	private int[] positions;
	private String[] values;
	private int nPoints;

	public IndexInstruction(String label, Expression expr, int[] positions,
			String[] values) {

		super(
				(expr.type == FPGCode.EXP_TYPE_VARIABLE)
						? FPGCode.INDEX_VAR
						: FPGCode.INDEX_PROP,
				label,
				true);

		if (expr.subType != FPGCode.TYPE_INT) {
			throw new IllegalArgumentException(
					"Expression type must be 'int'.");
		}

		if (positions.length == 0) {
			throw new IllegalArgumentException(
					"Postions array cannot be empty'.");
		}

		if (positions.length % 2 != 0) {
			throw new IllegalArgumentException(
					"Positions array must be paired values.");
		}

		if (values.length == 0) {
			throw new IllegalArgumentException(
					"Values array cannot be empty'.");
		}

		if (values.length != 1 && values.length != positions.length / 2) {
			throw new IllegalArgumentException(
					"Values array mismatched with positions array'.");
		}

		this.expr = expr;
		this.positions = positions;
		this.values = values;

		nPoints = positions.length / 2;
	}

	public byte[] toByteArray() {
		String s = expr.index + " " + positions.length;

		for (int i = 0; i < positions.length; i += 2) {
			s += " " + positions[i] + " " + positions[i + 1];
		}

		s += " " + values.length;

		for (int i = 0; i < values.length; i ++) {
			s += " \"" + values[i] + "\"";
		}

		s += "\n";

		return s.getBytes();
	}

	public void execute(CompiledPDFObject compiledObject,
			FlowControl flowControl) throws FPGException {

		int pIndex;

		if (code == FPGCode.INDEX_PROP) {
			pIndex = compiledObject.getIntProperty(expr.index);
		} else {
			pIndex = compiledObject.getIntVariable(expr.index);
		}

		if (pIndex < 0 || pIndex >= nPoints) {
			return;
		}

		int vIndex = (pIndex < values.length) ? pIndex : 0;

		pIndex <<= 1;

		compiledObject.write(
				  "0 1 1 0 "
				+ positions[pIndex] + " "
				+ positions[pIndex + 1] + " Tm\n"
				+ "[(" + values[vIndex] + ")]\nTJ\n");
	}
}
