

package cl.araucana.fpg.compiled.instructions;


/*
 * @(#) JumpInstruction.java    1.0 09-04-2008
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
 *            <TD> 09-04-2008 </TD>
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
public class JumpInstruction extends JumpableInstruction {

	public JumpInstruction(String label, String jumpLabel) {
		super(FPGCode.JUMP, label, jumpLabel);
	}

	public void execute(CompiledPDFObject compiledObject,
			FlowControl flowControl) throws FPGException {

		flowControl.flow = FlowControl.JUMP;
		flowControl.pc = address;
	}
}
