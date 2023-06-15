package cl.araucana.wsempresa.ibatis.config;
import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
 public class SqlMapLocator { 
 	private static SqlMapLocator instance = null; 
 	private static SqlMapClient sqlMap = null; 
 	static { 
 		String resource = "cl/araucana/wsempresa/ibatis/config/sqlMap_config.xml"; 
         Reader reader; 
 		try { 
 			reader = Resources.getResourceAsReader (resource); 
 	        sqlMap = (SqlMapClient) SqlMapClientBuilder.buildSqlMapClient(reader); 
 		} catch (IOException e) { 
 			// TODO Auto-generated catch block 
 			e.printStackTrace(); 
 		} 
 	} 
 	public static SqlMapClient getInstance(){ 
 		if(instance == null) 
 			instance = new SqlMapLocator(); 
 		return instance.sqlMap; 
 	}
 }