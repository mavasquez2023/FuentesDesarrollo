

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
public class FoliacionCPDAO implements DAO_Interface  {
	private ConectaDB2 db2;
	private Database dbDom;
	private static Logger logger = LogManager.getLogger();
	
	public FoliacionCPDAO(ConectaDB2 db2, Database dbDom){
		try {
			logger.fine("Verificando conexión en FoliacionCPDAO:" + !db2.isClosed());
			this.db2= db2;
			this.dbDom= dbDom;
		} catch (SQLException e) {
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
	
	public Object select(Object pk) throws SQLException {
		List entidades = new ArrayList();
		StringBuffer sqlstmt = new StringBuffer();
        sqlstmt.append("SELECT ID_ENTIDAD_PAGADORA, FOLIO_INICIAL, FOLIO_FINAL, FOLIO_ACTUAL ");
        sqlstmt.append("FROM FOLIACION ");
        sqlstmt.append("WHERE FOLIOS_EN_USO = 1 ");
        sqlstmt.append("AND ID_ENTIDAD_PAGADORA > 0 ");
        logger.fine("Ejecutando Query: "+ sqlstmt.toString());
        db2.executeQuery(sqlstmt.toString());
		while (db2.next()) {
			FoliosEntidadTO foliosTO= new FoliosEntidadTO();
			int rutEntidad= db2.getInt(1);
			int folioInicial= db2.getInt(2);
			int folioFinal= db2.getInt(3);
			int folioActual= db2.getInt(4);
			foliosTO.setRutEntidad(new Rut(rutEntidad));
			foliosTO.setFolioInicial(folioInicial);
			foliosTO.setFolioFinal(folioFinal);
			foliosTO.setFolioActual(folioActual);
			entidades.add(foliosTO);
		}
		return entidades;
	}
	public Object selectDomino(Object pk) throws SQLException {
		List entidades = new ArrayList();
		try {
			String vista= (String)pk;
			View viewlis 	= dbDom.getView(vista);
			ViewEntryCollection veclis 	= viewlis.getAllEntries();
			logger.fine("Número de entidades encontrados: "  + veclis.getCount());
			ViewEntry entrylis 	= veclis.getFirstEntry();
			while (entrylis != null ) {
				Document doclis	= entrylis.getDocument();
				String estado = doclis.getItemValueString("estado");
				if(estado.equalsIgnoreCase("En Uso")){
					
					String rutEntidad = doclis.getItemValueString("rutemp");
					String folioinicial = doclis.getItemValueString("numinicial");
					String folioActual = doclis.getItemValueString("numactual");
					String foliofinal = doclis.getItemValueString("numfinal");
					if(foliofinal!=null){
						FoliosEntidadTO foliosTO= new FoliosEntidadTO();
						foliosTO.setRutEntidad(new Rut(rutEntidad.substring(0, rutEntidad.indexOf('-'))));
						foliosTO.setFolioActual(Integer.parseInt(folioActual));
						foliosTO.setFolioInicial(Integer.parseInt(folioinicial));
						foliosTO.setFolioFinal(Integer.parseInt(foliofinal));
						entidades.add(foliosTO);
					}
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
		return entidades;
	}

	public int update(Object obj) throws SQLException {
		FoliosEntidadTO foliosEnt= (FoliosEntidadTO) obj;
		StringBuffer sqlstmt = new StringBuffer();
        sqlstmt.append("UPDATE FOLIACION ");
        sqlstmt.append("set FOLIO_INICIAL= ? ");
        sqlstmt.append(", FOLIO_FINAL= ? ");
        sqlstmt.append(", FOLIO_ACTUAL= ? ");
        sqlstmt.append("where ID_ENTIDAD_PAGADORA = ? ");
        sqlstmt.append("and FOLIOS_EN_USO = 1 ");
        db2.prepareQuery(sqlstmt.toString());
        db2.setStatement(1, foliosEnt.getFolioInicial());
        db2.setStatement(2, foliosEnt.getFolioFinal());
        db2.setStatement(3, foliosEnt.getFolioActual());
        db2.setStatement(4, foliosEnt.getRutEntidad().getNumber());
        logger.fine("Ejecutando query: " + sqlstmt.toString() + ", entidad: " + foliosEnt.getRutEntidad().toString() + ", folio Actual= " + foliosEnt.getFolioActual());
        return db2.executeUpdate();
	}

	public int updateDomino(Object obj, String vista) throws SQLException {
		try {
			FoliosEntidadTO foliosEnt= (FoliosEntidadTO) obj;
			Vector filtro= new Vector();
			filtro.add(foliosEnt.getRutEntidad().getNumber() + "-" + foliosEnt.getRutEntidad().getDV() );
			filtro.add("En Uso");
			View viewlis 	= dbDom.getView(vista);
			logger.fine("Actualizando documento entidad: " + foliosEnt.getRutEntidad().getNumber() + "-" + foliosEnt.getRutEntidad().getDV() + ", folio Actual: " + foliosEnt.getFolioActual());
			Document doc= viewlis.getDocumentByKey(filtro, true);
			if(doc!= null){
				doc.replaceItemValue("numinicial", String.valueOf(foliosEnt.getFolioInicial()));
				doc.replaceItemValue("numfinal", String.valueOf(foliosEnt.getFolioFinal()));
				doc.replaceItemValue("numactual", String.valueOf(foliosEnt.getFolioActual()));
				doc.save(true, true);
				logger.fine("Salvado OK");
				return 1;
			}
			
		} catch (NotesException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
        return 0;
	}
	

}

