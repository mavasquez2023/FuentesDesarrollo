

package cl.araucana.fpg.compiled;


/*
 * @(#) FPGLanguage.java    1.1 23-07-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
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
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 08-04-2008 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 23-07-2008 </TD>
 *            <TD align="center">  1.1 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Se agrega soporte para instrucción 'xybase'. </TD>
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
public interface FPGLanguage {

	/*
	 * High Level.
	 */

	/*
	 * Supported data types.
	 */
	public static final int NTYPES = 4;

	public static final int TYPE_BOOLEAN = 0;
	public static final int TYPE_IMAGE   = 1;
	public static final int TYPE_INT     = 2;
	public static final int TYPE_STRING  = 3;

	public static final String[] typeNames = {
		"boolean",
		"image",
		"int",
		"string"
	};

	/*
	 * Supported builtin properties.
	 */
	public static final int BUILTIN_CURRENT_DATE      = 0;
	public static final int BUILTIN_CURRENT_TIME      = 1;
	public static final int BUILTIN_CURRENT_TIMESTAMP = 2;
	public static final int BUILTIN_DOC_ID            = 3;
	public static final int BUILTIN_DOC_NO            = 4;
	public static final int BUILTIN_DOC_TYPE          = 5;
	public static final int BUILTIN_DOC_VERSION       = 6;
	public static final int BUILTIN_TEMPLATE_NAME     = 7;

	// docIndex was added after.
	public static final int BUILTIN_DOC_INDEX         = 8;

	public static final String[] builtinProperties = {
		"currentDate",
		"currentTime",
		"currentTimestamp",
		"docID",
		"docNo",
		"docType",
		"docVersion",
		"templateName",
		"docIndex",
	};

	/*
	 * Statements.
	 */
	public static final int STMT_BARCODE       = 0;
	public static final int STMT_BEGIN_CACHE   = 1;
	public static final int STMT_DECLARE       = 2;
	public static final int STMT_END_CACHE     = 3;
	public static final int STMT_FONT          = 4;
	public static final int STMT_GOTO          = 5;
	public static final int STMT_IMAGE         = 6;
	public static final int STMT_INCLUDE_CACHE = 7;
	public static final int STMT_INDEX         = 8;
	public static final int STMT_LABEL         = 9;
	public static final int STMT_LOCATE        = 10;
	public static final int STMT_SET           = 11;
	public static final int STMT_TEST          = 12;
	public static final int STMT_TEXT          = 13;
	public static final int STMT_XYBASE        = 14;

	public static final String[] stmtNames = {
		"barcode",
		"beginCache",
		"declare",
		"endCache",
		"font",
		"goto",
		"image",
		"includeCache",
		"index",
		"label",
		"locate",
		"set",
		"test",
		"text",
		"xybase"
	};

	public static final String[] reservedNames = {
		"true",
		"false"
	};

	public static final String[] barCodeTypeNames = {
		"ean128",
		"pdf417"
	};

	public static final int IMAGE_GIF  = 0;
	public static final int IMAGE_JPEG = 1;

	public static final String[] imageTypeNames = {
		"gif",
		"jpeg"
	};

	/*
	 *  Fields used in named object descriptor.
	 */
	public static final int TYPE  = 0;
	public static final int INDEX = 1;

	/*
	 * Expression types.
	 */
	public static final int EXP_TYPE_VARIABLE = 0;
	public static final int EXP_TYPE_PROPERTY = 1;
	public static final int EXP_TYPE_LITERAL  = 2;

	/*
	 * Maximum length for variable, property and cache names.
	 */
	public static final int MAX_ID_LENGTH = 64;

	public static final int OP_ASSIGN           = 0;
	public static final int OP_ASSIGN_PLUS      = 1;
	public static final int OP_ASSIGN_MINUS     = 2;
	public static final int OP_ASSIGN_MULTIPLY  = 3;
	public static final int OP_ASSIGN_DIVIDY    = 4;

	public static final String[] assignOperators = {
		"=",
		"+=",
		"-=",
		"*=",
		"/="
	};

	public static final int OP_CMP_EQ = 0;
	public static final int OP_CMP_GE = 1;
	public static final int OP_CMP_GT = 2;
	public static final int OP_CMP_LE = 3;
	public static final int OP_CMP_LT = 4;
	public static final int OP_CMP_NE = 5;

	public static final String[] comparationOperators = {
		"==",
		">=",
		">",
		"<=",
		"<",
		"!="
	};

	public static final String[] comparationOperatorNames = {
		"eq",
		"ge",
		"gt",
		"le",
		"lt",
		"ne"
	};

	/*
	 * Low Level.
	 */

	/*
	 *  mnemonics for every instruction.
	 */
	public static int BARCODE_EAN128   = 0;
	public static int BARCODE_PDF417   = 1;
	public static int BEGIN_CACHE      = 2;
	public static int DUP              = 3;
	public static int DUPN             = 4;
	public static int END              = 5;
	public static int END_CACHE        = 6;
	public static int EXP              = 7;
	public static int EXPX             = 8;
	public static int FONT             = 9;
	public static int IMAGE            = 10;
	public static int INCLUDE_CACHE    = 11;
	public static int INDEX_PROP       = 12;
	public static int INDEX_VAR        = 13;
	public static int JUMP             = 14;
	public static int LOCATE           = 15;
	public static int SET_BOOLEAN_VAR  = 16;
	public static int SET_IMAGE_VAR    = 17;
	public static int SET_INT_VAR      = 18;
	public static int SET_STRING_VAR   = 19;
	public static int TEST_BOOLEAN_EQ  = 20;
	public static int TEST_BOOLEAN_GE  = 21;
	public static int TEST_BOOLEAN_GT  = 22;
	public static int TEST_BOOLEAN_LE  = 23;
	public static int TEST_BOOLEAN_LT  = 24;
	public static int TEST_BOOLEAN_NE  = 25;
	public static int TEST_INT_EQ      = 26;
	public static int TEST_INT_GE      = 27;
	public static int TEST_INT_GT      = 28;
	public static int TEST_INT_LE      = 29;
	public static int TEST_INT_LT      = 30;
	public static int TEST_INT_NEQ     = 31;
	public static int TEST_STRING_EQ   = 32;
	public static int TEST_STRING_GE   = 33;
	public static int TEST_STRING_GT   = 34;
	public static int TEST_STRING_LE   = 35;
	public static int TEST_STRING_LT   = 36;
	public static int TEST_STRING_NE   = 37;
	public static int TEXT             = 38;
	public static int TEXT_PAD         = 39;
	public static int XYBASE           = 40;

	public static final String[] codeNames = {
		"barcode_ean128",
		"barcode_pdf417",
		"beginCache",
		"dup",
		"dupn",
		"end",
		"endCache",
		"exp",
		"expx",
		"font",
		"image",
		"includeCache",
		"index_prop",
		"index_var",
		"jump",
		"locate",
		"set_boolean_var",
		"set_image_var",
		"set_int_var",
		"set_string_var",
		"test_boolean_eq",
		"test_boolean_ge",
		"test_boolean_gt",
		"test_boolean_le",
		"test_boolean_lt",
		"test_boolean_ne",
		"test_int_eq",
		"test_int_ge",
		"test_int_gt",
		"test_int_le",
		"test_int_lt",
		"test_int_ne",
		"test_string_eq",
		"test_string_ge",
		"test_string_gt",
		"test_string_le",
		"test_string_lt",
		"test_string_ne",
		"text",
		"text_pad",
		"xybase"
	};

	public static final int NOT_FOUND = -1;
}
