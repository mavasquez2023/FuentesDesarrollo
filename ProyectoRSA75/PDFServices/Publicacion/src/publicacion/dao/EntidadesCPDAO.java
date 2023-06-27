/*
 * Creado el 13-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package publicacion.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import publicacion.EntidadTO;
import publicacion.dao.DAO_Interface;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.ConectaDB2;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class EntidadesCPDAO implements DAO_Interface{
	private ConectaDB2 db2;
	private static Logger logger = LogManager.getLogger();
	
	public EntidadesCPDAO(ConectaDB2 db2){
		try {
			System.out.println("Verificando conexión:" + !db2.isClosed());
			this.db2= db2;
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	public Collection selectEntidadesAFP() throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select nombre ");
		query.append("FROM cpedta.entpagad a join cpedta.entidad_fondo_pension b ");
		query.append("ON a.id_ent_pagadora= b.id_ent_pagadora ");
		query.append("WHERE  b.id_ent_fondo_pension>0 ");
		query.append("ORDER BY nombre ");
		logger.finest("Query=" + query.toString());
		db2.executeQuery(query.toString());
		List nombres= new ArrayList();
		while (db2.next()){
			String nombreEntidad= db2.getString(1);
			nombres.add(nombreEntidad);
		}
		return nombres;
	}
	
	public int esAdmin(String rut) throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select es_admin_caja ");
		query.append("FROM cpedta.persona ");
		query.append("WHERE  id_persona = " + rut);
		logger.finest("Query=" + query.toString());
		db2.executeQuery(query.toString());
		int esAdministrador = 0;
		while (db2.next()){
			esAdministrador = db2.getInt("es_admin_caja");
		}
		return esAdministrador;
	}
	
	
	public Collection selectEntidadesISAPRE() throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select nombre ");
		query.append("FROM cpedta.entpagad a join cpedta.entidad_salud b ");
		query.append("ON a.id_ent_pagadora= b.id_ent_pagadora ");
		query.append("WHERE  b.id_ent_salud>0 ");
		query.append("ORDER BY nombre ");
		logger.finest("Query=" + query.toString());
		db2.executeQuery(query.toString());
		List nombres= new ArrayList();
		while (db2.next()){
			EntidadTO entidad= new EntidadTO();
			String nombre= db2.getString(1);
			String value=nombre;
			if(value.length()>40){
				value= value.substring(0, 40);
			}
			entidad.setNombre(nombre);
			entidad.setValue(value);
			nombres.add(entidad);
		}
		return nombres;
	}
	
	public Collection selectEntidadesAPV() throws SQLException{
		StringBuffer query= new StringBuffer();
//		aqui se estan almacenando los datos de la consulta en el SQL en la variable sql
		//query.append("SELECT trim(lisempre), trim(tipadmin), trim(codholdi), trim(usuario), trim(inicotiz) ");
		query.append("select nombre ");
		query.append("FROM cpedta.entpagad a join cpedta.entapv b ");
		query.append("ON a.id_ent_pagadora= b.id_ent_pagadora ");
		query.append("WHERE  b.id_ent_apv>0 ");
		query.append("ORDER BY nombre ");
		logger.finest("Query=" + query.toString());
		db2.executeQuery(query.toString());
		List nombres= new ArrayList();
		while (db2.next()){
			EntidadTO entidad= new EntidadTO();
			String nombre= db2.getString(1);
			String value=nombre;
			if(value.length()>40){
				value= value.substring(0, 40);
			}
			entidad.setNombre(nombre);
			entidad.setValue(value);
			nombres.add(entidad);
		}
		return nombres;
	}
	
	public Collection selectEntidadesMUTUAL() throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select nombre ");
		query.append("FROM cpedta.entpagad a join cpedta.entidad_mutual b ");
		query.append("ON a.id_ent_pagadora= b.id_ent_pagadora ");
		query.append("WHERE  b.id_mutual>0 ");
		query.append("ORDER BY nombre ");
		logger.finest("Query=" + query.toString());
		db2.executeQuery(query.toString());
		List nombres= new ArrayList();
		while (db2.next()){
			String nombreEntidad= db2.getString(1);
			nombres.add(nombreEntidad);
		}
		return nombres;
	}
	
	public Collection selectEntidadesCCAF() throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select nombre ");
		query.append("FROM cpedta.entpagad a join cpedta.entidad_ccaf b ");
		query.append("ON a.id_ent_pagadora= b.id_ent_pagadora ");
		query.append("WHERE  b.id_ccaf>0 ");
		query.append("ORDER BY nombre ");
		logger.finest("Query=" + query.toString());
		db2.executeQuery(query.toString());
		List nombres= new ArrayList();
		while (db2.next()){
			String nombreEntidad= db2.getString(1);
			nombres.add(nombreEntidad);
		}
		return nombres;
	}
	
	public String selectPeriodo() throws SQLException{
		String periodo = "";
		StringBuffer query= new StringBuffer();
		query.append("select valor ");
		query.append("FROM cpedta.parametro ");
		query.append("WHERE  id_parametro = 1" );
		logger.finest("Query=" + query.toString());
		db2.executeQuery(query.toString());
		if (db2.next()){
			periodo = db2.getString("1");
		}
		return periodo;
	}
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	public void delete(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		
	}

	public int insert(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	public int update(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

}
