package cl.araucana.cp.hibernate.exceptions;

import org.apache.commons.lang.exception.NestableRuntimeException;
/*
* @(#) InfraestructuraException.java 1.1 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/
/**
 * @author cchamblas
 * 
 * @version 1.1
 */
public class InfrastructureException extends NestableRuntimeException 
{
	private static final long serialVersionUID = -585586630014618451L;

	public InfrastructureException() {}

	public InfrastructureException(String message) 
	{
		super(message);
	}

	public InfrastructureException(String message, Throwable cause) 
	{
		super(message, cause);
	}

	public InfrastructureException(Throwable cause) 
	{
		super(cause);
	}
}