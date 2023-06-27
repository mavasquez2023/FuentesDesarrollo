package cl.araucana.adminCpe.hibernate.utils;

/*
* @(#) ParcheDialecto.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.4
 */

import org.hibernate.dialect.DB2400Dialect;

public class ParcheDialecto extends DB2400Dialect {
	
	/**
	 * @param
	 */
	public String getSequenceNextValString(String arg0) {
		return "select nextval for " + arg0 + " FROM SYSIBM.SYSDUMMY1";
	}

}
