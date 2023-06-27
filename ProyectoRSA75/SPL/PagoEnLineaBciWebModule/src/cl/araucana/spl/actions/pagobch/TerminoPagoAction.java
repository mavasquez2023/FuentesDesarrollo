package cl.araucana.spl.actions.pagobch;

import org.apache.log4j.Logger;

import cl.araucana.spl.actions.pagobase.TerminoPagoEftBaseAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.TransaccionBChile;
import cl.araucana.spl.beans.TransaccionEft;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.mgr.PagoBChileManager;

/**
 * @version 	1.0
 * @author		malvarez
 */
public class TerminoPagoAction extends TerminoPagoEftBaseAction {
	private static final Logger logger = Logger.getLogger(TerminoPagoAction.class);
	private PagoBChileManager  pagoBChileManager = new PagoBChileManager();
		
	protected TransaccionEft getTransaccionBD(String codigoIdTrx) throws PagoEnLineaException {
		logger.debug("Estoy en getTransaccionBD / BCH (codigoIdTrx: " + codigoIdTrx + ")");
		TransaccionEft trx = pagoBChileManager.getTransaccionByCodigoIdTrx(codigoIdTrx);
		return trx;
	}

	protected void updateTransaccionBanco(TransaccionEft trx) throws PagoEnLineaException {
		logger.debug("Estoy en updateTransaccionBanco / BCH");
		pagoBChileManager.updateTrxBChile((TransaccionBChile) trx);
	}

	protected boolean reconfirmarTransaccionBanco(TransaccionEft trx, TransaccionEft trxBD, String urlReconfirmacion) throws PagoEnLineaException {
		logger.debug("Estoy en reconfirmarTransaccionBanco / BCH");
		boolean result = false;
		try {
			result = pagoBChileManager.reconfirmarPagoTermino((TransaccionBChile)trx, (TransaccionBChile)trxBD, urlReconfirmacion);
		} catch (Exception e) {
			throw new PagoEnLineaException("Problemas en reconfirmarTransaccionBanco BCH / trxBD: " + trxBD, e);
		}
		return result;
	}

	protected String getCodigoMedio() {
		return Constants.MEDIO_CODIGO_BCH;
	}
}