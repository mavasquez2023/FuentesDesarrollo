package cl.araucana.spl.actions.pagobsa;

import org.apache.log4j.Logger;

import cl.araucana.spl.actions.pagobase.TerminoPagoEftBaseAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.TransaccionBsa;
import cl.araucana.spl.beans.TransaccionEft;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.mgr.PagoBsaManager;

/**
 * @version 	1.0
 * @author		malvarez
 */
public class TerminoPagoAction extends TerminoPagoEftBaseAction {
	private static final Logger logger = Logger.getLogger(TerminoPagoAction.class);
	private PagoBsaManager  pagoBsaManager = new PagoBsaManager();
		
	protected TransaccionEft getTransaccionBD(String codigoIdTrx) throws PagoEnLineaException {
		logger.debug("Estoy en getTransaccionBD / BSA");
		TransaccionEft trx = pagoBsaManager.getTransaccionByCodigoIdTrx(codigoIdTrx);
		return trx;
	}

	protected void updateTransaccionBanco(TransaccionEft trx) throws PagoEnLineaException {
		logger.debug("Estoy en updateTransaccionBanco / BSA");
		pagoBsaManager.updateTrx((TransaccionBsa) trx);
	}

	protected boolean reconfirmarTransaccionBanco(TransaccionEft trx, TransaccionEft trxBD, String urlReconfirmacion) throws PagoEnLineaException {
		logger.debug("Estoy en reconfirmarTransaccionBanco / BSA");
		boolean result = false;
		try {
			result = pagoBsaManager.reconfirmarPagoTermino((TransaccionBsa)trx, (TransaccionBsa)trxBD, urlReconfirmacion);
		} catch (Exception e) {
			throw new PagoEnLineaException("Problemas en reconfirmarTransaccionBanco BSA / trxBD: " + trxBD, e);
		}
		return result;
	}

	protected String getCodigoMedio() {
		return Constants.MEDIO_CODIGO_BSA;
	}
}