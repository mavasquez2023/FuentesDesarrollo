/*
 * @(#) HTMLCodeBuilder.java    1.0 29-07-2010
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y estï¿¡
 * restringido a los sistemas de información propios de la institución.
 */
package publicacion.html;

import java.util.Arrays;

import publicacion.Constants;


/**
 * Clase utilitaria para generar codigo HTML para la clase PaginaPresentacion
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
 *            <TD> 29-07-2010 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Juan Pablo López N. <BR> jlopez@laaraucana.cl </TD>
 *            <TD> Versión inicial. </TD>
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
 * @author Juan Pablo López N. (jlopez@laaraucana.cl)
 *
 * @version 1.0
 */
public class HTMLCodeBuilder implements Constants {

    public static String buildAFPSelectList() {
    	
    	String optionEntries = "";
    	optionEntries += buildOptionEntry(OPTION_VALUE_TODAS, OPTION_LABEL_TODAS);

    	//Ordenamiento
    	Arrays.sort(AFPS);

    	//AFPS
    	for(int i = 0 ; i < AFPS.length; i++) {
    		optionEntries += buildOptionEntry(AFPS[i], AFPS[i]);
    	}

    	return buildSelectList(SELECT_ID_AFP, SELECT_NAME_AFP, optionEntries);
    }

    public static String buildAPVSelectList() {
    	
    	String optionEntries = "";
    	optionEntries += buildOptionEntry(OPTION_VALUE_TODAS, OPTION_LABEL_TODAS);

    	//Ordenamiento
    	Arrays.sort(AFPS);

    	//AFPS
    	for(int i = 0 ; i < AFPS.length; i++) {
    		optionEntries += buildOptionEntry(AFPS[i], AFPS[i]);
    	}

    	//TODO agregar APV que no son AFP
    	
    	return buildSelectList(SELECT_ID_APV, SELECT_NAME_APV, optionEntries);
    }
    
    private static String buildSelectList(String id, String name, String optionEntries) {
    	String selectList = "<select id='" + id + "' name='" + name
		+ "' size='1' class='campos' >";
    	selectList += optionEntries;
    	selectList += "</select>";
    	return selectList;
    }
    
    private static String buildOptionEntry(String value, String label) {
    	String optionHTMLCode = "<option value='" + value + "'>" + label + "</option>";
    	return optionHTMLCode;
    }
	
}

