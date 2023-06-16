package cl.laaraucana.simat.comun.conx;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import cl.laaraucana.simat.comun.Configuraciones;

public class ServiceLocator {
	public static final int DB2_DATA_SOURCE = 1;
	public static final int ORACLE_DATA_SOURCE = 2;
	public static final int MYSQL_DATA_SOURCE = 3;

	private static final String MYSQL_DATA_SOURCE_STR = "";

	private static ServiceLocator me;

	private ServiceLocator() {
	}

	public static ServiceLocator getInstance() {
		if (me == null) {
			me = new ServiceLocator();
		}
		return me;
	}

	public Object getService(int wichService) {
		switch (wichService) {
		case DB2_DATA_SOURCE: {
			return getService(Configuraciones.getConfig("jndiName"));
		}
		case MYSQL_DATA_SOURCE: {
			return getService(MYSQL_DATA_SOURCE_STR);
		}
		}
		return null;
	}

	private DataSource getService(String lookupName) {
		DataSource object = null;
		try {
			Context initial = new InitialContext();
			object = (DataSource) initial.lookup(lookupName);
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
		return object;
	}

}
