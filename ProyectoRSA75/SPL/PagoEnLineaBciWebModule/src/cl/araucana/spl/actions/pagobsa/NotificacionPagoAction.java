package cl.araucana.spl.actions.pagobsa;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;

import cl.araucana.spl.actions.pagobase.NotificacionPagoEftBaseAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.TransaccionBsa;
import cl.araucana.spl.beans.TransaccionEft;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.mgr.PagoBsaManager;
import cl.araucana.spl.mgr.PagoEftManager;
import cl.araucana.spl.util.XmlHelper;

/**
 * @version 	1.0
 * @author		malvarez
 */
public class NotificacionPagoAction extends NotificacionPagoEftBaseAction {
	private static final Logger logger = Logger.getLogger(NotificacionPagoAction.class);
	PagoBsaManager pagoBsaManager = new PagoBsaManager();
	PagoEftManager pagoEftManager = new PagoEftManager();
	
	protected String getCodigoMedio() {
		return Constants.MEDIO_CODIGO_BSA;
	}

	protected TransaccionEft getTransaccionRequest(String mensaje) throws PagoEnLineaException {
		TransaccionEft trx = null;
		try {
			logger.debug("Estoy en getTransaccionRequest BSA");
			
			mensaje = parseParameter("TX", mensaje);
			trx = XmlHelper.parseMensajeNotificacion(mensaje, new TransaccionBsa());
			
		} catch (TransformerConfigurationException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trx, e);
		} catch (UnsupportedEncodingException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trx, e);
		} catch (ParserConfigurationException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trx, e);			
		} catch (TransformerException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trx, e);
		} catch (ParseException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trx, e);
		}		
		return trx;
	}
	
	protected TransaccionEft consultaTransaccionBD(String codigoIdTrx) throws PagoEnLineaException {
		logger.debug("Entre a consultaTransaccionBD BSA");
		TransaccionBsa trx = pagoBsaManager.getTransaccionByCodigoIdTrx(codigoIdTrx);
		return trx;
	}

	protected boolean validaTrxNotificacion(TransaccionEft trx, TransaccionEft trxBD) throws PagoEnLineaException {
		boolean result = false;
		try {
			logger.debug("Entre a validaTrxNotificacion BSA");
			result = pagoEftManager.validaTrxNotificacion(trxBD, trx);
		} catch (Exception e) {
			throw new PagoEnLineaException("Problemas en validaTrxNotificacion: " + trx, e);
		}
		return result;
	}
	
	protected boolean validaEstadoNotifTrxBanco(TransaccionEft trx, TransaccionEft trxBD) throws PagoEnLineaException {
		boolean result = false;
		try {
			logger.debug("Entre a validaEstadoNotifTrxBanco BSA");
			
			result = pagoEftManager.checkCodRetornoTrx(trx, trxBD);
		} catch (Exception e) {
			throw new PagoEnLineaException("Problemas en validaEstadoNotifTrxBanco: " + trx, e);
		}
		return result;
	}
	
	protected void notificacionOk(TransaccionEft trx, TransaccionEft trxBD) throws PagoEnLineaException {
		try {
			logger.debug("Entre a notificacionOk BSA");
			
			pagoBsaManager.notificacionBancoOk(trx, trxBD);
		} catch (Exception e) {
			throw new PagoEnLineaException("Problemas en notificacionOk: " + trx, e);			
		}
	}

	protected void notificacionNOk(TransaccionEft trx, TransaccionEft trxBD) throws PagoEnLineaException {
		try {
			logger.debug("Entre a notificacionNOk BSA");
			
			pagoBsaManager.notificacionBancoNOk(trx, trxBD);
		} catch (Exception e) {
			throw new PagoEnLineaException("Problemas en notificacionNOk: " + trx, e);			
		}		
	}

}
