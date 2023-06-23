package cl.araucana.spl.beans.xmlbit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
 *            <TD> 13-01-2014 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Gonzalo Mallea Lorca <BR> gmallea@schema.cl </TD>
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
 * @author Gonzalo Mallea Lorca (gmallea@schema.cl)
 *
 * @version 1.0
 */

public class ArchivoRendicion {
	private TotalizadorPagos totalizador;
	private List detalles = new ArrayList();
	
	public String toString() {
		StringBuffer sb = new StringBuffer("[ArchivoRendicion::");
		sb.append("totalizador=" + totalizador);
		for (Iterator iter = detalles.iterator(); iter.hasNext();) {
			DetallePagos detalle = (DetallePagos) iter.next();
			sb.append(",detalle=" + detalle);
		}
		sb.append("]");
		return sb.toString();
	}		
	
	public List getDetalles() {
		return detalles;
	}
	public void setDetalles(List detalles) {
		this.detalles = detalles;
	}
	public TotalizadorPagos getTotalizador() {
		return totalizador;
	}
	public void setTotalizador(TotalizadorPagos totalizador) {
		this.totalizador = totalizador;
	}
	public void addDetalle(DetallePagos detallePagos) {
		detalles.add(detallePagos);
	}
}
