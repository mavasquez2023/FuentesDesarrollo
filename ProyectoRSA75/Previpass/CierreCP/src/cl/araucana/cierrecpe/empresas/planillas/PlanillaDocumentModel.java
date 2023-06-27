

/*
 * @(#) PlanillaDocumentModel.java    1.0 05-05-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.cierrecpe.empresas.planillas;


import java.util.Collection;

import cl.araucana.core.util.AbsoluteDate;


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
 *            <TD> 05-05-2008 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Claudio Lillo <BR> clillo@laaraucana.cl </TD>
 *            <TD> Versi�n inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Claudio Lillo (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public abstract class PlanillaDocumentModel{
	
	public abstract String docID();

	public abstract IdentificacionSucursal getDatosSucursal();

	public abstract String getFolio();

	public abstract String getNombreEntidad();

	public abstract int getNumeroAfiliadosInformados();
	
	public abstract int getNumeroHojasAnexas();

	public abstract Collection getPaginasDetalle();
	
	public abstract String getSecuenciaFolio();
	
	public abstract long getTotalAPagar();
	
	public abstract IdentificacionEmpleador getDatosEmpleador();
	
	public abstract int getFechaPago();

	public abstract int getPeriodo();

	public abstract String getTipoProceso();
	
}
