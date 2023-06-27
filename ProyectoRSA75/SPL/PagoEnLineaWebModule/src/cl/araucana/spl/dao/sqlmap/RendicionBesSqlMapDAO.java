
/*
 * @(#) RendicionBesSqlMapDAO.java    1.0 06-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */

package cl.araucana.spl.dao.sqlmap;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import cl.araucana.spl.beans.DetalleRendicionBES;
import cl.araucana.spl.beans.RendicionBES;
import cl.araucana.spl.beans.ResumenDetalleRendicionBES;
import cl.araucana.spl.dao.RendicionBesDAO;

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
 *            <TD> 06-08-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Jorge Landeros <BR> jlandero@schema.cl </TD>
 *            <TD> Versión inicial. Implementa los métodos para el manejo de una rendición del banco estado.
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
 * @author Lorge Landeros (jlandero@schema.cl)
 *
 * @version 1.0
 */

public class RendicionBesSqlMapDAO extends SqlMapDaoTemplate implements RendicionBesDAO {
	private static final String SQL_INSERT_RENDICION_BES = "sqlInsertRendicionBES";
	private static final String SQL_INSERT_RESUMEN_DETALLE_RENDICION_BES = "sqlInsertResumenDetalleRendicionBES";
	private static final String SQL_INSERT_DETALLE_RENDICION_BES = "sqlInsertDetalleRendicionBES";
	private static final String SQL_SEQUENCE_RENDICION_BES = "sqlSequenceRendicionBES";
	private static final String SQL_SEQUENCE_RESUMEN_DETALLE_RENDICION_BES = "sqlSequenceResumenDetalleRendicionBES";
	private static final String SQL_SEQUENCE_DETALLE_RENDICION_BES = "sqlSequenceDetalleRendicionBES";	
	private static final String SQL_FIND_DETALLE_RENDICION_BES_BY_PAGO_ID = "sqlFindDetalleRendicionBesByPagoId";
	private static final String SQL_COUNT_RENDICION_BES_BY_CHECKSUM = "sqlCountRendicionBesByChecksum";

	private static final Logger logger = Logger.getLogger(RendicionBesSqlMapDAO.class);
			
	public RendicionBesSqlMapDAO(DaoManager daoManager) {
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
	 * Retorna el numero de rendiciones BES con el mismo checksum
	 */
	public BigDecimal countRendicionByChecksum(String checksum) {
		BigDecimal contador = (BigDecimal) queryForObject(SQL_COUNT_RENDICION_BES_BY_CHECKSUM, checksum);
		return contador;
	}

	/**
	 * Busca detalles rendicion BES por idPago.
	 */
	public DetalleRendicionBES getDetalleRendicionBesByPagoId(BigDecimal idPago) {
		if (logger.isDebugEnabled())
			logger.debug("Entre a getDetalleRendicionBesByPagoId, el idPago: " + idPago);
		
		DetalleRendicionBES detalleRendicionBES = (DetalleRendicionBES) queryForObject(SQL_FIND_DETALLE_RENDICION_BES_BY_PAGO_ID, idPago);
		return detalleRendicionBES;
	}
	
	/**
	 * Insert de rendicion BES
	 */
	public BigDecimal insertRendicion(RendicionBES rendicionBES) {
		BigDecimal id = nextId(SQL_SEQUENCE_RENDICION_BES);
		rendicionBES.setIdRendicion(id);
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando rendicionBES " + rendicionBES);
		}
		super.insert(SQL_INSERT_RENDICION_BES, rendicionBES);
		return id;
	}

	/**
	 * Insert de resumen detalle rendicion BES
	 */
	public BigDecimal insertResumenDetalleRendicion(ResumenDetalleRendicionBES resumenDetalleRendicionBES) {
		BigDecimal id = nextId(SQL_SEQUENCE_RESUMEN_DETALLE_RENDICION_BES);
		resumenDetalleRendicionBES.setIdResDetalleRend(id);
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando rendicionBES " + resumenDetalleRendicionBES);
		}
		super.insert(SQL_INSERT_RESUMEN_DETALLE_RENDICION_BES, resumenDetalleRendicionBES);
		return id;
	}
	
	/**
	 * Insert detalle rendicion BES
	 */
	public void insertDetalleRendicion(DetalleRendicionBES detalleRendicionBES) {
		if (logger.isDebugEnabled()) {
			logger.debug("Insertando det rendicion BES " + detalleRendicionBES);
		}
	
		detalleRendicionBES.setIdDetalleRend(nextId(SQL_SEQUENCE_DETALLE_RENDICION_BES));
		super.insert(SQL_INSERT_DETALLE_RENDICION_BES, detalleRendicionBES);
	}	
}
