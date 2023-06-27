

package cl.araucana.fpg.compiled.instructions;


/*
 * @(#) Instruction.java    1.0 07-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


import cl.araucana.fpg.FPGException;

import cl.araucana.fpg.compiled.CompiledPDFObject;
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
 *            <TD> 07-04-2008 </TD>
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
public abstract class Instruction implements FPGLanguage {

	public int code;
	public String label;
	public boolean resolved;

	public Instruction(int code, String label, boolean resolved) {
		this.code = code;
		this.label = label;
		this.resolved = resolved;
	}

	public String getCodeName() {
		return FPGCode.getCodeName(code);
	}

	public byte[] toByteArray() {
		return "\n".getBytes();
	}

	public abstract void execute(CompiledPDFObject compiledObject,
			FlowControl flowControl) throws FPGException;

	public String toString() {
		String s = "";

		if (label != null) {
			s = label + ":";

		}

		s += getCodeName() + " " + new String(toByteArray());

		return s;
	}
}
