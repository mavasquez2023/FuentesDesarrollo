

/*
 * @(#) FoliacionCPDAO.java    1.0 13-03-2012
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.entidades.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import lotus.domino.Database;
import lotus.domino.Document;
import lotus.domino.NotesException;
import lotus.domino.View;
import lotus.domino.ViewEntry;
import lotus.domino.ViewEntryCollection;

import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.entidades.to.FoliosEntidadTO;
import cl.araucana.core.util.Rut;
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
 *            <TD> 13-03-2012 </TD>
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
public class ConcatenarCierresDAO implements DAO_Interface  {
	private ConectaDB2 db2;
	private Database dbDom;
	private static Logger logger = LogManager.getLogger();
	
	public ConcatenarCierresDAO(ConectaDB2 db2){
		try {
			logger.fine("Verificando conexión en ConcatenarCierresDAO:" + !db2.isClosed());
			this.db2= db2;
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	public ConcatenarCierresDAO(Database dbDom){
		try {
			logger.fine("Verificando conexión en ConcatenarCierresDAO:" + !dbDom.isOpen());
			this.dbDom= dbDom;
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	public void delete(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		
	}

	public int insert(Object obj) throws SQLException {
		return 0;
	}
	
	public List selectPrevipass(String periodo) throws SQLException {
		List cierres = new ArrayList();
		StringBuffer sqlstmt = new StringBuffer();
        sqlstmt.append("SELECT distinct cierre ");
        sqlstmt.append("FROM resprocie ");
        sqlstmt.append("WHERE periodo = ? ");
        sqlstmt.append("ORDER BY 1 ");
        logger.fine("Ejecutando Query: "+ sqlstmt.toString());
        db2.prepareQuery(sqlstmt.toString());
        db2.setStatement(1, Integer.parseInt(periodo));
        db2.executeQuery();
		while (db2.next()) {
			int cierre= db2.getInt(1);
			cierres.add(String.valueOf(cierre));
		}
		return cierres;
	}
	public List selectDomino(String vista, String periodo) throws SQLException {
		List cierres = new ArrayList();
		try {
			View viewlis 	= dbDom.getView(vista);
			ViewEntryCollection veclis 	= viewlis.getAllEntriesByKey(periodo, true);
			logger.fine("Número de cierres encontrados: "  + veclis.getCount());
			ViewEntry entrylis 	= veclis.getFirstEntry();
			while (entrylis != null ) {
				Document doclis	= entrylis.getDocument();
				String cierre = doclis.getItemValueString("cierre");
				if(!cierres.contains(cierre) && !cierre.trim().equals("")){
					cierres.add(cierre);
				}
				entrylis = veclis.getNextEntry();
				doclis.recycle();
			}
			veclis.recycle();
			viewlis.recycle();
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return cierres;
	}

	public int update(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	public Object select(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	

}

