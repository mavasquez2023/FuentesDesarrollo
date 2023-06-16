/*
 * Creado el 06-04-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.dao;

import java.sql.SQLException;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.ConectaDB2;

/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ParametrosCPDAO implements DAO_Interface{
	private ConectaDB2 db2;
	private static Logger logger = LogManager.getLogger();
	
	public ParametrosCPDAO(ConectaDB2 db2){
		this.db2= db2;
	}
	
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk) throws SQLException {
		ParametrosCPTO param= new ParametrosCPTO();
		//rescatando periodo actual
		String query= "select VALOR from PARAMETRO WHERE ID_PARAMETRO=1";
		logger.finest("Query:" + query);
		db2.executeQuery(query);
		if (db2.next()){
			String periodo= db2.getString(1);
			param.setPeriodoSistema(Integer.parseInt(periodo));
		}
		//rescatando periodo independiente
		query= "select VALOR from PARAMETRO WHERE ID_PARAMETRO=138";
		logger.finest("Query:" + query);
		db2.executeQuery(query);
		if (db2.next()){
			String periodo= db2.getString(1);
			param.setPeriodoSistemaIndependiente(Integer.parseInt(periodo));
		}
		//rescatando fecha cierre
		query= "select substr(VALOR, 1, 10) from PARAMETRO WHERE ID_PARAMETRO=59";
		logger.finest("Query:" + query);
		db2.executeQuery(query);
		if (db2.next()){
			String fecha= db2.getString(1);
			param.setFechaCierre(new AbsoluteDate(fecha));
		}
		//rescatando fecha cierre
		query= "select substr(VALOR, 1, 10) from PARAMETRO WHERE ID_PARAMETRO=131";
		logger.finest("Query:" + query);
		db2.executeQuery(query);
		if (db2.next()){
			String fecha= db2.getString(1);
			param.setFechaCierreIndependiente(new AbsoluteDate(fecha));
		}
		//rescatando email Usuarios
		query= "select VALOR from PARAMETRO WHERE ID_PARAMETRO=129";
		logger.finest("Query:" + query);
		db2.executeQuery(query);
		if (db2.next()){
			String emailUsuario= db2.getString(1);
			param.setEmailUsuario(emailUsuario);
		}
		//rescatando codigoBarra
		query= "select VALOR from PARAMETRO WHERE ID_PARAMETRO=37";
		logger.finest("Query:" + query);
		db2.executeQuery(query);
		if (db2.next()){
			String codigoBarra= db2.getString(1);
			param.setCodigoBarra(codigoBarra.substring(0, 2));
		}
		//rescatando url PDFServices
		query= "select VALOR from PARAMETRO WHERE ID_PARAMETRO=130";
		logger.finest("Query:" + query);
		db2.executeQuery(query);
		if (db2.next()){
			String url= db2.getString(1);
			param.setUrlPDFServices(url);
		}
		//clillo 07-08-2017
		//rescatando fecha apertura
		query= "select VALOR from PARAMETRO WHERE ID_PARAMETRO=159";
		logger.finest("Query:" + query);
		db2.executeQuery(query);
		if (db2.next()){
			String fecha= db2.getString(1);
			param.setFechaApertura(new AbsoluteDate(fecha));
		}
		return param;
	}
	
	public boolean isAdmin(int rut) throws SQLException{
		boolean resultado=false;
		String query= "select count(*) from adminempre where id_admin=? and habilitado= 1";
		logger.finest("Query:" + query);
		db2.prepareQuery(query);
		db2.setStatement(1, rut);
		db2.executeQuery();
		if (db2.next()){
			int exists= db2.getInt(1);
			if (exists==1){
				resultado=true;
			}
		}
		return resultado;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public int insert(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int update(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void delete(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		
	}
}
