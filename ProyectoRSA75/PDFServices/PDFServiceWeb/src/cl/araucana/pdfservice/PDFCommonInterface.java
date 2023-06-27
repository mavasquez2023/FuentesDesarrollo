

/*
 * @(#) PDFCommonInterface.java    1.0 22-04-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.pdfservice;


import java.util.Map;


/**
 * Represents the common semantics to be implemented for any
 * PDF process's component to be executed in pipelining.
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
 *            <TD align="center"> <B>Versi�n</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripci�n</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 22-04-2008 </TD>
 *            <TD align="center"> 1.0 </TD>
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
public interface PDFCommonInterface extends Runnable {

	/**
	 * Initializes this PDF process's component previous to be
	 * ran.
	 * 
	 * <p> This method will be indirectly called by {@link PDFService}
	 * as part of corresponding PDF Process initialization.
	 * </p>
	 * 
	 * @param process Parent PDF process.
	 * 
	 * @throws PDFServiceException If initialization is failed.
	 */
	public void init(PDFProcess process) throws PDFServiceException;
	
	/**
	 * Gets parameters map of this component.
	 * 
	 * @return Parameters map.
	 */
	public Map getParameterMap();
	
	/**
	 * Destroyes or finalizes this component.
	 * 
	 * <p> This method will be indirectly called by {@link PDFService}
	 * as part of corresponding PDF Process finalization, both normal
	 * or abnormal.
	 */
	public void destroy();
}
