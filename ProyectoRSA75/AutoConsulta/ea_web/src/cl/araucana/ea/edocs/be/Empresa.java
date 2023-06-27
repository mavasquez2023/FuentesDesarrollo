

/*
 * @(#) Empresa.java    1.0 30-10-2006
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.ea.edocs.be;


import java.util.HashMap;
import java.util.Map;

import cl.araucana.ea.edocs.util.Padder;


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
 *            <TD> 30-10-2006 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
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
 * @author Germán Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class Empresa implements Comparable {
	
	private String rut;
	private String name;
	
	private int flag;
	
	public Empresa() {
	}
	
	public Empresa(String rut, String name) {
		this.rut = rut;
		this.name = name;
		flag = 0;

	}
		
	public String getFullRut() {
		return rut;
	}

	public int getRut() {
		String auxRut = getFullRut();
		int index = auxRut.indexOf("-");
		
		if (index > 0) {
			return Integer.parseInt(auxRut.substring(0, index));
		}
		
		return Integer.parseInt(auxRut);
	}

	public char getDV() {
		String auxRut = getFullRut();
		
		return auxRut.charAt(auxRut.length() - 1);
	}

	public String getFormattedRut() {
		int rut = getRut();
		 
		return Padder.padSeparators(rut, '.') + "-" + getDV();
	}

	public String getName() {
		return name;
	}

	public int compareTo(Object object) {
		Empresa empresa = (Empresa) object;
			
		return getFullRut().compareTo(empresa.getFullRut());
	}
	

	public int getFlag() {
		return flag;
	}

	public void setFlag(int b) {
		flag = b;
	}

}
