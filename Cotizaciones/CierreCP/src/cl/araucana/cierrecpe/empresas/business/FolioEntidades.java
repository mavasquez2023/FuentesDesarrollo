

/*
 * @(#) FolioEntidades.java    1.0 04-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.business;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import cl.araucana.cierrecpe.dao.CPDAO;
import cl.araucana.cierrecpe.empresas.dao.FolioEntidadesCPDAO;
import cl.araucana.cierrecpe.empresas.planillas.EntidadFolio;
import cl.araucana.core.util.Rut;
import java.util.logging.Logger;
import cl.araucana.core.util.logging.LogManager;

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
 *            <TD> 04-08-2009 </TD>
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
public class FolioEntidades {
	private CPDAO cpDAO=null;
	private Map folios;
	private FolioEntidadesCPDAO foliosDAO=null;
	private static Logger logger = LogManager.getLogger();
	/**
	 * 
	 */
	public FolioEntidades(int cierre) {
		boolean exito=false;
		try {
			cpDAO= new CPDAO();
			cpDAO.setAutoCommit(false);
			//Se rescata los folios de las entidades asociadas a los comprobantes
			foliosDAO= new FolioEntidadesCPDAO(cpDAO.getConnection());
			folios= (HashMap)foliosDAO.select(String.valueOf(cierre));
			logger.fine("Cantidad de entidades a reservar folios:" + folios.size());
			for (Iterator iter = folios.keySet().iterator(); iter.hasNext();) {
				Rut rutEntidad = (Rut) iter.next();
				EntidadFolio entidad= (EntidadFolio)folios.get(rutEntidad);
				logger.config("Entidad: " + entidad.getNombreEntidad() + ", folio actual:" + entidad.getFolioActual() + ", a reservar:" + entidad.getDeltaRango());
				foliosDAO.update(entidad);
			}
			cpDAO.commit();
			exito=true;
		} catch (Exception e) {
			logger.severe("Error, mensaje:" + e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(!exito){
					logger.severe("Error, haciendo rollback de folios entidades");
					cpDAO.rollback();
					throw new Exception();
				}
			} catch (Exception e) {
				logger.severe("Error, mensaje:" + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	public synchronized int getFolio(Rut rutEntidad){
		EntidadFolio entidad=(EntidadFolio)folios.get(rutEntidad);
		int folio= entidad.getFolioActual();
		entidad.setFolioActual(folio+1); 
		if (folio==entidad.getRangoFinal()){
			getRango(entidad);
		}
		return folio;
	}
	
	private void getRango(EntidadFolio entidad){
		try {
			int folioActual=foliosDAO.select(entidad.getRutEntidad());
			entidad.setFolioActual(folioActual+1);
			if((folioActual + entidad.getDeltaRango())>entidad.getFolioFinal()){
				entidad.setRangoFinal(entidad.getFolioFinal());
			}else{
				entidad.setRangoFinal(folioActual + entidad.getDeltaRango());
			}
			logger.config("Entidad: " + entidad.getNombreEntidad() + ", a reservar:" + entidad.getDeltaRango()+ ", rango final: " + entidad.getRangoFinal());
			foliosDAO.update(entidad);
		} catch (SQLException e) {
			logger.severe("Error, mensaje:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void reciclar(){
		try {
			for (Iterator iter = folios.keySet().iterator(); iter.hasNext();) {
				Rut rutEntidad = (Rut) iter.next();
				EntidadFolio entidad= (EntidadFolio)folios.get(rutEntidad);
				int folioActual= foliosDAO.select(entidad.getRutEntidad());
				if (folioActual== entidad.getRangoFinal()){
					entidad.setRangoFinal(entidad.getFolioActual());
					logger.config("Entidad: " + entidad.getNombreEntidad() + ", retornando folio a: " + entidad.getRangoFinal());
					foliosDAO.update(entidad);
				}
			}
			cpDAO.commit();
		} catch (Exception e) {
			logger.warning("Problemas al devolver folios no utilizados, mensaje:" + e.getMessage());
			e.printStackTrace();
		}
	}
	public void close(){
		cpDAO.closeConnectionDAO();
	 }
	public int getSize(){
		return folios.size();
	}
	
	public Set getRutEntidades(){
		return folios.keySet();
	}
	public String getNombreEntidad(Rut rutEntidad){
		EntidadFolio entidad=(EntidadFolio)folios.get(rutEntidad);
		String nombreEntidad= entidad.getNombreEntidad();
		if(nombreEntidad.length()>40){
			nombreEntidad= nombreEntidad.substring(0, 40);
		}
		return nombreEntidad;
	}
}

