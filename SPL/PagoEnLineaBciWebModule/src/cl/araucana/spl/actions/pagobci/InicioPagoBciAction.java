package cl.araucana.spl.actions.pagobci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.spl.actions.pagobase.InicioPagoBaseAction;
import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.Convenio;
import cl.araucana.spl.beans.Transaccion;
import cl.araucana.spl.beans.TransaccionBci;
import cl.araucana.spl.beans.WrapperXmlMedioPago;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.mgr.MedioPagoManager;
import cl.araucana.spl.mgr.PagoBciManager;
import cl.araucana.spl.util.Constantes;

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
	
	protected String preIngresoCompra(Transaccion trx) {
		String salida= "NOK";
		try {
			String url_preingreso = construyeUrlPreingreso((TransaccionBci)trx);
			logger.info("Ejecutando Preingreso del banco, url: " + url_preingreso);
			
			URL url = new URL(url_preingreso);
			InputStream is = url.openConnection().getInputStream();

			BufferedReader reader = new BufferedReader( new InputStreamReader( is )  );

			String line = null;
			while( ( line = reader.readLine() ) != null )  {
				logger.info("Respuesta banco: " + line);
			   salida= line;
			}
			reader.close();

		} catch (Exception e) {
			logger.warn("Error invocando URL Preingreso del banco");
			e.printStackTrace();
		}

		return salida;
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
	
	/**
	 * Construye la url del preingreso que se llama inicialmente.
	 * @param trx La transaccion con la que se crea la llamada inicial.
	 * @return La url generada.
	 * @throws PagoEnLineaException 
	 * @throws Exception 
	 */
	private String construyeUrlPreingreso(TransaccionBci trx) throws Exception {
		String idProducto = trx.getCodigoSistema();
		Integer cantidad = new Integer(1); // Siempre 1 en cantidad porque banco multiplica (cantidad * monto).
		Integer costoEnvio = new Integer(0);
		
		String urlPrecompra = Constantes.getInstancia().URL_PRECOMPRA;
		logger.info("URL precompra en archivo parametros:" + urlPrecompra);
		String codBanco = "BCI";
		Convenio convenio = new MedioPagoManager().getConvenio(codBanco);
		String codigoConvenioBanco = convenio!=null?convenio.getCodigo():""; 
		
	    String url = new StringBuffer(urlPrecompra)
	    	.append("&cnvnum=").append(codigoConvenioBanco)
	    	.append("&compra=").append(idProducto).append("|").append(trx.getMontoTotal()).append("|").append(cantidad)
	    	.append("&cstenv=").append(costoEnvio)
	    	.append("&trx=").append(trx.getIdPago()).toString();
	    
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
