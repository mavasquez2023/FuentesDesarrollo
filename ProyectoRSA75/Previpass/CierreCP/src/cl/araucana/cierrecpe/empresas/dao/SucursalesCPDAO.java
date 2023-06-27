

/*
 * @(#) EmpresaCPDAO.java    1.0 10-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.dao;

import java.sql.SQLException;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.empresas.to.FiltroSucursal;
import cl.araucana.cierrecpe.empresas.planillas.IdentificacionSucursal;
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
public class SucursalesCPDAO implements DAO_Interface {
private ConectaDB2 db2;
private static Logger logger = LogManager.getLogger();	
	
	public SucursalesCPDAO(ConectaDB2 db2){
		this.db2= db2;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void delete(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente

	}

	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public int insert(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk) throws SQLException {
		IdentificacionSucursal sucursal=null;
		
		FiltroSucursal filtroTO= (FiltroSucursal) pk;
		StringBuffer query= new StringBuffer();
		query.append("SELECT  t1.direccion, t1.numero, t1.departamento, t1.telefono, t1.fax, t1.email, t1.id_comuna, t2.nombre as comuna, t3.nombre as ciudad , t3.id_region ");
		query.append("FROM sucursal t1, comuna t2, ciudad t3 ");
		query.append("WHERE t1.id_empresa= ? ");
		query.append("AND t1.id_sucursal= ? ");
		query.append("AND t1.id_comuna= t2.id_comuna ");
		query.append("AND t2.id_ciudad= t3.id_ciudad ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		//db2.setStatement(1, 5); //PAGADO
		db2.setStatement(1, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(2, filtroTO.getId_sucursal());
		//Se ejecuta la query
		db2.executeQuery();
		if (db2.next()) {
			//Se rescatan los datos de los cotizantes
			String direccion= db2.getString(1);
			String numero= db2.getString(2);
			String departamento= db2.getString(3);
			String telefono= db2.getString(4);
			String fax= db2.getString(5);
			String email= db2.getString(6);
			int id_comuna= db2.getInt(7);
			String comuna= db2.getString(8);
			String ciudad= db2.getString(9);
			String region= db2.getString(10);
			
			//Se genera nueva instancia de sucursal
			sucursal= new IdentificacionSucursal();
			sucursal.setDireccion(direccion + " " + numero + " " + departamento);
			sucursal.setTelefono(telefono);
			sucursal.setFax(fax);
			sucursal.setEmail(email);
			sucursal.setIdComuna(id_comuna);
			sucursal.setComuna(comuna);
			sucursal.setCiudad(ciudad);
			sucursal.setRegion(region);
			
			sucursal.setCodigo(filtroTO.getId_sucursal());
		}
		db2.closeStatement();
		return sucursal;
	}
	

	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int update(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

}

