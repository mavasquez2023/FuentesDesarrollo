package cl.araucana.spl.dao.sqlmap;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import cl.araucana.spl.beans.DetalleRendicionBCI;
import cl.araucana.spl.beans.RendicionBCI;
import cl.araucana.spl.dao.RendicionBciDAO;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;

public class RendicionBciSqlMapDAO extends SqlMapDaoTemplate implements RendicionBciDAO {
	private static final String SQL_FIND_DETALLE_RENDICION_BCIBY_PAGO_ID = "sqlFindDetalleRendicionBCIByPagoId";
	private static final String SQL_INSERT_DETALLE_RENDICION_BCI = "sqlInsertDetalleRendicionBCI";
	private static final String SQL_SEQ_DETALLE_RENDICION_BCI = "sqlSequenceDetalleRendicionBCI";
	private static final String SQL_INSERT_RENDICION_BCI = "sqlInsertRendicionBCI";
	private static final String SQL_SEQ_RENDICION_BCI = "sqlSequenceRendicionBCI";
	private static final Logger logger = Logger.getLogger(RendicionBciSqlMapDAO.class);

	public RendicionBciSqlMapDAO(DaoManager daoManager) {
		super(daoManager);
	}

	private BigDecimal nextId(String sql) {
		BigDecimal id = (BigDecimal) queryForObject(sql);
		if (logger.isDebugEnabled()) {
			logger.debug(sql + ": id recuperado: " + id);
		}
		return id;
	}

	public BigDecimal countRendicionByChecksum(String checksum) {
		BigDecimal contador = (BigDecimal) queryForObject("sqlCountRendicionBciByChecksum", checksum);
		return contador;
	}
	
	/**
	 * Insert de rendicion
	 * @param rendicionBCI
	 * @return id de la rendicion creada
	 */
	public BigDecimal insertRendicion(RendicionBCI rendicionBCI) {
		BigDecimal id = nextId(SQL_SEQ_RENDICION_BCI);
		rendicionBCI.setIdRendicion(id);
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando rendicionBCI " + rendicionBCI);
		}
		super.insert(SQL_INSERT_RENDICION_BCI, rendicionBCI);
		return id;
	}
	
	
	public void insertDetalleRendicion(DetalleRendicionBCI detalleRendicionBCI) {
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando det rendicion " + detalleRendicionBCI);
		}
		
		detalleRendicionBCI.setIdDetalleRend(nextId(SQL_SEQ_DETALLE_RENDICION_BCI));
		super.insert(SQL_INSERT_DETALLE_RENDICION_BCI, detalleRendicionBCI);
	}

	public DetalleRendicionBCI getDetalleRendicionBciByPagoId(BigDecimal idPago) {
		DetalleRendicionBCI detalleRendicionBCI = (DetalleRendicionBCI) queryForObject(SQL_FIND_DETALLE_RENDICION_BCIBY_PAGO_ID, idPago);
		return detalleRendicionBCI;
	}

}
