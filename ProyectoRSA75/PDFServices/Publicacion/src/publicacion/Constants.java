

/*
 * @(#) Constants.java    1.0 29-07-2010
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package publicacion;


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
 *            <TD> 29-07-2010 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Juan Pablo L�pez N. <BR> jlopez@laaraucana.cl </TD>
 *            <TD> Versi�n inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Juan Pablo L�pez N. (jlopez@laaraucana.cl)
 *
 * @version 1.0
 */
public interface Constants {

	public static final String OPTION_VALUE_TODAS = "";
	
	public static final String OPTION_LABEL_TODAS = "Todas";
	
	public static final String SELECT_ID_AFP = "NombreAFP";
	
	public static final String SELECT_NAME_AFP = "NombreAFP";
	
	public static final String SELECT_ID_APV = "NombreAPV";
	
	public static final String SELECT_NAME_APV = "NombreAPV";
	
	public static final String[] AFPS = {
		//actuales
		"AFP CAPITAL S.A.",
		"AFP MODELO S.A.",
		"CUPRUM S.A.",
		"HABITAT S.A.",
		"PLANVITAL",
		"PROVIDA",
		
		//deprecadas
		"BANSANDER S.A.",
		"ING AFP SANTA MARIA",
		"MAGISTER"
		};
	
}

