package cl.araucana.spl.mgr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.DetallePago;
import cl.araucana.spl.beans.DetalleTrxBit;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.TransaccionBit;
import cl.araucana.spl.beans.WrapperXmlMedioPago;
import cl.araucana.spl.dao.PagoDAO;
import cl.araucana.spl.dao.ParametroDAO;
import cl.araucana.spl.dao.TransaccionBitDAO;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.dao.config.DaoConfigCPE;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.util.HttpRequestor;
import cl.araucana.spl.util.Nulls;
import cl.araucana.spl.util.XmlHelper;

import com.ibatis.dao.client.DaoManager;

/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 13-01-2014 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Gonzalo Mallea Lorca <BR> gmallea@schema.cl </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Gonzalo Mallea Lorca (gmallea@schema.cl)
 *
 * @version 1.0
 */

public class PagoBitManager {
	private static Logger logger = Logger.getLogger(PagoBitManager.class);
	private PagoManager pagoManager;
	
	private TransaccionBitDAO transaccionBitDAO;
	private PagoDAO pagoDAO;
	private ParametroDAO parametroDAO;
	private PagoEftManager pagoEftManager;
	public PagoBitManager() {
		DaoManager daoManager = DaoConfig.getDaoManager();
		DaoManager daoManagerCPE = DaoConfigCPE.getDaoManager();
		pagoManager = new PagoManager();
		transaccionBitDAO = (TransaccionBitDAO)daoManager.getDao(TransaccionBitDAO.class);
		pagoEftManager = new PagoEftManager();
		pagoDAO = (PagoDAO)daoManager.getDao(PagoDAO.class);
		parametroDAO = (ParametroDAO)daoManagerCPE.getDao(ParametroDAO.class);
	}	
	
	public TransaccionBit createTransaccion(WrapperXmlMedioPago wxml, String banco, String origen) throws PagoEnLineaException {
		logger.info("Estoy en createTransaccion para banco " + banco + ", origen " + origen);
		
		BigDecimal idPago = pagoDAO.getIdPago();
		String srvrec = parametroDAO.getSrvrecPago();
		logger.info("idPago creado en createTransaccion: " + idPago);
		
		Pago pago = pagoManager.setPago(wxml, banco, origen);
		pago.setId(idPago);
		
		TransaccionBit trx = new TransaccionBit();
		trx.setPago(pago);
		
		trx.setIdContrato(pago.getConvenio().getCodigo());// Id Contrato asignado por el Banco 
		trx.setCodigoIdTrx(pagoEftManager.getCodIdPago(idPago, pago.getConvenio().getCodigo()));// Id Transacción del Comercio
		trx.setTotal(pago.getMontoTotal());
		trx.setNroPagos(new BigDecimal(pago.getCantidadItems()));

		trx.setCodRetorno(Nulls.BIGDECIMAL);
		trx.setDescRetorno(Nulls.STRING);
		trx.setFechaTransaccion(Nulls.DATE);
		trx.setFechaContable(Nulls.DATE);
		trx.setNroComprobante(Nulls.BIGDECIMAL);
		
		List detalleList = new ArrayList();
		DetallePago detallePago ;
		DetalleTrxBit detalleTrxBit ;
		for(Iterator it = pago.getDetalles().iterator() ; it.hasNext();){
			detallePago = (DetallePago)it.next();
			
			detalleTrxBit = new DetalleTrxBit();
			detalleTrxBit.setTransaccionBit(trx);
			detalleTrxBit.setServRecaudacion(srvrec.trim()); //	Código Servicio Recaudación 
			detalleTrxBit.setMonto(detallePago.getMonto());
			detalleTrxBit.setGlosa(Constants.GLOSA_SERVICIO_ITAU);
			detalleTrxBit.setCantidad("1");//Simpre es 1 
			detalleTrxBit.setPrecio("" + detallePago.getMonto().longValue());
			detalleTrxBit.setDatosAdicionales(pago.getPagador());
			detalleList.add(detalleTrxBit);
		}
		trx.setDetalleTrxBit(detalleList);			
		// Primero inserto el pago y su detalle
		pagoDAO.insertPago(pago);
	
		//Luego inserto la Trx
		transaccionBitDAO.insertTrx(trx);
		
		logger.info("Trx creada " + trx.getId() + ". Pago creado " + pago.getId() + ". Cantidad folios " + pago.getCantidadItems()); 
				
		return trx;
	}
	
	/**
	 * Consulta una trx del banco segun el codigo
	 * @param codigoIdTrx
	 * @return
	 */
	public TransaccionBit getTransaccionByCodigoIdTrx(String codigo) {
		TransaccionBit transaccion = transaccionBitDAO.findTransaccionByCodigoIdTrx(codigo);
		
		Pago pago = pagoDAO.findPagoById(transaccion.getIdPago());
		transaccion.setPago(pago);
		
		return transaccion;
	}
	
	/**
	 * Actualiza la notificacion de la trx Banco Itau
	 *
	 */
	public void updateNotificacionTrx(TransaccionBit trx) {
		transaccionBitDAO.updateNotificacion(trx);
	}
	
