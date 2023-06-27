package cl.araucana.spl.mgr;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.Convenio;
import cl.araucana.spl.beans.DetallePago;
import cl.araucana.spl.beans.DetalleTrxBBV;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.TransaccionBbv;
import cl.araucana.spl.beans.WrapperXmlMedioPago;
import cl.araucana.spl.cliente.webServices.bbva.Comercio;
import cl.araucana.spl.cliente.webServices.bbva.ComercioLocator;
import cl.araucana.spl.cliente.webServices.bbva.ComercioSoap;
import cl.araucana.spl.cliente.webServices.bbva.ComercioSoapStub;
import cl.araucana.spl.dao.PagoDAO;
import cl.araucana.spl.dao.TransaccionBbvDAO;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.util.Nulls;
import cl.araucana.spl.util.XmlHelperBbv;

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
 *            <TD> 17-01-2014 </TD>
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

public class PagoBbvManager {
	private static Logger logger = Logger.getLogger(PagoBbvManager.class);
	private PagoManager pagoManager;
	private MedioPagoManager medioManager;
	private PagoEftManager pagoEftManager;
	private TransaccionBbvDAO transaccionBbvDAO;
	private PagoDAO pagoDAO;
	public PagoBbvManager() {
		DaoManager daoManager = DaoConfig.getDaoManager();
		pagoManager = new PagoManager();
		transaccionBbvDAO = (TransaccionBbvDAO)daoManager.getDao(TransaccionBbvDAO.class);
		pagoDAO = (PagoDAO)daoManager.getDao(PagoDAO.class);
		medioManager = new MedioPagoManager();
		pagoEftManager = new PagoEftManager();
	}	
	
	public TransaccionBbv createTransaccion(WrapperXmlMedioPago wxml, String banco, String origen) throws PagoEnLineaException {
		logger.info("Estoy en createTransaccion para banco " + banco + ", origen " + origen);
		
		BigDecimal idPago = pagoDAO.getIdPago();
		logger.info("idPago creado en createTransaccion: " + idPago);
		
		Pago pago = pagoManager.setPago(wxml, banco, origen);
		pago.setId(idPago);
		
		TransaccionBbv trx = new TransaccionBbv();
		trx.setPago(pago);
		
		trx.setIdContrato(pago.getConvenio().getCodigo());// Id Contrato asignado por el Banco 
		trx.setCodigoIdTrx(pagoEftManager.getCodIdPago(idPago, pago.getConvenio().getCodigo()));// Id Transacción del Comercio
		trx.setTotal(pago.getMontoTotal());
		trx.setNroPagos(new BigDecimal(pago.getCantidadItems()));

		trx.setCodRetorno(Nulls.BIGDECIMAL);
		trx.setDescRetorno(Nulls.STRING);
		trx.setUrl(Nulls.STRING);
		
		List detalleList = new ArrayList();
		DetallePago detallePago ;
		DetalleTrxBBV detalleTrxBBV ;
		for(Iterator it = pago.getDetalles().iterator() ; it.hasNext();){
			detallePago = (DetallePago)it.next();
			
			detalleTrxBBV = new DetalleTrxBBV();
			
			detalleTrxBBV.setTransaccionBbv(trx);
			detalleTrxBBV.setSecuencia(new BigDecimal(1));
			detalleTrxBBV.setNumeroCliente(new BigDecimal(pago.getPagador()));
			detalleTrxBBV.setNumeroPDocumento(detallePago.getFolio());
			detalleTrxBBV.setMonto(detallePago.getMonto());
			detalleTrxBBV.setFechaVencimiento(new Date());
			detalleTrxBBV.setEstado(true);
			detalleList.add(detalleTrxBBV);
		}
		trx.setDetalles(detalleList);			
		// Primero inserto el pago y su detalle
		pagoDAO.insertPago(pago);

		//Luego inserto la Trx
		transaccionBbvDAO.insertTrx(trx);
		
		logger.info("Trx creada " + trx.getId() + ". Pago creado " + pago.getId() + ". Cantidad folios " + pago.getCantidadItems()); 
				
		return trx;
	}

