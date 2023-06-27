package cl.araucana.ea.edocs.bd;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionDB {
    public static ConnectionDB instancia = null;
    public static ConnectionDB getInstancia(){
    	if(instancia == null)
    		instancia = new ConnectionDB();
    	return instancia;
    }
    public ConnectionDB(){
    	cargarConfiguracionBD();
    }
	public static Connection cargarConfiguracionBD(){
		
		Context ctx = null;
		try {
			ctx = new InitialContext();
			
			DataSource dataSource =
					(DataSource) ctx.lookup("java:comp/env/DSEmpresasAdherentes");
			return dataSource.getConnection();
		} catch (NamingException e) {
			System.out.println("Errror al cargar el DataSource: "+"java:comp/env/DSEmpresasAdherentes");
		} catch (SQLException e) {
			System.out.println("Error al ejecutar sql: "+e.toString());
			e.printStackTrace();
		} finally {
			if (ctx != null) {
				try {
					ctx.close();
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static Connection obtenerConexion(){
		return cargarConfiguracionBD();
	}
}
