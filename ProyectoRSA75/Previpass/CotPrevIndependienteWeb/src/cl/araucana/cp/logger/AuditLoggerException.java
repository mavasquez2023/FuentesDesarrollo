package cl.araucana.cp.logger;
/*
* @(#) AuditLoggerException.java 1.4 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cllanos
 * @author cchamblas
 * 
 * @version 1.4
 */
public class AuditLoggerException extends Exception 
{
	private static final long serialVersionUID = -3781398354313054437L;

	public AuditLoggerException(String message) 
	{
		super(message);
	}
}
