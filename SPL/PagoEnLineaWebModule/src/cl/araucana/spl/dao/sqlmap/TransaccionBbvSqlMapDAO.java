package cl.araucana.spl.dao.sqlmap;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.spl.beans.DetalleTrxBBV;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.TransaccionBbv;
import cl.araucana.spl.dao.TransaccionBbvDAO;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;

/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 13-01-2014 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Gonzalo Mallea Lorca <BR> gmallea@schema.cl </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Gonzalo Mallea Lorca (gmallea@schema.cl)
 *
 * @version 1.0
 */
public class TransaccionBbvSqlMapDAO extends SqlMapDaoTemplate implements TransaccionBbvDAO {
	private static final Logger logger = Logger.getLogger(TransaccionBbvSqlMapDAO.class);
	
	public TransaccionBbvSqlMapDAO(DaoManager daoManager) {
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
	 * Insert Trx del Banco BBVA
	 */
	public void insertTrx(TransaccionBbv trx) {
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando transaccion BBVA " + trx);
		}

		trx.setId(nextId("sqlSequenceTransaccionBbv"));
		super.insert("sqlInsertTransaccionBbv", trx);
		
		List detalles = trx.getDetalles();
		for (Iterator iter = detalles.iterator(); iter.hasNext();) {
			DetalleTrxBBV detalle = (DetalleTrxBBV) iter.next();
			this.insertDetTrxBbv(detalle);
		}
		
	}
	
	/**
	 * Llamada a insert detalle trx Bco. BBVA
	 */
	private void insertDetTrxBbv(DetalleTrxBBV detalleTrxBbv) {
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando detalle Trx BBVA: " + detalleTrxBbv);
		}

		detalleTrxBbv.setId(nextId("sqlSequenceDetalleTrxBbv"));
		
		super.insert("sqlInsertDetalleTrxBbv", detalleTrxBbv);
	}
	
	/**
	 * Busqueda por codigoIdTrx de una trx Bco. BBVA
	 */
	public TransaccionBbv findTransaccionByCodigoIdTrx(String codigoIdTrx) {
		TransaccionBbv trx = (TransaccionBbv) queryForObject("sqlFindTrxBbvByCodigoIdTrx", codigoIdTrx);
		return trx;
	}

	/**
	 * Actualiza la Trx Bco. BBVA Notificada y el pago relacionado
	 */
	public void updateNotificacion(TransaccionBbv trx) {
		if (logger.isDebugEnabled()) {
			logger.debug("Estoy en updateNotificacion / (BBVA Dao)");
			logger.debug("Transaccion a actualizar, trx: " + trx);
		}
		
		//Actualizar la trx
		updateNotificacionTransaccion(trx);
		
		//Actualizar el pago
		Pago pago = trx.getPago();
		updateNotificacionPagoBbv(pago);
	}
	/**
	 * Actualiza la Trx Bco. BBVA URL de envia transaccion
	 */
	public void updateURLEnviaTrx(TransaccionBbv trx) {
		if (logger.isDebugEnabled()) {
			logger.debug("Estoy en updateURLEnviaTrx (Dao)");
			
			logger.debug("Trx a actualizar: ID " + trx.getId());
			logger.debug("Trx a actualizar: URL " + trx.getUrl());
		}		
		super.update("sqlUpdateUrlEnviarTrxBbv", trx);
		

	}
	
	/**
	 * Actualiza la Trx del Bco.BBVA
	 */
	public void updateNotificacionTransaccion(TransaccionBbv trx) {
		if (logger.isDebugEnabled()) {
			logger.debug("Estoy en sqlUpdateNotificacionTrxBbv (Dao)");
			
			logger.debug("Trx a actualizar: " + trx);
		}		
		super.update("sqlUpdateNotificacionTrxBbv", trx);
	}
	
	/**
	 * Actualiza el pago notificado del Bco.BBVA
	 */
	private void updateNotificacionPagoBbv(Pago pago) {
		if (logger.isDebugEnabled()) {
			logger.debug("Estoy en sqlUpdateNotificacionPagoBbv (Dao)");
			
			logger.debug("Pago a actualizar, pago: " + pago);
		}
		super.update("sqlUpdateNotificacionPagoBbv", pago);
	}
	
}
