package cl.araucana.spl.mgr;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.Convenio;
import cl.araucana.spl.beans.DetalleTrxBCH;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.TransaccionBChile;
import cl.araucana.spl.beans.TransaccionEft;
import cl.araucana.spl.beans.WrapperXmlMedioPago;
import cl.araucana.spl.dao.PagoDAO;
import cl.araucana.spl.dao.TransaccionBchDAO;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.util.HttpRequestor;
import cl.araucana.spl.util.Nulls;
import cl.araucana.spl.util.Renderer;
import cl.araucana.spl.util.XmlHelper;

import com.ibatis.dao.client.DaoManager;

public class PagoBChileManager {
	private static Logger logger = Logger.getLogger(PagoBChileManager.class);
	private PagoManager pagoManager;
	private PagoEftManager pagoEftManager;
	
	private TransaccionBchDAO transaccionBchDAO;
	private PagoDAO pagoDAO;
	public PagoBChileManager() {
		DaoManager daoManager = DaoConfig.getDaoManager();
		pagoManager = new PagoManager();
		pagoEftManager = new PagoEftManager();
		transaccionBchDAO = (TransaccionBchDAO)daoManager.getDao(TransaccionBchDAO.class);
		pagoDAO = (PagoDAO)daoManager.getDao(PagoDAO.class);
	}	
	
	public TransaccionBChile createTransaccion(WrapperXmlMedioPago wxml, String banco, String origen) throws PagoEnLineaException {
		Renderer render = new Renderer();		
		logger.info("Estoy en createTransaccion para banco " + banco + ", origen " + origen);
		
		BigDecimal idPago = pagoDAO.getIdPago();
		logger.info("idPago creado en createTransaccion: " + idPago);
		
		Pago pago = pagoManager.setPago(wxml, banco, origen);
		pago.setId(idPago);
		Convenio convenio = pago.getConvenio();
		String codConvenio = convenio.getCodigo();
		
		TransaccionBChile trx = new TransaccionBChile();
		trx.setPago(pago);
		trx.setCodigoIdTrx(pagoEftManager.getCodIdPago(idPago, codConvenio));
		trx.setTotal(wxml.getMontoTotal());
		trx.setIdContrato(codConvenio);
		trx.setCodRetorno(Nulls.BIGDECIMAL);
		trx.setDescRetorno(Nulls.STRING);
		trx.setIdRegistro(Nulls.BIGDECIMAL);
		trx.setIndicadorPago(Nulls.STRING);
		trx.setNroComprobante(Nulls.BIGDECIMAL);
		
		trx.setNroPagos(new BigDecimal(1));
		DetalleTrxBCH detalleTrxBCH = new DetalleTrxBCH();

		detalleTrxBCH.setTrxEft(trx);
		detalleTrxBCH.setServRecaudacion(convenio.getCodigoServicioRecaudacion());
		detalleTrxBCH.setMonto(trx.getTotal());
		detalleTrxBCH.setCantidad("1");
		detalleTrxBCH.setPrecio(render.formatInt(trx.getTotal()));
		
		//Se deja el idPago como dato adicional para disponer de este dato en las rendiciones.
		detalleTrxBCH.setDatosAdicionales(idPago.toString());
		
		trx.addDetalle(detalleTrxBCH);
		
		// Primero inserto el pago y su detalle
		pagoDAO.insertPago(pago);
		
		//Luego inserto la trx
		transaccionBchDAO.insertTrxBChile(trx);
		
		logger.info("Trx creada " + trx.getId() + ". Pago creado " + pago.getId() + ". Cantidad folios " + pago.getCantidadItems()); 
		
		return trx;
	}
	
	/**
	 * Consulta una trx del banco segun el codigo
	 * @param codigoIdTrx
	 * @return
	 */
	public TransaccionBChile getTransaccionByCodigoIdTrx(String codigoIdTrx) {
		TransaccionBChile transaccionBChile = transaccionBchDAO.findTransaccionBchByCodigoIdTrx(codigoIdTrx);
		Pago pago = pagoDAO.findPagoById(transaccionBChile.getIdPago());
		transaccionBChile.setPago(pago);
		
		return transaccionBChile;
	}
	
	/**
	 * Actualiza la tabla trx Banco Chile.
	 *
	 */
	public void updateTrxBChile(TransaccionBChile trx) {
		transaccionBchDAO.updateTrxBChile(trx);
	}
	
	/**
	 * Consulta el detalle de una Trx BChile segun el idTrx
	 * @param idTrx
	 */
	public DetalleTrxBCH getDetalleTrxBChileByIdTrx(BigDecimal idTrx) {
		return transaccionBchDAO.findDetalleTrxBchByIdTrx(idTrx);
	}
	
