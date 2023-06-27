package cl.araucana.adminCpe.logger;
/*
* @(#) AuditLoogerException.java 1.3 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
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
