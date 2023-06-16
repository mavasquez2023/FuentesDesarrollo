

/*
 * @(#) Parametros.java    1.0 21-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.ctasfam.resources.util;

import cl.araucana.ctasfam.business.to.ParametrosTO;


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
 *            <TD> 12-06-2018 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZORÍN <BR> clillo007@gmail.com </TD>
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
 * @author CLAUDIO LILLO AZORÍN (clillo007@gmail.com)
 *
 * @version 1.0
 */
public class Parametros {
	private static Parametros instance = new Parametros();
	ParametrosTO param=null;

	public static Parametros getInstance(){
			return instance;
	}

	public Parametros(){
	}

	/**
	 * @return el param
	 */
	public ParametrosTO getParam() {
		return param;
	}

	/**
	 * @param param el param a establecer
	 */
	public void setParam(ParametrosTO param) {
		this.param = param;
	}
}

