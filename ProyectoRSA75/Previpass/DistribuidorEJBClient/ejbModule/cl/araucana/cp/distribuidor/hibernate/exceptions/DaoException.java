package cl.araucana.cp.distribuidor.hibernate.exceptions;



public class DaoException extends Exception
{
	private static final long serialVersionUID = -4887824850955139402L;

	public DaoException() {}

	public DaoException(String message) 
    {
            super(message);
    }

    public DaoException(String message, Throwable cause) 
    {
            super(message,cause);
    }
}
