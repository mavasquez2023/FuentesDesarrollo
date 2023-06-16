

/*
 * @(#) Parametros.java    1.0 21-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.business;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import cl.araucana.cierrecpe.dao.CPDAO;
import cl.araucana.cierrecpe.dao.LogProcesosDAO;
import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.recursos.Today;
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
 *            <TD> 21-08-2009 </TD>
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
public class LogCierre {
	private static LogCierre instance = new LogCierre();
	private CPDAO cpDAO=null;

	public static LogCierre getInstance(){
			return instance;
	}

	public LogCierre(){
		
	}

	/**
	 * @param param el param a establecer
	 */
	public int grabarLog(int periodo, int cierre, String proceso, String inicio, String termino) {
		int idlog=0;
		try {
			this.cpDAO= new CPDAO();
			LogProcesosDAO log= new LogProcesosDAO(cpDAO.getConnection()); 
			log.insert(periodo, cierre, proceso, inicio, termino);
			cpDAO.closeConnectionDAO();
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return idlog;
	}
	

	/**
	 * @param param el param a establecer
	 */
	public Collection verLog() {
		int idlog=0;
		List logcol=null;
		try {
			this.cpDAO= new CPDAO();
			LogProcesosDAO log= new LogProcesosDAO(cpDAO.getConnection()); 
			logcol= (List)log.select(null);
			cpDAO.closeConnectionDAO();
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return logcol;
	}
}

