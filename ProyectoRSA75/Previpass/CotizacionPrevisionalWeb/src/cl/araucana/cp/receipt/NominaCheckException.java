package cl.araucana.cp.receipt;

public class NominaCheckException extends Exception
{
	private static final long serialVersionUID = 7460890808955231709L;
	private int id;

	public NominaCheckException() 
	{
		super();
	}

	public NominaCheckException(int id, String message) 
	{
		super(message);
		this.id = id;
	}

	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
}
