package cl.araucana.spl.dao.sqlmap;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import cl.araucana.spl.beans.DetalleRendicionBSA;
import cl.araucana.spl.beans.RendicionBSA;
import cl.araucana.spl.dao.RendicionBsaDAO;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;

public class RendicionBsaSqlMapDAO extends SqlMapDaoTemplate implements RendicionBsaDAO {
	private final Logger logger = Logger.getLogger(RendicionBsaSqlMapDAO.class); 
	
	public RendicionBsaSqlMapDAO(DaoManager daoManager) {
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
		BigDecimal contador = (BigDecimal) queryForObject("sqlFindRendicionBsaByChecksum", checksum);
		return contador;
	}

	public DetalleRendicionBSA getDetalleRendicionBsaByPagoId(BigDecimal idPago) {
		if (logger.isDebugEnabled())
			logger.debug("Entre a getDetalleRendicionBsaByPagoId, el idPago: " + idPago);
		
		DetalleRendicionBSA detalleRendicionBSA = (DetalleRendicionBSA) queryForObject("sqlFindDetalleRendicionBsaByPagoId", idPago);
		return detalleRendicionBSA;
	}

	
	/**
	 * Insert de rendicion BSA
	 */
	public BigDecimal insertRendicion(RendicionBSA rendicionBSA) {
		BigDecimal id = nextId("sqlSequenceRendicionBSA");
		rendicionBSA.setIdRendicion(id);
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando rendicionBSA " + rendicionBSA);
		}
		super.insert("sqlInsertRendicionBSA", rendicionBSA);
		return id;
	}
	
	/**
	 * Insert detalle rendicion BSA
	 */
	public void insertDetalleRendicion(DetalleRendicionBSA detalleRendicionBSA) {
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando det rendicion BSA " + detalleRendicionBSA);
		}
		detalleRendicionBSA.setIdDetalleRend(nextId("sqlSequenceDetalleRendicionBSA"));
		super.insert("sqlInsertDetalleRendicionBSA", detalleRendicionBSA);
	}

}
