package cl.araucana.cp.logger;
/*
* @(#) AuditLoggerException.java 1.4 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
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
