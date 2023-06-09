
/*
 * @(#) AnexoTrabajadores.java    1.0 08-11-2006
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.ea.edocs;


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
 *            <TD> 08-11-2006 </TD>
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
public class CargasFamiliares extends Document {

	private char dvRutEmpresa;
	
	public CargasFamiliares(int rutEmpresa, char dvRutEmpresa) {
		super(rutEmpresa);
		
		this.dvRutEmpresa = dvRutEmpresa;
	}	

	public int getRutEmpresa() {
		return getMemberId();
	}

	public char getDvRutEmpresa() {
		return dvRutEmpresa;
	}

	public String toString() {
		return getRutEmpresa() + "-" + getDvRutEmpresa();
	}
	
	
	public boolean equals(Object o) {
		if (!(o instanceof CargasFamiliares)) {
			return false;
		}
		
		CargasFamiliares other = (CargasFamiliares) o;
		
		return     this.getRutEmpresa() == other.getRutEmpresa()
				&& this.getDvRutEmpresa() == other.getDvRutEmpresa();
	}
	
	public int hashCode() {
		return getRutEmpresa();
	}	
}
