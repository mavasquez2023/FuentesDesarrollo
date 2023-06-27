

package cl.araucana.fpg.compiled.instructions;


/*
 * @(#) DupInstruction.java    1.0 08-04-2008
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
public class DupInstruction extends Instruction {

	private byte[] data;

	public DupInstruction(String label) {
		this(label, false);
	}

	public DupInstruction(String label, boolean noNewline) {
		super((noNewline) ? FPGCode.DUPN : FPGCode.DUP, label, true);
	}

	public void setData(byte[] data, int offset, int length) {
		if (code == FPGCode.DUP) {
			this.data = new byte[length];

			System.arraycopy(data, offset, this.data, 0, length);
		} else {
			this.data = new byte[length + 1];

			System.arraycopy(data, offset, this.data, 0, length);

			this.data[length] = '\n';
		}
	}

	public byte[] getData() {
		return data;
	}

	public byte[] toByteArray() {
		return data;
	}

	public void execute(CompiledPDFObject compiledObject,
			FlowControl flowControl) throws FPGException {

		if (code == FPGCode.DUP) {
			compiledObject.write(data);
		} else {
			compiledObject.write(data, 0, data.length - 1);
		}
	}
}
