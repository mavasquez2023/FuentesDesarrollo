

package  cl.araucana.pdf.publishers.implementations.fpg;


/*
 * @(#) DocTypeConstants.java    1.0 30-06-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


/**
 * This interface groups general constants used to manage document types.
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
 *            <TD> 30-06-2008 </TD>
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
public interface DocTypeConstants {

	/**
	 * Integer field type code.
	 */
	public static final int FIELD_TYPE_INT    = 0;
	
	/**
	 * Character field type code.
	 */
	public static final int FIELD_TYPE_CHAR   = 1;
	
	/**
	 * NDate field type code.
	 */
	public static final int FIELD_TYPE_NDATE  = 2;
	
	/**
	 * String field type code.
	 */
	public static final int FIELD_TYPE_STRING = 3;

	/**
	 *  Field type names. 
	 */
	public static final String[] fieldTypeNames = {
		"int",
		"char",
		"ndate",
		"string"
	};

	/**
	 * Document field type name maximum length in characters.
	 */		
	public static final int FIELD_TYPE_NAME_MAX_LENGTH = 6;

	/**
	 * Number of partitions to a partitioned PDF document. 
	 */
	public static final int NPARTITIONS = 4;
	
	/**
	 *  Number of partitions to a nonpartitioned PDF document.
	 */
	public static final int NONVERSIONED_NPARTITIONS = 1;

	/**
	 * Content index for a nonpartitioned PDF document.
	 */
	public static final int ALL_PDF_CONTENT = 0;

	/**
	 *  Formal partition names. 
	 */
	public static final String[] partitionNames = {
		"foc",
		"voc",
		"fmc",
		"vmc"
	};

	/**
	 *  Partition index for Fixed PDF Objects Content. 
	 */
	public static final int FOC = 0;

	/**
	 *  Partition index for Variable PDF Objects Content. 
	 */
	public static final int VOC = 1;

	/**
	 *  Partition index for Fixed PDF Metadata Content. 
	 */
	public static final int FMC = 2;

	/**
	 *  Partition index for Variable PDF Metadata Content. 
	 */
	public static final int VMC = 3;

	/**
	 * Source system ID maximum length in characters.
	 */
	public static final int SS_ID_MAX_LENGTH                =  6;

	/**
	 * Source system name maximum length in characters.
	 */	
	public static final int SS_NAME_MAX_LENGTH              = 20;
	
	/**
	 * Source system description maximum length in characters.
	 */	
	public static final int SS_DESCRIPTION_MAX_LENGTH       = 60;

	/**
	 * Publisher agent ID maximum length in characters.
	 */	
	public static final int PUB_ID_MAX_LENGTH               =  6;
	
	/**
	 * Publisher agent name maximum length in characters.
	 */		
	public static final int PUB_NAME_MAX_LENGTH             = 20;

	/**
	 * Document type name maximum length in characters.
	 */	
	public static final int DOC_TYPE_NAME_MAX_LENGTH        = 40;

	/**
	 * Document type base name maximum length in characters.
	 */
	public static final int DOC_TYPE_BASE_NAME_MAX_LENGTH   = 40;
	
	/**
	 * Document type remark maximum length in characters.
	 */	
	public static final int DOC_TYPE_REMARK_MAX_LENGTH      = 60;

	/**
	 * Document field length maximum length in digits.
	 */	
	public static final int DOC_FIELD_LENGTH_MAX_LENGTH     =  5;
	
	/**
	 * Document field name maximum length in characters.
	 */	
	public static final int DOC_FIELD_NAME_MAX_LENGTH       = 40;
	
	/**
	 * Document field label name maximum length in characters.
	 */		
	public static final int DOC_FIELD_LABEL_NAME_MAX_LENGTH = 40;
	
	/**
	 * Document field remark maximum length in characters.
	 */		
	public static final int DOC_FIELD_REMARK_MAX_LENGTH     = 60;
	
	/**
	 * Document version remark maximum length in characters.
	 */		
	public static final int DOC_VERSION_REMARK_MAX_LENGTH   = 40;	
	
	/**
	 * Document publication remark maximum length in characters.
	 */		
	public static final int DOC_PUB_REMARK_MAX_LENGTH       = 40;	
}
