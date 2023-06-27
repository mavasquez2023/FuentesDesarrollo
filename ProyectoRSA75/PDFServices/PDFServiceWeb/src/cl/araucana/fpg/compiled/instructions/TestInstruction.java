

package cl.araucana.fpg.compiled.instructions;


/*
 * @(#) TestInstruction.java    1.0 10-04-2008
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
import cl.araucana.fpg.compiled.FPGLanguage;


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
 *            <TD> 10-04-2008 </TD>
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
public class TestInstruction extends JumpableInstruction
		implements FPGLanguage {

	private static int[] baseCodes = {
		FPGCode.TEST_BOOLEAN_EQ,
		0,							// unsupported.
		FPGCode.TEST_INT_EQ,
		FPGCode.TEST_STRING_EQ
	};

	private Expression expr1;
	private int operator;
	private Expression expr2;

	public TestInstruction(String label, Expression expr1,
			int operator, Expression expr2, String jumpLabel) {

		super(baseCodes[expr1.subType] + operator, label, jumpLabel);

		if (expr1.subType != expr2.subType) {
			throw new IllegalArgumentException(
					"Expression types must be same.");
		}

		this.expr1 = expr1;
		this.operator = operator;
		this.expr2 = expr2;
	}

	public byte[] toByteArray() {
		return (expr1 + " " + expr2 + " " + jumpLabel + "\n").getBytes();
	}

	public void execute(CompiledPDFObject compiledObject,
			FlowControl flowControl) throws FPGException {

		boolean testResult = false;

		switch (expr1.subType) {
			case TYPE_BOOLEAN:
				testResult = testBoolean(compiledObject);

				break;

			case TYPE_IMAGE:
				testResult = testImage(compiledObject);

				break;

			case TYPE_INT:
				testResult = testInt(compiledObject);

				break;

			default: // TYPE_STRING.
				testResult = testString(compiledObject);
		}

		if (testResult) {
			flowControl.flow = FlowControl.JUMP;
			flowControl.pc = address;
		}
	}

	private boolean testBoolean(CompiledPDFObject compiledObject)
			throws FPGException {

		boolean value1 = compiledObject.getBooleanValue(expr1);
		boolean value2 = compiledObject.getBooleanValue(expr2);

		return compare(booleanValue(value1) - booleanValue(value2));
	}

	private boolean testImage(CompiledPDFObject compiledObject)
			throws FPGException {

		return true;
	}

	private boolean testInt(CompiledPDFObject compiledObject)
			throws FPGException {

		int value1 = compiledObject.getIntValue(expr1);
		int value2 = compiledObject.getIntValue(expr2);

		return compare(value1 - value2);
	}

	private boolean testString(CompiledPDFObject compiledObject)
			throws FPGException {

		String value1 = compiledObject.getStringValue(expr1);
		String value2 = compiledObject.getStringValue(expr2);

		return compare(value1.compareTo(value2));
	}

	private boolean compare(int cmp) {
		switch (operator) {
			case OP_CMP_EQ:
				return cmp == 0;

			case OP_CMP_NE:
				return cmp != 0;

			case OP_CMP_GE:
				return cmp >= 0;

			case OP_CMP_GT:
				return cmp > 0;

			case OP_CMP_LE:
				return cmp <= 0;

			default: // OP_CMP_LT.
				return cmp < 0;
		}
	}

	private int booleanValue(boolean b) {
		return (b) ? 1 : 0;
	}
}
