package cl.araucana.ldap.ibatis.config;
import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
 public class MyClassSqlConfig { 
 	private static MyClassSqlConfig instance = null; 
 	private static SqlMapClient sqlMap = null; 
 	private static MyClassSqlConfig instance2 = null; 
 	private static SqlMapClient sqlMap2 = null; 
 	
 	static { 
 		String resource = "cl/araucana/ldap/ibatis/config/sqlMap_config.xml";
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
 			instance = new MyClassSqlConfig(); 
 		return instance.sqlMap; 
 	} 
 	
 	static { 
 		String resource = "cl/araucana/ldap/ibatis/config/sqlMap_config2.xml";
         Reader reader; 
 		try { 
 			reader = Resources.getResourceAsReader (resource); 
 	        sqlMap2 = (SqlMapClient) SqlMapClientBuilder.buildSqlMapClient(reader); 
 		} catch (IOException e) { 
 			// TODO Auto-generated catch block 
 			e.printStackTrace(); 
 		} 	
 	} 
 	public static SqlMapClient getInstance2(){ 
 		if(instance2 == null) 
 			instance2 = new MyClassSqlConfig(); 
 		return instance2.sqlMap2; 
 	} 
 }