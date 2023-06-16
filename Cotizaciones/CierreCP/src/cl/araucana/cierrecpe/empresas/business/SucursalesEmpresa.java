

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
import cl.araucana.cierrecpe.empresas.dao.SucursalesCPDAO;
import cl.araucana.cierrecpe.empresas.planillas.EntidadFolio;
import cl.araucana.cierrecpe.empresas.planillas.IdentificacionSucursal;
import cl.araucana.cierrecpe.empresas.to.FiltroSucursal;
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
public class SucursalesEmpresa {
	private CPDAO cpDAO=null;
	private Map sucursales;
	private SucursalesCPDAO sucursalDAO=null;
	private Rut rutEmpresa;
	private static Logger logger = LogManager.getLogger();
	/**
	 * 
	 */
	public SucursalesEmpresa(Rut rutEmpresa) {
		try {
			cpDAO= new CPDAO();
			sucursalDAO= new SucursalesCPDAO(cpDAO.getConnection());
			sucursales= new HashMap();
			this.rutEmpresa= rutEmpresa;
		} catch (Exception e) {
			logger.severe("Error, mensaje: " + e.getMessage());
			e.printStackTrace();
		}
	}
	 public void setIdentificacionSucursal(String idsucursal, IdentificacionSucursal sucursal){
		 sucursales.put(idsucursal, sucursal);
	 }
	
	public IdentificacionSucursal getSucursal(String id_sucursal){
		IdentificacionSucursal sucursal=null;
		try {
			logger.fine("Consultando sucursal " + id_sucursal + " en mapa de sucursales en memoria");
			Object sucursalObj = sucursales.get(id_sucursal);
			if(sucursalObj== null){
				//se arma filtro de consulta
				FiltroSucursal filtro= new FiltroSucursal();
				filtro.setId_sucursal(id_sucursal);
				filtro.setRutEmpresa(rutEmpresa);
				logger.fine(">>Sucursal No mapeada. Invocando SucursalDAO para rescatar información");
				sucursalObj= sucursalDAO.select(filtro);
			setIdentificacionSucursal(id_sucursal, (IdentificacionSucursal) sucursalObj);
			}
			sucursal= (IdentificacionSucursal) sucursalObj;
		} catch (SQLException e) {
			sucursal=null;
			logger.warning("Sucursal null, mensaje: " + e.getMessage());
			e.printStackTrace();
		}
		return sucursal;
	}
	public void close(){
		cpDAO.closeConnectionDAO(); 
	}
}

