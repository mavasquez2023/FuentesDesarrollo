

/*
 * @(#) EmpresaCPDAO.java    1.0 10-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.empresas.to.BancoTO;
import cl.araucana.cierrecpe.empresas.to.BoletaTO;
import cl.araucana.cierrecpe.empresas.to.ComprobanteCPTO;
import cl.araucana.core.util.AbsoluteDate;
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
public class BoletasCPDAO implements DAO_Interface {
private ConectaDB2 db2;
private static Logger logger = LogManager.getLogger();	
private static int PAGADO=5;
private static int FORMA_PAGO_EMPRESA_SPL=1;

	public BoletasCPDAO(ConectaDB2 db2){
		this.db2= db2;
	}
	
	public Collection selectBoletas(int periodo) throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select ID_EMPRESA, ID_BOLETA, ID_BANCO, ID_CUENTA, MONTO, ESTADO, FECHA_CREACION, HORA_CREACION ");
		query.append("FROM BOLETA ");
		query.append("WHERE PERIODO= ? ");
		query.append("ORDER BY 7 desc, 8 desc ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		//Se ejecuta la query
		db2.setStatement(1, periodo);
		db2.executeQuery();
		List boletas= new ArrayList();
		while (db2.next()) {
			int rutempresa= db2.getInt(1);
			int idBoleta= db2.getInt(2);
			int idBanco= db2.getInt(3);
			String idCuenta= db2.getString(4);
			long monto= db2.getLong(5);
			String estado= db2.getString(6);
			//AbsoluteDate fechaPago= new AbsoluteDate(db2.getDate(6));
			Date fecha= db2.getDate(7);
			Time hora= db2.getTime(8);
				
			BoletaTO boleta= new BoletaTO();
			boleta.setPeriodo(periodo);
			boleta.setRutEmpresa(new Rut(rutempresa));
			boleta.setIdBoleta(idBoleta);
			boleta.setIdBanco(idBanco);
			boleta.setIdCuenta(idCuenta);
			boleta.setMonto(monto);
			boleta.setEstado(estado);
			boleta.setFecha(fecha.toString());
			boleta.setHora(hora.toString());
			boletas.add(boleta);
		}
		return boletas;
	}
	
	public Collection selectBancos() throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select NOMBRE, ID_BANCO, ID_CUENTA ");
		query.append("FROM BANCO_BOLETAS ");
		query.append("WHERE estado= ? ");
		query.append("ORDER BY 1 ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		//Se ejecuta la query
		db2.setStatement(1, '1');
		db2.executeQuery();
		List bancos= new ArrayList();
		while (db2.next()) {
			String nombre= db2.getString(1);
			int idBanco= db2.getInt(2);
			String idCuenta= db2.getString(3);
				
			BancoTO banco= new BancoTO();
			banco.setIdBanco(idBanco);
			banco.setIdCuenta(idCuenta.trim());
			banco.setNombre(nombre.trim());
			bancos.add(banco);
		}
		return bancos;
	}
	
	public Collection selectComprobantes(int rutEmpresa) throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select t1.id_codigo_barra, t1.folio_tesoreria, t2.id_empresa, t3.razon_social, t2.id_convenio, 'R', t1.total ");
		query.append("from comprobante_pago t1 ");
		query.append("join nominare t2 ");
		query.append("on t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("join empresa t3 ");
		query.append("on t2.id_empresa= t3.id_empresa "); 
		query.append("join "); 
		query.append("(select t1.id_empresa, t1.id_convenio from encargado_convenio t1 ");
		query.append("where id_encargado= (select id_admin from empresa "); 
		query.append("where id_empresa= ?) ");	
		query.append("UNION ");
		query.append("select t1.id_empresa, t1.id_convenio from convenio t1 "); 
		query.append("join empresa t2 ");
		query.append("on  t1.id_empresa= t2.id_empresa ");
		query.append("where id_admin= (select id_admin from empresa "); 
		query.append("where id_empresa= ?) ");
		query.append("group by t1.id_empresa, t1.id_convenio ");
		query.append(") as B ");
		query.append("on t2.id_empresa= B.id_empresa ");
		query.append("and t2.id_convenio= B.id_convenio ");
		query.append("where t1.id_estado in (3) ");
		query.append("and t1.folio_tesoreria>0 ");
		query.append("UNION ");
		query.append("select t1.id_codigo_barra, t1.folio_tesoreria, t2.id_empresa, t3.razon_social, t2.id_convenio, 'G', t1.total "); 
		query.append("from comprobante_pago t1 ");
		query.append("join nominagr t2 ");
		query.append("on t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("join empresa t3 ");
		query.append("on t2.id_empresa= t3.id_empresa "); 
		query.append("join "); 
		query.append("(select t1.id_empresa, t1.id_convenio from encargado_convenio t1 ");
		query.append("where id_encargado= (select id_admin from empresa "); 
		query.append("where id_empresa= ?) ");	
		query.append("UNION ");
		query.append("select t1.id_empresa, t1.id_convenio from convenio t1 "); 
		query.append("join empresa t2 ");
		query.append("on  t1.id_empresa= t2.id_empresa ");
		query.append("where id_admin= (select id_admin from empresa "); 
		query.append("where id_empresa= ?) ");
		query.append("group by t1.id_empresa, t1.id_convenio ");
		query.append(") as B ");
		query.append("on t2.id_empresa= B.id_empresa ");
		query.append("and t2.id_convenio= B.id_convenio ");
		query.append("where t1.id_estado in (3) ");
		query.append("and t1.folio_tesoreria>0 ");
		query.append("UNION ");
		query.append("select t1.id_codigo_barra, t1.folio_tesoreria, t2.id_empresa, t3.razon_social, t2.id_convenio, 'D', t1.total "); 
		query.append("from comprobante_pago t1 ");
		query.append("join nominadc t2 ");
		query.append("on t1.id_codigo_barra= t2.id_codigo_barra "); 
		query.append("join empresa t3 ");
		query.append("on t2.id_empresa= t3.id_empresa "); 
		query.append("join "); 
		query.append("(select t1.id_empresa, t1.id_convenio from encargado_convenio t1 ");
		query.append("where id_encargado= (select id_admin from empresa "); 
		query.append("where id_empresa= ?) ");	
		query.append("UNION ");
		query.append("select t1.id_empresa, t1.id_convenio from convenio t1 ");
		query.append("join empresa t2 ");
		query.append("on  t1.id_empresa= t2.id_empresa ");
		query.append("where id_admin= (select id_admin from empresa "); 
		query.append("where id_empresa= ?) ");
		query.append("group by t1.id_empresa, t1.id_convenio ");
		query.append(") as B ");
		query.append("on t2.id_empresa= B.id_empresa ");
		query.append("and t2.id_convenio= B.id_convenio ");
		query.append("where t1.id_estado in (3) ");
		query.append("and t1.folio_tesoreria>0 ");
		query.append("UNION ");
		query.append("select t1.id_codigo_barra, t1.folio_tesoreria, t2.id_empresa, t3.razon_social, t2.id_convenio, 'A', t1.total "); 
		query.append("from comprobante_pago t1 ");
		query.append("join nominara t2 ");
		query.append("on t1.id_codigo_barra= t2.id_codigo_barra "); 
		query.append("join empresa t3 ");
		query.append("on t2.id_empresa= t3.id_empresa "); 
		query.append("join "); 
		query.append("(select t1.id_empresa, t1.id_convenio from encargado_convenio t1 ");
		query.append("where id_encargado= (select id_admin from empresa "); 
		query.append("where id_empresa= ?) ");	
		query.append("UNION ");
		query.append("select t1.id_empresa, t1.id_convenio from convenio t1 "); 
		query.append("join empresa t2 ");
		query.append("on  t1.id_empresa= t2.id_empresa ");
		query.append("where id_admin= (select id_admin from empresa "); 
		query.append("where id_empresa= ?) ");
		query.append("group by t1.id_empresa, t1.id_convenio ");
		query.append(") as B ");
		query.append("on t2.id_empresa= B.id_empresa ");
		query.append("and t2.id_convenio= B.id_convenio ");
		query.append("where t1.id_estado in (3) ");
		query.append("and t1.folio_tesoreria>0 ");
		query.append("order by 2, 3 ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		//Se ejecuta la query
		db2.setStatement(1, rutEmpresa);
		db2.setStatement(2, rutEmpresa);
		db2.setStatement(3, rutEmpresa);
		db2.setStatement(4, rutEmpresa);
		db2.setStatement(5, rutEmpresa);
		db2.setStatement(6, rutEmpresa);
		db2.setStatement(7, rutEmpresa);
		db2.setStatement(8, rutEmpresa);
		db2.executeQuery();
		List comprobantes= new ArrayList();
		while (db2.next()) {
			long codigoBarra= db2.getLong(1);
			int folio= db2.getInt(2);
			int rutempresa= db2.getInt(3);
			String razonSocial= db2.getString(4);
			int convenio= db2.getInt(5);
			String tipoNomina= db2.getString(6);
			long monto= db2.getLong(7);
				
			ComprobanteCPTO comprobante= new ComprobanteCPTO();
			comprobante.setCodigoBarra(codigoBarra);
			comprobante.setFolioIngreso(folio);
			comprobante.setRutEmpresa(new Rut(rutempresa));
			comprobante.setRazonSocial(razonSocial);
			comprobante.setConvenio(convenio);
			comprobante.setTipoNomina(tipoNomina);
			comprobante.setMonto(monto);
			comprobantes.add(comprobante);
		}
		return comprobantes;
	}
	
	public BoletaTO selectBoleta(int idBoleta) throws SQLException{
		StringBuffer query= new StringBuffer();
		BoletaTO boleta=null;
		query.append("select ID_BANCO, ID_CUENTA, MONTO, FECHA_CREACION, HORA_CREACION ");
		query.append("FROM BOLETA ");
		query.append("WHERE ID_BOLETA= ? ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		//Se ejecuta la query
		db2.setStatement(1, idBoleta);
		db2.executeQuery();
		if (db2.next()) {
			int idBanco= db2.getInt(1);
			String idCuenta= db2.getString(2);
			long monto= db2.getLong(3);
			Date fecha= db2.getDate(4);
			Time hora= db2.getTime(5);
				
			boleta= new BoletaTO();
			boleta.setIdBoleta(idBoleta);
			boleta.setIdBanco(idBanco);
			boleta.setIdCuenta(idCuenta);
			boleta.setMonto(monto);
			boleta.setFecha(fecha.toString());
			boleta.setHora(hora.toString());
		}
		return boleta;
	}
	
	public BancoTO selectBanco(int idBanco) throws SQLException{
		StringBuffer query= new StringBuffer();
		BancoTO bancoTO=null;
		query.append("select ID_CUENTA, NOMBRE ");
		query.append("FROM BANCO_BOLETAS ");
		query.append("WHERE ID_BANCO= ? ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		//Se ejecuta la query
		db2.setStatement(1, idBanco);
		db2.executeQuery();
		if (db2.next()) {
			String idCuenta= db2.getString(1);
			String nombre= db2.getString(2);
				
			bancoTO= new BancoTO();
			bancoTO.setIdBanco(idBanco);
			bancoTO.setIdCuenta(idCuenta);
			bancoTO.setNombre(nombre);
		}
		return bancoTO;
	}
	
	public long select(int folio) throws SQLException {
		String query="";
		long resultado=0;
		try {
			query= "select id_codigo_barra from comprobante_pago where folio_tesoreria= ?";
			db2.prepareQuery(query);
			db2.setStatement(1, folio);
			db2.executeQuery();
			if(db2.next()){
				long codigo= db2.getLong(1);
				resultado= codigo;
			}
			db2.closeStatement();
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return resultado;
	}
	
	
public int insert(Object obj) throws SQLException {
	BoletaTO boleta= (BoletaTO)obj;
	StringBuffer query= new StringBuffer();
	query.append("INSERT INTO BOLETA (PERIODO, ID_EMPRESA, ID_BANCO, ID_CUENTA, MONTO, ESTADO) values( ?, ?, ?, ?, ?, ? ) ");
	logger.finest("Query=" + query.toString());
	db2.prepareQuery(query.toString());
	db2.setStatement(1, boleta.getPeriodo());
	db2.setStatement(2, boleta.getRutEmpresa().getNumber());
	db2.setStatement(3, boleta.getIdBanco());
	db2.setStatement(4, boleta.getIdCuenta());
	db2.setStatement(5, boleta.getMonto());
	db2.setStatement(6, boleta.getEstado());
	return db2.executeUpdate();
}

public int insertBanco(Object obj) throws SQLException {
	BancoTO bancoTO= (BancoTO)obj;
	StringBuffer query= new StringBuffer();
	query.append("INSERT INTO BANCO_BOLETAS (ID_BANCO, ID_CUENTA, NOMBRE, ESTADO) values( ?, ?, ?, ?) ");
	logger.finest("Query=" + query.toString());
	db2.prepareQuery(query.toString());
	db2.setStatement(1, bancoTO.getIdBanco());
	db2.setStatement(2, bancoTO.getIdCuenta());
	db2.setStatement(3, bancoTO.getNombre());
	db2.setStatement(4, "1");
	return db2.executeUpdate();
}

public Object select(Object pk) throws SQLException {
	// TODO Apéndice de método generado automáticamente
	return null;
}
/* (sin Javadoc)
 * @see cl.araucana.planillascp.dao.DAO_Interface#delete(java.lang.Object)
 */
