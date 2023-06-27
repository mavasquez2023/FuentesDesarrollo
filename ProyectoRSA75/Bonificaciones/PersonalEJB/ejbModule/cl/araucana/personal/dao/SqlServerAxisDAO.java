package cl.araucana.personal.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.araucana.personal.vo.BancoVO;

import com.schema.util.FileSettings;
/**
 * @author asepulveda
 *
 */
public class SqlServerAxisDAO implements AxisDAO {
	Logger logger = Logger.getLogger(SqlServerAxisDAO.class);
	private final static String PREFIX="SQLSERVER-";
	
	private String axisJNDIDataSource;
	
	private DataSource sqlServerDS;
	
	/**
	 * Constructor de DAO
	 * Recupera nombre de Bases de Datos utilizadas
	 */
	public SqlServerAxisDAO() throws NamingException{
		axisJNDIDataSource=FileSettings.getValue(AppConfig.getInstance().settingsFileName,
			 "/application-settings/jdbc/axis");
		
    	InitialContext ic = new InitialContext();
		sqlServerDS = (DataSource)ic.lookup(axisJNDIDataSource);		
	}
		
    /** 
     * Entrega la lista de bancos en un HashMap
     * 
     * @return HashMap de BancoVO
     * @throws Exception
     */
	public HashMap getListaBancos() throws Exception,BusinessException {

	   	 Connection con = null;
	   	 Statement stmt = null;
	   	 ResultSet result = null;
	   	 
	   	HashMap respuesta = new HashMap();
	   	 
	   	 try {
	   		 con = sqlServerDS.getConnection();
	   		 
	   		 String SQL = "SELECT TABACODBAN, "+
					       "TABADESAMP, "+
					       "TABADESRED, "+
					       "TABAABREVI "+
					"FROM   SYSBA01 ";	   		
	
	       	 logger.debug("CON:"+con);
	   		 stmt =  con.createStatement();
	       	 logger.debug("ST:"+stmt);
	   		 result = stmt.executeQuery( SQL );
	       	 logger.debug("RS:"+result);
	       	if ( result != null ) {
	       		 while (result.next()) {
	       			BancoVO banco  = buildBancoVO(result);
	       			respuesta.put(banco.getCodigo(), banco);
	       		 }
	       	 }
	   		 
	   	 } catch (Exception ex) {
	   		 logger.error(PREFIX + "Error : getListaBancos : ", ex);
	   		 throw ex;
	   	 } finally {
	   		 try { result.close(); } catch (Exception e) {}
	   		 try { stmt.close(); } catch (Exception e) {}
	   		 try { con.close(); } catch (Exception e) {}
	   	 }		
		
		return respuesta;

	}
	
    /**
     * Build para BancoVO
     * @param rs
     * @return
     * @throws SQLException
     */
   public static BancoVO buildBancoVO(ResultSet rs) throws SQLException{
	   BancoVO vo = new BancoVO();
	    	
	   vo.setCodigo(rs.getString("TABACODBAN"));
	   vo.setDescripcionLarca(rs.getString("TABADESAMP"));
	   vo.setDescripcionCorta(rs.getString("TABADESRED"));
	   vo.setAbreviacion(rs.getString("TABAABREVI"));

       return vo;
	}	



}
