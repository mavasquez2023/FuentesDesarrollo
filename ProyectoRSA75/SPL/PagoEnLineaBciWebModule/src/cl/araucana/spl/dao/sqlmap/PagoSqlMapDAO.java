package cl.araucana.spl.dao.sqlmap;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.spl.beans.DetallePago;
import cl.araucana.spl.beans.FiltroConcluirPago;
import cl.araucana.spl.beans.FiltroInconsistencias;
import cl.araucana.spl.beans.FiltroRendicion;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.TransaccionBci;
import cl.araucana.spl.dao.PagoDAO;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;

public class PagoSqlMapDAO extends SqlMapDaoTemplate implements PagoDAO {
	private static final String SQL_FIND_PAGO_BY_ID_PAGO_ID_CONVENIO = "sqlFindPagoByIdPagoIdConvenio";
	private static final String SQL_SUM_MONTO_PAGO_BY_FILTRO = "sqlSumMontoPagoByFiltro";
	private static final String SQL_SUM_MONTO_BANCO_BY_FILTRO = "sqlSumMontoBancoByFiltro";
	private static final String SQL_COUNT_PAGO_BY_FILTRO = "sqlCountPagoByFiltro";
	private static final String SQL_COUNT_BANCO_BY_FILTRO = "sqlCountBancoByFiltro";
	private static final String SQL_FIND_PAGOS_BY_NUMERO_PAGO = "sqlFindPagosByNumeroPago";
	private static final String SQL_FIND_PAGOS_BY_NUMERO_FOLIO = "sqlFindPagosByNumeroFolio";
	private static final String SQL_FIND_FOLIOS_BY_ID_PAGO = "sqlFindFoliosByIdPago";
	private static final String SQL_UPDATE_PAGO_TRX_RENDIDA = "sqlUpdatePagoTrxRendida";
	private static final String SQL_UPDATE_PAGO_TRX_CURSADA = "sqlUpdatePagoTrxCursada";
	private static final String SQL_UPDATE_FIN_TRANSACCION = "sqlUpdateFinTransaccion";
	private static final String SQL_UPDATE_FIN_PAGO = "sqlUpdateFinPago";
	private static final String SQL_UPDATE_PAGO_BITACORA = "sqlUpdatePagoBitacora";

	private static final String SQL_FIND_TRANSACCION_BCI_BY_ID_PAGO = "sqlFindTransaccionBciByIdPago";
	
	private static final String SQL_INSERT_DETALLE_PAGO = "sqlInsertDetallePago";
	private static final String SQL_SEQUENCE_DETALLE_PAGO = "sqlSequenceDetallePago";
	
	private static final String SQL_INSERT_PAGO = "sqlInsertPago";
	private static final String SQL_SEQUENCE_PAGO = "sqlSequencePago";
	
	private static final String SQL_INSERT_TRANSACCION_BCI = "sqlInsertTransaccionBci";
	private static final String SQL_SEQUENCE_TRANSACCION_BCI = "sqlSequenceTransaccionBci";
	
	private static final String SQL_FIND_PAGO_BY_ID_PAGO = "sqlFindPagoById";
	
	private static final String SQL_FIND_SRVREC = "sqlSrvrecPago";
	
	private static final String SQL_FIND_SP_SPL_PRIVADA = "sqlIPPrivadaSPL";
		
	private static final Logger logger = Logger.getLogger(PagoSqlMapDAO.class);
	
	public PagoSqlMapDAO(DaoManager daoManager) {
		super(daoManager);
	}
	
	public void insert(TransaccionBci trx) {
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando transaccion " + trx);
		}

		// Primero inserto el pago y su detalle
		insertPago(trx.getPago());

