
/*
 * @(#) RendicionBesDAO.java    1.0 06-08-2009
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */

package cl.araucana.spl.dao;

import java.math.BigDecimal;

import cl.araucana.spl.beans.DetalleRendicionBES;
import cl.araucana.spl.beans.RendicionBES;
import cl.araucana.spl.beans.ResumenDetalleRendicionBES;

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
 *            <TD> 06-08-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Jorge Landeros <BR> jlandero@schema.cl </TD>
 *            <TD> Versi�n inicial. Contiene las firmas de los m�todos para el manejo de una rendici�n del banco estado.
 *            </TD>
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
 * @author Lorge Landeros (jlandero@schema.cl)
 *
 * @version 1.0
 */

public interface RendicionBesDAO {
	public BigDecimal countRendicionByChecksum(String checksum);
	public DetalleRendicionBES getDetalleRendicionBesByPagoId(BigDecimal idPago);
	public BigDecimal insertRendicion(RendicionBES rendicionBES);
	public BigDecimal insertResumenDetalleRendicion(ResumenDetalleRendicionBES resumenDetalleRendicionBES);
	public void insertDetalleRendicion(DetalleRendicionBES detalleRendicionBES);
	
}
