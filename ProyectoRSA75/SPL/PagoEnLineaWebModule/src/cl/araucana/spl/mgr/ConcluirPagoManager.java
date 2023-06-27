package cl.araucana.spl.mgr;

import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.spl.beans.FiltroConcluirPago;
import cl.araucana.spl.dao.EstadoDAO;
import cl.araucana.spl.dao.PagoDAO;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.exceptions.PagoEnLineaException;

import com.ibatis.dao.client.DaoManager;

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

public class ConcluirPagoManager {
	private static final Logger logger = Logger.getLogger(InconsistenciasManager.class);

	private EstadoDAO estadoDAO;
	private PagoDAO pagoDAO;

	
	public ConcluirPagoManager() {
		DaoManager mgr = DaoConfig.getDaoManager();
		estadoDAO = (EstadoDAO) mgr.getDao(EstadoDAO.class);
		pagoDAO = (PagoDAO) mgr.getDao(PagoDAO.class);
	}
	
	public List getEstados() {
		return estadoDAO.getEstados();
	}
	
	public List getConcluirPago(FiltroConcluirPago filtro) throws PagoEnLineaException {

		List pagos= null;
		if(filtro.getBanco().intValue() == 1){
			//Banco BCI
			pagos = pagoDAO.findConcluirPagoBCI(filtro);
		}else if(filtro.getBanco().intValue() == 2){
			//Banco T Banc
			pagos = pagoDAO.findConcluirPagoBCI(filtro);
		}else if(filtro.getBanco().intValue() == 3){
			//Banco Chile
			pagos = pagoDAO.findConcluirPagoBCH(filtro);
		}else if(filtro.getBanco().intValue() == 4){
			//Santander Santiago
			pagos = pagoDAO.findConcluirPagoBSA(filtro);
		}else if(filtro.getBanco().intValue() == 5){
			//Banco Estado
			pagos = pagoDAO.findConcluirPagoBES(filtro);
		}else if(filtro.getBanco().intValue() == 6){
			//Banco Estado
			pagos = pagoDAO.findConcluirPagoBIT(filtro);
		}else if(filtro.getBanco().intValue() == 7){
			//Banco Estado
			pagos = pagoDAO.findConcluirPagoBBV(filtro);
		}
		
		if (logger.isDebugEnabled())
			logger.debug("Registros encontrados: " + pagos.size());
		return pagos;
	}
}