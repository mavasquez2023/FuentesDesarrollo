package cl.laaraucana.ventafullweb.dao.config;
import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
 public class SqlMapLocator { 
 	private static SqlMapLocator instance = null; 
 	private static SqlMapClient sqlMap = null; 
 	static { 
 		String resource = "cl/laaraucana/ventafullweb/dao/config/IbatisConfig.xml"; 
         Reader reader; 
 		try { 
 			reader = Resources.getResourceAsReader (resource); 
 	        sqlMap = (SqlMapClient) SqlMapClientBuilder.buildSqlMapClient(reader); 
 		} catch (IOException e) { 
 			e.printStackTrace(); 
 		} 
 	} 
 	public static SqlMapClient getInstance(){ 
 		if(instance == null) 
 			instance = new SqlMapLocator(); 
 		return sqlMap; 
 	}
 }