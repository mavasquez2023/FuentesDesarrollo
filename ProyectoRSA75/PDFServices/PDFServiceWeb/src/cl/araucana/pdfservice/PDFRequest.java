

/*
 * @(#) PDFRequest.java    1.0 25-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice;


/**
 * Represents a production/consume request to PDF process's components. It
 * encapsulates a indexed collection of parameters.
 *
 * <p> A parameter can be any object. Parameter indexes starts from 1. </p>
 * 
 * <BR>
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
 *            <TD> 25-04-2008 </TD>
 *            <TD align="center"> 1.0 </TD>
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
public class PDFRequest {

	private Object[] parameters;

	/**
	 * Constructs a request instance to support <code>nParameters</code>
	 * parameters.
	 * 
	 * @param nParameters Number of request parameters.
	 */
	public PDFRequest(int nParameters) {
		parameters = new Object[nParameters];
	}

	/**
	 * Sets request <code>parameter</code> in <code>index</code> position.
	 * 
	 * @param index Parameter index.
	 * 
	 * @param parameter Request parameter.
	 */
	public void setParameter(int index, Object parameter) {
		parameters[index] = parameter;
	}

	/**
	 * Gets request parameter in the <code>index</code> position.
	 * 
	 * @param index Parameter index.
	 * 
	 * @return Request parameter.
	 */
	public Object getParameter(int index) {
		return parameters[index];
	}

	/**
	 * Gets number of request parameters.
	 * 
	 * @return Number of request parameters.
	 */
	public int getParameterCount() {
		return parameters.length;
	}
}