public void delete(Object pk) throws SQLException {
	Integer idBoleta= (Integer)pk;
	StringBuffer query= new StringBuffer();
	query.append("DELETE  FROM BOLETA WHERE ID_BOLETA= ? ");
	logger.finest("Query=" + query.toString());
	db2.prepareQuery(query.toString());
	db2.setStatement(1, idBoleta.intValue());
	db2.executeUpdate();
}

public void deleteBanco(int idBanco) throws SQLException {
	StringBuffer query= new StringBuffer();
	query.append("DELETE  FROM BANCO_BOLETAS WHERE ID_BANCO= ? ");
	logger.finest("Query=" + query.toString());
	db2.prepareQuery(query.toString());
	db2.setStatement(1, idBanco);
	db2.executeUpdate();
}

public int updateBanco(Object obj) throws SQLException {
	BancoTO bancoTO= (BancoTO)obj;
	StringBuffer query= new StringBuffer();
	query.append("UPDATE BANCO_BOLETAS SET ID_CUENTA= ?, NOMBRE= ? WHERE ID_BANCO= ? AND ID_CUENTA= ?");
	logger.finest("Query=" + query.toString());
	System.out.println("Query=" + query.toString());
	db2.prepareQuery(query.toString());
	db2.setStatement(1, bancoTO.getIdCuenta());
	db2.setStatement(2, bancoTO.getNombre());
	db2.setStatement(3, bancoTO.getIdBanco());
	db2.setStatement(4, bancoTO.getIdCuentaOld());
	System.out.println("cuenta=" + bancoTO.getIdCuenta() + ", nombre=" + bancoTO.getNombre() + ", banco=" +bancoTO.getIdBanco());
	return db2.executeUpdate();
}

