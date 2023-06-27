package cl.araucana.spl.exceptions;

public class RendicionException extends PagoEnLineaException {
	private static final long serialVersionUID = 1L;

	public RendicionException() {
		super();
	}

	public RendicionException(String message, Throwable cause) {
		super(message, cause);
	}

	public RendicionException(String message) {
		super(message);
	}

	public RendicionException(Throwable cause) {
		super(cause);
	}
	
}