		// Ahora inserto lo particular de Bci.
		trx.setId(nextId(SQL_SEQUENCE_TRANSACCION_BCI));
		super.insert(SQL_INSERT_TRANSACCION_BCI, trx);
	}

	
	public void insertPago(Pago pago) {
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando pago " + pago);
		}
		
		if (pago.getId() == null) {
			pago.setId(getIdPago());
		}
		super.insert(SQL_INSERT_PAGO, pago);
		
		for (Iterator it = pago.getDetalles().iterator(); it.hasNext(); ) {
			DetallePago detalle = (DetallePago) it.next();
			insert(detalle);
		}
	}
	
	public BigDecimal getIdPago() {
		return nextId(SQL_SEQUENCE_PAGO);
	}

	private BigDecimal nextId(String sql) {
		BigDecimal id = (BigDecimal) queryForObject(sql);
		if (logger.isDebugEnabled()) {
			logger.debug(sql + ": id recuperado: " + id);
		}
		return id;
	}

	private void insert(DetallePago detalle) {
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando detalle de pago " + detalle);
		}
		detalle.setId(nextId(SQL_SEQUENCE_DETALLE_PAGO));
		super.insert(SQL_INSERT_DETALLE_PAGO, detalle);
	}
	

	public TransaccionBci findTransaccionByIdPago(BigDecimal idPago) {
		TransaccionBci trx = (TransaccionBci) queryForObject(SQL_FIND_TRANSACCION_BCI_BY_ID_PAGO, idPago);
		return trx;
	}

	public void updateFinPago(Pago pago) {
		update(SQL_UPDATE_FIN_PAGO, pago);
	}

	public void updateFinTransaccion(TransaccionBci trx) {
		update(SQL_UPDATE_FIN_TRANSACCION, trx);
	}

	public Pago findPagoById(BigDecimal idPago) {
		Pago pago = (Pago) queryForObject(SQL_FIND_PAGO_BY_ID_PAGO, idPago);
		return pago;
	}

	public List findInconsistenciasPaginadas(FiltroInconsistencias filtro) {
		List inconsistencias = queryForList("sqlFindInconsistenciasPaginadas", filtro);
		return inconsistencias;
	}

	public List findInconsistenciasPaginaPreviaInvertida(FiltroInconsistencias filtro) {
		List inconsistencias = queryForList("sqlFindInconsistenciasPaginaPreviaInvertida", filtro);
		return inconsistencias;
	}
	
	public List findPagosByNumeroFolio(BigDecimal numeroFolio) {
		List pagos = queryForList(SQL_FIND_PAGOS_BY_NUMERO_FOLIO, numeroFolio);
		return pagos;
	}


	public List findPagosByNumeroPago(BigDecimal numeroPago) {
		List pagos = queryForList(SQL_FIND_PAGOS_BY_NUMERO_PAGO, numeroPago);
		return pagos;
	}	

	public void updatePagoTrxRendida(Pago pago) {
		update(SQL_UPDATE_PAGO_TRX_RENDIDA, pago);		
	}
	
	public int updatePagoTrxCursada(Pago pago) {
		return update(SQL_UPDATE_PAGO_TRX_CURSADA, pago);	
	}
	
	public List findFoliosByPago(BigDecimal idPago) {
		List folios = queryForList(SQL_FIND_FOLIOS_BY_ID_PAGO, idPago);
		return folios;
	}

	
	public void updatePagoBitacora(Pago pago) {
		update(SQL_UPDATE_PAGO_BITACORA, pago);
	}


	public BigDecimal getCountBancoByFiltro(FiltroInconsistencias filtro) {
		BigDecimal count = (BigDecimal) queryForObject(SQL_COUNT_BANCO_BY_FILTRO, filtro);
		return count;
	}


	public BigDecimal getCountPagoByFiltro(FiltroInconsistencias filtro) {
		BigDecimal count = (BigDecimal) queryForObject(SQL_COUNT_PAGO_BY_FILTRO, filtro);
		return count;
	}


	public BigDecimal getSumMontoBancoByFiltro(FiltroInconsistencias filtro) {
		BigDecimal sum = (BigDecimal) queryForObject(SQL_SUM_MONTO_BANCO_BY_FILTRO, filtro);
		return sum;
	}


	public BigDecimal getSumMontoPagoByFiltro(FiltroInconsistencias filtro) {
		BigDecimal sum = (BigDecimal) queryForObject(SQL_SUM_MONTO_PAGO_BY_FILTRO, filtro);
		return sum;
	}
	
	/**
	 * Busqueda de un pago segun el id y el convenio.
	 * @param pago
	 * @return
	 */
	public Pago findPagoByIdPagoIdConvenio(Pago pago) {
		Pago result = (Pago) queryForObject(SQL_FIND_PAGO_BY_ID_PAGO_ID_CONVENIO, pago);
		return result;
	}

	/**
	 * Retorna pagos segun el convenio y una lista de fechas contables. 
	 */
	public List findPagosNoRendidos(FiltroRendicion filtroRendicion) {
		List lista = queryForList("sqlFindPagosNoRendidos", filtroRendicion);
		return lista;
	}
	
	/**
	 * Retorna los pagos que estan sin concluir CHILE.. 
	 */
	public List findConcluirPagoBCH(FiltroConcluirPago filtro) {
		List pagos = queryForList("sqlBCHFindPagos", filtro);
		return pagos;
	}
	
	/**
	 * Retorna los pagos que estan sin concluir BCI Y TBANC.. 
	 */
	public List findConcluirPagoBCI(FiltroConcluirPago filtro) {
		List pagos = queryForList("sqlBCIFindPagos", filtro);
		return pagos;
	}
	/**
	 * Retorna los pagos que estan sin concluir SANTANDER.. 
	 */
	public List findConcluirPagoBSA(FiltroConcluirPago filtro) {
		List pagos = queryForList("sqlBSAFindPagos", filtro);
		return pagos;
	}
	/**
	 * Retorna los pagos que estan sin concluir SANTANDER.. 
	 */
	public List findConcluirPagoBES(FiltroConcluirPago filtro) {
		List pagos = queryForList("sqlBESFindPagos", filtro);
		return pagos;
	}
	/**
	 * Retorna los pagos que estan sin concluir ITAU.. 
	 */
	public List findConcluirPagoBIT(FiltroConcluirPago filtro) {
		List pagos = queryForList("sqlBITFindPagos", filtro);
		return pagos;
	}
	/**
	 * Retorna los pagos que estan sin concluir BBVA.. 
	 */
	public List findConcluirPagoBBV(FiltroConcluirPago filtro) {
		List pagos = queryForList("sqlBBVFindPagos", filtro);
		return pagos;
	}
}
