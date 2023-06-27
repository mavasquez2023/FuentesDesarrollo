

package cl.araucana.fpg.compiled.instructions;


/*
 * @(#) SetInstruction.java    1.0 10-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


import java.awt.Image;

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
public class SetInstruction extends Instruction implements FPGLanguage {

	private int varIndex;
	private int type;
	private int assignOperator;
	private Expression expr;

	public SetInstruction(String label, int varIndex, int type,
			int assignOperator, Expression expr) {

		super(FPGCode.SET_BOOLEAN_VAR + type, label, true);

		if (assignOperator != FPGCode.OP_ASSIGN) {
			if (expr.subType != FPGCode.TYPE_INT) {
				throw new IllegalArgumentException(
						"Expression type must be 'int'.");
			}
		}

		this.varIndex = varIndex;
		this.type = type;
		this.assignOperator = assignOperator;
		this.expr = expr;
	}

	public byte[] toByteArray() {
		return (varIndex + " " + assignOperator + " " + expr + "\n").getBytes();
	}

	public void execute(CompiledPDFObject compiledObject,
			FlowControl flowControl) throws FPGException {

		if (assignOperator == OP_ASSIGN) {
			switch (type) {
				case TYPE_BOOLEAN:
					boolean booleanValue = compiledObject.getBooleanValue(expr);

					compiledObject.setBooleanVariable(varIndex, booleanValue);

					break;

				case TYPE_IMAGE:
					Image imageValue = compiledObject.getImageValue(expr);

					compiledObject.setImageVariable(varIndex, imageValue);

					break;

				case TYPE_INT:
					int intValue = compiledObject.getIntValue(expr);

					compiledObject.setIntVariable(varIndex, intValue);

					break;

				default: // TYPE_STRING.
					String stringValue = compiledObject.getStringValue(expr);

					compiledObject.setStringVariable(varIndex, stringValue);
			}

			return;
		}

		int varValue = compiledObject.getIntVariable(varIndex);
		int expValue = compiledObject.getIntValue(expr);
		int resultValue = varValue;

		switch (assignOperator) {
			case OP_ASSIGN_PLUS:
				resultValue += expValue;

				break;

			case OP_ASSIGN_MINUS:
				resultValue -= expValue;

				break;

			case OP_ASSIGN_MULTIPLY:
				resultValue *= expValue;

				break;

			default: // OP_ASSIGN_DIVIDY.
				resultValue /= expValue;
		}

		compiledObject.setIntVariable(varIndex, resultValue);
	}
}
