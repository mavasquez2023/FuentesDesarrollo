package cl.araucana.spl.dao.sqlmap;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import cl.araucana.spl.beans.DetalleRendicionBIT;
import cl.araucana.spl.beans.RendicionBIT;
import cl.araucana.spl.dao.RendicionBitDAO;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;

/**
 * ...
 *
 * @author Gonzalo Mallea (gmallea@schema.cl)
 *
 * @version 1.0
 */

public class RendicionBitSqlMapDAO extends SqlMapDaoTemplate implements RendicionBitDAO {
	private static final String SQL_FIND_DETALLE_RENDICION_BIT_BY_PAGO_ID = "sqlFindDetalleRendicionBitByPagoId";	
	private static final String SQL_INSERT_DETALLE_RENDICION_BIT = "sqlInsertDetalleRendicionBIT";
	private static final String SQL_INSERT_RENDICION_BIT = "sqlInsertRendicionBIT";
	private static final String SQL_SEQUENCE_DETALLE_RENDICION_BIT = "sqlSequenceDetalleRendicionBIT";
	private static final String SQL_SEQUENCE_RENDICION_BIT = "sqlSequenceRendicionBIT";
	private static final String SQL_COUNT_RENDICION_BIT_BY_CHECKSUM = "sqlCountRendicionBitByChecksum";
	
	private static final Logger logger = Logger.getLogger(RendicionBitSqlMapDAO.class);
			
	public RendicionBitSqlMapDAO(DaoManager daoManager) {
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
	 * Retorna el numero de rendiciones BIT con el mismo checksum
	 */
	public BigDecimal countRendicionByChecksum(String checksum) {
		BigDecimal contador = (BigDecimal) queryForObject(SQL_COUNT_RENDICION_BIT_BY_CHECKSUM, checksum);
		return contador;
	}
		
	/**
	 * Busca detalles rendicion BIT por idPago.
	 */
	public DetalleRendicionBIT getDetalleRendicionBitByPagoId(BigDecimal idPago) {
		if (logger.isDebugEnabled())
			logger.debug("Entre a getDetalleRendicionBchByPagoId, el idPago: " + idPago);
		
		DetalleRendicionBIT detalleRendicionBIT = (DetalleRendicionBIT) queryForObject(SQL_FIND_DETALLE_RENDICION_BIT_BY_PAGO_ID, idPago);
		return detalleRendicionBIT;
	}

	/**
	 * Insert de rendicion BIT
	 */
	public BigDecimal insertRendicion(RendicionBIT rendicionBIT) {
		BigDecimal id = nextId(SQL_SEQUENCE_RENDICION_BIT);
		rendicionBIT.setIdRendicion(id);
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando rendicionBCH " + rendicionBIT);
		}
		super.insert(SQL_INSERT_RENDICION_BIT, rendicionBIT);
		return id;
	}
	
	/**
	 * Insert detalle rendicion BIT
	 */
	public void insertDetalleRendicion(DetalleRendicionBIT detalleRendicionBIT) {
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando det rendicion BIT " + detalleRendicionBIT);
		}
		
		detalleRendicionBIT.setIdDetalleRend(nextId(SQL_SEQUENCE_DETALLE_RENDICION_BIT));
		super.insert(SQL_INSERT_DETALLE_RENDICION_BIT, detalleRendicionBIT);
	}	
}