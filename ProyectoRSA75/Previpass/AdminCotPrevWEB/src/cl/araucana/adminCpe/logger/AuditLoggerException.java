package cl.araucana.adminCpe.logger;
/*
* @(#) AuditLoogerException.java 1.3 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cllanos
 * @author cchamblas
 * 
 * @version 1.3
 */
public class AuditLoggerException extends Exception 
{
	private static final long serialVersionUID = -1587229780741899573L;

	/**
	 * 
	 * @param message
	 */
	public AuditLoggerException(String message) 
	{
		super(message);
	}
}
