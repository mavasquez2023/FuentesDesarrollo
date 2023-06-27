package cl.araucana.cp.distribuidor.hibernate.exceptions;

public class SeccionException extends Exception
{
	private static final long serialVersionUID = 4756731390837378894L;

	public SeccionException() {}

	public SeccionException(String message) 
    {
            super(message);
    }

    public SeccionException(String message, Throwable cause) 
    {
            super(message,cause);
    }

}