public int updateEstado(int idBoleta, String estado) throws SQLException {
	StringBuffer query= new StringBuffer();
	query.append("UPDATE BOLETA SET ESTADO= ? WHERE ID_BOLETA= ? ");
	logger.finest("Query=" + query.toString());
	db2.prepareQuery(query.toString());
	db2.setStatement(1, estado);
	db2.setStatement(2, idBoleta);
	return db2.executeUpdate();
}

public int updateComprobanteCP(int folio) throws SQLException {
	int resultado=0;
	String queryUpdateComprobante="", queryUpdateNominaRE="", queryUpdateNominaGR="", queryUpdateNominaDC="", queryUpdateNominaRA="";
	queryUpdateComprobante= "update comprobante_pago  set  id_estado= ?, forma_pago= ?, pagado= current_date  where  folio_tesoreria = ?  ";
	queryUpdateNominaRE= "update nominare  set  id_estado= ?  where  id_codigo_barra = ?  ";
	queryUpdateNominaGR= "update nominagr  set  id_estado= ?  where  id_codigo_barra = ?  ";
	queryUpdateNominaDC= "update nominadc  set  id_estado= ?  where  id_codigo_barra = ?  ";
	queryUpdateNominaRA= "update nominara  set  id_estado= ?  where  id_codigo_barra = ?  ";
	
	logger.finest("Query=" + queryUpdateComprobante);
	db2.prepareQuery(queryUpdateComprobante);
	db2.setStatement(1, PAGADO);
	db2.setStatement(2, FORMA_PAGO_EMPRESA_SPL);
	db2.setStatement(3, folio);
	resultado= db2.executeUpdate();
	if (resultado>0){
		long id_codigo_barra= select(folio);
		db2.prepareQuery(queryUpdateNominaRE);
		db2.setStatement(1, PAGADO);
		db2.setStatement(2, id_codigo_barra);
		resultado = db2.executeUpdate();
		if (resultado==0){
			db2.prepareQuery(queryUpdateNominaGR);
			db2.setStatement(1, PAGADO);
			db2.setStatement(2, id_codigo_barra);
			resultado = db2.executeUpdate();
			if (resultado==0){
				db2.prepareQuery(queryUpdateNominaDC);
				db2.setStatement(1, PAGADO);
				db2.setStatement(2, id_codigo_barra);
				resultado = db2.executeUpdate();
				if (resultado==0){
					db2.prepareQuery(queryUpdateNominaRA);
					db2.setStatement(1, PAGADO);
					db2.setStatement(2, id_codigo_barra);
					resultado = db2.executeUpdate();
				}
			}
		}
	}
	return resultado;
	
}

public int update(Object obj) throws SQLException {
	// TODO Apéndice de método generado automáticamente
	return 0;
}

}