	/**
	 * Consulta una trx del banco segun el codigo
	 * @param codigoIdTrx
	 * @return
	 */
	public TransaccionBbv getTransaccionByCodigoIdTrx(String codigo) {
		TransaccionBbv transaccion = transaccionBbvDAO.findTransaccionByCodigoIdTrx(codigo);
		
		Pago pago = pagoDAO.findPagoById(transaccion.getIdPago());
		transaccion.setPago(pago);
		
		return transaccion;
	}
	
	/**
	 * Actualiza la notificacion de la trx Banco Itau
	 *
	 */
	public void updateNotificacionTrx(TransaccionBbv trx) {
		transaccionBbvDAO.updateNotificacion(trx);
	}
	
	/**
	 * Acciones a realizar cuando se recibe un notificacion exitosa.
	 * @param trx
	 * @param trxBD
	 * @throws Exception
	 */
	public void notificacionBancoOk(TransaccionBbv trx, TransaccionBbv trxBD) throws Exception {
		logger.info("Estoy en notificacionBancoOk (Estado), Pago aceptado...");
		
		Date fecha = new Date(System.currentTimeMillis());
		trxBD.getPago().setFechaTransaccion(fecha);
		
		trxBD.getPago().setPagado(Constants.PAGO_PAGADO);
		trxBD.setCodRetorno(trx.getCodRetorno());
		trxBD.setDescRetorno(trx.getDescRetorno());
		trxBD.setCodigoIdTrx(trx.getCodigoIdTrx());
		//trxBD.setFechaTransaccion(trx.getFechaTransaccion());
		//trxBD.setFechaContable(trx.getFechaContable());
		
		//Actualizar la Trx
		updateNotificacionTrx(trxBD);
	}
	
	/**
	 * Acciones a realizar cuando se recibe un notificacion erronea.
	 * @param trx
	 * @param trxBD
	 * @throws Exception
	 */
	public void notificacionBancoNOk(TransaccionBbv trx, TransaccionBbv trxBD) throws Exception {
		logger.info("Estoy en notificacionBancoNOk (Estado), Pago NO aceptado...");
		
		trxBD.getPago().setPagado(Constants.PAGO_NO_PAGADO);
		
		//Actualizar la Trx
		updateNotificacionTrx(trxBD);
	}

	/**
	 * Valida consistencia de la trx de la base de datos con la trx recibida del banco. 
	 * @param trxBD
	 * @param trx
	 * @return
	 * @throws Exception
	 */
	public boolean validaTrxNotificacion(TransaccionBbv trxBD, TransaccionBbv trx) throws Exception {
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
		
		logger.info("El resultado de la validacion validaTrxNotificacion es: " + valid);
		
		return valid;
	}
		
		/**
		 * Verifica el valor del codigo de retorno que viene en el mensaje trx.
		 * @param trx
		 * @return
		 * @throws Exception
		 */
		public boolean checkCodRetornoTrx(TransaccionBbv trx, TransaccionBbv trxBD) 
			throws Exception {
			boolean result = false;
			
			// Si el mensaje contiene código de error
			if (!new BigDecimal(Constants.COD_RETORNO_EXITO).equals(trx.getCodRetorno())) {			
				logger.info("Transaccion rechazada por el banco, el mensaje es: "+trx.getDescRetorno()+"("+trx.getCodRetorno()+")");
				
			} else {
				result = true;
				logger.info("Transaccion aceptada por el banco, el mensaje es: "+trx.getDescRetorno()+"("+trx.getCodRetorno()+")");
			}
			
			if (logger.isDebugEnabled()) {
				logger.info("El resultado de checkCodRetornoTrx es: " + result);
			}
			
			return result;
		}
		
