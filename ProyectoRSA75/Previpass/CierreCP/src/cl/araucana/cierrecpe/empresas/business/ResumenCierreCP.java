

/*
 * @(#) GenerarPlanillas.java    1.0 21-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.business;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.dao.CPDAO;
import cl.araucana.cierrecpe.empresas.dao.PropuestaPagoDAO;
import cl.araucana.cierrecpe.empresas.dao.ResumenCierreCPDAO;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.Formato;

/*
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
 *            <TD> 21-07-2009 </TD>
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
public class ResumenCierreCP implements Constants{
	private CPDAO cpDAO=null;
	private static Logger logger = LogManager.getLogger();
	
	public ResumenCierreCP() throws SQLException{
		cpDAO= new CPDAO();
		//cpDAO.setAutoCommit(false);
	}
	
	public Collection generarEstadisticasComprobantes() {
		try {
			logger.finer("Se solicita Ver Estadísticas Propuestas de Pago");
			ResumenCierreCPDAO resumenDAO= new ResumenCierreCPDAO(cpDAO.getConnection());
			return (List)resumenDAO.selectEstadistica();
		} catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	public Collection getListCierresPlanillas(int periodo){
		try {
			PropuestaPagoDAO propuestaDAO= new PropuestaPagoDAO(cpDAO.getConnection());
			List cierres= (List)propuestaDAO.selectListCierresResumen(periodo);
			return cierres;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Collection getListPeriodos(){
		try {
			PropuestaPagoDAO propuestaDAO;
			propuestaDAO= new PropuestaPagoDAO(cpDAO.getConnection());
			return (List)propuestaDAO.selectListPeriodos();
		}
		catch (SQLException e) {
			logger.severe("Error mensaje=" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public Collection getResumenCierreHistorico(int periodo){
		try {
			ResumenCierreCPDAO resumenDAO= new ResumenCierreCPDAO(cpDAO.getConnection());
			//Se busca todos los cierres y cantidad de comprobantes generados, con y sin planillas
			List cierresPlanillas= (List)resumenDAO.selectCountResumenHistorico(periodo);
			logger.info("Número cierres disponibles periodo " + periodo + " = " + cierresPlanillas.size());
			return cierresPlanillas;
		}
		catch (SQLException e) {
			logger.severe("Mensaje: " +e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public String nombreEsquema(String esquemaFormato, int periodo, int cierre){
		String esquemaDestino= esquemaFormato.replaceAll("AAMM", String.valueOf(periodo).substring(2, 6));
		esquemaDestino= esquemaDestino.replaceAll("XXX", Formato.padding(cierre, 3));
		return esquemaDestino;
	}
	
	
	 public void close(){
		cpDAO.closeConnectionDAO();
	 }
	
}

