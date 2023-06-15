package cl.araucana.ctasfam.integration.jdbc.dao.impl;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import cl.araucana.ctasfam.integration.jdbc.dao.GenericDao;

public abstract class GenericDaoImpl implements GenericDao {

	private Connection connection;

	public GenericDaoImpl(String dataSourceName) throws Exception {
		Context ct = new InitialContext();
		DataSource dataSource = (DataSource) ct.lookup("jdbc/" + dataSourceName);
		ct.close();
		this.connection = dataSource.getConnection();
	}

	protected Connection getConnection() {
		return this.connection;
	}

}