		/**
		 * Consulta a las Web Services del banco BBVA (solictarAcceso y Enviar Transaccion)
		 * @param String claveSistema, int idComercio
		 * @param int idComercio
		 * @return String
		 * @throws Exception
		 */
		public String consultaBancoWebServices(String claveSistema, String medioPago,TransaccionBbv trx ) throws PagoEnLineaException{
					/*Procesos...
					 * 
					 * 1. SolicitarAcceso
					 * 2. EnviarTransaccion
					 * 3. Actualiza la tabla TRANSBBV para guardar la URL que envia el Banco
					 */
			String url="";
				try {
					
					Convenio convenio = medioManager.getConvenio(medioPago);					
					Comercio comercio = new ComercioLocator();
					ComercioSoap comercioSoap;					
					comercioSoap = new ComercioSoapStub(new URL(convenio.getUrlCgi()),comercio);
					
					//1. SolicitarAcceso
					logger.info("Parametros a enviar a solicitarAcceso : idComercio =" + convenio.getCodigo() +" Clave de sistema = " +claveSistema );
					String xmlRespSolicitaAcceso = comercioSoap.solicitarAcceso(Integer.parseInt(convenio.getCodigo()),claveSistema);
					logger.info("Respuesta WS solicitarAcceso : " + xmlRespSolicitaAcceso);
					
					String llave = XmlHelperBbv.parseMensajeSolicitarAcceso(xmlRespSolicitaAcceso, new TransaccionBbv());
					
					//2. enviarTransaccion
					String xmlPago = XmlHelperBbv.generaXMLPago(trx);
					logger.info("Parametro a enviara a enviarTransaccion : comercioID = " + convenio.getCodigo() +
																			" llave = " +llave +
																			" transaccion = " +trx.getCodigoIdTrx()+
																			" monto = "+ trx.getTotal().intValue() + 
																			" cantidadPagos = "+trx.getNroPagos().intValue()+
																			" pagos = " +xmlPago);
					
					String xmlRespEnviaTransaccion = comercioSoap.enviarTransaccion(Integer.parseInt(convenio.getCodigo()), 
																					llave, trx.getCodigoIdTrx(), 
																					trx.getTotal().intValue(), 
																					trx.getNroPagos().intValue(), 
																					xmlPago);
					
					logger.info("Respuesta WS enviarTransaccion : " + xmlRespEnviaTransaccion);
					url = XmlHelperBbv.parseMensajeEnviarTransaccion(xmlRespEnviaTransaccion, new TransaccionBbv());

					//3. Actualiza la tabla TRANSBBV
					trx.setUrl(url);
					transaccionBbvDAO.updateURLEnviaTrx(trx);
					
				} catch (Exception e) {
					logger.info("Error consultaBancoWebServices :.. " , e );
					throw new PagoEnLineaException("Problemas en el Web Services: " + e, e.getCause());
					
				}
			return url;
		}
		
		/**
		 * Notifica el pago  actualiza la tabla pago y transaccionBBV
		 * 
		 * @param idPago
		 * @param estado
		 * @return true si el pago resulta pagado, false si no.
		 * @throws PagoEnLineaException
		 */
		public boolean notificaPago(BigDecimal transaccion, Date fecha, BigDecimal monto, String estado, String mensaje) throws PagoEnLineaException {
			if (logger.isDebugEnabled()) {
				logger.info("Notificando pago " + transaccion + ". Estado " + estado);
			}
			TransaccionBbv trx = this.getTransaccionByCodigoIdTrx(""+transaccion.longValue());
			if (trx == null) {
				throw new PagoEnLineaException("Pago rechazado: No existe en el sistema: " + ""+transaccion.byteValue());
			}
			if (trx.getCodRetorno().byteValue() == 0) {
				throw new PagoEnLineaException("Pago rechazado: Resultado de pago ya habia sido notificado al sistema: " + ""+transaccion.byteValue());
			}
			
			
			trx.setCodigoIdTrx(Constants.COD_RETORNO_EXITO);
			trx.setDescRetorno(mensaje);
			trx.setFechaContable(fecha);			
			
			Date fechaTRX = new Date(System.currentTimeMillis());
			trx.getPago().setFechaTransaccion(fechaTRX);
			trx.getPago().setPagado(Constants.PAGO_PAGADO);
			trx.getPago().setFechaContable(fecha);
			
			logger.info("Actualizando es;tado de transaccion");
			transaccionBbvDAO.updateNotificacion(trx);
			
			return true;
		}
}
