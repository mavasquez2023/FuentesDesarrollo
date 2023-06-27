

package cl.araucana.fpg.compiled.instructions;


/*
 * @(#) BarCodePDF417Instruction.java    1.0 13-03-2010
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


import cl.araucana.fpg.FPGException;

import cl.araucana.fpg.barcodes.EAN128BarCode;

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
 *            <TD> 13-03-2010 </TD>
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
public class BarCodeEAN128Instruction extends Instruction {

	private EAN128BarCode barCode;
	private int propIndex;

	public BarCodeEAN128Instruction(String label, EAN128BarCode barCode,
			int propIndex) {

		super(FPGCode.BARCODE_EAN128, label, true);

		this.propIndex = propIndex;
		this.barCode = barCode;
	}

	public byte[] toByteArray() {
		return (propIndex + " " + barCode + "\n").getBytes();
	}

	public void execute(CompiledPDFObject compiledObject,
			FlowControl flowControl) throws FPGException {

		String text = compiledObject.getStringProperty(propIndex);

		if (text == null) {
			return;
		}

		try {
			byte[] data = barCode.generate(text);

			compiledObject.write(data);
		} catch (Exception e) {
			throw new FPGException("cannot generate barcode EAN128.", e);
		}
	}
}
