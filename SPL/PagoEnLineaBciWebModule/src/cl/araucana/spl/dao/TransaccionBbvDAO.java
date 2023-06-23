package cl.araucana.spl.dao;


import cl.araucana.spl.beans.TransaccionBbv;

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

public interface TransaccionBbvDAO {
	public void insertTrx(TransaccionBbv trx);
	public TransaccionBbv findTransaccionByCodigoIdTrx(String codigoIdTrx);
	public void updateNotificacion(TransaccionBbv trx);
	public void updateURLEnviaTrx(TransaccionBbv trx);
}
