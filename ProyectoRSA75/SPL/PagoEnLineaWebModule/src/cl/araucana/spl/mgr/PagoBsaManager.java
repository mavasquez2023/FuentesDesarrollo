package cl.araucana.spl.mgr;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.Convenio;
import cl.araucana.spl.beans.DetalleTrxBSA;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.TransaccionBsa;
import cl.araucana.spl.beans.TransaccionEft;
import cl.araucana.spl.beans.WrapperXmlMedioPago;
import cl.araucana.spl.dao.PagoDAO;
import cl.araucana.spl.dao.TransaccionBsaDAO;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.util.HttpRequestor;
import cl.araucana.spl.util.Nulls;
import cl.araucana.spl.util.Renderer;
import cl.araucana.spl.util.XmlHelper;

import com.ibatis.dao.client.DaoManager;

public class PagoBsaManager {
	private static Logger logger = Logger.getLogger(PagoBsaManager.class);
	private PagoManager pagoManager;
	private PagoEftManager pagoEftManager;
	
	private TransaccionBsaDAO transaccionBsaDAO;
	private PagoDAO pagoDAO;
	public PagoBsaManager() {
		DaoManager daoManager = DaoConfig.getDaoManager();
		pagoManager = new PagoManager();
		pagoEftManager = new PagoEftManager();
		transaccionBsaDAO = (TransaccionBsaDAO)daoManager.getDao(TransaccionBsaDAO.class);
		pagoDAO = (PagoDAO)daoManager.getDao(PagoDAO.class);
	}	
	
	public TransaccionBsa createTransaccion(WrapperXmlMedioPago wxml, String banco, String origen) throws PagoEnLineaException {
		Renderer render = new Renderer();
		logger.info("Estoy en createTransaccion para banco " + banco + ", origen " + origen);
		
		BigDecimal idPago = pagoDAO.getIdPago();
		logger.info("idPago creado en createTransaccion: " + idPago);
		
		Pago pago = pagoManager.setPago(wxml, banco, origen);
		pago.setId(idPago);
		Convenio convenio = pago.getConvenio();
		String codConvenio = convenio.getCodigo();
		
		TransaccionBsa trx = new TransaccionBsa();
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
		DetalleTrxBSA detalleTrx = new DetalleTrxBSA();

		detalleTrx.setTrxEft(trx);
		detalleTrx.setServRecaudacion(convenio.getCodigoServicioRecaudacion());
		detalleTrx.setMonto(trx.getTotal());
		detalleTrx.setCantidad("1");
		detalleTrx.setPrecio(render.formatInt(trx.getTotal()));
		
		//Se deja el idPago como dato adicional, en las rendiciones vendra en tag: descProducto.
		detalleTrx.setDatosAdicionales(idPago.toString());
		
		trx.addDetalle(detalleTrx);
		
		// Primero inserto el pago y su detalle
		pagoDAO.insertPago(pago);
		
		//Luego inserto la Trx
		transaccionBsaDAO.insertTrx(trx);
		
		logger.info("Trx creada " + trx.getId() + ". Pago creado " + pago.getId() + ". Cantidad folios " + pago.getCantidadItems()); 
				
		return trx;
	}
	
	/**
	 * Consulta una trx del banco segun el codigo
	 * @param codigoIdTrx
	 * @return
	 */
	public TransaccionBsa getTransaccionByCodigoIdTrx(String codigoIdTrx) {
		TransaccionBsa transaccion = transaccionBsaDAO.findTransaccionByCodigoIdTrx(codigoIdTrx);
		
		Pago pago = pagoDAO.findPagoById(transaccion.getIdPago());
		transaccion.setPago(pago);
		
		return transaccion;
	}
	
	/**
	 * Actualiza la tabla trx Banco Santander.
	 *
	 */
	public void updateTrx(TransaccionBsa trx) {
		transaccionBsaDAO.updateTrx(trx);
	}
	
