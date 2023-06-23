package cl.araucana.spl.dao.sqlmap;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.spl.beans.Bitacora;
import cl.araucana.spl.dao.BitacoraDAO;
import cl.araucana.spl.util.Utiles;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;

public class BitacoraSqlMapDAO extends SqlMapDaoTemplate implements BitacoraDAO {
	private static final Logger logger = Logger.getLogger(BitacoraSqlMapDAO.class);
	
	private static final String SQL_FIND_BITACORA_BY_ID_PAGO = "sqlFindBitacoraByIdPago";
	public BitacoraSqlMapDAO(DaoManager daoManager) {
		super(daoManager);
	}
	
	private BigDecimal nextId(String sql) {
		BigDecimal id = (BigDecimal) queryForObject(sql);
		if (logger.isDebugEnabled()) {
			logger.debug(sql + ": id recuperado: " + id);
		}
		return id;
	}

	public void insertBitacora(Bitacora bitacora) {
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando bitacora " + bitacora);
		}
		bitacora.setId(nextId("sqlSequenceBitacora"));
		super.insert("sqlInsertBitacora", bitacora);
	}


	public List findBitacora(BigDecimal idPago) {
		List eventos = queryForList(SQL_FIND_BITACORA_BY_ID_PAGO, idPago);
		return eventos;
	}

	public List findCountBitacoraByPagos(List ids) {
		String joined = Utiles.join(",", ids);
		List cantidades = queryForList("sqlCountBitacoraByIdPago", joined);
		return cantidades;
	}


}
