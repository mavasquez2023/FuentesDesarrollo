package cl.araucana.spl.actions.pagobit;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;

import cl.araucana.spl.actions.pagobase.ReconfirmacionReplierEftBaseAction;
import cl.araucana.spl.beans.TransaccionBit;
import cl.araucana.spl.beans.TransaccionEft;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.util.XmlHelper;

/**
 * @author rgili
 *
 */
public class ReconfirmacionReplierAction extends ReconfirmacionReplierEftBaseAction {
	private static final Logger logger = Logger.getLogger(ReconfirmacionReplierAction.class);
	
	protected TransaccionEft parseMensajeBanco(String contenido) throws PagoEnLineaException {
		logger.debug("Estoy en parseMensajeBanco de ReconfirmacionReplierAction / BIT");
		TransaccionEft trx = null;
		try {
			trx = XmlHelper.parseMensajeConfirmacion(contenido, new TransaccionBit());
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
	
}
