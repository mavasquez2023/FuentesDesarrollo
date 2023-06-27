package cl.araucana.contabilidad.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;

import com.schema.util.FileSettings;
import com.schema.util.dao.DB2Utils;

/**
 * @author asepulveda
 *
 */
public class DummyFolioDAO implements FolioDAO {
	Logger logger = Logger.getLogger(DummyFolioDAO.class);
	private final static String PREFIX="DB2-";
	
	private String folioDatabase;
	private String folioJNDIDataSource;
	
	/**
	 * Constructor de DAO
	 * Recupera nombre de Bases de Datos utilizadas
	 */
	public DummyFolioDAO(){
		folioDatabase=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
			 "/application-settings/databases/folios-contabilidad");
		folioJNDIDataSource=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
			 "/application-settings/jdbc/folios-contabilidad");
	}
	
	/**
	 * Genera una número de folio para el asiento contable
	 * @return folio
	 * @throws Exception
	 * @throws BusinessException
	 */
	public long getFolio() throws Exception, BusinessException {
		
		long idDisponibleActual = 0;
		int intentos = 0;
		
		do {
			intentos++;
			idDisponibleActual = getIDDisponible();
			if (idDisponibleActual > 0) {
				if (updateIDDisponible(idDisponibleActual + 1) == 0) {
					idDisponibleActual = 0;
				}
			}
		} while (idDisponibleActual == 0 && intentos < 10);
		logger.debug("idDisponibleActual: " + idDisponibleActual + " Intentos: " + intentos);
		return idDisponibleActual;
	}
	
	/** 
	 * Obtiene un número de folio disponible
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public long getIDDisponible() throws Exception, BusinessException {
	Connection conn = null;
	CallableStatement stmt = null;
	ResultSet ors = null;
	long idDisponible = 0;
		
	String command;
		
	command = "SELECT CM64A FROM "+folioDatabase+".CM11F1 " +
	"WHERE SAJKKXL = 2";
								
	try {
	  conn = DB2Utils.createConnection(folioJNDIDataSource);
	  stmt = conn.prepareCall(command);
	  
	  logger.debug("Inicia operación: " + command);
	  ors = stmt.executeQuery();
	  logger.debug("Finaliza operación: " + command);
	
	  if (ors.next()) {
		idDisponible = ors.getLong("CM64A");
	  }
	  
	} catch (SQLException ex) {
		ex.printStackTrace();
		int code=ex.getErrorCode();
		throw new BusinessException(PREFIX+code);
	} finally {
	  DB2Utils.closeAll(conn, stmt, ors);
	}
		return idDisponible;		
	}
	
	/** 
	 * Actualiza el ID disponible de la tabla
	 * Es para mantener un valor que sirva como identity
	 * @param nuevo valor disponible
	 * @return ID disponible
	 * @throws Exception
	 */
	public int updateIDDisponible(long nuevoValorDisponible) throws Exception,BusinessException {

	 if (nuevoValorDisponible == 0)
		throw new BusinessException("CCAF_CONTA_FOLIOINVALIDO",
								   "El nuevo valor del Folio es incorrecto");

	  Connection conn = null;
	  CallableStatement stmt = null;
	  ResultSet ors = null;
	  int filasActualizadas = 0;
	  
	  String command;
	  
	  command = "UPDATE "+folioDatabase+".CM11F1 SET CM64A = ? " +
	  "WHERE SAJKKXL = 2 AND CM64A = ?";
	
	  try {
		conn = DB2Utils.createConnection(folioJNDIDataSource);
		stmt = conn.prepareCall(command);
		stmt.setDouble(1,nuevoValorDisponible);
		stmt.setDouble(2,nuevoValorDisponible - 1);
		
		logger.debug("Inicia operación: " + command);
		filasActualizadas = stmt.executeUpdate();
		logger.debug("Finaliza operación: " + command);
		
	  } catch (SQLException ex) {
			ex.printStackTrace();
			int code=ex.getErrorCode();
			throw new BusinessException(PREFIX+code);
	  } finally {
		DB2Utils.closeAll(conn, stmt, ors);
	  }
	  return filasActualizadas;
	}
	
}
