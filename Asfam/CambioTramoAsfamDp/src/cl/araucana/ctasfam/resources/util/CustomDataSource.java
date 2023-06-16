/*
* @(#) CustomDataSource.java 1.1 29/10/2010
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/

package cl.araucana.ctasfam.resources.util;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;



import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.servlet.http.HttpSession;

/**
 * @author Administrador
 *
 */
public class CustomDataSource {
    static Connection conn = null;
    CallableStatement cs = null;
    /**
     * 
     */
    public static Properties cfg = null;
    /**
     * 
     */
    public String JNDI_ORACLE = "FRAMEWORK_JDNI";
    // Setear conexión
    /**
     * @param newConn
     */
    public void setConnection(Connection newConn) {
        conn = newConn;
    }
    // Recuperar conexión
    /**
     * @return conexion
     */
    public Connection getConnection() {
        return conn;
    }
    // Abrir conexión por defecto oracle
    /**
     * 
     */
    public void openConnection() {
        openConnection(JNDI_ORACLE);
    }
    /**
     * @param jndi
     */
    // Abrir conexión
    public void openConnection(String jndi) {
    	if(conn==null){
	       // System.out.println("jndi : " + jndi);
	       // System.out.println("abro la conexion");
	        try {
	        	if(cfg==null){
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
	            ds = (DataSource)ic.lookup(cfg.getProperty(jndi));
	            conn = ds.getConnection();
	        } catch (SQLException ex) {
	            System.out.println("CustomDataSource.openConnection: SQLException(" + ex + ")");
	            conn = null;
	        } catch (Exception ex) {
	            System.out.println("CustomDataSource.openConnection: Exception(" + ex + ")");
	            conn = null;
	        }
	       // System.out.println("abri la conexion");
    	} else {
    		//System.out.println("la conexion ya estaba abierta");
    	}
    }

    // Verificar conexión (rescata conexión de session)
    /**
     * @param session
     * @return verifica conexion
     */
    public boolean isConnect(HttpSession session) {
        conn = (Connection)session.getAttribute("connDataBase");
        if (conn == null)
            return false;
        else
            return true;
    }
    
    /**
     * se cierra conexion
     */
    // Cerrar conexión
    public void closeConnection() {
        try {
            //conn.close();
        } catch (Exception ex) {
            System.out.println("CustomDataSource.closeConnection: Exception(" + ex + ")");
        }
    }

    
}