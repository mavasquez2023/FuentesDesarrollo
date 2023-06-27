package cl.araucana.cp.hibernate.exceptions;

import org.apache.commons.lang.exception.NestableRuntimeException;
/*
* @(#) InfraestructuraException.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
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