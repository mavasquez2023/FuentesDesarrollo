/**
 * 
 */
package com.araucana.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author 13247428-1
 *
 */
public class SessionFactory {
	private static SessionFactory instance= null;	
	private static SqlSessionFactory sqlSessionFactory;
	
	public SessionFactory() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	public static SqlSessionFactory getSessionFactory() {
		
		if(instance== null) {
			instance= new SessionFactory();
		}
		return sqlSessionFactory;
	}
}
