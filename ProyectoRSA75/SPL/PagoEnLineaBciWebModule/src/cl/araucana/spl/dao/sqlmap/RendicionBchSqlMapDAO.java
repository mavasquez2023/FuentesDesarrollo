package cl.araucana.spl.dao.sqlmap;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import cl.araucana.spl.beans.DetalleRendicionBCH;
import cl.araucana.spl.beans.RendicionBCH;
import cl.araucana.spl.dao.RendicionBchDAO;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;

public class RendicionBchSqlMapDAO extends SqlMapDaoTemplate implements RendicionBchDAO {
	private static final String SQL_INSERT_DETALLE_RENDICION_BCH = "sqlInsertDetalleRendicionBCH";
	private static final String SQL_INSERT_RENDICION_BCH = "sqlInsertRendicionBCH";
	private static final String SQL_SEQUENCE_DETALLE_RENDICION_BCH = "sqlSequenceDetalleRendicionBCH";
	private static final String SQL_SEQUENCE_RENDICION_BCH = "sqlSequenceRendicionBCH";
	private static final String SQL_FIND_DETALLE_RENDICION_BCH_BY_PAGO_ID = "sqlFindDetalleRendicionBchByPagoId";
	private static final String SQL_COUNT_RENDICION_BCH_BY_CHECKSUM = "sqlCountRendicionBchByChecksum";

	private static final Logger logger = Logger.getLogger(RendicionBchSqlMapDAO.class);
			
	public RendicionBchSqlMapDAO(DaoManager daoManager) {
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
	 * Retorna el numero de rendiciones BCH con el mismo checksum
	 */
	public BigDecimal countRendicionByChecksum(String checksum) {
		BigDecimal contador = (BigDecimal) queryForObject(SQL_COUNT_RENDICION_BCH_BY_CHECKSUM, checksum);
		return contador;
	}

	/**
	 * Busca detalles rendicion BCH por idPago.
	 */
	public DetalleRendicionBCH getDetalleRendicionBchByPagoId(BigDecimal idPago) {
		if (logger.isDebugEnabled())
			logger.debug("Entre a getDetalleRendicionBchByPagoId, el idPago: " + idPago);
		
		DetalleRendicionBCH detalleRendicionBCH = (DetalleRendicionBCH) queryForObject(SQL_FIND_DETALLE_RENDICION_BCH_BY_PAGO_ID, idPago);
		return detalleRendicionBCH;
	}
	
	/**
	 * Insert de rendicion BCH
	 */
	public BigDecimal insertRendicion(RendicionBCH rendicionBCH) {
		BigDecimal id = nextId(SQL_SEQUENCE_RENDICION_BCH);
		rendicionBCH.setIdRendicion(id);
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando rendicionBCH " + rendicionBCH);
		}
		super.insert(SQL_INSERT_RENDICION_BCH, rendicionBCH);
		return id;
	}
	
	/**
	 * Insert detalle rendicion BCH
	 */
	public void insertDetalleRendicion(DetalleRendicionBCH detalleRendicionBCH) {
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando det rendicion BCH " + detalleRendicionBCH);
		}
		
		detalleRendicionBCH.setIdDetalleRend(nextId(SQL_SEQUENCE_DETALLE_RENDICION_BCH));
		super.insert(SQL_INSERT_DETALLE_RENDICION_BCH, detalleRendicionBCH);
	}	
}
