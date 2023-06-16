

/*
 * @(#) FiltroSucursal.java    1.0 23-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.to;

import cl.araucana.core.util.Rut;


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
 *            <TD> 23-07-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZORÍN <BR> clillo@laaraucana.cl </TD>
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
 * @author CLAUDIO LILLO AZORÍN (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class FiltroSucursal {
	private Rut rutEmpresa;
	private String id_sucursal;
	/**
	 * @return el id_sucursal
	 */
	public String getId_sucursal() {
		return id_sucursal;
	}
	/**
	 * @param id_sucursal el id_sucursal a establecer
	 */
	public void setId_sucursal(String id_sucursal) {
		this.id_sucursal = id_sucursal;
	}
	/**
	 * @return el rutEmpresa
	 */
	public Rut getRutEmpresa() {
		return rutEmpresa;
	}
	/**
	 * @param rutEmpresa el rutEmpresa a establecer
	 */
	public void setRutEmpresa(Rut rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
}

