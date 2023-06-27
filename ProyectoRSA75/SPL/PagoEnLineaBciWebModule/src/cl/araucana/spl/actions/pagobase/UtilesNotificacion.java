package cl.araucana.spl.actions.pagobase;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.log4j.Logger;

import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.Sistema;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.mgr.PagoManager;
import cl.araucana.spl.mgr.SistemaManager;
import cl.araucana.spl.util.MailSender;
import cl.araucana.spl.util.crypto.CryptResult;
import cl.araucana.spl.util.crypto.SimpleEncoder;
import cl.araucana.spl.util.crypto.TripleDesEngine;

public class UtilesNotificacion {
	private static final Logger logger = Logger.getLogger(UtilesNotificacion.class);
	
	public static void notificaOrigen(BigDecimal idPago) {
		if (logger.isDebugEnabled()) {
			logger.debug("Notificando al sistema de origen. Pago: " + idPago);
		}
		
		try {
			PagoManager pagoManager = new PagoManager();
			Pago pago = pagoManager.getPagoById(idPago);
			String urlNotificacion = pago.getUrlNotificacionOrigen();
			logger.debug("Url notificacion sistema origen: " + urlNotificacion);
			
			SistemaManager sistemaManager = new SistemaManager();
			Sistema sistemaOrigen = sistemaManager.getSistema(pago.getIdSistema());

			if (logger.isDebugEnabled()) {
				logger.debug("Sistema Origen(" + pago.getIdSistema() + "): " + sistemaOrigen);
			}
			TripleDesEngine ecipher = new TripleDesEngine();
			SimpleEncoder encoder = new SimpleEncoder();
			CryptResult result = ecipher.encrypt(sistemaOrigen.getClave(), String.valueOf(idPago), Constants.CHARSET);
			String trx = encoder.bytesToHex(result.getCrypted());
			String vector = encoder.bytesToHex(result.getIvector());

			String response = sendRequest(urlNotificacion, trx, vector);

			if (logger.isDebugEnabled()) {
				logger.debug("Parametros notificacion. trx: " + trx + ", vector = " + vector);
				logger.debug("Respuesta de notificacion: " + response);
			}
			if (response == null || !response.trim().equals("1")) {
				throw new PagoEnLineaException("Respuesta recibida desde origen no aceptable: " + response);
			}
			logger.debug("Notificacion a sistema de origen ejecutada exitosamente");
		} catch (Exception ex) {
			// Notificacion por correo.
			String mensaje = "El error se produjo al tratar de notificar pago a sistema de origen. IdPago: " + idPago;
			mensaje = mensaje + ": " + ex.getMessage();
			logger.error(mensaje+": "+ex.getMessage(), ex);
			
			MailSender.sendError(mensaje, ex);
		}
	}

	private static String sendRequest(String urlNotificacion, String trx, String vector) throws IOException {
		HttpClientParams params = new HttpClientParams();
		params.setParameter(HttpClientParams.CONNECTION_MANAGER_TIMEOUT, Constants.NOTIFICACION_CONNECTION_TIMEOUT);
		params.setParameter(HttpClientParams.SO_TIMEOUT, Constants.NOTIFICACION_DATA_TIMEOUT);
		HttpClient client = new HttpClient(params);
		
		PostMethod post = new PostMethod(urlNotificacion);
		post.setRequestBody(new NameValuePair[] {
			new NameValuePair("trx", trx),
			new NameValuePair("vector", vector)
		});
		client.executeMethod(post);
		String output = post.getResponseBodyAsString();
		post.releaseConnection();
		
		return output;
	}
}
