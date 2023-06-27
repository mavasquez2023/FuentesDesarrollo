/*
 * Creado el 13-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package publicacion.dao;

import java.sql.SQLException;
import java.util.logging.Logger;
import publicacion.BitacoraTO;
import publicacion.dao.DAO_Interface;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.ConectaDB2;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class BitacoraPubDAO implements DAO_Interface{
	private ConectaDB2 db2;
	private static Logger logger = LogManager.getLogger();
	
	public BitacoraPubDAO(ConectaDB2 db2){
		try {
			System.out.println("BitacoraPubDAO, Verificando conexión DB2:" + !db2.isClosed());
			this.db2= db2;
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	public Object selectEmpresas(String nombre) throws SQLException{
		return null;
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
		BitacoraTO bitacora= (BitacoraTO)obj;
		StringBuffer query= new StringBuffer();
		query.append("INSERT INTO PWDTAD.PWFBITPUB ");
		query.append("(PWPERIODO, PWHOLDING, PWUSUARIO, PWIPREMOTA, PWTIPODOC, PWACCION, PWRANGO) ");
		query.append(" VALUES (?, ?, ?, ?, ?, ?, ? )");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, bitacora.getPeriodo());
		db2.setStatement(2, bitacora.getHolding());
		db2.setStatement(3, bitacora.getUsuario());
		db2.setStatement(4, bitacora.getIp());	
		db2.setStatement(5, bitacora.getTipo_documento());
		db2.setStatement(6, bitacora.getAccion());
		db2.setStatement(7, bitacora.getRango());
			
		return db2.executeUpdate();
	}

	public int update(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

}
