package cl.araucana.cp.exceptions;

public class SesionException extends Exception 
{
	private static final long serialVersionUID = 8923951550855153527L;

	public SesionException() 
	{
		super();
	}

	public SesionException(String message, Throwable cause) 
	{
		super(message, cause);
	}

	public SesionException(String message) 
	{
		super(message);
	}

	public SesionException(Throwable cause) 
	{
		super(cause);
	}	
}
