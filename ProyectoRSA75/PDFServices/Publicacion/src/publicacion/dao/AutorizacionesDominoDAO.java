/*
 * Creado el 13-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package publicacion.dao;

import java.sql.SQLException;
import java.util.logging.Logger;

import publicacion.AtributosUsuarioTO;
import publicacion.dao.DAO_Interface;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.ConectaDB2;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class AutorizacionesDominoDAO implements DAO_Interface{
	private ConectaDB2 db2;
	private static Logger logger = LogManager.getLogger();
	
	public AutorizacionesDominoDAO(ConectaDB2 db2){
		try {
			System.out.println("Verificando conexión:" + !db2.isClosed());
			this.db2= db2;
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	public Object selectEmpresas(String nombre) throws SQLException{
		StringBuffer query= new StringBuffer();
//		aqui se estan almacenando los datos de la consulta en el SQL en la variable sql
		//query.append("SELECT trim(lisempre), trim(tipadmin), trim(codholdi), trim(usuario), trim(inicotiz) ");
		query.append("SELECT trim(lisempre), trim(lisconve), trim(lissucur), trim(tipadmin), trim(tipentid), trim(codholdi), trim(usuario), trim(inicotiz) ");
		query.append("FROM pwdtad.pwfseusu ");
		query.append("WHERE trim(docid) = ? ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, nombre);
		db2.executeQuery();
		AtributosUsuarioTO atributos=null;
		if (db2.next()){
			atributos= new AtributosUsuarioTO();
			atributos.setListaEmpresasDomino(db2.getString(1));
			atributos.setListaConveniosDomino(db2.getString(2));
			atributos.setListaSucursalesDomino(db2.getString(3));
			atributos.setTipoUsuario(db2.getString(4));//{empresa, entidad}
			atributos.setTipoEntidad(db2.getString(5));//{Todas, AFP, ISAPRE, APV...}
			atributos.setHolding(db2.getString(6));
			atributos.setUsuario(db2.getString(7));
			atributos.setIniCotiza(db2.getString(8));
			
		}
		return atributos;
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
