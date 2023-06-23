package cl.araucana.spl.mgr;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.TransaccionEft;
import cl.araucana.spl.util.Renderer;

public class PagoEftManager {
	private static Logger logger = Logger.getLogger(PagoEftManager.class);
	
	public PagoEftManager() {		
	}
	
	/**
	 * Entrega el codigo de la trx formateado (segun normativa del banco)
	 * @param idTrx
	 * @param codConvenio
	 * @return
	 */
	public String getCodIdPago(BigDecimal idTrx, String codConvenio) {
		Renderer render = new Renderer();
		return render.formatIdTransaccion(codConvenio, idTrx);
	}
	
	/**
	 * Valida consistencia de la trx de la base de datos con la trx recibida del banco. 
	 * @param trxBD
	 * @param trx
	 * @return
	 * @throws Exception
	 */
	public boolean validaTrxNotificacion(TransaccionEft trxBD, TransaccionEft trx) throws Exception {
		boolean valid = true;
		// 1.- Debe existir la transacción en la base de datos
		if (trxBD == null) {
			valid = false;
			logger.error("Pago rechazado: no hay registro en la base de datos para la transacción "+trx.getId());
			
		// 2.- La transacción no debe haber sido pagada
		} else if (Constants.TRX_PAGADA.equals(trxBD.getIndicadorPago())) {
			valid = false;
			logger.error("Pago rechazado: la transacción "+trxBD.getId()+" ya fue pagada");
			
		// 3.- Los montos deben coincidir
		} else if (!trxBD.getTotal().equals(trx.getTotal())) {
			valid = false;
			logger.error("Pago rechazado: los montos de la transacción no coinciden (Mensaje="+trx.getTotal()+",BD="+trxBD.getTotal()+")");

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
	public boolean checkCodRetornoTrx(TransaccionEft trx, TransaccionEft trxBD) 
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
	 * Valida la transaccion BCH que se trata de terminar.
	 * @param trxBD
	 * @param trx
	 * @param reconfirmacion
	 * @return
	 */
	private boolean validaTrxTermino(TransaccionEft trxBD, TransaccionEft trx, boolean reconfirmacion) {
		boolean valid = true;
		// 1.- Debe existir la transacción en la base de datos
		if (trxBD == null) {
			valid = false;
			logger.error("Transaccion no valida: no hay registro en la base de datos para la transacción "+trx.getId());
		
		// 2.- La transacción SI debe haber sido pagada a menos que sea reconfirmacion
		} else if (!reconfirmacion && !Constants.TRX_PAGADA.equals(trxBD.getIndicadorPago()) ) {
			valid = false;
			logger.error("Transaccion no valida: la transacción "+trx.getId()+" no fue pagada y no es reconfirmacion");
			
		// 3.- Los montos deben coincidir
		} else if (!trxBD.getTotal().equals(trx.getTotal())) {
			valid = false;
			logger.error("Transaccion no valida: los montos de la transacción no coinciden (Mensaje="+trx.getTotal()+",BD="+trxBD.getTotal()+")");

		// 4.- El mensaje no debe contener un código de error
		} else if (!new BigDecimal(Constants.COD_RETORNO_EXITO).equals(trx.getCodRetorno())) {
			valid = false;
			logger.error("Transaccion no valida: código de error en el mensaje "+trx.getDescRetorno()+"("+trx.getCodRetorno()+")");
		} 
		return valid;
	}

	/**
	 * Llamada a validar termino de transaccion sin reconfirmar. 
	 * @param trxBD
	 * @param trx
	 * @return
	 */
	public boolean validaTrxTermino(TransaccionEft trxBD, TransaccionEft trx) {
		boolean reconfirmacion = false;
		return validaTrxTermino(trxBD, trx, reconfirmacion);
	}
	
	/**
	 * Llamada a validar termino de transaccion reconfirmando.
	 * @param trxBD
	 * @param trx
	 * @return
	 */
	public boolean validaTrxTerminoReconfirmacion(TransaccionEft trxBD, TransaccionEft trx) {
		boolean reconfirmacion = true;
		return validaTrxTermino(trxBD, trx, reconfirmacion);
	}	
	
}
