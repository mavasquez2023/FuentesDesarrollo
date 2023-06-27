package cl.araucana.cp.distribuidor.hibernate.exceptions;

public class InfrastructureException extends Exception 
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