	/**
	 * Consulta el detalle de una Trx B.Santander segun el idTrx
	 * @param idTrx
	 */
	public DetalleTrxBSA getDetalleTrxByIdTrx(BigDecimal idTrx) {
		return transaccionBsaDAO.findDetalleTrxByIdTrx(idTrx);
	}
	
	/**
	 * Actualiza la notificacion de la trx Banco Santander
	 *
	 */
	public void updateNotificacionTrx(TransaccionBsa trx) {
		transaccionBsaDAO.updateNotificacion(trx);
	}
	
	/**
	 * Acciones a realizar cuando se recibe un notificacion exitosa.
	 * @param trx
	 * @param trxBD
	 * @throws Exception
	 */
	public void notificacionBancoOk(TransaccionEft trx, TransaccionEft trxBD) throws Exception {
		logger.info("Estoy en notificacionBancoOk (Santander), Pago aceptado...");
		
		trxBD.addDatosNotificacion(trx);
		trxBD.setIndicadorPago(Constants.TRX_PAGADA);
		trxBD.getPago().setPagado(Constants.PAGO_PAGADO);
		
		//Actualizar la Trx
		updateNotificacionTrx((TransaccionBsa) trxBD);
	}
	
	/**
	 * Acciones a realizar cuando se recibe un notificacion erronea.
	 * @param trx
	 * @param trxBD
	 * @throws Exception
	 */
	public void notificacionBancoNOk(TransaccionEft trx, TransaccionEft trxBD) throws Exception {
		logger.info("Estoy en notificacionBancoNOk (Santander), Pago NO aceptado...");
		
		trxBD.addDatosNotificacion(trx);
		trxBD.setIndicadorPago(Constants.TRX_NO_PAGADA);
		trxBD.getPago().setPagado(Constants.PAGO_NO_PAGADO);
		
		//Actualizar la Trx
		updateNotificacionTrx((TransaccionBsa) trxBD);
	}
	
	/**
	 * Reconfirmacion de la transaccion del B.Santander  
	 * @param trx
	 * @param urlReconfirmacion
	 * @return
	 * @throws Exception
	 */
	public TransaccionBsa doReconfirmacion(TransaccionBsa trx, String urlReconfirmacion) throws Exception {
		String confirmacionMsg = XmlHelper.formatMensajeConfirmacion(trx);
		HttpRequestor requestor = new HttpRequestor(urlReconfirmacion);
		logger.info("El mensaje de confirmación es:\n"+confirmacionMsg);
		String respuestaConfirmacion = requestor.doRequest(confirmacionMsg);
		logger.info("La respuesta al mensaje de confirmación es:\n"+respuestaConfirmacion);
		
		trx =  (TransaccionBsa) XmlHelper.parseRespuestaConfirmacion(respuestaConfirmacion, new TransaccionBsa());
		logger.info("Respuesta Confirmacion: "+trx.toString());
		return trx;
	}
	
	/**
	 * Reconfirma el pago realizado en BCH.
	 * @param trx
	 * @param trxBD
	 * @param urlReconfirmacion
	 * @return
	 * @throws Exception
	 */
	public boolean reconfirmarPagoTermino(TransaccionBsa trx, TransaccionBsa trxBD, String urlReconfirmacion) 
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
			TransaccionBsa trxRec = doReconfirmacion(trxBD, urlReconfirmacion);
			if (pagoEftManager.validaTrxTerminoReconfirmacion(trxBD, trxRec)) {
				logger.info("Pago reconfirmado y aceptado...");
				trxBD.addDatosConfirmacion(trxRec);
				//log.debug("Fecha transacción: "+render.formatDatetime(trxBD.getFechaTransaccion()));
				trxBD.setIndicadorPago(Constants.TRX_PAGADA);
				updateTrx(trxBD);
		
			} else {
				result = false;
			}	
		} else {
			result = false;
		}
		return result;
	}

}