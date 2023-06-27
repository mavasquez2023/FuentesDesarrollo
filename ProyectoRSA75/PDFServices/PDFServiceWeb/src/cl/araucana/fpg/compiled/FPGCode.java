

package cl.araucana.fpg.compiled;


/*
 * @(#) FPGCode.java    1.0 08-04-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


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
public class FPGCode implements FPGLanguage {

	public static String getCodeName(int code) {

		if (code < 0 || code >= codeNames.length) {
			return null;
		}

		return codeNames[code];
	}

	public static String getTypeName(int type) {

		if (type < 0 || type >= typeNames.length) {
			return null;
		}

		return typeNames[type];
	}

	public static String getComparationOperatorName(int operator) {

		if (operator < 0 || operator >= comparationOperatorNames.length) {
			return null;
		}

		return comparationOperatorNames[operator];
	}

	public static String getBarCodeTypeName(int type) {

		if (type < 0 || type >= barCodeTypeNames.length) {
			return null;
		}

		return barCodeTypeNames[type];
	}

	public static String getImageTypeName(int type) {

		if (type < 0 || type >= imageTypeNames.length) {
			return null;
		}

		return imageTypeNames[type];
	}
	public static int getStmt(String name) {
		return indexOfStringArray(name, stmtNames);
	}

	public static int getCode(String name) {
		return indexOfStringArray(name, codeNames);
	}

	public static boolean isReservedName(String name) {
		return indexOfStringArray(name, reservedNames) != NOT_FOUND;
	}

	public static int indexOfAssignOperator(String operator) {
		return indexOfStringArray(operator, assignOperators);
	}

	public static int indexOfComparationOperator(String operator) {
		return indexOfStringArray(operator, comparationOperators);
	}

	public static int indexOfBuiltInProperty(String propertyName) {
		return indexOfStringArray(propertyName, builtinProperties);
	}

	public static int indexOfStatement(String stmtName) {
		return indexOfStringArray(stmtName, stmtNames);
	}

	public static int indexOfTypeName(String typeName) {
		return indexOfStringArray(typeName, typeNames);
	}

	public static int indexOfBarCodeTypeName(String barCodeTypeName) {
		return indexOfStringArray(barCodeTypeName, barCodeTypeNames);
	}

	public static int indexOfImageTypeName(String imageTypeName) {
		return indexOfStringArray(imageTypeName, imageTypeNames);
	}

	public static int indexOfStringArray(String s, String[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(s)) {
				return i;
			}
		}

		return NOT_FOUND;
	}

	public static boolean isID(String token) {
		/*
		 * [a-zA-Z]([a-zA-Z0-9])*
		 */
		if (token.length() == 0) {
			return false;
		}

		char firstChar = token.charAt(0);

		if (!Character.isLetter(firstChar)) {
			return false;
		}

		for (int i = 1; i < token.length(); i++) {
			char nextChar = token.charAt(i);

			if (!Character.isLetter(nextChar)
						&& !Character.isDigit(nextChar)) {

				return false;
			}
		}

		if (token.length() > MAX_ID_LENGTH) {
			return false;
		}

		return !isReservedName(token);
	}
}
