

/*
 * @(#) planillaAFPDAO.java    1.0 10-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.dao;

import java.sql.SQLException;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.ConectaDB2;

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
 *            <TD> 10-07-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZORÍN <BR> clillo@laaraucana.cl </TD>
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
 * @author CLAUDIO LILLO AZORÍN (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class EsquemaPublicacionDAO implements DAO_Interface, Constants {
	private ConectaDB2 db2;
	private static Logger logger = LogManager.getLogger();
	/**
	 * 
	 */
	public EsquemaPublicacionDAO(ConectaDB2 db2) {
		this.db2= db2;
	}

	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void delete(Object pk) throws SQLException {
	}

	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public int insert(Object obj) {
		return 0;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public boolean selectExists(String nombreEsquema) throws SQLException {
		boolean result=false;
		try {
			StringBuffer query= new StringBuffer();
			query.append("select count(*) from " + nombreEsquema + ".PWF1000" );
			logger.finest("Query=" + query.toString());
			db2.executeQuery(query.toString());
			if(db2.next()){
				result=true;
			}
		} catch (SQLException e) {
			logger.fine(">>CrearEsquema.select, esquema NO existe" + nombreEsquema);
		}
		finally{
			db2.closeStatement();
		}
		return result;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public int selectCount(String nombreEsquema, String tabla) throws SQLException {
		int count=0;
		try {
			StringBuffer query= new StringBuffer();
			query.append("select count(*) from " + nombreEsquema + "." + tabla );
			logger.finest("Query=" + query.toString());
			db2.executeQuery(query.toString());
			if(db2.next()){
				count= db2.getInt(1);
			}
		} catch (SQLException e) {
			logger.fine(">>CrearEsquema.select, esquema NO existe" + nombreEsquema);
		}
		finally{
			db2.closeStatement();
		}
		return count;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int update(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	public Object select(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

}

