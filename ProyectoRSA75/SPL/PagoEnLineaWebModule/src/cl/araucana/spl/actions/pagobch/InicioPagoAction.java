package cl.araucana.spl.actions.pagobch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.actions.pagobase.InicioPagoBaseAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.Transaccion;
import cl.araucana.spl.beans.TransaccionBChile;
import cl.araucana.spl.beans.WrapperXmlMedioPago;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.mgr.PagoBChileManager;
import cl.araucana.spl.util.XmlHelper;

/**
 * Marca el inicio de una transaccion Banco Chile
 * y redirige al usuario a la pagina del banco.
 * 
 * @param sistema Indica el sistema de origen (columna SISTEMA.CODIGO)
 * @param xml Los datos de pago (cifrado)
 * @param vector Vector inicial con el que esta cifrado el xml.
 * 
 **/
public class InicioPagoAction extends InicioPagoBaseAction {
	private static final Logger logger = Logger.getLogger(InicioPagoAction.class);

	protected Transaccion createTransaccion(WrapperXmlMedioPago wxml, String codigoMedio, String codigoSistema) throws PagoEnLineaException {
		PagoBChileManager pagoBChileManager = new PagoBChileManager();
		TransaccionBChile trx = pagoBChileManager.createTransaccion(wxml, getCodigoMedio(), codigoSistema);
		if (logger.isInfoEnabled()) {
			logger.info("Transaccion creada: " + trx + ", url retorno: " + wxml.getUrlRetorno());
		}
		return trx;
	}

	protected String getCodigoMedio() {
		return Constants.MEDIO_CODIGO_BCH;
	}
	
	protected ActionForward redireccionCgi(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping, Transaccion trx) throws PagoEnLineaException {
		TransaccionBChile trxBChile = (TransaccionBChile) trx;
		String url = trxBChile.getUrlCgi();
		String msg = new String("");
		try {
			msg = XmlHelper.formatMensajeInicial(trxBChile);
		} catch (TransformerConfigurationException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trxBChile, e);
		} catch (ParserConfigurationException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trxBChile, e);
		} catch (TransformerException e) {
			throw new PagoEnLineaException("Problemas dando formato a mensaje para objeto " + trxBChile, e);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("Ejecutando redireccion a CGI del banco: " + url);
			logger.debug("Mensaje inicial: " + msg);
		}
		request.setAttribute("url_cgi", url);
		request.setAttribute("mensaje_inicial", msg);
		return mapping.findForward("target");
	}
	

}
