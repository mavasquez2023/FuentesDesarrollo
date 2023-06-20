package cl.araucana.ldap.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import cl.araucana.ldap.ibatis.config.MyClassSqlConfig;
import cl.araucana.ldap.ibatis.vo.SucursalesVO;


import com.ibatis.sqlmap.client.SqlMapClient;

public class SucursalDB2DAO {
	
	private static Logger logger = Logger.getLogger(SucursalDB2DAO.class);

		@SuppressWarnings("unchecked")
		public static List<SucursalesVO> obtenerSucursales(HashMap<String, String> params){
			List<SucursalesVO> resultado = new ArrayList<SucursalesVO>();
			SqlMapClient sqlMap = MyClassSqlConfig.getInstance();
			try {
				resultado = (List<SucursalesVO>) sqlMap.queryForList("custom.oficinasysucursales", params);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resultado;
		}

		public static int ejecutarUpdate(String id,  Object data){
			SqlMapClient sqlMap = MyClassSqlConfig.getInstance();
			int resultado=0;
			try {
				resultado= sqlMap.update(id, data);
				logger.info("resultado update: "+ resultado);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resultado;
		}	
		
		public static int ejecutarInsert(String id,  Object data){
			SqlMapClient sqlMap = MyClassSqlConfig.getInstance();
			Integer resultado=0;
			try {
				resultado= (Integer)sqlMap.insert(id, data);
				
				resultado= resultado==null?0:resultado;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resultado;
		}
		
		public static int ejecutarDelete(String id,  Object data){
			SqlMapClient sqlMap = MyClassSqlConfig.getInstance();
			int resultado=0;
			try {
				resultado= sqlMap.delete(id, data);
				logger.info("resultado delete: "+ resultado);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resultado;
		}
		
		public static HashMap obtenerRegistro(String query, Object data){
			HashMap resultado = null;
			SqlMapClient sqlMap = MyClassSqlConfig.getInstance();
			
			try {
				resultado = (HashMap) sqlMap.queryForObject(query,data);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resultado;
		}
}
