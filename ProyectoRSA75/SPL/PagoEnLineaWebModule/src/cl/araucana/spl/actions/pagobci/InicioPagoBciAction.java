package cl.araucana.spl.actions.pagobci;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.actions.pagobase.InicioPagoBaseAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.Transaccion;
import cl.araucana.spl.beans.TransaccionBci;
import cl.araucana.spl.beans.WrapperXmlMedioPago;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.mgr.PagoBciManager;

public class InicioPagoBciAction extends InicioPagoBaseAction {
	private static final Logger logger = Logger.getLogger(InicioPagoBciAction.class);
	
	protected Transaccion createTransaccion(WrapperXmlMedioPago wxml, String codigoMedio, String codigoSistema) throws PagoEnLineaException {
		PagoBciManager pagoBciManager = new PagoBciManager();
		TransaccionBci trx = pagoBciManager.createTransaccion(wxml, getCodigoMedio(), codigoSistema);
		if (logger.isInfoEnabled()) {
			logger.info("Transaccion creada: " + trx + ", url retorno: " + wxml.getUrlRetorno());
		}
		return trx;
	}

	protected String getCodigoMedio() {
		return Constants.MEDIO_CODIGO_BCI;
	}

	protected ActionForward redireccionCgi(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping, Transaccion trx) throws PagoEnLineaException, IOException {
		String url = construyeUrlcgi(request, (TransaccionBci)trx);
		logger.info("Ejecutando redireccion a CGI del banco: " + url);
		
		redirect(response, url);

		return null;
	}

	/**
	 * Construye la url del cgi que se llama inicialmente.
	 * @param req
	 * @param trx La transaccion con la que se crea la llamada inicial.
	 * @return La url generada.
	 * @throws PagoEnLineaException 
	 */
	private String construyeUrlcgi(HttpServletRequest req, TransaccionBci trx) throws PagoEnLineaException {
		String idProducto = trx.getCodigoSistema();
		Integer cantidad = new Integer(1); // Siempre 1 en cantidad porque banco multiplica (cantidad * monto).
		Integer costoEnvio = new Integer(0);
		
		String urlRetorno = req.getContextPath() + "/pagobci/TerminoPago.do";

	    String url = new StringBuffer(trx.getUrlCgi())
	    	.append("?").append(idProducto).append("|").append(trx.getMontoTotal()).append("|").append(cantidad)
	    	.append("&pagret=").append(urlRetorno)
	    	.append("&cstenv=").append(costoEnvio)
	    	.append("&bco=").append(getBco(trx.getCodigoBanco()))
	    	.append("&trx=").append(trx.getIdPago()).toString();
	    
		logger.debug("Url cgi construida: " + url);
		return url;
	}

	private String getBco(String codigo) throws PagoEnLineaException {
		String c;
		if (Constants.MEDIO_CODIGO_BCI.equalsIgnoreCase(codigo)) {
			c = "bci";
		} else if (Constants.MEDIO_CODIGO_TBC.equalsIgnoreCase(codigo)) {
			c = "tbc";
		} else {
			throw new PagoEnLineaException("Codigo de banco no corresponde: " + codigo);
		}
		return c;
	}
}
