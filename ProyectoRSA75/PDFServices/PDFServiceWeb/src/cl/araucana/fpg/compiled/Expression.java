

package cl.araucana.fpg.compiled;


/*
 * @(#) Expression.java    1.0 08-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


import java.awt.Image;


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

public class Expression implements FPGLanguage {

	public int type;
	public int subType;
	public int index;
	public Object value;

	public static Expression getExpression(String sType, int subType,
			String sValue) {

		int type = Integer.parseInt(sType);
		Object value = null;

		if (type == EXP_TYPE_LITERAL) {
			switch (subType) {
				case TYPE_BOOLEAN:
					value = new Boolean(sValue);
					break;

				case TYPE_IMAGE:
					value = null;
					break;

				case TYPE_INT:
					value = new Integer(sValue);
					break;

				case TYPE_STRING:
					value = sValue;
					break;
			}

			return new Expression(subType, value);
		}

		int index = Integer.parseInt(sValue);

		return new Expression(type, subType, index);
	}

	public Expression(int type, int subType, int index) {
		this(type, subType, index, null);
	}

	public Expression(int subType, Object value) {
		this(EXP_TYPE_LITERAL, subType, 0, value);
	}

	public Expression(int type, int subType, int index, Object value) {
		this.type = type;
		this.subType = subType;
		this.index = index;
		this.value = value;
	}

	public boolean getBoolean() {
		return ((Boolean) value).booleanValue();
	}

	public Image getImage() {
		return (Image) value;
	}

	public int getInt() {
		return ((Integer) value).intValue();
	}

	public String getString() {
		return (String) value;
	}

	public String toString() {
		if (value == null) {
			return type + " " + index;
		}

		if (value instanceof String) {
			return type + " \"" + value + "\"";
		}

		return type + " " + value;
	}
}
