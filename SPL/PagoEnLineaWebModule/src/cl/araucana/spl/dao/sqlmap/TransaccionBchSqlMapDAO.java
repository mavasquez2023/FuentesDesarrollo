package cl.araucana.spl.dao.sqlmap;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.spl.beans.DetalleTrxBCH;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.TransaccionBChile;
import cl.araucana.spl.dao.TransaccionBchDAO;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;

public class TransaccionBchSqlMapDAO extends SqlMapDaoTemplate implements TransaccionBchDAO {
	private static final Logger logger = Logger.getLogger(TransaccionBchSqlMapDAO.class); 
	private static final String SQL_FIND_DETALLES_TRX_BCHILE_BY_ID_TRX = "sqlFindDetallesTrxBChileByIdTrx";
	private static final String SQL_UPDATE_TRX_BCHILE = "sqlUpdateTrxBChile";
	private static final String SQL_UPDATE_NOTIFICACION_PAGO_BCHILE = "sqlUpdateNotificacionPagoBChile";
	private static final String SQL_UPDATE_NOTIFICACION_TRX_BCHILE = "sqlUpdateNotificacionTrxBChile";
	private static final String SQL_FIND_TRX_BCHILE_BY_CODIGO_ID_TRX = "sqlFindTrxBChileByCodigoIdTrx";
	private static final String SQL_INSERT_DETALLE_TRX_BCHILE = "sqlInsertDetalleTrxBChile";
	private static final String SQL_SEQUENCE_DETALLE_TRX_BCHILE = "sqlSequenceDetalleTrxBChile";
	private static final String SQL_INSERT_TRANSACCION_BCHILE = "sqlInsertTransaccionBChile";
	private static final String SQL_SEQUENCE_TRANSACCION_BCHILE = "sqlSequenceTransaccionBChile";
	
	public TransaccionBchSqlMapDAO(DaoManager daoManager) {
		super(daoManager);
	}

	private BigDecimal nextId(String sql) {
		BigDecimal id = (BigDecimal) queryForObject(sql);
		if (logger.isDebugEnabled()) {
			logger.debug(sql + ": id recuperado: " + id);
		}
		return id;
	}
	
	//INICIO: METODOS BANCO CHILE
	/**
	 * Insert Trx del Banco Chile
	 */
	public void insertTrxBChile(TransaccionBChile trx) {
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando transaccion BChile " + trx);
		}

		// Ahora inserto la trx del Banco Chile.
		trx.setId(nextId(SQL_SEQUENCE_TRANSACCION_BCHILE));
		super.insert(SQL_INSERT_TRANSACCION_BCHILE, trx);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Transaccion BChile insertada ok: " + trx);
		}
		
		List detalles = trx.getDetalles();
		for (Iterator iter = detalles.iterator(); iter.hasNext();) {
			DetalleTrxBCH detalle = (DetalleTrxBCH) iter.next();
			insertDetTrxBcoChile(detalle);
		}
		
	}
	
	/**
	 * Llamada a insert detalle trx Bco. Chile
	 */
	private void insertDetTrxBcoChile(DetalleTrxBCH detalleTrxBCH) {
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando detalle Trx BCH: " + detalleTrxBCH);
		}
		// Insert detalle trx Banco Chile.
		BigDecimal idDetTrx = nextId(SQL_SEQUENCE_DETALLE_TRX_BCHILE);
		detalleTrxBCH.setId(idDetTrx);
		
		super.insert(SQL_INSERT_DETALLE_TRX_BCHILE, detalleTrxBCH);
	}

	
	/**
	 * Busqueda por codigoIdTrx de una trx Bco. Chile
	 */
	public TransaccionBChile findTransaccionBchByCodigoIdTrx(String codigoIdTrx) {
		TransaccionBChile trxBChile = (TransaccionBChile) queryForObject(SQL_FIND_TRX_BCHILE_BY_CODIGO_ID_TRX, codigoIdTrx);
		return trxBChile;
	}

	/**
	 * Actualiza la Trx Bco. Chile Notificada y el pago relacionado
	 */
	public void updateNotificacionBChile(TransaccionBChile trx) {
		if (logger.isDebugEnabled()) {
			logger.debug("Estoy en updateNotificacionBChile (Dao)");
			logger.debug("Transaccion a actualizar, trx: " + trx);
		}
		
		//Actualizar la trx
		updateNotificacionTransaccionBChile(trx);
		
		//Actualizar el pago
		Pago pago = trx.getPago();
		updateNotificacionPagoBChile(pago);
	}
	
	/**
	 * Actualiza la Trx del Bco. Chile
	 */
	public void updateNotificacionTransaccionBChile(TransaccionBChile trx) {
		super.update(SQL_UPDATE_NOTIFICACION_TRX_BCHILE, trx);
	}
	
	/**
	 * Actualiza el pago notificado del Bco. Chile
	 */
	private void updateNotificacionPagoBChile(Pago pago) {
		if (logger.isDebugEnabled()) {
			logger.debug("Estoy en updateNotificacionPagoBChile (Dao)");
			
			logger.debug("Pago a actualizar, pago: " + pago);
		}
		
		super.update(SQL_UPDATE_NOTIFICACION_PAGO_BCHILE, pago);
	}
	
	/**
	 * Actualiza la transaccion del Banco Chile.
	 */
	public void updateTrxBChile(TransaccionBChile trx) {
		super.update(SQL_UPDATE_TRX_BCHILE, trx);
	}
	
	/**
	 * Consulta el detalle de una trx BChile
	 * @param idTrx
	 * @return
	 */
	public DetalleTrxBCH findDetalleTrxBchByIdTrx(BigDecimal idTrx) {
		return (DetalleTrxBCH)queryForObject(SQL_FIND_DETALLES_TRX_BCHILE_BY_ID_TRX, idTrx);
	}
	//FIN: METODOS BANCO CHILE
	
}
