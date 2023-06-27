
/*
 * @(#) Encargado.java    1.0 30-10-2006
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.ea.edocs.re;


import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import cl.araucana.ea.ctacte.dto.EmpresaTO;
import cl.araucana.ea.ctacte.dto.RutTO;
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
 *            <TD align="center"> <B>Versi�n</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripci�n</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 30-10-2006 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Germ�n Pavez I. <BR> gpavez@hotmail.com </TD>
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
 * @author Germ�n Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class Encargado {
	
		
	private String rut;
	private String fullName;
	private Map empresas = new TreeMap();
	
	public Encargado() {	
	}
	
	public Encargado(String rut, String fullName, Collection empresas) {
		this.rut = rut;
		this.fullName = fullName;
				
		Iterator iterator = empresas.iterator();
		
		while (iterator.hasNext()) {
			EmpresaTO empresaTO = (EmpresaTO) iterator.next();
			RutTO rutEmpresa = empresaTO.getRut();
			String nombreEmpresa = empresaTO.getNombre();
			Empresa empresa =
					new Empresa(
							rutEmpresa.getRut() + "-" + rutEmpresa.getDv(),
							nombreEmpresa);
	
			this.empresas.put(new Integer(empresa.getRut()), empresa);
		}
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

	public String getFullName() {
		return fullName;
	}
		
	public void addEmpresa(Empresa empresa) {
		empresas.put(new Integer(empresa.getRut()), empresa);
	}
	
	public Collection getEmpresas() {
		return empresas.values();
	}
	
	public int getEmpresasCount() {
		return empresas.size();
	}
}