	/**
	 * Actualiza la notificacion de la trx Banco Chile
	 *
	 */
	public void updateNotificacionTrxBChile(TransaccionBChile trx) {
		transaccionBchDAO.updateNotificacionBChile(trx);
	}
	
	/**
	 * Acciones a realizar cuando se recibe un notificacion exitosa.
	 * @param trx
	 * @param trxBD
	 * @throws Exception
	 */
	public void notificacionBChileOk(TransaccionEft trx, TransaccionEft trxBD) throws Exception {
		logger.info("Estoy en notificacionBChileOk, Pago aceptado...");
		
		trxBD.addDatosNotificacion(trx);
		trxBD.setIndicadorPago(Constants.TRX_PAGADA);
		trxBD.getPago().setPagado(Constants.PAGO_PAGADO);
		
		//Actualizar la Trx
		updateNotificacionTrxBChile((TransaccionBChile) trxBD);
	}
	
	/**
	 * Acciones a realizar cuando se recibe un notificacion erronea.
	 * @param trx
	 * @param trxBD
	 * @throws Exception
	 */
	public void notificacionBChileNOk(TransaccionEft trx, TransaccionEft trxBD) throws Exception {
		logger.info("Estoy en notificacionBChileNOk, Pago NO aceptado...");
		
		trxBD.addDatosNotificacion(trx);
		trxBD.setIndicadorPago(Constants.TRX_NO_PAGADA);
		trxBD.getPago().setPagado(Constants.PAGO_NO_PAGADO);
		
		//Actualizar la Trx
		updateNotificacionTrxBChile((TransaccionBChile) trxBD);
	}
	
	/**
	 * Reconfirmacion de la transaccion del BCH.  
	 * @param trx
	 * @param urlReconfirmacion
	 * @return
	 * @throws Exception
	 */
	public TransaccionBChile doReconfirmacion(TransaccionBChile trx, String urlReconfirmacion) throws Exception {
		String confirmacionMsg = XmlHelper.formatMensajeConfirmacion(trx);
		HttpRequestor requestor = new HttpRequestor(urlReconfirmacion);
		logger.info("El mensaje de confirmación es:\n"+confirmacionMsg);
		String respuestaConfirmacion = requestor.doRequest(confirmacionMsg);
		logger.info("La respuesta al mensaje de confirmación es:\n"+respuestaConfirmacion);
		
		trx =  (TransaccionBChile) XmlHelper.parseRespuestaConfirmacion(respuestaConfirmacion, new TransaccionBChile());
		logger.info("Respuesta Confirmacion: "+trx.toString());
		return trx;
	}
	
	/**
	 * Reconfirma el pago realizado en BCH.
	 * @param req
	 * @param trx
	 * @param trxBD
	 * @param urlReconfirmacion
	 * @return
	 * @throws Exception
	 */
	public boolean reconfirmarPagoTermino(TransaccionBChile trx, TransaccionBChile trxBD, String urlReconfirmacion) 
		throws Exception {
		
		boolean result = true;
		
		if (trxBD != null && new BigDecimal(Constants.COD_RETORNO_RECHAZO).equals(trx.getCodRetorno()) && Constants.TRX_PAGADA.equals(trx.getIndicadorPago())) {
			logger.info("Reconfirmación del Pago...");
			trxBD.addDatosFinalizacion(trx);
			//String urlReconfirmacion = parseParameter("url_reconfirmacion", contenidoLlamada);
			if (urlReconfirmacion == null || "".equals(urlReconfirmacion))
				//urlReconfirmacion = getProperty("url_cgi", getRootContext(req));
				urlReconfirmacion = trxBD.getPago().getConvenio().getUrlCgi();
			logger.info("La reconfirmacion se realiza a "+urlReconfirmacion);	
			TransaccionBChile trxRec = doReconfirmacion(trxBD, urlReconfirmacion);
			if (pagoEftManager.validaTrxTerminoReconfirmacion(trxBD, trxRec)) {
				logger.info("Pago reconfirmado y aceptado...");
				trxBD.addDatosConfirmacion(trxRec);
				//log.debug("Fecha transacción: "+render.formatDatetime(trxBD.getFechaTransaccion()));
				trxBD.setIndicadorPago(Constants.TRX_PAGADA);
				updateTrxBChile(trxBD);
		
			} else {
				result = false;
			}	
		} else {
			result = false;
		}
		return result;
	}
	
}