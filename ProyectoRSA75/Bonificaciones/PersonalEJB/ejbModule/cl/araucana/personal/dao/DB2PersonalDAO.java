package cl.araucana.personal.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.araucana.personal.vo.CargaVO;
import cl.araucana.personal.vo.EmpleadoVO;

import com.schema.util.FileSettings;
import com.schema.util.dao.DB2Utils;
/**
 * @author asepulveda
 *
 */
public class DB2PersonalDAO implements PersonalDAO {
	Logger logger = Logger.getLogger(DB2PersonalDAO.class);
	private final static String PREFIX="DB2-";
	
	private String personalDatabase;
	private String benefDatabase;
	private String personalJNDIDataSource;
	private String benefJNDIDataSource;
	
	/**
	 * Constructor de DAO
	 * Recupera nombre de Bases de Datos utilizadas
	 */
	public DB2PersonalDAO(){
		personalDatabase=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
		     "/application-settings/databases/personal");
		benefDatabase=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
			 "/application-settings/databases/beneficios");
		personalJNDIDataSource=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
			 "/application-settings/jdbc/personal");
		benefJNDIDataSource=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
			 "/application-settings/jdbc/beneficios");
	}
		
	/**
	 * Obtiene la lista de Empleados Activos de La Araucana desde el Sistema de Personal
	 * Permite filtar por Rut y Nombre del Socio
	 * @param String rut, String nombre
	 * @return EmpleadoVO
	 */
	public ArrayList getListaEmpleados(String rut, String nombre) throws Exception,BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		boolean filtarPorRut= false;
		boolean filtarPorNombre= false;
		int contador = 0;
		
		//Vigentes y tipo contrato distinto a reemplazo
		String command = "SELECT FNCODFUN,FNNOMBRES, FNAPELLPAT, FNAPELLMAT, FNESTCIVIL, FNFECHING, FNFECHNAC, "+
				"FNFECHEGR,	FNSEXO, FNCODCARG, FNESTADO, FNFEC1, FNTXA2, FNTXA1, FNABV1, FNCOD1, FNCOD10, FNLPAGO " + 
				"FROM "+personalDatabase+".APLFN01 WHERE FNESTADO = 'V' AND FNTIPOFUN <> 'REE' ";
			
		logger.debug("Parametros Personal: RUT: " + rut + " ,Nombre: " + nombre);
		
		//verifico si vienen filtros
				
		if (rut != null && (rut.trim()).length() > 0){
			command = command + " AND FNCODFUN LIKE ?";
			filtarPorRut= true;
		}
		if (nombre != null && (nombre.trim()).length() > 0){
			command = command + " AND (UCASE(FNNOMBRES) LIKE ? OR UCASE(FNAPELLPAT) like ? OR UCASE(FNAPELLMAT) like ?)";
			filtarPorNombre= true;
		}
	
		ArrayList retorno = new ArrayList();

		try {
		  conn = DB2Utils.createConnection(personalJNDIDataSource);
		  stmt = conn.prepareCall(command);
		  if (filtarPorRut){
			contador++;
			stmt.setString(contador,'%' + rut + '%');
		  }
		  if (filtarPorNombre) {
			contador++;
			stmt.setString(contador,'%' + nombre.toUpperCase()+ '%');
			contador++;
			stmt.setString(contador,'%' + nombre.toUpperCase()+ '%');
			contador++;
			stmt.setString(contador,'%' + nombre.toUpperCase()+ '%');
		  }  
		  		  
		  logger.debug("Inicia operación: " + command);
		  ors = stmt.executeQuery();
		  logger.debug("Finaliza operación: " + command);
		  
		  while (ors.next()) {
			EmpleadoVO vo = new EmpleadoVO();
			vo.setRut(ors.getString("FNCODFUN").trim());
			vo.setNombre(ors.getString("FNNOMBRES").trim());
			vo.setApePaterno(ors.getString("FNAPELLPAT").trim());
			vo.setApeMaterno(ors.getString("FNAPELLMAT").trim());
			vo.setEstCivil(ors.getString("FNESTCIVIL").trim());
			vo.setFecIngreso(ors.getDate("FNFECHING"));
			vo.setFecNacimiento(ors.getDate("FNFECHNAC"));
			vo.setFecEgreso(ors.getDate("FNFECHEGR"));
			vo.setSexo(ors.getString("FNSEXO"));
			vo.setCodCargo(ors.getString("FNCODCARG"));
			vo.setCodOficina(ors.getString("FNLPAGO"));
			vo.setFecInicioBeneficio(ors.getDate("FNFEC1"));
			vo.setDomicilioParticular(ors.getString("FNTXA2"));
			vo.setCodCiudad(ors.getString("FNTXA1"));
			vo.setCodComuna(ors.getString("FNABV1"));
			vo.setFonoParticular(ors.getString("FNCOD1"));
			vo.setFonoEmergencia(ors.getString("FNCOD10"));			
			vo.setEstado(ors.getString("FNESTADO"));
			retorno.add(vo);
		  }

		} catch (SQLException ex) {
			int code=ex.getErrorCode();
			throw new BusinessException(PREFIX+code);
		} finally {
		  DB2Utils.closeAll(conn, stmt, ors);
		}
		return retorno;		
	}

	 /**
	 * Obtiene un Empleado de La Araucana desde el Sistema de Personal
	 * @param String rut, Rut del Empleado
	 * @return EmpleadoVO 
	 */
	public EmpleadoVO getEmpleado(String rut) throws Exception,BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
			
		String command = "SELECT FNCODFUN, FNNOMBRES, FNAPELLPAT, FNAPELLMAT, FNESTCIVIL, " +
		"FNFECHING, FNFECHNAC, FNFECHEGR, FNSEXO, FNCODCARG, FNFEC1, FNTXA2, FNTXA1, FNABV1, " +
		"FNCOD1, FNCOD10, FNESTADO, FNLPAGO FROM " + personalDatabase + ".APLFN01 WHERE FNCODFUN = ? "+
		"AND FNESTADO = 'V'";
			
		logger.debug("Parametros Personal: RUT: " + rut);		
	
		EmpleadoVO empleado = new EmpleadoVO();

		try {
			conn = DB2Utils.createConnection(personalJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1,rut);
		  		  
			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);
		  
		  if (ors.next()) {
			empleado.setRut(ors.getString("FNCODFUN").trim());
			empleado.setNombre(ors.getString("FNNOMBRES").trim());
			empleado.setApePaterno(ors.getString("FNAPELLPAT").trim());
			empleado.setApeMaterno(ors.getString("FNAPELLMAT").trim());
			empleado.setEstCivil(ors.getString("FNESTCIVIL").trim());
			empleado.setFecIngreso(ors.getDate("FNFECHING"));
			empleado.setFecNacimiento(ors.getDate("FNFECHNAC"));
			empleado.setFecIngreso(ors.getDate("FNFECHEGR"));
			empleado.setSexo(ors.getString("FNSEXO"));
			empleado.setCodCargo(ors.getString("FNCODCARG"));
			empleado.setCodOficina(ors.getString("FNLPAGO"));
			empleado.setFecInicioBeneficio(ors.getDate("FNFEC1"));
			empleado.setDomicilioParticular(ors.getString("FNTXA2"));
			empleado.setCodCiudad(ors.getString("FNTXA1"));
			empleado.setCodComuna(ors.getString("FNABV1"));
			empleado.setFonoParticular(ors.getString("FNCOD1"));
			empleado.setFonoEmergencia(ors.getString("FNCOD10"));
			empleado.setEstado(ors.getString("FNESTADO"));
		  }

		} catch (SQLException ex) {
			int code=ex.getErrorCode();
			throw new BusinessException(PREFIX+code);
		} finally {
		  DB2Utils.closeAll(conn, stmt, ors);
		}

		return empleado;		
	} 


	/**
	 * Obtiene una Carga Familiar desde el Sistema de Beneficios
	 * @param long rut, rut de carga familiar
	 * @return CargaVO
	 */
	public CargaVO getCargaFamiliar(long rut) throws Exception,BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
				
		String command = "SELECT AF8FA, AF8EA, AF8IA, AF8LA, AF8RA, AF8JA, AF8GA, AF8DA,"+
			"AF8HA, AF8MA, AF8KA, SE5FAJC, AF8OA FROM " + benefDatabase + ".AF05F1 " +
			"WHERE AF8HA = ?";
			
		logger.debug("Parametros Carga: RUT: " + rut);
		
		CargaVO carga = new CargaVO();

		try {
			conn = DB2Utils.createConnection(benefJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1,rut);
	  
			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);
		 
		  if (ors.next()) {
			carga.setCargaApeMat(ors.getString("AF8FA").trim());
			carga.setCargaApePat(ors.getString("AF8EA").trim());
			carga.setCargaDv(ors.getString("AF8IA").charAt(0));
			carga.setCargaEstado(ors.getString("AF8LA").charAt(0));
			carga.setCargaFecFin(ors.getDate("AF8RA"));
			carga.setCargaFecNac(ors.getDate("AF8JA"));
			carga.setCargaNombre(ors.getString("AF8GA").trim());
			carga.setCargaNum(ors.getInt("AF8DA"));
			carga.setCargaRut(ors.getLong("AF8HA"));
			carga.setCargaSexo(ors.getString("AF8MA").charAt(0));
			carga.setCargaTipo(ors.getString("AF8KA").charAt(0));
			carga.setSocioRut(ors.getLong("SE5FAJC"));
			carga.setCargaFecIniBeneficios((ors.getDate("AF8OA")));
		  }

		} catch (SQLException ex) {
			int code=ex.getErrorCode();
			throw new BusinessException(PREFIX+code);
		} finally {
		  DB2Utils.closeAll(conn, stmt, ors);
		}

		return carga;		
	}
	
	/**
	 * Obtiene las Cargas Familiares de un Empleado desde el Sistema de Beneficios
	 * @param long rut, rut de Empleado
	 * @return ArrayList de CargaVO
	 */
	public ArrayList getListaCargasFamiliaresEmpleado(long rut) throws Exception,BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
				
		String command = "SELECT AF8FA, AF8EA, AF8IA, AF8LA, AF8RA, AF8JA, AF8GA, AF8DA,"+
			"AF8HA, AF8MA, AF8KA, SE5FAJC, AF8OA FROM "+benefDatabase+".AF05F1 WHERE "+
			"SE5FAJC = ?";
			
		logger.debug("Parametros Carga: RUT: " + rut);
	
		ArrayList retorno = new ArrayList();

		try {
			conn = DB2Utils.createConnection(benefJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setLong(1,rut);
		  		  
			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);
		  
		  while (ors.next()) {
			CargaVO cargas = new CargaVO();
			cargas.setCargaApeMat(ors.getString("AF8FA").trim());
			cargas.setCargaApePat(ors.getString("AF8EA").trim());
			cargas.setCargaDv(ors.getString("AF8IA").charAt(0));
			cargas.setCargaEstado(ors.getString("AF8LA").charAt(0));
			cargas.setCargaFecFin(ors.getDate("AF8RA"));
			cargas.setCargaFecNac(ors.getDate("AF8JA"));
			cargas.setCargaNombre(ors.getString("AF8GA").trim());
			cargas.setCargaNum(ors.getInt("AF8DA"));
			cargas.setCargaRut(ors.getLong("AF8HA"));
			cargas.setCargaSexo(ors.getString("AF8MA").charAt(0));
			cargas.setCargaTipo(ors.getString("AF8KA").charAt(0));
			cargas.setSocioRut(ors.getLong("SE5FAJC"));
			cargas.setCargaFecIniBeneficios((ors.getDate("AF8OA")));
			retorno.add(cargas);
		  }

		} catch (SQLException ex) {
			int code=ex.getErrorCode();
			throw new BusinessException(PREFIX+code);
		} finally {
		  DB2Utils.closeAll(conn, stmt, ors);
		}

		return retorno;		
	}
	
	/**
	 * Obtiene la glosa de una Oficina
	 * @param String codigoOficina, lugar de pago (cod Oficina)
	 * @return  String
	 */
	public String getOficinaEmpleado(String codigoOficina) throws Exception,BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String oficina = null;

		String command = "SELECT TABDEDEAMP FROM "+personalDatabase+".SYSTE01 "+
		"WHERE TABDECOTAB = 15 AND TABDECODEMP = 'LRA' AND TABDEVALUEA = ?";
		
		try {
			conn = DB2Utils.createConnection(benefJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1,codigoOficina);
	  
			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);
		 
		  if (ors.next()) {
			oficina = ors.getString("TABDEDEAMP").trim();
		  }

		} catch (SQLException ex) {
			int code=ex.getErrorCode();
			throw new BusinessException(PREFIX+code);
		} finally {
		  DB2Utils.closeAll(conn, stmt, ors);
		}

		return oficina;		
	}

	/**
	 * Obtiene la glosa de un Departamento
	 * @param String codigoCargo, codigo de Cargo de un funcionario
	 * @return  String
	 */
	public String getDeptoEmpleadoByCodigoCargo(String codigoCargo) throws Exception,BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String oficina = null;
		
		String command = "SELECT  A.TABDEDEAMP " +
				"FROM " + personalDatabase + ".SYSTE01 A, " +
					 personalDatabase + ".APLCG01 B " +
					 	"WHERE   A.TABDECOTAB = 15 " +
					 	"AND     A.TABDECODEMP = 'LRA' " +
					 	"AND     A.TABDEVALUEA = B.CGCOD3 " +
					 	"AND     B.CGCODCARG = ?";
			
		try {
			conn = DB2Utils.createConnection(benefJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1,codigoCargo);
	  
			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);
		 
		  if (ors.next()) {
			oficina = ors.getString("TABDEDEAMP").trim();
		  }

		} catch (SQLException ex) {
			int code=ex.getErrorCode();
			throw new BusinessException(PREFIX+code);
		} finally {
		  DB2Utils.closeAll(conn, stmt, ors);
		}

		return oficina;		
	}
	
	/**
	 * Obtiene la glosa de un Departamento
	 * @param String codigoDepartamento, codigo de departamento
	 * @return  String
	 */
	public String getDeptoEmpleadoByCodigoDepto(String codigoDepartamento) throws Exception,BusinessException {
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet ors = null;
		String oficina = null;
		
		String command = "SELECT  TABDEDEAMP " +
				"FROM " + personalDatabase + ".SYSTE01 " +
						"WHERE   TABDECOTAB = 15 " +
						"AND     TABDECODEMP = 'LRA' " +
						"AND     TABDEVALUEA = ? ";
				
		try {
			conn = DB2Utils.createConnection(benefJNDIDataSource);
			stmt = conn.prepareCall(command);
			stmt.setString(1,codigoDepartamento);
	  
			logger.debug("Inicia operación: " + command);
			ors = stmt.executeQuery();
			logger.debug("Finaliza operación: " + command);
		 
		  if (ors.next()) {
			oficina = ors.getString("TABDEDEAMP").trim();
		  }

		} catch (SQLException ex) {
			int code=ex.getErrorCode();
			throw new BusinessException(PREFIX+code);
		} finally {
		  DB2Utils.closeAll(conn, stmt, ors);
		}

		return oficina;		
	}	

	/**
	 * Traduce codigo de Ciudad
	 * @param String codCiudad
	 * @return String con nombre de Ciudad
	 * @throws Exception
	 * @throws BusinessException
	 */
   public String getCiudad(String codCiudad) throws Exception,BusinessException {
	   Connection conn = null;
	   CallableStatement stmt = null;
	   ResultSet ors = null;
			
	   String command = "SELECT TABDTDEAMP FROM " + personalDatabase + ".SYSTB01 " +
		"WHERE TABDTCOTAB = 3 AND TABDTVALUEA = ?";		

		String ciudad = null;
		
	   try {
		   conn = DB2Utils.createConnection(personalJNDIDataSource);
		   stmt = conn.prepareCall(command);
		   stmt.setString(1,codCiudad);
		  		  
		   logger.debug("Inicia operación: " + command);
		   ors = stmt.executeQuery();
		   logger.debug("Finaliza operación: " + command);
		  
		 if (ors.next()) {
			ciudad = ors.getString("TABDTDEAMP");
		 }

	   } catch (SQLException ex) {
		   int code=ex.getErrorCode();
		   throw new BusinessException(PREFIX+code);
	   } finally {
		 DB2Utils.closeAll(conn, stmt, ors);
	   }

	   return ciudad;		
   }
   
   /**
	* Traduce codigo de Comuna
	* @param String codComuna
	* @return String con nombre de Comuna
	* @throws Exception
	* @throws BusinessException
	*/
  public String getComuna(String codComuna) throws Exception,BusinessException {
	  Connection conn = null;
	  CallableStatement stmt = null;
	  ResultSet ors = null;
			
	  String command = "SELECT TABDTDEAMP FROM " + personalDatabase + ".SYSTB01 " +
	   "WHERE TABDTCOTAB = 4 AND TABDTVALUEA = ?";		

	   String comuna = null;
		
	  try {
		  conn = DB2Utils.createConnection(personalJNDIDataSource);
		  stmt = conn.prepareCall(command);
		  stmt.setString(1,codComuna);
		  		  
		  logger.debug("Inicia operación: " + command);
		  ors = stmt.executeQuery();
		  logger.debug("Finaliza operación: " + command);
		  
		if (ors.next()) {
			comuna = ors.getString("TABDTDEAMP");
		}

	  } catch (SQLException ex) {
		  int code=ex.getErrorCode();
		  throw new BusinessException(PREFIX+code);
	  } finally {
		DB2Utils.closeAll(conn, stmt, ors);
	  }

	  return comuna;		
  }

}
