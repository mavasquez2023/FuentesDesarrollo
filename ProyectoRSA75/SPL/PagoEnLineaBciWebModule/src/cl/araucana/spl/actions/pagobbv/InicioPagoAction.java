package cl.araucana.spl.actions.pagobbv;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.Transaccion;
import cl.araucana.spl.beans.TransaccionBbv;
import cl.araucana.spl.beans.WrapperXmlMedioPago;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.mgr.IntegracionManager;
import cl.araucana.spl.mgr.PagoBbvManager;
import cl.araucana.spl.mgr.SistemaManager;
import cl.araucana.spl.util.MailSender;

import com.bh.talon.User;

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
 *            <TD> 17-01-2014 </TD>
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

public class InicioPagoAction extends AppAction {
	private static final Logger logger = Logger.getLogger(InicioPagoAction.class);
	
	
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String codSistema = request.getParameter("sistema");
		String sCrypted   = request.getParameter("xml");
		String sIvector   = request.getParameter("vector");

		if (logger.isInfoEnabled()) {
			logger.info("InicioPagoAction [medio=" + getCodigoMedio() + ", sistema=" + codSistema + ", sivector=" + sIvector+ ", scrypted= = " + sCrypted + "]");
		}

		try {
			DaoConfig.startTransaction();

			IntegracionManager iManager = new IntegracionManager();
			SistemaManager sisManager = new SistemaManager();

			String claveSistema = sisManager.getClaveSistema(codSistema);			
			
			boolean updateCorrelativo = true;
			
			WrapperXmlMedioPago wxml = iManager.wrapperXmlMedioPago(claveSistema, sCrypted, sIvector, updateCorrelativo);
			
			String codMedioPago = this.getCodigoMedio();
			TransaccionBbv trx = (TransaccionBbv) this.createTransaccion(wxml, codMedioPago, codSistema);
			
			String urlPago = this.consultaBancoWebServices(claveSistema,this.getCodigoMedio(),trx);
			
			DaoConfig.commitTransaction();

			response.sendRedirect(urlPago);
			
			//ActionForward forward = this.redireccionCgi(request, response, mapping, trx);
			//if (forward != null) {
			//	return forward;
			//}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			MailSender.sendError(ex.getMessage(), ex);
			throw ex;
		} finally {
			DaoConfig.endTransaction();
		}
		
		return null;
	}
	
	protected Transaccion createTransaccion(WrapperXmlMedioPago wxml, String codigoMedio, String codigoSistema) throws PagoEnLineaException {
		logger.debug("En InicioPagoAction BBV");
		PagoBbvManager pagoBbvManager = new PagoBbvManager();
		TransaccionBbv trx = pagoBbvManager.createTransaccion(wxml, getCodigoMedio(), codigoSistema);
		if (logger.isInfoEnabled()) {
			logger.info("Transaccion BBV creada: " + trx + ", url retorno: " + wxml.getUrlRetorno());
		}

		return trx;
	}

	protected String  consultaBancoWebServices(String claveSistema, String medioPago,TransaccionBbv trx ) throws PagoEnLineaException {
		logger.info("En consultaBancoWebServices BBVA");
		PagoBbvManager pagoBbvManager = new PagoBbvManager();
		String resp = pagoBbvManager.consultaBancoWebServices(claveSistema,medioPago,trx);
		
		return resp;
	}

	protected String getCodigoMedio() {
		return Constants.MEDIO_CODIGO_BBV;
	}	

	
	}
