package cl.araucana.contabilidad.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.araucana.contabilidad.model.Asiento;
import cl.araucana.contabilidad.model.Linea;

import com.schema.util.FileSettings;
import com.schema.util.dao.DB2Utils;

/**
 * @author asepulveda
 *
 */
public class DB2ContabilidadDAO implements ContabilidadDAO {
	Logger logger = Logger.getLogger(DB2ContabilidadDAO.class);
	private final static String PREFIX="DB2-";
	
	public static final int CONTABILIDAD_BIENESTAR=0;
	
	private String contabilidadBienestarDatabase;
	private String contabilidadBienestarJNDIDataSource;
	
	/**
	 * Constructor de DAO
	 * Recupera nombre de Bases de Datos utilizadas
	 */
	public DB2ContabilidadDAO(){
		contabilidadBienestarDatabase=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
			 "/application-settings/databases/contabilidad-bienestar");
		contabilidadBienestarJNDIDataSource=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
			 "/application-settings/jdbc/contabilidad-bienestar");
	}
	
	/**
	 * Crea un Asiento Contable
	 * @param asiento
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void insertAsiento(Asiento asiento, int esquemaContable) throws Exception, BusinessException {
		
	String database =null;
	String JNDIDataSource=null;
  
	switch (esquemaContable) {
	  case CONTABILIDAD_BIENESTAR:
		  database = contabilidadBienestarDatabase;
		  JNDIDataSource = contabilidadBienestarJNDIDataSource;
		  break;
	  default:
		  throw new BusinessException("CCAF_CONTA_SISTEMAINVALIDO",
					 "Debe indicar un Esquema de Contabilidad Válido");
	}
		
	  if (asiento == null) {
		throw new BusinessException("CCAF_CONTA_ASIENTOINVALIDO",
								   "Se ha intentado crear un Asiento Nulo");
	  }
	
	  Connection conn = null;
	  CallableStatement stmt = null;
	  ResultSet ors = null;
	  
	  String command = "INSERT INTO "+database+".GBH (BHID,BHPGM,"+
	  "BHSEQ,BHLDES,BHUS01,BHUS02,BHUS03,BHUS04,BHUS05,BHUS06,BHAMT1,"+
	  "BHAMT2,BHAMT3,BHAMT4,BHAMT5,BHAMT6,BHDRAT,BHCRAT,BHTOLN,BHTOST,"+
	  "BHCURR,BHRATE,BHORTE,BHEVNT,BHSTAT,BHJDAT,BHUDT1,BHUDT2,BHREAS,"+
	  "BHUSER,BHDATE,BHTIME,BHLOCK,BHCO,BHBCUR,BHRTYP,BHODAT,BHUS07,"+
	  "BHUS08,BHUS09,BHUS10,BHUS11,BHUS12,BHUS13,BHUS14) VALUES (?,?,"+
	  "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"+
	  "?,?,?,?,?,?,?,?,?,?,?,?)";
		
	  try {
		conn = DB2Utils.createConnection(JNDIDataSource);
		
		stmt = conn.prepareCall(command);
		stmt.setString(1, asiento.getBhId());
		stmt.setString(2, asiento.getBhPgm());
		stmt.setDouble(3, asiento.getBhSeq());
		stmt.setString(4, asiento.getBhLdes());
		stmt.setString(5, asiento.getBhUs01());
		stmt.setString(6, asiento.getBhUs02());
		stmt.setString(7, asiento.getBhUs03());
		stmt.setString(8, asiento.getBhUs04());
		stmt.setString(9, asiento.getBhUs05());
		stmt.setString(10, asiento.getBhUs06());
		stmt.setDouble(11, asiento.getBhAmt1());
		stmt.setDouble(12, asiento.getBhAmt2());
		stmt.setDouble(13, asiento.getBhAmt3());
		stmt.setDouble(14, asiento.getBhAmt4());
		stmt.setDouble(15, asiento.getBhAmt5());
		stmt.setDouble(16, asiento.getBhAmt6());
		stmt.setDouble(17, asiento.getBhDrat());
		stmt.setDouble(18, asiento.getBhCrat());
		stmt.setDouble(19, asiento.getBhToln());
		stmt.setDouble(20, asiento.getBhTost());
		stmt.setString(21, asiento.getBhCurr());
		stmt.setDouble(22, asiento.getBhRate());
		stmt.setDouble(23, asiento.getBhOrte());
		stmt.setString(24, asiento.getBhEvnt());
		stmt.setDouble(25, asiento.getBhStat());
		stmt.setString(26, asiento.getBhJdat());
		stmt.setDouble(27, asiento.getBhUdt1());
		stmt.setDouble(28, asiento.getBhUdt2());
		stmt.setString(29, asiento.getBhReas());
		stmt.setString(30, asiento.getBhUser());
		stmt.setDouble(31, asiento.getBhDate());
		stmt.setDouble(32, asiento.getBhTime());
		stmt.setDouble(33, asiento.getBhLock());
		stmt.setDouble(34, asiento.getBhCo());
		stmt.setString(35, asiento.getBhBcur());
		stmt.setString(36, asiento.getBhRtyp());
		stmt.setDouble(37, asiento.getBhOdat());
		stmt.setString(38, asiento.getBhUs07());
		stmt.setString(39, asiento.getBhUs08());
		stmt.setString(40, asiento.getBhUs09());
		stmt.setString(41, asiento.getBhUs10());
		stmt.setString(42, asiento.getBhUs11());
		stmt.setString(43, asiento.getBhUs12());
		stmt.setString(44, asiento.getBhUs13());
		stmt.setString(45, asiento.getBhUs14());
			
		logger.debug("Inicia operación: " + command);
		stmt.execute();
		logger.debug("Finaliza operación: " + command);
	  } catch (SQLException ex) {
		ex.printStackTrace();
		int code=ex.getErrorCode();
		throw new BusinessException(PREFIX+code);
	  } finally {
		DB2Utils.closeAll(conn, stmt, ors);
	  }
	}

	/**
	 * Crea un Detalle para un Asiento Contable
	 * @param linea
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void insertLinea(Linea linea, int esquemaContable) throws Exception, BusinessException {
		
		String database =null;
		String JNDIDataSource=null;
  
		switch (esquemaContable) {
		  case CONTABILIDAD_BIENESTAR:
			  database = contabilidadBienestarDatabase;
			  JNDIDataSource = contabilidadBienestarJNDIDataSource;
			  break;
		  default:
			  throw new BusinessException("CCAF_CONTA_SISTEMAINVALIDO",
						 "Debe indicar un Esquema de Contabilidad Válido");
		}
		
	  if (linea == null) {
		throw new BusinessException("CCAF_CONTA_LINEAINVALIDO",
								   "Se ha intentado crear una Línea Nula");
	  }

	  Connection conn = null;
	  CallableStatement stmt = null;
	  ResultSet ors = null;
	  
	  String command = "INSERT INTO "+database+".GBL (BLID,BLPGM,BLSEQ,"+
	  "BLLNUM,BLUS01,BLUS02,BLUS03,BLUS04,BLUS05,BLUS06,BLUS07,BLUS08,"+
	  "BLUS09,BLUS10,BLUS11,BLUS12,BLUS13,BLUS14,BLAF01,BLAF02,BLAF03,"+
	  "BLAF04,BLAF05,BLAF06,BLAF07,BLAF08,BLAF09,BLAF10,BLDRAT,BLCRAT,"+
	  "BLLTYP,BLSTT1,BLSTT2,BLSTT3,BLLDES,BLANB1,BLANB2,BLANB3,BLANB4,"+
	  "BLANB5,BLANB6,BLANN1,BLANN2,BLAND1,BLAND2,BLJRF1,BLJRF2,BLDREF,"+
	  "BLDDAT,BLSTAT,BLREAS,BLUSER,BLDATE,BLTIME,BLLOCK) VALUES (?, ?,"+
	  "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?,"+
	  "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"+
	  "?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
	  try {
		conn = DB2Utils.createConnection(JNDIDataSource);
		
		stmt = conn.prepareCall(command);
		stmt.setString(1, linea.getBlId());
		stmt.setString(2, linea.getBlPgm());
		stmt.setDouble(3, linea.getBlSeq());
		stmt.setDouble(4, linea.getBlLnum());
		stmt.setString(5, linea.getBlUs01());
		stmt.setString(6, linea.getBlUs02());
		stmt.setString(7, linea.getBlUs03());
		stmt.setString(8, linea.getBlUs04());
		stmt.setString(9, linea.getBlUs05());
		stmt.setString(10, linea.getBlUs06());
		stmt.setString(11, linea.getBlUs07());
		stmt.setString(12, linea.getBlUs08());
		stmt.setString(13, linea.getBlUs09());
		stmt.setString(14, linea.getBlUs10());
		stmt.setString(15, linea.getBlUs11());
		stmt.setString(16, linea.getBlUs12());
		stmt.setString(17, linea.getBlUs13());
		stmt.setString(18, linea.getBlUs14());
		stmt.setDouble(19, linea.getBlAf01());
		stmt.setDouble(20, linea.getBlAf02());
		stmt.setDouble(21, linea.getBlAf03());
		stmt.setDouble(22, linea.getBlAf04());
		stmt.setDouble(23, linea.getBlAf05());
		stmt.setDouble(24, linea.getBlAf06());
		stmt.setDouble(25, linea.getBlAf07());
		stmt.setDouble(26, linea.getBlAf08());
		stmt.setDouble(27, linea.getBlAf09());
		stmt.setDouble(28, linea.getBlAf10());
		stmt.setDouble(29, linea.getBlDrat());
		stmt.setDouble(30, linea.getBlCrat());
		stmt.setDouble(31, linea.getBlLtyp());
		stmt.setDouble(32, linea.getBlStt1());
		stmt.setDouble(33, linea.getBlStt2());
		stmt.setDouble(34, linea.getBlStt3());
		stmt.setString(35, linea.getBlLdes());
		stmt.setString(36, linea.getBlAnb1());
		stmt.setString(37, linea.getBlAnb2());
		stmt.setString(38, linea.getBlAnb3());
		stmt.setString(39, linea.getBlAnb4());
		stmt.setString(40, linea.getBlAnb5());
		stmt.setString(41, linea.getBlAnb6());
		stmt.setDouble(42, linea.getBlAnn1());
		stmt.setDouble(43, linea.getBlAnn2());
		stmt.setDouble(44, linea.getBlAnd1());
		stmt.setDouble(45, linea.getBlAnd2());
		stmt.setString(46, linea.getBlJrf1());
		stmt.setString(47, linea.getBlJrf2());
		stmt.setString(48, linea.getBlDref());
		stmt.setDouble(49, linea.getBlDdat());
		stmt.setDouble(50, linea.getBlStat());
		stmt.setString(51, linea.getBlReas());
		stmt.setString(52, linea.getBlUser());
		stmt.setDouble(53, linea.getBlDate());
		stmt.setDouble(54, linea.getBlTime());
		stmt.setDouble(55, linea.getBlLock());
			
		logger.debug("Inicia operación: " + command);
		stmt.execute();
		logger.debug("Finaliza operación: " + command);
	  } catch (SQLException ex) {
		ex.printStackTrace();
		int code=ex.getErrorCode();
		throw new BusinessException(PREFIX+code);
	  } finally {
		DB2Utils.closeAll(conn, stmt, ors);
	  }
	}
	
}
