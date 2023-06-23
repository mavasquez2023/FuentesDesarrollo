package cl.araucana.spl.dao.sqlmap;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cl.araucana.spl.beans.DetalleTrxBit;
import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.TransaccionBit;
import cl.araucana.spl.dao.TransaccionBitDAO;

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
public class TransaccionBitSqlMapDAO extends SqlMapDaoTemplate implements TransaccionBitDAO {
	private static final Logger logger = Logger.getLogger(TransaccionBitSqlMapDAO.class);
	
	public TransaccionBitSqlMapDAO(DaoManager daoManager) {
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
	 * Insert Trx del Banco Itau
	 */
	public void insertTrx(TransaccionBit trx) {
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando transaccion Itau " + trx);
		}

		trx.setId(nextId("sqlSequenceTransaccionBit"));
		super.insert("sqlInsertTransaccionBit", trx);
		
		List detalles = trx.getDetalleTrxBit();
		for (Iterator iter = detalles.iterator(); iter.hasNext();) {
			DetalleTrxBit detalle = (DetalleTrxBit) iter.next();
			this.insertDetTrxBit(detalle);
		}
		
	}
	
	/**
	 * Llamada a insert detalle trx Bco. Itau
	 */
	private void insertDetTrxBit(DetalleTrxBit detalleTrxBit) {
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando detalle Trx Itau: " + detalleTrxBit);
		}

		detalleTrxBit.setId(nextId("sqlSequenceDetalleTrxBit"));
		
		super.insert("sqlInsertDetalleTrxBit", detalleTrxBit);
	}
	
	/**
	 * Busqueda por codigoIdTrx de una trx Bco. Itau
	 */
	public TransaccionBit findTransaccionByCodigoIdTrx(String codigoIdTrx) {
		logger.debug("Estoy en findTransaccionByCodigoIdTrx codigoIdTrx = "+codigoIdTrx.trim() );		
		TransaccionBit trx = (TransaccionBit) queryForObject("sqlFindTrxBitByCodigoIdTrx", codigoIdTrx.trim());
		logger.debug("findTransaccionByCodigoIdTrx data = " + trx.toString());
		return trx;
	}

	/**
	 * Actualiza la Trx Bco. Itau Notificada y el pago relacionado
	 */
	public void updateNotificacion(TransaccionBit trx) {
		if (logger.isDebugEnabled()) {
			logger.debug("Estoy en updateNotificacion / (Iatu Dao)");
			logger.debug("Transaccion a actualizar, trx: " + trx);
		}
		
		//Actualizar la trx
		updateNotificacionTransaccion(trx);
		
		//Actualizar el pago
		Pago pago = trx.getPago();
		updateNotificacionPagoBes(pago);
	}
	
	/**
	 * Actualiza la Trx del Bco.Itau
	 */
	public void updateNotificacionTransaccion(TransaccionBit trx) {
		if (logger.isDebugEnabled()) {
			logger.debug("Estoy en sqlUpdateNotificacionTrxBit (Dao)");
			
			logger.debug("Trx a actualizar: " + trx);
		}		
		super.update("sqlUpdateNotificacionTrxBit", trx);
	}
	
	/**
	 * Actualiza el pago notificado del Bco.Itau
	 */
	private void updateNotificacionPagoBes(Pago pago) {
		if (logger.isDebugEnabled()) {
			logger.debug("Estoy en sqlUpdateNotificacionPagoBit (Dao)");
			
			logger.debug("Pago a actualizar, pago: " + pago);
		}
		
		super.update("sqlUpdateNotificacionPagoBit", pago);
	}
	
}
