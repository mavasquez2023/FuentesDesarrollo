package cl.araucana.cp.distribuidor.hibernate.utils;

import org.hibernate.dialect.DB2400Dialect;

public class ParcheDialecto extends DB2400Dialect {
	
	public String getSequenceNextValString(String arg0) {
		return "select nextval for " + arg0 + " FROM SYSIBM.SYSDUMMY1";
	}

}
