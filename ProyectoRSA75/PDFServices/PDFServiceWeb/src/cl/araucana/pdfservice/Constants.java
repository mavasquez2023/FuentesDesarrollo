

/*
 * @(#) Constants.java    1.0 30-08-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.pdfservice;


/**
 * Common constants used be PDF process's components.
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
 *            <TD> 30-08-2008 </TD>
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
public interface Constants {

		/**
		 * PDF Page Prolog Object index.
		 */
		public static final int CONTENT_PROLOG     = 0;
		
		/**
		 * PDF Page Background Object index.
		 */
		public static final int CONTENT_BACKGROUND = 1;
		
		/**
		 * PDF Page Foreground Object index.
		 */
		public static final int CONTENT_FOREGROUND = 2;

		// Common PDF request parameters.
		
		/**
		 * Document Model request parameter index.
		 */
		public static final int PARAM_DOCUMENT_MODEL = 0;
		
		/**
		 * PDF Document request parameter index.
		 */
		public static final int PARAM_PDF_DOCUMENT   = 1;

		/**
		 * Number of request parameters. 
		 */
		public static final int PARAM_COUNT = 2;
}
