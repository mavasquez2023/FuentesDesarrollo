
/*
 * @(#) NominaCredito.java    1.0 30-10-2006
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
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
public class NominaCredito extends Document {

	private char dvRutEmpresa;
	private int oficina;
	private int codigo;
	
	public NominaCredito(int rutEmpresa, char dvRutEmpresa, int oficina,
		int codigo) {
			
		super(rutEmpresa);
		
		this.dvRutEmpresa = dvRutEmpresa;
		this.oficina = oficina;
		this.codigo = codigo;
	}

	public int getRutEmpresa() {
		return getMemberId();
	}

	public char getDvRutEmpresa() {
		return dvRutEmpresa;
	}

	public int getOficina() {
		return oficina;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public String toString() {
		return    getRutEmpresa() + "-" + getDvRutEmpresa()
				+ "/" +  getOficina() 
				+ "/" + getCodigo();
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof NominaCredito)) {
			return false;
		}
		
		NominaCredito other = (NominaCredito) o;
		
		return     this.getRutEmpresa() == other.getRutEmpresa()
				&& this.getDvRutEmpresa() == other.getDvRutEmpresa()
				&& this.getOficina() == other.getOficina()
				&& this.getCodigo() == other.getCodigo();
	}
	
	public int hashCode() {
		return getRutEmpresa() + 10000 * getOficina() + getCodigo();
	}	
}
