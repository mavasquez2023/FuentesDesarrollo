

package cl.araucana.fpg.compiled;


/*
 * @(#) Property.java    1.0 07-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


import java.awt.Image;

import java.lang.reflect.Method;

import cl.araucana.fpg.DocumentModel;
import cl.araucana.fpg.FPGException;


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

public class Property extends NamedObject {

	private static final Object[] withoutArgs = new Object[0];

	public Method method;

	public Property(String name) {
		this.name = name;
	}

	public Property(String name, Method method) {
		this.name = name;
		this.method = method;
	}

	public int getInteger(DocumentModel docModel) throws FPGException {

		Integer value = (Integer) getValue(docModel);

		return value.intValue();
	}

	public String getString(DocumentModel docModel)
			throws FPGException {

		return (String) getValue(docModel);
	}

	public boolean getBoolean(DocumentModel docModel)
			throws FPGException {

		Boolean value = (Boolean) getValue(docModel);

		return value.booleanValue();
	}

	public Image getImage(DocumentModel docModel)
			throws FPGException {

		return (Image) getValue(docModel);
	}

	public Object getValue(DocumentModel docModel) throws FPGException {

		try {
			return method.invoke(docModel, withoutArgs);
		} catch (Exception e) {
			String className = docModel.getClass().getName();

			throw new FPGException(
					  "Cannot get value of property '" + name + "' "
					+ "[" + className + "." + method.getName() + "()]",
					e);
		}
	}

	public String toString() {
		return name + " -> " + method.getName() + "()";
	}
}
