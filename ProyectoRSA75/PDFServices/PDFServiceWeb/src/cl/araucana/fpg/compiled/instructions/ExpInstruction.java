

package cl.araucana.fpg.compiled.instructions;


/*
 * @(#) ExpInstruction.java    1.0 08-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.core.util.AbsoluteDateTime;
import cl.araucana.core.util.AbsoluteTime;

import cl.araucana.fpg.FPGException;
import cl.araucana.fpg.PDFDocument;

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
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 08-04-2008 </TD>
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
public class ExpInstruction extends Instruction {

	public static String GMTOffset = "-04'00'";

	static {
		try {
			GMTOffset = System.getProperty("fpg.gmtOffset", "-04'00'");
		} catch (Exception e) {}
	}

	private int propIndex;

	public ExpInstruction(String label, boolean builtin, int propIndex) {
		super((builtin) ? FPGCode.EXPX : FPGCode.EXP, label, true);

		this.propIndex = propIndex;
	}

	public byte[] toByteArray() {
		return (propIndex + "\n").getBytes();
	}

	public void execute(CompiledPDFObject compiledObject,
			FlowControl flowControl) throws FPGException {

		String s = null;

		if (code == FPGCode.EXP) {
			s = compiledObject.getStringProperty(propIndex);
		} else {
			PDFDocument document = compiledObject.document;

			switch (propIndex) {
				case BUILTIN_CURRENT_DATE:
					s = new AbsoluteDate().toString();

					break;

				case BUILTIN_CURRENT_TIME:
					s = new AbsoluteTime().toString();

					break;

				case BUILTIN_CURRENT_TIMESTAMP:
					// s = new AbsoluteDateTime() + " GMT" + GMTOffset;
					AbsoluteDateTime aDateTime = new AbsoluteDateTime();

					s =
							  "D:"
							+  aDateTime.getAbsoluteDate().toString(
							          AbsoluteDate.YMD, "")
							+  aDateTime.getAbsoluteTime().toString("")
							+ GMTOffset;

					break;

				case BUILTIN_DOC_ID:
					s = document.getID();

					break;

				case BUILTIN_DOC_INDEX:
					s = document.getIndex();

					break;

				case BUILTIN_DOC_NO:
					s = document.getSequenceNumber() + "";

					break;

				case BUILTIN_DOC_TYPE:
					s = document.getTemplate().getDocType();

					break;

				case BUILTIN_DOC_VERSION:
					s =	document.getTemplate().getDocVersion() + "";

					break;

				default: // BUILTIN_TEMPLATE_NAME.
					s = document.getTemplateName();
			}
		}

		if (s != null) {
			compiledObject.write(s);
		}
	}
}
