package cl.araucana.spl.actions.pagobch;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;

import cl.araucana.spl.actions.pagobase.SalidaPagoEftBaseAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.TransaccionBChile;
import cl.araucana.spl.beans.TransaccionEft;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.util.XmlHelper;

/**
 * @version 	1.0
 * @author		malvarez
 */
public class SalidaPagoAction extends SalidaPagoEftBaseAction {
	private static final Logger logger = Logger.getLogger(SalidaPagoAction.class);
	
	protected TransaccionEft parseMensajeFinal(String mensaje) throws PagoEnLineaException {
		logger.debug("Entre a parseMensajeFinal en SalidaPagoAction BCH");
		TransaccionEft trx = null;
		try {
			trx = XmlHelper.parseMensajeFinal(mensaje, new TransaccionBChile());
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

	protected String getCodigoMedio() {
		return Constants.MEDIO_CODIGO_BCH;
	}
}