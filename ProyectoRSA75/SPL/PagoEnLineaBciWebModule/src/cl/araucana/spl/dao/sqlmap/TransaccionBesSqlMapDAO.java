
/*
 * @(#) TransaccionBesSqlMapDAO.java    1.0 06-08-2009
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */

package cl.araucana.spl.dao.sqlmap;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import cl.araucana.spl.beans.Pago;
import cl.araucana.spl.beans.TransaccionBes;
import cl.araucana.spl.dao.TransaccionBesDAO;

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
 *            <TD align="center"> <B>Versi�n</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripci�n</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 06-08-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Alejandro Sep�lveda Page <BR> asepulveda@schema.cl </TD>
 *            <TD> Versi�n inicial. Implementa los m�todos para el manejo de una transacci�n con banco estado. 
 *            </TD>
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
 * @author Alejandro Sep�lveda Page (asepulveda@schema.cl)
 *
 * @version 1.0
 */

public class TransaccionBesSqlMapDAO extends SqlMapDaoTemplate implements TransaccionBesDAO {
	private static final Logger logger = Logger.getLogger(TransaccionBesSqlMapDAO.class);
	
	public TransaccionBesSqlMapDAO(DaoManager daoManager) {
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
	 * Insert Trx del Banco Estado
	 */
	public void insertTrx(TransaccionBes trx) {
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando transaccion BEstado " + trx);
		}

		// Ahora inserto la trx del Banco Estado.
		trx.setId(nextId("sqlSequenceTransaccionBes"));
		super.insert("sqlInsertTransaccionBes", trx);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Transaccion BEstado insertada ok: " + trx);
		}
		
	}
		
	/**
	 * Busqueda por codigoIdTrx de una trx Bco.Estado
	 */
	public TransaccionBes findTransaccionByCodigoIdTrx(String codigoIdTrx) {
		TransaccionBes trx = (TransaccionBes) queryForObject("sqlFindTrxBesByCodigoIdTrx", codigoIdTrx);
		return trx;
	}

	/**
	 * Actualiza la Trx Bco.Estado Notificada y el pago relacionado
	 */
	public void updateNotificacion(TransaccionBes trx) {
		if (logger.isDebugEnabled()) {
			logger.debug("Estoy en updateNotificacion / (Estado Dao)");
			logger.debug("Transaccion a actualizar, trx: " + trx);
		}
		
		//Actualizar la trx
		updateNotificacionTransaccion(trx);
		
		//Actualizar el pago
		Pago pago = trx.getPago();
		updateNotificacionPagoBes(pago);
	}
	
	/**
	 * Actualiza la Trx del Bco.Estado
	 */
	public void updateNotificacionTransaccion(TransaccionBes trx) {
		if (logger.isDebugEnabled()) {
			logger.debug("Estoy en sqlUpdateNotificacionTrxBes (Dao)");
			
			logger.debug("Trx a actualizar: " + trx);
		}		
		super.update("sqlUpdateNotificacionTrxBes", trx);
	}
	
	/**
	 * Actualiza el pago notificado del Bco.Estado
	 */
	private void updateNotificacionPagoBes(Pago pago) {
		if (logger.isDebugEnabled()) {
			logger.debug("Estoy en updateNotificacionPagoBes (Dao)");
			
			logger.debug("Pago a actualizar, pago: " + pago);
		}
		
		super.update("sqlUpdateNotificacionPagoBes", pago);
	}
	
}
