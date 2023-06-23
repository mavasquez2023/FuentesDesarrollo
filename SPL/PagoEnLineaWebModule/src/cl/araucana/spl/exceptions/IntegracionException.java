package cl.araucana.spl.exceptions;

public class IntegracionException extends PagoEnLineaException {
	private static final long serialVersionUID = 1L;

	public IntegracionException() {
		super();
	}

	public IntegracionException(String message, Throwable cause) {
		super(message, cause);
	}

	public IntegracionException(String message) {
		super(message);
	}

	public IntegracionException(Throwable cause) {
		super(cause);
	}
}
