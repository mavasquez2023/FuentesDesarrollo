

/*
 * @(#) CompilationOptions.java    1.0 11-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg;


/**
 * Set of options used by {@link cl.araucana.fpg.tools.FPGCompiler} and
 * {@link PDFObjectCompiler} components.
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
 *            <TD> 11-04-2008 </TD>
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
public class CompilationOptions {

	/**
	 * Constructs a default set of compilation options. 
	 */
	public CompilationOptions() {
	}
	
	/**
	 * Verbose control flag. 
	 */
	public boolean verbose;
	
	/**
	 * Indicates if compilation unit must be check a PDF template
	 * againt document model class. Default is no check.
	 */
	public boolean checkDocModel;
	
	/**
	 * Full document model class name.
	 */
	public String docModelClassName;
	
	/**
	 * Full document model class.
	 */
	public Class docModelClass;
	
	/**
	 * Indicates if respective document model must be generated. Default
	 * is no generate.
	 */
	public boolean generateDocModel;
	
	/**
	 * Package name for the generated document model. Default is anonymous
	 * package.
	 */
	public String packageName;
	
	/**
	 * Class name for the generated document model.
	 */
	public String className;
	
	/**
	 * Output directory to save generated java source for document model.
	 * Default is current directory.
	 */
	public String sourceDir;
}