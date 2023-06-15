package cl.araucana.ctasfam.batch.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import cl.araucana.ctasfam.batch.common.exceptions.TechnicalException;
import cl.araucana.ctasfam.batch.common.util.ConfiguracionUtil;

import com.ibm.as400.access.AS400;

public class BasicDataSource {

	private String cnfDb2Jndi = ConfiguracionUtil.getValor("araucana.cambiotramo.jndi");
	private String cnfAs400Server = ConfiguracionUtil.getValor("BATCH_FILE_SERVER_HOST");
	private String cnfAs400User = ConfiguracionUtil.getValor("BATCH_FILE_SERVER_USER"); 
	private String cnfAs400Pass = ConfiguracionUtil.getValor("BATCH_FILE_SERVER_PASS"); 

	public Connection getDb2Connection() throws TechnicalException {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup(cnfDb2Jndi);
			Connection connection = ds.getConnection();
			return connection;
		} catch (NamingException e) {
			throw new TechnicalException("0601","Ocurrio un error al establecer la conexion con la base de datos", e);
		} catch (SQLException e) {
			throw new TechnicalException("0602","Ocurrio un error al establecer la conexion con la base de datos", e);
		} catch (Exception e) {
			throw new TechnicalException("0603","Ocurrio un error al establecer la conexion con la base de datos", e);
		}
	}
	
	public AS400 getAs400Connection() throws TechnicalException{
		try {
			AS400 connection = new AS400(cnfAs400Server, cnfAs400User, cnfAs400Pass);
			return connection;
		} catch (Exception e) {
			throw new TechnicalException("0604","Ocurrio un error al establecer la conexion con el sistema de archivos", e);
		}
	}
}
