package cl.araucana.cp.distribuidor.hibernate.exceptions;

public class UsuarioNoEncontradoException extends Exception
{
	private static final long serialVersionUID = -5144352038416762583L;

	public UsuarioNoEncontradoException() {
		super();
	}
	
	public UsuarioNoEncontradoException(String msje) {
		super(msje);
	}
	
	public UsuarioNoEncontradoException(String msje, Throwable causa) {
		super(msje, causa);
	}
}
