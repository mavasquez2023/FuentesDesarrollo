package cl.araucana.spl.dao.sqlmap;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import cl.araucana.spl.beans.DetalleRendicionBBV;
import cl.araucana.spl.beans.RendicionBBV;
import cl.araucana.spl.dao.RendicionBbvDAO;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;

/**
 * ...
 *
 * @author Gonzalo Mallea (gmallea@schema.cl)
 *
 * @version 1.0
 */

public class RendicionBbvSqlMapDAO extends SqlMapDaoTemplate implements RendicionBbvDAO {
	private static final String SQL_SEQUENCE_RENDICION_BBV =   "sqlSequenceRendicionBBV";
	private static final String SQL_SEQUENCE_DET_RENDICION_BBV =   "sqlSequenceDetRendicionBBV";
	
	private static final String SQL_INSERT_DET_RENDICION_BBV = "sqlInsertDetRendicionBBV";
	private static final String SQL_INSERT_RENDICION_BBV = "sqlInsertRendicionBBV";
	
	private static final String SQL_FIN_DET_RENDICION_BBV = "sqlFindDetRendicionBbvByPagoId";
	private static final String SQL_COUNT_RENDICION_BBV_BY_CHECKSUM = "sqlCountRendicionBbvByChecksum";
		
	private static final Logger logger = Logger.getLogger(RendicionBbvSqlMapDAO.class);
			
	public RendicionBbvSqlMapDAO(DaoManager daoManager) {
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
	 * Retorna el numero de rendiciones BBV con el mismo checksum
	 */
	public BigDecimal countRendicionByChecksum(String checksum) {
		BigDecimal contador = (BigDecimal) queryForObject(SQL_COUNT_RENDICION_BBV_BY_CHECKSUM, checksum);
		return contador;
	}
	
	/**
	 * Insert de rendicion Bbv
	 */
	public BigDecimal insertRendicion(RendicionBBV rendicionBbv) {
		BigDecimal id = nextId(SQL_SEQUENCE_RENDICION_BBV);
		rendicionBbv.setIdRendicion(id);
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando rendicionBBV " + rendicionBbv);
		}
		super.insert(SQL_INSERT_RENDICION_BBV, rendicionBbv);
		return id;
	}
		
	/**
	 * Insert de detalle rendicion Bbv
	 */
	public BigDecimal insertDetalleRendicion(DetalleRendicionBBV detalleRendicionBBV) {
		BigDecimal id = nextId(SQL_SEQUENCE_DET_RENDICION_BBV);
		detalleRendicionBBV.setIdDetalleRend(id);
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando rendicionBBV " + detalleRendicionBBV);
		}
		super.insert(SQL_INSERT_DET_RENDICION_BBV, detalleRendicionBBV);
		return id;
	}
	
	/**
	 * Busca rendicion Bbv por idPago.
	 */
	public DetalleRendicionBBV getRendicionBbvByPagoId(BigDecimal idPago) {
		if (logger.isDebugEnabled())
			logger.debug("Entre a getRendicionBbvByPagoId, el idPago: " + idPago);
		
		DetalleRendicionBBV detalleRendicionBBV = (DetalleRendicionBBV) queryForObject(SQL_FIN_DET_RENDICION_BBV, idPago);
		return detalleRendicionBBV;
	}
}