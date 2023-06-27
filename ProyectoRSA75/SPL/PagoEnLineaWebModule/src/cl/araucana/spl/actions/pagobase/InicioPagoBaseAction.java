package cl.araucana.spl.actions.pagobase;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.base.AppAction;
import cl.araucana.spl.beans.Transaccion;
import cl.araucana.spl.beans.WrapperXmlMedioPago;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.mgr.IntegracionManager;
import cl.araucana.spl.mgr.SistemaManager;
import cl.araucana.spl.util.MailSender;

import com.bh.talon.User;

public abstract class InicioPagoBaseAction extends AppAction {
	private static final Logger logger = Logger.getLogger(InicioPagoBaseAction.class);
	
	protected abstract Transaccion createTransaccion(WrapperXmlMedioPago wxml, String codigoMedio, String codigoSistema) throws PagoEnLineaException;
	protected abstract String getCodigoMedio();
	protected abstract ActionForward redireccionCgi(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping, Transaccion trx) throws PagoEnLineaException, IOException;
	
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

			String codMedioPago = getCodigoMedio();
			Transaccion trx = createTransaccion(wxml, codMedioPago, codSistema);
			
			DaoConfig.commitTransaction();

			ActionForward forward = redireccionCgi(request, response, mapping, trx);
			if (forward != null) {
				return forward;
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			MailSender.sendError(ex.getMessage(), ex);
			throw ex;
		} finally {
			DaoConfig.endTransaction();
		}
		
		return null;
	}
}
