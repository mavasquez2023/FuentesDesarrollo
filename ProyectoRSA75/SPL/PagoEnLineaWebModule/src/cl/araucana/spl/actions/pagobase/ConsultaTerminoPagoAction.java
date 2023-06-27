package cl.araucana.spl.actions.pagobase;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.mgr.PagoManager;
import cl.araucana.spl.util.Nulls;

import com.bh.talon.User;

public class ConsultaTerminoPagoAction extends AppAction {
	private static final Logger logger = Logger.getLogger(ConsultaTerminoPagoAction.class);
	
	protected ActionForward doTask(User user, ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		HttpSession sessionHttp = request.getSession();
		String idPagoStr = (String) sessionHttp.getAttribute("idPago");
		if (logger.isDebugEnabled()) {
			logger.debug("Estoy en Consulta termino de pago... idPago: " + idPagoStr);
		}
		try {
			DaoConfig.startTransaction();
			
			BigDecimal idPago = new BigDecimal(idPagoStr);
			String mensaje = "";
			Integer estadoPagado = Constants.PAGO_INICIAL;
			
			PagoManager mgrPago = new PagoManager();
			Pago pago = mgrPago.getPagoById(idPago);
			Integer pagadoBD = pago.getPagado();
			
			DaoConfig.commitTransaction();
			
			if (logger.isDebugEnabled()) {
				logger.debug("Pago: " + pago);
			}
			
			if (Nulls.INTEGER.compareTo(pagadoBD)==0) {
				estadoPagado = Constants.PAGO_INICIAL;
				mensaje = "El estado Pagado de la trx es inicial.";
			} else if (pagadoBD.compareTo(new Integer(1))==0) {
				estadoPagado = Constants.PAGO_PAGADO;
				mensaje = "¡¡La trx esta pagada!!";
			} else {
				estadoPagado = Constants.PAGO_NO_PAGADO;
				mensaje = "La trx no esta pagada";				
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug("Pagado: " + pagadoBD + " /estadoPagado: " + estadoPagado + " /mensaje: " + mensaje);
			}
			
			StringBuffer sb = new StringBuffer("");
			response.setContentType("text/plain");
			sb.append(estadoPagado);
			
		    response.getOutputStream().println(sb.toString());
			
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		} finally {
			DaoConfig.endTransaction();
		}
		
		return null;
	}
}
