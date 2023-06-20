package cl.laaraucana.licenciasivr.ibatis.config;
import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
 public class SqlMapLocator { 
 	private static SqlMapLocator instance = null; 
 	private static SqlMapClient sqlMap = null; 
 	static { 
 		String resource = "cl/laaraucana/licenciasivr/ibatis/config/IbatisConfig.xml"; 
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
 		return sqlMap; 
 	}
 	
 	private static SqlMapLocator instanceAS400 = null; 
 	private static SqlMapClient sqlMapAS400 = null; 
 	static { 
 		String resource = "cl/laaraucana/licenciasivr/ibatis/config/IbatisConfig_AS400.xml"; 
         Reader reader; 
 		try { 
 			reader = Resources.getResourceAsReader (resource); 
 			sqlMapAS400 = (SqlMapClient) SqlMapClientBuilder.buildSqlMapClient(reader); 
 		} catch (IOException e) { 
 			// TODO Auto-generated catch block 
 			e.printStackTrace(); 
 		} 
 	} 
 	public static SqlMapClient getInstanceAS400(){ 
 		if(instanceAS400 == null) 
 			instanceAS400 = new SqlMapLocator(); 
 		return sqlMapAS400; 
 	}
 }