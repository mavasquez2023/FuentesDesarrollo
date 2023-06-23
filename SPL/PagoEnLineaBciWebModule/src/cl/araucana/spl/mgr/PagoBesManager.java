
/*
 * @(#) PagoBesManager.java    1.0 06-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */

package cl.araucana.spl.mgr;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;

import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.TransaccionBes;
import cl.araucana.spl.beans.WrapperXmlMedioPago;
import cl.araucana.spl.dao.PagoDAO;
import cl.araucana.spl.dao.TransaccionBesDAO;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.util.Nulls;

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
 *            <TD> 06-08-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Alejandro Sepúlveda Page <BR> asepulveda@schema.cl </TD>
 *            <TD> Versión inicial. Implementa metodos con lógica de negocio para la administración
 *            de la información del pago en línea cn banco estado. 
 *            </TD>
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
 * @author Alejandro Sepúlveda Page (asepulveda@schema.cl)
 *
 * @version 1.0
 */

public class PagoBesManager {
	private static Logger logger = Logger.getLogger(PagoBesManager.class);
	private PagoManager pagoManager;
	
	private TransaccionBesDAO transaccionBesDAO;
	private PagoDAO pagoDAO;
	public PagoBesManager() {
		DaoManager daoManager = DaoConfig.getDaoManager();
		pagoManager = new PagoManager();
		transaccionBesDAO = (TransaccionBesDAO)daoManager.getDao(TransaccionBesDAO.class);
		pagoDAO = (PagoDAO)daoManager.getDao(PagoDAO.class);
	}	
	
	public TransaccionBes createTransaccion(WrapperXmlMedioPago wxml, String banco, String origen) throws PagoEnLineaException {
		logger.info("Estoy en createTransaccion para banco " + banco + ", origen " + origen);
		
		BigDecimal idPago = pagoDAO.getIdPago();
		logger.info("idPago creado en createTransaccion: " + idPago);
		
		Pago pago = pagoManager.setPago(wxml, banco, origen);
		pago.setId(idPago);
		
		TransaccionBes trx = new TransaccionBes();
		trx.setPago(pago);
		trx.setResultado(Nulls.STRING);
		trx.setGlosaError(Nulls.STRING);
		trx.setCodTrx(Nulls.STRING);
		trx.setFechaPago(Nulls.DATE);
		trx.setFechaContable(Nulls.DATE);
		trx.setRutCliente(wxml.getRutCliente());
		
		// Primero inserto el pago y su detalle
		pagoDAO.insertPago(pago);
		
		//Luego inserto la Trx
		transaccionBesDAO.insertTrx(trx);
		
		logger.info("Trx creada " + trx.getId() + ". Pago creado " + pago.getId() + ". Cantidad folios " + pago.getCantidadItems()); 
				
		return trx;
	}
	
	/**
	 * Consulta una trx del banco segun el codigo
	 * @param codigoIdTrx
	 * @return
	 */
	public TransaccionBes getTransaccionByCodigoIdTrx(String codigoIdTrx) {
		TransaccionBes transaccion = transaccionBesDAO.findTransaccionByCodigoIdTrx(codigoIdTrx);
		
		Pago pago = pagoDAO.findPagoById(transaccion.getIdPago());
		transaccion.setPago(pago);
		
		return transaccion;
	}
	
	/**
	 * Actualiza la notificacion de la trx Banco Estado
	 *
	 */
	public void updateNotificacionTrx(TransaccionBes trx) {
		transaccionBesDAO.updateNotificacion(trx);
	}
	
	/**
	 * Acciones a realizar cuando se recibe un notificacion exitosa.
	 * @param trx
	 * @param trxBD
	 * @throws Exception
	 */
	public void notificacionBancoOk(TransaccionBes trx, TransaccionBes trxBD) throws Exception {
		logger.info("Estoy en notificacionBancoOk (Estado), Pago aceptado...");
		
		//Asepulveda 11-09-2009 Actualiza fecha de transacción
		Date fecha = new Date(System.currentTimeMillis());
		trxBD.getPago().setFechaTransaccion(fecha);
		
		trxBD.getPago().setPagado(Constants.PAGO_PAGADO);
		trxBD.setResultado(trx.getResultado());
		trxBD.setGlosaError(trx.getGlosaError());
		trxBD.setCodTrx(trx.getCodTrx());
		trxBD.setFechaPago(trx.getFechaPago());
		trxBD.setFechaContable(trx.getFechaContable());
		
		//Actualizar la Trx
		updateNotificacionTrx((TransaccionBes) trxBD);
	}
	
	/**
	 * Acciones a realizar cuando se recibe un notificacion erronea.
	 * @param trx
	 * @param trxBD
	 * @throws Exception
	 */
	public void notificacionBancoNOk(TransaccionBes trx, TransaccionBes trxBD) throws Exception {
		logger.info("Estoy en notificacionBancoNOk (Estado), Pago NO aceptado...");
		
		trxBD.getPago().setPagado(Constants.PAGO_NO_PAGADO);
		
		//Actualizar la Trx
		updateNotificacionTrx((TransaccionBes) trxBD);
	}

	/**
	 * Valida consistencia de la trx de la base de datos con la trx recibida del banco. 
	 * @param trxBD
	 * @param trx
	 * @return
	 * @throws Exception
	 */
	public boolean validaTrxNotificacion(TransaccionBes trxBD, TransaccionBes trx) throws Exception {
		boolean valid = true;
		// 1.- Debe existir la transacción en la base de datos
		if (trxBD == null) {
			valid = false;
			logger.error("Pago rechazado: no hay registro en la base de datos para la transacción "+trx.getId());
			
		// 2.- La transacción no debe haber sido pagada
		} else if (Constants.NOTIFICACION_OK.equals(trxBD.getResultado())) {
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
		public boolean checkCodRetornoTrx(TransaccionBes trx, TransaccionBes trxBD) 
			throws Exception {
			boolean result = false;
			
			// Si el mensaje contiene código de error
			if (!Constants.NOTIFICACION_OK.equals(trx.getResultado())) {			
				logger.debug("Transaccion rechazada por el banco, el mensaje es: "+trx.getGlosaError()+"("+trx.getResultado()+")");
				
			} else {
				result = true;
				logger.debug("Transaccion aceptada por el banco, el mensaje es: "+trx.getGlosaError()+"("+trx.getResultado()+")");
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug("El resultado de checkCodRetornoTrx es: " + result);
			}
			
			return result;
		}
}