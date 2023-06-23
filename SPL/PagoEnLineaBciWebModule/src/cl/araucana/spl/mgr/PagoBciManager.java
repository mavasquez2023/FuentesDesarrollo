package cl.araucana.spl.mgr;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;

import cl.araucana.spl.base.Constants;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.TransaccionBci;
import cl.araucana.spl.beans.WrapperXmlMedioPago;
import cl.araucana.spl.dao.PagoDAO;
import cl.araucana.spl.dao.config.DaoConfig;
import cl.araucana.spl.exceptions.PagoEnLineaException;
import cl.araucana.spl.util.FechaContable;
import cl.araucana.spl.util.Utiles;

import com.ibatis.dao.client.DaoManager;

public class PagoBciManager {
	private static final Logger logger = Logger.getLogger(PagoBciManager.class);
	private PagoManager mgrPago;
	
	private PagoDAO pagoDAO;
	public PagoBciManager() {
		DaoManager daoManager = DaoConfig.getDaoManager();
		mgrPago = new PagoManager();
		pagoDAO = (PagoDAO)daoManager.getDao(PagoDAO.class);
	}
	
	public TransaccionBci createTransaccion(WrapperXmlMedioPago wxml, String banco, String origen) throws PagoEnLineaException {
		if (logger.isDebugEnabled()) {
			logger.debug("Creando transaccion para banco " + banco + ", origen " + origen);
		}
		Pago pago = mgrPago.setPago(wxml, banco, origen);
		
		TransaccionBci trx = new TransaccionBci();
		trx.setPago(pago);
		pagoDAO.insert(trx);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Trx creada " + trx.getId() + ". Pago creado " + pago.getId() + ". Cantidad folios " + pago.getCantidadItems()); 
		}
		return trx;
	}

	/**
	 * 
	 * @param idPago
	 * @param estado
	 * @return true si el pago resulta pagado, false si no.
	 * @throws PagoEnLineaException
	 */
	public boolean notificaPago(BigDecimal idPago, String estado) throws PagoEnLineaException {
		boolean ok = false;
		if (logger.isDebugEnabled()) {
			logger.debug("Notificando pago " + idPago + ". Estado " + estado);
		}
		TransaccionBci trx = pagoDAO.findTransaccionByIdPago(idPago);
		if (trx == null) {
			throw new PagoEnLineaException("Pago rechazado: No existe en el sistema: " + idPago);
		}
		if (trx.existeNotificacion()) {
			throw new PagoEnLineaException("Pago rechazado: Resultado de pago ya habia sido notificado al sistema: " + idPago);
		}
		
		Integer pagado = null;
		if (Utiles.estadoBciPagado(estado)) {
			logger.debug("Pago aceptado: " + idPago);
			pagado = Constants.PAGO_PAGADO;
			ok = true;
		} else {
			logger.warn("Pago rechazado: Estado indica que cargo no ha sido aplicado. Pago=" + idPago + ",estado=" + estado);
			pagado = Constants.PAGO_NO_PAGADO;
		}
		
		trx.setEstadoBci(estado);
		trx.setPagado(pagado);
		Date fecha = new Date(System.currentTimeMillis());
		trx.setFechaTransaccion(fecha);

		logger.debug("Actualizando estado de transaccion");
		pagoDAO.updateFinTransaccion(trx);

		FechaContable contable = new FechaContable();
		trx.getPago().setFechaContable(contable.getFechaContable(fecha));
		pagoDAO.updateFinPago(trx.getPago());
		logger.debug("Pago actualizado.");
		
		return ok;
	}

}
