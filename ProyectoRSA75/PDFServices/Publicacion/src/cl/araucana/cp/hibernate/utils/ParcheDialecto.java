package cl.araucana.cp.hibernate.utils;

import org.hibernate.dialect.DB2400Dialect;
/*
* @(#) ParcheDialecto.java 1.10 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/
/**
 * @author pfrigolett
 * 
 * @version 1.10
 */
public class ParcheDialecto extends DB2400Dialect {
	
	public String getSequenceNextValString(String arg0) {
		return "select nextval for " + arg0 + " FROM SYSIBM.SYSDUMMY1";
	}

}