	/**
	 * Acciones a realizar cuando se recibe un notificacion exitosa.
	 * @param trx
	 * @param trxBD
	 * @throws Exception
	 */
	public void notificacionBancoOk(TransaccionBit trx, TransaccionBit trxBD) throws Exception {
		logger.info("Estoy en notificacionBancoOk (Estado), Pago aceptado...");
		
		Date fecha = new Date(System.currentTimeMillis());
		trxBD.getPago().setFechaTransaccion(fecha);
		
		trxBD.getPago().setPagado(Constants.PAGO_PAGADO);
		trxBD.setCodRetorno(trx.getCodRetorno());
		trxBD.setDescRetorno(trx.getDescRetorno());
		trxBD.setCodigoIdTrx(trx.getCodigoIdTrx());
		trxBD.setFechaTransaccion(trx.getFechaTransaccion());
		trxBD.setFechaContable(trx.getFechaContable());
		
		//Actualizar la Trx
		updateNotificacionTrx((TransaccionBit) trxBD);
	}
	
	/**
	 * Acciones a realizar cuando se recibe un notificacion erronea.
	 * @param trx
	 * @param trxBD
	 * @throws Exception
	 */
	public void notificacionBancoNOk(TransaccionBit trx, TransaccionBit trxBD) throws Exception {
		logger.info("Estoy en notificacionBancoNOk (Estado), Pago NO aceptado...");
		
		trxBD.getPago().setPagado(Constants.PAGO_NO_PAGADO);
		
		//Actualizar la Trx
		updateNotificacionTrx((TransaccionBit) trxBD);
	}

	/**
	 * Valida consistencia de la trx de la base de datos con la trx recibida del banco. 
	 * @param trxBD
	 * @param trx
	 * @return
	 * @throws Exception
	 */
	public boolean validaTrxNotificacion(TransaccionBit trxBD, TransaccionBit trx) throws Exception {
		boolean valid = true;
		// 1.- Debe existir la transacción en la base de datos
		if (trxBD == null) {
			valid = false;
			logger.error("Pago rechazado: no hay registro en la base de datos para la transacción "+trx.getId());
			
		// 2.- La transacción no debe haber sido pagada
		} else if (new BigDecimal(Constants.COD_RETORNO_EXITO).equals(trxBD.getCodRetorno())) {
			valid = false;
			logger.error("Pago rechazado: la transacción "+trxBD.getId()+" ya fue pagada");
		}
		
		logger.debug("El resultado de la validacion validaTrxNotificacion es: " + valid);
		
		return valid;
	}
		
		/**
		 * Verifica el valor del codigo de retorno que viene en el mensaje trx.
		 * @param trx
		 * @return
		 * @throws Exception
		 */
		public boolean checkCodRetornoTrx(TransaccionBit trx, TransaccionBit trxBD) 
			throws Exception {
			boolean result = false;
			
			// Si el mensaje contiene código de error
			if (!new BigDecimal(Constants.COD_RETORNO_EXITO).equals(trx.getCodRetorno())) {			
				logger.debug("Transaccion rechazada por el banco, el mensaje es: "+trx.getDescRetorno()+"("+trx.getCodRetorno()+")");
				
			} else {
				result = true;
				logger.debug("Transaccion aceptada por el banco, el mensaje es: "+trx.getDescRetorno()+"("+trx.getCodRetorno()+")");
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug("El resultado de checkCodRetornoTrx es: " + result);
			}
			
			return result;
		}
		
		/**
		 * Reconfirma el pago realizado en BIT.
		 * @param req
		 * @param trx
		 * @param trxBD
		 * @param urlReconfirmacion
		 * @return
		 * @throws Exception
		 */
		public boolean reconfirmarPagoTermino(TransaccionBit trx, TransaccionBit trxBD, String urlReconfirmacion) 
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
				TransaccionBit trxRec = doReconfirmacion(trxBD, urlReconfirmacion);
				if (pagoEftManager.validaTrxTerminoReconfirmacion(trxBD, trxRec)) {
					logger.info("Pago reconfirmado y aceptado...");
					trxBD.addDatosConfirmacion(trxRec);
					//log.debug("Fecha transacción: "+render.formatDatetime(trxBD.getFechaTransaccion()));
					trxBD.setIndicadorPago(Constants.TRX_PAGADA);
					updateNotificacionTrx(trxBD);
			
				} else {
					result = false;
				}	
			} else {
				result = false;
			}
			return result;
		}
		/**
		 * Reconfirmacion de la transaccion del BCH.  
		 * @param trx
		 * @param urlReconfirmacion
		 * @return
		 * @throws Exception
		 */
		public TransaccionBit doReconfirmacion(TransaccionBit trx, String urlReconfirmacion) throws Exception {
			String confirmacionMsg = XmlHelper.formatMensajeConfirmacion(trx);
			HttpRequestor requestor = new HttpRequestor(urlReconfirmacion);
			logger.info("El mensaje de confirmación es:\n"+confirmacionMsg);
			String respuestaConfirmacion = requestor.doRequest(confirmacionMsg);
			logger.info("La respuesta al mensaje de confirmación es:\n"+respuestaConfirmacion);
			
			trx =  (TransaccionBit) XmlHelper.parseRespuestaConfirmacion(respuestaConfirmacion, new TransaccionBit());
			logger.info("Respuesta Confirmacion: "+trx.toString());
			return trx;
		}
		
		/**
		 * @author gmallea
		 * Retorna la IP privada de SPL
		 * 
		 * @return string
		 */
		public String getIpSPLPrivada(){
			return parametroDAO.getIpSPLPrivada();
		}

}