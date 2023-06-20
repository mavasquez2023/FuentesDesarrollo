package cl.laaraucana.reportesil.dao;

import java.sql.Connection;
import javax.sql.DataSource;

import cl.laaraucana.reportesil.ibatis.MyClassSqlConfig;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ReportDaoImpl implements ReportDao {
	
	
private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Connection getConnection() throws Exception {
		
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
			Connection conexion= sqlMap.getDataSource().getConnection();
			return conexion;
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
	}

}
