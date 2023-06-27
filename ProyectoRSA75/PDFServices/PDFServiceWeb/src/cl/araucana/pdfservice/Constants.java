

/*
 * @(#) Constants.java    1.0 30-08-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
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
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 30-08-2008 </TD>
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
