/*
* @(#) CustomDataSource.java 1.1 29/10/2010
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/

package cl.araucana.tupla2.struts.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * @author Administrador
 *
 */
public class CustomDataSource {
	static Connection conn = null;
	//	CallableStatement cs = null;

	public static Properties cfg = null;

	public String JNDI_ORACLE = "FRAMEWORK_JDNI";

	// Setear conexi�n
	/**
	 * @param newConn
	 */
	public void setConnection(Connection newConn) {
		conn = newConn;
	}

	// Recuperar conexi�n
	/**
	 * @return conexion
	 */
	public Connection getConnection() {
		try {
			openConnection();
		} catch (SQLException e) {
			// TODO Bloque catch generado autom�ticamente
			e.printStackTrace();
		}
		return conn;
	}

	// Abrir conexi�n por defecto oracle
	/**
	 * @throws SQLException 
	 */
	public void openConnection() throws SQLException {
		openConnection(JNDI_ORACLE);
	}

	// Abrir conexi�n
	/**
	 * @param jndi
	 * @throws SQLException 
	 */
	public void openConnection(String jndi) throws SQLException {

		if (conn == null || conn.isClosed()) {
			// System.out.println("jndi : " + jndi);
			// System.out.println("abro la conexion");
			try {
				if (cfg == null) {
					cfg = new Properties();
					cfg.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("conectaDB2.cfg"));
				}
				InitialContext ic = null;
				DataSource ds = null;
				if (jndi.equalsIgnoreCase(JNDI_ORACLE)) {
					ic = new InitialContext();
				}
				/*
				if (jndi.equalsIgnoreCase(JNDI_SQLSERVER)) {
				    Hashtable env = new Hashtable();
				    env.put(Context.INITIAL_CONTEXT_FACTORY,"com.ibm.websphere.naming.WsnInitialContextFactory");
				    ic = new InitialContext(env);
				}
				*/
				ds = (DataSource) ic.lookup(cfg.getProperty(jndi));
				conn = ds.getConnection();
			} catch (SQLException ex) {
				System.out.println("CustomDataSource.openConnection: SQLException(" + ex + ")");
				conn = null;
			} catch (Exception ex) {
				System.out.println("CustomDataSource.openConnection: Exception(" + ex + ")");
				conn = null;
			}
			System.out.println("abri la conexion");
		} else {
			//System.out.println("la conexion ya estaba abierta");
		}
	}

	/*	
	// Verificar conexi�n (rescata conexi�n de session)
	public boolean isConnect(HttpSession session) {
	conn = (Connection) session.getAttribute("connDataBase");
	if (conn == null)
		return false;
	else
		return true;
	}*/

	/**
	 * se cierra conexion
	 */
	// Cerrar conexi�n
	public void closeConnection() {
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (Exception ex) {
			System.out.println("CustomDataSource.closeConnection: Exception(" + ex + ")");
		}
	}

	/**
	 * @param nomFuncion
	 * @param listaParametros
	 * @return objeto con resultados de la funcion
	 */
	// Ejecutar funci�n

}