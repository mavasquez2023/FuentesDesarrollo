package cl.araucana.spl.dao.sqlmap;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.spl.beans.DetalleTrxBSA;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.TransaccionBsa;
import cl.araucana.spl.dao.TransaccionBsaDAO;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;

public class TransaccionBsaSqlMapDAO extends SqlMapDaoTemplate implements TransaccionBsaDAO {
	private static final Logger logger = Logger.getLogger(TransaccionBsaSqlMapDAO.class);
	
	public TransaccionBsaSqlMapDAO(DaoManager daoManager) {
		super(daoManager);
	}
	
	private BigDecimal nextId(String sql) {
		BigDecimal id = (BigDecimal) queryForObject(sql);
		if (logger.isDebugEnabled()) {
			logger.debug(sql + ": id recuperado: " + id);
		}
		return id;
	}	

	/**
	 * Insert Trx del Banco Chile
	 */
	public void insertTrx(TransaccionBsa trx) {
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando transaccion BSantander " + trx);
		}

		// Ahora inserto la trx del Banco Santander.
		trx.setId(nextId("sqlSequenceTransaccionBsa"));
		super.insert("sqlInsertTransaccionBsa", trx);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Transaccion BSantander insertada ok: " + trx);
		}
		
		List detalles = trx.getDetalles();
		for (Iterator iter = detalles.iterator(); iter.hasNext();) {
			DetalleTrxBSA detalle = (DetalleTrxBSA) iter.next();
			insertDetTrx(detalle);
		}
		
	}
	
	/**
	 * Llamada a insert detalle trx Bco. Santander
	 */
	private void insertDetTrx(DetalleTrxBSA detalleTrx) {
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando detalle Trx BSA: " + detalleTrx);
		}
		// Insert detalle trx Banco Chile.
		BigDecimal idDetTrx = nextId("sqlSequenceDetalleTrxBsa");
		detalleTrx.setId(idDetTrx);
		
		super.insert("sqlInsertDetalleTrxBsa", detalleTrx);
	}
	
	/**
	 * Busqueda por codigoIdTrx de una trx Bco.Santander
	 */
	public TransaccionBsa findTransaccionByCodigoIdTrx(String codigoIdTrx) {
		TransaccionBsa trx = (TransaccionBsa) queryForObject("sqlFindTrxBsaByCodigoIdTrx", codigoIdTrx);
		return trx;
	}

	/**
	 * Actualiza la Trx Bco.Santander Notificada y el pago relacionado
	 */
	public void updateNotificacion(TransaccionBsa trx) {
		if (logger.isDebugEnabled()) {
			logger.debug("Estoy en updateNotificacion / (Santander Dao)");
			logger.debug("Transaccion a actualizar, trx: " + trx);
		}
		
		//Actualizar la trx
		updateNotificacionTransaccion(trx);
		
		//Actualizar el pago
		Pago pago = trx.getPago();
		updateNotificacionPagoBsa(pago);
	}
	
	/**
	 * Actualiza la Trx del Bco.Santander
	 */
	public void updateNotificacionTransaccion(TransaccionBsa trx) {
		super.update("sqlUpdateNotificacionTrxBsa", trx);
	}
	
	/**
	 * Actualiza el pago notificado del Bco.Santander
	 */
	private void updateNotificacionPagoBsa(Pago pago) {
		if (logger.isDebugEnabled()) {
			logger.debug("Estoy en updateNotificacionPagoBsa (Dao)");
			
			logger.debug("Pago a actualizar, pago: " + pago);
		}
		
		super.update("sqlUpdateNotificacionPagoBsa", pago);
	}
	
	/**
	 * Actualiza la transaccion del Banco Santander.
	 */
	public void updateTrx(TransaccionBsa trx) {
		super.update("sqlUpdateTrxBsa", trx);
	}
	
	/**
	 * Consulta el detalle de una trx B.Santander
	 * @param idTrx
	 * @return
	 */
	public DetalleTrxBSA findDetalleTrxByIdTrx(BigDecimal idTrx) {
		return (DetalleTrxBSA)queryForObject("sqlFindDetallesTrxBsaByIdTrx", idTrx);
	}
}
